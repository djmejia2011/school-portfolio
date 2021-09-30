$(document).ready(function() {
  populateAppointmentSelection();
  $('#modifyAppointmentButton').on('click', function() {
    if (sessionSelected()) {
      $('#modifyForm').show();
      prefillForm();
    };
  });
  $('#cancelAppointmentButton').on('click', function() {
    if (sessionSelected()) {
      cancelAppointment();
      alert("Your appointment has been cancelled.");
      window.location = "home.html";
    }
  });
  $('#saveModButton').on('click', function() {
    cancelAppointment();
    createAppointment();
  });
});

function sessionSelected() {
  if (document.getElementById('scheduledAppointments').value === "Choose session") {
    $('#no-session').show();
    return false;
  }
  $('#no-session').hide();
  return true;
}


function populateAppointmentSelection() {
  let dropdown = document.getElementById('scheduledAppointments');
  let appointments = getUserAppointments();
  
  for (let i in appointments) {
    let appointment = appointments[i];
    let opt = document.createElement('option');
    opt.textContent = appointment.date + " at " + appointment.time + " with " + appointment.trainer;
    opt.value = i;
    dropdown.appendChild(opt);
  }
}

function prefillForm() {
  let appointments = getUserAppointments();
  let selectedAppointment = appointments[document.getElementById('scheduledAppointments').value];
  
  $('#appointmentTrainer').val(selectedAppointment.trainer);
  $('#appointmentDate').attr('value', selectedAppointment.date);
  $('#appointmentTime').val(selectedAppointment.time);
}

function cancelAppointment() {
  let appointments = getUserAppointments();
  let selectedAppointment = appointments[document.getElementById('scheduledAppointments').value];
  let allAppointments = JSON.parse(localStorage.getItem('appointmentData'));
  for (let i in allAppointments) {
    if (JSON.stringify(allAppointments[i]) === JSON.stringify(selectedAppointment)) {
      allAppointments.splice(i, 1);
    }
  }
  localStorage.setItem('appointmentData', JSON.stringify(allAppointments));
}