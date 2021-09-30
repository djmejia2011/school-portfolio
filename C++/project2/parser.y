/* Compiler Theory and Design
   Dr. Duane J. Jarc 
   David Mejia - CMSC 430
   skeleton code was extended by adding additional
   		* tokens
   		* non terminals
		* productions
		* error recovery productions 
*/

%{

#include <string>

using namespace std;

#include "listing.h"

int yylex();
void yyerror(const char* message);

%}

%define parse.error verbose

%token IDENTIFIER
%token INT_LITERAL
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
%token REAL_LITERAL BOOL_LITERAL
%token OROP NOTOP REMOP EXPOP
/************************************************************
*
************************************************************/


%%

function:	
	function_header optional_variable body |   					/*optional variable was added*/
	function_header body |
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
	IDENTIFIER ':' type IS statement_ 
;

parameters_o:													/*optional parameter 0 or more was added*/
	optional_params | 
;

optional_params:
	optional_params ',' parameter |								/*optional parameter comma for more than 1*/
	parameter
;

parameter:														/*parameter terminal*/
	IDENTIFIER ':' type
;

type:
	INTEGER |
	REAL |														/*REAL was added*/
	BOOLEAN 
;

statement_:
	statement ';'|
	error ';' 
;

statement:  /*if then else case where added to the program*/
	expression |
	REDUCE operator reductions ENDREDUCE |
	IF expression THEN statement_ ELSE statement_ ENDIF |				/*if then else were addded*/
	CASE expression IS case_optional OTHERS ARROW statement_ ENDCASE  	/*case is others were added*/
	;

case_optional:
	case_optional case|												/*optional case was added*/
	case | error ';'
	;

case:
	WHEN INT_LITERAL ARROW statement_							/*case was added*/
;

body:
	BEGIN_ statement_ END ';'
;

operator:
	ADDOP |
	MULOP |
;

reductions:
	reductions statement_ |
	;
	
expression:
	expression OROP logical_or |								/*OROP was added*/
	logical_or
;

logical_or:
	logical_or ANDOP relation |
	relation ;

relation:
	relation RELOP term |
	term;

term:
	term ADDOP factor |
	factor ;
      
factor:
	factor MULOP exponent |
	factor REMOP exponent |										/*REMOP was added*/
	exponent ;

exponent:
	not_operator |
	not_operator EXPOP exponent 								/*Exponent was added*/
;

not_operator:
	NOTOP not_operator | 								/*NOTOP was added*/
	primary
;

primary:
	'(' expression ')' |
	IDENTIFIER |
	INT_LITERAL | 
	REAL_LITERAL |   											/*real literal was added*/
	BOOL_LITERAL												/*boolean literal was added*/ ;
    
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
