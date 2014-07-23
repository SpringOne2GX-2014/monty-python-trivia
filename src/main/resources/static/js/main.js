;
$(document).foundation();

function populateCategories() {
	//	Make an AJAX call to get the list of categories:
    $.ajax({
        type: "GET",
        url: "/categories",
        contentType: "application/json; charset=utf-8",
        async: false,
        dataType: "json",
        success: function(jsonObj) {
        	//	Place each returned category as an option in the list:
            var listItems;
            for (var i = 0; i < jsonObj.length; i++){
                listItems+= "<option value='" + jsonObj[i].id + "'>" + jsonObj[i].name + "</option>";
              }
              $("select[name='movie']").html(listItems);
        }
    });
    return false;
}

function populateQuestions(category) {
	//	Make an AJAX call to get the list of questions for a category:
    $.ajax({
        type: "GET",
        url: "/categories/" + category + "/questions",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(jsonObj) {
        	//	Place each returned question into the list:
            var listItems;
            for (var i = 0; i < jsonObj.length; i++){
                listItems+= "<option value='" + jsonObj[i].id + "'>" + jsonObj[i].question + "</option>";
              }
              $("select[name='question']").html(listItems);
        }
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
  
	// Cause initial movie / skit selection.
	$movie.change(); 
});   