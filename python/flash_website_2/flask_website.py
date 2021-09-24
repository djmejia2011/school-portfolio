# Flask_website.py
# Author: David Mejia
# Date: May 02, 2020
# Project 7 will extend project 6, and include images, and a form using python 
# flask

from flask import Flask, request, redirect, url_for
import time
import secrets


# needed to display current time on the website footer
seconds = time.time()
time = time.ctime(seconds)

#List that will populate the table in the website index
content_list = state_info = [
    # STATE 	   Capitol		Bird
    ["Alabama", "Montgomery", "Yellowhammer"],
    ["Alaska", "Juneau", "Willow Ptarmigan"],
    ["Arizona", "Phoenix", "Cactus Wren"],
    ["Arkansas", "Little Rock", "Mockingbird"],
    ["California", "Sacramento", "California Valley Quail"],
    ["Colorado", "Denver", "Lark Bunting"],
    ["Connecticut", "Hartford", "American Robin"],
    ["Delaware", "Dover", "Blue Hen Chicken"],
    ["Florida", "Tallahassee", "Mockingbird"],
    ["Georgia", "Atlanta", "Brown Thrasher"],
    ["Hawaii", "Honolulu", "Hawaiian Goose"],
    ["Idaho", "Boise", "Mountain Bluebird"],
    ["Illinois", "Springfield", "Cardinal", ],
    ["Indiana", "Indianapolis", "Cardinal"],
    ["Iowa", "Des Moines", "Eastern Goldfinch"],
    ["Kansas", "Topeka", "Western Meadowlark"],
    ["Kentucky", "Frankfort", "Cardinal"],
    ["Louisiana", "Baton Rouge", "Eastern Brown Pelican"],
    ["Maine", "Augusta", "Chickadee"],
    ["Maryland", "Annapolis", "Baltimore Oriole"],
    ["Massachusetts", "Boston", "Chickadee"],
    ["Michigan", "Lansing", "American Robin"],
    ["Minnesota", "Saint Paul", "Common Loon"],
    ["Mississippi", "Jackson", "Mockingbird"],
    ["Missouri", "Jefferson City", "Bluebird"],
    ["Montana", "Helena", "Western Meadowlark"],
    ["Nebraska", "Lincoln", "Western Meadowlark"],
    ["Nevada", "Carson City", "Mountain Bluebird"],
    ["New Hampshire", "Concord", "Purple Finch"],
    ["New Jersey", "Trenton", "Eastern Goldfinch"],
    ["New Mexico", "Santa Fe", "Greater Roadrunner"],
    ["New York", "Albany", "Eastern Bluebird"],
    ["North Carolina", "Raleigh", "Cardinal"],
    ["North Dakota", "Bismarck", "Western Meadowlark"],
    ["Ohio", "Columbus", "Cardinal"],
    ["Oklahoma", "Oklahoma City", "Scissor-tailed Flycatcher"],
    ["Oregon", "Salem", "Western Meadowlark"],
    ["Pennsylvania", "Harrisburg", "Ruffed Grouse"],
    ["Rhode Island", "Providence", "Rhode Island Red"],
    ["South Carolina", "Columbia", "Carolina Wren"],
    ["South Dakota", "Pierre", "Ring-necked Pheasant"],
    ["Tennessee", "Nashville", "Mockingbird"],
    ["Texas", "Austin", "Mockingbird"],
    ["Utah", "Salt Lake City", "California Gull"],
    ["Vermont", "Montpelier", "Hermit Thrush"],
    ["Virginia", "Richmond", "Cardinal"],
    ["Washington", "Olympia", "Willow Goldfinch"],
    ["West Virginia", "Charleston", "Cardinal"],
    ["Wisconsin", "Madison", "American Robin"],
    ["Wyoming", "Cheyenne", "Western Meadowlark"]
]


