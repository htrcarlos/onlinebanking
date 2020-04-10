
$(function () {
    $("#loginButton").click(function () {
       
        var email = $("#email").val();
        var credentials = $("#credentials").val();
        
        var stringUrl = "http://localhost:49000/api/onlinebanking/customerLogin?email="+email+"&credentials="+credentials;


        $.ajax({
            url: stringUrl 
       }).then(function (data) {
            
            var queryString = "?email="+data.email;
            window.location.href = "options.html"+queryString;

        });
    });

});

