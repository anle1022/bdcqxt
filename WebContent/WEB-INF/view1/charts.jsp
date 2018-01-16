<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Unicorn Admin</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/matrix-admin01/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/matrix-admin01/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/easyui/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/easyui/themes/color.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/matrix-admin01/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/matrix-admin01/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/matrix-admin01/css/unicorn.main.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/drawing/jquery.handsontable.full.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/drawing/style.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/easyui-lang-zh_CN.js"></script>
<script src="<%=request.getContextPath() %>/drawing/highcharts.js"></script>
<style type="text/css">
.row-fluid>.span12{margin-top:-7px;}
.chart_combo{border:1px solid lightgray;margin-bottom:10px;}
.height-350{height:350px}
input.easyui-datebox {
	width: 100px !important
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
</style>
</head>
<body>
<div class="row-fluid">
		<div class="span12">
		<div id="widget-box" class="widget-box">
			<div class="widget-title">
			<div class="tool-bar ">
					<label class="control-label">开始日期:</label>
					<div class="controls">
						<input id="startTime" value="${startTime }" name="time" class="easyui-datebox">
					</div>
					<label class="control-label">结束日期:</label>
					<div class="controls">
						<input id="endTime" value="${endTime }" name="time" class="easyui-datebox">
					</div>
					<button onclick="initCharts()" class="btn btn-mini">
						<i class="icon icon-search"></i>查询
					</button>
					
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row-fluid">
	<div class="span3">
		<div id="pie_djlx1" class="chart_combo height-350"></div>
		<!-- <div id="widget-box" class="widget-box">
			<div class="widget-title">
				<span class="icon"> <i class="icon-signal"></i></span>
				<h5>登记类型数据信息</h5>
			</div>
			<div class="widget-content">
				<div id="pie_djlx1" class="chart_combo"></div>
			</div>
		</div> -->
	</div> 
	<div class="span3">
		<div id="pie_bllx" class="chart_combo height-350"></div>
	</div> 
	<div class="span6">
		<div class="chart_combo height-350">
				<table id="dg" title="列表" style="height:350px;"
					class="easyui-datagrid"
					url="<%=request.getContextPath()%>/serialNumber/findEntityListInPage.do"
					pagination="true" toolbar="#toolbar_item" rownumbers="true"
					fitColumns="true" singleSelect="true"
					data-options="onDblClickRow:function(rowIndex,rowData){modifyRow(rowIndex,rowData)}">
					<thead>

						<tr>
							<!-- <th field="id" width="210px">序号</th> -->
							<th field="year" width="50px">年份</th>
							<th
								data-options="field:'numberType',
					                	formatter:function(val){  
											if(val){  
												 return val.text;  
											}  
										}"
								width="100px">分区类型</th>
							<th field="minNumber" width="100px">初始值</th>
							<th field="curNumber" width="100px">当前值</th>
							<th field="maxNumber" width="100px">最大值</th>

						</tr>
					</thead>
				</table>
			</div>
	</div> 
 </div>
 <div class="row-fluid">
	<div class="span12">
		<div id="column_org_processNode" class="chart_combo"></div>
	</div> 
</div>


<script type="text/javascript">



</script>
<script type="text/javascript">
$(function(){
	initCharts();
});
//保留2位小数
function twoDecimal(x) {
    var f_x = parseFloat(x);
    if (isNaN(f_x)) {
        alert('错误的参数');
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
function initCharts(){
	var startTime = $("#startTime").datebox("getValue");
	var endTime = $("#endTime").datebox("getValue");
	$.ajax({
		url:'<%=request.getContextPath() %>/count/findAll.do',
		data:{startTime:startTime,endTime:endTime},
		type:'POST',
		success:function(data){
			if(!data){
				return;
			}
	        //柱状图
	        var columnSeries = new Array();
			for(var i=0;i<data.processNode.length;i++){
				var serie = new Object();
				serie.type = "column";
				serie.name = data.processNode[i].key;
				serie.data = data.processNode_orgList[i];
				columnSeries.push(serie);
			}
			var orgSLSerie = new Object();
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
			columnSeries.push(orgSLSerie);
			var xtext = new Array();
			$.each(data.org,function(i,d){
				xtext.push(d.orgName);
			}); 
			initChart("column_org_processNode",xtext,"地区流程信息","受理信息","",columnSeries);
			//饼图
		 	var bllxSeries = new Array();
			var bllxSerie = new Object();
			bllxSerie.type = "pie";
			bllxSerie.name = "办理类型分类对比";
			var bllxData = new Array();
			$.each(data.bllxList,function(i,d){
				var obj = new Object();
				obj.name = d.key;
				obj.y = d.value-0;
				bllxData.push(obj);
			});
			bllxSerie.data = bllxData;
			bllxSeries.push(bllxSerie); 
			initChart("pie_bllx",xtext,"办理类型分类对比","","",bllxSeries);
			//
			
			var djlx1Serie = new Object();
			djlx1Serie.type = "pie";
			djlx1Serie.name = "登记类型分类对比";
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
			initChart("pie_djlx1",xtext,"登记类型分类对比","","",djlx1Series);
		}
	});
	
}

function initChart(target,xAxisText,title,title2,ytitle,series){
	new Highcharts.Chart({
        chart: {
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

</body>
</html>
