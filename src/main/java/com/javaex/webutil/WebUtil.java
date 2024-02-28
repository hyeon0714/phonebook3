package com.javaex.webutil;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {
	
	//필드
	
	//생성자
	
	//gs
	
	//일반
	public static void forward(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	}
	
	public static void redir(String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.sendRedirect(url);
	}
}
