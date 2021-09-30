$(document).ready(function () {

  $('#retrieve-resources').click(function () {
    http://api.wunderground.com/api/ // set this to the display-resources ID in index.html
    var iso2code = $("#country:text").val(); // this variable should capture text input from the user

    displayResources.text('Loading data from JSON source...');
    /* AJAX Call to RESTful Service */
    $.ajax({
      type: "GET",// Specify "GET" or "POST"
      url: "https://restcountries.eu/rest/v2/alpha",// URL of the service
      data: {codes:iso2code},// Use the format { parameterName: variable },
      success: function(result)
      {
        // Build output render
        var output="<table><thead><tr><th>Name</th><th>Alpha 2 Code</th><th>Alpha 3 Code</th></thead><tbody>";
        for (var i in result){
          output+= "<tr><td>" +
            result[i].name +
            "</td><td>" +
            result[i].alpha2Code +
            "</td><td>" +
            result[i].alpha3Code +
            "</td></tr>";
        }
        // Add data to the output render
        // Create a for loop that will iterate over the result.RestResponse.result object and add <tr><td> element
        // Hint: You will want to display result.RestResponse.result[i].name, result.RestResponse.result[i].alpha2_code, and result.RestResponse.result[i].alpha3_code in your table

        displayResources.html(output);

        // Add bootstrap class to the table for styling
        $("table").addClass("table");
      }
    });
  });
});
