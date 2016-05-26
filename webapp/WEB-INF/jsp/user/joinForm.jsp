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
	<h1> 회원가입 </h1>
	<!-- spring form tag -->
	<form:form name="user" modelAttribute="user" action="/users/join" method="post">
                <div class="form-group">
                    <label for="email">이메일</label>
                    <form:input path="email" cssClass="form-control"/>
                    <form:errors path="email" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label for="name">이름</label>
                    <form:input path="name" cssClass="form-control"/>
                    <form:errors path="name" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label for="password">비밀번호</label>
                    <form:password path="password" cssClass="form-control"/>
                    <form:errors path="password" cssClass="error"/>
                </div>
                <button type="submit" class="btn btn-success clearfix pull-right">회원가입</button>
                <div class="clearfix" />
       </form:form>
	<%@ include file="/include/footer.jspf"%>
</body>
</html>