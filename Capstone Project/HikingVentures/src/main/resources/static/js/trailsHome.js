$(document).ready(function () {
    //Load all Trails into the page
    loadTrails();
    
    //Add functionality for each menu button - be able to access each page
    $("#tripsButton").on("click", function() {
        window.open("/tripsHome", "_blank");
    });
    
    $("#trailsButton").on("click", function() {
        window.open("/trailsHome", "_self");
    });
    
    $("#travelersButton").on("click", function() {
        window.open("/travelersHome", "_blank");
    });
    
    $("#equipmentButton").on("click", function() {
        window.open("/equipmentHome", "_blank");
    });
    
    $("#locationsButton").on("click", function() {
        window.open("/locationsHome", "_blank");
    });
    
    $("#aboutButton").on("click", function() {
        window.open("/about", "_blank");
    });
    
    //Add functionality for page header buttons
    $("#createNewTrailButton").on("click", function() {
        window.open("/trailsAdd", "_self");
    });
    
    $(".dropDownMenu").hide();
    
    //Hovering over each menu button
    $(".navigationButtons").hover(function() {
        if($(this).attr("id") == "tripsButton") {
            $(this).css("border", "thin solid #87A330");
            $("#tripsTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "trailsButton") {
            $(this).css("border", "medium solid #87A330");
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
            $(this).css("border", "none");
            $("#tripsTitle").css("opacity", "0");
        } else if ($(this).attr("id") == "trailsButton") {
            $(this).css("border", "medium solid #87A330");
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
        }
    });
    
    //Hovering over buttons at top of page
    $(".pageHeaderButtons").hover(function() {
        if($(this).attr("id") == "createNewTrailButton") {
            $(this).css("border", "medium solid #87A330");
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
                    toAdd += '<a href="#">View More Details</a>';
                    toAdd += '<a href="trailsEdit?id=' + id + '">Edit</a>';
                    toAdd += '<a href="#">Delete</a>';
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

function showMoreOptionsMenu(id) {
    $("#dropDownMenu" + id).toggle();
}