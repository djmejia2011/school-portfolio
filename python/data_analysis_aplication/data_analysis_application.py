# data_analysis_application.py
# Author: David Mejia
# Date: April 19, 2020
# This program will read information from a csv file and ask the user for columns that need to be
# analysed. for every column the user analyses a svg of a histogram will be created.

import pandas as pd
import matplotlib.pyplot as plt
import csv

# these list will be needed to store the information from each object
housing = list()
pop_change = list()


class PopChangeRecord:
    """This class will read the information from the CSV file"""
    def __init__(self, line):
        self.data = line
        self.id = self.data[0]
        self.geography = self.data[1]
        self.target_geo_id = self.data[2]
        self.target_geo_id2 = self.data[3]
        self.pop_apr_1 = int(self.data[4])
        self.pop_jul_1 = int(self.data[5])
        self.change_pop = int(self.data[6])

    def to_list(self):
        """this function returns a list of the object"""
        list_of_items = [self.id, self.geography, self.target_geo_id, self.target_geo_id2, self.pop_apr_1,
                         self.pop_jul_1, self.change_pop]
        return list_of_items


class HousingRecord:
    """This class will read the information from the CSV file"""
    def __init__(self, line):
        self.data = line
        self.age = int(self.data[0])
        self.bedrms = int(self.data[1])
        self.built = int(self.data[2])
        self.nunits = int(self.data[3])
        self.rooms = int(self.data[4])
        self.weight = float(self.data[5])
        self.utility = float(self.data[6])

    @property
    def age(self):
        return self._age

    @age.setter
    def age(self, age):
        if age < 0:
            self.age = 0
        else:
            self._age = age

    def to_list(self):
        """this function returns a list of the object"""
        list_of_items = [self.age, self.bedrms, self.built, self.nunits, self.rooms, self.weight, self.utility]
        return list_of_items


def display_analysis(data_frame, column_name):
    """This function was created to display the analysis of each column """
    print(f"{column_name} Count: {data_frame[column_name].count()}")
    print(f"Mean: {data_frame[column_name].mean()}")
    print(f"Standard Deviation: {data_frame[column_name].std()}")
    print(f"Min: {data_frame[column_name].min()}")
    print(f"Max: {data_frame[column_name].max()}")


def create_svg(data_frame, column_name):
    """This function creates the svg using the information from the database"""
    plt.hist(data_frame[column_name], density=True, facecolor='g', alpha=0.75)
    plt.autoscale()
    plt.grid(True)
    fig1 = plt
    fig1.savefig(f'figure_{column_name}.svg')


def open_file(file_name, list_name, record_class):
    """This function opens the file and appends it to the list that is passed on"""
    try:
        with open(file_name, 'r') as file:
            reader = csv.reader(file, delimiter=',')
            next(reader)
            for line in reader:
                # this will append a new record to the corresponding class
                list_name.append(record_class(line))
    except IOError:
        print("File Not Found")
        exit()
        
        
