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
.sp_tb{width:100%;height:100%;}

.sp_tb td{height:30px;margin-bottom:5px;border:1px solid lightgray}
.sp_yj{ height: 250px !important;}
.sp_yj textarea{width:95%;height:100%;margin-top:6px}
</style>
</head>

<body>
<div id="content" class="container">
	<div id="breadcrumb">
		<a href="#" title="流程监控" class="tip-bottom"><i class="icon-facetime-video"></i> 流程监控</a>
		<a href="#" class="current">业务流程监控</a>
	</div>
	<!-- <div  id="center" data-options="region:'center'"  style="width:800px;height:100%;overflow:hidden" iconCls="icon-color_swatch"   > -->
	<div class="row-fluid">
		<div class="span12">
			<div class="widget-box">
				<div id="toolbar_item" class="easyui-panel">
					<div>
					开始日期: <input id="startTime" value="${startTime }" width="80" name="time" style="width: 120px"
						class="easyui-datebox">
					结束日期: <input id="endTime" value="${endTime}"  width="80" name="time" style="width: 120px"
						class="easyui-datebox">	
					登记字号: <input id="djzh" width="80" name="djzh" style="width: 220px"
						class="easyui-textbox">
					</div>		
					<div class="datagrid-btn-separator">
						案件编号: <input class="easyui-textbox" id="ajbh" name="ajbh"
							style="width: 180px;">
					</div>

					<div class="datagrid-btn-separator">
						地区:<input class="easyui-combobox" value="${orgCode }" id="orgCode" name="orgCode"
							style="width: 120px"
							data-options="
			                    valueField: 'id',
			                    textField: 'orgName',
			                  	url:'<%=request.getContextPath()%>/dept/findEntityList.do'
							">
					</div>

					<div class="datagrid-btn-separator">
						环节:<input class="easyui-combobox" value="" id="processNode"
							name="processNode" style="width: 100px"
							data-options="
			                     valueField: 'key',
			                    textField: 'key',
			                   	url:'<%=request.getContextPath()%>/processNode/findEntityList.do'
			                    ">
					</div>
					<div>
					登记类型：<input id="djlx" class="easyui-combotree" value="${djlx}" 
					data-options="valueField:'id',textField:'text',
					url:'<%=request.getContextPath() %>/registType/findTypeByPid.do',method:'get'" style="width:28%">
					
					<button onclick="reload()" class="btn btn-mini">
						<i class="icon icon-search"></i>查询
					</button>
					<button onclick="reset()" class="btn btn-mini">
							<i class="icon icon-repeat"></i> 重置
					</button>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<div id="datagrid" ></div> 
					</div>
				</div>
				
			</div>
		</div>
	</div>
	<div class="easyui-window " id="w" title="案件祥情 "  closed="true"  modal="true" style="width:1100px;height:455px;display:none">
		<div id="tt" title="案件详情" fit="true" >
		<div  title="案件流程信息" style="padding:1px;"  fit="true">	
			<div id="dg1" style="padding:1px;"></div>		
		</div>
		<div title="产权及权利人信息" style="padding:8px;"> 
					<div style="height:100px;padding:8px;">
						<table id="dg2" title="产权信息 " class="easyui-datagrid "  fit="true" style="margin: 0 0;">
							<thead>
								<tr>
									<th field="bdcdyh" width="300px">不动产单元号</th>
									<th field="djzh" width="200px">登记字号</th>
						 			<th field="tdzl" width="300px">坐落</th>
									<th field="cqmj" width="200px">产权面积</th>
								</tr>
							</thead>
						</table>
					</div>
					<div style="height:220px;padding:8px;">
						<table id="dg3" title="权利人信息 " class="easyui-datagrid "  fit="true" style="margin: 0 0;">
							<thead>
								<tr>
									<th field="qlr" width="240px">权利人</th>
									<th field="qlrlb" width="170px">权利人类别</th>
						 			<th field="qlrzjlx" width="170px">权利人证件类型</th>
									<th field="qlrzjbh" width="170px">权利人证件编号</th>
									<th field="szfe" width="170px">份额</th>
									<th field="zt" width="100px">状态</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<div title="审批信息" style="padding:20px;">
					<div class="row-fluid" >
						<div class="span4">
							<table class="sp_tb">
								<thead>
									<tr>
										<th width="20%" ></th>
										<th ></th>
									</tr>
								</thead>
								<tbody >
									<tr>
										<td>初审人</td>
										<td>
											<input id="csryxm" class="easyui-textbox" readonly="readonly">
										</td>
									</tr>
									<tr>
										<td>初审时间</td>
										<td >
											<input class="easyui-textbox" id="csjssj" readonly="readonly">
										</td>
									</tr>
									<tr>
										<td>初审意见</td>
										<td  class="sp_yj" >
											<textarea rows="" cols="" id="csyj" readonly="readonly">
											</textarea>
