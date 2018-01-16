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
.row-fluid>.span12{margin-top:-16px;}
</style>

</head>
<body >
<div id="content" class="container">
<!-- 	<div region="east" split="true" title="East" style="width:100px;padding:10px;">east region</div> -->
<div id="breadcrumb">
	<a href="#" title="流程监控" class="tip-bottom"><i class="icon-facetime-video"></i> 业务量监控</a>
	<a href="#" class="current">业务量监控</a>
</div>
<div class="row-fluid">
	<div class="span4">
	<div id="widget-box" class="widget-box">
		<ul id="tt1" class="easyui-tree" animate="true" dnd="true" data-options="checkbox:true"> </ul>
	</div>
	</div>
	<div class="span8" style="margin-left:0px;">
			<div class="widget-box">
				<div class="widget-title">
					<div class="tool-bar ">
					
						<label class="control-label">开始日期:</label>
						<div class="controls">
							<!-- <input type="text" width="100px" data-date="2016-09-07" data-date-format="yyyy-mm-dd" value="2016-09-07" class="datepicker" /> -->
							<input id="startTime" name="time" value="${startTime}" class="easyui-datebox">
						</div>
						
						<label class="control-label">结束日期:</label>
						<div class="controls">
							<!-- <input type="text" width="100px" data-date="2016-09-07" data-date-format="yyyy-mm-dd" value="2016-09-07" class="datepicker" /> -->
							<input id="endTime" name="time" value="${endTime}" class="easyui-datebox">
						</div>
						
<!-- 						<div id="city" class="checkbox"><ins class="checked"></ins>地区</div> -->

						<div class="datagrid-btn-separator" id="city"><ins class="checked"></ins>
							登记部门:<input class="easyui-combobox" id="deptId"  style="width: 100px" 
								data-options=" valueField:'id',textField:'orgName',url:'<%=request.getContextPath()%>/dept/findEntityList.do'">
						</div>
						<div id="djlx"  ><ins class="checked"></ins></div>
						<button onclick="reload()" class="btn btn-mini">
							<i class="icon icon-search"></i>查询
						</button>
						
						<button onclick="exportExcel()" class="btn btn-success btn-mini">
							<i class="icon-download-alt icon-white"></i>导出
						</button>
					</div>
				</div>
			
				
				<div class="widget-content nopadding">
					<table class="table table-bordered data-table">
						<thead id="thead">
							
						</thead>
						<tbody id="tbody">
						</tbody>
					</table>
				</div>
				
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
var columns = null;
$(function(){
	initTree();
	reload();
	
});
function collapseAll(){
	var node = $('#tt1').tree('getSelected');
	if (node){
		$('#tt1').tree('collapseAll', node.target);
	} else {
		$('#tt1').tree('collapseAll');
	}
}
function reload(){
	initThead();
	initTbody();
}
function reload1(){
	var nodes = $("#tt1").tree('getChecked');
	var array = new Array();
	for(var i=0;i<nodes.length;i++){
		if(nodes[i].leaf == 1){
			array.push(nodes[i].id);
			
	alert(nodes[i].id);
		}
	}
}
function initTree(){
	var ul = $('#tt1');
	ul.tree({
		url:'<%=request.getContextPath() %>/registType/findTypeByPid.do',
		onClick:function(node){
			if(node.leaf==1){
				$('#tt1').tree('collapse',node.target);
			}else{
				//$(this).tree('toggle'); 
				$('#tt1').tree('collapse',node.target);

			}
		}
	});
}

function initThead(){
	var city = true;
	var thead = $("#thead");
	thead.html("");
	var tr = $("<tr></tr>");
	if(city){
		var td0 = $("<td>地区</td>");
		tr.append(td0);
	}
	var nodes = $("#tt1").tree('getChecked');
	
	if(nodes.length>0){
		var td0 = $("<td>登记类型</td>");
		tr.append(td0);
	}
	
	$.ajax({
		url:'<%=request.getContextPath()%>/processNode/findEntityList.do',
		type:'get',
		success:function(data){
			$.each(data,function(i,d){
				var td = $("<td>"+d.name+"</td>");
				tr.append(td);
			});
			thead.append(tr);
		}
	});
}
function initTbody(){
	var orgCode = $("#deptId").combobox("getValue");
	var startTime = $("#startTime").datebox("getValue");
	var endTime = $("#endTime").datebox("getValue");
	var nodes = $("#tt1").tree('getChecked');
	var node = "";
	for(var i=0;i<nodes.length;i++){
		if(nodes[i].leaf == 1){
			node += nodes[i].id+",";
		}
	}
	var tbody = $("#tbody");
	tbody.html("");
	$.ajax({
		url:'<%=request.getContextPath()%>/case/countReports.do',
		type:'POST',
		data:{ "orgCode":orgCode,"startTime":startTime,"endTime":endTime,"nodes":node},
		success:function(data){
			$.each(data,function(i,d){
				var tr = $("<tr></tr>");
				$.each(d,function(index,value){
					if(value == null){
						value = 0;
					}
					var td = $("<td>"+value+"</td>");
					tr.append(td);
				});
				tbody.append(tr);
			});
			$("tbody>tr").click(function(){
				$(this).addClass("tr-click-color").siblings().removeClass("tr-click-color");
			})
		}
	});
}
function exportExcel(){
	//var city = $("#city>ins").hasClass("checked");
	var orgCode = $("#deptId").combobox("getValue");
	var startTime = $("#startTime").datebox("getValue");
	var endTime = $("#endTime").datebox("getValue");
	var nodes = $("#tt1").tree('getChecked');
	var node = "";
	for(var i=0;i<nodes.length;i++){
		if(nodes[i].leaf == 1){
			node += nodes[i].id+",";
		}
	}

	window.open("<%=request.getContextPath()%>/case/exportExcels.do?orgCode="+orgCode+"&nodes="+node+"&startTime="+startTime+"&endTime="+endTime,"_self");
}
</script>
</html>