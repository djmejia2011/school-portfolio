# state_capitol_and_bird_aplication
# Author: David Mejia
# Date: April 3, 2020
# This program will display, sort state capitals and birds

user_selection = 0
# embedded state information Capital and Bird
state_info = [
    # STATE 	   Capital		Bird
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


def display_sorted_list():
    """displays the state information when user selects US States in alphabetical order"""
    for state in range(len(state_info)):
        print(state+1, "\b)", state_info[state][0] + ", State Capital: " +
              state_info[state][1] + ", State Bird: " + state_info[state][2])


def search_state_info(state_name):
    """searches for the state and displays the corresponding information about the state"""
    not_found = 0  # keeps track if there is any match in the search
    for state in range(len(state_info)):
        # if state is found the information will be displayed
        # if the state is not found error message will be displayed
        if state_name.lower() not in (state_info[state][0]).lower():
            not_found = 1
        if state_name.lower() in (state_info[state][0]).lower():
            print(state + 1, "\b)", state_info[state][0] + ", State Capital: " +
                  state_info[state][1] + ", State Bird: " + state_info[state][2])
            not_found = 0
            break
    if not_found == 1:
        print("\n*******State Not Found, Please Try Again.*******")


def update_state_bird(state_name,new_bird):
    """updates bird for a specific state"""
    not_found = 0  # keeps track if there is any match in the search
    for state in range(len(state_info)):
        # if state is found the bird will be updated for the specific state
        # if the state is not found error message will be displayed
        if state_name.lower() not in (state_info[state][0]).lower():
            not_found = 1
        if state_name.lower() in (state_info[state][0]).lower():
            state_info[state][2] = new_bird
            print("State Bird Updated: ", state + 1, "\b)", state_info[state][0] + ", State Capital: " +
                  state_info[state][1] + ", State Bird: " + state_info[state][2])
            not_found = 0
            break
    if not_found == 1:
        print("\n*******State Not Found, Please Try Again.*******")


while user_selection != 4:
    # displays welcome screen to user  and asks for input. if user enters a number greater than 4
    # program will display error message and start the loop again.
    print("\nWelcome to the State Capital and Bird Application\n")
    print("Please select from the following menu: ")
    print("\t1.Display all U.S. States in Alphabetical order along"
          " with Capital and Bird.")
    print("\t2.Search for a specific state and display the appropriate"
          " Capital and Bird.")
    print("\t3.Update a Bird for a specific State.")
    print("\t4.Exit the application.")

    # input validation
    try:
        user_selection = int(input("Selection: "))
    except ValueError:
        print("Integers Only Please")
        user_selection = 0

    # if user enters a number greater than 4 the loop will be restarted.
    if user_selection > 4 or user_selection <= 0:
        print("Please select a number from the menu. Try Again")
        user_selection = 0
    # if user enters 1 all US states will be displayed
    elif user_selection == 1:
        display_sorted_list()
    # if user enters 2 user will be asked for a state. then state information will be displayed
    elif user_selection == 2:
        user_state = input("Please Enter State: ")
        search_state_info(user_state)
    # if user enters 3 user will be asked for state and new bird name. updated state info will be displayed
    elif user_selection == 3:
        user_state = input("Please Enter State: ")
        user_bird = input("Please Enter new Bird: ")
        update_state_bird(user_state, user_bird)
#end of the program good bye
print("\nThank You for Using The State Capitol and Bird Application.")