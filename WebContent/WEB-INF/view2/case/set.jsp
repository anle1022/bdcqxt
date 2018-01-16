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

</style>
</head>

<body>
<div id="content" class="container">
	<div id="breadcrumb">
		<a href="#" title="配置管理" class="tip-bottom"><i class="icon-cog"></i> 配置管理</a>
		<a href="#" class="current">流程监控节点配置</a>
	</div>
	<!-- <div  id="center" data-options="region:'center'"  style="width:800px;height:100%;overflow:hidden" iconCls="icon-color_swatch"   > -->
	<div class="row-fluid">
		<div class="span8">
			<div class="widget-box">
				<div id="toolbar_item">
<!-- 					<input class="easyui-searchbox" -->
<!-- 						data-options="prompt:'Please Input Value',menu:'#mm',searcher:doSearch" -->
<!-- 						style="width: 50%"> <a href="javascript:void(0)" -->
<!-- 						class="easyui-linkbutton" iconCls="icon-no" plain="true" -->
<!-- 						onclick="deleteSerialNumber()">删除</a> -->

						节点关键字：<input id="pointId" class="easyui-textbox" name="key"
								style="width: 100px" data-options="">
						节点名称：<input id="pointName" class="easyui-textbox" name="name"
								style="width: 100px" data-options="">
						节点排序：<input id="pointSort" class="easyui-textbox" name="orderBy"
								style="width: 100px" data-options="">
						<button onclick="reload()" class="btn btn-mini">
							<i class="icon icon-search"></i>查询
						</button>
						
						<button onclick="deleteSerialNumber()" class="btn btn-mini">
							<i class="icon icon-no"></i> 删除
						</button>	
				</div>
				
				
<!-- 				<div id="mm"> -->
<!-- 					<div data-options="name:'type'">取号类型</div> -->
<!-- 					<div data-options="name:'year'">年份</div> -->
<!-- 				</div> -->
				<table id="dg" title="列表"
					class="easyui-datagrid embed-responsive-item  "
					url="<%=request.getContextPath()%>/processNode/findEntityListOrder.do"
					toolbar="#toolbar_item" rownumbers="true" fitColumns="true"
					singleSelect="true"
					data-options="onDblClickRow:function(rowIndex,rowData){modifyRow(rowIndex,rowData)}">
					<thead>
						<tr>
							<!-- <th field="id" width="210px">序号</th> -->
<!-- 							<th field="id" width="50px">序号</th> -->
							<th field="key" width="100px">关键字</th>
							<th field="name" width="100px">节点名称</th>
							<th field="orderBy" width="100px">节点顺序</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<div class="span4">
			<div class="widget-box">
				<div class="easyui-panel" iconCls="icon-plugin_add" title="修改"
					style="width: 100%; height: auto;">
					<div class="easyui-panel" id="toolbar_add"
						style="margin-bottom: 3px">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-edit" plain="true" onclick="updateEntity()">修改</a>
					</div>
					<form id="updateForm" method="post">
						<div class="fitem">
							<label>序号</label> <input class="easyui-textbox" name="id"
								readonly="readonly" style="width: 200px"
								data-options="required:true">
						</div>
						<div class="fitem">
							<label>节点关键字</label> <input class="easyui-textbox" name="key"
								style="width: 200px" data-options="required:true">
						</div>
						<div class="fitem">
							<label>节点名称</label> <input class="easyui-textbox" name="name"
								style="width: 200px" data-options="required:true">
						</div>
						<div class="fitem">
							<label>节点排序</label> <input class="easyui-textbox" name="orderBy"
								style="width: 200px" data-options="required:true">
						</div>
					</form>
				</div>
				<div class="easyui-panel" iconCls="icon-plugin_add" title="添加"
					style="width: 100%; height: auto;">
					<div class="easyui-panel" id="toolbar_add"
						style="margin-bottom: 3px">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-add" plain="true" onclick="addEntity()">添加</a>
					</div>
					<form id="addForm" method="post">

						<div class="fitem">
							<label>节点关键字</label> <input class="easyui-textbox" name="key"
								style="width: 200px" data-options="required:true">
						</div>
						<div class="fitem">
							<label>节点名称</label> <input class="easyui-textbox" name="name"
								style="width: 200px" data-options="required:true">
						</div>
						<div class="fitem">
							<label>节点排序</label> <input class="easyui-textbox" name="orderBy"
								style="width: 200px" data-options="required:true">
						</div>
					</form>
				</div>
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
$(function(){
	var date = new Date();
	$("#add_year").numberbox("setValue",date.getFullYear());
}); 
</script>
<script type="text/javascript">
	function doSearch(value,name){
		var type;
		var year;
		if(name=="type"){
			type = value;
		}else{
			year = value;
		}
	    $("#dg").datagrid('load', {
            type:type,
            year:year
        });  
	}
	
	function addEntity(){
		$("#addForm").form('submit', {
            url: '<%=request.getContextPath()%>/processNode/addEntity.do',
            contentType:"text/html",
            onSubmit: function () {
                //对form表单进行验证，true才会继续执行
                if ($('#addForm').form('validate')){
                	
                	return true;
                }
                else
                    return false;
            },
            success: function (data) {
            	$.messager.alert("操作提示", data, "info");
            	 $("#dg").datagrid('reload');
            },
            error:function(data){
            	$.messager.alert("操作提示", "操作失败，请联技术人员！", "error");
            }
        }); 
	}
	function updateEntity(){
		$("#updateForm").form('submit', {
            url: '<%=request.getContextPath()%>/processNode/updateEntity.do',
            contentType:"text/html",
            onSubmit: function () {
                //对form表单进行验证，true才会继续执行
                if ($('#updateForm').form('validate')){
                	
                	return true;
                }
                else
                    return false;
            },
            success: function (data) {
            	 $.messager.alert("操作提示", data, "info");
            	 $("#dg").datagrid('reload');
            },
            error:function(data){
            	$.messager.alert("操作提示", "操作失败，请联技术人员！", "error");
            }
        }); 
	}
	function deleteSerialNumber(){
		var row = $("#dg").datagrid('getSelected');
		if(row){
			$.messager.confirm("提示","确定删除选中数据？", function (r) {  
		        if(r){
		        	$.ajax({
		        		url:"<%=request.getContextPath()%>/processNode/deleteEntity.do",
		        		contentType:"html/text",
		        		data:{id:row.id},
		        		success:function(data){
		        			if (data == "ok") {
		                        $.messager.alert("操作提示", data, "info");
		                        $("#dg").datagrid('reload');
		                    } else {
		                        $.messager.alert("操作提示", "操作失败，请联技术人员！", "info");
		                    }
		        		},
		        		error:function(data){
		        			$.messager.alert("操作提示", "系统内部错误，请联技术人员！", "info");
		        		}
		        	});
		        }  
		    });  
		}else{
			$.messager.alert("操作提示", "请先选择一条数据！", "info");
		}
	}
	function modifyRow(rowIndex,rowData){
		$("#updateForm").form('load', {
			id:rowData.id,
			key:rowData.key,
			name:rowData.name,
			orderBy:rowData.orderBy
		});
	}
	
	function reload(){
		var key = $("#pointId").textbox("getValue");
		var name = $("#pointName").textbox("getValue");
		var orderBy = $("#pointSort").textbox("getValue");
		
		$("#dg").datagrid('load', {
	    	
			key:key,
			name:name,
			orderBy:orderBy
        });  
	}
</script>
</html>