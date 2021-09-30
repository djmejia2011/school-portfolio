/* Compiler Theory and Design
   Dr. Duane J. Jarc 
   David Mejia - CMSC 430
   skeleton code was extended by adding additional
   		* tokens
   		* non terminals
		* productions
		* error recovery productions 
	project 2 was expanded to allow program to display output from expressions
	project 2 was then used to expand project 3
*/

%{

/*
included from project 3 skeleton code
*/
#include <iostream>
#include <string>
#include <vector>
#include <map>
#include <cmath>

using namespace std;

/*
included from project 3 skeleton code
*/
#include "values.h"
#include "listing.h"
#include "symbols.h"

double *passed_parameters; // added
int num_of_params = 0;		// added

int yylex();
void yyerror(const char* message);

/*
included from project 3 skeleton code
*/
Symbols<double> symbols; 
double result;

%}

%define parse.error verbose

%union
{
	CharPtr iden;
	Operators oper;
	double value;
}

/*
included from project 3 skeleton code
*/
%token <iden> IDENTIFIER
%token <value> INT_LITERAL BOOL_LITERAL REAL_LITERAL CASE
%token <oper> ADDOP MULOP RELOP REMOP EXPOP
%token ANDOP
%token BEGIN_ BOOLEAN END ENDREDUCE FUNCTION INTEGER IS REDUCE RETURNS

%type <value> body statement_ statement cases case reductions expression logical_or
relation term factor remainder exponent not_operator primary
%type <oper> operator

/************************************************************
* Tokens added based on the project requirements
************************************************************/
%token ARROW
%token ENDCASE
%token IF ENDIF ELSE THEN WHEN
%token OTHERS
%token REAL
//%token REAL_LITERAL BOOL_LITERAL
%token OROP NOTOP




/************************************************************
*   the following code was modified using recommended approach
************************************************************/
%%

function:	
	function_header optional_variable body {result = $3;}|   	/*optional variable was added*/
	function_header body {result = $2;}|						
	error ';'													/*error handling was added*/
;


function_header:
	FUNCTION IDENTIFIER parameters_o RETURNS type';' |			/*optional parameters were added*/
	error ';'													/*error handling was added*/
	;

optional_variable:												/*optional variable was added*/
	optional_variable variable | variable | error ';'			/*error handling was added*/
;

variable:									
	IDENTIFIER ':' type IS statement_  {symbols.insert($1, $5);}  /*Added*/
;

parameters_o:													/*optional parameter 0 or more was added*/
	optional_params | 
;

optional_params:
	optional_params ',' parameter |								/*optional parameter comma for more than 1*/
	parameter
;

parameter: /*Added for project 3*/														/*parameter terminal*/
	IDENTIFIER ':' type {symbols.insert($1, passed_parameters[num_of_params++]);} 
;

type:
	INTEGER |
	REAL |														/*REAL was added*/
	BOOLEAN 
;

body:
	BEGIN_ statement_ END ';' {$$ = $2;} 
; 

statement_:
	statement ';'|
	error ';' {$$ = 0;} 
;

/*modified for project 3*/
statement:  /*if then else case where added to the program*/
	expression |
	REDUCE operator reductions ENDREDUCE {$$ = $3;} |
	IF expression THEN statement_ ELSE statement_ ENDIF {		
		if($2 >= 1){
			$$ = $4;
		}else{
			$$ = $6;
		}
		;} |				
		
		/*if then else were addded*/
	
	/*I was not able to make the CASE work correctly, I tried many
	different ways but i'm missing something.*/

	CASE expression IS cases OTHERS ARROW statement_ ENDCASE {		
		if(isnan($4)){
			$$ = $4;
		}else{
			$$ = $7;			
		}
	;}	/*case is others were added*/


cases:
	cases case{
		if(isnan($1)){
			$$ = $1;
		}else{
			$$ = $2;
		}
	;} | %empty {$$=NAN;}
;

case:
	WHEN INT_LITERAL ARROW statement_ {
		if($<value>-2 == $<value>2){
			$$ = $4;
		}else{
			$$ = NAN;
		}
			
	}
;



operator:
	ADDOP |
	MULOP 
;

reductions: /*from project 3 skeleton*/
	reductions statement_ {$$ = evaluateReduction($<oper>0, $1, $2);} |
	{$$ = $<oper>0 == ADD ? 0 : 1;}
	;
	
expression: /*modified for project 3 to translate OR*/
	expression OROP logical_or {$$ = $1 || $3;} |				/*OROP was added*/
	logical_or
;

logical_or: /*modified for project 3 to translate AND*/
	logical_or ANDOP relation {$$ = $1 && $3;}|
	relation ;

relation: /*RELATIONAL OPERATORS*/
	relation RELOP term {$$ = evaluateRelational($1, $2, $3);} |
	term;

term: /*ADDITION from project 3 skeleton*/
	term ADDOP factor {$$ = evaluateArithmetic($1, $2, $3);} |
	factor ;
      
factor: /*MULTIPLICATION from project 3 skeleton*/
	factor MULOP remainder {$$ = evaluateArithmetic($1, $2, $3);} |										/*REMOP was added*/
	remainder ;

remainder: /*REMAINDER for project 3*/
	factor REMOP exponent {$$ = evaluateArithmetic($1, $2, $3);} |										/*REMOP was added*/
	exponent ;

exponent: /*EXPONENT for project 3*/
	not_operator |
	not_operator EXPOP exponent {$$ = evaluateArithmetic($1, $2, $3);} 								/*Exponent was added*/
;

not_operator:
	NOTOP not_operator {$$ = $2;} | 								/*NOTOP was added*/
	primary
;

primary:
	'(' expression ')' {$$ = $2;} |
	IDENTIFIER {if (!symbols.find($1, $$)) appendError(UNDECLARED, $1);} |
	INT_LITERAL | 
	REAL_LITERAL |   											/*real literal was added*/
	BOOL_LITERAL												/*boolean literal was added*/ ;
    
%%

void yyerror(const char* message)
{
	appendError(SYNTAX, message);
}
/*project 3 - modified to accept parameters*/
int main(int argc, char *argv[])    
{
	//argc shows 2 when only passed one parameter
	passed_parameters =  new double[argc];
	//int i = 0 doesn't work
	for(int i = 1; i<argc; i++){
		//cout << argv[i] << endl;
		passed_parameters[i-1] = atof(argv[i]);

	}
	firstLine();
	yyparse();
	if (lastLine() == 0)
		cout << "Result = " << result << endl;
	return 0;
} 
