<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>데이터 수정</title>
</head>
<body>
	<form method="post" enctype="multipart/form-data" id="updateform">
		아이템 아이디:<input type="text" id="itemid" name="itemid" readonly="readonly" /><br/>
		아이템 이름<input type="text" id="itemname" name="itemname" /><br/>
		아이템 가격<input type="text" id="price" name="price" /><br/>
		설명<input type="text" id="description" name="description" /><br/>
		새로운 이미지<input type="file" id="pictureurlfile" name="pictureurlfile" accept="image/*"/><br />
		<input type="hidden" id="pictureurl" name="pictureurl" />
		<input type="button" value="수정" id="updatebtn" onclick="update()"/>
	</form>
	
</body>

<script>
load();
function load(){
	//폼에 입력된 데이터 가져오기
	var formData = new FormData(document.getElementById("updateform"));
	//ajax로 데이터를 전송해서 결과를 받기
	var xhr = new XMLHttpRequest();
	var data = window.location.pathname;
	var dataSplit=data.split("/");
	//요청 생성
	xhr.open("GET", "/item/update-view/"+dataSplit[dataSplit.length-1], true);
	//전송
	xhr.send(formData);
	//응답 받기
	xhr.onload = function(){
			var data = JSON.parse(xhr.responseText);
			var itemid = data.itemid;
			var itemname = data.itemname;
			var price = data.price;
			var description = data.description;
			var pictureurl= data.pictureurl;
			console.log(pictureurl);
			document.getElementById("itemid").value = itemid;
			document.getElementById("itemname").value = itemname;
			document.getElementById("price").value = price;
			document.getElementById("description").value = description;
			document.getElementById("pictureurl").value = pictureurl;
	}
}

function update(){
	//폼에 입력된 데이터 가져오기
	var formData = new FormData(document.getElementById("updateform"));
	//ajax로 데이터를 전송해서 결과를 받기
	var xhr = new XMLHttpRequest();
	//요청 생성
	xhr.open("POST", "/item/update", true);
	//전송
	xhr.send(formData);
	//응답 받기
	xhr.onload = function(){
		if(xhr.status == 200){
			alert(xhr.responseText);
			var data = JSON.parse(xhr.responseText);
			if(data.result == true){
				alert("상품 수정 성공");
				location.href="/item/list";
			}else{
				alert("상품 수정 실패");
			}
		}
	}
}
</script>

</html>