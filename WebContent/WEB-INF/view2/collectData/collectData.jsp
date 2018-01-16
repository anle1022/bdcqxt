<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>统一取号系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/matrix-admin01/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/matrix-admin01/css/bootstrap-responsive.min.css" /> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/easyui/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/easyui/themes/color.css">
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/main.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/js_css/my/unicorn.blue.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/easyui/jquery.min.js"></script>
<jsp:include page="../main_header.jsp"></jsp:include>
<style type="text/css">
body {
	border: 0px;
}

.fitem {
	margin-left: 10px;
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}

.fitem input {
	width: 160px;
}

.fitem p {
	color: red;
	font-size: 12px;
}

.datagrid-row {
	cursor: pointer
}

.panel-body {
	overflow: hidden
}
.combo-panel{
	overflow:scroll;
}
.sp_tb{width:100%;height:100%;}

.sp_tb td{height:30px;margin-bottom:5px;border:1px solid lightgray}
.sp_yj{ height: 250px !important;}
.sp_yj textarea{width:95%;height:100%;margin-top:6px}
</style>
</head>
<body>
<div id="content" class="container">
	<div id="breadcrumb">
		<a href="#" title="流程监控" class="tip-bottom"><i class="icon-facetime-video"></i>信息采集</a>
		<a href="#" class="current">信息采集计算</a>
	</div>
	<!-- <div  id="center" data-options="region:'center'"  style="width:800px;height:100%;overflow:hidden" iconCls="icon-color_swatch"   > -->
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div id="toolbar_item">
					<div>
					开始日期: <input id="startTime" value="${startTime }" width="80" name="time" style="width: 120px"
						class="easyui-datebox">
					结束日期: <input id="endTime" value="${endTime}"  width="80" name="time" style="width: 120px"
						class="easyui-datebox">	
				
					<button onclick="collect()" class="btn btn-mini">
						<i class="icon icon-search"></i>采集
					</button>
					<button onclick="count()" class="btn btn-mini">
						<i class="icon icon-search"></i>计算
					</button>
					<button onclick="countExtendedCase()" class="btn btn-mini">案件超期计算</button>
					</div>
				</div>
				<!-- <div id="dg"></div> -->
				
			</div>
		</div>
	</div>
	
</div>
</body>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/easyui/jquery.easyui.min.js"></script>
<script
	src="<%=request.getContextPath()%>/matrix-admin01/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/easyui/datagrid-groupview.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/easyui/easyui.extends.validate.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	function collect(){
		var startTime = $("#startTime").datebox("getValue");
		var endTime = $("#endTime").datebox("getValue");
		$.ajax({
			url:"<%=request.getContextPath()%>/collectData/collect.do",
			data:{startTime:startTime,endTime:endTime},
			success:function(data){
			}
		})
	}
	function count(){
		var startTime = $("#startTime").datebox("getValue");
		var endTime = $("#endTime").datebox("getValue");
		$.ajax({
			url:"<%=request.getContextPath()%>/collectData/countFlowData.do",
			data:{startTime:startTime,endTime:endTime},
			success:function(data){
			}
		})
	}
	function countExtendedCase(){
		var startTime = $("#startTime").datebox("getValue");
		var endTime = $("#endTime").datebox("getValue");
		$.ajax({
			url:"<%=request.getContextPath()%>/collectData/countExtendedCase.do",
			data:{startTime:startTime,endTime:endTime},
			success:function(data){
				alert(data);
			}
		})
	}
</script>
</html>