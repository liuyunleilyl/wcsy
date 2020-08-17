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
                    field: 'ID',
                    align: 'center',
                    valign: 'middle',
                    visible: false
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
                    valign: 'middle'
                },
                {
                    title: '编辑',
                    field: 'bj',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '质检',
                    field: 'zj',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '二查',
                    field: 'ec',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '合库',
                    field: 'hk',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '上交',
                    field: 'sj',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '进度详情',
                    field: '',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row) {
                        var e = '<button button="#" mce_href="#" onclick="editRecode(\'' + row.WORKRECORDID + '\')">查看</button> ';
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
    alert("接口整合中");
}
//根据计划ID查询进度
function editRecode(id) {
    openlayer2()
    currentID = id;
}
function addRecode() {
    openlayer1()
    currentID = "";
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
        content:"myrecode01_tail.html"

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
    return y + '-' + add0(m) + '-' + add0(d) + ' ' + add0(h) + ':' + add0(mm) + ':' + add0(s);
}
function add0(m) { return m < 10 ? '0' + m : m }



