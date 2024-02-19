package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PersonDao;
import com.javaex.vo.PersonVo;


@WebServlet("/pbc")
public class Phonebook3Controller extends HttpServlet {
	//필드
	//private static final long serialVersionUID = 1L;
	
	//생성자 기본사용
	
	//게터세터 메소드 없음
	
	//메소드 일반
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("Phonebook3Controller.goGet()");
		
		String action= request.getParameter("action");
		System.out.println(action);
		
		if("wform".equals(action)){
			System.out.println("wform:등록폼");
			//jsp한테 html그리기 응답명령-->포워드
			//포워드 방법(포워드 작업을 통해 jsp파일과도 연결시킬수 잇다
			
			RequestDispatcher rd = request.getRequestDispatcher("/writeForm.jsp");
			rd.forward(request, response);
		}else if("insert".equals(action)) {
			System.out.println("insert:등록");
			
			String name=request.getParameter("name");
			String hp=request.getParameter("hp");
			String company=request.getParameter("company");
			
			System.out.println(name+hp+company);
			
			//Vo로 묶기 1번
			PersonVo pv=new PersonVo(name, hp, company);
			System.out.println(pv.toString());
			
			//db관련업무 2번
			PersonDao phoneDao = new PersonDao();
			
			//db에 저장(dao의 insert사용)
			phoneDao.personInsert(pv);
			
			//db에서 전체 데이터 가져오기 4번
			List<PersonVo> personList = phoneDao.personSelect();
			System.out.println(personList);
			
			//가져온 db에서 html로 보내기 5번번(리스트 사용시 html파일로 보내줄때) 
			request.setAttribute("personList", personList);
			
			//포워드(html에 그리기 명령 (db내용 보내고 페이지 html로 이동))
			RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
			rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
