# Log_in_form.py
# Author: David Mejia
# Date: May 05, 2020
# project 8 will create a log in form, this project will keep information of the user
# in a user object, also the failed log in attempts will be stored in a log object
# the index page will display the log in screen with the option button to log in or
# register

from flask import Flask, request, redirect, url_for, flash
# from wtforms import Form, StringField, PasswordField, validators
from ip2geotools.databases.noncommercial import DbIpCity
from flask_bcrypt import Bcrypt
import time
import secrets
import csv

b_crypt = Bcrypt()
list_of_items = list()
list_of_common_password = list()
SECRET_KEY = secrets.token_hex(16)

#this initilizes timer for 5 minutes
after_5_min = str(time.perf_counter() + 300)

#*****************************************************************************
#
#This section creates the classes for the program. user and log class
#
#*****************************************************************************
class user:
    """This class will save the information of the user"""
    def __init__(self, list_of_items):
        self.data = list_of_items
        self.username = self.data[0]
        self.password = self.data[1].replace("\n","",89)
        self.failed_counter = 0
        self.log = list()

        
class log:
    """this class will allow program to save log of login attempts"""
    def __init__(self, ip_address, date):
        self.ip = ip_address
        self.date = date
        self.latitude = str(DbIpCity.get(ip_address, api_key='free').latitude)
        self.longitude = str(DbIpCity.get(ip_address, api_key='free').longitude)
        
    def __str__(self):
        return ("Ip-Address: " + self.ip + 
        " Date: " + self.date + "\nLatitude: " +
        self.latitude + " Longitude: " + self.longitude)
        

#*****************************************************************************
#
#This section creates the functions to display the html on each page
#
#*****************************************************************************


def insert_header(title):
    """this function will insert the header portion of the HTML page"""
    html_code = '<!DOCTYPE html>'
    html_code += '<html lang="en">'
    html_code += '<head>'
    html_code += '<title>' + title + '</title>'
    html_code += '<!--style sheet will be hosted on my website djmejia.com to avoid broken link -->'
    html_code += '<link rel="stylesheet" type="text/css" href="http://djmejia.com/umgc/sdev300/styles2.css">'
    html_code += '</head>'
    return html_code

def open_file(file_name, listthing, user_class):
    """This function opens the file and appends it to the list that is passed on"""
    try:
        with open(file_name, 'r') as file:
            file_list = file.readlines()
            for line in file_list:
                listthing.append(user_class(line.split(",")))
    except IOError:
        print("File Not Found")
        exit()

def insert_login():
    """This function will display a form on the website, will ask the user for 
    username, and password"""
    html_code = '<h3>Please Log In</h3>'
    html_code += '<form action="/login" method="POST">'
    html_code += '<div><label for="username"> Username:</label>'
    html_code += '<input type="text" name="username" id="username" required></div>'
    html_code += '<div><label for="password"> Password:</label>'
    html_code += '<input type="password" name="password" id="password" required></div>'
    html_code += '<div class = "button"><button type="submit">Log in</button></div>'
    html_code += '</form>'
    return html_code
    
def insert_update_password():
    """This function will display a form on the website, will ask the user to update Password"""
    html_code = '<h3>Update Password</h3>'
    html_code += '<form action="/updated" method="POST">'
    html_code += '<div><label for="username"> Username:</label>'
    html_code += '<input type="text" name="username" id="username" required></div>'
    html_code += '<div><label for="password"> Current Password:</label>'
    html_code += '<input type="password" name="password" id="password" required></div>'
    html_code += '<div><label for="new_password"> New Password:</label>'
    html_code += '<input type="password" name="new_password" id="new_password" required></div>'
    html_code += '<div><label for="confirm_password"> Confirm New Password:</label>'
    html_code += '<input type="password" name="confirm_password" id="confirm_password" required></div>'
    html_code += '<div class = "button"><button type="submit">Update</button></div>'
    html_code += '</form>'
    return html_code

def insert_footer():
    """This function will write the footer into the page"""
    # needed to display current time on the website footer
    seconds = time.time()
    times = time.ctime(seconds)
    html_code = '<footer>'
    html_code += '<div class = "centered">Current date and time is: ' + times + '</div></footer>'
    return html_code
    
