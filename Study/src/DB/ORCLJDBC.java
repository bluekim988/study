package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 
 * @author	김종형
 * @since	2020.04.06
 * 
 * 	이 클래스는 게시판 구현 프로잭트의 DB 구현을 위한 기본 클래스이다.
 * 	생성자를 호출하면 드라이버로드를 이루고,
 * 	각각의 함수들을 이용해
 * 	Connection, Statement, PreparedStatement 를 얻는다. 
 *
 */

public class ORCLJDBC {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String user;
	String pw;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	
	public ORCLJDBC() {}
	
	/*
	 * 생성자 호출시 오라클 데이터베이스 접속시 
	 * 유저 계정과 비밀번호를 매개변수로 전달한다.
	 */
	public ORCLJDBC(String user, String pw) {
		this.user = user;
		this.pw = pw;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getCon() {
		try {
			con = DriverManager.getConnection(url, user, pw);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	/*
	 	getCon() 통해 받아온 Connection 객체를 기반으로 Statement를 반환해준다.
	 */
	public Statement getStatement() {
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	/*
 	getCon() 통해 받아온 Connection 객체를 기반으로 PreparedStatement를 반환해준다.
 */
	public PreparedStatement getPreparedStatement(String sql) {
		try {
			pstmt = con.prepareStatement(sql ,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}
	
	/*
	 * Wrapper 클래스의 강제 닫기 기능을 담당한다.
	 * AutoCloseable이 구현되어 있지만
	 * 강제로 닫기위한 Wrapper클래스를 매개변수로 보내면 닫아주는 기능을 수행한다.
	 */
	public <T extends AutoCloseable>void close(T o) {
		try {
			o.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
