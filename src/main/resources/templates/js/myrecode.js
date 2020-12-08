/**
 * Created by Administrator on 2016/8/4.
 */

var recodeTitle, Publisher, currentID, recodeTime, flag = true;
function Recodeload() {
    //绑定任务下拉框
    $.ajax({
        async: false,
        type: "GET",
        url: "/task/taskList",
        dataType:"json",
        contentType:"application/json;charset=UTF-8",
        success: function(message){
            if(message.code==200){
                var RccodeTableData = message.data.records;
                var tempIdStr = '<option  value="">选择</option>';
                $("#rw").append(tempIdStr);
                $.each(RccodeTableData, function (i, item) {
                    var tempId = '<option  value="' + item.taskId + '">' + item.taskYear + item.taskName + '</option>';
                    $("#rw").append(tempId);
                });
                // 更新 。 这一步很重要
                $('#rw').selectpicker('refresh');
            }
            else{
                alert("请求失误");
            }
        }
    });
    //表格绑定
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
                    field: 'ID',
                    align: 'center',
                    valign: 'middle',
                    //visible: false
                },
                {
                    title: "计划ID",
                    field: 'taskPlanId',
                    align: 'center',
                    valign: 'middle',
                    visible: false
                },
                {
                    title: '作业员',
                    field: 'userName',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '检查员',
                    field: 'jcyName',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '地理分区',
                    field: 'dlfq',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采集',
                    field: 'cj',
                    align: 'center',
                    valign: 'middle',
                    formatter:function(value,row,index){
                        return getFormatTime(value);
                    }
                },
                {
                    title: '核查',
                    field: 'hc',
                    align: 'center',
                    valign: 'middle',
                    formatter:function(value,row,index){
                        return getFormatTime(value);
                    }
                },
                {
                    title: '编辑',
                    field: 'bj',
                    align: 'center',
                    valign: 'middle',
                    formatter:function(value,row,index){
                        return getFormatTime(value);
                    }
                },
                {
                    title: '一查',
                    field: 'zj',
                    align: 'center',
                    valign: 'middle',
                    formatter:function(value,row,index){
                        return getFormatTime(value);
                    }
                },
                {
                    title: '二查',
                    field: 'ec',
                    align: 'center',
                    valign: 'middle',
                    formatter:function(value,row,index){
                        return getFormatTime(value);
                    }
                },
                {
                    title: '合库',
                    field: 'hk',
                    align: 'center',
                    valign: 'middle',
                    formatter:function(value,row,index){
                        return getFormatTime(value);
                    }
                },
                {
                    title: '上交',
                    field: 'sj',
                    align: 'center',
                    valign: 'middle',
                    formatter:function(value,row,index){
                        return getFormatTime(value);
                    }
                },
                {
                    title: '进度详情',
                    field: '',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row) {
                        var e = '<button button="#" mce_href="#" onclick="editRecode(\'' + row.taskPlanId + '\')">查看</button> ';
                        return e;
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
        url: "/task/editTaskPlanList",
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
//根据条件查询
function getDataByname(){
    var elem = document.getElementById("rw");
    var index=elem.selectedIndex;
    var name=elem.options[index].text;
    var id=elem.options[index].value
    if(name=="选择"){
        $.ajax({
            async: false,
            type: "GET",
            url: "/task/editTaskPlanList",
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
    }else{
        $.ajax({
            async: false,
            type: "GET",
            url: "/task/editTaskPlanList",
            dataType:"json",
            data:{
                taskId:id
            },
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
}
//根据计划ID查询进度
var jihuaid;
function editRecode(id) {
    jihuaid=id;
    openlayer2()
    currentID = id;
}
function addRecode() {
    openlayer1()
    currentID = "";
}

function del(){
    var a= $('#table').bootstrapTable('getSelections');
    if(a<1){
        alert("请选中一行")
    }else if(a.length==1){
        var id=a[0].taskPlanId;
        $.ajax({
            async: false,
            type: "GET",
            url: "/task/invalidTaskPlan?taskPlanIds="+id,
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
        alert("请选中一行进行删除")
    }
}
function getCurrentID() {
    return currentID;
}
function openlayer1() {
    layer.open({
        type: 2,
        title: '新增计划',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
        closeBtn: 2,
        area: ['98%', '98%'],
        shadeClose: true,
        closeBtn: 2,
        content:"myrecode_tail.html"
    });
};

function openlayer2() {
    layer.open({
        type: 2,
        title: '进度详情',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
        closeBtn: 2,
        area: ['98%', '98%'],
        shadeClose: true,
        closeBtn: 2,
        content:"myrecode01_tail.html?data="+jihuaid

    });
}


//时间格式化函数
function getFormatTime(time) {
    if(!time){
        return ''
    }
    var time = new Date(time);
    var y = time.getFullYear();
    var m = time.getMonth() + 1;
    var d = time.getDate();
    var h = time.getHours();
    var mm = time.getMinutes();
    var s = time.getSeconds();
    return y + '-' + add0(m) + '-' + add0(d);
}
function add0(m) { return m < 10 ? '0' + m : m }

function exportTaskSchdule(){
    var elem = document.getElementById("rw");
    var index=elem.selectedIndex;
    var name=elem.options[index].text;
    var id=elem.options[index].value;
    if(name=="选择"){
        window.location.href = '/taskSchedule/downloadUnDoneAllSchedule'
    }else{
        window.location.href = '/taskSchedule/downloadUnDoneAllSchedule?taskId='+id
    }


        // $.ajax({
        //     async: false,
        //     type: "GET",
        //     url: "/taskSchedule/downloadUnDoneAllSchedule",
        //     responseType: 'blob',
        //     success(res){
        //         let blob = new Blob([res], {type: `application/blob;charset=utf-8`});
        //         // 获取heads中的filename文件名
        //         let downloadElement = document.createElement('a');
        //         // 创建下载的链接
        //         let href = window.URL.createObjectURL(blob);
        //         downloadElement.href = href;
        //         // 下载后文件名
        //         downloadElement.download = '111.xls';
        //         document.body.appendChild(downloadElement);
        //         // 点击下载
        //         downloadElement.click();
        //         // 下载完成移除元素
        //         document.body.removeChild(downloadElement);
        //         // 释放掉blob对象
        //         window.URL.revokeObjectURL(href);
        //
        //     }
        // });
}