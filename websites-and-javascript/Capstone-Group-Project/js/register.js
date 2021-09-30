$(document).ready(function() {
  $('#newUser').on('focusout', function() {
    checkName();
  });
  
  $('#newUsername').on('focusout', function() {
    checkUsername();
  });
  
  $('#newPassword').on('focusout', function() {
    checkPassword();
  });
  
  $('#confirmPassword').on('focusout', function() {
    checkMatchingPasswords();
  });
  
  $('#newEmail').on('focusout', function() {
    checkEmail();
  });
  
  // Allow enter button to submit form
  $('input').on('keyup', function(e) {
    if (e.keyCode === 13) {
      $('#registerUserButton').click();
    }
  });
  
  // Register button action
  $('#registerUserButton').on('click', function() {
    if (checkName() && checkUsername() && checkPassword() && checkMatchingPasswords() && checkEmail()) {
      $('#submission-error').hide();
      registerNewUser();
    }
    else {
      $('#submission-error').show();
    }
  });
  
  // Cancel button action
  $('#cancelButton').on('click', function() {
    window.location = "login.html";
  });
});

function checkName() {
  if (!$('#newUser').val()) {
    $('#no-name').show();
    return false; // unacceptable username
  }
  else {
    $('#no-name').hide();
    return true;
  }
}

function checkUsername() {
  let newUsername = $('#newUsername').val();
  // Make sure field is not blank
  if (!newUsername) {
    $('#no-username').show();
    return false;
  }
  else {
    if (!isInputAlphaNumeric(newUsername)) {
      $('#invalid-username').show();
      return false;
    } 
    else {
      $('#invalid-username').hide();
      $('#no-username').hide();
    }
  }
  
  // Make sure username does not already exists in system
  let data = JSON.parse(localStorage.getItem('userData'));
  for (let x in data) {
    if (data[x].username === newUsername) {
      $('#duplicate-username').show();
      return false;
    }
    else {
      $('#duplicate-username').hide();
    }
  }
  
  return true;
}

function checkPassword() {
  if (!$('#newPassword').val()) {
    $('#no-password').show();
    $('#invalid-password').hide();
    return false;
  }
  else {
    if (!isPasswdComplex($('#newPassword').val())) {
      $('#invalid-password').show();
      $('#no-password').hide();
      return false;
    }
    $('#invalid-password').hide();
    $('#no-password').hide();
    return true;
  }
}

function checkMatchingPasswords() {
  if ($('#newPassword').val() !== $('#confirmPassword').val()) {
    $('#different-passwords').show();
    return false;
  }
  else {
    $('#different-passwords').hide();
    return true;
  }
}

function checkEmail() {
  let newEmail = $('#newEmail').val();
  
  // Make sure field is not blank
  if (!newEmail) {
    $('#no-email').show();
    return false;
  }
  else {
    $('#no-email').hide();
  }
  
  // Make sure field is in email format
  let emailFormat = new RegExp('^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]');
  if (!emailFormat.test(newEmail)) {
    $('#invalid-email').show();
    return false;
  }
  else {
    $('#invalid-email').hide();
  }
  
  return true;
}

function registerNewUser() {
  // Make sure username does not already exists in system
  let users = JSON.parse(localStorage.getItem('userData'));
  
  const newUser = {
    name: $('#newUser').val(),
    username: $('#newUsername').val(),
    password: $('#newPassword').val(),
    email: $('#newEmail').val()
  }
  
  // add new user object to localstorage object
  users.push(newUser)
  localStorage.setItem('userData', JSON.stringify(users));
  
  // alert of registration and redirect to login page
  alert("You have been successfully registered. You will now be returned to the login page to login to the" +
      " application.");
  window.location = "login.html";
}

function isInputAlphaNumeric(username){
  var regExp = /^[a-zA-Z0-9]*$/;
  var validUserName = regExp.test(username);
  return validUserName;
}

//alphanumeric and a few special symbols allowed for passwords
function isPasswdComplex(password){
/*
  Method used to check a password complexity
*/
  var regExp = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%&*()\-\.\/\[\]\\\*\+\?\)\{\}\|]).{8,}/; 
  var validPassword = regExp.test(password);
  return validPassword;
}