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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/easyui/themes/bootstrap/easyui.css">
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
	background-color: #FFF;
    margin-top: -23px;
    width: 100px;
    text-align: center;
}
div.djlx{
	display:none;
} 
div.bllx{
	display:none;
} 
.widget-box {
    background: none repeat scroll 0 0 #FFF;
}
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
<div class="row-fluid">
		<div class="span12">
		<div id="widget-box" class="widget-box">
			<div class="widget-title">
				<div class="tool-bar ">
					<label class="control-label">开始日期:</label>
					<div class="controls">
						<input id="startTime" value="${startTime }" name="time" class="easyui-datebox">
					</div>
					<label class="control-label">结束日期:</label>
					<div class="controls">
						<input id="endTime" value="${endTime }" name="time" class="easyui-datebox">
					</div>
					<button onclick="reload()" class="btn btn-mini">
						<i class="icon icon-search"></i>查询
					</button>
					<label class="control-label">地区/登记种类/办理类型:</label>
					<div class="controls">
						<input id="model" class="easyui-combobox" value="" style="width: 100px" data-options=" valueField: 'modelValue', textField: 'modelName', data:[{'modelName':'地区','modelValue':'organization'},{'modelName':'登记类型','modelValue':'djlx'},{'modelName':'办理类型','modelValue':'bllx'}],panelHeight:'auto'" />
					</div>
				</div>
			</div>
			<div class="widget-content nopadding updates">

				<div class="new-update clearfix">
					<ul class="stat-boxes quick-actions">
						<li class="processNode_li">
							<div class="left ">
								<a><i class="icon-people"></i></a>
							</div>
							<div class="right">
								<strong id="受理"></strong>受理
							</div>
						</li>
						<li class="processNode_li">
							<div class="left peity_bar_neutral">
								<a><i class="icon-people"></i></a>
							</div>
							<div class="right">
								<strong id="核定"></strong>核定
							</div>
						</li>
						<li class="processNode_li">
							<div class="left peity_bar_neutral">
								<a><i class="icon-shopping-bag"></i></a>
							</div>
							<div class="right">
								<strong id="缮证"></strong>缮证
							</div>
						</li>
						<li class="processNode_li">
							<div class="left peity_bar_neutral">
								<a><i class="icon-shopping-bag"></i></a>
							</div>
							<div class="right">
								<strong id="发证"></strong>发证
							</div>
						</li>
						<li class="processNode_li">
							<div class="left peity_bar_neutral">
								<a><i class="icon-shopping-bag"></i></a>
							</div>
							<div class="right">
								<strong id="退案"></strong>退案
							</div>
						</li>
					</ul>
				</div>
			</div>
			
		</div>

	</div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/jquery.easyui.min.js"></script>
<script src="<%=request.getContextPath()%>/matrix-admin01/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/datagrid-groupview.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/easyui.extends.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">

$(function(){
	
	reload();
	$("#model").combobox({
		onChange:function(option,option1){
			if(option != option1){
				$("div.box_div").slideUp(200);
				$("div."+option).slideDown(200);
			}
		}
	});
	$("#model").combobox("setValue","organization");
}); 

function initBtnEvent(){
	$(".processNode_li").click(function(){
		var startTime = $("#startTime").datebox("getValue");
		var endTime = $("#endTime").datebox("getValue");
		var processNode = $(this).find(".right").find("strong").attr("id");
		window.parent.parent.setBreadCrumb("案件流程");
		window.parent.parent.loadFrame("<%=request.getContextPath()%>/case/flow.do?startTime="+startTime+"&endTime="+endTime+"&processNode="+processNode);
	});
	$("#organization_ul>li").click(function(){
		var startTime = $("#startTime").datebox("getValue");
		var endTime = $("#endTime").datebox("getValue");
		var orgCode = $(this).find("strong").attr("class");
		window.parent.setBreadCrumb("案件流程");
		window.parent.loadFrame("<%=request.getContextPath()%>/case/flow.do?startTime="+startTime+"&endTime="+endTime+"&caseOrgCode="+orgCode);
	});
	$(".djlx_li").click(function(){
		var startTime = $("#startTime").datebox("getValue");
		var endTime = $("#endTime").datebox("getValue");
		var djlx = $(this).find("small").attr("class");
		window.parent.setBreadCrumb("案件流程");
		window.parent.loadFrame("<%=request.getContextPath()%>/case/flow.do?startTime="+startTime+"&endTime="+endTime+"&djlx="+djlx);
	});
	
}

