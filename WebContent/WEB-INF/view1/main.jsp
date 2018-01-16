<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>统一取号系统</title>
<jsp:include page="./head.jsp"></jsp:include>
<link rel="Bookmark"
	href="<%=request.getContextPath() %>/img/favicon.ico" />
<link rel="shortcut icon"
	href="<%=request.getContextPath() %>/img/favicon.ico"
	type="image/x-icon" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/matrix-admin01/css/bootstrap-responsive.min.css" />

<link rel="stylesheet"
	href="<%=request.getContextPath() %>/matrix-admin01/css/fullcalendar.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/matrix-admin01/css/unicorn.main.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/js_css/my/unicorn.blue.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/easyui/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/easyui/themes/color.css">
<script
	src="<%=request.getContextPath()%>/matrix-admin01/js/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/matrix-admin01/js/bootstrap.min.js"></script>

<style type="text/css">
body{overflow: hidden;border-bottom: 0;background:url("<%=request.getContextPath()%>/img/top_bg.gif") repeat-x center top #fff;
    background-position: 0px -100px;}
#sidebar>ul {
	border-top: 0px !important;
}
#sidebar>ul>li {
    border-top: 0px;
}
#header1 {
    <%-- background: url("<%=request.getContextPath() %>/img/logo.png") !important; --%>
	height: 83px;
    margin-top: 10px;
}
#header1 img{height:80px;}
.leaf-submenu>ul>li.leaf-submenu {margin-left: 0px;}
.leaf-submenu>ul>li {
	background-color: #BEDFF3;
	margin-left: 0px;
	list-style: none
}
.leaf-submenu>ul>li.active {
	background-color:blue;
}
a.leaf-click:hover {
	cursor: pointer
}
#content{
	margin-bottom:42px;
}
#main-content,#iframe {
	margin-left: 0px;
	margin-right: 0px;
	margin-bottom: 2px;
	/*min-height: 500px;*/
	width: 100%;
	border: 1px solid #FFFFFF;
	border-top:0px;
}
#sidebar{margin-top:-10px;width:180px;}
#breadcrumb{background-color:#AFDDF7;border-top:0px}
#nav_ul{
	height: 30px;
    margin-top: 30px !important;
    margin-right: -20px !important;
}

