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
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/easyui/default/easyui.css"> 	
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
};  
document.write(html);	
</script>
<body>
	<div class="row-fluid">
		<div class="span8">
			<div class="widget-box">
				<div id="toolbar_item">
<!-- 					<input class="easyui-searchbox" -->
<!-- 						data-options="prompt:'Please Input Value',menu:'#mm'" -->
<!-- 						style="width: 50%"> <a href="javascript:void(0)" -->
<!-- 						class="easyui-linkbutton" iconCls="icon-no" plain="true" -->
<!-- 						onclick="deleteEntity()">删除</a> -->
						
					<div>
						系统名称: <input id="sysName" class="easyui-textbox" name="name"
								style="width: 200px" data-options="">
						所属地区: <input id= "orgCode" class="easyui-combobox" name="orgCode"
								style="width: 200px"
								data-options="
			                    valueField: 'orgCode',
			                    textField: 'orgName',
			                    url:'<%=request.getContextPath()%>/organization/findOrgTreeByOrgCode.do?orgCode=${user.orgCode }'
			                    ">	
						<button onclick="reload()" class="btn btn-mini">
							<i class="icon icon-search"></i>查询
						</button>
						<button onclick="deleteEntity()" class="btn btn-mini">
							<i class="icon icon-no"></i> 删除
						</button>
					</div>	
						
				</div>
<!-- 				<div id="mm"> -->
<!-- 					<div data-options="name:'key',iconCls:'icon-ok'">系统名称</div> -->
<!-- 					<div data-options="name:'orgCode'">所属地区</div> -->
<!-- 				</div> -->
				
				

				<table id="dg" title="列表"
					class="easyui-datagrid embed-responsive-item "
					url="<%=request.getContextPath()%>/selfSystem/findEntityListInPage.do"
					pagination="true" toolbar="#toolbar_item" rownumbers="true"
					fitColumns="true" singleSelect="true"
					data-options="onDblClickRow:function(rowIndex,rowData){modifyRow(rowIndex,rowData)}">
					<thead>
						<tr>
							<th field="name" width="100px">系统名称</th>
							<th field="password" width="150px">系统序号</th>
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
							<label>序号</label> <input class="easyui-textbox" name="id"
								readonly="readonly" style="width: 250px"
								data-options="required:true">
						</div>
						<div class="fitem">
							<label>系统名称</label> <input class="easyui-textbox" name="name"
								style="width: 250px" data-options="required:true">
						</div>
						
						<div class="fitem">
							<label>系统序号</label> <input class="easyui-textbox" name="password"
								style="width: 250px" data-options="required:true">
						</div>
<!-- 						<div class="fitem"> -->
<!-- 							<label>所属地区</label> <input id= "orgCode" class="easyui-combobox" name="orgCode" readonly="readonly"  -->
<!-- 								style="width: 250px" -->
<%-- 								data-options=" --%>
<!-- 								required:true, -->
<!-- 			                    valueField: 'orgCode', -->
<!-- 			                    textField: 'orgName', -->
<%-- 			                    url:'<%=request.getContextPath()%>/organization/findOrgTreeByOrgCode.do?orgCode=${user.orgCode }' --%>
<%-- 			                    "> --%>
<!-- 						</div> -->
						<div class="fitem">
							<label>状态类型</label> <input class="easyui-textbox" name="status"
								style="width: 250px" data-options="required:true">
						</div>
						
<!-- 						<div class="fitem"> -->
<!-- 							<label>可选取号类型</label> <input class="easyui-combobox" -->
<!-- 								name="numberTypes" style="width: 200px; height: 40px;" -->
<%-- 								data-options=" --%>
<!-- 			                    valueField: 'id', -->
<!-- 			                    textField: 'text', -->
<!-- 			                    multiple:true, -->
<!-- 			               		multiline:true, -->
<%-- 			                    url:'<%=request.getContextPath()%>/numberType/findAll.do', --%>
<!-- 			                    required:true -->
<%-- 			                    "> --%>
<!-- 						</div> -->
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
							<label>系统序号</label> <input class="easyui-textbox" name="password"
								style="width: 250px" data-options="required:true"
								>
<!-- 								onkeyup="value=value.replace([0-1]{1})" -->
						</div>
						<div class="fitem">
							<label>所属地区</label> <input class="easyui-combobox" name="orgCode"
								style="width: 250px"
								data-options="
 			                    valueField: 'orgCode', 
  			                    textField: 'orgName',  
 			                    url:'<%=request.getContextPath()%>/organization/findOrgTreeByOrgCode.do?orgCode=${user.orgCode }', 
  			                    required:true  
 			                    "> 
 						</div> 
						<div class="fitem">
							<label>状态类型</label> <input class="easyui-textbox" name="status"
								style="width: 250px" data-options="required:true">
						</div>
						
						
<!-- 						<div class="fitem"> -->
<!-- 							<label>可选取号类型</label> <input class="easyui-combobox" -->
<!-- 								name="numberTypes" style="width: 200px; height: 40px;" -->
<%-- 								data-options=" --%>
<!-- 			                    valueField: 'id', -->
<!-- 			                    textField: 'text', -->
<!-- 			                    multiple:true, -->
<!-- 			                    multiline:true, -->
<%-- 			                    url:'<%=request.getContextPath()%>/numberType/findAll.do', --%>
<!-- 			                    required:true -->
<%-- 			                    "> --%>
<!-- 						</div> -->
					</form>
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
		var name = $("#sysName").val();
		var orgCode = $("#orgCode").combobox("getValue");
		$("#dg").datagrid('load', {
	    	
	    	name:name,
	    	orgCode:orgCode
        });  
	}
</script>
</html>