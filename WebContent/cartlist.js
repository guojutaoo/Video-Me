function handleCartData(data){
    let elements = jQuery("#singlemovie");         //rendering cart list.
    
    let index = 0;
    let rowhtml = "<li class='list-group-item list-group-item-success'>Movie Title</li>";
    if(!jQuery.isEmptyObject(data)){
        while(data["id"][index]){
            rowhtml += "<li class='list-group-item info'>" + data["title"][index] + "</li>";
            index++;
        }
    }
    rowhtml += "<button class='btn btn-primary'" + 
    " onclick='" + "checkOut()'" + 
    " type='button'>Check Out</button>"; 
    rowhtml += '&nbsp;&nbsp;';
    rowhtml += "<button class='btn btn-primary'" + 
    " onclick='location.href=" +'"movielist.html"' + "'" +
    "type='button'>Return to Movie List</button>";
    elements.append(rowhtml);
    
}


jQuery.ajax({
    dataType: "json",               //jQuery starts running while entering cart.html
    method: "POST",
    url: "ShoppingList",
    async: true,
    error: function(){alert("ajax 1 error occurs!");},
    success: data => handleCartData(data)
});
