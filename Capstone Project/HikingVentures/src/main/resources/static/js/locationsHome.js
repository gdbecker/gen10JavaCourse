$(document).ready(function () {
    //Load all Locations into the page
    loadLocations();
    
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
        window.open("/equipmentHome", "_blank");
    });
    
    $("#locationsButton").on("click", function() {
        window.open("/locationsHome", "_self");
    });
    
    $("#aboutButton").on("click", function() {
        window.open("/about", "_blank");
    });
    
    //Add functionality for page header buttons
    $("#createNewLocationButton").on("click", function() {
        window.open("/locationsAdd", "_self");
    });
    
    $(".dropDownMenu").hide();
    
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
            $(this).css("border", "thin solid #87A330");
            $("#equipmentTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "locationsButton") {
            $(this).css("border", "medium solid #87A330");
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
            $(this).css("border", "none");
            $("#equipmentTitle").css("opacity", "0");
        } else if ($(this).attr("id") == "locationsButton") {
            $(this).css("border", "medium solid #87A330");
            $("#locationsTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "aboutButton") {
            $(this).css("border", "none");
            $("#aboutTitle").css("opacity", "0");
        }
    });
    
    //Hovering over buttons at top of page
    $(".pageHeaderButtons").hover(function() {
        if($(this).attr("id") == "createNewLocationButton") {
            $(this).css("border", "medium solid #87A330");
            $("#createNewLocationTitle").css("opacity", "1");
        }
        
    }, function() {
        if($(this).attr("id") == "createNewLocationButton") {
            $(this).css("border", "none");
            $("#createNewLocationTitle").css("opacity", "0"); 
        }
    });
});

function loadLocations() {
    //Clear out page of locations
    clearLocations();

    //Load all locations from db into the page 
    $.ajax ({
        type: 'GET',
        url: '/getAllLocations',
        success: function (data, status) {
            $.each(data, function (index, Location) {
                var id = Location.locationId;
                var name = Location.parkName;
                var city = Location.nearbyCity;
                var state = Location.state;
                var pic = Location.photoLink;
                
                var toAdd = '<div class="row">';
                    toAdd += '<div class="locationCard" style="background-image: url(img/' + pic + ');">';
                    toAdd += '<div class="layer"><div>';
                    toAdd += '<div class="row" id="locationCardInfo">';
                    toAdd += '<div class="col-md-8">';
                    toAdd += '<div class="locationCardText">' + name + '</div>';
                    toAdd += '</div>';
                    toAdd += '<div class="col-md-2">';
                    toAdd += '<div class="dropDownMenu" id="dropDownMenu' + id + '">';
                    toAdd += '<a href="locationsViewDetails?id=' + id + '">View More Details</a>';
                    toAdd += '<a href="locationsEdit?id=' + id + '">Edit</a>';
                    toAdd += '<a href="" onclick="deleteLocation(' + id + ')" >Delete</a>';
                    toAdd += '</div>';
                    toAdd += '</div>';
                    toAdd += '<div class="col-md-2">';
                    toAdd += '<input type="image" src="img/MenuBar.png" alt="View More Options"';
                    toAdd += 'title="View More Options" class="locationCardButtons"';
                    toAdd += 'id="' + id + '" onclick="showMoreOptionsMenu(id)">';
                    toAdd += '</div>';
                    toAdd += '</div>';
                    toAdd += '</div>';
                    toAdd += '</div>';
                $("#locationsDiv").append(toAdd);
            });
        },
        error: function() {
            $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service.  Please try again later.'));
        }
    });
}

function clearLocations() {
    $('#locationsDiv').empty();
}

function openViewDetails(id) {
    $("#viewDetailsButton" + id).on("click", function() {
        window.open("/locationsViewDetails/" + id);
    });
}

function deleteLocation(id) {
    //Confirm that user wants to delete
    var r = confirm("Are you sure you want to delete this location and all trips and trails associated with it?");
    
    //Delete if true
    if (r == true) {
        var url = "locationsDelete?id=" + id;
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

function showMoreOptionsMenu(id) {
    $("#dropDownMenu" + id).toggle();
}