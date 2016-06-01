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
	<h1>프로젝트 수정/삭제</h1>
	<!-- spring form tag -->
	<div class="mdl-dialog__content">
        <form:form action="/projects/{projectId}/edit" name="project" modelAttribute="project" method="put">
          <div class="mdl-textfield mdl-js-textfield">
            <form:input path="name" class="mdl-textfield__input" />
            <form:errors path="name" class="error" id="new-issue__title"/>
            <label class="mdl-textfield__label" for="new-issue__title"><strong>${project.name}</strong></label>
          </div>
	      <div class="mdl-dialog__actions mdl-dialog__actions">
	        <button type="submit" class="mdl-button close">수정하기 </button>
	      </div>
        </form:form>
      </div>
      
	<%-- <%@ include file="/include/footer.jspf"%> --%>
	<script src="https://code.getmdl.io/1.1.3/material.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/dialog-polyfill/0.4.3/dialog-polyfill.min.js"></script>
	<script src="https://cdn.tinymce.com/4/tinymce.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
</body>
</html>
