/**
 * 添加到购物车
 * Created by Administrator on 2018/8/2.
 */
$().ready(function() {
    validateRule();
});

$.validator.setDefaults({
    submitHandler : function() {
        save(1);
    }
});

/**
 * 保存
 * @param status
 */
function save(status) {
    $("#status").val(status);
    var num = $("#num").val();
    if (num == null || '' == num) {
        layer.alert("请填需要购买的数量后再提交");
    } else {
        $.ajax({
            cache : true,
            type : "POST",
            url : "/production/production/saveProductionCar",
            data : $('#signupForm').serialize(),// 你的formid
            async : false,
            error : function(request) {
                parent.layer.alert("Connection error");
            },
            success : function(r) {
                if (r.code == 0) {
                    var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                    parent.layer.msg(r.msg);
                    //操作成功以后返回列表
                    parent.layer.close(index);
                    //修改添加完成以后刷新列表
                    parent.reLoad();
                } else {
                    parent.layer.alert(r.msg)
                }
            }
        });
    }
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules : {
            num : "required"
        },
        messages : {
            num : "请填需要购买的数量"
        }
    });
}

function returnList() {
    var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    parent.layer.close(index);
}