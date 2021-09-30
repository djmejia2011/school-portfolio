
/**
This code will populate the inventory page from a JSON file. this way it will be easier
to update any of the fields of the objects.
**/
$(function(){
  var displayProducts = $("#current_products");
  $.ajax({
    url: 'json/products.json',
    dataType: 'json',
    type: 'GET',
    success: function(data){
      var output = '<div class="row">'
        for (var i in data){
      output += '<div class="col-lg-6">'
      output += '<div class="card"><img class="card-img-top img-thumbnail" style="width:100px;height:100px;" src=images/'+ data[i].image +' alt='+data[i].image +'>'
      output += '<div class="card-body">'
      output += '<h4 class="card-title">'+data[i].name+'</h4>'
      output += '<p class="card-text">'
      output += data[i].description
      output += '</p><h5>$'
      output += data[i].price
      output += ' Rent Per Item</h5><p class="text-black-50">'
      output += data[i].qty_in_stock
      output += ' Currently in stock</p></p></div></div></div>'
    }
      output += "</div>"
      displayProducts.html(output);
    }
  });

});
