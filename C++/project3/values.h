// CMSC 430
// Duane J. Jarc

// This file contains function definitions for the evaluation functions
/* David Mejia
*  CMSC 430
*  Skeleton code was extended using recommended approach paper
*  
*/
typedef char* CharPtr;
/*more operators were added as required by project 3*/
enum Operators {LESS, GREATER, EQUAL, NOT_EQUAL, lESS_OR_EQUAL, GREATER_OR_EQUAL, ADD, SUBTRACT, MULTIPLY, DIVIDE, REMAINDER, EXPONENT};

int evaluateReduction(Operators operator_, int head, int tail);
int evaluateRelational(int left, Operators operator_, int right);
double evaluateArithmetic(double left, Operators operator_, double right);

