package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 
 * @author	������
 * @since	2020.04.06
 * 
 * 	�� Ŭ������ �Խ��� ���� ������Ʈ�� DB ������ ���� �⺻ Ŭ�����̴�.
 * 	�����ڸ� ȣ���ϸ� ����̹��ε带 �̷��,
 * 	������ �Լ����� �̿���
 * 	Connection, Statement, PreparedStatement �� ��´�. 
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
	 * ������ ȣ��� ����Ŭ �����ͺ��̽� ���ӽ� 
	 * ���� ������ ��й�ȣ�� �Ű������� �����Ѵ�.
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
	 	getCon() ���� �޾ƿ� Connection ��ü�� ������� Statement�� ��ȯ���ش�.
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
 	getCon() ���� �޾ƿ� Connection ��ü�� ������� PreparedStatement�� ��ȯ���ش�.
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
	 * Wrapper Ŭ������ ���� �ݱ� ����� ����Ѵ�.
	 * AutoCloseable�� �����Ǿ� ������
	 * ������ �ݱ����� WrapperŬ������ �Ű������� ������ �ݾ��ִ� ����� �����Ѵ�.
	 */
	public <T extends AutoCloseable>void close(T o) {
		try {
			o.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
