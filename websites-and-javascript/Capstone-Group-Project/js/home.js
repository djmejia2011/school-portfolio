$(document).ready(function() {
  // Add action to make appointment button
  $('#scheduleRedirectButton').on('click', function() {
    window.location = "scheduleSession.html";
  });
  
  // Add action to modify appointment button
  $('#modifyRedirectButton').on('click', function() {
    window.location = "modifySessions.html";
  });
  
  // Add action to manage account button
  $('#accountRedirectButton').on('click', function() {
    window.location = "accountManagement.html";
  });
  
  printNextAppointment();
});

function printNextAppointment() {
  let nextAppointment = getNextAppointment();
  let message = "";
  // no appointments scheduled
  if (!nextAppointment) {
    message = "You have no upcoming appointments."
  }
  else {
    message = "Your next appointment is on <b>" + nextAppointment.date + "</b> at <b>" +
        nextAppointment.time + "</b> with <b>" + nextAppointment.trainer + "</b>";
  }
  
  $('#nextAppointment').html("<h4>" + message + "</h4>");
}

function getNextAppointment() {
  let appointments = getUserAppointments();
  let nextAppointment = [];
  
  // one appointment
  if (appointments.length === 1) {
    nextAppointment = appointments[0];
  }
  
  // multiple appointment
  else if (appointments.length > 1) {
    // Set appointment to super far date for comparison
    let nextTime = "2044-01-01 11:59 pm";
    for (let i in appointments) {
      let appointment = appointments[i].date + " " + appointments[i].time;
      if (Date.parse(appointment) < Date.parse(nextTime)) {
        nextTime = appointment;
        nextAppointment = appointments[i];
      }
    }
  }
  return nextAppointment;
}

function getUserAppointments() {
  let currentUser = sessionStorage.getItem('user');
  let allAppointments = JSON.parse(localStorage.getItem('appointmentData'));
  let userAppointments = [];
  for (let i in allAppointments) {
    if (allAppointments[i].user === currentUser) {
      userAppointments.push(allAppointments[i]);
    }
  }
  return userAppointments;
}