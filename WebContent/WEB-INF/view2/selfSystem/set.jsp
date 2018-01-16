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
		<a href="#" class="current">自建系统配置</a>
	</div>
	<div class="row-fluid">
		<div class="span8">
			<div class="widget-box">
				<div id="toolbar_item">
						
					<div>
						系统名称: <input id="sysName" class="easyui-textbox" name="name"
								style="width: 200px" data-options="" >
						<button onclick="reload()" class="btn btn-mini">
							<i class="icon icon-search"></i>查询
						</button>
						<button onclick="deleteEntity()" class="btn btn-mini">
							<i class="icon icon-no"></i> 删除
						</button>
					</div>	
						
				</div>

				<table id="dg" title="列表"
					class="easyui-datagrid embed-responsive-item "
					url="<%=request.getContextPath()%>/selfSystem/findEntityListInPage.do"
					pagination="true" toolbar="#toolbar_item" rownumbers="true"
					fitColumns="true" singleSelect="true"
					data-options="onDblClickRow:function(rowIndex,rowData){modifyRow(rowIndex,rowData)}">
					<thead>
						<tr>
							<th field="name" width="140px">系统名称</th>
							<th field="password" width="90px">系统密码</th>
							<th field="id" width="170px">KEY</th>
							<th width="50px" data-options="field:'status', 
 					                	formatter:function(val){   
 											if(val==1){   
 												 return '有效';   
 											}else{
 												return '无效';
 											}   
 										}">状态</th>
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
						style="margin-bottom: 10px">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-edit" plain="true" onclick="updateEntity()">修改</a>
					</div>
					<form id="updateForm" method="post">
						<div class="fitem">
							<label>KEY</label> <input class="easyui-textbox" name="id"
								readonly="readonly" style="width: 250px"
								data-options="required:true">
						</div>
						<div class="fitem">
							<label>系统名称</label> <input class="easyui-textbox" name="name"
								style="width: 250px" data-options="required:true">
						</div>
						
						<div class="fitem">
							<label>系统密码</label> <input class="easyui-textbox" name="password"
								style="width: 250px" data-options="required:true">
						</div>
						<div class="fitem">
							<label>状态类型</label> <input class="easyui-combobox" name="status"
								style="width: 250px" data-options="valueField:'status',textField:'statusValue',data:[{status:'1',statusValue:'有效'},{status:'0',statusValue:'无效'}],required:true">
						</div>
					</form>
				</div>
				<div class="easyui-panel" iconCls="icon-plugin_add" title="添加"
					style="width: 100%; height: auto;">
					<div class="easyui-panel" id="toolbar_add"
						style="margin-bottom: 20px">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-add" plain="true" onclick="addEntity()">添加</a>
					</div>
					<form id="addForm" method="post">

						<div class="fitem">
							<label>系统名称</label> <input class="easyui-textbox" name="name"
								style="width: 250px;" data-options="required:true">
						</div>
						
						<div class="fitem">
							<label>系统密码</label> <input class="easyui-textbox" name="password"
								style="width: 250px" data-options="required:true"
								>
<!-- 								onkeyup="value=value.replace([0-1]{1})" -->
						</div>
						<div class="fitem">
							<label>状态类型</label> <input class="easyui-combobox" name="status"
								style="width: 250px" data-options="valueField:'status',textField:'statusValue',data:[{status:'1',statusValue:'有效'},{status:'0',statusValue:'无效'}],required:true">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
</body>

<script
	src="<%=request.getContextPath()%>/matrix-admin01/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/easyui/datagrid-groupview.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/easyui/easyui.extends.validate.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/easyui/easyui-lang-zh_CN.js"></script>
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
	function deleteEntity(){
		var row = $("#dg").datagrid('getSelected');
		if(row){
			$.messager.confirm("提示","确定删除选中数据？", function (r) {  
		        if(r){
		        	$.ajax({
		        		url:"<%=request.getContextPath()%>/selfSystem/deleteEntity.do",
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
	function updateEntity(){
		
		$("#updateForm").form('submit', {
            url: '<%=request.getContextPath()%>/selfSystem/updateEntity.do',
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
	function addEntity(){
		$("#addForm").form('submit', {
            url: '<%=request.getContextPath()%>/selfSystem/addEntity.do',
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
                /* if (data == "ok") {
                    $.messager.alert("操作提示", "操作成功！", "info");
                    $("#dg").datagrid('reload');
                } else {
                    $.messager.alert("操作提示", "操作失败，请联技术人员！", "info");
                } */
            	 $.messager.alert("操作提示", data, "info");
                 //$('#addForm').form('clear');
                 $("#dg").datagrid('reload');
            },
            error:function(data){
            	$.messager.alert("操作提示", "操作失败，请联技术人员！", "error");
            }
        }); 
	}
	
	function modifyRow(rowIndex,rowData){
		$("#updateForm").form('load', {
			id:rowData.id,
			name:rowData.name,
			password:rowData.password,
			status:rowData.status,
			numberTypes:rowData.numberTypes,
			orgCode:rowData.orgCode
		});
	}
	function validateEntity(type){
		var id = $("#addForm input[name=id]").val();
		var text = $("#addForm input[name=text]").val();
		
		var data ;
		if(type == 'id'){
			if(!id){
				return;
			}
			var data = {id:id};
		}else{
			if(!text){
				return;
			}
			var data = {text:text};
		}
		$.ajax({
			url:"<%=request.getContextPath()%>/selfSystem/validateEntity.do",
			type:"post",
			data:data,
			success:function(data){
				if(data>0){
					if(type == 'id'){
						$.messager.alert("操作提示", "关键字已存在！请重新输入。", "info");
						$("#add_id").textbox("setValue","");
						$("input",$("#add_id").next("span")).focus()
					}else{
						$.messager.alert("操作提示", "取号类型已存在！请重新输入。", "info");
						$("#add_text").textbox("setValue","");
					}
				}
			},
			error:function(data){
				$.messager.alert("操作提示", "操作失败，请联技术人员！", "error");
			}
		})
	}
	function reload(){
		var name = $("#sysName").textbox('getValue');
		$("#dg").datagrid('load', {
	    	name:name
        });  
	}
</script>
</html>