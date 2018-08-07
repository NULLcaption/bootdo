/**
 * Created by Administrator on 2018/7/27.
 */
var prefix = "/production/production"
$(function() {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
        {
            method : 'get', // 服务器数据的请求方式 get or post
            url : prefix + "/productMecoList", // 服务器数据的加载地址
            // showRefresh : true,
            // showToggle : true,
            showColumns : true,
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
            //showColumns : false, // 是否显示内容下拉框（选择显示的列）
            sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
            // "server"

            queryParams : function(params) {
                return {
                    // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                    limit : params.limit,
                    offset : params.offset
                    // name:$('#searchName').val(),
                    // username:$('#searchName').val()
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
                    field : 'productImage',
                    title : '产品图片',
                    formatter : function(value, row, index) {
                        var productImage = row.productImage;
                        var productImageUrl = row.productImageUrl;
                        if (productImageUrl != null ) {
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
                    field : 'volume', // 列字段名
                    title : '体积(m^2)' // 列标题
                },
                {
                    field : 'weight', // 列字段名
                    title : '重量(KG)' // 列标题
                },
                {
                    title : '操作',
                    field : 'id',
                    align : 'center',
                    formatter : function(value, row, index) {
                        var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="加入购物车" onclick="addCar(\''
                            + row.pid
                            + '\')"><i class="fa fa-edit"></i></a> ';
                        var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="加入收藏"  mce_href="#" onclick="addCollect(\''
                            + row.pid
                            + '\')"><i class="fa fa-star"></i></a> ';
                        var f = '<a class="btn btn-success btn-sm" href="#" title="预览产品详情"  mce_href="#" onclick="preview(\''
                            + row.pid
                            + '\')"><i class="fa fa-rocket"></i></a> ';
                        return e + d +f;
                    }
                } ]
        });
}
/**
 * 点击放大图片
 * @type {{click .view: Function}}
 */
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
    $('#exampleTable').bootstrapTable('refresh');
}

/**
 * 批量加入购物车
 */
function batchAddCar() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要添加的数据!");
        return;
    }
    layer.confirm("确认要添加选中的'" + rows.length + "'条数据吗?", {
            btn : [ '确定', '取消' ]
            // 按钮
        }, function() {
            var pids = new Array();
            // 遍历所有选择的行数据，取每条数据对应的ID
            $.each(rows, function(i, row) {
                pids[i] = row['pid'];
            });
            var addPage = layer.open({
                type : 2,
                title : '加入购物车',
                maxmin : true,
                shadeClose : false, // 点击遮罩关闭层
                area : [ '400px', '520px' ],
                content : prefix + '/batchAddCar/' + pids // iframe的url
            });
            layer.full(addPage);
            //$.ajax({
            //    type : 'POST',
            //    data : {
            //        "pids" : pids
            //    },
            //    url : prefix + '/batchAddCar',
            //    success : function(r) {
            //        if (r.code == 0) {
            //            layer.msg(r.msg);
            //            reLoad();
            //        } else {
            //            layer.msg(r.msg);
            //        }
            //    }
            //});
        }, function() {

        }
    );
}

/**
 * 加入购物车
 * @param pid
 */
function addCar(pid) {
    var editPage = layer.open({
        type : 2,
        title : '加入购物车',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '400px', '520px' ],
        content : prefix + '/addCar/' + pid // iframe的url
    });
    layer.full(editPage);
}

/**
 * 加入收藏夹
 * @param id
 */
function addCollect(pid) {
    layer.confirm('确定要加入收藏夹？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            url : prefix + "/addCollect",
            type : "POST",
            data : {
                'pid' : pid
            },
            success : function(r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else if (r.code == 1) {
                    layer.msg(r.msg);
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}
/**
 * 预览产品详情
 * @param id
 */
function preview(pid) {
    var viewPage = layer.open({
        type : 2,
        title : '预览产品详情',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '400px', '520px' ],
        content : prefix + '/preview/' + pid // iframe的url
    });
    layer.full(viewPage);
}
/**
 * 批量加入收藏夹
 */
function batchAddCollect() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要收藏的数据!");
        return;
    }
    layer.confirm("确认要收藏选中的'" + rows.length + "'条数据吗?", {
        btn : [ '确定', '取消' ]
        // 按钮
    }, function() {
        var pids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function(i, row) {
            pids[i] = row['pid'];
        });
        $.ajax({
            type : 'POST',
            data : {
                "pids" : pids
            },
            url : prefix + '/batchAddCollect',
            success : function(r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function() {

    });
}