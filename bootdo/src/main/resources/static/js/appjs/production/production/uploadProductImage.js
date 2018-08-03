/**
 * Created by Administrator on 2018/7/31.
 */
$().ready(function(){
    alert("11");
});

function returnList() {
    var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    parent.layer.close(index);
    window.parent.reLoadindex();
}