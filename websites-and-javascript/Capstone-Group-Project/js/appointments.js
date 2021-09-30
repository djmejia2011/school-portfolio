$(document).ready(function() {
  $('#appointmentTrainer').change(function() {
    showSelectedTrainer();
  });
  $('#scheduleAppointmentButton').on('click', function() {
    if (checkChoices()) {
      if (checkOpening()) {
        createAppointment();
      }
      else {
        alert("Your chosen trainer is busy during your preferred time slot. Please try a different time or trainer.")
      }
    }
  })
});

function showSelectedTrainer() {
  // Get values from fields
  let selectedTrainer = document.getElementById('appointmentTrainer').value;
  let bob = $('#trainer_bio_bob');
  let jane = $('#trainer_bio_jane');
  let arnold = $('#trainer_bio_arnold');
  
  if (selectedTrainer === "Bob Ross") {
    bob.show();
    jane.hide();
    arnold.hide();
  }
  else if (selectedTrainer === "Jane Fonda") {
    bob.hide();
    jane.show();
    arnold.hide();
  }
  else {
    bob.hide();
    jane.hide();
    arnold.show();
  }
}

function checkChoices() {
  // Get values from fields
  let selectedTrainer = document.getElementById('appointmentTrainer').value;
  let selectedDate = document.getElementById('appointmentDate').value;
  let selectedTime = document.getElementById('appointmentTime').value;
  if (selectedTrainer === "Trainers") {
    alert("Please choose a trainer!");
    return false;
  }
  else if (!selectedDate) {
    alert("Please choose a date!");
    return false;
  }
  else if (selectedTime === "Choose time") {
    alert("Please choose a time!");
    return false;
  }
  return true;
}

function checkOpening() {
  // Get values from fields
  let selectedTrainer = document.getElementById('appointmentTrainer').value;
  let selectedDate = document.getElementById('appointmentDate').value;
  let selectedTime = document.getElementById('appointmentTime').value;
  // Parse selected appointment time slot
  let convertedSelection = Date.parse(selectedDate + " " + selectedTime);
  
  // Get all appointments scheduled for trainer
  let appointments = JSON.parse(localStorage.getItem('appointmentData'));
  let trainerAppointments = [];
  for (let i in appointments) {
    if (appointments[i].trainer === selectedTrainer) {
      trainerAppointments.push(appointments[i]);
    }
  }
  
  // Convert existing appointments to parsed dates
  let date = "";
  let time = "";
  let busyTimes = [];
  for (let j in trainerAppointments) {
    date = trainerAppointments[j].date;
    time = trainerAppointments[j].time;
    busyTimes.push(Date.parse(date + " " + time));
  }
  
  // Compare chosen appointment slot to existing ones
  return !busyTimes.includes(convertedSelection);
}

function createAppointment() {
  // Get values from fields
  let selectedTrainer = document.getElementById('appointmentTrainer').value;
  let selectedDate = document.getElementById('appointmentDate').value;
  let selectedTime = document.getElementById('appointmentTime').value;
  let appointments = JSON.parse(localStorage.getItem('appointmentData'));
  let newAppointment = {
    user: sessionStorage.getItem('user'),
    trainer: selectedTrainer,
    date: selectedDate,
    time: selectedTime
  };
  appointments.push(newAppointment);
  localStorage.setItem('appointmentData', JSON.stringify(appointments));
  
  alert("You are scheduled for a training session with " + selectedTrainer + " on " + selectedDate + " at " +
      selectedTime + "! See you soon.");
  window.location = "home.html";
}