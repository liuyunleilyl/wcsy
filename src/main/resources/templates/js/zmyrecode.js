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
                    title: "年份",
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
                    title: '采集进度（%）',
                    field: 'cj',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '核查进度（%）',
                    field: 'hc',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '编辑进度（%）',
                    field: 'bj',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '一查进度（%）',//质检
                    field: 'zj',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '二查进度（%）',
                    field: 'ec',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '合库进度（%）',
                    field: 'hk',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '上交进度（%）',
                    field: 'sj',
                    align: 'center',
                    valign: 'middle'
                }
                /*,{
                    title: '进度详情',
                    field: 'id',
                    align: 'center',
                    formatter: function (value, row) {
                        var e = '<button button="#" mce_href="#" onclick="editRecode(\'' + row.WORKRECORDID + '\')">查看</button> ';
                        return e;
                    }
                }*/
            ]
        });
    });
   getRecodeTableData();
}
var hre=window.location.href;
var usercode=hre.split("#")[1];
function getRecodeTableData() {
    $.ajax({
        async: false,
        type: "GET",
        url: "/taskSchedule/unDoneAllSchedule?userCode="+usercode,
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
function getDataByname12(){
    alert("接口整合中");
}
//根据计划ID查询进度
function editRecode(id) {
    openlayer22()
    currentID = id;
}

function getCurrentID() {
    return currentID;
}
function openlayer22() {
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
        content:"zmyrecode01_tail.html"

    });
}







