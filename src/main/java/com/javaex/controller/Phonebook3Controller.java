package com.javaex.controller;

import java.io.IOException;
import java.util.List;

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
	int num=-1;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String action= request.getParameter("action");
		
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
			
		}else if("list".equals(action)) {
			
			//db관련업무 2번
			PersonDao phoneDao = new PersonDao();
			
			//db에서 전체 데이터 가져오기 4번
			List<PersonVo> personList = phoneDao.personSelect();
			
			//가져온 db에서 html로 보내기 5번번(리스트 사용시 html파일로 보내줄때) 
			request.setAttribute("personList", personList);
			
			RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
			rd.forward(request, response);
			
		}else if("insert2".equals(action)) {
			
			String name=request.getParameter("name");
			String hp=request.getParameter("hp");
			String company=request.getParameter("company");
			
			PersonVo pv=new PersonVo(name, hp, company);
			
			PersonDao phoneDao = new PersonDao();
			
			phoneDao.personInsert(pv);
			
			//리다이렉트(인서트 추가후 추가된 파라미터 표시가 아닌 새로운 연결을 표시한다(추가후 리스트 연결시 attribute가 수정될때의 작업을 단축할수 있다))
			response.sendRedirect("http://localhost:8080/phonebook3/pbc?action=list");//http://localhost:8080까지는 생략가능
			//연결된 응답의 파라미터를 바꿔서 보낸다(insert의 추가된 파라미터가 나오지 않는다)
			
		}else if("delete".equals(action)) {
			System.out.println("삭제");
			int no=Integer.parseInt(request.getParameter("no"));
			System.out.println(no);
			
			//db사용
			PersonDao phoneDao = new PersonDao();
			
			//삭제
			phoneDao.personDelete(no);
			
			//리다이렉트
			response.sendRedirect("/phonebook3/pbc?action=list");
			
		}else if("modify".equals(action)) {
			System.out.println("modify");
			int no=Integer.parseInt(request.getParameter("no"));
			num=no;
			
			System.out.println(num);
			
			RequestDispatcher rd = request.getRequestDispatcher("/modify.jsp");
			rd.forward(request, response);
			
		}else if("modify2".equals(action)) {
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			PersonVo pv=new PersonVo(num, name, hp, company);
			
			PersonDao phoneDao = new PersonDao();
			
			phoneDao.personModify(num, name, hp, company);
			
			System.out.println(num);
			
			response.sendRedirect("/phonebook3/pbc?action=list");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