function reload(){
	$("#model").combobox("setValue","organization");
	var startTime = $("#startTime").datebox("getValue");
	var endTime = $("#endTime").datebox("getValue");
	$.ajax({
		url:"<%=request.getContextPath()%>/case/countCaseDetial.do",
		data:{startTime:startTime,endTime:endTime},
		success:function(data){
			$(".box_div").remove();
			$.each(data.processNode,function(i,d){
				$("#"+d.key).html(d.value);
			});
			
			$.each(data.bllx,function(i,d){
				if($("#bllx").length>0){
					div = $("#bllx");
				}else{
					div = $("<div id='bllx' class='box_div bllx widget-content ' class='widget-content'><label class='box_title'>办理类型</label><ul id='bllx_ul' class='thumbnails'></ul>");
					$("#widget-box").append(div);
				}
				var li;
				if($("."+d.key).length>0){
					li = $("."+d.key).parent();
				}else{
					li = $("<li class='BOX_li bllx_li span2 col-md-2 col-sm-2 col-xxs-2' ><i class='icon-arrow-right'></i><strong class='"+d.key+"'>"+d.key+"</strong><br /></li>");
				}
				
				$("#"+d.key).html(d.value);
				var small = $("<small >"+d.key+":"+d.value+"</small><br />");
				li.append(small);
				$("#bllx_ul").append(li);
			});
			$.each(data.org_processNode,function(i,d){				
				var orgCode = d.key.split("-")[0];
				var processNode = d.key.split("-")[1];
				if($("#organization").length>0){
					div = $("#organization");
				}else{
					div = $("<div id='organization' class='box_div organization widget-content ' class='widget-content'><label class='box_title'>地区</label><ul id='organization_ul' class='thumbnails'></ul>");
					$("#widget-box").append(div);
				}
				var li;
				if($("."+orgCode).length>0){
					li = $("."+orgCode).parent();
				}else{
					li = $("<li class='BOX_li org_li span2 col-md-2 col-sm-2 col-xxs-2' ><i class='icon-arrow-right'></i><strong class='"+orgCode+"'>"+d.value1+"</strong><br /></li>");
				}
				var small = $("<small >"+processNode+":"+d.value+"</small><br />");
				li.append(small);
				$("#organization_ul").append(li);
			});
			$.each(data.djlx_processNode,function(i,d){
				var djlx = d.key.split("-")[0];
				var djlx1 = djlx.split("_")[0];
				var djlx2 = djlx.split("_")[1];
				
				var processNode = d.key.split("-")[1];
				var div;
				if($("#"+djlx2).length>0){
					div = $("#"+djlx2);
				}else{
					div = $("<div id='"+djlx2+"' class='box_div djlx widget-content ' class='widget-content'><label class='box_title'>"+djlx2+"</label><ul id='ul_"+djlx2+"' class='thumbnails'></ul>");
					$("#widget-box").append(div);
				}
				var li;
				if($("small."+djlx1).length>0){
					li = $("small."+djlx1).parent();
				}else {
					li = $("<li class='BOX_li djlx_li span2 col-md-2 col-sm-2 col-xxs-2' ><i class='icon-arrow-right'></i><small class='"+djlx1+"'>"+djlx1+"</small><br></li>");
				}
				var small = $("<small >"+processNode+":"+d.value+"</small><br />");
				li.append(small);
				$("#ul_"+djlx2).append(li);
				
			});
		 	
			initBtnEvent();
		},
		error:function(data){
			
		}
	});
}

</script>


</body>
</html>