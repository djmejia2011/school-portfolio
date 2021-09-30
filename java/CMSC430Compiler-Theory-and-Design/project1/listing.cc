// Compiler Theory and Design
// Dr. Duane J. Jarc

// This file contains the bodies of the functions that produces the compilation
// listing

#include <cstdio>
#include <string>
#include <iostream>

using namespace std;

#include "listing.h"

static int lineNumber;
static string error = "";
static int totalErrors = 0;

static void displayErrors();

void firstLine()
{
	lineNumber = 1;
	printf("\n%4d  ",lineNumber);
}

void nextLine()
{
	displayErrors();
	lineNumber++;
	printf("%4d  ",lineNumber);
}

int lastLine()
{
	printf("\r");
	//displayErrors();
	printf("     \n");
	if(totalErrors > 0){
		cout << "Lexical Errors " << totalErrors<< "\n";
		printf("%s\n", "Syntax Errors 0");
		printf("%s\n", "Semantic Errors 0");
	}else{
		printf("%s\n", "Compiled Successfully");
	}
	

	return totalErrors;
}
    
void appendError(ErrorCategories errorCategory, string message)
{
	string messages[] = { "Lexical Error, Invalid Character ", "",
		"Semantic Error, ", "Semantic Error, Duplicate Identifier: ",
		"Semantic Error, Undeclared " };

	error += messages[errorCategory] + message + "\n";
	totalErrors++;
}

void displayErrors()
{
	if (error != "")
		printf("%s\n", error.c_str());	
		error = "";
}