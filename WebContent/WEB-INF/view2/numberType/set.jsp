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
	width: 100px;
}

.fitem input {
	width: 160px;
}

.fitem p {
	color: red;
	font-size: 12px;
}
div.radio{margin-top:2px;margin-right:0px;height:11px ! important;}
.datagrid-row {
	cursor: pointer
}

.panel-body,.row-fluid {
	overflow: hidden
}
div.dept{display: none}
.combo-panel{
	overflow:scroll;
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
		<a href="#" class="current">取号类型配置</a>
	</div>
	<div class="row-fluid">
		<div class="span8">
			<div class="widget-box">
				<div id="toolbar_item">
						分区类型：<input id="typeName" class="easyui-combobox" name="text"
								style="width: 200px" 
								data-options="valueField:'text',textField: 'text',
 								url:'<%=request.getContextPath()%>/numberType/findAll.do'"
								>
						取号类型: <input id="type" class="easyui-combobox" name="id"
							style="width: 200px" 
							data-options="valueField: 'id',
			                    textField: 'name',
			                   	url:'<%=request.getContextPath()%>/type/findAll.do'">
						<button onclick="reload()" class="btn btn-mini">
							<i class="icon icon-search"></i>查询
						</button>
						
						<button onclick="deleteEntity()" class="btn btn-mini">
							<i class="icon icon-no"></i> 删除
						</button>	

				</div>
				<table id="dg" title="列表"
					class="easyui-datagrid  embed-responsive-item "
					url="<%=request.getContextPath()%>/numberType/findEntityListInPage.do"
					pagination="true" toolbar="#toolbar_item" rownumbers="true"
					fitColumns="true" singleSelect="true"
					data-options="onDblClickRow:function(rowIndex,rowData){modifyRow(rowIndex,rowData)}">
					<thead>
						<tr>
							<th field="id" width="140px">关键词</th>
							<th field="text" width="200px">分区类型</th>
							<th field="numberLength" width="100px">流水号长度</th>
							<th field="rule" width="180px">取号规则</th>
							<th field="name" width="100px">取号类型</th>
							<th data-options="field:'bind',
										formatter:function(val){
											if(val == 'no'){  
												 return '否';  
											}else{
												return '是';
											}  
										}"  width="100px">是否绑定案件</th>
						</tr>
					</thead>
				</table>
				<!-- </div> -->
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
							<label>关键词</label> <input class="easyui-textbox" name="id" 
							readonly="readonly"
								style="width: 200px" data-options="required:true">
						</div>
						<div class="fitem">
							<label>分区类型</label> <input class="easyui-textbox" name="text"
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
							<label>取号类型</label> <input class="easyui-combobox" name="type"
								style="width: 200px" data-options="required:true,
								valueField: 'id',
			                    textField: 'name',
			                   	url:'<%=request.getContextPath()%>/type/findAll.do'
								">
						</div>
						<div class="fitem">
							<label>是否绑定案件</label> <input class="easyui-combobox" name="bind"
								style="width: 200px" data-options="valueField:'bind',textField:'bindValue',data:[{bind:'yes',bindValue:'是'},{bind:'no',bindValue:'否'}],required:true">
						</div>
						<div class="fitem">
							<p>取号规则（注）：</p>
							<p>目前只能解析year(年),month(月),day(日),number(流水号)</p>
							<p>如：不动产权证号规则写法 --- 粤（year）广州市不动产权第number号</p>
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
							<label>取号类型</label> <input class="easyui-combobox" name="type" id="bjlx"
								style="width: 200px" data-options="required:true,
 								valueField: 'id', 
 			                    textField: 'name', 
			                   	url:'<%=request.getContextPath()%>/type/findAll.do'
								">
						</div>
						<div class="fitem">
							<label>关键词</label> <input class="easyui-textbox" id="add_id"
								name="id" style="width: 200px" data-options="required:true">
						</div>
						<div class="fitem">
							<label>分区类型</label> <input class="easyui-textbox" id="add_text"
								name="text" style="width: 200px" data-options="required:true">
						</div>
						<div class="fitem">
							<label>流水号长度</label> <input class="easyui-textbox"
								name="numberLength" style="width: 200px"
								data-options="required:true,validType:'Number'">
						</div>
 						<div class="fitem dept djzh" style="display: none "> 
							<label >字轨规范</label> <input class="easyui-combobox" id="djzg_rule"  
								style="width: 200px " data-options="url:'<%=request.getContextPath()%>/dict/findEntityList.do?key=ZGGF',valueField:'nameCN',textField:'nameCN'">  
						</div>
						
						<%-- <div class="fitem dept djzh" >
							<label>字轨规范</label> <input class="easyui-combobox" id="djzg_rule"
								style="width: 200px" data-options="url:'<%=request.getContextPath()%>/dept/findEntityList.do',valueField:'djzgNo',textField:'djzgNo',formatter:function(val){return val.organization.orgName}">
						</div> --%>
						<div class="fitem dept djzh" style="display: none ">
							<label>登记机构部门</label> <input class="easyui-combobox" id="djzg_no"
								style="width: 200px" data-options="url:'<%=request.getContextPath()%>/dept/findEntityList.do',valueField:'id',textField:'djzgNo',formatter:function(val){return val.orgName}">
						</div>
						<div class="fitem dept zszmh" style="display: none ">
							<label>登记机构部门</label> <input class="easyui-combobox" id="zs_no" 
								style="width: 200px" data-options="url:'<%=request.getContextPath()%>/dept/findEntityList.do',valueField:'id',textField:'zsNo',formatter:function(val){return val.orgName}">
						</div>
						<div class="fitem dept zmh" style="display: none ">
							<label>登记机构部门</label> <input class="easyui-combobox" id="zs_no1" 
								style="width: 200px" data-options="url:'<%=request.getContextPath()%>/dept/findEntityList.do',valueField:'id',textField:'zsNo',formatter:function(val){return val.orgName}">
						</div>
						<div class="fitem dept qt" style="display: none ">
							<label>登记机构部门</label> <input class="easyui-combobox" id="qt_no"
								style="width: 200px" data-options="url:'<%=request.getContextPath()%>/dept/findEntityList.do',valueField:'id',textField:'djzgNo',formatter:function(val){return val.orgName}">
						</div>
						<div class="fitem">
							<label>取号规则</label> <input class="easyui-textbox" name="rule"
								style="width: 200px" data-options="required:true">
						</div>
						<div class="fitem">
							<label>是否绑定案件</label> <input class="easyui-combobox" name="bind"
								style="width: 200px" data-options="valueField:'bind',textField:'bindValue',data:[{bind:'yes',bindValue:'是'},{bind:'no',bindValue:'否'}],required:true">
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
	$("#bjlx").combobox({
		onChange:function(option,option1){
		var type = $("#addForm input[name=type]").val();
		var bjlx = $("#bjlx").combobox("getValue");
			if(type == "DJZH"){
				$("#add_id").textbox("setValue","DJZH");
				$(".djzh").show("slow");
				$(".zszmh").hide("slow");
				$(".zmh").hide("slow");
				$(".qt").hide("slow");
			}else{
				if(type == "BDCQZSH"){
					$("#add_id").textbox("setValue","BDCQZSH");
					$(".zszmh").show("slow");
					$(".djzh").hide("slow");
					$(".zmh").hide("slow");
					$(".qt").hide("slow");
				}else if(type == "BDCDJZMH"){
					$("#add_id").textbox("setValue","BDCDJZMH");
					$(".zmh").show("slow");
					$(".djzh").hide("slow");
					$(".zszmh").hide("slow");
					$(".qt").hide("slow");
				}else {
					$("#add_id").textbox("setValue",bjlx);
					$(".qt").show("slow");
					$(".zmh").hide("slow");
					$(".djzh").hide("slow");
					$(".zszmh").hide("slow");
				}
			}
		}
	});
	var date = new Date();
	$("#add_year").numberbox("setValue",date.getFullYear());
	$("input",$("#add_id").next("span")).blur(function(){
		validateEntity("id");
	});
	$("input",$("#add_text").next("span")).blur(function(){
		validateEntity("text");
	});
	$("div.radio").click(function(){
		var value = $(this).attr("value");
		$(".dept").hide();
		$(".dept."+value).show();
		$("#add_id").textbox("setValue","");
		if(value=="djzh"){
			$("#add_id").textbox("setValue","DJZH").attr("readonly","readonly");
		}else if(value=="zszmh"){
			$("#add_id").textbox("setValue","BDCQZSH").attr("readonly","readonly");
		}else if(value=="zmh"){
			$("#add_id").textbox("setValue","BDCDJZMH").attr("readonly","readonly");
		}  
	});
	$("#djzg_rule").combobox({
		onSelect:function(record){
			$("#add_id").textbox("setValue","DJZH"+record.nameCN);
		}
	});
	$("#djzg_no").combobox({
		onSelect:function(record){
			var djzg_rule = $("#djzg_rule").textbox("getValue");
			if(djzg_rule){
				$("#add_id").textbox("setValue","DJZH"+djzg_rule+record.djzgNo);
			}else{
				$(this).combobox("setValue","");
				$.messager.alert("操作提示", "请先选择‘字轨规范’！", "info");
			}
		}
	});
	$("#zs_no").combobox({
		onSelect:function(record){
			var zsh = $("#add_id").textbox("getValue");
			if(zsh){
				$("#add_id").textbox("setValue","BDCQZSH"+record.zsNo);
			}else{
				$("#add_id").textbox("setValue","BDCDJZMH"+zsh+record.zsNo);
			}
		}
	});
	$("#zs_no1").combobox({
		onSelect:function(record){
			var zsh = $("#add_id").textbox("getValue");
			if(zsh){
				$("#add_id").textbox("setValue","BDCDJZMH"+record.zsNo);
			}else{
				$("#add_id").textbox("setValue","BDCQZSH"+zsh+record.zsNo);
			}
		}
	});
	
	$("#qt_no").combobox({
		onSelect:function(record){
			var bjlx = $("#bjlx").combobox("getValue");
			var zsh = $("#add_id").textbox("getValue");
			zsh = bjlx;
			if(zsh){
				$("#add_id").textbox("setValue",bjlx+record.djzgNo);
			}
		}
	});
	
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
			numberLength:rowData.numberLength,
			bind:rowData.bind,
			type:rowData.type
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
	
	function reload(){
		var type = $("#type").textbox("getValue");
		var text = $("#typeName").textbox("getValue");
// 		alert(type+"222");
		
		$("#dg").datagrid('load', {
	    	
	    	type:type,
	    	text:text
        });  
	}
</script>

</html>