def insert_header(title):
    """this function will insert the header portion of the HTML page"""
    html_code = '<!DOCTYPE html>'
    html_code += '<html lang="en">'
    html_code += '<head>'
    html_code += '<title>' + title + '</title>'
    html_code += '<!--style sheet will be hosted on my website djmejia.com to avoid broken link -->'
    html_code += '<link rel="stylesheet" type="text/css" href="http://djmejia.com/umgc/sdev300/styles.css">'
    html_code += '</head>'
    return html_code


  
def insert_nav():
    """this function will insert the navigation portion of the page"""
    html_code = '<nav>'
    html_code += '<a href="index" title="Home">Home</a>'
    html_code += '<a href="next_page" title="Next Page">Next Page</a>'
    html_code += '<a href="contact" title="Contact">Contact</a>'
    html_code += '<a href="gallery" title="Gallery">Gallery</a>'
    html_code += '<a href="http://umgc.edu" title="UMGC Home">UMGC Home</a>'
    html_code += '<a href="http://google.com" title="Google Home">Google Home</a>'
    html_code += '<a href="http://data.gov" title="Data.gov">Data.gov</a>'
    html_code += '</nav>'
    return html_code
        
        
def insert_table():
    """This function will use the state list and populate a table"""
    html_code = insert_h3("State Capitol and Birds")
    html_code += '<table>'
    html_code += '<thead>'
    html_code += "<tr><th>State</th><th>State Capitol</th><th>State Bird</th><th>State Flags</th></tr></thead>"
    for record in content_list:
        html_code += ('<tr><td>' + record[0] + '</td><td>' + record[1] + '</td><td>' + record[2] 
        + '</td><td><img src="https://flaglane.com/download/' + record[0].lower().replace(" ", "-",3) 
        + '-flag/' + record[0].lower().replace(" ", "-",3)
        + '-flag-small.png" alt="State Flag"></td>')
    html_code += '</table>'
    return html_code
    
    
def insert_description():
    """This function will insert a description of the main page"""
    html_code = insert_h2("Description:")
    html_code += "<p>Project 7 will expand on project 6. In this project I will be \
    adding a new column to my table that will include all the US State flags. on\
     the contact page, I will be creating a Contact Form. The information from the \
     form will be then saved in a local file called user_data.txt. I will also be including \
     a gallery page that will consist of 4 localy hosted images.</p>"
    return html_code
    
    
def insert_form():
    """This function will display a form on the website, will ask the user for 
    first name, last name, comments, and what state the user is located in"""
    html_code = '<h3>Contact Form for Comments</h3>'
    html_code += '<form action="/sent" method="POST">'
    html_code += '<div><label for="first_name"> First Name</label>'
    html_code += '<input type="text" name="first_name" id="first_name" required></div>'
    html_code += '<div><label for="last_name"> Last Name</label>'
    html_code += '<input type="text" name="last_name" id="last_name" required></div>'
    html_code += '<div><label for="comments"> Comments</label>'
    html_code += '<textarea name="comments" id="comments"></textarea></div>'
    html_code += '<div><label for="states"> Choose Your State</label>'
    html_code += '<select name="states" id="states">'
    
    for record in content_list:
        html_code += '<option value="' + record[0] + '">' + record[0] + '</option>'
        
    html_code += '</select></div>'
    html_code += '<div class = "button"><button type="reset">Reset Form</button></div>'
    html_code += '<div class = "button"><button type="submit">Submit</button></div>'
    html_code += '</form>'
    return html_code


def insert_list():
    """This function displays how unorder and order list function in html"""
    html_code = '<br><h3>Unorder List Examples Using Random Hex Keys</h3><ul>'
    html_code += '<li>' + (secrets.token_hex(16)) + '</li>'
    html_code += '<li>' + (secrets.token_hex(16)) + '</li>'
    html_code += '<li>' + (secrets.token_hex(16)) + '</li>'
    html_code += '<li>' + (secrets.token_hex(16)) + '</li></ul>'
    html_code += '<h3>Order List Examples Using Random Hex Keys</h3><ol>'
    html_code += '<li>' + (secrets.token_hex(16)) + '</li>'
    html_code += '<li>' + (secrets.token_hex(16)) + '</li>'
    html_code += '<li>' + (secrets.token_hex(16)) + '</li>'
    html_code += '<li>' + (secrets.token_hex(16)) + '</li></ol>'
    return html_code
    