<!-- 												<input id="csyj" style="width: 264px;height: 250px" class="easyui-textbox"> -->
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="span4">
							<table class="sp_tb" >
								<thead>
									<tr>
										<th width="20%" ></th>
										<th ></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>审核人</td>
										<td>
											<input id="shryxm" class="easyui-textbox" readonly="readonly">
										</td>
									</tr>
									<tr>
										<td>审核时间</td>
										<td>
											<input id="shjssj" class="easyui-textbox" readonly="readonly">
										</td>
									</tr>
									<tr>
										<td>审核意见</td>
										<td class="sp_yj">
											<textarea rows="" cols="" id="shyj" readonly="readonly"></textarea>
<!-- 											<input id="shyj" style="width: 264px;height: 250px" class="easyui-textbox"> -->
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="span4">
							<table class="sp_tb" >
								<thead>
									<tr>
										<th width="20%" ></th>
										<th ></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>审批人</td>
										<td >
											<input id="spryxm" class="easyui-textbox" readonly="readonly">
										</td>
									</tr>
									<tr>
										<td>审批时间</td>
										<td >
											<input id="spjssj" class="easyui-textbox" readonly="readonly">
										</td>
									</tr>
									<tr>
										<td>审批意见</td>
										<td class="sp_yj">
											<textarea rows="" cols="" style="" id="spyj" readonly="readonly"></textarea>
<!-- 											<input id="spyj" style="width: 264px;height: 250px" class="easyui-textbox"> -->
										</td>
									</tr>
								</tbody>
							</table>
						</div>
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
<%-- <script type="text/javascript"
	src="<%=request.getContextPath()%>/easyui/datagrid-groupview.js"></script> --%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/easyui/easyui.extends.validate.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
