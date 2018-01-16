<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>JS 在线统计图表制作工具, 在线统计图, 曲线图, 折线图, 柱状图, 饼图, 直方图, JS 图表, 免费的 JS 统计图表生成器</title>
	<meta name="keywords" content="在线图表制作工具,在线统计图,曲线图,折线图,柱状图,饼图,直方图,JS图表,JS统计图表" />
	<meta name="description" content="这是一个免费的在线生成各种数据图表的工具, 可像 Excel 一样编辑" />
	<link rel="stylesheet" type="text/css" href="drawing/jquery.handsontable.full.css" />
	<link rel="stylesheet" type="text/css" href="drawing/style.css" />
	<script src="drawing/jquery.min.js"></script>
	<script src="drawing/highcharts.js"></script>
	<script src="drawing/jquery.handsontable.full.js"></script>
	<script src="drawing/ext-base.js" type="text/javascript"></script> 
	<script src="drawing/ext-all.js" type="text/javascript"></script> 
	<script src="drawing/ext-lang-zh_CN.js" type="text/javascript"></script> 
</head>
<body>

<h1 style="text-align: center;">
</h1>

<h3>表格录入:</h3>
<div id="data"></div>

<p>注: 支持从 Excel 表直接复制粘贴表格. 填入数据后, 表格会自动扩展.</p>

<fieldset id="form" style="margin: 20px 0; padding: 0;">
	<legend>生成图表</legend>
	<table>
		<tr>
			<td>类型:</td>
			<td>
				<select name="type">
					<option value="spline">曲线图</option>
					<option value="line">折线图</option>
					<option value="column" selected="selected">柱状图(竖柱)</option>
					<option value="bar">条形图(横条)</option>
					<option value="pie">饼图</option>
					<option value="area">面积图</option>
					<option value="scatter">XY 散点图</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>标题:</td>
			<td>
				<input type="text" name="title" value="标题" style="width: 200px;" />
			</td>
		</tr>
		<tr>
			<td>Y轴:</td>
			<td>
				<input type="text" name="ytitle" value="Y轴" style="width: 200px;" />
			</td>
		</tr>
		<tr>
			<td></td>
			<td><button>生成图表</button></td>
		</tr>
	</table>
</fieldset>


<div id="charts" style="width: 99%; margin: 0; padding: 0;"></div>


<script type="text/javascript">
<!--


$(function(){
	$(document).ready(function() {
		var data=<%=request.getAttribute("a1")%>
		var table = $('#data').handsontable({
			minRows:2,
			minCols:2,
			minSpareRows: 1,
			minSpareCols: 1,
			rowHeaders: true,
			colHeaders: true,
			data: data
		}).data('handsontable');

		$('button').click(function(){
			var datagrid = table.getData();
			var type = $('select[name=type]').val();
			var title = $('input[name=title]').val();
			var ytitle = $('input[name=ytitle]').val();
			show_chart(type, title, ytitle, datagrid);
			return false;
		}).click();
	});
});

function show_chart(type, title, ytitle, datagrid){
	var series = [];
	var xlabels = [];
	var xtitle = datagrid[0][0];

	for(i=1; i<datagrid[0].length-1; i++){
		var y = datagrid[0][i];
		if(y == '' || y == null){
			break;
		}
		series.push({
			name: y,
			data: []
		});
	}
	for(i=1; i<datagrid.length-1; i++){
		var x = datagrid[i][0];
		if(x == '' || x == null){
			break;
		}
		xlabels.push(x);
		for(j=1; j<datagrid[i].length-1; j++){
			if(datagrid[0][j].length == 0){
				continue;
			}
			var y = parseFloat(datagrid[i][j]);
			if(!isNaN(y)){
				series[j-1].data.push([x, y]);
			}else{
				series[j-1].data.push([x, null]);
			}
		}
	}
	var tmp = [];
	for(i=0; i<series.length; i++){
		if(series[i].data.length > 0){
			tmp.push(series[i]);
		//	alert(series[i].data);
		}
	}
	series = tmp;
	//alert(xlabels);
	//alert(series[0].data);

	$('#charts').html('');
	var charts = new Highcharts.Chart({
		chart: {
			renderTo: 'charts',
			type: type
		},
		title: {
			text: title
		},
		subtitle: {
			text: ''
		},
		xAxis: {
			title: {
				text: xtitle,
				style: {
					color: '#666',
					"font-size": '120%'
				}
			},
			categories: xlabels,
			min: 0, //
			//minRange: 1,
			minPadding: 1, //
			labels: {
				formatter: function() {
					return this.value + '';
				}
			}
		},
		yAxis: {
			title: {
				text: ytitle,
				style: {
					color: '#666',
					"font-size": '120%'
				}
			},
			labels: {
				formatter: function() {
					return this.value;
				}
			}
		},
		tooltip: {
			enabled: true,
			formatter: function() {
				return this.series.name + ': '+this.y +''; //
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
				allowPointSelect: true,
				showInLegend: true,
				dataLabels: {
					enabled: true,
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
		series: series
	});
}

//-->
</script>


<div style="font-size: 12px; margin-top: 50px; text-align: center;">
	<script type="text/javascript">
	
	</script>
</div>


</body>
</html>