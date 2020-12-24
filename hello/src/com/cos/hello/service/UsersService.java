package com.cos.hello.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.hello.dao.UsersDao;
import com.cos.hello.model.Users;

public class UsersService {

	public void 로그인(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		Users user = Users.builder().username(username).password(password).build();

		UsersDao userDao = new UsersDao();
		Users userEntity = userDao.login(user);

		if (userEntity != null) {
			HttpSession session = req.getSession();
			// session에는 사용자 패스워드 절대 넣지 않기
			session.setAttribute("sessionUser", userEntity);
			resp.sendRedirect("index.jsp");
		} else {
			resp.sendRedirect("auth/login.jsp");
		}

	}

	public void 회원가입(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1번 form의 input 태그에 있는 3가지 값 username, password, email 받기

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		// req.getParameter => 얘가 다 알아서 파싱 해줌 그냥 개 꿀

		System.out.println("================joinProc Start================");
		System.out.println(username);
		System.out.println(password);
		System.out.println(email);
		System.out.println("================joinProc End================");

		// 2번 DB에 연결해서 3가지 값을 INSERT 하기

		Users user = Users.builder().username(username).password(password).email(email).build();

		UsersDao usersDao = new UsersDao();
		int result = usersDao.insert(user);

		if (result == 1) {
			// 3번 INSERT가 정상적으로 되었다면 index.jsp를 응답하기 !
			resp.sendRedirect("auth/login.jsp");
		} else {
			resp.sendRedirect("auth/join.jsp");
		}
	}

	public void 유저정보보기(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		Users user = (Users) session.getAttribute("sessionUser");
		UsersDao usersDao = new UsersDao();

		if (user != null) {
			Users userEntity = usersDao.selectById(user.getId());
			req.setAttribute("user", userEntity);
			RequestDispatcher dis = req.getRequestDispatcher("user/selectOne.jsp");
			dis.forward(req, resp);
		} else {
			resp.sendRedirect("auth/login.jsp");
		}

	}

	public void 유저정보수정페이지(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		Users user = (Users) session.getAttribute("sessionUser");
		UsersDao usersDao = new UsersDao();

		if (user != null) {
			Users userEntity = usersDao.selectById(user.getId());
			req.setAttribute("user", userEntity);
			RequestDispatcher dis = req.getRequestDispatcher("user/updateOne.jsp");
			dis.forward(req, resp);
		} else {
			resp.sendRedirect("auth/login.jsp");
		}

	}
}
