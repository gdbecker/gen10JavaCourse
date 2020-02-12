$(document).ready(function () {
    //Load all Equipment into the page
    loadEquipment();
    
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
    $("#createNewEquipmentButton").on("click", function() {
        window.open("/equipmentAdd", "_self");
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
        if($(this).attr("id") == "createNewEquipmentButton") {
            $(this).css("border", "medium solid #87A330");
            $("#createNewEquipmentTitle").css("opacity", "1");
        }
        
    }, function() {
        if($(this).attr("id") == "createNewEquipmentButton") {
            $(this).css("border", "none");
            $("#createNewEquipmentTitle").css("opacity", "0"); 
        }
    });
});

function loadEquipment() {
    // Clear out all equipment previously loaded on the page
    clearEquipment();

    $.ajax ({
        type: 'GET',
        url: '/getAllEquipment',
        success: function (data, status) {
            var counter = 0;
            var rowNum = 1;
            $.each(data, function (index, Equipment) {
                var id = Equipment.equipmentId;
                var name = Equipment.name;
                var description = Equipment.description;
                var pic = Equipment.photoFilePath;

                if(counter == 0 || counter % 3 == 0) {
                    $('#equipmentDiv').append('<div class="row" id="row'+ rowNum + '"></div>')
                    rowNum++;
                }
                
                //var toAdd = '<div class="row">';
                var toAdd = '<div class="col-md-3">';
                    toAdd += '<div class="equipmentCard">';
                    toAdd += '<div class="row" id="equipmentCardInfo">';
                    toAdd += '<div class="col-md-4">';
                    toAdd += '<div class="equipmentCardText">' + name + '</div>';
                    toAdd += '</div>';
                    toAdd += '<div class="col-md-1">';
                    toAdd += '<input type="image" src="img/MenuBar.png" alt="View More ';
                    toAdd += 'Options" title="View More Options" ';
                    toAdd += 'class="equipmentCardButtons" id="' + id + '" ';
                    toAdd += 'onclick="showMoreOptionsMenu(id)">';
                    toAdd += '<div class="dropDownMenu" id="dropDownMenu' + id + '">';
                    toAdd += '<a href="#">View More Details</a>';
                    toAdd += '<a href="equipmentEdit?id=' + id + '">Edit</a>';
                    toAdd += '<a href="" onclick="deleteEquipment(' + id + ')">Delete</a>';
                    toAdd += '</div>';
                    toAdd += '</div>';
                    toAdd += '</div>';
                    toAdd += '<div class="row">';
                    toAdd += '<img src="img/' + pic + '" alt="Equipment" ';
                    toAdd += 'class="equipmentImages">';
                    toAdd += '</div>';
                    toAdd += '</div>';
                    toAdd += '</div>';
                    //toAdd += '</div>';
                
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

function clearEquipment() {
    $('#equipmentDiv').empty();
}

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

function showMoreOptionsMenu(id) {
    $("#dropDownMenu" + id).toggle();
}