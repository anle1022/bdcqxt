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
	tr,td{    margin: 0;
    padding: 0 4px !important;    line-height: 18px;
    font-size: 12px;
    border: 1px solid #ccc;
    border-style: dashed; 
     }
    tbody>tr{
     	background-color:#FFF;
     	border-top:1px solid lightgray;
    }
    thead>tr{
     	background-color:#F5F5F5;
   		white-space: nowrap;
    	word-wrap: normal;
    }
    tbody>tr:hover{
    	cursor:pointer;
    	background: #e6e6e6;
		color: #00438a;
		
    }
    .tr-click-color{
    	background-color:#0081C2 ! important;
    	color:#FFF;
    }
.widget-title>div.tool-bar>* {
	float: left;
	/* margin:auto auto; */
}

.widget-title>div.tool-bar {
	margin-top: 3px;
	padding-left: 3px;
}
/*  div.tool-bar>label, div.tool-bar>button{ padding-top:3px; line-height: 26px;} */
div.tool-bar>* {
	margin-right: 5px;
	margin-top: 6px;
}
div.checkbox>ins{margin-top: 0px;margin-left: -16px;margin-right: 2px;height: 26px;}
input.easyui-datebox {
	width: 100px !important
}
.row-fluid>.span12{margin-top:-7px;}
</style>

</head>
<body >
<div id="content" class="container"> 
	<div id="breadcrumb">
		<a href="#" title="不动产单元号验证" class="tip-bottom"><i class="icon-camera"></i> 不动产单元号验证</a>
		<a href="#" class="current">不动产单元号验证统计</a>
	</div>
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
						地区: <input id= "orgCode" class="easyui-combobox" name="orgCode"
								style="width: 200px"
								data-options="
			                    valueField: 'orgCode',
			                    textField: 'orgName',
			                    url:'<%=request.getContextPath()%>/organization/findOrgTreeByOrgCode.do?orgCode=${user.orgCode }'
			                    ">	
						是否存在: <input id="exist" class="easyui-combobox" name="exists" 
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
					class="easyui-datagrid"
					url="<%=request.getContextPath()%>/bdcdyh/countNum.do"
					pagination="true" toolbar="#toolbar_item" rownumbers="true"
					fitColumns="true" singleSelect="true"
					data-options="">
					<thead >
						<tr>
							
							<th  width="100px" data-options="field:'organization',
					                	formatter:function(val){ 
											return val.orgName;
										}"
								
							>地区</th>
							<th  width="100px" data-options="field:'exist',
					                	formatter:function(val){ 
											if(val==1){  
												 return '存在';  
											}else{
												return '不存在';
											}  
										}">是否存在</th>
							<th field="sum" width="100px">数量</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/jquery.easyui.min.js"></script>
<script src="<%=request.getContextPath()%>/matrix-admin01/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/datagrid-groupview.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/easyui.extends.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/easyui-lang-zh_CN.js"></script>
	
<script type="text/javascript">
function reload(){
	var startTime = $("#startTime").datebox("getValue");
	var endTime = $("#endTime").datebox("getValue");
	var orgCode = $("#orgCode").combobox("getValue");
	var exists = $("#exist").combobox("getValue");
    $("#dg").datagrid('load', {
    	startTime:startTime,
    	endTime:endTime,
    	orgCode:orgCode,
    	exist:exists
    });  
}

function reset(){
	$("#orgCode").textbox("setValue","");
	$("#exist").combobox("setValue","");
}

function exportExcel(){
	var startTime = $("#startTime").datebox("getValue");
	var endTime = $("#endTime").datebox("getValue");
	var orgCode = $("#orgCode").combobox("getValue");
	var exist = $("#exist").textbox("getValue");
	window.open("<%=request.getContextPath()%>/bdcdyh/exportExcel.do?startTime="+startTime+"&endTime="+endTime+"&orgCode="+orgCode+"&exist="+exist,"_self");
}
</script>
</html>