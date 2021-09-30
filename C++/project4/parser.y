/* Compiler Theory and Design
   Dr. Duane J. Jarc 
   David Mejia - CMSC 430
   skeleton code was extended by adding additional
   		* tokens
   		* non terminals
		* productions
		* error recovery productions 

	project 2 was expanded with project 4 skeleton, semantic checks were
	added on productions.
*/

%{

#include <string>
#include <vector>
#include <map>

using namespace std;

#include "types.h"
#include "listing.h"
#include "symbols.h"

int yylex();
void yyerror(const char* message);

Symbols<Types> symbols;

%}

%define parse.error verbose

%union
{
	CharPtr iden;
	Types type;
}

%token <iden> IDENTIFIER
%token <type> INT_LITERAL NOTOP REAL_LITERAL BOOL_LITERAL

%token ADDOP MULOP RELOP ANDOP
%token BEGIN_ BOOLEAN END ENDREDUCE FUNCTION INTEGER IS REDUCE RETURNS

/************************************************************
* Tokens added based on the project requirements
************************************************************/
%token ARROW
%token CASE ENDCASE
%token IF ENDIF ELSE THEN WHEN
%token OTHERS
%token REAL
%token OROP REMOP EXPOP
/************************************************************
*
************************************************************/
%type <type> type function_header body statement statement_ reductions expression relation 
term logical_or exponent not_operator factor primary variable case_optional case

%%

function:	
	function_header optional_variable body {checkAssignment($1,$3,"Function Return");}|   					/*optional variable was added*/
	function_header body {checkAssignment($1,$2,"Function Return");}|
	error ';'																								/*error handling was added*/
;


function_header:
	FUNCTION IDENTIFIER parameters_o RETURNS type';' {$$ = $5;}|											/*optional parameters were added*/
	error ';' {$$ = MISMATCH;}																				/*error handling was added*/
	;

optional_variable:																							/*optional variable was added*/
	optional_variable variable | variable | error ';'														/*error handling was added*/
;

variable:									
	IDENTIFIER ':' type IS statement_{
		if (symbols.find($1, $$)){
			appendError(DUPLICATE_IDENTIFIER, $1);
		}else{
			checkAssignment($3, $5, "Variable Initialization");
			symbols.insert($1, $3);} 
		};
		
;

parameters_o:																								/*optional parameter 0 or more was added*/
	optional_params | 
;

optional_params:
	optional_params ',' parameter |																			/*optional parameter comma for more than 1*/
	parameter
;

parameter:																									/*parameter terminal*/
	IDENTIFIER ':' type
;

type:
	INTEGER {$$ = INT_TYPE;} |
	REAL {$$ = REAL_TYPE;}|																					/*REAL was added*/
	BOOLEAN {$$ = BOOL_TYPE;}
;

statement_:
	statement ';' {$$ = $1;}|
	error ';' {$$ = MISMATCH;}
;

statement:  /*if then else case where added to the program*/
	expression {$$ = $1;}|
	REDUCE operator reductions ENDREDUCE {$$ = $3;}|
	IF expression THEN statement_ ELSE statement_ ENDIF {$$ = checkIfStatement($2,$4,$6);}|					/*if then else were addded*/
	CASE expression IS case_optional OTHERS ARROW statement_ ENDCASE {$$ = checkCase($2,$4,$7);} 			/*case is others were added*/
	;

case_optional:
	case_optional case|																						/*optional case was added*/
	case | error ';' {$$ = MISMATCH;}
	;

case:
	WHEN INT_LITERAL ARROW statement_ {$$ = $4;}															/*case was added*/
;

body:
	BEGIN_ statement_ END ';' {$$ = $2;}
;

operator:
	ADDOP |
	MULOP |
;

reductions:
	reductions statement_ {$$ = checkArithmetic($1, $2);} |
	{$$ = INT_TYPE;} 
	;
	
expression:
	expression OROP logical_or {$$ = checkLogical($1, $3);}|												/*OROP was added*/
	logical_or
;

logical_or:
	logical_or ANDOP relation {$$ = checkLogical($1, $3);} |
	relation ;

relation:
	relation RELOP term {$$ = checkRelational($1, $3);}|
	term;

term:
	term ADDOP factor {$$ = checkArithmetic($1, $3);} |
	factor ;
      
factor:
	factor MULOP exponent {$$ = checkArithmetic($1, $3);} |
	factor REMOP exponent {$$ = checkRemOp($1, $3);} |														/*REMOP was added*/
	exponent ;

exponent:
	not_operator |
	not_operator EXPOP exponent {$$ = checkArithmetic($1, $3);} 											/*Exponent was added*/
;

not_operator:
	NOTOP not_operator {$$ = checkLogical($1, $2);} | 														/*NOTOP was added*/
	primary
;

primary:
	'(' expression ')' {$$ = $2;}|
	IDENTIFIER {if (!symbols.find($1, $$)) appendError(UNDECLARED, $1);} |
	INT_LITERAL {$$ = $1;}| 
	REAL_LITERAL {$$ = $1;}|   																				/*real literal was added*/
	BOOL_LITERAL {$$ = $1;}																					/*boolean literal was added*/ ;
    
%%

void yyerror(const char* message)
{
	appendError(SYNTAX, message);
}

int main(int argc, char *argv[])    
{
	firstLine();
	yyparse();
	lastLine();
	return 0;
} 
