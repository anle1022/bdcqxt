<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>取号类型配置</title>
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/matrix-admin01/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/matrix-admin01/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/easyui/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/easyui/themes/color.css">
<script type="text/javascript"
	src="<%=request.getContextPath() %>/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js_css/common.js"></script>
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
    $(".row-fluid").show();
};  
document.write(html);	
</script>
<body>
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
						案件编号: <input id="ajbh" class="easyui-textbox" name="ajbh" clientidmode="Static"
								style="width: 200px" data-options="">
						自建系统名称: <input id= "name" class="easyui-textbox" name="name" clientidmode="Static"
								style="width: 200px"
								data-options="
			                    valueField: 'id',
			                    textField: 'name',
			                    url:'<%=request.getContextPath()%>/organization/findOrgTreeByOrgCode.do?orgCode=${user.orgCode }'
			                    ">	
			         	 所取编号: <input id="numberTypeId" class="easyui-textbox" name="numberTypeId" 
								style="width: 200px" data-options="">       
						<button onclick="reload()" class="btn btn-mini">
							<i class="icon icon-search"></i>查询
						</button>
						<button onclick="reset()" class="btn btn-mini">
							<i class="icon icon-repeat"></i> 重置
						</button>
					</div>
				</div>
				<table id="dg" title="列表"
					class="easyui-datagrid embed-responsive-item  "
					url="<%=request.getContextPath()%>/alotNumber/findEntityInPage.do"
					pagination="true" toolbar="#toolbar_item" rownumbers="true"
					fitColumns="true" singleSelect="true"
					data-options="
		            	onDblClickRow:function(rowIndex,rowData){loadDetail(rowIndex,rowData)}">
					<thead >
						<tr>
							<th field="name1" width="80px">取号类型</th>
							<th width="100px" data-options="field:'numberType1',
					                	formatter:function(val){ 
											if(val){  
												 return val.text;  
											}  
										}">分区类型</th>
							<th field="numbers" width="100px">所取号码</th>
							<th field="time" width="70px">取号时间</th>
							<th  width="100px" data-options="field:'selfSystem',
					                	formatter:function(val){ 
											if(val){  
												 return val.name;  
											}  
										}"
							>自建系统名称</th>
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
							<th field="numberTypeId" width="100px">分区类型</th>
<!-- 							<th field="selfSystemId" width="100px">自建系统编号</th> -->
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
// 	 $('#tt').tabs();
}); 
</script>
<script type="text/javascript">
	function reload(){
		var startTime = $("#startTime").datebox("getValue");
		var endTime = $("#endTime").datebox("getValue");
		var name = $("#name").textbox("getValue");
		var ajbh = $("#ajbh").textbox("getValue");
		var numberTypeId = $("#numberTypeId").textbox("getValue");
	    $("#dg").datagrid('load', {
	    	ajbh:ajbh,
	    	startTime:startTime,
	    	endTime:endTime,
	    	name:name,
	    	numberTypeId:numberTypeId
        });  
	}
	
	function loadDetail(rowIndex,rowData){
		var startTime = $("#startTime").datebox("getValue");
		var endTime = $("#endTime").datebox("getValue");
		$('#tt').window('open');
		$("#dg1").datagrid({
	    	queryParams:{ajbh:rowData.ajbh,
	    		startTime:rowData.startTime,
	    		endTime:rowData.endTime
// 	    		numbers:rowData.numbers,
// 				numberTypeId:rowData.numberTypeId,
// 				selfSystemId:rowData.selfSystemId,
// 				time:rowData.time,
// 				text:rowData.text,
// 				type:rowData.type
				},
	    	url:"<%=request.getContextPath()%>/alotNumber/findEntityInPage.do?startTime="+startTime+"&endTime="+endTime
	    	
	    });
	}
	function reset(){
		$("#startTime").datebox("setValue","");
		$("#endTime").datebox("setValue","");
		$("#name").textbox("setValue","");
		$("#ajbh").textbox("setValue","");
		$("#numberTypeId").textbox("setValue","");
	}
	
</script>

</html>