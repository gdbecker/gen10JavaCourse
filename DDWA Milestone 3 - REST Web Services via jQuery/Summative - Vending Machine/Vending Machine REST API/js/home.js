$(document).ready(function () {

    loadItems();
    
    //Add in functionality for buttons to add different money amounts
    $('#dollar-button').click(function (event) {
        var currentAmt = Number($('#totalMoneyInBox').val());
        currentAmt += 1.00;
        currentAmt = currentAmt.toFixed(2);
        $('#totalMoneyInBox').val(currentAmt);
    });
    
    $('#quarter-button').click(function (event) {
        var currentAmt = Number($('#totalMoneyInBox').val());
        currentAmt += 0.25;
        currentAmt = currentAmt.toFixed(2);
        $('#totalMoneyInBox').val(currentAmt);
    });
    
    $('#dime-button').click(function (event) {
        var currentAmt = Number($('#totalMoneyInBox').val());
        currentAmt += 0.10;
        currentAmt = currentAmt.toFixed(2);
        $('#totalMoneyInBox').val(currentAmt);
    });
    
    $('#nickel-button').click(function (event) {
        var currentAmt = Number($('#totalMoneyInBox').val());
        currentAmt += 0.05;
        currentAmt = currentAmt.toFixed(2);
        $('#totalMoneyInBox').val(currentAmt);
    });
    
    //Functionality for Make Purchase button
    $('#makePurchase-button').click(function (event) {
        var id = Number($('#itemBox').val());
        var moneyAmt = Number($('#totalMoneyInBox').val());
        
        if ($('#itemBox').val() == "") {
            $('#messagesBox').val("Please make a selection.");
            return;
        }
        
        $.ajax ({
            type: 'POST',
            url: 'http://tsg-vending.herokuapp.com/money/' + moneyAmt + '/item/' + id,
            success: function (response) {
                var changeString = "";
                
                if (response.quarters > 0) {
                    changeString += response.quarters + " quarters ";
                }
                
                if (response.dimes > 0) {
                    changeString += response.dimes + " dimes ";
                }
                
                if (response.nickels > 0) {
                    changeString += response.nickels + " nickels ";
                }
                
                if (response.pennies > 0) {
                    changeString += response.pennies + " pennies ";
                }
                
                if (response.quarters == 0 && response.dimes == 0 && response.nickels == 0 && response.pennies == 0) {
                    changeString = "No change";
                }
                
                $('#changeBox').val(changeString);
                $('#messagesBox').val("Thank You!!!");
                $('#totalMoneyInBox').val("");
                $('#itemBox').val("");
                loadItems();
            },
            
            error: function(responseJSON) {
                //var msg = response.message;
                $('#messagesBox').val(responseJSON.responseJSON.message);
                loadItems();
            }
        })
    });
    
    //Functionality for Change Return button
    $('#change-button').click(function (event) {
        $('#itemBox').val("");
        $('#messagesBox').val("");
        $('#totalMoneyInBox').val("");
        $('#changeBox').val("");
        loadItems();
    });
    
});

function loadItems() {
    // Clear out the items previously loaded into the machine
    clearItemsTable();

    $.ajax ({
        type: 'GET',
        url: 'http://tsg-vending.herokuapp.com/items',
        success: function (data, status) {
            var counter = 0;
            var rowNum = 1;
            $.each(data, function (index, item) {
                var id = item.id;
                var name = item.name;
                var price = item.price;
                var quantity = item.quantity;

                if(counter == 0 || counter % 3 == 0) {
                    $('#itemsDiv').append('<div class="row" id="row'+ rowNum + '"></div>')
                    rowNum++;
                }
                
                var toAdd = '<div class="col-md-2" id="item' + id + '" style="cursor:pointer" onclick="showItemIdInForm(' + id + ')">';
                    toAdd += '<p>' + id + '<br/>';
                    toAdd += '<p style="text-align: center">' + name + '<br/>';
                    toAdd += '<p style="text-align: center">$' + price + '<br/>';
                    toAdd += '<p style="text-align: center">Quantity Left: ' + quantity + '</p>';
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

function showItemIdInForm(id) {
    $('#itemBox').val(id);
}

function clearItemsTable() {
    $('#itemsDiv').empty();
}