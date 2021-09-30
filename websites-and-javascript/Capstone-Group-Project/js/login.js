$(document).ready(function() {
  $.getJSON('data/data.json', function(data) {
    // make sure localstorage objects exists with JSON data
    if (localStorage.getItem('userData') === null) {
      localStorage.setItem('userData', JSON.stringify(data['users']));
    }
    if (localStorage.getItem('appointmentData') === null) {
      localStorage.setItem('appointmentData', JSON.stringify(data['appointments']));
    }
    
    // Allow enter button to submit form
    $('input').on('keyup', function(e) {
      if (e.keyCode === 13) {
        $('#loginButton').click();
      }
    });
    
    // Sign in button action
    $('#loginButton').on('click', function() {
      verifyCredentials();
    });
    
    // Register button action
    $('#registerButton').on('click', function() {
      window.location = "register.html";
    });
  });
});

function verifyCredentials() {
  let username = $('#loginUsername').val();
  let password = $('#loginPassword').val();
  let data = JSON.parse(localStorage.getItem('userData'));
  let validUsernames = [];
  let correctPassword = "";
  let name = "";
  let email = "";
  
  for (let x in data) {
    validUsernames[x] = data[x].username;
    if (data[x].username === username) {
      correctPassword = data[x].password;
      name = data[x].name;
      email = data[x].email;
    }
  }
  
  if (validUsernames.includes(username)) {
    if (password === correctPassword) {
      sessionStorage.setItem("user", username);
      sessionStorage.setItem("name", name);
      sessionStorage.setItem("password", correctPassword);
      sessionStorage.setItem("email", email);
      window.location = "home.html";
    }
    else {
      alert("Invalid password. Try again.")
    }
  }
  else {
    alert("Invalid username. Try again or register for a new account.")
  }
}