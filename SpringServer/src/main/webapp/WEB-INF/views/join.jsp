<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
<h2>회원가입</h2>
<form action="member/join" method="post" enctype="multipart/form-data" id="memberform">
		이메일:<input type="text" name="email" id="email"/>
		<input type="button" value="이메일 중복확인" id="emailcheckbtn"/>
		<br/>
		비밀번호:<input type="password" name="pw"/>
		<br/>
		닉네임:<input type="text" name="nickname" id="nickname"/>
		<input type="button" value="닉네임 중복확인" id="nicknamecheckbtn"/>
		<br/>
		이미지:<input type="file" name='profile' accept="image/*"/>
		<br/>
		
		<input type="button" value="회원가입" id="joinbtn" onclick="join()"/>
</form>

</body>

<script>
//중복체크를 위한 프로퍼티
var emailcheck = false;
var nicknamecheck = false;
// 회원가입
function join(){
	
	//emailcheck가 true가 아니라면
	if(emailcheck != true){
		alert("이메일 중복체크를 하세요.")
		document.getElementById("email").focus();
	}
	//nicknamecheck가 true가 아니라면
	else if(!nicknamecheck){
		alert("닉네임 중복체크를 하세요.")
		document.getElementById("nickname").focus();
	}
	//중복체크가 모두 되었을 때
	else{
		//폼에 입력된 데이터 가져오기
		var formData = new FormData(document.getElementById("memberform"));
		//ajax로 데이터를 전송해서 결과를 받기
		var xhr = new XMLHttpRequest();
		//요청 생성
		xhr.open("POST", "member/join", true);
		//전송
		xhr.send(formData);
		//응답 받기
		xhr.onload = function(){
			if(xhr.status == 200){
				alert("회원가입 성공");
				location.href="/";
			}else{
				alert("데이터 응답 실패");
			}
		}
	}
};


//이메일 중복체크
document.getElementById("emailcheckbtn").addEventListener('click', function(e){
		//폼에 입력된 데이터 가져오기
		var formData = new FormData(document.getElementById("memberform"));
		//ajax로 데이터를 전송해서 결과를 받기
		var xhr = new XMLHttpRequest();
		//요청 생성
		xhr.open("POST", "member/emailcheck", true);
		//전송
		xhr.send(formData);
		//응답 받기
		xhr.onload = function(){
			if(xhr.status == 200){
				//alert(xhr.responseText);
				var data = JSON.parse(xhr.responseText);
				if(data.result == true){
					alert(data.msg);
					emailcheck = true;
				}else{
					alert(data.msg);
					emailcheck = false;
				}
			}else{
				alert("데이터 응답 실패");
			}
		}
})

//닉네임 중복체크
document.getElementById("nicknamecheckbtn").addEventListener('click', function(e){
		//폼에 입력된 데이터 가져오기
		var formData = new FormData(document.getElementById("memberform"));
		//ajax로 데이터를 전송해서 결과를 받기
		var xhr = new XMLHttpRequest();
		//요청 생성
		xhr.open("POST", "member/nicknamecheck", true);
		//전송
		xhr.send(formData);
		//응답 받기
		xhr.onload = function(){
			if(xhr.status == 200){
				//alert(xhr.responseText);
				var data = JSON.parse(xhr.responseText);
				if(data.result == true){
					alert(data.msg);
					nicknamecheck = true;
				}else{
					alert(data.msg);
					nicknamecheck = false;
				}
			}else{
				alert("데이터 응답 실패");
			}
		}
})

document.getElementById()
</script>
</html>