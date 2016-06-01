<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/tags.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/include/header.jspf"%>
</head>
<body>
	<div
		class="issues issues--wrap mdl-layout mdl-js-layout has-drawer is-upgraded">
		<%@ include file="/include/navigation.jspf"%>
		<main class="mdl-layout__content">
		<div class="issues__posts mdl-grid">
			<div class="issues-card-wide mdl-card mdl-shadow--2dp">
				<div class="mdl-card__title mdl-shadow--2dp">
					<h2 class="mdl-card__title-text mdl-color-text--grey-800">수정권한 없음.</h2>
				</div>
				<button class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored show-modal">
					<i class="material-icons">add</i>
				</button>
				<div class="mdl-card__supporting-text">
					<ul class="issue-list mdl-list">
							<li class="mdl-list__item mdl-list__item--two-line">
							<span class="mdl-list__item-primary-content"> <a href="/"><strong>홈으로 가기</strong></a>
							</span>
							<span class="mdl-list__item-sub-title">
								해당 프로젝트에 참여자가 아니어서 프로젝트 수정 권한이 없습니다. 		
							</span>
							</li>
					</ul>
				</div>
			</div>
		</div>
		<footer class="mdl-mini-footer">
          <div class="mdl-mini-footer--left-section">
            <div class="mdl-logo mdl-color-text--grey-600">Proudly powered by <a href="https://github.com/Byeol">Byeol</a></div>
          </div>
        </footer>
		</main>
	</div>
	<dialog class="mdl-dialog new-issue">
      <div class="pr">
        <i class="material-icons">create</i>
      </div>
      <div class="mdl-dialog__content">
        <form action="/projects">
          <div class="mdl-textfield mdl-js-textfield">
            <input class="mdl-textfield__input" type="text" id="new-issue__title">
            <label class="mdl-textfield__label" for="new-issue__title"><strong>Title</strong></label>
          </div>
        </form>
      </div>
      <div class="mdl-dialog__actions mdl-dialog__actions">
        <button type="button" class="mdl-button close">Submit new project</button>
      </div>
    </dialog>
	<%@ include file="/include/footer.jspf"%>
</body>
</html>