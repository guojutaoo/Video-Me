function actorInfo(data){
            let elements = jQuery("#actor");
            index = 0;
            let rowhtml = "<ul class='list-group info'>"
            rowhtml += "<li class='list-group-item list-group-item-primary'> " + "Actor" + "</li>"
			rowhtml += "<li class='list-group-item'> " +
		            "<a href='SingleMovieSession?search=1&id="+data["id"][index]+
		            "&artist=&genre='" + ">" +
			    	data["artist"][index] +
                    "</a></li>";
            rowhtml += "<li class='list-group-item list-group-item-primary'> " + "BirthYear" + "</li>"
			rowhtml += "<li class='list-group-item info'> " +
                    "<a href='#'>" +
		            data["birthyear"][index]+
	        	    "</a></li>";
            rowhtml += "</ul>";
            rowhtml += "<button class='btn btn-primary'" + 
            " onclick='location.href=" +
            '"SingleMovieSession?search=1&id='+data["id"][index]+
		            '&artist=&genre="' + "'" + 
            "type='button'>Return to Movie Info</button>";
            rowhtml += '&nbsp;&nbsp;';
            rowhtml += "<button class='btn btn-primary'" + 
            " onclick='location.href=" +'"movielist.html"' + "'" +
            "type='button'>Return to Movie List</button>";
            elements.append(rowhtml);
}

jQuery.ajax({                           //rendering single actor's info 
    dataType: "json",
    method: "GET",                
    url: "SingleMovie",
    async: true,
    error: function(){
        alert("Single actor failed");
    },
    success: data => actorInfo(data) 
});