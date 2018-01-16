<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/easyui/themes/bootstrap/easyui.css">
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/matrix-admin01/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/matrix-admin01/css/bootstrap-responsive.min.css" />
	<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/easyui/themes/bootstrap/easyui.css">
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/matrix-admin01/css/datepicker.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/matrix-admin01/css/unicorn.main.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/matrix-admin01/css/select2.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/js_css/radioAndCheckbox.css" />
<script type="text/javascript"
	src="<%=request.getContextPath() %>/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js_css/radioAndCheckbox.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js_css/common.js"></script>
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
</head>
<body class="easyui-layout">
<!-- 	<div region="east" split="true" title="East" style="width:100px;padding:10px;">east region</div> -->
	
	<div region="west" split="true" title="登记种类" style="width:300px;padding:10px;">
	
    <ul id="tt1" class="easyui-tree" animate="true" dnd="true" data-options="checkbox:true">
    </ul>
	</div>
	
	<div region="center" title="">
	
	<div class="row-fluid">
		<div class="span12">
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
							地区:<input class="easyui-combobox" id="orgCode" name="orgCode"
								style="width: 100px"
								data-options="
				                    valueField: 'orgCode',
				                    textField: 'orgName',
				                    url:'<%=request.getContextPath()%>/organization/findOrgTreeByOrgCode.do?orgCode=${user.orgCode }'
								">
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

function reload(){
	initThead();
	initTbody();
}
function reload1(){
	var nodes = $("#tt1").tree('getChecked');
	var array = new Array();
	for(var i=0;i<nodes.length;i++){
		if(nodes[i].leaf == 1){
			array.push(nodes[i].text);
		}
	}
}
function initTree(){
	var ul = $('#tt1');
	ul.tree({
		url:'<%=request.getContextPath() %>/registType/findTypeByPid.do',
		onClick:function(node){
			if(node.leaf==1){
			}else{
				$(this).tree('toggle',node.target); 
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
	
	var orgCode = $("#orgCode").combobox("getValue");
	var startTime = $("#startTime").datebox("getValue");
	var endTime = $("#endTime").datebox("getValue");
	var nodes = $("#tt1").tree('getChecked');
	var node = "";
	for(var i=0;i<nodes.length;i++){
		if(nodes[i].leaf == 1){
			node += nodes[i].text+",";
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
	var orgCode = $("#orgCode").combobox("getValue");
	var startTime = $("#startTime").datebox("getValue");
	var endTime = $("#endTime").datebox("getValue");
	var nodes = $("#tt1").tree('getChecked');
	var node = "";
	for(var i=0;i<nodes.length;i++){
		if(nodes[i].leaf == 1){
			node += nodes[i].text+",";
		}
	}

	window.open("<%=request.getContextPath()%>/case/exportExcels.do?orgCode="+orgCode+"&nodes="+node+"&startTime="+startTime+"&endTime="+endTime,"_self");
}
</script>
</html>