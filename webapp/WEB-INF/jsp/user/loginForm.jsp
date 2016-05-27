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
	<h1>Log in</h1>
	<div class="panel panel-default content-main">
		<c:if test="${loginFailed}">
			<div class="alert alert-danger" role="alert" style= "color: #FF8000;" >아이디 또는 비밀번호가 틀립니다.
				다시 로그인 해주세요.</div>
		</c:if>
		<form:form name="question" modelAttribute="user" method="post" action="/users/login">
			<div class="form-group">
				<label for="email">이메일</label>
				<form:input path="email" cssClass="form-control" />
				<form:errors path="email" cssClass="error" />
			</div>
			<form:hidden path="name" value="name"/>
			<div class="form-group">
				<label for="password">비밀번호</label>
				<form:password path="password" cssClass="form-control" />
				<form:errors path="password" cssClass="error" />
				<button type="submit" class="btn btn-success clearfix pull-right">Log in</button>
			</div>
			</form:form>
	</div>
	<script src="https://code.getmdl.io/1.1.3/material.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/dialog-polyfill/0.4.3/dialog-polyfill.min.js"></script>
	<script src="https://cdn.tinymce.com/4/tinymce.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
</body>
</html>
