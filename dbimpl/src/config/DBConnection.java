package config;

import java.sql.Connection;
import java.sql.DriverManager;

import oracle.jdbc.driver.OracleDriver;

public class DBConnection {
	public static Connection getinstance() {
		Connection conn = null;
		// 주소를 저장하는 변수
		// 주소 앞에 적어줘야 하는 고정 값(jdbc:oracle:thin or oci:@)
		// thin => 순수하게 자바 패키지만으로 바로 DB와 연결.   순수하게 자바함수로 연결하는 것
		// oci => 운영체제의 네이티브 함수를 사용하는 것.   윈도우나 리눅스가 가지고 있는 함수로 연결
		// conn에 연결하기 위한 준비 사항들
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "superuser";
		String password = "1234";
		
		OracleDriver o = new OracleDriver();
		try {
			// () 을 new해서 띄워주는게 forname
			// OracleDriver 클래스를 메모리에 로드
			// 문자열을 호출하는 곳에 실수할 수 있기 때문에 try 가 동반된다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 메모리에 OracleDriver를 띄우는 코드
			// reflection : 메모리에 떠있는 어떤 클래스를 타입으로 찾아내는 기법
			//							그리고 어떤 피드가 있는지 분석할 수 있다.
			//							그래서 그때 그때마다 때려지는 타입이 다르다.
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("DB연결 성공");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("DB연결 실패");
		return null;
	}
}
