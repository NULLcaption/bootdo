/**
 * 批量添加到购物车
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
    //购买的数量
    var numArr = new Array();
    $('input[id="num"]').each(function(){
        numArr.push($(this).val());
    });
    //产品id
    var pids = new Array();
    $('input[id="pid"]').each(function(){
        pids.push($(this).val());
    });
    if (numArr.length != 0) {
        for (var i=0; i<numArr.length; i++) {
            if (numArr[i] == null || '' == numArr[i]) {
                return layer.alert("请填需要购买的数量后再提交");
            }
        }
    } else {
        return layer.alert("请填需要购买的数量后再提交");
    }

    var productionDoList = [];
    $("#signupForm div").each(function(){
        //this代表当前dom对象tr
        var that = this;
        var orderItemObj = new Object();
        $(that).find("input").each(function(){
            var name = $(this).attr("name");
            orderItemObj[name] = $(this).val();
        });
        productionDoList.push(orderItemObj);
    });
    alert(productionDoList);

    //$.ajax({
    //    cache : true,
    //    type : "POST",
    //    url : "/production/production/saveProductionCar",
    //    data : $('#signupForm').serialize(),// 你的formid
    //    async : false,
    //    error : function(request) {
    //        parent.layer.alert("Connection error");
    //    },
    //    success : function(r) {
    //        if (r.code == 0) {
    //            var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    //            parent.layer.msg(r.msg);
    //            //操作成功以后返回列表
    //            parent.layer.close(index);
    //        } else {
    //            parent.layer.alert(r.msg)
    //        }
    //    }
    //});
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