<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Unicorn Admin</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/matrix-admin01/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/matrix-admin01/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/easyui/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/easyui/themes/color.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/matrix-admin01/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/matrix-admin01/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/matrix-admin01/css/jquery.gritter.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/matrix-admin01/css/unicorn.main.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/js_css/radioAndCheckbox.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/easyui/jquery.min.js"></script>
<%-- <script type="text/javascript" src="<%=request.getContextPath() %>/js_css/fix/respond.min.js"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js_css/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js_css/radioAndCheckbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/matrix-admin01/js/jquery.min.js"></script>

<style type="text/css">
.quick-actions li a {
	padding: 4px 0px;
	padding-left: 9px;
}

.stat-boxes,.quick-actions,.quick-actions-horizontal,.stats-plain {
	display: inline-block;
	list-style: none outside none;
	/* margin: -8px 0 -23px; */
	text-align: center;
}

.stat-boxes>li:hover {
	cursor: pointer
}
/* .widget-box {
	margin-top: 9px;
	margin-bottom: 0px;
} */
select {
	width: 65px;
	background-color: #fff;
	border: 1px solid #ccc;
}

.widget-title>div.tool-bar>* {
	float: left;
	/* margin:auto auto; */
}

.widget-title>div.tool-bar {
	margin-top: 3px;
	padding-left: 3px;
}
/*  div.tool-bar>label, div.tool-bar>button{ padding-top:3px; line-height: 26px;} */
div.tool-bar>* {
	margin-right: 5px;
	margin-top: 6px;
}
.row-fluid>.span12{margin-top:-7px;}
input.easyui-datebox {
	width: 100px !important
}
.BOX_li{
    background-color: #F5F5F5;
    border: 1px solid #DDDDDD;
    cursor: pointer;
    margin-left:5px !important;
    padding: 13px 20px 10px;
    position: relative;
	height:165px !important;
}
.BOX_li small {
    margin-left: 5px;
    font-size: 12px;
    color: #888888;
/*     font-style: italic; */
}
.box_title{    
	background-color: #F9F9F9;
    margin-top: -23px;
    width: 100px;
    text-align: center;
}
div.djlx{
	display:none;
} 
.row-fluid>.span12{margin-top:8px;}
</style>
<script type="text/javascript">
var width = $(window).width();  
var height = $(window).height();    
var html = "<div id='loading' style='position:absolute;left:0;width:100%;height:" + height + "px;top:0;background:lightgray;opacity:1;filter:alpha(opacity=100);'>";  
html += "<div style='position:absolute;cursor1:wait;left:" + ((width / 2) - 75) + "px;top:200px;width:160px;height:16px;padding:12px 5px 10px 30px;";  
html += "background:#fff url(<%=request.getContextPath() %>/easyui/themes/default/images/loading.gif) no-repeat scroll 5px 10px;border:2px solid #ccc;font-size:12px;color:#000;'>";  
html += "正在加载，请等待...";  
html += "</div>";  
html += "</div>";    
window.onload = function () {  
    var mask = document.getElementById('loading');  
    mask.parentNode.removeChild(mask);  
    $("body").show();
};  
document.write(html);	
</script>
</head>
<body>
<div id="tt" title=" " fit="true" style="height:100%;">
		<div title="图标统计" style="padding:1px;" closable="false"  fit="true">			
			<iframe scrolling="yes" frameborder="0"  src="<%=request.getContextPath()%>/charts.do" style="width:100%;height:100%;"></iframe>
			
		</div>
		<div title="分类统计" style=""  fit="true" >
			<iframe scrolling="yes" frameborder="0"  src="<%=request.getContextPath()%>/dashboard.do" style="width:100%;height:100%;"></iframe>
			
		</div>
		
	</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/jquery.easyui.min.js"></script>
<script src="<%=request.getContextPath()%>/matrix-admin01/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/datagrid-groupview.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/easyui.extends.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">

$(function(){
	$("#tt").tabs();
	
}); 

</script>
</body>
</html>