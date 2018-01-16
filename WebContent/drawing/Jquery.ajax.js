var jq = jQuery.noConflict();
jq(document).ajaxComplete(function(event,xhr,optin){
  			var flag = xhr.responseText;
  			flag = flag.substring(1,2)-1;
  			 //alert("ajax complete" +flag);  
  			 //window.top.location.href='login.jsp';  
  			 //alert(xhr.responseText)
		     if(flag == 0){  
		         window.top.location.href='login.jsp';  
		     }  
}); 