#winId{
	
    margin-top: 40px ;
    margin-left: 60px ;
}
</style>
<script type="text/javascript">
	$(function(){
		var clientHeihgt;
		var scrollHeight;
		if(document.all){
			clientHeihgt = document.documentElement.clientHeight;
			scrollHeight = document.documentElement.scrollHeight;
		}else{
			clientHeihgt = document.body.clientHeight;
			scrollHeight = document.body.scrollHeight;
		}
		$("#content").css("height",clientHeihgt-scrollHeight );
		$("#main-content").css("height",$("#content").css("height") );
		
	});
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<div id="header1">
		<img src="<%=request.getContextPath() %>/img/logo.png" alt="" />
	</div>
	<div id="user-nav" >
		<ul id="nav_ul" class="btn-group">
			<li>
				<a href="javascript:void(0)" class=" " onclick="open1()">用户信息</a>
			</li>
			<li>
				<a href="<%=request.getContextPath() %>/user/logout.do" class="" >
				<!-- <i class=" icon-share-alt icon-danger"></i>  -->退出</a>
			</li>
		</ul>
	</div>
	<div id="sidebar">
		<a href="#" class="visible-phone"><i class="icon icon-home"></i>主   页</a>
		<ul id="sidebar_ul"></ul>
	</div>
	<div id="content">
		<div id="main-content" class="container">
		 	<div id="breadcrumb">
				<a href="#" class="current">主 页</a>
			</div>  
			<iframe id="iframe"  height="100%" width="100%" src="<%=request.getContextPath() %>/dashboard.do">
			 
			</iframe>
			<div id="loading" class="loading"> 正在加载中...</div>
			 <script type="text/javascript">
			 	iframeLoad();
			 	function iframeLoad(){
			  		var iframe = document.getElementById("iframe");
				 	var loading = $("#loading");
				 	iframe.style.display = "none";
				 	loading.show();
					if(iframe.attachEvent){
					    iframe.attachEvent("onload", function(){
					    	loading.hide();
					    	iframe.style.display = "block";
					    });
					} else {
					    iframe.onload = function(){
					    	
					    	loading.hide();
					    	iframe.style.display = "block";
					    };
					}
			  	}
			 </script>
		</div>
	</div>

	<div id="w" class="easyui-window" title="修改登陆密码" iconCls="icon-save" closed="true" modal="true" style="width:450px;height:270px;padding:5px;float:left;">
		<div id="winId">
			<p >用户名：<input type="text" class="easyui-textbox" style="height: 30px" name ="loginName" value="${sessionScope.user.loginname}" readonly="readonly"></p>
	    	<p>旧密码：<input type="password"  name="loginPassword" placeholder="请输入旧密码……"></p>
			<p>新密码：<input type="password"  name="loginPassword2" placeholder="请输入新密码……"></p>
	    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    	<button onclick="conserve()" class="btn btn-mini">
				<i class=" "></i>保存
			</button>
		</div>
	</div>

	<script
		src="<%=request.getContextPath() %>/matrix-admin01/js/excanvas.min.js"></script>
	<script
		src="<%=request.getContextPath() %>/matrix-admin01/js/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath() %>/matrix-admin01/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath() %>/js_css/common.js"></script>
	<script type="text/javascript"
	src="<%=request.getContextPath()%>/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/easyui/datagrid-groupview.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/easyui/easyui.extends.validate.js"></script>
	<script type="text/javascript"
	src="<%=request.getContextPath()%>/easyui/easyui-lang-zh_CN.js"></script>		
	<script type="text/javascript">
    $(function(){
    	initAccordion();
		
    });
    	
    function initMenuShow(){
    	var li = $("#sidebar_ul").find("li").eq(0);
    	li.addClass("active");
    	$(".leaf-submenu > ul").hide();
    	$(".leaf-submenu > a").click(function(){
    		$(this).parent("li").addClass("active");
    		$(this).parent("li").siblings().removeClass("active");
    		if($(this).parent("li").hasClass("submenu")){
    			$(this).parent("li").siblings().find("ul").slideUp(250);
    		}else{
	    		if($(this).next("ul").is(":hidden")){
	    			$(this).next("ul").slideDown(250);
	    			$(this).parent("li").siblings().find("ul").slideUp(250);
	    		}else{
	    			$(this).parent().find("ul").slideUp(250);
	    		}
    		}
    	});
    }
    function initAccordion(){
    	/* <li class="active"><a class="leaf-click" data-url="/dashboard.do" data-text="Dashboard"><i class="icon icon-home"></i> <span>Dashboard</span></a></li> */
    	var obj = new Object();
    	obj.url = "/dashboard.do";
    	obj.text = "主  页";
    	obj.iconCls = "icon icon-home";
    	obj.leaf = 1;
    	$("#sidebar_ul").append(parseData(obj));
    	var obj1 = new Object();
    	obj1.url = "/dashboard1.do";
    	obj1.text = "主  页1";
    	obj1.iconCls = "icon icon-home";
    	obj1.leaf = 1;
    	$("#sidebar_ul").append(parseData(obj1));
		$.ajax({
			url:"<%=request.getContextPath() %>/menu/findMenuList.do",
			success:function(data){
				$.each(data,function(n,d){
					$("#sidebar_ul").append(parseData(d));
				});
				initMenuShow();
				initClickUrl();
			}
		});
	}
    function initClickUrl(){
    	$(".leaf-click").click(function(){
    		var url = "<%=request.getContextPath() %>"+$(this).data("url");
    		var text = $(this).data("text");
    		$(this).parent().find("ul").slideUp(250);
    		setBreadCrumb(text);
    		loadFrame(url);
    	});
    }
    function setBreadCrumb(text){
    	$("#breadcrumb > a.current").html(text);
    }
    function parseData(data){
    	var li = $("<li></li>");
    	if(data.leaf==0){
    		li.attr("class","leaf-submenu");
    		var a = $('<a href="#"><i class="'+data.iconCls+' icon-black "></i> <span>'+data.text+'</span></a>');
    		li.append(a);
    		var ul = $("<ul></ul>");
    		$.each(data.children,function(i,d){
    			ul.append( parseData(d) );
    		});
    		li.append(ul);
		}else{
			li.attr("class","leaf-submenu submenu");
			var a = $('<a class="leaf-click" data-url="'+data.url+'" data-text="'+data.text+'"><i class="'+data.iconCls+'"></i> <span>'+data.text+'</span></a>');
			li.append(a);
		}
    	return li;
    }
  	function loadFrame(url){
  		
  		$("#iframe",parent.document.body).attr("src",url);
  	}
  	
  	
  	function open1(){
		$('#w').window('open');
	}
  	
  	function conserve(){
  		var loginname = $("input[name=loginName]").val();
    	var password = $("input[name=loginPassword]").val();
    	var password2 = $("input[name=loginPassword2]").val();
		if(password != null && password !='' && password2 != null && password2 !=''){
			$.ajax({
				url:"<%=request.getContextPath() %>/user/updateEntity.do",
				data:{"loginname":loginname,"password":password,"passwordNew":password2},
				type:"post",
 				success:function(data){
 					$.messager.alert("操作提示", data, "info");
 					if(data == "修改成功！"){
 						$('#w').window('close');
 					}
 				},
				error:function(data){
						$.messager.alert("操作提示", data, "info");
				}
			});
		}else{
			$.messager.alert("操作提示", "请将数据填写完整！", "info");
		}
  	}

	</script>
</body>
</html>