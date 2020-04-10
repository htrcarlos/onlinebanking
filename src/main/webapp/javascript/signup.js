$(function () {
    $("#signupButton").click(function () {

        var name = $("#name").val();
        var address = $("#address").val();
        var email = $("#email").val();
        var credentials = $("#credentials").val();
        var customer = "{name:'"+name+"',address:'"+address+"',email:'"+email+"',credentials:'"+credentials+"'}";

         $.ajax({
            type: "POST",
            url: "api/onlinebanking/createCustomer",
            data: customer,
            dataType: "json",
            contentType: "application/json"
        }).then(function (customer) {
            setCustomerIdentifier(customer.identifier);
            window.location.href = "index.html";
        });

    });
});