def insert_h1(h1_title):
    """This function insert a H1 title into the page"""
    html_code = '<h1>' + h1_title + '</h1>'
    return html_code
    
def insert_h2(h2_title):
    """This function insert a H2 title into the page"""
    html_code = '<h2>' + h2_title + '</h2>'
    return html_code

def insert_h3(h3_title):
    """This function insert a H3 title into the page"""
    html_code = '<h3>' + h3_title + '</h3>'
    return html_code
    
def insert_ending_tag():
    """This function insert the closing tags for the page"""
    html_code = '</html>'
    return html_code


#*****************************************************************************
#
#This section creates the routes of the website
#
#*****************************************************************************
# instantiate flask into app variable    
app = Flask(__name__)
app.secret_key = SECRET_KEY

# open and populate the file with all user information from the text file
open_file("./project8/webuser_data.txt", list_of_items, user)

# index page will display the log in form
@app.route('/index')
def indexPage():
    """This will display the main log in page"""
    myreply = '<!--Project 8 using flask to create a log in app form-->'
    myreply += insert_header('Project 8 - Log in App')
    myreply += '<body class= "container">'
    myreply += insert_h1("Project 8 - Log In App")
    myreply += insert_login()
    myreply += insert_footer()
    myreply += '</body>'
    myreply += insert_ending_tag()
    return myreply


# This function will display correct login confirmation back to user
@app.route('/login', methods = ['POST'])
def loginPage():
    myreply = '<!--Project 8 using flask to create a log in app form-->'
    myreply += insert_header('Project 8 - Login')
    myreply += '<body class= "container">'
    myreply += insert_h1("Project 8 - Log In App")
    
    seconds = time.time()
    times = time.ctime(seconds)
    # request information from form into function
    username = request.form['username']
    password = request.form['password']
    
    # check username and password against the login information found
    # in the webuser_data.txt file
    for user1 in range(len(list_of_items)):
        temp_username = list_of_items[user1].username == username
        temp_password = list_of_items[user1].password == password 
        # keeps count of failed login attempts
        temp_counter = list_of_items[user1].failed_counter
        # starts counter
        time_login = time.perf_counter()
       
        
        # if the counter of failed attempts is less than 10 or if the counter
        # has passed the 5 minute mark
        if list_of_items[user1].failed_counter < 10 or time_login > float(after_5_min) or (temp_username and temp_password):
            # if there is a match of username and password
            if temp_username and temp_password:
                # display message to user when successfully signin
                myreply += insert_h3("Login Successfull!")
                myreply += '<p>Welcome Back ' + username + '!</p><br>'
                # display failed attempt counter and related information
                myreply += '<p>Failed Login Attempts since your last visit ' + str(temp_counter) + '!</p>'
                # include ip info only after counter is greater or equal to 1
                if temp_counter>=1:
                    myreply += '<p>Failed Login Date: ' + str(list_of_items[user1].log[0].date) + '</p>'
                    myreply += '<p>From Ip: ' + str(list_of_items[user1].log[0].ip) + '</p>'
                    myreply += '<p>From Latitude: ' + str(list_of_items[user1].log[0].latitude) + '</p>'
                    myreply += '<p>From Longitude: ' + str(list_of_items[user1].log[0].longitude) + '</p>'
                # allow the user to update the password
                myreply += '<a href="update" title="update_password">Update Password</a>'
                myreply += insert_footer()
                myreply += '</body>'
                myreply += insert_ending_tag()
                list_of_items[user1].failed_counter = 0
                return myreply
            # if the username is found but wrong password is entered then the failed counter
            # will be increased by 1
            elif temp_username and not temp_password:
                list_of_items[user1].failed_counter += 1
                seconds = time.time()
                times = time.ctime(seconds)
                myreply += insert_h3("Login Failed!")
                myreply += '<p>Please Try Again</p>'
                myreply += '<p>After 10 failed login attempts this computer will be locked</p>'
                myreply += '<p>Failed Login ' + str(temp_counter+1) + ' out of 10!</p>'
                # every failed attempt will be recorded back to the log of the user
                list_of_items[user1].log.append(log(request.environ['REMOTE_ADDR'], times))
                myreply += '<a href="index" title="Try Again">Try Again</a>'
                # list_of_items[user1].ip = request.environ['REMOTE_ADDR']
                myreply += insert_footer()
                myreply += '</body>'
                myreply += insert_ending_tag()
                return myreply
        # if the counter of failed attempts is 10 or larger then computer access
        # will be locked until the 5 minutes from the begining of the session pass
        elif list_of_items[user1].failed_counter >= 10:
            myreply += insert_h2("Too Many Login Attempts!")
            myreply += insert_h3("This Computer Access Has been Blocked")
            myreply += '<p>Your IP address has been recorded</p>'
            # display the entire list in the log 
            for log1 in list_of_items[user1].log:
                myreply += '<p>' + str(log1) + '</p>'
            myreply += insert_footer()
            myreply += '</body>'
            myreply += insert_ending_tag()
            return myreply
        # throttling starts after 15 attempts and will sleep code for 30 seconds
        if list_of_items[user1].failed_counter >= 15:
            time.sleep(30)
    # if username is not found the following message is displayed to user        
    myreply += insert_h3("Username doesn't Exist!")
    myreply += '<p>Please Try Again</p>'
    myreply += '<a href="index" title="Try Again">Try Again</a>'
    myreply += insert_footer()
    myreply += '</body>'
    myreply += insert_ending_tag()
    return myreply
    