def insert_footer():
    """This function will write the footer into the page"""
    html_code = '<footer>'
    html_code += '<div class = "centered">Current date and time is: ' + time + '</div></footer>'
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
    
def insert_picture(url):
    """"this function will insert a picture using local images"""
    html_code = '<img class = "gallery" src='+ url_for('static', filename = url) +' alt =' + url + ' >'
    return html_code
    
    
# instantiate flask into app variable    
app = Flask(__name__)


# index page will display the table
@app.route('/index')
def indexPage():
    myreply = '<!--Project 7 using flask to create a simple html website-->'
    myreply += insert_header('Project 7 - Flask Website')
    myreply += '<body class= "container">'
    myreply += insert_h1("Project 7 - Flask Website")
    myreply += insert_nav()
    myreply += insert_description()
    myreply += insert_table()
    myreply += '<p>Flag images Copyright of flaglane.com</p>'
    myreply += insert_footer()
    myreply += '</body>'
    myreply += insert_ending_tag()
    return myreply


# next_page will display the unorder and order list
@app.route('/next_page')
def nextPage():
    myreply = '<!--Project 7 using flask to create a simple html website-->'
    myreply += insert_header('Project 7 - Next Page')
    myreply += '<body class= "container">'
    myreply += insert_h1("Project 7 - Flask Website")
    myreply += insert_nav()
    myreply += insert_list()
    myreply += insert_footer()
    myreply += '</body>'
    myreply += insert_ending_tag()
    return myreply
    
    
# contact will display a form that will allow the user to send us information
@app.route('/contact')
def formPage():
    myreply = '<!--Project 7 using flask to create a simple html website-->'
    myreply += insert_header('Project 7 - Contact')
    myreply += '<body class= "container">'
    myreply += insert_h1("Project 7 - Flask Website")
    myreply += insert_nav()
    myreply += insert_form()
    myreply += insert_footer()
    myreply += '</body>'
    myreply += insert_ending_tag()
    return myreply  
    
# This function will display sent confirmation back to user
@app.route('/sent', methods = ['POST'])
def sentPage():
    # request information from form into function
    first_name = request.form['first_name']
    last_name = request.form['last_name']
    comments = request.form['comments']
    state = request.form['states']
    # This will add new user to the user_data.txt file
    # every new form filled will be appended to the file
    file = open("user_data.txt", "a")
    file.write("First Name: " + first_name)
    file.write("\nLast Name: " + last_name)
    file.write("\nComments: " + comments)
    file.write("\nUser State: " + state)
    file.write("\n****************************\n")
    file.close()
    # display sent page
    myreply = '<!--Project 7 using flask to create a simple html website-->'
    myreply += insert_header('Project 7 - Form Sent')
    myreply += '<body class= "container">'
    myreply += insert_h1("Project 7 - Flask Website")
    myreply += insert_nav()
    myreply += insert_h1("Thank You " + first_name)
    myreply += insert_h3("Your Form Has been Sent Succesfully!")
    myreply += insert_footer()
    myreply += '</body>'
    myreply += insert_ending_tag()
    return myreply
    
    
@app.route('/gallery')
def galleryPage():
    myreply = '<!--Project 7 using flask to create a simple html website-->'
    myreply += insert_header('Project 7 - Gallery')
    myreply += '<body class= "container">'
    myreply += insert_h1("Project 7 - Flask Website")
    myreply += insert_nav()
    myreply += insert_h2("Gallery - Desktop Wallpapers")
    myreply += insert_picture("picture1.jpg")
    myreply += insert_picture("picture2.jpg")
    myreply += insert_picture("picture3.jpg")
    myreply += insert_picture("picture4.jpg")
    myreply += insert_footer()
    myreply += '</body>'
    myreply += insert_ending_tag()
    return myreply
    


# run enviorenment    
app.run(host='0.0.0.0', port= 8080)

