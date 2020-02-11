$(document).ready(function () {    
    //Add functionality for each menu button - be able to access each page
    $("#tripsButton").on("click", function() {
        window.open("tripsHome.html");
    });
    
    $("#trailsButton").on("click", function() {
        window.open("trailsHome.html");
    });
    
    $("#travelersButton").on("click", function() {
        window.open("travelersHome.html");
    });
    
    $("#equipmentButton").on("click", function() {
        window.open("equipmentHome.html");
    });
    
    $("#locationsButton").on("click", function() {
        window.open("locationsHome.html");
    });
    
    $("#aboutButton").on("click", function() {
        window.open("/about", "_blank");
    });
    
    //Add functionality for page header buttons
    $("#backButton").on("click", function() {
        window.open("/tripsHome", "_self");
    });
        
    //Hovering over each menu button
    $(".navigationButtons").hover(function() {
        if($(this).attr("id") == "tripsButton") {
            $(this).css("border", "medium solid #87A330");
            $("#tripsTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "trailsButton") {
            $(this).css("border", "thin solid #87A330");
            $("#trailsTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "travelersButton") {
            $(this).css("border", "thin solid #87A330");
            $("#travelersTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "equipmentButton")  {
            $(this).css("border", "thin solid #87A330");
            $("#equipmentTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "locationsButton") {
            $(this).css("border", "thin solid #87A330");
            $("#locationsTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "aboutButton") {
            $(this).css("border", "thin solid #87A330");
            $("#aboutTitle").css("opacity", "1");
        }
        
    }, function() {
        if($(this).attr("id") == "tripsButton") {
            $(this).css("border", "medium solid #87A330");
            $("#tripsTitle").css("opacity", "1");
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
    
    //Hovering over buttons at top of page
    $(".pageHeaderButtons").hover(function() {
        if($(this).attr("id") == "backButton") {
            $(this).css("border", "medium solid #87A330");
            $("#backTitle").css("opacity", "1");
        }
        
    }, function() {
        if($(this).attr("id") == "backButton") {
            $(this).css("border", "none");
            $("#backTitle").css("opacity", "0"); 
        }
    });
});