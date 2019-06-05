$(document).ready(function () {
    bsCustomFileInput.init()
})

$("#addHomework").click(function() {
    $.post("/add/homework", $('#addHomeworkForm').serialize(), function(data) {
        //alert(data.code);
        if (data.code == 0) {
            alert("布置成功");
            location.reload()
        } else {
            alert(data.msg);
        }
        //alert("数据: \n" + data + "\n状态: " + status);
    });
});

$(".addFile").click(function() {
    var formData = new FormData($(this).parents(".addFileForm")[0]);
    $.ajax({
        type: "POST",
        data: formData,
        url: "/add/taskOrAssignment",
        contentType: false,
        processData: false,
        dataType: "json",
        success: function (data) {
            if (data.code == 0) {
                alert("上传成功");
                location.reload()
            } else {
                alert(data.msg);
            }
        },
        error: function (data) {
            alert(data.msg);
            console.log(data);
        }
    });
});