# **************************program starts here **********************
while True:
    print("**********Welcome to the Python Data Analysis App***************\n")
    print("Select the file you want to analyze:")
    print("\t1) Population Data")
    print("\t2) Housing Data")
    print("\t3) Exit the Program")
    try:
        file_selection = int(input("Please Enter Your Selection: "))
    except ValueError:
        print("Input Is Not a Number")
        file_selection = 0

    # if user selects one then open the file from PopChange.csv
    if file_selection == 1:
        open_file("./project5/PopChange.csv", pop_change, PopChangeRecord)
        # list to help create dataframe
        pop_change_list = list()
        for record in pop_change:
            pop_change_list.append(record.to_list())
        # create dataframe and assign the columns
        pop_change_data_frame = pd.DataFrame(pop_change_list,
                                             columns=["Id", "Geography", "Target Geo Id", "Target Geo Id2",
                                                      "Pop Apr 1", "Pop Jul 1", "Change Pop"])
        while True:
            print("\nYou have entered Population Data.")
            print("Select the Column you want to analyze")
            print("\ta) Pop Apr 1")
            print("\tb) Pop Jul 1")
            print("\tc) Change Pop")
            print("\td) Exit Column")
            column_selection = input("Please Enter Your Selection: ")
            # user will select from the following operations
            if column_selection.lower() == "a":
                print("You selected Pop April 1")
                print("The statistics for this column are:\n")
                display_analysis(pop_change_data_frame, "Pop Apr 1")
                create_svg(pop_change_data_frame, "Pop Apr 1")
                print("Your Histogram 'figure_Pop Apr 1.svg' has been saved\n")
            elif column_selection.lower() == "b":
                print("You selected Pop Jul 1")
                print("The statistics for this column are:\n")
                display_analysis(pop_change_data_frame, "Pop Jul 1")
                create_svg(pop_change_data_frame, "Pop Jul 1")
                print("Your Histogram 'figure_Pop Jul 1.svg' has been saved\n")
            elif column_selection.lower() == "c":
                print("You selected Change Pop")
                print("The statistics for this column are:\n")
                display_analysis(pop_change_data_frame, "Change Pop")
                create_svg(pop_change_data_frame, "Change Pop")
                print("Your Histogram 'figure_Pop Jul 1.svg' has been saved\n")
            elif column_selection.lower() == "d":
                print("****Exiting back to main menu.****")
                break
            else:
                print(f"\nIncorrect input {column_selection}, Please Try Again\n")
    # if user selects 2 then Housing.csv will be loaded to the program
    elif file_selection == 2:
        open_file("./project5/Housing.csv", housing, HousingRecord)
        # list that will help create dataframe
        housing_list = list()
        for thing in housing:
            housing_list.append(thing.to_list())
        # creation of data frame using the list from above
        housing_data_frame = pd.DataFrame(housing_list,
                                          columns=["Age", "BedRms", "Built", "Nunits", "Rooms", "Weight", "Utility"])
        while True:
            print("\nYou have entered Housing Data.")
            print("Select the Column you want to analyze")
            print("\ta) Age")
            print("\tb) BedRms")
            print("\tc) Built")
            print("\td) Rooms")
            print("\te) Utility")
            print("\tf) Exit Column")
            column_selection = input("Please Enter Your Selection: ")
            # user will select from the following operations
            if column_selection.lower() == "a":
                print("You selected Age")
                print("The statistics for this column are:\n")
                display_analysis(housing_data_frame, "Age")
                create_svg(housing_data_frame, "Age")
                print("Your Histogram 'figure_age.svg' has been saved\n")
            elif column_selection.lower() == "b":
                print("You selected BedRms")
                print("The statistics for this column are:\n")
                display_analysis(housing_data_frame, "BedRms")
                create_svg(housing_data_frame, "BedRms")
                print("Your Histogram 'figure_BedRms.svg' has been saved")
            elif column_selection.lower() == "c":
                print("You selected Built")
                print("The statistics for this column are:\n")
                display_analysis(housing_data_frame, "Built")
                create_svg(housing_data_frame, "Built")
                print("Your Histogram 'figure_Built.svg' has been saved")
            elif column_selection.lower() == "d":
                print("You selected Rooms")
                print("The statistics for this column are:\n")
                display_analysis(housing_data_frame, "Rooms")
                create_svg(housing_data_frame, "Rooms")
                print("Your Histogram 'figure_Rooms.svg' has been saved")
            elif column_selection.lower() == "e":
                print("You selected Utility")
                print("The statistics for this column are:\n")
                display_analysis(housing_data_frame, "Utility")
                create_svg(housing_data_frame, "Utility")
                print("Your Histogram 'figure_Utility.svg' has been saved")
            elif column_selection.lower() == "f":
                print("****Exiting back to main menu.****")
                break
            else:
                print(f"\nIncorrect input {column_selection}, Please Try Again\n")
    elif file_selection == 3:
        print("****You have selected to Exit****")
        print("Thank you for using our application.")
        break
    else:
        print("\nIncorrect input Please Try Again\n")
