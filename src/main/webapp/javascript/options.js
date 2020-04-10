/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    if (isCustomerLoggedIn() == false) { return; }

    let customerIdentifier = getCustomerIdentifier();

    $.getJSON('api/onlinebanking/customer/' + customerIdentifier, function (customer) {
        $('#customerData').html(JSON.stringify(customer));
    });
});
