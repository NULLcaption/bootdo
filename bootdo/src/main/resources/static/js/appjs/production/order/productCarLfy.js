var prefix = "/production/order"
$(function() {
    load3();
});

function load3() {
    $('#exampleTable3')
        .bootstrapTable(
        {
            method : 'get', // 服务器数据的请求方式 get or post
            url : prefix + "/productLfyCarList", // 服务器数据的加载地址
            // showRefresh : true,
            // showToggle : true,
            // showColumns : true,
            editable : true, //开启编辑模式
            iconSize : 'outline',
            toolbar : '#exampleToolbar',
            striped : true, // 设置为true会有隔行变色效果
            dataType : "json", // 服务器返回的数据类型
            pagination : true, // 设置为true会在底部显示分页条
            // queryParamsType : "limit",
            // //设置为limit则会发送符合RESTFull格式的参数
            singleSelect : false, // 设置为true将禁止多选
            // contentType : "application/x-www-form-urlencoded",
            // //发送到服务器的数据编码类型
            pageSize : 10, // 如果设置了分页，每页数据条数
            pageNumber : 1, // 如果设置了分布，首页页码
            // search : true, // 是否显示搜索框
            showColumns : false, // 是否显示内容下拉框（选择显示的列）
            sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
            // "server"
            queryParams : function(params) {
                return {
                    // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                    limit : params.limit,
                    offset : params.offset,
                    name : $('#searchName').val()
                };
            },
            // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
            // queryParamsType = 'limit' ,返回参数必须包含
            // limit, offset, search, sort, order 否则, 需要包含:
            // pageSize, pageNumber, searchText, sortName,
            // sortOrder.
            // 返回false将会终止请求
            columns : [
                {
                    checkbox : true
                },
                {
                    field : 'pid', // 列字段名
                    title : '产品ID' // 列标题
                },
                {
                    field : 'productImageUrl',
                    title : '产品图片链接',
                    visible : false
                },
                {
                    field : 'productImage',
                    title : '产品图片',
                    formatter : function(value, row, index) {
                        var productImageUrl = row.productImageUrl;
                        if (productImageUrl != null) {
                            return '<a class = "view"  href="javascript:void(0)"><img style="width:70px;height:30px;"  src="'+productImageUrl+'" /></a>';
                        }
                        return '';
                    },
                    events: 'operateEvents'//定义点击之后放大图片的事件
                },
                {
                    field : 'productCode', // 列字段名
                    title : '产品编码' // 列标题
                },
                {
                    field : 'productName', // 列字段名
                    title : '产品名称' // 列标题
                },
                {
                    field : 'category', // 列字段名
                    title : '所属分类' // 列标题
                },
                {
                    field : 'models', // 列字段名
                    title : '规格型号' // 列标题
                },
                {
                    field : 'units', // 列字段名
                    title : '单位' // 列标题
                },
                {
                    field : 'price', // 列字段名
                    title : '价格(元)' // 列标题
                },
                {
                    field : 'num',
                    title : '数量'
                },
                {
                    field : 'volume', // 列字段名
                    title : '体积(DM3)' // 列标题
                },
                {
                    field : 'weight', // 列字段名
                    title : '重量(KG)' // 列标题
                },
                {
                    title : '操作',
                    field : 'operation',
                    align : 'center',
                    formatter : function(value, row, index) {
                        var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="修改数量" onclick="editCarNum(\''
                            + row.pid
                            + '\')"><i class="fa fa-edit"></i></a> ';
                        var f = '<a class="btn btn-success btn-sm ' + s_resetPwd_h + '" href="#" title="删除" id="test1" mce_href="#" onclick="deleteData(\''
                            + row.pid
                            + '\')"><i class="fa fa-trash"></i></a> ';
                        return e + f;
                    }
                } ]
        });
}

window.operateEvents = {
    'click .view': function (e, value, row, index) {
        layer.open({
            type: 1,
            title: false,
            closeBtn: 0,
            area: 'auto',
            skin: 'layui-layer-nobg', //没有背景色
            shadeClose: true,
            content: '<img src="'+row.productImageUrl+'"/>'
        });
    }
};

function reLoad() {
    window.location.reload();
}


function reLoadindex() {
    $('#exampleTable3').bootstrapTable('refresh');
}

/**
 * editCarNum修改订单数量
 * @param pid
 */
function editCarNum(pid) {
    layer.open({
        type: 2,
        title: '修改订单数量',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/editCarNum/' + pid // iframe的url
    });
}

/**
 * 删除购物车数据
 * @param pid
 */
function deleteData(pid) {
    layer.confirm('确定要删除这条数据吗？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            url : prefix + "/deleteCar",
            type : "POST",
            data : {
                'pid' : pid
            },
            success : function(r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}