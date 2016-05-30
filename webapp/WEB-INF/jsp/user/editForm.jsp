<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/include/tags.jspf"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html lang="kr">
		<head>
			<%@ include file="/include/header.jspf"%>
		</head>
		<body>
			<%@ include file="/include/navigation.jspf"%>
			<h1>회원가입</h1>
			<!-- spring form tag -->
			<form:form name="user" modelAttribute="user" action="/users/${user.email}/" method="put">
				<div class="form-group">
					<label for="email">${user.email}</label>
				</div>
				<div class="form-group">
					<label for="name">${usr.name}</label>
				</div>
				<div class="form-group">
					<label for="password">비밀번호</label>
					<form:password path="password" cssClass="form-control" />
					<form:errors path="password" cssClass="error" />
					<button type="submit" class="btn btn-success clearfix pull-right">회원가입</button>
				</div>
			</form:form>
			<%-- <%@ include file="/include/footer.jspf"%> --%>
			<script src="https://code.getmdl.io/1.1.3/material.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/dialog-polyfill/0.4.3/dialog-polyfill.min.js"></script>
			<script src="https://cdn.tinymce.com/4/tinymce.min.js"></script>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
		</body>
</html>
