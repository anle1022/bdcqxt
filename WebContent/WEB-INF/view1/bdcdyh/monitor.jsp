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
						登记字号: <input id= "djzh" class="easyui-textbox" name="djzh" clientidmode="Static"
								style="width: 200px">	
			         	不动产单元号: <input id="bdcdyh" class="easyui-textbox" name="bdcdyh" 
								style="width: 200px" data-options="">
						是否存在: <input id="exist" class="easyui-combobox" name="exist" 
								style="width: 100px" data-options=" 
								valueField: 'exist',
			                    textField: 'text',data:[{'exist':'','text':'全部'},{'exist':'1','text':'存在'},{'exist':'0','text':'不存在'}]">         
						<button onclick="reload()" class="btn btn-mini">
							<i class="icon icon-search"></i>查询
						</button>
						<button onclick="reset()" class="btn btn-mini">
							<i class="icon icon-repeat"></i> 重置
						</button>
						<button onclick="exportExcel()" class="btn btn-success btn-mini">
							<i class="icon-download-alt icon-white"></i>导出
						</button>
					</div>
				</div>
				<table id="dg" title="列表"
					class="easyui-datagrid embed-responsive-item  "
					url="<%=request.getContextPath()%>/bdcdyh/findEntityListInPage.do"
					pagination="true" toolbar="#toolbar_item" rownumbers="true"
					fitColumns="true" singleSelect="true"
					data-options=" onDblClickRow:function(rowIndex,rowData){loadDetail(rowIndex,rowData)}">
					<thead >
						<tr>
							<th field="ajbh" width="100px">案件编号</th>
							<th field="djzh" width="100px">登记字号</th>
							<th field="bdcdyh" width="130px">不动产单元号</th>
							<th  width="70px" data-options="field:'exist',
					                	formatter:function(val){ 
											if(val==1){  
												 return '存在';  
											}else{
												return '不存在';
											}  
										}"
								
							>是否存在</th>
							<th field="time" width="100px">验证时间</th>
						</tr>
					</thead>
				</table>
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
		var djzh = $("#djzh").textbox("getValue");
		var ajbh = $("#ajbh").textbox("getValue");
		var bdcdyh = $("#bdcdyh").textbox("getValue");
		var exist = $("#exist").textbox("getValue");
	    $("#dg").datagrid('load', {
	    	ajbh:ajbh,
	    	startTime:startTime,
	    	endTime:endTime,
	    	djzh:djzh,
	    	bdcdyh:bdcdyh,
	    	exist:exist
        });  
	}
	
	function reset(){
		$("#startTime").datebox("setValue","");
		$("#endTime").datebox("setValue","");
		$("#djzh").textbox("setValue","");
		$("#ajbh").textbox("setValue","");
		$("#bdcdyh").textbox("setValue","");
	}
	function exportExcel(){
		var startTime = $("#startTime").datebox("getValue");
		var endTime = $("#endTime").datebox("getValue");
		var djzh = $("#djzh").textbox("getValue");
		var ajbh = $("#ajbh").textbox("getValue");
		var bdcdyh = $("#bdcdyh").textbox("getValue");
		var exist = $("#exist").textbox("getValue");
		window.open("<%=request.getContextPath()%>/bdcdyh/exportExcels.do?startTime="+startTime+"&endTime="+endTime+"&djzh="+djzh+"&ajbh="+ajbh+"&bdcdyh="+bdcdyh+"&exist="+exist,"_self");
	}
</script>

</html>