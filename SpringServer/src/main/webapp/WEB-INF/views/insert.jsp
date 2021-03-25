<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 추가</title>
</head>
<body>

	<form action="item/insert" method="post" enctype="multipart/form-data" id="itemform">
		이름:<input type="text" name="itemname"/>
		<br/>
		설명:<input type="text" name="description"/>
		<br/>
		가격:<input type="text" name="price"/>
		<br/>
		이미지:<input type="file" name='pictureurlfile' accept="image/*"/>
		<br/>
		
		<input type="button" value="데이터 삽입" id="insertbtn" onclick="insert()"/>
	</form>
	
</body>

<script>
function insert(){
	//폼에 입력된 데이터 가져오기
	var formData = new FormData(document.getElementById("itemform"));
	//ajax로 데이터를 전송해서 결과를 받기
	var xhr = new XMLHttpRequest();
	//요청 생성
	xhr.open("POST", "item/insert", true);
	//전송
	xhr.send(formData);
	//응답 받기
	xhr.onload = function(){
		if(xhr.status == 200){
			alert(xhr.responseText);
			var data = JSON.parse(xhr.responseText);
			if(data.result == true){
				alert("상품 추가 성공");
				location.href="item/list";
			}else{
				alert("상품 추가 실패");
			}
		}
	}
}
</script>

</html>