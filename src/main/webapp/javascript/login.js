
$(function () {
    $("#loginButton").click(function () {
        $.ajax({
            type: 'POST',
            url: 'api/onlinebanking/customerLogin',
            data: JSON.stringify({
                email: $("#email").val(),
                credentials: parseInt($("#credentials").val())
            }),
            dataType: 'json',
            contentType: 'application/json'
       }).then(function (customer) {
            if (customer == null) { return; }
            setCustomerIdentifier(customer.identifier);
            window.location.href = 'options.html';
        });
    });
});

