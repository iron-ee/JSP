package test.config;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.Test;

import config.DBConnection;

public class DBConnectionTest {

	@Test
	public void 데이터베이스연결_테스트() {
//		DBConnection.getinstance();
		Connection conn = DBConnection.getinstance();
		assertNotNull(conn);
	}
	// main으로 테스트하면 시간이 너무 오래 걸리니깐 꼭 주의해서 테스트 하도록 하자 !
}
