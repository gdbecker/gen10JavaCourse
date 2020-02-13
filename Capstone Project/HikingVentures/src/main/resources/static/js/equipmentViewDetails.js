$(document).ready(function () {
    //Add functionality for each menu button - be able to access each page
    $("#tripsButton").on("click", function() {
        window.open("/tripsHome", "_blank");
    });
    
    $("#trailsButton").on("click", function() {
        window.open("/trailsHome", "_blank");
    });
    
    $("#travelersButton").on("click", function() {
        window.open("/travelersHome", "_blank");
    });
    
    $("#equipmentButton").on("click", function() {
        window.open("/equipmentHome", "_self");
    });
    
    $("#locationsButton").on("click", function() {
        window.open("/locationsHome", "_blank");
    });
    
    $("#aboutButton").on("click", function() {
        window.open("/about", "_blank");
    });
    
    //Add functionality for page header buttons
    $("#backButton").on("click", function() {
        window.open("/equipmentHome", "_self");
    });
    
    $(".dropDownMenu").toggle();
    
    //Hovering over each menu button
    $(".navigationButtons").hover(function() {
        if($(this).attr("id") == "tripsButton") {
            $(this).css("border", "thin solid #87A330");
            $("#tripsTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "trailsButton") {
            $(this).css("border", "thin solid #87A330");
            $("#trailsTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "travelersButton") {
            $(this).css("border", "thin solid #87A330");
            $("#travelersTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "equipmentButton")  {
            $(this).css("border", "medium solid #87A330");
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
            $(this).css("border", "none");
            $("#tripsTitle").css("opacity", "0");
        } else if ($(this).attr("id") == "trailsButton") {
            $(this).css("border", "none");
            $("#trailsTitle").css("opacity", "0");
        } else if ($(this).attr("id") == "travelersButton") {
            $(this).css("border", "none");
            $("#travelersTitle").css("opacity", "0");
        } else if ($(this).attr("id") == "equipmentButton")  {
            $(this).css("border", "medium solid #87A330");
            $("#equipmentTitle").css("opacity", "1");
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

function deleteEquipment(id) {
    //Confirm that user wants to delete
    var r = confirm("Are you sure you want to delete this equipment item?");
    
    //Delete if true
    if (r == true) {
        var url = "equipmentDelete?id=" + id;
        $.ajax ({
        type: 'GET',
        url: url,
        success: function (data, status) {
            //Do nothing
        }, 
        error: function() {
            $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service.  Please try again later.'));
        }
    });
    } else {
        //Do nothing
    }
}