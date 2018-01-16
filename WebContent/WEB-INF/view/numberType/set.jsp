<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>取号类型配置</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/easyui/themes/color.css">
<script type="text/javascript"
	src="<%=request.getContextPath() %>/easyui/jquery.min.js"></script>
<style type="text/css">
body {
	border: 0px;
}

.fitem {
	margin-left: 10px;
	margin-bottom: 10px;
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
<body class="easyui-layout">
	<div id="center" data-options="region:'center'"
		style="width: 50%; height: 98%" iconCls="icon-color_swatch">
		<div id="toolbar_item">
			<input class="easyui-searchbox"
				data-options="prompt:'Please Input Value',menu:'#mm'"
				style="width: 50%"> <a href="javascript:void(0)"
				class="easyui-linkbutton" iconCls="icon-no" plain="true"
				onclick="deleteEntity()">删除</a>
		</div>
		<div id="mm">
			<div data-options="name:'key',iconCls:'icon-ok'">关键词</div>
			<div data-options="name:'type'">类型名称</div>
		</div>
		<table id="dg" title="列表" class="easyui-datagrid" style="height: 89%"
			url="<%=request.getContextPath()%>/numberType/findEntityListInPage.do"
			pagination="true" toolbar="#toolbar_item" rownumbers="true"
			fitColumns="true" singleSelect="true"
			data-options="onDblClickRow:function(rowIndex,rowData){modifyRow(rowIndex,rowData)}">
			<thead>
				<tr>
					<th field="id" width="50px">关键词</th>
					<th field="text" width="100px">类型名称</th>
					<th field="numberLength" width="50px">流水号长度</th>
					<th field="rule" width="300px">取号规则</th>
				</tr>
			</thead>
		</table>

	</div>

	<div data-options="region:'east'"
		style="width: 49%; height: 98%; margin-left: 3px; margin-bottom: 0px;">

		<div class="easyui-panel" iconCls="icon-plugin_edit" title="修改"
			style="width: 100%; height: 47%;">
			<div class="easyui-panel" id="toolbar_edit"
				style="margin-bottom: 10px">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true" onclick="updateEntity()">修改</a>
			</div>
			<form id="updateForm" method="post">
				<div class="fitem">
					<label>关键词</label> <input class="easyui-textbox" name="id"
						style="width: 200px" data-options="required:true">
				</div>
				<div class="fitem">
					<label>类型名称</label> <input class="easyui-textbox" name="text"
						style="width: 200px" data-options="required:true">
				</div>
				<div class="fitem">
					<label>取号规则</label> <input class="easyui-textbox" name="rule"
						style="width: 200px" data-options="required:true">
				</div>
				<div class="fitem">
					<label>流水号长度</label> <input class="easyui-textbox"
						name="numberLength" style="width: 200px"
						data-options="required:true,validType:'Number'">
				</div>
				<div class="fitem">
					<p>取号规则（注）：</p>
					<p>目前只能解析year(年),month(月),day(日),number(流水号)</p>
					<p>如：不动产权证号规则写法 --- 粤（year）广州市不动产权第number号</p>
				</div>

			</form>
		</div>
		<div class="easyui-panel" iconCls="icon-plugin_add" title="添加"
			style="width: 100%; height: 45%;">
			<div class="easyui-panel" id="toolbar_add"
				style="margin-bottom: 20px">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-add" plain="true" onclick="addEntity()">添加</a>
			</div>
			<form id="addForm" method="post">
				<div class="fitem">
					<label>关键词</label> <input class="easyui-textbox" id="add_id"
						name="id" style="width: 200px" data-options="required:true">
				</div>
				<div class="fitem">
					<label>类型名称</label> <input class="easyui-textbox" id="add_text"
						name="text" style="width: 200px" data-options="required:true">
				</div>
				<div class="fitem">
					<label>流水号长度</label> <input class="easyui-textbox"
						name="numberLength" style="width: 200px"
						data-options="required:true,validType:'Number'">
				</div>
				<div class="fitem">
					<label>取号规则</label> <input class="easyui-textbox" name="rule"
						style="width: 200px" data-options="required:true">
				</div>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/easyui/datagrid-groupview.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
$(function(){
	var date = new Date();
	$("#add_year").numberbox("setValue",date.getFullYear());
	$("input",$("#add_id").next("span")).blur(function(){
		validateEntity("id");
	})
	$("input",$("#add_text").next("span")).blur(function(){
		validateEntity("text");
	})
	
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
            
        });  
	}
	function deleteEntity(){
		var row = $("#dg").datagrid('getSelected');
		if(row){
			$.messager.confirm("提示","确定删除选中数据？", function (r) {  
		        if(r){
		        	$.ajax({
		        		url:"<%=request.getContextPath()%>/numberType/deleteEntity.do",
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
            url: '<%=request.getContextPath()%>/numberType/updateEntity.do',
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
            url: '<%=request.getContextPath()%>/numberType/addEntity.do',
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
			text:rowData.text,
			rule:rowData.rule,
			numberLength:rowData.numberLength
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
			url:"<%=request.getContextPath()%>/numberType/validateEntity.do",
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
</script>
</html>