<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ include file="common/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>首页</title>
<%@ include file="common/meta.jsp" %>
<%@ include file="common/include_base_style.jsp" %>

<script type="text/javascript">
$(document).ready(function(){
	//alert("456");
    $('.nav a[rel]').click(function(){
    	$('iframe').attr('src', ctx + "/" + $(this).attr('rel'));
    });
    
	// 自动根据分辨率调整iframe的大小
    window.onresize = function() {
    	autoResizeIframeHeight();
    };
    window.onresize();

});
/**
 * 自动根据分辨率调整iframe的大小
 */
function autoResizeIframeHeight() {
	$('iframe').height(document.documentElement.clientHeight - 60);
}
</script>
</head>
<body>
<p>Hello World!</p>
<ul class="nav">

</ul>
<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
	    <div class="container-fluid">
	      <a href="#" class="brand">Activiti Explorer</a>
	      <div class="nav-collapse">
	        <ul class="nav">
				<li><a href="#" rel="process-list">流程定义</a></li>
				<li><a href="#" rel="task-list">代办任务</a></li>
	        </ul>
	      </div>
          <ul class="nav pull-right">
			<li><a id="changePwd" href="#"><i class="icon-wrench icon-black"></i>修改密码</a></li>
			<li><a id="loginOut" href="${ctx }/logout"><i class="icon-eject icon-black"></i>安全退出</a></li>
          </ul>
	    </div>
	</div>
</div>

<div>
	<iframe id="mainIframe" name="mainIframe" src="${ctx }/welcome" class="module-iframe" scrolling="auto" frameborder="0" style="width:100%;"></iframe>
</div>
</body>
</html>