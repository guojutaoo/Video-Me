function movieData(data){
	
			let movieTableElements = jQuery("#table_body");
			for(let index = 0; index < 20 ; index++){
				if (!data["movie_id"][index]){
					break;
				}
				let rowhtml = "<tr>"
			    rowhtml += "<th class='col'> " +
		                "<a href='SingleMovieSession?search=1&genre=&id="+data["movie_id"][index]+
		                "&artist=&year='"+data["year"][index]+">"+
			    		data["name"][index]+
			    		"</a></th>";
			    rowhtml += "<th class='col'> " +
                        "<a href='#'"+">"+
	    		        data["director"][index]+
	    		        "</a></th>";
			    rowhtml += "<th class='col'> " +
                        "<a href='SingleMovieSession?search=-1&genre=&id=&artist=&year="+
                        data["year"][index]+"'>"+
	    		        data["year"][index]+
	    	        	"</a></th>";
			    rowhtml += "<th class='col'> " +
                        "<a href='#'"+">"+
		                data["rating"][index]+
	        	        "</a></th>";
    	                "</a></th>";
				rowhtml += "/tr"
				movieTableElements.append(rowhtml);
			}
}
			
function refresh(){
	window.locaton.href="movielist.html";
}

jQuery.ajax({
	dataType: "json",               //rendering movie data in movielist.html
	method: "GET",
	url: "Movie",
	async: true,
	error: function(){console.log("fail");},
	complete: function (data) {
	    console.log("Reload page ajax star running!");
	  },
	success: data => movieData(data)
});

