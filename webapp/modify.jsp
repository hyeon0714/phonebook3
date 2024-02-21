<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import = "com.javaex.controller.Phonebook3Controller" %>
    
    <% int a=(int)request.getAttribute("num"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전화번호부</h1>
	
	<h2>수정폼</h2>
	
	<p>	전화번호를 등록하려면<br>
		아래 항목을 기입하고 "등록"버튼을 클릭하세요
	</p>
	
	<form action="http://localhost:8080/phonebook3/pbc" method="get">
	<!-- http://localhost:8080 까지는 생략가능 -->
		<div>
			<label>이름(name)</label>
			<input type="text" name="name" value="">
		</div>
		<div>
			<label>핸드폰(hp)</label>
			<input type="text" name="hp" value="">
		</div>
		<div>
			<label>회사(company)</label>
			<input type="text" name="company" value="">
		</div>
		
		<input type="hidden" name="no" value="<%=a%>">
		<input type="hidden" name="action" value="modify2">
		<!-- action을 주기위해 보이지않는 값을 넣어준다 -->
		<button type="submit">수정</button>
	</form>
	
	<a href="">리스트 바로가기</a>
	
</body>
</html>