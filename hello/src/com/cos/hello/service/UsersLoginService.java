package com.cos.hello.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.hello.dao.UsersDao;
import com.cos.hello.model.Users;

public class UsersLoginService {
	
	public void 로그인(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		Users user = Users.builder()
				.username(username)
				.password(password)
				.build();
		
		UsersDao userDao = new UsersDao();
		Users userEntity = userDao.login(user);
		
		if (userEntity != null) {
			HttpSession session = req.getSession();
			// session에는 사용자 패스워드 절대 넣지 않기
			session.setAttribute("sessionUser", userEntity);
			resp.sendRedirect("index.jsp");
		}else {
			resp.sendRedirect("auth/login.jsp");
		}
		
	}
}
