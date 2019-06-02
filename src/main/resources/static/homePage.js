$('#updateModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)
    var modal = $(this)
    modal.find('input[name="id"]').val(button.data('id'));
    modal.find('input[name="name"]').val(button.data('name'));
    modal.find('input[name="description"]').val(button.data('description'));
    modal.find('option').attr("selected", null);
    modal.find('option[value='+button.data('zoneid')+']').attr("selected", "selected");
});
$("#update").click(function(){
    $.post("/update/board", $('#updateForm').serialize(), function(data) {
        //alert(data.code);
        if (data.code == 0) {
            alert("修改成功");
            location.reload()
        } else {
            alert(data.msg);
        }
        //alert("数据: \n" + data + "\n状态: " + status);
    });
});

$('#deleteModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)
    var modal = $(this)
    modal.find('input[name="id"]').val(button.data('id'));
    modal.find('input[name="name"]').val(button.data('name'));
});

$("#delete").click(function(){
    $.post("/delete/board", $('#deleteForm').serialize(), function(data) {
        if (data.code == 0) {
            alert("删除成功");
            location.reload()
        } else {
            alert(data.msg);
        }
    });
});

$('#addModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)
    var modal = $(this)
    modal.find('option').attr("selected", null);
    modal.find('option[value='+button.data('zoneid')+']').attr("selected", "selected");
});
$("#add").click(function(){
    $.post("/add/board", $('#addForm').serialize(), function(data) {
        if (data.code == 0) {
            alert("增加成功");
            location.reload()
        } else {
            alert(data.msg);
        }
    });
});