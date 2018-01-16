<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

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
<jsp:include page="./main_header.jsp"></jsp:include>
<style type="text/css">
.quick-actions li a {
	padding: 4px 0px;
	padding-left: 9px;
}
select {
	width: 65px;
	background-color: #fff;
	border: 1px solid #ccc;
}
.stat-boxes,.quick-actions,.quick-actions-horizontal,.stats-plain {
	display: inline-block;
	list-style: none outside none;
	/* margin: -8px 0 -23px; */
	text-align: center;
}
.stat-boxes>li:hover {
	cursor: pointer
}
div.bllx{
	display:none;
}
.widget-title>div.tool-bar>* {float: left;}
.widget-title>div.tool-bar {margin-top: 3px;padding-left: 3px;}
div.tool-bar>* {margin-right: 5px;margin-top: 6px;}
input.easyui-datebox {width: 100px !important}
.BOX_li{ background-color: #F5F5F5;border: 1px solid #DDDDDD;cursor: pointer;margin-left:5px !important;padding: 13px 20px 10px;position: relative;height:175px !important;}
.BOX_li small { margin-left: 4px; font-size: 12px;color: #888888;}
.box_title{background-color: #F9F9F9; margin-top: -23px;width: 100px;text-align: center;}
div.djlx{display:none;} 
.row-fluid>.span12{margin-top:8px;}
body{background-color:#FFF;border:0px}
.tab-pane{margin-top:-25px;}
.nav { margin-bottom: 0px;}
.chart_combo{border:1px solid lightgray;margin-bottom:10px;}
.height-350{height:350px}
input.easyui-datebox {width: 100px !important}
.widget-title>div.tool-bar>* {float: left;}

.widget-title>div.tool-bar {margin-top: 3px;padding-left: 3px;}
/*  div.tool-bar>label, div.tool-bar>button{ padding-top:3px; line-height: 26px;} */
div.tool-bar>* {margin-right: 5px;margin-top: 6px;}
.widget-box {
    margin-top: 15px !important;
}
</style>
</head>
<body>
	<div id="content" class="container">
		<div id="breadcrumb">
			<a href="#" class="current"><i class="icon-home"></i>主页</a>
		</div>
		<div class="row-fluid">
			<ul class="nav nav-tabs">
				<!-- <li class="active"><a href="#tab3" data-toggle="tab">超期案件</a></li> -->
				<li class="active"><a href="#tab1" data-toggle="tab">图形统计</a></li>
				<li><a href="#tab2" data-toggle="tab">数据统计</a></li>
				
			</ul>

			<div class="tab-content">
				
				<div id="tab1" class="tab-pane active">
					<div class="row-fluid">
						<div class="span12">
							<div class="widget-box">
								<div class="widget-title">
									<div class="tool-bar ">
										<label class="control-label">开始日期:</label>
										<div class="controls">
											<input id="startTime" value="${startTime }" name="time"
												class="easyui-datebox">
										</div>
										<label class="control-label">结束日期:</label>
										<div class="controls">
											<input id="endTime" value="${endTime }" name="time"
												class="easyui-datebox">
										</div>
										<button onclick="find()" class="btn btn-mini">
											<i class="icon icon-search"></i>查询
										</button>

									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div id="pie_djlx1" class="chart_combo height-350"></div>
						</div>
						<div class="span6">
							<div id="pie_bllx" class="chart_combo height-350"></div>
						</div>
						
					</div> 
					<div class="row-fluid">
						<div class="span12">
							<div id="column_org_processNode" class="chart_combo"></div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span3">
							<div class="chart_combo height-350">
								<div id="gd"></div>

							</div>
						</div>
						<div class="span2">
							<div id="" class="chart_combo height-350">
								<div id="extendedCase"></div>
							</div>
						</div>
						<div class="span7">
							<div id="extendedCaseChart" class="chart_combo height-350"></div>
						</div>
						
					</div>
				</div>
				<div id="tab2" class="tab-pane">
					<div class="row-fluid">
						<div class="span12">
							<div id="widget-box" class="widget-box">
								<div class="widget-title">
									<div class="tool-bar ">
										<label class="control-label">开始日期:</label>
										<div class="controls">
											<input id="startTime1" value="${startTime }" name="time"
												class="easyui-datebox">
										</div>
										<label class="control-label">结束日期:</label>
										<div class="controls">
											<input id="endTime1" value="${endTime }" name="time"
												class="easyui-datebox">
										</div>
										<button onclick="reload()" class="btn btn-mini">
											<i class="icon icon-search"></i>查询
										</button>
										<label class="control-label">地区/登记种类<!-- /办理类型: --></label>
										<div class="controls">
											<input id="model" class="easyui-combobox" value=""
												style="width: 100px"
												data-options=" valueField: 'modelValue', textField: 'modelName', data:[{'modelName':'地区','modelValue':'organization'},{'modelName':'登记类型','modelValue':'djlx'}],panelHeight:'auto'" />
										</div>
									</div>
								</div>
								<div class="widget-content nopadding updates">

									<div class="new-update clearfix">
										<ul class="stat-boxes quick-actions">
											<li class="processNode_li">
												<div class="left ">
													<a><i class="icon-people"></i></a>
												</div>
												<div class="right">
													<strong id="受理"></strong>受理
												</div>
											</li>
											<li class="processNode_li">
												<div class="left peity_bar_neutral">
													<a><i class="icon-people"></i></a>
												</div>
												<div class="right">
													<strong id="核定"></strong>核定
												</div>
											</li>
											<li class="processNode_li">
												<div class="left peity_bar_neutral">
													<a><i class="icon-shopping-bag"></i></a>
												</div>
												<div class="right">
													<strong id="缮证"></strong>缮证
												</div>
											</li>
											<li class="processNode_li">
												<div class="left peity_bar_neutral">
													<a><i class="icon-shopping-bag"></i></a>
												</div>
												<div class="right">
													<strong id="发证"></strong>发证
												</div>
											</li>
											<li class="processNode_li">
												<div class="left peity_bar_neutral">
													<a><i class="icon-shopping-bag"></i></a>
												</div>
												<div class="right">
													<strong id="退案"></strong>退案
												</div>
											</li>
										</ul>
									</div>
								</div> 

							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
			<div class="easyui-window " id="caseFlow" title=" "  closed="true"  modal="true" style="width:1100px;height:400px;display:none;">
				
				<table id="caseFlowGrid" title="流程信息列表"
					class="easyui-datagrid embed-responsive-item  " >
					<thead >
						<tr>
							<!-- <th field="id" width="50px">年份</th> -->
							<th field="ajbh" width="70px">案件编号</th>
							<th field="djlx" width="70px">登记类型</th>
							<th field="djzh" width="60px">登记字号</th>
							<th field="operator" width="45px">业务办理人</th>
							<th field="operateTime" width="50px">办理时间</th>
							<th width="35px" data-options="field:'organization1',
					                	formatter:function(val){ 
											if(val){  
												 return val.orgName;  
											}  
										}"
								
							>案件所属地区</th>
							<th width="35px" data-options="field:'dept',
 					                	formatter:function(val){  
 											if(val){   
 												 return val.orgName;   
 											}   
 										}" 
							>案件办理地区</th>
							<th field="processNode" width="35px">已办环节</th>
						</tr>
					</thead>
				</table>
			</div>
		<div class="easyui-window " id="ww" title=" "  closed="true"  modal="true" style="width:1100px;height:400px;display:none;">
			<div class="easyui-panel">
				<input type="hidden" id="extendedFlow_orgCode"><a href="javascript:void(0)" class="easyui-linkbutton" iconCls=" " plain="true" onclick="exportExtendedInfo()">导出Excel</a>
			</div>
			<table id="tt" title="超期案件流程列表" class="easyui-datagrid embed-responsive-item" >
					<thead>
						<tr>
							<th field="ajbh" width="100px">案件编号</th>
							<th field="djzh" width="100px">登记字号</th>
							<th field="operator" width="100px">当前办理人</th>
							<th field="processNode" width="100px">当前环节</th>
							<th field="operateTime" width="100px">办理时间</th>
							<th field="slsj" width="100px">受理时间</th>
							<th field="extendedTime" width="100px">应办结时间</th>
							
						</tr>
					</thead>
				</table>
		</div>	
	</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/matrix-admin01/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js_css/common.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/jquery.easyui.min.js"></script>
<script src="<%=request.getContextPath()%>/matrix-admin01/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/easyui-lang-zh_CN.js"></script>
<script src="<%=request.getContextPath() %>/drawing/highcharts.js"></script> 
<script type="text/javascript">
$(function(){
	var startTime = $("#startTime").datebox("getValue");
	var endTime = $("#endTime").datebox("getValue");
	initCharts(startTime,endTime);
	initGrid(startTime,endTime);
	initExtendedCase(startTime,endTime);
});

//保留2位小数
function twoDecimal(x) {
    var f_x = parseFloat(x);
    if (isNaN(f_x)) {
        return false;
    }
    var f_x = Math.round(x * 100) / 100;
    var s_x = f_x.toString();
    var pos_decimal = s_x.indexOf('.');
    if (pos_decimal < 0) {
        pos_decimal = s_x.length;
        s_x += '.';
    }
    while (s_x.length <= pos_decimal + 2) {
        s_x += '0';
    }
    return s_x;
}

function find(){
	var startTime = $("#startTime").datebox("getValue");
	var endTime = $("#endTime").datebox("getValue");
	initCharts(startTime,endTime);
	$('#gd').datagrid({queryParams:{startTime:startTime,endTime:endTime}});
	initExtendedCase(startTime,endTime);
	
}

function initCharts(startTime,endTime){
	
	$.ajax({
		url:'<%=request.getContextPath() %>/count/findAll.do',
		data:{startTime:startTime,endTime:endTime},
		success:function(data){
	        //柱状图
	     	var columnSeries = new Array();
			for(var i=0;i<data.processNode.length;i++){
				var serie = new Object();
				serie.type = "column";
				serie.name = data.processNode[i].key;
				serie.data = data.processNode_orgList[i];
				columnSeries.push(serie);
			}
			/* var orgSLSerie = new Object();
			orgSLSerie.type = "pie";
			orgSLSerie.name = "受理信息";
			orgSLSerie.center = [100, 80];  //饼状图坐标
			orgSLSerie.size = 100;  //饼状图直径大小
			var orgSLData = new Array();
			$.each(data.orgSLList,function(i,d){
				var obj = new Object();
				obj.name = d.key;
				obj.y = d.value-0;
				orgSLData.push(obj);
			});
			orgSLSerie.data = orgSLData;
			columnSeries.push(orgSLSerie); */
			var xtext = new Array();
			$.each(data.org,function(i,d){
				xtext.push(d.orgName);
			}); 
			initChart("column_org_processNode",xtext,"地区流程信息",''/* "受理信息" */,"",columnSeries);
			//饼图
		 	var bllxSeries = new Array();
			var bllxSerie = new Object();
			bllxSerie.type = "pie";
			bllxSerie.name = "登记量占比图（按不动产类型）";
			var bllxData = new Array();
			$.each(data.bllxList,function(i,d){
				if(d.key!=null){
					var obj = new Object();
					obj.name = d.key;
					obj.y = d.value-0;
					bllxData.push(obj);
				}
				
			});
			bllxSerie.data = bllxData;
			bllxSeries.push(bllxSerie); 
			initChart("pie_bllx",xtext,"登记量占比图（按不动产类型）","","",bllxSeries);
			//
			
			var djlx1Serie = new Object();
			djlx1Serie.type = "pie";
			djlx1Serie.name = "登记量占比图（按登记种类）";
			var djlx1Data = new Array();
			$.each(data.djlx1List,function(i,d){
				var obj = new Object();
				obj.name = d.key;
				obj.y = d.value-0;
				djlx1Data.push(obj);
			}); 
			djlx1Serie.data = djlx1Data;
			var djlx1Series = new Array();
			djlx1Series.push(djlx1Serie);
			initChart("pie_djlx1",xtext,"登记量占比图（按登记种类）","","",djlx1Series); 
		}
	});
	
}

function initGrid(startTime,endTime){
	$('#gd').datagrid({
		title:'统计信息',//标题
		width:'100%',
		height:350,
		nowrap: false, //是否在一行显示数据
		striped: true, //是否显示斑马线
		collapsible:true,//是否显示可折叠按钮
		url: '<%=request.getContextPath()%>/count/findDaySummaryInPage.do', //从远程请求数据的地址
		queryParams:{startTime:startTime,endTime:endTime},
		remoteSort: false,//false可以按从大到小或从小到大排序
		fitColumns: true,
		columns:[[
            /* {field:'id',checkbox:true}, */
            {title:'地区',field:'dept',width:'30%',sortable:true,
            	formatter:function(value,rec){
            		return value.orgName;
            	},sorter:function(a,b){
            		return a.orgCode>b.orgCode?1:-1;
            	}   
            },
            {title:'流程节点',field:'processNode',width:'35%' },
            {title:'合计',field:'total',width:'30%' }
            
		]]
	});
}

function initChart(target,xAxisText,title,title2,ytitle,series){
	new Highcharts.Chart({
        chart: {
        	type: 'column',
        	 margin: 75,
             options3d: {
                 enabled: true,
                 alpha: 10,
                 beta: 25,
                 depth: 70
             },
            renderTo:target //关联页面元素div#id
        },
        title: {  //图表标题
            text:title
        },
        xAxis: { //x轴
            categories:xAxisText,  //X轴类别
			labels:{y:18}  //x轴标签位置：距X轴下方18像素
        },
		yAxis: {  //y轴
            title: {text: ytitle}, //y轴标题
			lineWidth: 2 //基线宽度
        },
        tooltip: {
            formatter: function() { //格式化鼠标滑向图表数据点时显示的提示框
                var s;
                if (this.point.name) { // 饼状图
                    s = '<b>' + this.point.name + '</b>: <br>' + this.y+ '(' + twoDecimal(this.percentage) + '%)';
                } else {
                    s = '' + this.x +'-'+this.series.name+ ': ' + this.y + '';
                }
                return s;
            }
        },
        plotOptions: {
        	series:{
				connectNulls: true
			},
			line: {
				dataLabels: {
					enabled: true
				},
				enableMouseTracking: true
			},
			spline: {
				dataLabels: {
					enabled: true
				}
			},
			bar: {
				dataLabels: {
					enabled: true
				}
			},
			column: {
				dataLabels: {
					enabled: true
				}
			},
			area: {
				dataLabels: {
					enabled: true
				}
			},
			pie: {
				dataLabels: {
					formatter: function() {
						var p = this.percentage + '';
						var pos = p.indexOf('.');
						if(pos != -1){
							p = p.substr(0, pos + 2);
						}
						return '<b>'+ this.point.name +'</b>: ' + p +' %';
					}
				}
			}
        },
        labels: { //图表标签
            items: [{
                html: title2,//图表标签名称
                style: {
                    left: '28px',
                    top: '8px'
                }
            }]
        },
		exporting: {
			enabled: true  //设置导出按钮不可用
		},
		credits: { 
			text: '',
			href: ''
		},
        series:series
    });
}
</script>
<script type="text/javascript">
$(function(){
	setTimeout(function(){
		reload();
	}, 100)
	
	$("#model").combobox({
		onChange:function(option,option1){
			if(option != option1){
				$("div.box_div").slideUp(200);
				$("div."+option).slideDown(200);
			}
		}
	});
	$("#model").combobox("setValue","organization");
}); 

function initBtnEvent(){
	
	$(".processNode_li").click(function(){
		var startTime = $("#startTime1").datebox("getValue");
		var endTime = $("#endTime1").datebox("getValue");
		var processNode = $(this).find(".right").find("strong").attr("id");
		var dataObj = new Object();
		dataObj.startTime = startTime;
		dataObj.endTime = endTime;
		dataObj.processNode = processNode;
		loadFlow(dataObj);
	});
	$("#organization_ul>li").click(function(){
		var startTime = $("#startTime1").datebox("getValue");
		var endTime = $("#endTime1").datebox("getValue");
		var orgCode = $(this).find("strong").attr("class");
		var dataObj = new Object();
		dataObj.startTime = startTime;
		dataObj.endTime = endTime;
		dataObj.operateOrgCode  = orgCode;
		loadFlow(dataObj);
	});
	$(".djlx_li").click(function(){
		var startTime = $("#startTime1").datebox("getValue");
		var endTime = $("#endTime1").datebox("getValue");
		var djlx = $(this).find("small").attr("class");
		var dataObj = new Object();
		dataObj.startTime = startTime;
		dataObj.endTime = endTime;
		dataObj.djlx  = djlx;
		loadFlow(dataObj);
		
	});
	
	
}
function loadFlow(dataObj){
	var scrollTop = null; 
	var width = null;
	if(document.all){
		scrollTop = window.parent.document.documentElement.scrollTop+100;
		width = window.document.clientWidth/2-200;
	}else{
		scrollTop = window.parent.pageYOffset || window.parent.document.body.scrollTop;
		scrollTop = scrollTop+100;
		width = window.document.body.clientWidth/2-200;
	}
	$('#caseFlow').window({left:width-300+"px", top:scrollTop+"px"});
	$('#caseFlow').window("open");
    $("#caseFlowGrid").datagrid({
    	url: '<%=request.getContextPath()%>/case/findTheLatest.do',
    	queryParams:dataObj,
    	fitColumns: true,
    	 pagination: true, //是否显示底部分页工具栏
	        rownumbers: true,
	       	pageNumber:1,
	        pageList: [10],
	        pageSize:10
    });
}
function reload(){
	$("#model").combobox("setValue","organization");
	var startTime = $("#startTime1").datebox("getValue");
	var endTime = $("#endTime1").datebox("getValue");
	$.ajax({
		url:"<%=request.getContextPath()%>/case/countCaseDetial.do",
		data:{startTime:startTime,endTime:endTime},
		onDblClickRow:function(rowIndex,rowData){loadDetail(rowIndex,rowData)},
		success:function(data){
			$(".box_div").remove();
			$.each(data.processNode,function(i,d){
				$("#"+d.key).html(d.value);
			});
			
			$.each(data.bllx,function(i,d){
				if($("#bllx").length>0){
					div = $("#bllx");
				}else{
					div = $("<div id='bllx' class='box_div bllx widget-content ' class='widget-content'><label class='box_title'>办理类型</label><ul id='bllx_ul' class='thumbnails'></ul>");
					$("#widget-box").append(div);
				}
				var li;
				if($("."+d.key).length>0){
					li = $("."+d.key).parent();
				}else{
					li = $("<li class='BOX_li bllx_li span2 col-md-2 col-sm-2 col-xxs-2' ><i class='icon-arrow-right'></i><strong class='"+d.key+"'>"+d.key+"</strong><br /></li>");
				}
				
				$("#"+d.key).html(d.value);
				var small = $("<small >"+d.key+":"+d.value+"</small><br />");
				li.append(small);
				$("#bllx_ul").append(li);
			});
			$.each(data.org_processNode,function(i,d){				
				var orgCode = d.key.split("-")[0];
				var processNode = d.key.split("-")[1];
				if($("#organization").length>0){
					div = $("#organization");
				}else{
					div = $("<div id='organization' class='box_div organization widget-content ' class='widget-content'><label class='box_title'>地区</label><ul id='organization_ul' class='thumbnails'></ul>");
					$("#widget-box").append(div);
				}
				var li;
				if($("."+orgCode).length>0){
					li = $("."+orgCode).parent();
				}else{
					li = $("<li class='BOX_li org_li span2 col-md-2 col-sm-2 col-xxs-2' ><i class='icon-arrow-right'></i><strong class='"+orgCode+"'>"+d.value1+"</strong><br /></li>");
				}
				var small = $("<small >"+processNode+":"+d.value+"</small><br />");
				li.append(small);
				$("#organization_ul").append(li);
			});
			$.each(data.djlx_processNode,function(i,d){
				var djlx = d.key.split("-")[0];
				var djlx1 = djlx.split("_")[0];
				var djlx2 = djlx.split("_")[1];
				var djlxId = d.value1;
				var processNode = d.key.split("-")[1];
				var div;
				if($("#"+djlx2).length>0){
					div = $("#"+djlx2);
				}else{
					div = $("<div id='"+djlx2+"' class='box_div djlx widget-content ' class='widget-content'><label class='box_title'>"+djlx2+"</label><ul id='ul_"+djlx2+"' class='thumbnails'></ul>");
					$("#widget-box").append(div);
				}
				var li;
				if($("small."+djlxId).length>0){
					li = $("small."+djlxId).parent();
				}else {
					li = $("<li class='BOX_li djlx_li span2 col-md-2 col-sm-2 col-xxs-2' ><i class='icon-arrow-right'></i><small class='"+djlxId+"'>"+djlx1+"</small><br></li>");
				}
				var small = $("<small >"+processNode+":"+d.value+"</small><br />");
				li.append(small);
				$("#ul_"+djlx2).append(li);
				
			});
		 	
			initBtnEvent();
		},
		error:function(data){
			
		}
	});
}
</script>
<script type="text/javascript">

function initExtendedCase(startTime,endTime){
	$.ajax({
		url:'<%=request.getContextPath()%>/count/findExtendedCaseList.do',
		data:{startTime:startTime,endTime:endTime},
		success:function(data){
			var cqSeries = new Array();
			var columns = new Array();
			var values = new Array(); 
			var serie = new Object();
			serie.type = "column";
			$.each(data,function(i,d){
				columns.push(d.orgName);
				values.push(d.total-0);
			});
			serie.data = values;
			serie.name = ["数量"];
			cqSeries.push(serie);
			initChart("extendedCaseChart",columns,"超期信息","","",cqSeries);
		}
	})
	
	$("#extendedCase").datagrid({
		title:'超期案件统计',//标题
		width:'100%',
		height:'100%',
		nowrap: false, //是否在一行显示数据
		striped: true, //是否显示斑马线
		collapsible:true,//是否显示可折叠按钮
		url: '<%=request.getContextPath()%>/count/findExtendedCaseList.do', //从远程请求数据的地址
		queryParams:{startTime:startTime,endTime:endTime},
		remoteSort: false,//false可以按从大到小或从小到大排序
		fitColumns: true,
		columns:[[
            {title:'地区',field:'orgName',width:'50%',sortable:true},
            {title:'合计',field:'total',width:'40%' }
		]] ,
		onDblClickRow:function(rowIndex,rowData){loadDetail(rowIndex,rowData)},
        rownumbers: true
	});
}
function loadDetail(rowIndex,rowData){
	var scrollTop = null; 
	if(document.all){
		scrollTop = window.parent.document.documentElement.scrollTop+100;
	}else{
		scrollTop = window.parent.pageYOffset || window.parent.document.body.scrollTop;
		scrollTop = scrollTop+100;
	}
	$('#ww').window({left:$(window).width()/2-500,top:scrollTop});
	$('#ww').window("open");
	$("#extendedFlow_orgCode").val(rowData.orgCode);
	var startTime = $("#startTime").datebox("getValue");
	var endTime = $("#endTime").datebox("getValue");
	$("#tt").datagrid({
    	fitColumns:true,
    	queryParams:{"orgCode":rowData.orgCode,"startTime":startTime,"endTime":endTime},
    	url: '<%=request.getContextPath()%>/count/findExtend.do',
    	pagination: true, //是否显示底部分页工具栏
	  	rownumbers: true,
	  	pageNumber:1,
	  	pageList: [10],
	  	pageSize:10
    });
}
function exportExtendedInfo(){
	var orgCode = $("#extendedFlow_orgCode").val();
// 	var orgCode = rowData.orgCode;
	var startTime = $("#startTime").datebox("getValue");
	var endTime = $("#endTime").datebox("getValue");
	 console.log(orgCode)
	window.open("<%=request.getContextPath()%>/case/exportExtendedCase.do?orgCode="+orgCode+"&startTime="+startTime+"&endTime="+endTime,"_self");
}
</script>
</body>
</html>