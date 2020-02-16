$(document).ready(function () {
    //Load all Travelers into the page
    loadTravelers();
    
    //Add functionality for each menu button - be able to access each page
    $("#tripsButton").on("click", function() {
        window.open("/tripsHome", "_blank");
    });
    
    $("#trailsButton").on("click", function() {
        window.open("/trailsHome", "_blank");
    });
    
    $("#travelersButton").on("click", function() {
        window.open("/travelersHome", "_self");
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
    $("#createNewTravelerButton").on("click", function() {
        window.open("/travelersAdd", "_self");
    });
    
    $(".dropDownMenu").hide();
    
    //Hovering over each menu button
    $(".navigationButtons").hover(function() {
        if($(this).attr("id") == "tripsButton") {
            $(this).css("border", "thin solid #FF8552");
            $("#tripsTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "trailsButton") {
            $(this).css("border", "thin solid #FF8552");
            $("#trailsTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "travelersButton") {
            $(this).css("border", "medium solid #FF8552");
            $("#travelersTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "equipmentButton")  {
            $(this).css("border", "thin solid #FF8552");
            $("#equipmentTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "locationsButton") {
            $(this).css("border", "thin solid #FF8552");
            $("#locationsTitle").css("opacity", "1");
        } else if ($(this).attr("id") == "aboutButton") {
            $(this).css("border", "thin solid #FF8552");
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
            $(this).css("border", "medium solid #FF8552");
            $("#travelersTitle").css("opacity", "1");
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
        if($(this).attr("id") == "createNewTravelerButton") {
            $(this).css("border", "medium solid #FF8552");
            $("#createNewTravelerTitle").css("opacity", "1");
        }
        
    }, function() {
        if($(this).attr("id") == "createNewTravelerButton") {
            $(this).css("border", "none");
            $("#createNewTravelerTitle").css("opacity", "0"); 
        }
    });
});

function loadTravelers() {
    // Clear out all travelers previously loaded on the page
    clearTravelers();

    $.ajax ({
        type: 'GET',
        url: '/getAllTravelers',
        success: function (data, status) {
            var counter = 0;
            var rowNum = 1;
            $.each(data, function (index, Traveler) {
                var id = Traveler.travelerId;
                var firstName = Traveler.firstName;
                var lastName = Traveler.lastName;
                var birthDate = Traveler.birthDate;
                var pic = Traveler.photoFilePath;

                if(counter == 0 || counter % 2 == 0) {
                    $('#travelersDiv').append('<div class="row" id="row'+ rowNum + '"></div>')
                    rowNum++;
                }
                
                var toAdd = '<div class="col-md-offset-1 col-md-4">';
                    toAdd += '<div class="travelerCard">';
                    toAdd += '<div class="row" id="trailCardInfo">';
                    toAdd += '<div class="col-md-3">';
                    toAdd += '<img src="img/' + pic + '" alt="Traveler" class="travelerImages" id="Traveler' + id + '">';
                    toAdd += '</div>';
                    toAdd += '<div class="col-md-6">';
                    toAdd += '<div class="travelerCardText">' + firstName + ' ' + lastName + '</div>';
                    toAdd += '<div class="travelerCardText">ID: ' + id + '</div>';
                    toAdd += '<div class="travelerCardText">DOB: ' + birthDate + '</div>';
                    toAdd += '</div>';
                    toAdd += '<div class="col-md-3">';
                    toAdd += '<div class="row">';
                    toAdd += '<input type="image" src="img/MenuBar.png" alt="View More Options" title="View More Options" class="travelerCardButtons" id="' + id + '" onclick="showMoreOptionsMenu(id)">';
                    toAdd += '</div>';
                    toAdd += '<div class="row">';
                    toAdd += '<div class="dropDownMenu" id="dropDownMenu' + id + '">';
                    toAdd += '<a href="travelersViewDetails?id=' + id + '">View More Details</a>';
                    toAdd += '<a href="travelersEdit?id=' + id + '">Edit</a>';
                    toAdd += '<a href="" onclick="deleteTraveler(' + id + ')">Delete</a>';
                    toAdd += '</div>';
                    toAdd += '</div>';
                    toAdd += '</div>';
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

function clearTravelers() {
    $('#travelersDiv').empty();
}

function deleteTraveler(id) {
    //Confirm that user wants to delete
    var r = confirm("Are you sure you want to delete this traveler?");
    
    //Delete if true
    if (r == true) {
        var url = "travelersDelete?id=" + id;
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