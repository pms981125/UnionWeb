/*const btn = document.querySelector('#logOut');
	btn.addEventListener('click', e => {
	location.href ="logIn.jsp";
	//location.replace("logIn.jsp");
});*/

function logOut() {
	let f = document.createElement('form');
	
    f.setAttribute('method', 'post');
	f.setAttribute('action', 'logOut.do');
	document.body.appendChild(f);
	f.submit();
}
		
function logIn(id, password){
    let f = document.createElement('form');
	    
    let obj;
    obj = document.createElement('input');
    obj.setAttribute('type', 'hidden');
    obj.setAttribute('name', 'id');
    obj.setAttribute('value', id);
	    
    let obj2;
    obj2 = document.createElement('input');
    obj2.setAttribute('type', 'hidden');
    obj2.setAttribute('name', 'password');
    obj2.setAttribute('value', password);
		    
    f.appendChild(obj);
    f.appendChild(obj2);
    f.setAttribute('method', 'post');
    f.setAttribute('action', 'logIn.do');
    document.body.appendChild(f);
    f.submit();
}
		
function getChattingRoomList(id){
    let f = document.createElement('form');
    
    let obj;
    obj = document.createElement('input');
    obj.setAttribute('type', 'hidden');
    obj.setAttribute('name', 'id');
    obj.setAttribute('value', id);
			    
    f.appendChild(obj);
    f.setAttribute('method', 'post');
    f.setAttribute('action', 'getChattingRoomList.do');
    document.body.appendChild(f);
    f.submit();
}
		
function goSettingPage(id){
    let f = document.createElement('form');
			    
    let obj;
    obj = document.createElement('input');
    obj.setAttribute('type', 'hidden');
    obj.setAttribute('name', 'id');
    obj.setAttribute('value', id);
			    
    f.appendChild(obj);
    f.setAttribute('method', 'post');
    f.setAttribute('action', 'goSettingPage.do');
    document.body.appendChild(f);
    f.submit();
}
		
function getBoardList(id){
	let f = document.createElement('form');
			    
	let obj;
    obj = document.createElement('input');
    obj.setAttribute('type', 'hidden');
    obj.setAttribute('name', 'id');
    obj.setAttribute('value', id);
			    
    f.appendChild(obj);
    f.setAttribute('method', 'post');
    f.setAttribute('action', 'getBoardList.do');
	document.body.appendChild(f);
	f.submit();
}
		
function getMemberList(id){
	let f = document.createElement('form');
			    
	let obj;
    obj = document.createElement('input');
    obj.setAttribute('type', 'hidden');
    obj.setAttribute('name', 'id');
    obj.setAttribute('value', id);
			    
    f.appendChild(obj);
    f.setAttribute('method', 'post');
    f.setAttribute('action', 'getMemberList.do');
	document.body.appendChild(f);
	f.submit();
}
		
function getReservationList(id){
	let f = document.createElement('form');
			    
	let obj;
    obj = document.createElement('input');
    obj.setAttribute('type', 'hidden');
    obj.setAttribute('name', 'id');
    obj.setAttribute('value', id);
			    
    f.appendChild(obj);
    f.setAttribute('method', 'post');
    f.setAttribute('action', 'getReservationList.do');
	document.body.appendChild(f);
	f.submit();
}