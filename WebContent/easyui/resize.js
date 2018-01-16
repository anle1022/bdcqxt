var settime = null;
function redraw(){
$('#wrap').layout('resize');
$('#subWrap').layout('panel', 'north').panel('resize',{width:$('#subWrap').width()});
$('#subWrap').layout('panel', 'center').panel('resize',{width:$('#subWrap').width()});
$('#subWrap').layout('resize');
}
$(function(){
$(window).resize(function(){
if(settime != null)
clearTimeout(settime);
settime=setTimeout('redraw()',100);
});
var p1 = $('body').layout('panel','west').panel({
onCollapse:function(){
if(settime != null)
clearTimeout(settime);
settime=setTimeout('redraw()',100);
},
onExpand:function(){
if(settime != null)
clearTimeout(settime);
settime=setTimeout('redraw()',100);
},
onResize:function(width,height){
if(settime != null)
clearTimeout(settime);
settime=setTimeout('redraw()',100);
}
});
var p2 = $('body').layout('panel','east').panel({
onCollapse:function(){
if(settime != null)
clearTimeout(settime);
settime=setTimeout('redraw()',100);
},
onExpand:function(){
if(settime != null)
clearTimeout(settime);
settime=setTimeout('redraw()',100);
},
onResize:function(width,height){
if(settime != null)
clearTimeout(settime);
settime=setTimeout('redraw()',100);
}
});
});