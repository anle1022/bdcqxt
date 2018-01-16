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
					开始日期: <input id="startTime" value="${startTime }" width="80" name="time" style="width: 120px"
						class="easyui-datebox">
					结束日期: <input id="endTime" value="${endTime}"  width="80" name="time" style="width: 120px"
						class="easyui-datebox">	
					登记字号: <input id="djzh" width="80" name="djzh" style="width: 220px"
						class="easyui-textbox">
					</div>		
					<div class="datagrid-btn-separator">
						案件编号: <input class="easyui-textbox" id="ajbh" name="ajbh"
							style="width: 180px;">
					</div>

					<div class="datagrid-btn-separator">
						地区:<input class="easyui-combobox" value="${orgCode }" id="orgCode" name="orgCode"
							style="width: 100px"
							data-options="
			                    valueField: 'orgCode',
			                    textField: 'orgName',
			                    url:'<%=request.getContextPath()%>/organization/findOrgTreeByOrgCode.do?orgCode=${user.orgCode }'
							">
					</div>

					<div class="datagrid-btn-separator">
						环节:<input class="easyui-combobox" value="" id="processNode"
							name="processNode" style="width: 100px"
							data-options="
			                     valueField: 'key',
			                    textField: 'key',
			                   	url:'<%=request.getContextPath()%>/processNode/findEntityList.do'
			                    ">
					</div>
					<div>
					登记类型：<input id="oprid" class="easyui-combotree" value="${oprid}" name="oprid"
					data-options="valueField:'id',textField:'text',
					url:'<%=request.getContextPath() %>/registType/findTypeByPid.do',method:'get'" style="width:28%">
					
					<button onclick="reload()" class="btn btn-mini">
						<i class="icon icon-search"></i>查询
					</button>
					</div>
				</div>
				<table id="dg" title="列表"
					class="easyui-datagrid embed-responsive-item  "
					url="<%=request.getContextPath()%>/case/findTheLatestData.do?startTime=${startTime}&endTime=${endTime}&processNode=${processNode}&caseOrgCode=${orgCode}&djlx=${djlx}"
					pagination="true" toolbar="#toolbar_item" rownumbers="true"
					fitColumns="true" singleSelect="true"
					data-options="
		            	onDblClickRow:function(rowIndex,rowData){loadDetail(rowIndex,rowData)}">
					<thead >
						<tr>
							<!-- <th field="id" width="50px">年份</th> -->
							<th field="ajbh" width="50px">案件编号</th>
							<th field="djlx" width="50px">登记类型</th>
							<th field="djzh" width="50px">登记字号</th>
							<th field="operator" width="50px">业务办理人</th>
							<th field="operateTime" width="50px">办理时间</th>
							<th width="50px" data-options="field:'organization1',
					                	formatter:function(val){ 
											if(val){  
												 return val.orgName;  
											}  
										}"
								
							>案件所属地区</th>
							<th width="50px" data-options="field:'organization2',
 					                	formatter:function(val){  
 											if(val){   
 												 return val.orgName;   
 											}   
 										}" 
							>案件办理地区</th>
							<th field="processNode" width="50px">已办环节</th>

						</tr>
					</thead>
				</table>
			</div>
		</div>

	</div>
	
	<div class="easyui-window " id="w" title="案件详情 "  closed="true" style="width:1100px;height:400px;">
		<div id="tt" title="案件详情 " fit="true" >
		<div title="案件流程信息" style="padding:1px;"  fit="true">			
			<table id="dg1" title=" " class="easyui-datagrid embed-responsive-item"  fit="true" style="margin: 0 0">
				<thead>
						<tr>
							<th field="ajbh" width="70px">案件编号</th>
							<th field="djlx" width="70px">登记类型</th>
							<th field="djzh" width="70px">登记字号</th>
							<th field="operator" width="50px">业务办理人</th>
							<th field="operateTime" width="50px">办理时间</th>
							<th  
							  width="30px" data-options="field:'organization1',
					                	formatter:function(val){ 
											if(val){  
												 return val.orgName;  
											}  
										}"
							>案件所属地区</th>
							<th  
							  width="30px" data-options="field:'organization2',
 					                	formatter:function(val){  
 											if(val){   
 												 return val.orgName;   
 											}   
 										}" 
							>案件办理地区</th>
							<th field="processNode" width="30px">已办环节</th>

						</tr>
					</thead>
				</table>
		</div>
		<div title="案件信息" style="padding:20px;" cache="false" href="tabs_href_test.html"  fit="true">
			This is Tab2 with close button.
		</div>
		<div title="附件" style="padding:20px;"  fit="true">
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
	 $('#tt').tabs();
}); 
</script>
<script type="text/javascript">
	function reload(){
		var startTime = $("#startTime").datebox("getValue");
		var endTime = $("#endTime").datebox("getValue");
		var processNode = $("#processNode").combobox("getValue");
		var caseOrgCode = $("#orgCode").combobox("getValue");
		var ajbh = $("#ajbh").textbox("getValue");
		var djzh = $("#djzh").textbox("getValue");
		var oprid = $('#oprid').combotree('getValue');
	    $("#dg").datagrid({
	    	queryParams:{
		    	startTime:startTime,
		    	endTime:endTime,
		    	processNode:processNode,
		    	caseOrgCode:caseOrgCode,
		    	ajbh:ajbh,
		    	djzh:djzh,
		    	oprid:oprid
        	},
        	url:"<%=request.getContextPath()%>/case/findTheLatestData.do"
	    });  
	}
	
	function loadDetail(rowIndex,rowData){
		$('#w').window('open');
	    $("#dg1").datagrid({
	    	queryParams:{"ajbh":rowData.ajbh},
	    	url:"<%=request.getContextPath()%>/case/findEntityList.do"
	    });
	    
	}
</script>

</html>