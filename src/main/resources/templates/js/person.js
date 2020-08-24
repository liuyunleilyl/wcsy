/**
 * Created by Administrator on 2016/8/4.
 */

var user, role, currentID, flag = true;
function Personload() {
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
                field: 'userId',
                align: 'center',
                valign: 'middle'
            },
            {
                title: "用户名",
                field: 'userCode',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '密码',
                field: 'password',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '姓名',
                field: 'userName',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '角色',
                field: 'userRole',
                align: 'center',
                valign: 'middle'
            },
            /*{
                title: '操作',
                field: '',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row) {
                    var e = '<button button="#" mce_href="#" onclick="delNotice(\'' + row.WORKRECORDID + '\')">删除</button> '
                    return e;
                }
            }*/
        ]
    });
    getData();
}

//查询全部用户信息
function getData() {
    $.ajax({
        async: false,
        type: "GET",
        url: "/user/userList",
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
function add() {
    openlayer()
    currentID = "";
}
function openlayer(){
    layer.open({
        type: 2,
        title: '添加信息',
        shadeClose: true,
        shade: 0.5,
        skin: 'layui-layer-rim',
        closeBtn:2,
        area: ['80%', '90%'],
        shadeClose: true,
        closeBtn: 2,
        content: 'person_tail.html'
    });
}





