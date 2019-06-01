$("#login").click(function() {
    $.post("/login", {
        name : $("#name").val(),
        password : $("#password").val()
    }, function(data) {
        //alert(data);
        if (data.code == 0) {
            //alert("成功")
            location.reload()
        } else {
            alert(data.msg);
            $("#loginForm").find("input").attr("class", "form-control is-invalid");
        }
        //alert("数据: \n" + data + "\n状态: " + status);
    });
});
$("#logout").click(function() {
    $.post("/logout", {

    }, function(data) {
        if (data.code == 0) {
            location.reload()
        } else {
            alert("无法登出");
        }
        //alert("数据: \n" + data + "\n状态: " + status);
    }, "json");
});