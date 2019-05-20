$("#login").click(function() {
    $.post("/login", {
        name : $("#name").val(),
        password : $("#password").val()
    }, function(data, status) {
        if (data == "true") {
            location.reload()
        } else {
            alert("用户名或密码错误");
            $("#loginForm").children("div").attr("class", "form-group has-error");
        }
        //alert("数据: \n" + data + "\n状态: " + status);
    });
});
$("#logout").click(function() {
    $.post("/logout", {

    }, function(data, status) {
        if (data == "true") {
            location.reload()
        } else {
            alert("无法登出");
        }
        //alert("数据: \n" + data + "\n状态: " + status);
    });
});