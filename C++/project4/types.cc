// Compiler Theory and Design
// Duane J. Jarc

// This file contains the bodies of the type checking functions

// David Mejia - CMSC 430
// project 4 skeleton was expanded, semantic checks in
// form of functions were added.

#include <string>
#include <vector>
#include <iostream>

using namespace std;

#include "types.h"
#include "listing.h"

void checkAssignment(Types lValue, Types rValue, string message)
{
	//assignment checker was modified to allow program
	// to check for illegal narrowing and type mismatch
	if (lValue != MISMATCH && rValue != MISMATCH && lValue != rValue){
		if (rValue == REAL_TYPE){
			appendError(GENERAL_SEMANTIC, "Illegal Narrowing " + message);
		}else
		appendError(GENERAL_SEMANTIC, "Type Mismatch on " + message);

	}
}


Types checkArithmetic(Types left, Types right)
{
	// arithmetic checker was expanded to allow program
	// to detect non numeric types and display semantic errors
	// and type coercion
	if(left == INT_TYPE && right == REAL_TYPE){
			return REAL_TYPE;
	}else if(right == INT_TYPE && left == REAL_TYPE){
			return REAL_TYPE;
	}
	if (left == MISMATCH || right == MISMATCH)
		return MISMATCH;
	if (left == BOOL_TYPE || right == BOOL_TYPE)
	{
		appendError(GENERAL_SEMANTIC, "Numeric Type Required");
		return MISMATCH;
	}
	return INT_TYPE;
}


Types checkLogical(Types left, Types right)
{
	if (left == MISMATCH || right == MISMATCH)
		return MISMATCH;
	if (left != BOOL_TYPE || right != BOOL_TYPE)
	{
		appendError(GENERAL_SEMANTIC, "Boolean Type Required");
		return MISMATCH;
	}	
	return BOOL_TYPE;
	//return MISMATCH;
}

Types checkRelational(Types left, Types right)
{
	if (checkArithmetic(left, right) == MISMATCH)
		return MISMATCH;
	return BOOL_TYPE;
}

/*
function allows program to detect whenever an integer is not used
when doing rem operation
*/
Types checkRemOp(Types left, Types right){
	if(left != INT_TYPE || right != INT_TYPE){
		appendError(GENERAL_SEMANTIC, "Integer Type Required");
		return MISMATCH;
	}
	return INT_TYPE;
}

/*
function checks that expression in if statement is boolean and
return types of if statements are the same
*/
Types checkIfStatement(Types left, Types middle, Types right){
	if (left != BOOL_TYPE){
		appendError(GENERAL_SEMANTIC, "If expression Must be a Boolean");
	}
	else if(middle != right){
		appendError(GENERAL_SEMANTIC, "If-Then Type Mismatch");
	}else if(middle == right){
		return middle;
	}
	return MISMATCH;

}


/*
function checks that expression in case is integer and
that case return type is the same on all cases.
*/
Types checkCase(Types left, Types middle, Types right){
	if (left != INT_TYPE){
		appendError(GENERAL_SEMANTIC, "Case Expression Not Integer");
		return MISMATCH;
	}else{
		if(middle != right){
			appendError(GENERAL_SEMANTIC, "Case Types Mismatch");
			return MISMATCH;
		}
	}return right;
}
