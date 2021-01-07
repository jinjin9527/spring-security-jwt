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

$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

function getUrlParam(key) {
	var href = window.location.href;
	var url = href.split("?");
	if(url.length <= 1){
		return "";
	}
	var params = url[1].split("&");

	for(var i=0; i<params.length; i++){
		var param = params[i].split("=");
		if(key == param[0]){
			return param[1];
		}
	}
}

$.ajaxSetup({
	cache : false,
	error : function(xhr, textStatus, errorThrown) {
		var msg = xhr.responseText;
		var response = JSON.parse(msg);
		var code = response.code;
		var message = response.message;
		localStorage.removeItem("token");
		if (code == 400) {
		    console.log("400 Bad Request : " + message);
		    location.href = '/';
		} else if (code == 401) {
			console.log("401 Unauthorized : " + message);
			location.href = '/';
		} else if (code == 403) {
			console.log("403 Forbidden : " + message);
			location.href = '/';
		} else if (code == 500) {
			console.log('500 Internal Server Error : ' + message);
			location.href = '/';
		}
	}
});

function tokencheck(param){

    var token = localStorage.getItem("token");
    if (param ==2) {
        token = "";
    }
	$.ajax({
		type : 'post',
		url : '/tokencheck',
        headers : {
            "token" : token
        },
		success : function(data) {
		    console.log(data);
		    $("#tokencheck_result").text(data["code"] + " " + data["message"]);
		}
	});
}