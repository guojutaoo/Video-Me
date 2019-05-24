function sessionChange(data){
    if(data["status"]=="success"){
        window.location.reload();
    }
    else{
        console.log("Here occurs an error!");
    }
}

function findGenre(genre){
        $.ajax({
        dataType: "json",
        method: "GET",
        async: true,
        url: "RenewSession?page=&orderby=&genre="+ genre +"&asc=&rvs_page=1",
        success: data => sessionChange(data)
    });
}


function next(){
    // alert("you are in next()!");
    $.ajax({
        dataType: "json",
        method: "GET",
        async: true,
        url: "RenewSession?page=&orderby=&genre=&asc=&rvs_page=1",
        success: data => sessionChange(data)
    });
}


function prev(){
    // alert("you are in prev()!");
    $.ajax({
        dataType: "json",
        method: "GET",
        async: true,
        url: "RenewSession?page=&orderby=&genre=&asc=&rvs_page=-1",
        success: data => sessionChange(data)
    });
}


function nav_change(num){
    // alert("you are in nav_change()!");
    jQuery.ajax({
        dataType: "json",
        method: "GET",
        async: true,
        url: "RenewSession?page="+num+"&orderby=&genre=&asc=&rvs_page=", //change page number directly
        error: function(){alert("!");},
        success: data => sessionChange(data)
    });
}


function change_page(){
    var jump = document.getElementById("num_page").value;
    // alert(jump);
    jQuery.ajax({
        dataType: "json",
        method: "GET",
        async: true,
        url: "RenewSession?page="+jump+"&orderby=&genre=&asc=&rvs_page=", //change page number directly
        error: function(){alert("!");},
        success: data => sessionChange(data)
    });
}


function asc(){
	$.ajax({
		dataType: "json",
		method: "GET",
		url: "RenewSession?page=&orderby=&genre=&genre=&asc=1&rvs_page=", //render specific genre
        async: true,
        async: true,
        success: data => sessionChange(data)
	    });
    }
    

function desc(){
	$.ajax({
		dataType: "json",
		method: "GET",
		url: "RenewSession?page=&orderby=&genre=&genre=&asc=-1&rvs_page=", //render specific genre
		async: true,
        success: data => sessionChange(data)
	    });
	}


function orderby(){
    var orderby = document.getElementsByName("keyword")[0].value;
    // alert(orderby);
	$.ajax({
		dataType: "json",
		method: "GET",
		url: "RenewSession?page=&orderby="+orderby+"&genre=&asc=&rvs_page=", //alphabet or genre or rating or year
		async: true,
        success: data => sessionChange(data)
	    });
	}

    
function genre(){
	let genre = jQuery("#genre");
	$.ajax({
		dataType: "json",
		method: "GET",
		url: "RenewSession?page=&orderby=&genre="+genre+"&asc=&rvs_page=", //render specific genre
		async: true,
        success: data => sessionChange(data)
	    });
	}
