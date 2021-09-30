/*this code will be used to validate the contact form in the contact page*/

$('#contact-btn').click(function(event){
  event.preventDefault();
  const isValidName = $('#contact-name').valid();
  const isValidEmail = $('#contact-email').valid();
  const isValidPhone = $('#contact-phone').valid();
  if(isValidName && isValidEmail && isValidPhone){
    $('label').removeClass('text-danger');
    $('#contact-form').css('display', 'none');
    $('#success').css('display', 'block');

  }
  else if (!(isValidName || isValidEmail || isValidPhone)) {
    $('label').addClass('text-danger');
  }

});
