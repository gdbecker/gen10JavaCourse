$(document).ready(function () {    
    //Add functionality for each menu button - be able to access each page
    $("#tripsButton").on("click", function() {
        window.open("/tripsHome", "_self");
    });
    
    $("#trailsButton").on("click", function() {
        window.open("/trailsHome", "_self");
    });
    
    $("#travelersButton").on("click", function() {
        window.open("/travelersHome", "_self");
    });
    
    $("#equipmentButton").on("click", function() {
        window.open("/equipmentHome", "_self");
    });
    
    $("#locationsButton").on("click", function() {
        window.open("/locationsHome", "_self");
    });
    
    $("#aboutButton").on("click", function() {
        window.open("/about", "_self");
    });
    
    $("#logoutButton").on("click", function() {
        //Confirm that user wants to logout
        var r = confirm("Are you sure you want to log out?");
        
        if (r == true) {
            window.open("/", "_self");
        }
    });
    
    //Add functionality for page header buttons
    $("#backButton").on("click", function() {
        window.open("/locationsHome", "_self");
    });
        
    //Hovering over each menu button
    $(".navigationButtons").hover(function() {
        if($(this).attr("id") == "tripsButton") {
            $(this).css("border", "thin solid #6BBF59");
            $("#tripsTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "trailsButton") {
            $(this).css("border", "thin solid #6BBF59");
            $("#trailsTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "travelersButton") {
            $(this).css("border", "thin solid #6BBF59");
            $("#travelersTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "equipmentButton")  {
            $(this).css("border", "thin solid #6BBF59");
            $("#equipmentTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "locationsButton") {
            $(this).css("border", "medium solid #6BBF59");
            $("#locationsTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "aboutButton") {
            $(this).css("border", "thin solid #6BBF59");
            $("#aboutTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "logoutButton") {
            $(this).css("border", "thin solid #6BBF59");
            $("#logoutTitle").css("opacity", "1");
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
            $(this).css("border", "medium solid #6BBF59");
            $("#locationsTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "aboutButton") {
            $(this).css("border", "none");
            $("#aboutTitle").css("opacity", "0");
        } else if ($(this).attr("id") == "logoutButton") {
            $(this).css("border", "none");
            $("#logoutTitle").css("opacity", "0");
        }
    });
    
    //Hovering over buttons at top of page
    $(".pageHeaderButtons").hover(function() {
        if($(this).attr("id") == "backButton") {
            $(this).css("border", "medium solid #6BBF59");
            $("#backTitle").css("opacity", "1");
        }
        
    }, function() {
        if($(this).attr("id") == "backButton") {
            $(this).css("border", "none");
            $("#backTitle").css("opacity", "0"); 
        }
    });
});