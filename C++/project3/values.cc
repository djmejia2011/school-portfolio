// CMSC 430
// Duane J. Jarc

// This file contains the bodies of the evaluation functions

/* David Mejia
*  CMSC 430
*  Skeleton code was extended using recommended approach paper
*  the following was changed
*  arithmetic operators
*  relational operators
*/


#include <string>
#include <vector>
#include <cmath>
#include <iostream>

using namespace std;

#include "values.h"
#include "listing.h"


int evaluateReduction(Operators operator_, int head, int tail)
{
	if (operator_ == ADD)
		return head + tail;
	return head * tail;
}

/*relational operators were added based on project 3 requirements*/
int evaluateRelational(int left, Operators operator_, int right)
{
	int result;
	switch (operator_)
	{
		case LESS:
			result = left < right;
			break;
		case GREATER:
			result = left > right;
			break;
		case EQUAL:
			result = left = right;
			break;
		case NOT_EQUAL:
			result = left /= right;
			break;
		case lESS_OR_EQUAL:
			result = left <= right;
			break;
		case GREATER_OR_EQUAL:
			result = left >= right;
			break;
				
	}
	return result;
}

/*arithmetic operators were added based on project 3 requirements*/
double evaluateArithmetic(double left, Operators operator_, double right)
{
	double result;
	switch (operator_)
	{
		case ADD:
			result = left + right;
			break;
		case SUBTRACT:
			result = left - right;
			break;		
		case MULTIPLY:
			result = left * right;
			break;	
		case DIVIDE:
			result = left / right;
			break;
		case REMAINDER:
			result = (int)left % (int)right;
		break;
		case EXPONENT:
			result = pow((double)left, (double)right);
			break;
	}
	return result;
}

