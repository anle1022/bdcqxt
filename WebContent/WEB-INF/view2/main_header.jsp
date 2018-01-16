<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="Bookmark" href="<%=request.getContextPath() %>/img/favicon.ico" />
<link rel="shortcut icon" href="<%=request.getContextPath() %>/img/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/matrix-admin01/css/fullcalendar.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/matrix-admin01/css/unicorn.main.css" />
<%-- <link rel="stylesheet" href="<%=request.getContextPath() %>/js_css/my/unicorn.blue.css" /> --%>
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/main.css"> --%>
	
<style type="text/css">
body{overflow-y: hidden;overflow-y: scroll;border-bottom: 0;background:url("<%=request.getContextPath() %>/img/top_bg.gif") repeat-x center top #fff;
    background-position: 0px -100px;}
#sidebar>ul {
	border-top: 0px !important;
}
#sidebar>ul>li {
    border-top: 0px;
}
#header1 {
	height: 83px;
    margin-top: 10px;
}
#header1 img{height:80px;}
.submenu > ul > li{background-color: #BEDFF3;
	margin-left: 0px;
	list-style: none}
.submenu > ul > li.active {
	background-color:blue;
}
@media (max-width: 767px) and (min-width: 481px)
#sidebar>ul ul:after {
    border-right: 6px solid #BEDFF3 !important;
}
#sidebar{margin-top:-10px;width:180px;}
#breadcrumb{background-color:#AFDDF7;border-top:0px}
a.leaf-click:hover {
	cursor: pointer
}
.datagrid-mask {  
  position: absolute;  
  left: 0;  
  top: 0;  
  width: 100%;  
  height: 100%;  
  opacity: 0.3;  
  filter: alpha(opacity=30);  
  display: none;  
}  
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<%=request.getContextPath()%>/matrix-admin01/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js_css/common.js"></script>
</head>
<body>
	<div id="header1">
		<img src="<%=request.getContextPath() %>/img/YU_jiankongLogo1.png" alt="" />
	</div>
	<div id="user-nav" >
		<ul id="nav_ul" class="btn-group">
			<li>
				<a href="javascript:void(0)" class=" " onclick="open1()">用户信息</a>
			</li>
			<li>
				<a href="<%=request.getContextPath() %>/user/logout.do" class="" > 退出</a>
			</li>
		</ul>
	</div>
		
 	<div id="sidebar">
		<ul id="sidebar_ul">
		<li><a href="<%=request.getContextPath() %>/dashboard.do"><i class="icon icon-home"></i><span>主   页</span></a></li>
			<li><a href="<%=request.getContextPath() %>/collectData/collectData.do"><i class="icon icon-home"></i><span>采 集</span></a></li>
			<c:forEach items="${menuList}" var="menu" >
				<c:choose>
					<c:when test="${ menu.leaf eq 1 }">
						<li id="${menu.id }"><a href="<%=request.getContextPath() %>${menu.url}?menuId=${list.id}" ><i class="${menu.iconCls }"></i><span>${menu.text }</span></a></li>
					</c:when>
					<c:otherwise>
						<li class="submenu" id="${menu.id }">
							<a href="#" ><i class="${menu.iconCls }"></i><span>${menu.text }</span></a>
							<ul>
								<c:forEach items="${menu.children }" var="list">
									<li id="${list.id }"><a href="<%=request.getContextPath() %>${list.url}?menuId=${list.id}"><i class="${list.iconCls }"></i><span>${list.text }</span></a></li>
								</c:forEach>
							</ul>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</ul>
	</div> 
	<div id="modifyUserWindow" class="easyui-window" title="修改登陆密码" iconCls="icon-save" closed="true" modal="true" style="width:450px;height:270px;padding:5px;float:left;display:none;">
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
	<script type="text/javascript">
	$(function(){
		initMenuShow();
		$("li:not(.submenu)").click(function(){
			
			MaskUtil.mask();	
		});
	});
	function initMenuShow(){
		var menuId = '${curMenu.id}';
			var li = $("#sidebar_ul").find("li").eq(0);
			li.addClass("active");
			$(".submenu").click(
					function() {
						$(this).addClass("active").find("ul").slideDown(250);
						$(this).siblings().removeClass("active").find("ul")
								.slideUp(250);
					})
			$.each($("#sidebar_ul").find("li"), function(i, d) {
				if (menuId == $(this).attr("id")) {
					if ($(this).parents(".submenu").length > 0) {
						$(this).parents(".submenu").addClass("active")
								.siblings().removeClass("active");
						$(this).parents(".submenu").find("ul").show();
						$(this).addClass("active").siblings().removeClass(
								"active");
					} else {
						//$(this).addClass("active").siblings().removeClass("active");
					}
					return;
				}

			})
		}
		function open1() {
			$('#modifyUserWindow').window('open');
		}

		function conserve() {
			var loginname = $("input[name=loginName]").val();
			var password = $("input[name=loginPassword]").val();
			var password2 = $("input[name=loginPassword2]").val();
			if (password != null && password != '' && password2 != null && password2 != '') {
				$.ajax({
					url : "<%=request.getContextPath() %>/user/updateEntity.do",
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