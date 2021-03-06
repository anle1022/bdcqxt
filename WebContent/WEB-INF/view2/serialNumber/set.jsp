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
</style>
</head>

<body>
<div id="content" class="container">
	<div id="breadcrumb">
		<a href="#" title="配置管理" class="tip-bottom"><i class="icon-cog"></i> 配置管理</a>
		<a href="#" class="current">流水号配置</a>
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
						
						分区类型：<input id="getType" class="easyui-combobox" name="numberTypeId"
								style="width: 200px"
								data-options="valueField:'text',textField: 'text',
 								url:'<%=request.getContextPath()%>/numberType/findAll.do'
 								"> 
 								
						年份：<input class="easyui-numberspinner" name="year"
								id="add_year" data-options="min:1970,max:2999"
								style="width: 200px">
						<button onclick="reload()" class="btn btn-mini">
							<i class="icon icon-search"></i>查询
						</button>
						<button onclick="findYear()" class="btn btn-mini">
							<i class="icon icon-search"></i>复制上年度
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
					url="<%=request.getContextPath()%>/serialNumber/findEntityListInPage.do"
					pagination="true" toolbar="#toolbar_item" rownumbers="true"
					fitColumns="true" singleSelect="true"
					data-options="
		            	view:groupview,
		                groupField:'year',
		                groupFormatter:function(value,rows){
		                    return value + '年  ： ' + rows.length + '条';
		                },onDblClickRow:function(rowIndex,rowData){modifyRow(rowIndex,rowData)}">
					<thead>
						<tr>
							<!-- <th field="id" width="210px">序号</th> -->
							<th field="year" width="50px">年份</th>
							<th data-options="field:'numberType',
					                	formatter:function(val){  
											if(val){  
												 return val.text;  
											}  
										}"
								width="100px">分区类型</th>
							<th field="minNumber" width="80px">初始值</th>
							<th field="curNumber" width="80px">当前值</th>
							<th field="maxNumber" width="80px">最大值</th>
							<th width="150px" data-options="field:'selfSystem', 
    					                	formatter:function(val){      
    												if(val){    
  												 return val.name;    
  											}   
   										}"  
							>系统名称</th>

						</tr>
					</thead>
				</table>
			</div>
		</div>
		<div class="span4">
			<div class="widget-box">
				<div class="easyui-panel" iconCls="icon-plugin_edit" title="修改"
					style="width: 100%; height: auto;">
					<div class="easyui-panel" id="toolbar_edit"
						style="margin-bottom: 3px">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-edit" plain="true" onclick="updateSerialNumber()">修改</a>
					</div>
					<form id="updateForm" method="post">
						<div class="fitem">
							<label>ID</label> <input class="easyui-textbox" name="pkid" 
								readonly="readonly" style="width: 200px" 
								data-options="required:true">
						</div>
						<div class="fitem">
							<label>序号</label> <input class="easyui-textbox" name="id"
								readonly="readonly" style="width: 200px"
								data-options="required:true">
						</div>
						<div class="fitem">
							<label>年份</label> <input class="easyui-numberspinner" name="year"
								readonly="readonly"
								data-options="min:1970,max:2999,required:true"
								style="width: 200px">
						</div>
						<div class="fitem">
							<label>分区类型</label> <input class="easyui-combobox" name="numberTypeId" readonly="readonly"
								style="width: 200px"
								data-options="required:true,valueField:'id',textField: 'text',url:'<%=request.getContextPath()%>/numberType/findAll.do'">
						</div>
						<div class="fitem">
							<label>系统</label> <input class="easyui-combobox" name="selfSystemId" 
								style="width: 200px"
								data-options="valueField:'id',textField: 'name',url:'<%=request.getContextPath()%>/selfSystem/findAll.do'">
						</div>
						<div class="fitem">
							<label>初始值</label> <input class="easyui-textbox" name="minNumber"
								style="width: 200px" data-options="required:true">
						</div>
						<div class="fitem">
							<label>当前值</label> <input class="easyui-textbox" name="curNumber"
								style="width: 200px" data-options="required:true">
						</div>
						<div class="fitem">
							<label>最大值</label> <input class="easyui-textbox" name="maxNumber"
								style="width: 200px" data-options="required:true">
						</div>
					</form>
				</div>
				<div class="easyui-panel" iconCls="icon-plugin_add" title="添加"
					style="width: 100%; height: auto;">
					<div class="easyui-panel" id="toolbar_add"
						style="margin-bottom: 3px">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-add" plain="true" onclick="addSerialNumber()">添加</a>
					</div>
					<form id="addForm" method="post">
						<div class="fitem">
							<label>年份</label> <input class="easyui-numberspinner" name="year"
								id="add_year1" data-options="min:1970,max:2999"
								style="width: 200px">
						</div>

						<div class="fitem">
							<label>分区类型</label>
							<!-- <input class="easyui-textbox" name="type" style="width:200px" data-options="required:true">
			            	 -->
							<input class="easyui-combobox" name="numberTypeId" 
								style="width: 200px"
								data-options="required:true,valueField:'id',textField: 'text',url:'<%=request.getContextPath()%>/numberType/findAll.do'">
						</div>
						<div class="fitem">
							<label>系统</label> <input class="easyui-combobox" name="selfSystemId"
								style="width: 200px"
								data-options="valueField:'id',textField: 'name',url:'<%=request.getContextPath()%>/selfSystem/findAll.do'">
						</div>
						<div class="fitem">
							<label>初始值</label> <input class="easyui-textbox" name="minNumber"
								style="width: 200px"
								data-options="required:true,validType:'Number'">
						</div>
						<div class="fitem">
							<label>当前值</label> <input class="easyui-textbox" name="curNumber"
								style="width: 200px"
								data-options="required:true,validType:'Number'">
						</div>
						<div class="fitem">
							<label>最大值</label> <input class="easyui-textbox" name="maxNumber"
								style="width: 200px"
								data-options="required:true,validType:'Number'">
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
	$("#add_year1").numberbox("setValue",date.getFullYear());
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
	function deleteSerialNumber(){
		var row = $("#dg").datagrid('getSelected');
		if(row){
			$.messager.confirm("提示","确定删除选中数据？", function (r) {  
		        if(r){
		        	$.ajax({
		        		url:"<%=request.getContextPath()%>/serialNumber/deleteEntity.do",
		        		contentType:"html/text",
		        		data:{pkid:row.pkid},
		        		success:function(data){
		        			if (data == "ok") {
		                        $.messager.alert("操作提示", "操作成功！", "info");
		                        $('#updateForm').form('clear');
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
	function updateSerialNumber(){
		$("#updateForm").form('submit', {
            url: '<%=request.getContextPath()%>/serialNumber/updateEntity.do',
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
//             	alert(data);
                if (data == "ok") {
                    $.messager.alert("操作提示", "操作成功！", "info");
                    $('#updateForm').form('clear');
                    $("#dg").datagrid('reload');
                } else {
                    $.messager.alert("操作提示", "操作失败，请联技术人员！", "info");
                }
                
            },
            error:function(data){
            	$.messager.alert("操作提示", "操作失败，请联技术人员！", "error");
            }
        }); 
	}
	function addSerialNumber(){
		$("#addForm").form('submit', {
            url: '<%=request.getContextPath()%>/serialNumber/addEntity.do',
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
	
	function modifyRow(rowIndex,rowData){
		$("#updateForm").form('load', {
			pkid:rowData.pkid,
			id:rowData.id,
			year:rowData.year,
			numberTypeId:rowData.numberTypeId,
			minNumber:rowData.minNumber,
			curNumber:rowData.curNumber,
			maxNumber:rowData.maxNumber,
// 			selfSystemId:rowData.selfSystem.name
			selfSystemId:rowData.name
		});
	}
	
	function reload(){
		var text = $("#getType").combobox("getValue");
		var year = $("#add_year").numberspinner("getValue");
		$("#dg").datagrid('load', {
	    	
			text:text,
	    	year:year
        });  
	}
	
	function findYear(){
		var year = $("#add_year").numberspinner("getValue");
		$.ajax({
			data:{year:year},
			url: '<%=request.getContextPath()%>/serialNumYear/year.do'
		})
	}
</script>
</html>