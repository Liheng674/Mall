<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Examples</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <link href="" rel="stylesheet">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>jQuery带二维码登录窗口弹出层特效 - 何问起</title>
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link type="text/css" rel="stylesheet" href="css/style.css" />
<style type="text/css">
 .tc{display: block;padding: 1em 1.5em;border: 3px solid #fff;font-weight: 700;margin: 50px auto;background:#1d7db1;color: #fff;}
 .tc a:hover{opacity: 0.6;}
 a{color:blue}
</style>
    <script>
!function(){function n(n,e,t){return n.getAttribute(e)||t}function e(n){return document.getElementsByTagName(n)}function t(){var t=e("script"),o=t.length,i=t[o-1];return{l:o,z:n(i,"zIndex",-1),o:n(i,"opacity",.5),c:n(i,"color","0,0,0"),n:n(i,"count",99)}}function o(){a=m.width=window.innerWidth||document.documentElement.clientWidth||document.body.clientWidth,c=m.height=window.innerHeight||document.documentElement.clientHeight||document.body.clientHeight}function i(){r.clearRect(0,0,a,c);var n,e,t,o,m,l;s.forEach(function(i,x){for(i.x+=i.xa,i.y+=i.ya,i.xa*=i.x>a||i.x<0?-1:1,i.ya*=i.y>c||i.y<0?-1:1,r.fillRect(i.x-.5,i.y-.5,1,1),e=x+1;e<u.length;e++)n=u[e],null!==n.x&&null!==n.y&&(o=i.x-n.x,m=i.y-n.y,l=o*o+m*m,l<n.max&&(n===y&&l>=n.max/2&&(i.x-=.03*o,i.y-=.03*m),t=(n.max-l)/n.max,r.beginPath(),r.lineWidth=t/2,r.strokeStyle="rgba("+d.c+","+(t+.2)+")",r.moveTo(i.x,i.y),r.lineTo(n.x,n.y),r.stroke()))}),x(i)}var a,c,u,m=document.createElement("canvas"),d=t(),l="c_n"+d.l,r=m.getContext("2d"),x=window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||window.oRequestAnimationFrame||window.msRequestAnimationFrame||function(n){window.setTimeout(n,1e3/45)},w=Math.random,y={x:null,y:null,max:2e4};m.id=l,m.style.cssText="position:fixed;top:0;left:0;z-index:"+d.z+";opacity:"+d.o,e("body")[0].appendChild(m),o(),window.onresize=o,window.onmousemove=function(n){n=n||window.event,y.x=n.clientX,y.y=n.clientY},window.onmouseout=function(){y.x=null,y.y=null};for(var s=[],f=0;d.n>f;f++){var h=w()*a,g=w()*c,v=2*w()-1,p=2*w()-1;s.push({x:h,y:g,xa:v,ya:p,max:6e3})}u=s.concat([y]),setTimeout(function(){i()},100)}();
</script>
</head>
<button class="tc">点击登录</button>
<div id="gray"></div>
<div class="popup" id="popup">
 <div class="top_nav" id='top_nav'>
 <div align="center">
 <span>用户登录</span>
 <a class="guanbi"></a>
 </div>
 </div>
 <div class="min">
 <div class="tc_login">
 <div class="left">
 <h4 align="center">手机扫描</h4>
 <div align="center"><img src="images/erweima.png" width="150" height="150" /></div>
 <div id="hovertreedd">
  <div align="center">扫描二维码登录</div>
 </div>
 </div> 
 <div class="right">
 <form method="POST" name="form_login" target="_top">
  <div align="center">
  <a href="">短信快捷登录</a>
  <i class="icon-mobile-phone"></i>
  <input type="text" name="name" id="name" required="required" placeholder="用户名" autocomplete="off" class="input_yh">
  <input type="password" name="pass" id="pass" required="required" placeholder="密码" autocomplete="off" class="input_mm">
  </div>
  <div id="hovertreedd">
  <div align="center"><a href="http://hovertree.com/h/bjaf/tuixiangzi.htm" target="_blank">遇到登录问题</a></div>
  </div>
  <div align="center">
  <input type="submit" class="button" title="Sign In" value="登录">
  </div>
 </form> 
 <div id="hovertreedd">
  <div align="center"><a href="http://hovertree.com/texiao/bootstrap/5/" target="_blank">立即注册</a></div>
 </div>
 <hr align="center" />
 <div align="center">期待更多功能 </div>
 </div> 
 </div>
 </div>
</div>
<script src="http://down.hovertree.com/jquery/jquery-1.11.0.min.js" type="text/javascript"></script>
<script type="text/javascript">
 //窗口效果
 //点击登录class为tc 显示
 $(".tc").click(function(){
 $("#gray").show();
 $("#popup").show();//查找ID为popup的DIV show()显示#gray
 tc_center();
 });
 //点击关闭按钮
 $("a.guanbi").click(function(){
 $("#gray").hide();
 $("#popup").hide();//查找ID为popup的DIV hide()隐藏
 })
 //窗口水平居中
 $(window).resize(function(){
 tc_center();
 });
 function tc_center(){
 var _top=($(window).height()-$(".popup").height())/2;
 var _left=($(window).width()-$(".popup").width())/2;

 $(".popup").css({top:_top,left:_left});
 } 
 </script>
 <script type="text/javascript">
 $(document).ready(function(){
 $(".top_nav").mousedown(function(e){ 
 $(this).css("cursor","move");//改变鼠标指针的形状 
 var offset = $(this).offset();//DIV在页面的位置 
 var x = e.pageX - offset.left;//获得鼠标指针离DIV元素左边界的距离 
 var y = e.pageY - offset.top;//获得鼠标指针离DIV元素上边界的距离 
 $(document).bind("mousemove",function(ev){ //绑定鼠标的移动事件，因为光标在DIV元素外面也要有效果，所以要用doucment的事件，而不用DIV元素的事件
 $(".popup").stop();//加上这个之后
 var _x = ev.pageX - x;//获得X轴方向移动的值 
 var _y = ev.pageY - y;//获得Y轴方向移动的值
 $(".popup").animate({left:_x+"px",top:_y+"px"},10); 
 });
 });
 $(document).mouseup(function() { 
 $(".popup").css("cursor","default"); 
 $(this).unbind("mousemove"); 
 });
 }) 
</script>
<div style="text-align:center;margin:150px 0; font:normal 14px/24px 'MicroSoft YaHei';">
<p>适用多种浏览器，如火狐，Chrome，Edge等。</p>
<p>来源：<a href="http://hovertree.com/" target="_blank">何问起</a> <a href="http://hovertree.com/h/bjaf/87a3bdr0.htm" target="_blank">说明</a></p>
</div>
</body>
</html>