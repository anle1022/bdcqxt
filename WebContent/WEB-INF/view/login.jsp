<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>取号系统-登录</title>
<link rel="shortcut icon"
	href="<%=request.getContextPath() %>/img/favicon.ico"
	type="image/x-icon" />
<link rel="Bookmark"
	href="<%=request.getContextPath() %>/img/favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/easyui/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/easyui/themes/color.css">
<style type="text/css">
body {
	background: url('<%=request.getContextPath()%>/img/desktop.jpg')
		no-repeat center center;
}

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

	<div id="win" iconCls="icon-lock_go" title="登录">
		<form id="form" method="post">
			<div
				style="float: left; width: 130px; height: 100px; margin-right: 10px; border: 1px solid red; top: 30px;"></div>

			<div style="float: left; width: 180px;">
				<div id="msg_div"
					style="position: absolute; display: none; height: 20px;">
					<font color="red" size="1"><label>错误信息:</label> <label
						id="msg"></label></font>
				</div>
				<div style="position: absolute; margin-top: 22px;">
					<div class="fitem">
						<input name="loginname" class="easyui-textbox" iconCls="icon-user"
							required="true">
					</div>
					<div class="fitem">
						<input name="password" type="password" class="easyui-textbox"
							iconCls="icon-key" required="true">
					</div>
					<div id="fitem">
						<button class="easyui-linkbutton" type="button" onclick="login()"
							iconCls="icon-ok" style="width: 80px">登录</button>
						<button class="easyui-linkbutton" type="reset"
							iconCls="icon-cancel" style="width: 80px">重置</button>
					</div>
				</div>
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
		width:400,
		height:180,
		closable: false,
		resizable:false,
		minimizable: false,
		maximizable: false,
		collapsible: false,
		shadow:true,
		modal:true
		
	});
	
})
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