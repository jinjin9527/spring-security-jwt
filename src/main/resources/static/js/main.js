function logout(){
	$.ajax({
		type : 'get',
		url : '/logout',
		success : function(data) {
			localStorage.removeItem("token");
			location.href='/';
		}
	});
}