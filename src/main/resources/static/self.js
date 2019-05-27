$("#submitUserInfo").click(function() {

    var jsonObj = {"userName":$("#userName").val(),"userEmail":$("#userEmail").val(),
                    "userPassword":$("#userPassword").val(), "userPassword2" : $("#userPassword2").val()};
    /*
        Jquery默认Content-Type为application/x-www-form-urlencoded类型
     */
    $.ajax({
        type: 'POST',
        url: "/modify",
        dataType: "json",
        data: JSON.stringify(jsonObj),
        contentType : "application/json",
        beforeSend :function(xmlHttp){
            xmlHttp.setRequestHeader("If-Modified-Since","0");
            xmlHttp.setRequestHeader("Cache-Control","no-cache");
        },
        success: function(data) {
            if (data.code == 0) {
            alert(data.msg)
            // location.reload()
            }
            else
                alert(data.msg);
        },
        error: function() {
            alert(data.msg);
        }
    });
});
