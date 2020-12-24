package com.cos.hello.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cos.hello.config.DBConn;
import com.cos.hello.model.Users;

public class UsersDao {

	public Users login(Users user) {
		
		StringBuffer sb = new StringBuffer();		// String 전용 컬렉션 ( 동기화 )
		sb.append("SELECT id, username, password, email FROM users ");
		sb.append("WHERE username = ? AND password = ?");
		String sql = sb.toString();
		Connection conn = DBConn.getinstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Users userEntity = Users.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.email(rs.getString("email"))
						.build();
				return userEntity;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public int insert(Users user) {
		
		StringBuffer sb = new StringBuffer();		// String 전용 컬렉션 ( 동기화 )
		sb.append("INSERT INTO users(username, password, email)");
		sb.append("VALUES(?,?,?)");
		String sql = sb.toString();
		Connection conn = DBConn.getinstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			int result = pstmt.executeUpdate(); 	// 변경된 행의 개수를 리턴
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public Users selectById(int id)	{
		
		StringBuffer sb = new StringBuffer();		// String 전용 컬렉션 ( 동기화 )
		sb.append("SELECT id, username, password, email FROM users ");
		sb.append("WHERE id = ?");
		String sql = sb.toString();
		Connection conn = DBConn.getinstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Users userEntity = Users.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.password(rs.getString("password"))
						.email(rs.getString("email"))
						.build();
				return userEntity;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int update(Users user) {
		
		StringBuffer sb = new StringBuffer();		// String 전용 컬렉션 ( 동기화 )
		sb.append("UPDATE users SET password = ?, email = ? ");
		sb.append("WHERE id = ?");
		String sql = sb.toString();
		Connection conn = DBConn.getinstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getEmail());
			pstmt.setInt(3, user.getId());
			int result = pstmt.executeUpdate(); 	// 변경된 행의 개수를 리턴
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
public int delete(int id) {
		
		StringBuffer sb = new StringBuffer();		// String 전용 컬렉션 ( 동기화 )
		sb.append("DELETE FROM users ");
		sb.append("WHERE id = ?");
		String sql = sb.toString();
		Connection conn = DBConn.getinstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate(); 	// 변경된 행의 개수를 리턴
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
