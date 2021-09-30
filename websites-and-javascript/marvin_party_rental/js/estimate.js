
/**
This code will populate the free estimate from a JSON file. this way it will be easier
to update any of the fields of the objects. as the user makes changes to the form the right
column will update with the new estimate of the user.
**/
$(function(){
  var productdescriptions = $("#productdescriptions");
  $.ajax({
    url: 'json/products.json',
    dataType: 'json',
    type: 'GET',
    success: function(data){
      var output = '<div class="row m-3">'
        for (var i in data){
      output += '<div class="card p-2 col-md-4">'
      output += '<img class="card-img-left img-thumbnail d-inline" style="width:50px;height:50px;" src=images/'+ data[i].image +' alt='+data[i].image +'>'
      output += '<p class="d-inline"> '+data[i].name+' $'+data[i].price+' - Each</p>'
      output += '<p class="text-black-50 mt-0">'
      output += data[i].qty_in_stock
      output += ' Currently in stock</p>'
      output += ' Enter Qty<input type="number" class="form-control" id="'+data[i].item_id+'" value="0" min="0" max="'+data[i].qty_in_stock+'">'
      output += '</div>'
    }
      output += "</div>"
      productdescriptions.html(output);

      /**
      this code will calculate the new estimate after each change of the form
      **/
      $('#estimate-id').on('change', function() {
        var displayestimate = $("#displayestimate")
        var totalEstimate = 0
        for (var l in data){
          var itemID = data[l].item_id
          var itemQty = document.getElementById(itemID).value;
          totalEstimate += itemQty * data[l].price
          var totalEstimateString = "<h3>$"+totalEstimate.toFixed(2)+"</h3>"
          displayestimate.html(totalEstimateString)

        }
      });

    }
  });

});
