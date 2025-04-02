<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<title>친구 이름으로 검색</title>
<script type="text/javascript">
	
	$(function(){
		// 이름을 입력하고 blur 되었을 때
		$("#friendName").keyup(function(){
			searchFriend($("#friendName").val());
		})
	});
	
	function searchFriend(fname){
		
		
		// 검색어가 1글자 이상일 때
		if(fname.length > 0){
			$.ajax({
	              url: "<%=request.getContextPath() %>/findFriendByname.do", // 데이터가 송수신될 서버의 주소
	              type: "GET", // 통신 방식 (GET, POST, PUT, DELETE)
				  data: { // 서버에 전송할 데이터
					  'searchName' : fname
				  },
	              dataType: "json", // 수신받을 데이터의 타입 (MIME TYPE)
				  async: false,
	              success: function(data){ // 통신이 성공하면 수행할 함수
	            	  console.log(data);
	              	  outputFriends(data);
	              },
	              error: function(){},
	              complete: function(){}
	        });
		}
	}
	
	function outputFriends(data){
		
		let output = '';
 		// console.log(data[0].friendName)
		$.each(data, function(index, item){
			console.log(item);
// 			<div class="card">
// 			  <div class="card-body">
// 			    <h4 class="card-title">Card title</h4>
// 			    <p class="card-text">Some example text. Some example text.</p>
// 			    <a href="#" class="card-link">Card link</a>
// 			    <a href="#" class="card-link">Another link</a>
// 			  </div>
// 			</div>
			output += `<div class="card"><div class="card-body">`;
			output += `<h4 class="card-title">\${item.friendName}</h4>`;
			output += `<a href="#" class="card-link">\${item.mobile}</a>`;
			output += `<p class="card-text">\${item.addr}</p>`;
			output += `</div></div>`;
		});
		$(".friends").html(output);
	}
</script>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="container mt-3">
		<h1>친구 이름 검색</h1>
		<div class="mb-3 mt-3">
	      <label for="friendName">이름 :</label>
	      <input type="text" class="form-control" id="friendName" placeholder="검색할 친구의 이름을 입력하세요..." name="friendName">
	    </div>
	    <div class="friends"></div>	
	</div>
</body>
</html>