# this function will allow user to update the password
@app.route('/update')
def updatePage():
    myreply = '<!--Project 8 using flask to create a log in app form-->'
    myreply += insert_header('Project 8 - Update Password')
    myreply += '<body class= "container">'
    myreply += insert_h1("Project 8 - Log In App")
    myreply += insert_update_password()
    myreply += insert_footer()
    myreply += '</body>'
    myreply += insert_ending_tag()
    return myreply

# this function will check the information the user enters
# and validate the form. if user enters a password that is found in the 
# CommonPassword.txt or if the the password is too short or too long then a
# error message will be displayed.
@app.route('/updated', methods=['POST'])
def updatedPage():
    myreply = '<!--Project 8 using flask to create a log in app form-->'
    myreply += insert_header('Project 8 - Update Password')
    myreply += '<body class= "container">'
    myreply += insert_h1("Project 8 - Log In App")
    
    # open the common passwords and save it to the list of common passwords
    try:
        with open("./project8/CommonPassword.txt", 'r') as file:
            file_list = file.readlines()
            for line in file_list:
                list_of_common_password.append(line.replace("\n",""))
    except IOError:
        print("File Not Found")
        exit()
    # process the information from the form  
    username = request.form['username']
    password = request.form['password']
    new_password = request.form['new_password']
    confirm_password = request.form['confirm_password']
    # Checks to see if the password is common and if its the right size        
    is_common = new_password in list_of_common_password
    is_right_length = len(new_password) > 7 and len(new_password) < 65
    
    # The following for loop checks the username credentials and if new password
    # is valid based on the NIST SP800-63B
    for user2 in range(len(list_of_items)):
        temp_username1 = list_of_items[user2].username == username
        temp_password1 = list_of_items[user2].password == password
        # if conditionals to validate new password
        if temp_username1 and temp_password1:
            if new_password == confirm_password and not is_common and is_right_length:
                myreply += insert_h3('Password Has Been Updated!')
                list_of_items[user2].password = new_password
                myreply += '<a href="index" title="login">Log in</a>'
                break
            if new_password == confirm_password and is_common and is_right_length:
                myreply += insert_h3('Password is too common, Try again!')
                myreply += '<a href="update" title="update_password">Try Again</a>'
                break
            if new_password == confirm_password and not is_common and not is_right_length:
                myreply += insert_h3('New Password must be bigger than 8 and smaller than 64 characters')
                myreply += '<a href="update" title="update_password">Try Again</a>'
                break
            if new_password != confirm_password and not is_common and is_right_length:   
                myreply += insert_h3('new password and confirm password must match')
                myreply += '<a href="update" title="update_password">Try Again</a>'
                break
        else:
            myreply += insert_h3('Incorrect Credentials')
            myreply += '<a href="update" title="update_password">Try Again</a>'
            break
        
    myreply += insert_footer()
    myreply += '</body>'
    myreply += insert_ending_tag()
    return myreply
#*****************************************************************************
#
#                   RUN THE ENVIORENMENT
#
#*****************************************************************************

app.run(host='0.0.0.0', port= 8080)

