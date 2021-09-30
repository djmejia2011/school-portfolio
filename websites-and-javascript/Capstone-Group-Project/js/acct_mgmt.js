$(document).ready(function () { 
    getEmail();
});

function getEmail() {
    let data = JSON.parse(localStorage.getItem("userData"));
    let username = sessionStorage.getItem("user");
    for (let x in data) {
        if (data[x].username === username) {
            document.getElementById('changeEmail').placeholder=data[x].email;
        }
    }
}

function updateUser() {
    let data = JSON.parse(localStorage.getItem("userData"));
    let username = sessionStorage.getItem("user");
    let fullName = sessionStorage.getItem("name");
    let passwd = $('#changePassword').val();
    let confirmPasswd = $('#confirmChangePassword').val();
    let email = $('#changeEmail').val();

    if (passwd === confirmPasswd) {
        
        if ($('#changeEmail').val() === "") {
            for (let x in data) {
                if (data[x].username === username) {
                    email = data[x].email;
                    break;
                }
            }
        }

        if (passwd === "") {
            for (let x in data) {
                if (data[x].username === username) {
                    passwd = data[x].password;
                    break;
                }
            }
        }
    
        let index = 0;
        for (let x in data) {
            if (data[x].username === username) {
                index = x;
            }
        }
    
        data.splice(index, 1);
        let userLength = data.length;
        
        data[userLength] = {
            "name": fullName,
            "username": username,
            "password": passwd,
            "email": email
        };

        localStorage.setItem("userData", JSON.stringify(data));
        pageRedirect(username);
    } 
    else {
        alert("Passwords do not match. Please re-enter information.")
    }

}

function pageRedirect(username) {
    var delay = 2000; // time in milliseconds

    // Display message
    alert("User " + username + " has been updated. Returning to home page.");
        
    setTimeout(function(){
        window.location = "home.html";
    },delay);
}