# data_munging_application.py
# Author: David Mejia
# Date: April 12, 2020
# This program will demonstrate the use of numpy, pandas and re modules to format and eliminate records
# that are not correct from the data base of fake contacts.

import numpy as np
import pandas as pd
import re

# hard wire data with fake contact information, some fields are not correct
fake_info = np.array((["Kendra", "Gorham", "682936288", "4028723922"],
        ["Omar", "Casto", "200929364", "2022549275"],
        ["Matrika", "Caviness", "653518336", "4172382981"],
        ["Armand", "Han", "607688968", "6309478813"],
        ["156", "Caviness", "995550990", "9075813114"],
        ["Marvyn", "Laughlin", "384676195", "6155534950"],
        ["Neel", "Bridgman", "299831202", "843301-3378"],
        ["Walker", "Batey", "594065422", "406767ader"],
        ["Wayne", "Beaver", "2012667752", "4136294239"],
        ["Donna", "Grijalva", "198250809", "3021359029"],
        ["Borg", "Lorenzana", "267994450", "819707671"],
        ["Tuyen", "Vanorden", "379384785", "931xxx7410"],
        ["Malila","Militello", "578955363", "6050840606"],
        ["Donna", "Casey", "725215072", "4796323328"],
        ["owen", "uglisi", "252820425", "3040555742"],
        ["Ziazan", "Bridgman", "s719035726", "8701s155250"],
        ["Xuxa", "Haw2rter", "737695674", "4053940001"],
        ["Cantara", "Lafreniere", "290812065", "8033753520"],
        ["Adiva", "eirce", "200931542", "2021159590"],
        ["Aila", "Brockman", "577104040", "6057406859"],
        ["Saxon", "Morehead", "259asd2433", "(681)2483874"],
        ["Lauchlan", "Vanorden", "576946942", "6050549588"],
        ["Malise", "Bridwell", "732584423", "4056301041"],
        ["Luc", "Batey", "85271054", "8654005888"],
        ["Carolyn", "Burress", "257714436", "3016336105"],
        ["David", "Mejia", "20743", "2022132915"]))


def format_phone(phone_number):
    """This methods allows the program to format the phone numbers in the correct xxx-xxx-xxxx"""
    formatted = re.fullmatch(r"(\d{3})(\d{3})(\d{4})", phone_number)
    return '-'.join(formatted.groups()) if formatted else " "


def format_name(name):
    """This method searches for any non numeric values in the name fields"""
    formatted = re.fullmatch(r"([A-Z]?[a-z]+)", name)
    return "".join(formatted.groups()) if formatted else " "


def format_zipcode(zipcode):
    """this method looks at the data and formats it into zipcode+4 """
    formatted = re.fullmatch(r"(\d{5})(\d{4}|\s*)", zipcode)
    return '-'.join(formatted.groups()) if formatted else " "


def insert_formatted_data(data_frames, column, format_function):
    """this method allows insertion of the new formatted fields into the data frame"""
    corrected = data_frames[column].map(format_function)
    data_frame[column] = corrected
    return corrected


# create data frame using the information from the array
data_frame = pd.DataFrame(fake_info, columns=["FirstName", "LastName", "Zipcode+4", "PhoneNumber"])
print("\nData Before Reformatting")
print(data_frame)
print()
# this will modify the data frame to its correct formats
insert_formatted_data(data_frame, "PhoneNumber", format_phone)
insert_formatted_data(data_frame, "Zipcode+4", format_zipcode)
insert_formatted_data(data_frame, "FirstName", format_name)
insert_formatted_data(data_frame, "LastName", format_name)
# after modification the new dataframe will be displayed back to screen
print("\nData after reformatting")
print(data_frame)






