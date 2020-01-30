$(document).ready(function () {
    //Load all Hiking Trips into the page
    
    //Add functionality for each menu button - be able to get to each page
    $("#tripsButton").on("click", function() {
        window.open("about.html");
    });
    
    $("#trailsButton").on("click", function() {
        window.open("about.html");
    });
    
    $("#travelersButton").on("click", function() {
        window.open("about.html");
    });
    
    $("#equipmentButton").on("click", function() {
        window.open("about.html");
    });
    
    $("#locationsButton").on("click", function() {
        window.open("about.html");
    });
    
    $("#aboutButton").on("click", function() {
        window.open("about.html");
    });
    
    //Hovering over each menu button
    $(".navigation").hover(function() {
        if($(this).attr("id") != "tripsButton") {
            $(this).css("border", "thin solid white");
        } else {
            $(this).css("border", "medium solid white");
        }
        
    }, function() {
        if($(this).attr("id") != "tripsButton") {
            $(this).css("border", "none");
        } else {
            $(this).css("border", "medium solid white");
        }
    });
});