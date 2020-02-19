$(document).ready(function () {
    //Load all Hiking Trips into the page
    loadTrips();
    
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
    $("#createNewTripButton").on("click", function() {
        window.open("/tripsAdd", "_self");
    });
    
    $(document).on('.dropDownMenu', function(){
        $(this).hide();
    });
    
    //$(".dropDownMenu").hide();
        
    //Hovering over each menu button
    $(".navigationButtons").hover(function() {
        if($(this).attr("id") == "tripsButton") {
            $(this).css("border", "medium solid #6BBF59");
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
            $(this).css("border", "thin solid #6BBF59");
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
            $(this).css("border", "medium solid #6BBF59");
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
        } else if ($(this).attr("id") == "logoutButton") {
            $(this).css("border", "none");
            $("#logoutTitle").css("opacity", "0");
        }
    });
    
    //Hovering over buttons at top of page
    $(".pageHeaderButtons").hover(function() {
        if($(this).attr("id") == "createNewTripButton") {
            $(this).css("border", "medium solid #6BBF59");
            $("#createNewTripTitle").css("opacity", "1");
        }
        
    }, function() {
        if($(this).attr("id") == "createNewTripButton") {
            $(this).css("border", "none");
            $("#createNewTripTitle").css("opacity", "0"); 
        }
    });
});

function loadTrips() {
    // Clear out all trips previously loaded on the page
    clearTrips();
    
    var pic = '';
    var location = '';
    var locationName = '';
    
    $.ajax ({
        type: 'GET',
        url: '/getAllTrips',
        success: function (data, status) {
            var counter = 0;
            var rowNum = 1;
            $.each(data, function (index, Trip) {
                var id = Trip.tripId;
                var tripName = Trip.tripName;
                var startDate = Trip.startDate;
                var endDate = Trip.endDate;
                var cost = Trip.tripCostPerTraveler;
                
                //Get the pic of a trail and location name the trip is on
                var urlUse = '/getTrailsForTrip?id=' + id;
                $.ajax ({
                    type: 'GET',
                    url: urlUse,
                    async: false,
                    success: function (data, status) {
                        $.each(data, function (index, Trail) {
                            pic = Trail.photoFilePath;
                            location = Trail.location;
                            locationName = location.parkName;
                            
                            
                        });
                    },
                    error: function() {
                        $('#errorMessages')
                            .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text('Error calling web service.  Please try again later.'));
                    }  
                });
                
                if(counter == 0 || counter % 2 == 0) {
                    $('#tripsDiv').append('<div class="row" id="row'+ rowNum + '"></div>')
                    rowNum++;
                }
                
                var toAdd = '<div class="col-md-6">';
                    toAdd += '<div class="tripCard">';
                    toAdd += '<div class="row">';
                    toAdd += '<img src="img/' + pic + '" class="card-img-top" alt="trail">';
                    toAdd += '</div>';
                    toAdd += '<div class="row">';
                    toAdd += '<div class="col-md-7">';
                    toAdd += '<div class="tripCardHeader">' + tripName + '</div>';
                    toAdd += '</div>';
                    toAdd += '<div class="col-md-2">';
                    toAdd += '<input type="image" src="img/MenuBar.png" alt="View More Options" title="View More Options" class="tripCardButtons" id="' + id + '" onclick="showMoreOptionsMenu(id)">';
                    toAdd += '<div class="dropDownMenu" id="dropDownMenu' + id + '">';
                    toAdd += '<a href="tripsViewDetails?id=' + id + '">View More Details</a>';
                    toAdd += '<a href="tripsEdit?id=' + id + '">Edit</a>';
                    toAdd += '<a href="" onclick="deleteTrip(' + id + ')">Delete</a>';
                    toAdd += '</div>';
                    toAdd += '</div>';
                    toAdd += '</div>';
                    toAdd += '<div class="tripCardBody">';
                    toAdd += '<p class="tripCardText">Location: ' + locationName + '</p>';
                    toAdd += '<p class="tripCardText">Start Date: ' + startDate + '</p>';
                    toAdd += '<p class="tripCardText">End Date: ' + endDate + '</p>';
                    toAdd += '<p class="tripCardText">Cost Per Traveler: $' + cost + '</p>';
                    toAdd += '</div>';
                    toAdd += '</div>';
                    toAdd += '</div>';
                
                var rowIndexToAdd = rowNum - 1;
                var rowToAdd = '#row'+ rowIndexToAdd;
                $(rowToAdd).append(toAdd);
                counter++;
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

function clearTrips() {
    $('#tripsDiv').empty();
}

function deleteTrip(id) {
    //Confirm that user wants to delete
    var r = confirm("Are you sure you want to delete this trip?");
    
    //Delete if true
    if (r == true) {
        var url = "tripsDelete?id=" + id;
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