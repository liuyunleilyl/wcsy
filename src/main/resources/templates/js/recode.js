/**
 * Created by Administrator on 2016/8/4.
 */

var recodeTitle, Publisher, currentID, recodeTime, flag = true;
function Recodeload() {
    $(function () {
        $('#table').bootstrapTable({
            method: "get",
            striped: true,
            singleSelect: false,

            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search: false, //显示搜索框
            contentType: "application/x-www-form-urlencoded",
            queryParams: null,
            columns: [
                {
                    checkbox:"true",
                    align: 'center',
                    valign: 'middle'
                },
                {
                    field : 'taskId',
                    title : 'ID',
                    align : "center",
                    valign : "middle",
                    visible: false
                },
                {
                    title: "时间",
                    field: 'taskYear',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '任务名称',
                    field: 'taskName',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '完成情况',
                    field: 'wcbj',
                    align: 'center',
                    valign: 'middle',
                    formatter:function(value,row,index){
                        if(value==0){
                            return "未完成";
                        }else{
                            return "完成";
                        }
                    }
                }
            ]
        });
    });
   getRecodeTableData();
}
function getRecodeTableData() {
    $.ajax({
        async: false,
        type: "GET",
        url: "/task/taskList",
        dataType:"json",
        contentType:"application/json;charset=UTF-8",
        success: function(message){
            if(message.code==200){
                var RccodeTableData = message.data.records;
                $('#table').bootstrapTable("load", RccodeTableData);
            }
            else{
                alert("请求失误");
            }
        }

    });
}
//条件查询
function getRecodeTableDataByname() {
   var name=$("#name").val();
    $.ajax({
        async: false,
        type: "GET",
        url: "/task/taskList?taskName="+name,
        dataType:"json",
        contentType:"application/json;charset=UTF-8",
        success: function(message){
            if(message.code==200){
                var RccodeTableData = message.data.records;
                $('#table').bootstrapTable("load", RccodeTableData);
            }
            else{
                alert("请求失误");
            }
        }
    });
}

function addRecode() {
    openlayer()
    currentID = "";
}
function delRecode() {
    var a= $('#table').bootstrapTable('getSelections');
    if(a.length==1){
        var id=a[0].taskId;
        $.ajax({
            async: false,
            type: "GET",
            url: "/task/invalidTask?taskIds="+id,
            contentType:"application/json;charset=UTF-8",
            success: function(message){
                if(message.code==200){
                    alert("删除成功");
                    getRecodeTableData();
                }
                else{
                    alert("请求失误");
                }
            }
        });
    }else{
        alert("请选中一行")
    }
}
function getCurrentID() {
    return currentID;
}
function openlayer() {
    layer.open({
        type: 2,
        title: '定制任务',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
        closeBtn: 2,
        area: ['98%', '98%'],
        shadeClose: true,
        closeBtn: 2,
        content:"recode_tail.html"

    });
}





