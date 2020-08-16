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
                    valign: 'middle'
                },
                {
                    title: "时间",
                    field: 'class',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '任务名称',
                    field: 'sex',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '作业员',
                    field: 'type',
                    align: 'center'
                },
                {
                    title: '检查员',
                    field: 'name',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '地理分区',
                    field: 'name',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采集',
                    field: 'work',
                    align: 'center'
                },
                {
                    title: '核查',
                    field: 'work',
                    align: 'center'
                },
                {
                    title: '编辑',
                    field: 'work',
                    align: 'center'
                },
                {
                    title: '质检',
                    field: 'work',
                    align: 'center'
                },
                {
                    title: '二查',
                    field: 'work',
                    align: 'center'
                },
                {
                    title: '合库',
                    field: 'work',
                    align: 'center'
                },
                {
                    title: '上交',
                    field: 'work',
                    align: 'center'
                },
                {
                    title: '进度',
                    field: 'id',
                    align: 'center',
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
    alert("数据查询类别接口");
    /*$.ajax({
        type: "GET",
        url: "../WorkRecord/SearchWork?dtStart=" +recodeTitle  + "&dtEnd=" + Publisher + "&dtEnd=" +  recodeTime,
        dataType: "json",
        success: function (result) {
            if (result.data) {
                var RccodeTableData = result.data;
                $('#table').bootstrapTable("load", RccodeTableData);
            }
        }
    })*/
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







