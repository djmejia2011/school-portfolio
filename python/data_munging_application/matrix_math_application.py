# matrix_math_application.py
# Author: David Mejia
# Date: April 12, 2020
# This program will user 3x3 matrices from the user to add, subtract, and multiply

import numpy as np


def create_matrix():
    """Creates matrix using user input, Each row should be entered num[space]num[space]num"""
    first_row = input("Please enter first row of 3 numbers separated by a space: ").strip().split()
    second_row = input("Please enter second row of 3 numbers separated by a space: ").strip().split()
    third_row = input("Please enter third row of 3 numbers separated by a space: ").strip().split()

    # turn list of inputs into numbers using map()
    first_row = list(map(float, first_row))
    second_row = list(map(float, second_row))
    third_row = list(map(float, third_row))

    # using int list from above, create one single list
    array = first_row, second_row, third_row

    # turn list into array with numpy
    array = np.asarray(array)
    print("The matrix you enter is the following:")

    # displays the current array
    print_matrix(array)
    return array


def print_matrix(array):
    """prints array passed on by the user"""
    for row in array:
        for column in row:
            print(column, end="  ")
        print()


def do_operation(matrix1, matrix2, user_selection):
    """calculates and displays new matrix based on user selection """
    if user_selection == "a" or user_selection == "A":
        print("You selected Addition, your result is below:")
        # adds the two matrix, prints new matrix and transpose it
        calculated_matrix = np.add(matrix1, matrix2)
        print_matrix(calculated_matrix)
        do_transpose(calculated_matrix)

    if user_selection == "b" or user_selection == "B":
        print("You selected Subtraction, your result is below:")
        # adds the two matrix, prints new matrix and transpose it
        calculated_matrix = np.subtract(matrix1, matrix2)
        print_matrix(calculated_matrix)
        do_transpose(calculated_matrix)

    if user_selection == "c" or user_selection == "C":
        print("You selected Matrix Multiplication, your result is below:")
        # adds the two matrix, prints new matrix and transpose it
        calculated_matrix = np.matmul(matrix1, matrix2)
        print_matrix(calculated_matrix)
        do_transpose(calculated_matrix)

    if user_selection == "d" or user_selection == "D":
        print("You selected Element by element multiplication, your result is below:")
        # adds the two matrix, prints new matrix and transpose it
        calculated_matrix = np.multiply(matrix1, matrix2)
        print_matrix(calculated_matrix)
        do_transpose(calculated_matrix)


def do_transpose(array):
    """creates and transposes array and display row and column mean"""
    print("\nThe Transpose is:")
    # array is transpose into new matrix
    transpose_matrix = array.T
    print_matrix(transpose_matrix)
    print("\nThe row and column mean values of the results are:")

    # row mean is calculated and displayed
    row_mean = array.mean(axis=0)
    print("Row mean:")

    # column mean is calculated and displayed
    print_flat(row_mean)
    print("Column mean:")
    column_mean = array.mean(axis=1)
    print_flat(column_mean)


def print_flat(array):
    """cuts to two decimal spaces and prints out array"""
    # array is rounded to two decimal places and displayed separated by commas
    array = np.around(array, decimals=2)
    for i in array.flat:
        print(i, end=", ")
    print()


def display_app():
    """displays the app and asks the user for input"""
    print("\n*********Welcome to the Python Matrix Application*********")
    print("Do you want to play the Matrix Game?")
    # user will need to select either yes or no or a ValueError will be raised
    user_yes_no = input("Enter Y for Yes or N for No: ")
    print("")
    # while the user keeps entering y the program will keep looping.
    while user_yes_no == "y" or user_yes_no == "Y":
        print("Enter your First Matrix")
        first_matrix = create_matrix()
        print("")
        print("Enter your Second Matrix")
        second_matrix = create_matrix()
        print("")
        invalid = True
        # while the user enters wrong selection the loop will continue
        while invalid:
            print("\nSelect a Matrix operation from the list below: ")
            print("a) Addition\nb) Subtraction \nc) Matrix Multiplication \nd) Element by Element Multiplication")
            user_selection = input("Please enter selection: ").lower()
            # if the user doesn't select from the menu then the game will ask the user if it would like to play again
            if user_selection == "a" or user_selection == "b" or user_selection == "c" or user_selection == "d":
                print("")
                do_operation(first_matrix, second_matrix, user_selection)
                invalid = False
            else:
                print("Wrong selection.", user_selection, "is not in the menu\n")
                

        print("\nWould you like to Play Again? ")
        user_yes_no = input("Enter Y for Yes or N for No: ")
        
    else:
        # if user selets n then the program will be terminated
        if user_yes_no == "n" or user_yes_no == "N":
            print("\nWe hope you try our application in the future.")
        else:
            raise ValueError
    print("Thank you for playing the Python Matrix Application")


# this is where the program starts and catches any exception from the program
try:
    display_app()
except ValueError:
    print("Invalid Input, Please try again.")
except TypeError:
    print("Invalid Matrix, Please try again.")


