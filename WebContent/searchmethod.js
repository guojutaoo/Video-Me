function fuzzySearch(){
    let keyword = document.getElementById('movie_name').value;
    $.ajax({
        method: 'GET',
        dataType: 'json',
        async: false,
        url: "FuzzySearch?kwd=" + keyword,
        success: data => renderSearchResult(data)
    });
}


function renderSearchResult(data){
    jQuery('#searchresult').html("");  //clean cache
    let result = jQuery('#searchresult')
    let index = 0
    let rowhtml = '';
    if(!jQuery.isEmptyObject(data)){
        while(data["title"][index]){       
            rowhtml += "<option>" + data["title"][index];
            index++;
        }
    }
    result.append(rowhtml);
}