<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 연결된 db받아올 import 내용 클래스 -->
<%@	page import ="java.util.List"  %>
<%@ page import ="com.javaex.vo.PersonVo" %>


<%
	/*자바 연결 db받아오기 (컨트롤러의 attribute 받아오기임)*/
	List<PersonVo> personList = (List<PersonVo>)request.getAttribute("personList");//형변환필요하다
	System.out.println(personList);
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전화번호 리스트</h1>
	
	<h2>리스트</h2>
	
	<p>입력된 전화번호 리스트입니다</p>
	
	<%
	for(int i=0; i<personList.size(); i++){
	%>

	
	<table border='1'>
		
		<tr>
			<td>이름(name)</td>
			<td><%=personList.get(i).getName() %></td>
		</tr>
		<tr>
			<td>핸드폰(hp)</td>
			<td><%=personList.get(i).getHp() %></td>
		</tr>
		<tr>
			<td>회사(company)</td>
			<td><%=personList.get(i).getCompany() %></td>
		</tr>
	
	</table>
	<%
	}
	%>
</body>
</html>