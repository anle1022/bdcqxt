﻿<%@ page language="java" contentType="text/html; charset=utf-8"
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

</style>
</head>
<body>
<div id="content" class="container">
	<div id="breadcrumb">
		<a href="#" title="取号管理" class="tip-bottom"><i class="icon-eye-open"></i> 取号管理</a>
		<a href="#" class="current">取号统计</a>
	</div> 
	<!-- <div  id="center" data-options="region:'center'"  style="width:800px;height:100%;overflow:hidden" iconCls="icon-color_swatch"   > -->
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div id="toolbar_item">
					<div>
					开始日期: <input id="startTime" width="80" name="time" value="${startTime}" style="width: 120px" type ="text"
						class="easyui-datebox">
					结束日期: <input id="endTime" width="80" name="time" value="${endTime}" style="width: 120px" type ="text"
						class="easyui-datebox">	
					</div>
					<div>
						登记机构部门: <input id="djType" width="80" name="time"  style="width: 120px" 
						class="easyui-combobox"
						data-options="
			                     valueField: 'id',
			                    textField: 'orgName',
			                  	url:'<%=request.getContextPath()%>/dept/findEntityList.do'
			                  	">	
						取号类型: <input id="type" class="easyui-combobox" name="type" 
								style="width: 200px" data-options="
			                     valueField: 'id',
			                    textField: 'name',
			                   	url:'<%=request.getContextPath()%>/type/findAll.do'
			                    ">
			                 
			         	取号子类: <input id="typeChild" class="easyui-combobox" name="text" 
								style="width: 200px" 
								data-options="valueField:'text',textField: 'text',
 								url:'<%=request.getContextPath()%>/numberType/findAll.do'">       
						<button onclick="reload()" class="btn btn-mini">
							<i class="icon icon-search"></i>查询
						</button>
<!-- 						<button onclick="deleteEntity()" class="btn btn-mini"> -->
<!-- 							<i class="icon icon-repeat"></i> 重置 -->
<!-- 						</button> -->
					</div>
				</div>
				<table id="dg" title="列表"
					class="easyui-datagrid embed-responsive-item  "
					url="<%=request.getContextPath()%>/alotNumber/findEntityInPages.do"
					pagination="true" toolbar="#toolbar_item" rownumbers="true"
					fitColumns="true" singleSelect="true"
					data-options="
		            	">
					<thead >
						<tr>
							<th field="djType" width="80px">登记机构部门</th>
							<th field="type" width="80px">取号类型</th>
							<th field="text" width="80px">取号子类</th>
							<th field="curNumber" width="80px">取号量</th>
							<th field="maxNumber" width="200px">最大号</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>

	</div>
	<div class="easyui-window" id="tt" title=" " closed="true" style="width:1000px;height:400px;">
		
			
				<table id="dg1" title="取号详情列表" class="easyui-datagrid embed-responsive-item" >
					<thead>
						<tr>
							<th field="ajbh" width="100px">案件编号</th>
							<th field="numbers" width="100px">所取号码</th>
							<th field="numberTypeId" width="100px">取号子类</th>
							<th  width="100px" data-options="field:'selfSystem',
					                	formatter:function(val){ 
											if(val){  
												 return val.name;  
											}  
										}"
								
							>自建系统名称</th>
							<th field="time" width="100px">取号时间</th>
						</tr>
					</thead>
				</table>
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
$(function(){
	 $("#dg1").datagrid({
		method:'get',
		url:'',
		fitColumns:true,
		rownumbers:true,
		singleSelect:true
	 });
	 $("#type").combobox({
			onChange:function(){
				var type = $('#type').combobox('getValue');
				$("#typeChild").combobox({
					url:"<%=request.getContextPath()%>/numberType/findAll.do?type="+type+"",
					valueField:'text',
					textField:'text'
				});
			}
		})
}); 
</script>
<script type="text/javascript">
	function reload(){
		var startTime = $("#startTime").datebox("getValue");
		var endTime = $("#endTime").datebox("getValue");
		var type = $("#type").textbox("getValue");
		var text = $("#typeChild").textbox("getValue");
		var djType = $("#djType").combobox("getValue");
	    $("#dg").datagrid('load', {
	    	startTime:startTime,
	    	endTime:endTime,
	    	type:type,
	    	text:text,
	    	operateOrgCode:djType
        });  
	}

// 	function deleteEntity(){
// 		$("#startTime").datebox("setValue","");
// 		$("#endTime").datebox("setValue","");
// 		$("#name").textbox("setValue","");
// 		$("#ajbh").textbox("setValue","");
// 		$("#numberTypeId").textbox("setValue","");
// 	}

	
		
		

	
</script>


</html>