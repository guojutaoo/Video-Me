function changePageNumber(data){
    if(!data){
        data = "1";
    }
    var currentpage = parseInt(data, 10)
    let list = jQuery('#pagebar');
    list.html("");
    let listhtml = "<li><a onclick='prev();'>" + "&laquo;" + "</a></li>";
    listhtml += "<li><a onclick='nav_change(" + currentpage + ");'>" + currentpage + "</a></li>";
    listhtml += "<li><a onclick='nav_change(" + (currentpage+1) + ");'>" + (currentpage+1) + "</a></li>";
    listhtml += "<li><a onclick='nav_change(" + (currentpage+2) + ");'>" + (currentpage+2) + "</a></li>";
    listhtml += "<li><a onclick='nav_change(" + (currentpage+3) + ");'>" + (currentpage+3) + "</a></li>";
    listhtml += "<li><a onclick='nav_change(" + (currentpage+4) + ");'>" + (currentpage+4) + "</a></li>";
    listhtml += "<li><a onclick='next();'>" + "&raquo;" + "</a></li>";

    list.append(listhtml);

}


jQuery.ajax({
        method: 'GET',
        dataType: 'text',
        async: false,
        url: "Pagination?",
        success: data =>  changePageNumber(data)    
});