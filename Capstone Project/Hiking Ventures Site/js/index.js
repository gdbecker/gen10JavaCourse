$(document).ready(function () {
    //Load all Hiking Trips into the page
    
    //Add functionality for each menu button - be able to access each page
    $("#tripsButton").on("click", function() {
        window.open("tripsHome.html");
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
    $(".mainMenuButtons").hover(function() {
        if($(this).attr("id") == "tripsButton") {
            $(this).css("border", "thick solid #87A330");
            $("#tripsTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "trailsButton") {
            $(this).css("border", "thick solid #87A330");
            $("#trailsTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "travelersButton") {
            $(this).css("border", "thick solid #87A330");
            $("#travelersTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "equipmentButton")  {
            $(this).css("border", "thick solid #87A330");
            $("#equipmentTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "locationsButton") {
            $(this).css("border", "thick solid #87A330");
            $("#locationsTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "aboutButton") {
            $(this).css("border", "thick solid #87A330");
            $("#aboutTitle").css("opacity", "1");
        }
        
    }, function() {
        if($(this).attr("id") == "tripsButton") {
            $(this).css("border", "none");
            $("#tripsTitle").css("opacity", "0");
        } else if ($(this).attr("id") == "trailsButton") {
            $(this).css("border", "none");
            $("#trailsTitle").css("opacity", "0");
        } else if ($(this).attr("id") == "travelersButton") {
            $(this).css("border", "none");
            $("#travelersTitle").css("opacity", "0");
        } else if ($(this).attr("id") == "equipmentButton")  {
            $(this).css("border", "none");
            $("#equipmentTitle").css("opacity", "0");
        } else if ($(this).attr("id") == "locationsButton") {
            $(this).css("border", "none");
            $("#locationsTitle").css("opacity", "0");
        } else if ($(this).attr("id") == "aboutButton") {
            $(this).css("border", "none");
            $("#aboutTitle").css("opacity", "0");
        }
    });
});