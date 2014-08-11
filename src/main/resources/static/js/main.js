;
$(document).foundation();

function populateCategories() {
	//	Make an AJAX call to get the list of categories:
	
    $.ajax({
        url: "categories"
    }).then(function(jsonObj) {
    	//	Place each returned category as an option in the list:
        var listItems;
        for (var i = 0; i < jsonObj.length; i++){
            listItems+= "<option value='" + jsonObj[i].id + "'>" + jsonObj[i].name + "</option>";
        }
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
    $.ajax({
        url: "categories/" + category + "/questions"
    }).then(function(jsonObj) {
    	//	Place each returned question into the list:
        var listItems;
        for (var i = 0; i < jsonObj.length; i++){
            listItems+= "<option value='" + jsonObj[i].id + "'>" + jsonObj[i].question + "</option>";
          }
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