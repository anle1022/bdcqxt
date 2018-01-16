<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>CRM Login</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/easyui/themes/color.css">
<style type="text/css">
#form {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}

.fitem input {
	width: 160px;
}
</style>
</head>
<body>

	<div id="win" title="Login Window">
		<form id="form" method="post">
			<div id="msg_div" style="display: none">
				<font color="red" size="1"><label>错误信息:</label> <label
					id="msg"></label></font>
			</div>
			<br />
			<div class="fitem">
				<label>username:</label> <input name="loginname"
					class="easyui-textbox" required="true">
			</div>
			<div class="fitem">
				<label>password:</label> <input name="password" type="password"
					class="easyui-textbox" required="true">
			</div>


			<div id="fitem">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="easyui-linkbutton" type="button" onclick="login()"
					iconCls="icon-ok" style="width: 90px">Login</button>
				<button class="easyui-linkbutton" type="reset" iconCls="icon-cancel"
					style="width: 90px">Cancel</button>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
$(function(){
	$('#win').window({
		width:350,
		height:180,
		modal:true
		
	});
	
});
function login(){
	$("#form").form({
		url:"<%=request.getContextPath() %>/user/login.do",
		//params:{"username":$("input[name=username]").val(),"password":$("input[name=password]").val()},
		Onsubmit:function(){
			return $(this).form('validate');
		},
		success:function(data){
			if(data=="ok"){
				location.href="<%=request.getContextPath() %>/main.do"
			}else{
				$("#msg_div").show();
				$("#msg").html(data)
			} 
		}
	})
	$("#form").submit();
}
</script>
</html>