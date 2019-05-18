function checkStatus(data){
    if(data["status"] == "success"){
        cleancart();                      //username and password authentication
        window.location.reload();
    }
}

function cleanCart(){
    console.log("I will empty my cart!");
    $.ajax({
            dataType: "json",
            method: "POST",
            url: "Cart?method=0&id=&title=&director=",
            async: true,
            error: function(){alert("cleancart() returned error");},
            });
}


function checkOut(){                //if checkout successfully, then jQuery calls cleancart function which
    var retVal = confirm("Continue to checkout?");              //will empty the cart.
    if(retVal==true){
        var email = prompt("Enter your email : ", "your email here");
        var psw = prompt("Enter your password : ", "your password here");
        $.ajax({
                dataType: "json",
                method: "POST",
                url: "CheckOut?email="+ email + "&psw="+ psw,
                async: true,
                error: function(){alert("checkout() returned error");},
                success: data => checkstatus(data)
            });
        }
}