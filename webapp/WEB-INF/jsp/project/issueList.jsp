<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/tags.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="kr">
<head>
<%@ include file="/include/header.jspf"%>
</head>
<body>
	<%-- menu bar--%>
	<div
		class="issues issues--wrap mdl-layout mdl-js-layout has-drawer is-upgraded">
		<%@ include file="/include/navigation.jspf"%>
		<main class="mdl-layout__content">
		<div class="issues__posts mdl-grid">
				 <%-- <buttion class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored><a href="projects/${project.projectId}/join"><i class="material-icons">join</i></buttion>  --> --%>
			<div class="issues-card-wide mdl-card mdl-shadow--2dp">
				<div class="mdl-card__title mdl-shadow--2dp">
					<h2 class="mdl-card__title-text mdl-color-text--grey-800">${project.name}</h2>
					<span class="editText"><a href="/projects/${project.projectId}/edit">프로젝트 수정</a></span>
					<span class= "joinText"><a href="/projects/${project.projectId}/join">참여하기</a></span>
				</div>
				<button
					class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored show-modal">
					<i class="material-icons">add</i>
				</button>
				<div class="mdl-card__supporting-text">
					<ul class="issue-list mdl-list">
						
						<li class="mdl-list__item mdl-list__item--two-line">
						<span class="mdl-list__item-primary-content"> 
							<strong>Members</strong></a> 
							<c:forEach items="${members}" var="member">
								<span class="member">${member.name}</span>
								<span class="member">${member.email}</span>
							</c:forEach>
						 <span class="mdl-list__item-secondary-content">
									
						</span></li>
						<c:if test="${empty issues}">
							<li class="mdl-list__item mdl-list__item--two-line"><span
								class="mdl-list__item-primary-content"> <a href="/"><strong>등록된 이슈가 없습니다.</strong></a>
							</span></li>
						</c:if>
						<c:if test="${not empty issues}">
						<c:forEach items="${issues}" var="each">
							<li class="mdl-list__item mdl-list__item--two-line">
							<span class="mdl-list__item-primary-content"> 
								<a href="#"> <img class="mdl-list__item-avatar" height="48" width="48" src="#" alt="@javajigi"></a>
	 								<a href="/projects/${project.projectId}/issues/${each.issueId}">
	 									<strong>${each.title}</strong></a> 
	 									<span class="mdl-list__item-sub-title">#${each.issueId} opened by ${each.writer} ${each.user_email}</span>
									<!-- 	<relative-time datetime="2016-01-12T08:08:59Z"
											title="2016년 1월 12일 오후 5:08 GMT+9">on 12 Jan</relative-time> --> 
										<!-- <a href="https://github.com/javajigi/slipp/issues?q=is%3Aissue+is%3Aopen+author%3Ajavajigi"
										aria-label="View all issues opened by javajigi"
										class="tooltipped tooltipped-s muted-link">writer </a> -->
								
							</span> 
							<span class="mdl-list__item-secondary-content">
									<div class="material-icons mdl-badge mdl-badge--overlap"
										data-badge="0">comment</div>
							</span></li>
						</c:forEach>						
						</c:if>
						<!-- <li class="mdl-list__item mdl-list__item--two-line"><span
							class="mdl-list__item-primary-content"> <a
								href="https://github.com/javajigi"> <img
									class="mdl-list__item-avatar" height="48" width="48"
									src="https://avatars2.githubusercontent.com/u/520500?v=3&amp;s=96"
									alt="@javajigi">
							</a> <a href="issue.html"><strong>질문 간에 답변을 이동할 수 있어야
										한다.</strong></a> <span class="mdl-list__item-sub-title">#259 opened
									<relative-time datetime="2016-01-12T08:08:59Z"
										title="2016년 1월 12일 오후 5:08 GMT+9">on 12 Jan</relative-time>
									by <a
									href="https://github.com/javajigi/slipp/issues?q=is%3Aissue+is%3Aopen+author%3Ajavajigi"
									aria-label="View all issues opened by javajigi"
									class="tooltipped tooltipped-s muted-link">javajigi</a>
							</span>
						</span> </span> <span class="mdl-list__item-secondary-content">
								<div class="material-icons mdl-badge mdl-badge--overlap"
									data-badge="0">comment</div>
						</span></li> -->
					</ul>
				</div>
			</div>
		</div>
		<footer class="mdl-mini-footer">
		<div class="mdl-mini-footer--left-section">
			<div class="mdl-logo mdl-color-text--grey-600">
				Proudly powered by <a href="https://github.com/Byeol">Byeol</a>
			</div>
		</div>
		</footer> </main>
		<div class="mdl-layout__obfuscator"></div>
	</div>
	<dialog class="mdl-dialog new-issue">
	<div class="pr">
		<i class="material-icons">create</i>
	</div>
	<div class="mdl-dialog__content">
	<form:form action="/projects/${project.projectId}/issues" name="issue" modelAttribute="issue" method="post">
			<div class="mdl-textfield mdl-js-textfield">
				<form:input path = "title" class="mdl-textfield__input" type="text" id="new-issue__title"/> 
				<form:errors path = "title" class="mdl-textfield__input" type="text" id="new-issue__title"/> 
				<label class="mdl-textfield__label"	for="new-issue__title"><strong>Title</strong></label>
			</div>
			<form:textarea path="contents" rows="10" class="mdl-textfield__input" id="new-issue__comment" />
			<div class="mdl-dialog__actions mdl-dialog__actions">
				<button type="submit" class="mdl-button close">Submit new issue</button>
			</div>
	</form:form>
	</div>
	</dialog>
	<!-- <script src="https://cdn.tinymce.com/4/tinymce.min.js"> -->
    <%@ include file="/include/footer.jspf" %>
</body>
</html>