<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>取号系统-登录</title>
<link rel="Bookmark"
	href="<%=request.getContextPath() %>/img/favicon.ico" />
<link rel="shortcut icon"
	href="<%=request.getContextPath() %>/img/favicon.ico"
	type="image/x-icon" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/matrix-admin01/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/matrix-admin01/css/bootstrap-responsive.min.css" />

<link rel="stylesheet"
	href="<%=request.getContextPath() %>/js_css/my/unicorn.login.css" />
<script src="<%=request.getContextPath()%>/matrix-admin01/js/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
body{background:url("<%=request.getContextPath()%>/img/top_bg.gif") repeat-x center top #fff;}

#logo{
    width: 519px;
    margin-left: auto;
    margin-right: auto;
    position: relative;
    
}
#loginbox{border:1px solid blue;}
#logo img {
    width: 505px;
    margin: 0 auto;
    display: block;
}

</style>
</head>
<script type="text/javascript">
	$(function(){
		var clientHeihgt = document.body.clientHeight;
		var padding = (clientHeihgt/2-200)+"px 58px 30px 5px";
		$("#logo").css("padding",padding );
	});

	
</script>
<body>
	<div id="logo">
		<img src="<%=request.getContextPath() %>/img/logo.png" alt="" />
	</div>
	<div id="loginbox">
		<form  class="form-vertical" >
		<div class="control-group">
			<div class="controls" style="height: 20px; margin-top: 16px;">
				<div class="input-prepend" id="msg_div" style="display: none">
					<span><font color="red" size="0"> <label
							style="vertical-align: center" id="msg"></label></font></span>
				</div>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
			
				<div class="input-prepend">
					
					<span class="add-on">用户：</span><input
						name="loginname" type="text" placeholder="用户名" /><span class="add-on"><i class="icon-user"></i></span>
				</div>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
			
				<div class="input-prepend">
				
					<span class="add-on">密码：</span><input
						name="password" type="password" placeholder="密码" /><span class="add-on"><i class="icon-lock"></i></span>
				</div>
			</div>
		</div>
		<div class="form-actions">
			<span class="pull-right"><input type="button" class="btn  btn-success" value="登 录" onclick="login()" /></span>
		</div>
		</form>
	
	</div>
	<script
		src="<%=request.getContextPath() %>/matrix-admin01/js/excanvas.min.js"></script>
	<script src="<%=request.getContextPath() %>/js_css/fix/respond.min.js" /></script>
	<script
		src="<%=request.getContextPath() %>/matrix-admin01/js/jquery.min.js"></script>
	
	<script type="text/javascript">
		$(document).keypress(function (e) { //这里给function一个事件参数命名为e，叫event也行，随意的，e就是IE窗口发生的事件。
		    var key = e.which; //e.which是按键的值
		    if (key == 13) {
		    	login();
		    }
		});
		//var height = document.body.clientHeight;
	        function login(){
	        	
	        	var loginname = $("input[name=loginname]").val();
	        	var password = $("input[name=password]").val();
	        	$.ajax({
	        		url:"<%=request.getContextPath() %>/user/login.do",
	        		data:{"loginname":loginname,"password":password},
	        		type:'post',
	        		success:function(data){
	        			if(data=="ok"){
	        				document.write("努力加载中...");
	        				location.href="<%=request.getContextPath() %>/main.do"
	        			}else{
	        				$("#msg_div").show();
	        				$("#msg").html("错误信息:"+data);
	        			} 
	        		}
	        	});
	        }
        </script>
</body>

</html>