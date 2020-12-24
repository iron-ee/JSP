package com.cos.hello.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
// javax로 시작하는 패키지는 톰캣이 들고있는 라이브러리  
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.hello.config.DBConn;
import com.cos.hello.dao.UsersDao;
import com.cos.hello.model.Users;
import com.cos.hello.service.UsersJoinService;

public class UserController extends HttpServlet{
	
	// req와 res는 톰캣이 만들어준다. (클라이언트의 요청이 있을 때 마다)
	// req는 BufferedReader 할 수 있는 ByteStream
	// res는 BufferedWriter 할 수 있는 ByteStream
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("get 요청");
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("post 요청");
		doProcess(req, resp);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("userController 실행 됨");
		String gubun = req.getParameter("gubun");
		System.out.println(gubun);
		route(gubun, req, resp);
		
		}
	
	
	private void route(String gubun, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if (gubun.equals("login")) {
			resp.sendRedirect("auth/login.jsp");
		}else if (gubun.equals("join")) {
			resp.sendRedirect("auth/join.jsp");
		}else if (gubun.equals("selectOne")) {		// 유저 정보 보기
			// 인증이 필요한 페이지
			String result;
			HttpSession session = req.getSession();
			if (session.getAttribute("sessionUser") != null) {		// 인증 끝
				Users user = (Users)session.getAttribute("sessionUser");
				result = "인증되었습니다.";
				System.out.println(user);
			}else {
				result = "인증되지않았습니다.";
			}
			req.setAttribute("result", result);
			RequestDispatcher dis = req.getRequestDispatcher("user/selectOne.jsp");
			dis.forward(req, resp);
		}else if (gubun.equals("updateOne")) {
			resp.sendRedirect("user/updateOne.jsp");
		}else if (gubun.equals("joinProc")) {	// 회원가입 수행해줘
			UsersJoinService usersJoinService = new UsersJoinService();
			usersJoinService.회원가입(req, resp);


		}else if (gubun.equals("loginProc")) {
			// SELECT id, username, email FROM users WHERE username = ? AND email = ?
			// DAO의 함수명 : login()  return 을 Users 오브젝트를 리턴
			// 정상 : 세션에 Users 오브젝트 담고 index.jsp
			// 비정상 : login.jsp
			
			// 1번  전달되는 값 받기
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			System.out.println("================loginProc Start================");
			System.out.println(username);
			System.out.println(password);
			System.out.println("================loginProc End================");
			// 2번 데이터베이스 값이 있는 select 해서 확인
			// 생략
			Users user = Users.builder()
					.id(1)
					.username(username)
					.build();
			// 3번
			HttpSession session = req.getSession();
			// session에는 사용자 패스워드 절대 넣지 않기
			session.setAttribute("sessionKey", user);
//			resp.setHeader("Set-Cookie", "sessionKey=9998");
			// 모든 응답에는 jSessionid가 쿠키로 추가된다.
			
			// 4번 index.jsp 페이지로 이동
			resp.sendRedirect("index.jsp");
		}
	}
}

