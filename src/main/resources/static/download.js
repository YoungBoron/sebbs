$("#addFile").click(function() {
    /*$.post("/add/file", {
        file : $("#file")[0].files[0]
    }, function(data) {
        //alert(data.code);
        if (data.code == 0) {
            alert("上传成功");
            location.reload()
        } else {
            alert(data.msg);
        }
        //alert("数据: \n" + data + "\n状态: " + status);
    });*/
    var formData = new FormData($("#addFileForm")[0]);
    $.ajax({
        type: "POST",
        data: formData,
        url: "/add/file",
        contentType: false,
        processData: false,
        dataType: "json"
    }).success(function (data) {
        //alert('成功' + data.code)
        if (data.code == 0) {
            alert("上传成功");
            location.reload()
        } else {
            alert(data.msg);
        }

    }).error(function (data) {
        alert(data.msg);
        console.log(data);
    });

});