$(function(){
	initDataGrid();
	
	 $("#dg1").datagrid({
		method:'post',
		url:'',
		nowrap: false, //是否在一行显示数据
		striped: true, //是否显示斑马线
		collapsible:false,//是否显示可折叠按钮
		fitColumns:true,
		rownumbers:true,
		singleSelect:true,
		columns:[[ 
					{title:'案件编号',field:'ajbh',width:'20%' }, 
					{title:'登记类型',field:'djlx',width:'15%' },  
					{title:'登记字号',field:'djzh',width:'15%' }, 
					{title:'办理时间',field:'operateTime',width:'9%' } , 
					{title:'案件所属地区',field:'organization1',width:'8%',
						formatter:function(value,rec){
		        			return value.orgName;
		            	},sorter:function(a,b){
		            		return a.orgCode>b.orgCode?1:-1;
		            	}
		        	}
		        	,
		        	{title:'案件办理地区',field:'dept',width:'8%',
						formatter:function(value,rec){
							 return value.orgName;   
		            	}
		        	}, {title:'已办环节',field:'processNode',width:'9%' }
				] ],
	 });
	
	 $('#tt').tabs();
}); 
</script>
<script type="text/javascript">
	function initDataGrid(){
		var startTime = $("#startTime").datebox("getValue");
		var endTime = $("#endTime").datebox("getValue");
		var processNode = $("#processNode").combobox("getValue");
		var operateOrgCode = $("#orgCode").combobox("getValue");
		var ajbh = $("#ajbh").textbox("getValue");
		var djzh = $("#djzh").textbox("getValue");
		var oprid = $('#djlx').combotree('getValue');
		$('#datagrid').datagrid({
			title:'统计信息',//标题
			//iconCls: 'icon-save', //一个css类，将提供一个背景图片作为标题图标
			//height:350,
			nowrap: false, //是否在一行显示数据
			striped: true, //是否显示斑马线
			collapsible:true,//是否显示可折叠按钮
			url:'<%=request.getContextPath()%>/case/findTheLatestData.do', //从远程请求数据的地址
			queryParams:{
				startTime:startTime,
				endTime:endTime,
				processNode:processNode,
				operateOrgCode:operateOrgCode,
				djlx:'${djlx}',
// 				djlx:djlx,
				ajbh:ajbh,
				djzh:djzh,
				oprid:oprid
			},
			//sortName: 'organization', //定义哪一列可以排序
			//sortOrder: 'desc', //定义列排序的方式，递增（asc）或递减（desc）
			//remoteSort: false,//false可以按从大到小或从小到大排序
			//idField: 'organization', //指定哪些字段时标识字段
			//frozenColumns: [[//与columns属性相通，但这些列将固定在左侧，不得变动。
			fitColumns: true,
			columns:[[ 
				{title:'案件编号',field:'ajbh',width:'20%' }, 
				{title:'登记类型',field:'djlx',width:'15%' },  
				{title:'登记字号',field:'djzh',width:'15%' }, 
				{title:'业务办理人',field:'operator',width:'10%' }, 
				{title:'办理环节',field:'processNode',width:'10%' }, 
				{title:'办理时间',field:'operateTime',width:'9%' } , 
				{title:'案件所属地区',field:'organization1',width:'8%',
					formatter:function(value,rec){
	        			return value.orgName;
	            	},sorter:function(a,b){
	            		return a.orgCode>b.orgCode?1:-1;
	            	}
	        	}
	        	,
	        	{title:'登记机构',field:'dept',width:'9%',
					formatter:function(value,rec){
						 return value.orgName;   
	            	}
	        	} 
			] ],
			onDblClickRow:function(rowIndex,rowData){loadDetail(rowIndex,rowData)},
	        pagination: true, //是否显示底部分页工具栏
	        rownumbers: true,
	       	pageNumber:1,
	        pageList: [15,50],
	        pageSize:15
		});
	}
				
</script> 
<script type="text/javascript">
	function reload(){
		initDataGrid();
	}
	
	function loadDetail(rowIndex,rowData){
		$('#w').window('open');
	    $("#dg1").datagrid({
	    	queryParams:{"ajbh":rowData.ajbh},
	    	url:"<%=request.getContextPath()%>/case/findEntityList.do"
	    });
	    $("#dg2").datagrid({
			 queryParams:{"ywbh":rowData.ajbh},
			 url:"<%=request.getContextPath()%>/case/findCq.do"
		 });
	    $("#dg3").datagrid({
			 queryParams:{"ywbh":rowData.ajbh},
			 url:"<%=request.getContextPath()%>/case/findQlr.do"
		 });

		$.ajax({ 
		data:{"ywbh":rowData.ajbh},
		url:'<%=request.getContextPath()%>/case/findSpb.do',
			success:function(data){
				$.each(data,function(i,d){
					$("#csryxm").textbox("setValue",d.csryxm);
					$("#csjssj").textbox("setValue",d.csjssj);
// 					$("#csyj").textbox("setValue",d.csyj);
					$("#csyj").val(d.csyj);
					
					$("#shryxm").textbox("setValue",d.shryxm);
					$("#shjssj").textbox("setValue",d.shjssj);
// 					$("#shyj").textbox("setValue",d.shyj);
					$("#shyj").val(d.shyj);
					
					$("#spryxm").textbox("setValue",d.spryxm);
					$("#spjssj").textbox("setValue",d.spjssj);
// 					$("#spyj").textbox("setValue",d.spyj);
					$("#spyj").val(d.spyj);
				})
			}
		});
	}
	function reset(){
		$("#startTime").datebox("setValue","");
		$("#endTime").datebox("setValue","");
		$("#djzh").textbox("setValue","");
		$("#ajbh").textbox("setValue","");
		$("#orgCode").combobox("setValue","");
		$("#processNode").combobox("setValue","");
		$("#djlx").combotree("setValue","");
	}
</script>
</html>