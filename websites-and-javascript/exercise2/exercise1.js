$(function () {
    /* Selectors */
    $("#add-classes").click(function () {
        $('.selector-examples li').first().addClass('first');
        $('.selector-examples li').last().addClass('last');
         //odd will have the even color to match required look from project requirement
        $('.selector-examples li:odd').addClass('even');
         //even will have even color to match required look from project requirement
        $('.selector-examples li:even').addClass('odd');
        $('.selector-examples li').addClass(function(index){
          if (index == 3 || index == 4){
            return "highlighter"
          }
        });

    });

    /* Change Text */
    $("#change-language").click(function () {
        // Step 1: Create a new variable called inputValue and set it to the value of the #newLanguage id
        var inputValue = $('#newLanguage:text').val();
        // Step 2: Create a new variable called element and set it to the .currentLanguage class
        var element = $('.currentLanguage').val('.currentLanguage');
        // Step 4: Using .html (http://api.jquery.com/html/), update element with inputValue
        $(element).html(inputValue);
    });

    /* Toggles Part 1: Modifying CSS attributes with .css() */
    var boxColor = "rgb(153, 51, 51)"

    $("#button_toggle_colors").click(function() {
        /* Your code goes here */
        $(".box").each(function () {

            if ($(this).css('background-color') == boxColor) {
                // element currently has a color
                $(this).css("background-color","white");
                // remove it
            }
            else {
                // element currently has no background color
                $(this).css("background-color", boxColor);
                // add it
            }
        });
    });

    /* Toggles Part 2: Adding/Removing classes to manipulate shapes */
    $("#button_toggle_roundedges").click(function() {
        $(".box").each(function () {
            // Add a toggleClass using $(this) to add the class 'round-edge'
            $(this).toggleClass("round-edge");
        });
    });
    var counter = 4;
    /* Toggles Part 3: Adding new elements */
    $("#button_add_box").click(function() {
        /* Your code goes here  */
        // var element = $("<div id=box"+ counter +" class='box outlined' > </div>");
        var element = $("<div id=box"+ counter +" > </div>");
        element.addClass('box outlined');
        $("#boxes").append(element);
        counter++;


    });
});
