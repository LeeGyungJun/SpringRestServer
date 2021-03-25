<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<h2>로그인</h2>
<form action="member/login" method="post" id="loginform">
		이메일:<input type="text" name="email" id="email"/>
		<br/>
		비밀번호:<input type="password" name="pw"/>
		<br/>
		<input type="button" value="로그인" id="loginbtn" onclick="login()"/>
</form>

</body>
<script>

//로그인
function login(){
	//폼에 입력된 데이터 가져오기
	var formData = new FormData(document.getElementById("loginform"));
	//ajax로 데이터를 전송해서 결과를 받기
	var xhr = new XMLHttpRequest();
	//요청 생성
	xhr.open("POST", "member/login", true);
	//전송
	xhr.send(formData);
	//응답 받기
	xhr.onload = function(){
		var data = JSON.parse(xhr.responseText);
		if(xhr.status == 200){
			alert(data.msg);
			location.href="/";
		}else{
			alert(data.msg);
		}
	}
};

</script>
</html>