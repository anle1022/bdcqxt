<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>统一取号系统</title>
<link rel="shortcut icon"
	href="<%=request.getContextPath() %>/img/favicon.ico"
	type="image/x-icon" />
<link rel="Bookmark"
	href="<%=request.getContextPath() %>/img/favicon.ico" />
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/easyui/default/easyui.css"> --%>
<%--  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/easyui/themes/bootstrap/easyui.css">
    --%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/easyui/default/easyui.css">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/easyui/demo/demo.css">
<style type="text/css">
*,body {
	margin: 0px;
	padding: 0px;
}

body {
	min-width: 800px;
	meizz: expression(this.style.width = this.offsetParent.clientWidth > 800 ?
		"800px" : "100%") min-height:500px;
	meizz: expression(this.style.height = this.offsetParent.clientHeight > 500 ?
		"500px" : "100%")
}

#north_div div {
	width: 69px;
	height: 17px;
	margin-right: 10px;
	margin-top: 35px;
	float: right;
}

#north {
	background-color: #E0ECFF
}

.top {
	background: url(images/bj_g.png) no-repeat #086faa;
	width: 100%;
	height: 80px;
}

.logo {
	vertical-align: middle;
	margin: 13px 0 0 48px;
	float: left;
}

.rit {
	height: 70px;
	float: right;
	margin-right: 40px;
	width: 580px;
}

.nav_tex {
	color: green;
	text-align: right;
	letter-spacing: 2px;
	margin: 5px 0 3px 5px;
	height: 24px;
	line-height: 24px;
}

.nav_a {
	margin-top: 10px;
	height: 30px;
}

.pic {
	vertical-align: middle;
	margin-right: 8px;
	margin-top: -5px;
}

.nav {
	/*height:30px;*/
	height: 25px;
	display: block;
	float: right;
	text-decoration: none;
	color: green;
	letter-spacing: 2px;
	margin-left: 15px;
}

.nav:hover {
	text-decoration: underline;
}

.nav:active {
	background: #086faa;
}

.panel-body {
	overflow-x: hidden
}
</style>
</head>

<body>
	<div class="easyui-layout" style="width: 100%; height: 100%">
		<div id="north" data-options="region:'north'"
			style="height: 60px; margin: 0px 4px 0px 0px;">
			<div class="indextop">
				<div class="indextoplogo">
					<div class="rit">
						<div class="nav_tex">
							欢迎您,<span id="deptuser">${user.username} </span>! &nbsp;<span
								id="weekDay">星期五</span>&nbsp;<span id="thetime">10：30</span>&nbsp;&nbsp;&nbsp;

						</div>
						<div class="nav_a">
							<a class="nav" href="#" id="logout"><img class="pic"
								src="images/clo_but.png" width="28" height="27" />退出</a> <a
								class="nav" href="#" id="login"><img class="pic"
								src="images/zux_but.png" width="28" height="27" />注销用户</a> <a
								class="nav" href="#" onClick="SysUser();"><img class="pic"
								src="images/ger_but.png" width="28" height="27" />个人设置</a> <a
								class="nav" href="#" onClick="SysSet();"><img class="pic"
								src="images/sz_but.png" width="28" height="27" />系统设置</a>
						</div>
					</div>

				</div>
			</div>
			<div id="north_buttom"></div>
		</div>
		<div data-options="region:'west',split:false"
			iconCls="icon-color_swatch" title="系统导航" style="width: 200px;">
			<div iconCls="icon-anchor" class="easyui-accordion"></div>
		</div>
		<div id="center" id="center-panel" class="easyui-tabs"
			data-options="region:'center',fit:false"
			style="margin-left: 3px; margin-bottom: 0px; overflow: hidden">
			<div title="系统首页" iconCls="icon-application_home"
				style="padding: 3px 3px;">
				<label>初始化 Menu</label>
			</div>
		</div>
		<div data-options="region:'south'"
			style="height: 20px; text-align: center">Copyright © 2016
			CangQiong. All rights reserved.</div>
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
		$.parser.parse();
		initAccordion();
		setTopTime();
		initLayout();
	});
	
	function initLayout(){
		//$(".easyui-layout").attr("style","width:100%;height:"+document.body.scrollHeight+"px");
	}
	function setTopTime() {
        var day = "";
        var month = "";
        var ampm = "";
        var ampmhour = "";
        var myweekday = "";
        var year = "";
        var myHours = "";
        var myMinutes = "";
        var mySeconds = "";
        mydate = new Date();
        myweekday = mydate.getDay();
        mymonth = parseInt(mydate.getMonth() + 1) < 10 ? "0" + (mydate.getMonth() + 1) : mydate.getMonth() + 1;
        myday = mydate.getDate();
        myyear = mydate.getYear();
        myHours = mydate.getHours();
        myMinutes = parseInt(mydate.getMinutes())<10? "0" + mydate.getMinutes() : mydate.getMinutes();
        mySeconds = parseInt(mydate.getSeconds()) < 10 ? "0" + mydate.getSeconds() : mydate.getSeconds();
        year = (myyear > 200) ? myyear : 1900 + myyear;
        if (myweekday == 0)
            weekday = " 星期日 ";
        else if (myweekday == 1)
            weekday = " 星期一 ";
        else if (myweekday == 2)
            weekday = " 星期二 ";
        else if (myweekday == 3)
            weekday = " 星期三 ";
        else if (myweekday == 4)
            weekday = " 星期四 ";
        else if (myweekday == 5)
            weekday = " 星期五 ";
        else if (myweekday == 6)
            weekday = " 星期六 ";

       // datetime.innerText = year + "年" + mymonth + "月" + myday + "日 " + myHours + ":" + myMinutes + ":" + mySeconds + " " + weekday;
        document.getElementById("weekDay").innerHTML = weekday;
        document.getElementById("thetime").innerHTML = myHours + ":" + myMinutes;
        setTimeout("setTopTime()", 60000);
    }
	function initAccordion(){
		$.ajax({
			url:"<%=request.getContextPath() %>/menu/findMenuByPid.do",
			success:function(data){
				$.each(data,function(n,d){
					$(".easyui-accordion").accordion("add",{
						title:d.text,
						iconCls:d.iconCls,
						selected:false//,
					});
					var div = $('<div style="padding:10px;"><ul iconCls="'+d.iconCls+'" name="'+d.text+'"></ul></div>');
					$(".easyui-accordion").accordion('panels')[n].append(div);
					var ul = $('ul[name="'+d.text+'"]');
					ul.tree({
						url:'<%=request.getContextPath() %>/menu/findMenuByPid.do?pid='+d.id,
						onClick:function(node){
							if(node.leaf==1){
								addTab(node.id,node.text,node.url,node.iconCls);
							}else{
								$(this).tree('toggle',node.target); 
							}
							
						}
						//params:{id:d.id}
					});
					
				});
				
			}
		});
	}
	
	function addTab(id,title,url,iconCls){
		if ($('#center').tabs('exists', title)){
			$('#center').tabs('select', title);
		} else{
			
			$('#center').tabs('add',{
				title:title,
				iconCls:iconCls,
				content:'<iframe scrolling="no" data-option="overflow:hidden" frameborder="auto"  src="<%=request.getContextPath() %>'+url+'" style="padding:0px 3px 0px 3px;width:100%;height:100%;"></iframe>',
				closable:true//,
				//id:id,
				//tools:[{
					/* iconCls:'icon-mini-refresh',
					handler:function(){
						alert('refresh');
					} */
				//}]
			});
		}
	}
	
</script>
</html>