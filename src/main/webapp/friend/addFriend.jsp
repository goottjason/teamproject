<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>친구저장</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>


<script type="text/javascript">

	$(function() {
		$("#friendName").blur(function() {
			isValidName();
		});
		$("#mobile").blur(function() {
			isValidMobile();
		});
	});

	function outputError(msg, obj, idVal) {
	
		$("#" + idVal).remove();
		let errorMsg = `<div class="alert alert-danger errorMsg" id="\${idVal}">에러!!\${msg}</div>`;
		$(errorMsg).insertAfter(obj);
		$(obj).focus();
	}
	
	function isValidName() {
		let result = false;
		let friendName = $("#friendName").val();
		if (friendName == "") {
			outputError("이름은 반드시 입력해야 합니다!", $("#friendName"), "nameErrorMsg");
		} else {
			result = true;
			$("#nameErrorMsg").remove();
		}
		return result;
	}
	
	function isValidMobile() {
		let result = false;
		let mobile = $("#mobile").val();
		console.log(mobile);
		const mobileRegExp = /^(01[016789]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
		console.log(mobileRegExp.test(mobile));
		if(!mobileRegExp.test(mobile)) {
			outputError("핸드폰 번호 형식이 아닙니다", $("#mobile"), "mobileErrorMsg");
		} else {
			$("#mobileErrorMsg").remove();
			// 중복체크
			
			$.ajax({
		          url: "<%=request.getContextPath()%>/mobileCheck.do", // 데이터가 송수신될 서버의 주소
		          type: "GET", // 통신 방식 (GET, POST, PUT, DELETE)
				  data: { // 서버에 전송할 데이터
					'userInputMobile': mobile
					},
				  async: false,
		          dataType: "json", // 수신받을 데이터 타입 (MIME TYPE)
		          success: function (data) {
		            // 통신이 성공하면 수행할 함수
		            console.log(data);
		            if (data.isDuplicated == 'true') {
						$("#mobile").val('');
						outputError("핸드폰 번호가 중복됩니다!", $("#mobile"), "mobileErrorMsg");
					} else if (data.isDuplicated == 'false') {
						result = true;
					}
		          },
		          error: function () {},
		          complete: function () {},
		        });
			
		}
		return result;
	}
	
	function isValid() {
		
		let result = false;
		// 유효성검사(이름, 전화번호)
		if (isValidName() && isValidMobile()) {
			result = true;
		}
		
		return result;
	}

</script>

</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="container mt-3">
	  <h1>친구 저장</h1>
	  <form action="<%=request.getContextPath()%>/save.do" method="post">
	    <div class="mb-3 mt-3">
	      <label for="friendName">이름: </label>
	      <input type="text" class="form-control" id="friendName" placeholder="friendName을 입력하세요" name="friendName">
	    </div>
	    <div class="mb-3 mt-3">
	      <label for="mobile">전화번호: </label>
	      <input type="text" class="form-control" id="mobile" placeholder="mobile을 입력하세요" name="mobile">
	    </div>
	    <div class="mb-3 mt-3">
	      <label for="addr">주소: </label>
	      <input type="text" class="form-control" id="addr" placeholder="addr 입력하세요" name="addr">
	    </div>
	    

	    <button type="submit" class="btn btn-primary" onclick="return isValid();">저장</button>
	    <button type="reset" class="btn btn-danger">취소</button>
	  </form>
	</div>
</body>
</html>