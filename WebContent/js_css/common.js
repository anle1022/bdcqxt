jQuery(function($){  
    // 备份jquery的ajax方法    
    var _ajax=$.ajax;  
    // 重写ajax方法，先判断登录在执行success函数   
    $.ajax=function(opt){  
        var _success = opt && opt.success || function(a, b){};  
        var _opt = $.extend(opt, {  
            success:function(data, textStatus,XMLHttpRequest){ 
            	var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
                // 如果后台将请求重定向到了登录页，则data里面存放的就是登录页的源码，这里需要找到data是登录页的证据(标记) 
                if(sessionstatus == "timeout") {  
                    window.location.href= getProjectName()+"/login.do";  
                    return;  
                }  
                _success(data, textStatus);    
            }    
        });  
        _ajax(_opt);  
    };  
});  

function getRootPath(){  
    //获取当前网址，如： http://localhost:8083/proj/meun.jsp  
    var curWwwPath = window.document.location.href;  
    //获取主机地址之后的目录，如： proj/meun.jsp  
    var pathName = window.document.location.pathname;  
    var pos = curWwwPath.indexOf(pathName);  
    //获取主机地址，如： http://localhost:8083  
    var localhostPath = curWwwPath.substring(0, pos);  
    //获取带"/"的项目名，如：/proj  
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/')+1);  
    return(localhostPath + projectName);  
}  

function getProjectName(){
	 var pathName = window.document.location.pathname;  
	 var projectName = pathName.substring(0, pathName.substr(1).indexOf('/')+1); 
	 return projectName;
}

function windowParent(self_window){
	var myWindow = self_window.parent;
	if(myWindow != self_window){
		windowParent(myWindow);
	}else{
		return myWindow;
	}
}

var MaskUtil = (function(){    
    
    var $mask,$maskMsg;    
        
    var defMsg = '正在处理，请稍待。。。';    
        
    function init(){    
        if(!$mask){    
            $mask = $("<div class=\"datagrid-mask mymask\"></div>").appendTo("body");    
        }    
        if(!$maskMsg){    
            $maskMsg = $("<div class=\"datagrid-mask-msg mymask\">"+defMsg+"</div>")    
                .appendTo("body").css({'font-size':'12px'});  
        }    
            
        $mask.css({width:"100%",height:$(document).height()});    
            
        $maskMsg.css({    
            left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2,  
        });     
                    
    }    
        
    return {    
        mask:function(msg){    
            init();    
            $mask.show();    
            $maskMsg.html(msg||defMsg).show();    
        }    
        ,unmask:function(){    
            $mask.hide();    
            $maskMsg.hide();    
        }    
    }    
        
}());  