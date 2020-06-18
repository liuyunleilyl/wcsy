<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>会员登录</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="etool/js/bcss/bootstrap.css" type="text/css"></link>
<link rel="stylesheet" href="etool/js/bcss/bootstrap.min.css" type="text/css"></link>
<style type="text/css">
.oneDiv {
	text-align: center;
}

h1 {
	color: #FFF;
	font-size: 40px;
	text-shadow: #000 2px 2px 2px;
	letter-spacing: 5px;
	line-height: 20pt;
	margin-top:0px;
}

.twoDiv {
	width: 30%;
	height: 40%;
	position: relative;
	margin-left: 36%;
}

.accnumDiv {
	width: 90%;
	height: 25%;
	position: absolute;
	margin-left: 1%;
	border: 1px solid #D2D2D2;
	-moz-border-radius: 50px;
	-webkit-border-radius: 50px;
	border-radius: 50px;
}

input {
	border: 1px solid #FFF;
	font-size: 17px;
	width: 100%;
	margin-top: 8px;
}

.pwdDiv {
	width: 90%;
	height: 25%;
	position: absolute;
	margin-left: 1%;
	border: 1px solid #D2D2D2;
	-moz-border-radius: 50px;
	-webkit-border-radius: 50px;
	border-radius: 50px;
	margin-top: 18%;
}

.loginDiv {
	width: 100px;
	height: 40px;
	position: absolute;
	margin-left:30%;
	-moz-border-radius: 50px;
	-webkit-border-radius: 50px;
	border-radius: 50px;
	background: #0D7504;
	margin-top: 50%;
	color: white;
	text-align: center;
}

h2 {
	color: #FFF;
	letter-spacing: 7px;
	margin-bottom: 10px;
	margin-top:8%;
	margin-left:10%;
	cursor: pointer;
}

.ts {
	margin-top: 30%;
}

#btn_hover :hover {
	background-color: #71BC3A;
}
</style>
<script type="text/javascript" src="etool/js/jquary/jquery-2.1.1.js"></script>
<script type="text/javascript" src="etool/js/jquary/jquery.min.js"></script>
<script type="text/javascript" src="etool/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="etool/js/login.js"></script>
<script type="text/javascript">
	var path="<%=path%>";
	function keyLogin() {
		if (event.keyCode == 13) //回车键的键值为13
			login();
	}
	$(function() {
		$(document).ajaxComplete(
				function(event, xhr, settings) {
					if (xhr.getResponseHeader("sessionstatus") == "timeOut") {
						if (xhr.getResponseHeader("loginPath")) {
							alert("会话过期，请重新登录!");
							window.location.replace(xhr
									.getResponseHeader("loginPath"));
						} else {
							alert("请求超时请重新登录!");
						}
					}
				});
		$("#btn_hover").hover(function() {
			$("#btn_hover").css("background", "#72BC39");
		}, function() {
			$("#btn_hover").css("background", "#F0F0F0");
		});
	});
</script>
</head>

<body id="ibody" style="background:F7F7F7;cursor:hand;" onselectstart="return false">
	<table width="1003px" align="center" style="border:1px solid gray;" cellspacing="0" cellpadding="0">
		<tbody>
			<tr>
				<td height="230px" colspan="2" style="background-image:url('etool/image/login/login.jpg')">
					<div class="oneDiv">
						<h1>中国生态学学会</h1>
						<h1 style="margin-top:4%;">空中生态大讲堂</h1>
					</div></td>
			</tr>
			<tr>
				<td height="429px" colspan="2">
					<div class="twoDiv">
						<div class="accnumDiv">
							<div style="width: 18%;height: 65%; border-right: 1px solid #D2D2D2;float: left;margin-top: 2%;">
								<img src="etool/image/login/accnum.png" style="margin-left: 40%;height: 100%;"></img>
							</div>
							<div style="width: 70%;height: 70%; float: left;">
								<input placeholder="请输入会员账号" id="accnum" style="" type="text" />
							</div>
							<div class="ts" style="float: left;margin-left:10%"></div>
						</div>

						<div class="pwdDiv">
							<div style="width: 18%;height: 65%; border-right: 1px solid #D2D2D2;float: left;margin-top: 2%;">
								<img src="etool/image/login/pwd.png" style="margin-left: 40%;height: 100%;"></img>
							</div>
							<div style="width: 70%;height: 70%; float: left;">
								<input placeholder="请输入会员密码" id="pwd" style="" type="password" />
							</div>

							<div class="ts" style="float: left;width:50%"></div>

						</div>
						<button class="loginDiv" onclick="login()">
							<h2 style="font-size:20px">登录</h2>
						</button>
					</div></td>
			</tr>
		</tbody>
	</table>
</body>
</html>
