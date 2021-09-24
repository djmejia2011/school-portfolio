# square_cube_application.py
# Author: David Mejia
# Date: April 3, 2020
# This program will generate the square and cube for integers between 1 and 100 and allow users to
# search, display union, intersection and difference of squares and cube sets

import math as m
import textwrap as tw

# create three empty sets to be filled later
integers = set()
squares = set()
cubes = set()

# fill the sets with integer, square and cube numbers
for numbers in range(1, 101):
    integers.add(numbers)
    squares.add(int(m.pow(numbers, 2)))
    cubes.add(int(m.pow(numbers, 3)))
# wrapper will be needed when program needs to display large number list
wrapper = tw.TextWrapper(width=120)


def display_squares_cubes():
    """Display the integers, squares and cubes from 1 to 100"""
    for x in range(1, 101):
        print("Int:", x, "", "\tSquare: ", int(m.pow(x, 2)), " ", "\tCube:", int(m.pow(x, 3)))


def search_set_for(integer):
    """This function will search for an specific integer and then display result"""
    if integer in integers:
        print("Int:", integer, "", "\tSquare: ", int(m.pow(integer, 2)), " ", "\tCube:", int(m.pow(integer, 3)))
    if integer not in integers:
        print("Number is not in the set of integers between 1 and 100, please try again.")

def display_union():
    """This will display the union of the squares and cubes"""
    union = squares.union(cubes)
    print("The union of squares and cubes is the following:")
    numbers_text = wrapper.wrap(text=str(sorted(union)))
    for number in numbers_text:
        print(number)


def display_intersection():
    """This will display the intersection of the squares and cubes"""
    intersection = squares.intersection(cubes)
    print("The intersection of squares and cubes is the following:")
    numbers_text = wrapper.wrap(text=str(sorted(intersection)))
    for number in numbers_text:
        print(number)


def display_difference():
    """This will display the difference of the squares and cubes"""
    difference = squares.symmetric_difference(cubes)
    print("The difference of squares and cubes is the following:")
    numbers_text = wrapper.wrap(text=str(sorted(difference)))
    for number in numbers_text:
        print(number)


"""program will start from here"""
user_selection = 0
while user_selection != 6:
    # displays welcome screen to user  and asks for input. if user enters a number greater than 6
    # program will display error message and start the loop again.
    print("\nWelcome to the square cube application\n")
    print("Please select from the following menu: ")
    print("\t1.Display the Square and Cube for Integers ranging from 1 to 100.")
    print("\t2.Search for sets for a specific Integer and display the Square and Cube values.")
    print("\t3.Display the Union of Square and Cube sets.")
    print("\t4.Display the Intersection of Square and Cube sets.")
    print("\t5.Display the Difference of Square and Cube sets.")
    print("\t6.Exit the Program")
    # input validation
    try:
        user_selection = int(input("Selection: "))
    except ValueError:
        print("Integers Only Please")
        user_selection = 0

    # if user enters a number greater than 6 or lower than 1 the loop will be restarted.
    if user_selection > 6 or user_selection <= 0:
        print("Please select a number from the menu. Try Again")
        user_selection = 0
    # if user enters 1 then the program will display the squares and cubes of the integers 1 to 100
    elif user_selection == 1:
        display_squares_cubes()
    # if user enters 2, program will ask for an integer between 1 and 100 and then display square and cube
    elif user_selection == 2:
        integer = 0
        try:
            integer = int(input("Please enter a number between 1 and 100: "))
        except ValueError:
            print("Integers Only Please")
        search_set_for(integer)
    # if user enters 3, program will display the union of the square and cube sets
    elif user_selection == 3:
        display_union()
    # if user enters 4, program will display the intersection of the square and cube sets
    elif user_selection == 4:
        display_intersection()
    # if user enters 5, program will display the difference of the square and cube sets
    elif user_selection == 5:
        display_difference()
#end of the program good bye
print("\nThank You for Using The Square Cube Application.")
