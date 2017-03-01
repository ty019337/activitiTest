<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/global.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="common/meta.jsp" %>
	<%@ include file="common/include_base_style.jsp" %>
<title>process list</title>
</head>
<body>
	<fieldset>
		<legend>部署流程资源</legend>
		<form action="${ctx }/deploy" method="post" enctype="multipart/form-data">
			<input type="file" name="file"/>
			<input type="submit" name="sbt" value="提交"/>
		</form>
	</fieldset>
	<table width="100%" class="table table-bordered table-hover table-condensed">
		<tr>
			<td>流程定义ID</td>
			<td>部署ID</td>
			<td>流程定义名称</td>
			<td>流程定义key</td>
			<td>版本号</td>
			<td>XML资源名称</td>
			<td>图片资源名称</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${processDefinitionList}" var="pd">
			<tr>
				<td>${pd.id}</td>
				<td>${pd.deploymentId}</td>
				<td>${pd.name }</td>
				<td>${pd.key }</td>
				<td>${pd.version }</td>
				<td><a target="_blank" href="${ctx }/getresource?id=${pd.id }&name=${pd.resourceName}">${pd.resourceName }</a></td>
				<td>${pd.diagramResourceName }</td>
				<td>
					<a target="_blank" href="${ctx }/delete-process?deploymentId=${pd.deploymentId}">删除</a>
					<a href="${ctx }/task-start?processDefinitionId=${pd.id }">启动流程</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>

