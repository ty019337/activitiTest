<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>task-list</p>
	<table>
		<c:forEach var="task" items="${taskList }">
		<tr>
			<td>${task.id }</td>
			<td>${task.name }</td>
			<td>${task.owner }</td>
			<td><a href="${ctx }/task/form?taskId=${task.id}">办理</a></td>
			<td><a href="${ctx }/task/delete?taskId=${task.id }">删除</a></td>
		</tr>
		</c:forEach>
	</table>

</body>
</html>