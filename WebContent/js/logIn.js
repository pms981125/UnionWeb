// 스택 추가
history.pushState(null, null, location.href); 

// 뒤로라기 이벤트감지 -> 현재페이지로 이동
window.onpopstate = function() {
	history.go(1); /* 작동 안함 */
}