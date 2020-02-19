$(document).ready(function () {
    //Load all Trails into the page
    loadTrails();
    
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
    $("#createNewTrailButton").on("click", function() {
        window.open("/trailsAdd", "_self");
    });
    
    $(".dropDownMenu").hide();
    
    //Hovering over each menu button
    $(".navigationButtons").hover(function() {
        if($(this).attr("id") == "tripsButton") {
            $(this).css("border", "thin solid #6BBF59");
            $("#tripsTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "trailsButton") {
            $(this).css("border", "medium solid #6BBF59");
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
            $(this).css("border", "none");
            $("#tripsTitle").css("opacity", "0");
        } else if ($(this).attr("id") == "trailsButton") {
            $(this).css("border", "medium solid #6BBF59");
            $("#trailsTitle").css("opacity", "1");
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
        if($(this).attr("id") == "createNewTrailButton") {
            $(this).css("border", "medium solid #6BBF59");
            $("#createNewTrailTitle").css("opacity", "1");
        }
        
    }, function() {
        if($(this).attr("id") == "createNewTrailButton") {
            $(this).css("border", "none");
            $("#createNewTrailTitle").css("opacity", "0"); 
        }
    });
});

function loadTrails() {
    //Clear out page of trails
    clearTrails();

    //Load all trails from db into the page 
    $.ajax ({
        type: 'GET',
        url: '/getAllTrails',
        success: function (data, status) {
            $.each(data, function (index, Trail) {
                var id = Trail.trailId;
                var trailName = Trail.trailName;
                var location = Trail.location;
                var locationName = location.parkName
                var pic = Trail.photoFilePath;
                
                var toAdd = '<div class="row">';
                    toAdd += '<div class="trailCard" style="background-image: url(img/' + pic + ');">';
                    toAdd += '<div class="layer"><div>';
                    toAdd += '<div class="row" id="trailCardInfo">';
                    toAdd += '<div class="col-md-1">';
                    toAdd += '<input type="image" src="img/MenuBar.png" alt="View More Options" title="View More Options" class="trailCardButtons" id="' + id + '" onclick="showMoreOptionsMenu(id)">';
                    toAdd += '</div>';
                    toAdd += '<div class="col-md-3">';
                    toAdd += '<div class="dropDownMenu" id="dropDownMenu' + id + '">';
                    toAdd += '<a href="trailsViewDetails?id=' + id + '">View More Details</a>';
                    toAdd += '<a href="trailsEdit?id=' + id + '">Edit</a>';
                    toAdd += '<a href="" onclick="deleteTrail(' + id + ')">Delete</a>';
                    toAdd += '</div>';
                    toAdd += '</div>';
                    toAdd += '<div class="col-md-11">';
                    toAdd += '<div class="trailCardTextTrail">' + trailName + '</div>';
                    toAdd += '<div class="trailCardTextLocation">' + locationName + '</div>';
                    toAdd += '</div>';
                    toAdd += '</div>';
                    toAdd += '</div>';
                    toAdd += '</div>';
                
                $("#trailsDiv").append(toAdd);
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

function clearTrails() {
    $('#trailsDiv').empty();
}

function deleteTrail(id) {
    //Confirm that user wants to delete
    var r = confirm("Are you sure you want to delete this trail?");
    
    //Delete if true
    if (r == true) {
        var url = "trailsDelete?id=" + id;
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