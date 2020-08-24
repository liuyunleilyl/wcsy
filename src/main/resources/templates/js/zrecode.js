/**
 * Created by Administrator on 2016/8/4.
 */
var hre=window.location.href;
var usercode=hre.split("#")[1];


var  currentID;
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
                    title: '质检',
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
                        var e = '<button button="#" mce_href="#" onclick="editRecode(\'' + row.taskPlanId + '\')">填报</button> ';
                        return e;
                    }
                }
            ]
        });
    });
   getRecodeTableData1();
}
function getRecodeTableData1() {
    $.ajax({
        async: false,
        type: "GET",
        url: "/taskPlan/taskPlanList?userCode="+usercode,
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

var jihuaid;
function editRecode(id) {
    jihuaid=id;
    openlayer3()
    currentID = id;
}

function getCurrentID() {
    return currentID;
}
function openlayer3() {
    layer.open({
        type: 2,
        title: '计划与进度',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
        closeBtn: 2,
        area: ['98%', '98%'],
        shadeClose: true,
        closeBtn: 2,
        content:"zrecode_tail.html#"+jihuaid+","+usercode

    });
}


//时间格式化函数
function getFormatTime(time) {
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


