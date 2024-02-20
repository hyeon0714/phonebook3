package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.PersonVo;

public class PersonDao {
	
	//필드 생성자 메소드-g/s 메소드-일반
	//필드 없음
	
	//생성자 기본
	
	//g/s 없음
	
	//등록
	public int personInsert(PersonVo personVo) {
		int count=-1;//결과내용 출력을 위한 변수 지정
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/phone_db";
			conn = DriverManager.getConnection(url, "phone", "phone");
			
		// 3. SQL문 준비 / 바인딩 / 실행
			//sql문 준비
			String query ="";
			query+=" insert into person ";
			query+=" value(null, ?, ?, ?) ";
			
			//바인딩
			pstmt =conn.prepareStatement(query);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			
			//실행
			count = pstmt.executeUpdate();
			
		// 4.결과처리(콘솔에서 보기위해)
			System.out.println(count+"건 등록되었습니다");
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return count;//정상 처리시 처리된 카운트 값 출력
	}
	
	//리스트
	public List<PersonVo> personSelect() {
		List<PersonVo> personList = new ArrayList<PersonVo>();
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/phone_db";
			conn = DriverManager.getConnection(url, "phone", "phone");
			
		// 3. SQL문 준비 / 바인딩 / 실행
			String query="";
			query+=" select	person_id, ";
			query+=" 		name, ";
			query+="        hp, ";
			query+="        company ";
			query+=" from person ";
			
			pstmt=conn.prepareStatement(query);
			
			//리스트출력은 executeQuery
			rs = pstmt.executeQuery();
			
			while(rs.next()) {// rs.next()는 출력될 값이 없으면 false로 바뀐다
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				//db에서 가져온 데이터 Vo에 묶기
				PersonVo personvo = new PersonVo(personId, name, hp, company);
				
				//입력된거 확인용
				//System.out.println(personvo);
				
				//리스트에 추가
				personList.add(personvo);
			}
			
			
		// 4.결과처리
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return personList;
		
	}
	
	//삭제
	public int personDelete(int no) {
		int count=-1;
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/phone_db";
			conn = DriverManager.getConnection(url, "phone", "phone");
			
		// 3. SQL문 준비 / 바인딩 / 실행
			String query="";
			query+=" delete from person ";
			query+=" where person_id = ? ";
			
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			count = pstmt.executeUpdate();

			
		// 4.결과처리
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
			} catch (SQLException e) {
			System.out.println("error:" + e);
			}
		}
		return count;
	}
	
	//수정
	public int personModify(int personId, String name, String hp, String company) {
		int count=-1;
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/phone_db";
			conn = DriverManager.getConnection(url, "phone", "phone");
			
		// 3. SQL문 준비 / 바인딩 / 실행
			String query="";
			query+=" update person ";
			query+=" set 	name = ?, ";
			query+=" 		hp = ?, ";
			query+="        company = ? ";
			query+=" where person_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, hp);
			pstmt.setString(3, company);
			pstmt.setInt(4, personId);
			
			count = pstmt.executeUpdate();
			
		// 4.결과처리
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
		// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return count;
	}
	
	
}
