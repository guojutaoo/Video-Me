function singleMovieData(data){
            let elements = jQuery("#singlemovie");
            let cart_button = jQuery("#cart_button");
            let index = 0;

			let rowhtml = "<li class='list-group-item list-group-item-primary'> " + "Title" + "</li>"
			rowhtml += "<li class='list-group-item info'> " +
		            "<a href='SingleMovieSession?wd=&search=1&id=" + data["id"][index] +
		            "&artist=&genre='" + ">" +
			    	data["title"][index] +
                    "</a></li>";  
            rowhtml += "<li class='list-group-item list-group-item-primary'> " + "Actor" + "</li>"
			rowhtml += "<li class='list-group-item info'> " +
                    "<a href='SingleMovieSession?wd=&search=0&id=" + data["id"][index] +
		            "&artist=" + data["director"][index] + "&genre='" + ">" +
	    		    data["director"][index] +
                    "</a></li>";
            rowhtml += "<li class='list-group-item list-group-item-primary'> " + "Year" + "</li>"
			rowhtml += "<li class='list-group-item info'> " +
                    "<a href='#'>" +
	    		    data["year"][index]+
                    "</a></li>";
            rowhtml += "<li class='list-group-item list-group-item-primary'> " + "genre" + "</li>"
			rowhtml += "<li class='list-group-item info'> " +
                    "<a href='SingleMovieSession?wd=&search=-1&id=" +
		            "&artist=&genre='" + data["genre"][index] + ">" +
		            data["genre"][index]+
    	            "</a></li>";
            rowhtml += "";

            let elements_button = '<button class="btn btn-success" onclick="location.href='+
            "'Cart?method=1&id="+ data["id"][index] + "&title=" + data["title"][index]  + "&director=" + 
            data["director"][index] + "'" + 
            '"type="button">Add to Cart</button>';
            elements_button += '&nbsp;';
            elements_button += '<button class="btn btn-danger" onclick="location.href='+
            "'Cart?method=-1&id="+ data["id"][index] + "&title=" + data["title"][index] + "&director=" + 
            data["director"][index] + "'" + 
            '"type="button">Delete from Cart</button>';

            elements.append(rowhtml);
            cart_button.append(elements_button);

}

jQuery.ajax({                       //rendering single movie info
    dataType: "json",
    method: "GET",
    url: "SingleMovie",
    async: true,
    error: function(){
        location.href = "movielist.html";
    },
    success: data => singleMovieData(data) 
});

