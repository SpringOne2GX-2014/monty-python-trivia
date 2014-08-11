;
$(document).foundation();

function errorReporter ( jqXHR, textStatus, errorThrown ){
	console.log("Error occurred on AJAX call: " + textStatus + " errorThrown: " + errorThrown );
}

function complete ( jqXHR, textStatus ) {
	console.log("AJAX Complete, status: " + textStatus + " jqXHR: " + jqXHR );
}


function populateCategories() {
	//	Make an AJAX call to get the list of categories:
	console.log("AJAX Call: Obtaining Categories");
	
	//	TODO: Will not work in HtmlUnit unless async is turned off!
    $.ajax({
        url: "categories",
        async: false,
        error:  errorReporter,
        complete: complete
    }).then(function(jsonObj) {
    	//	Place each returned category as an option in the list:
        var listItems;
        for (var i = 0; i < jsonObj.length; i++){
            listItems += "<option value='" + jsonObj[i].id + "'>" + jsonObj[i].name + "</option>";
        }	
        
        console.log("The following items are about to be added to the movie list: " + listItems);
        $("select[name='movie']").html(listItems);

        //  Select first item in the list:
        $("select[name='movie']").prop("selectedIndex",0);	
      	
        // Cause initial movie / skit selection.
      	$movie.change();           
    });
    return false;
}

function populateQuestions(category) {
	//	Make an AJAX call to get the list of questions for a category:
	console.log("AJAX Call: Obtaining questions for category: " + category);
	
	//	TODO: Will not work in HtmlUnit unless async is turned off!
    $.ajax({
        url: "categories/" + category + "/questions",
        async: false,
        error:  errorReporter,
        complete: complete
    }).then(function(jsonObj) {
        console.log("returned JSON for questions: " + jsonObj);
    	//	Place each returned question into the list:
        var listItems;
        for (var i = 0; i < jsonObj.length; i++){
            listItems+= "<option value='" + jsonObj[i].id + "'>" + jsonObj[i].question + "</option>";
        }
        console.log("The following items are about to be added to the question list: " + listItems);
        $("select[name='question']").html(listItems);
    });
    return false;
}



$( document ).ready(function() {
	
	//	Identify the category and question selection lists:
	$movie = $("select[name='movie']");
	$question = $("select[name='question']");
	
  	//	Set the behavior of the category selection list so
  	//	any change results in a revised list of questions:
  	$movie.change(function() {
  		populateQuestions($(this).val());
  	});
	
	//	Initially populate the categories:
	populateCategories();
	
});   