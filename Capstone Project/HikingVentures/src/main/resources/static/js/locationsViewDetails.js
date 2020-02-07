$(document).ready(function () {    
    getLocationDetails();
    
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
        window.open("about.html");
    });
    
    //Add functionality for page header buttons
    $("#backButton").on("click", function() {
        window.open("/locationsHome");
    });
        
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

function getLocationDetails() {
    //Get location details
    $.ajax ({
        type: 'GET',
        url: '/getAllLocations',
        success: function (data, status) {
            $.each(data, function (index, Location) {
                var id = Location.locationID;
                var name = Location.parkName;
                var city = Location.nearbyCity;
                var state = Location.state;
                var pic = Location.photoLink;
                
                var toAdd = '<div class="row">';
                    toAdd += '<div class="locationCard" style="background-image: url(img/' + pic + ');">';
                    toAdd += '<div class="row" id="locationCardInfo">';
                    toAdd += '<div class="col-md-8">';
                    toAdd += '<div class="locationCardText">' + name + '</div>';
                    toAdd += '</div>';
                    toAdd += '<div class="col-md-2">';
                    toAdd += '<div class="dropDownMenu" id="dropDownMenu' + id + '">';
                    toAdd += '<a onclick=window.open("/locationsViewDetails/' + id + ')">View More Details</a>';
                    toAdd += '<a href="#">Edit</a>';
                    toAdd += '<a href="#">Delete</a>';
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