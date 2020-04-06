package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ORCLJDBC {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String user;
	String pw;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	
	public ORCLJDBC() {}
	
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
	
	public Statement getStatement() {
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	public PreparedStatement getPreparedStatement(String sql) {
		try {
			pstmt = con.prepareStatement(sql ,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}
	
	public <T extends AutoCloseable>void close(T o) {
		try {
			o.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
