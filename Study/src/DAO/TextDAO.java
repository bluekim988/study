package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeSet;

import DB.ORCLJDBC;
import DTO.TextDTO;
import sql.DataAddSql;
import sql.DataEditSql;
import sql.DataSelectSql;

/**
 * 
 * @author	������
 * @since	2020.04.12
 * @see		ORCLJDBC, sql.*
 * 
 * 				�� Ŭ������ �ؽ�Ʈ ���̺� ���õ� �����ͺ��̽� �۾��� �̷�� Ŭ�����̴�.
 * 				���� �����ͺ��̽� ������ study �̴�.
 * 				
 */


public class TextDAO {
	ORCLJDBC db;
	DataAddSql addSql;
	DataEditSql editSql;
	DataSelectSql selectSql;
	
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	String user;
	String pw;
	
	
	/*
	 	������ �Լ��� ����
	 	�����ͺ��̽� ���� ���� �۾��� �Ѵ�.
	 	����Ʈ������ study ������ �����Ѵ�.
	 */
	public TextDAO() {
		this("study", "study");
	}
	
	public TextDAO(String user, String pw) {
		this.user = user;
		this.pw = pw;
		db = new ORCLJDBC(this.user, this.pw);
	}
	
	/*
	 * 	�ؽ�Ʈ �߰� �����ͺ��̽� �۾��� ��û�Ѵ�.
	 * 	������ �Է¿� �����ϸ� 1�̻��� ������ ��ȯ�ϰ�,
	 *  �Է¿� ���н� 0�� ��ȯ�Ѵ�.
	 */
	public int createText(TextDTO data) { 
		con = db.getCon();
		addSql = new DataAddSql();
		String sql = addSql.getSql(addSql.INSERT_TEXT);
		pstmt = db.getPreparedStatement(sql);
		try {
			pstmt.setString(1, data.getTitle());
			pstmt.setString(2, data.getWriter());
			pstmt.setString(3, data.getText());
			pstmt.setInt(4, data.getBno());
			int result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			db.close(pstmt);
			db.close(con);
		}
		return 0;
	}
	
	/*
	 * 	�ؽ�Ʈ Ÿ��Ʋ ���� �����ͺ��̽� �۾��� ��û�Ѵ�.
	 * 	������ �Է¿� �����ϸ� 1�̻��� ������ ��ȯ�ϰ�,
	 *  �Է¿� ���н� 0�� ��ȯ�Ѵ�.
	 */
	public int updateText_Title(String title, int tno) { 
		con = db.getCon();
		editSql = new DataEditSql();
		String sql = editSql.getUpdateSql(editSql.UPDATE_TEXT_TITLE);
		pstmt = db.getPreparedStatement(sql);
		try {
			pstmt.setString(1, title);
			pstmt.setInt(2, tno);
			int result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			db.close(pstmt);
			db.close(con);
		}
		return 0;
	}
	
	/*
	 * 	�ؽ�Ʈ ���� ���� �����ͺ��̽� �۾��� ��û�Ѵ�.
	 * 	������ �Է¿� �����ϸ� 1�̻��� ������ ��ȯ�ϰ�,
	 *  �Է¿� ���н� 0�� ��ȯ�Ѵ�.
	 */
	public int updateText_Text(String text, int tno) { 
		con = db.getCon();
		editSql = new DataEditSql();
		String sql = editSql.getUpdateSql(editSql.UPDATE_TEXT_TEXT);
		pstmt = db.getPreparedStatement(sql);
		try {
			pstmt.setString(1, text);
			pstmt.setInt(2, tno);
			int result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			db.close(pstmt);
			db.close(con);
		}
		return 0;
	}
	
	/*
	 * 	�ؽ�Ʈ ���� �����ͺ��̽� �۾��� ��û�Ѵ�.
	 * 	������ �Է¿� �����ϸ� 1�̻��� ������ ��ȯ�ϰ�,
	 *  �Է¿� ���н� 0�� ��ȯ�Ѵ�.
	 */
	public int deleteText(int tno) { 
		con = db.getCon();
		editSql = new DataEditSql();
		String sql = editSql.getDeleteSql(editSql.DELETE_TEXT);
		pstmt = db.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, tno);
			int result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			db.close(pstmt);
			db.close(con);
		}
		return 0;
	}
	
	/*
	 * 	�ؽ�Ʈ ��ȸ�� ���� �����ͺ��̽� �۾��� ��û�Ѵ�.
	 * 	������ �Է¿� �����ϸ� 1�̻��� ������ ��ȯ�ϰ�,
	 *  �Է¿� ���н� 0�� ��ȯ�Ѵ�.
	 */
	public int updateTextCount(int tno) {
		con = db.getCon();
		editSql = new DataEditSql();
		String sql = editSql.updateTextCount(editSql.UPDATE_TEXT_COUNT);
		pstmt = db.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, tno);
			pstmt.setInt(2, tno);
			int result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			db.close(pstmt);
			db.close(con);
		}
		
		return 0;
	}
	
	/*
	 * 	�ؽ�Ʈ ���ƿ� �� ���� �����ͺ��̽� �۾��� ��û�Ѵ�.
	 * 	������ �Է¿� �����ϸ� 1�̻��� ������ ��ȯ�ϰ�,
	 *  �Է¿� ���н� 0�� ��ȯ�Ѵ�.
	 */
	public int updateTextLike(int tno) {
		con = db.getCon();
		editSql = new DataEditSql();
		String sql = editSql.updateTextCount(editSql.UPDATE_TEXT_LIKE);
		pstmt = db.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, tno);
			pstmt.setInt(2, tno);
			int result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			db.close(pstmt);
			db.close(con);
		}
		
		return 0;
	}
	
	/*
	 * �Խ����� �Խñ� ��� ȭ�鿡 ������ �����ͺ��̽� ������ �����´�.
	 * ������ �����ʹ� TextDTO��ü���·� �����ѵ�,
	 * TreeSet<TextDTO> Ÿ������ ��ȯ�Ѵ�.
	 */
	public TreeSet<TextDTO> selectTextForMain(String bname) {
		TreeSet<TextDTO> set = new TreeSet<TextDTO>();
		con = db.getCon();
		selectSql = new DataSelectSql();
		String sql = selectSql.getSelectSql(selectSql.SELECT_TEXT_FOR_NAV);
		pstmt = db.getPreparedStatement(sql);
		try {
			pstmt.setString(1, bname);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TextDTO text = new TextDTO();
				text.setTno(rs.getInt("tno"));
				text.setTitle(rs.getString("title"));
				text.setWriter(rs.getString("writer"));
				text.setCrtimeOri(rs.getTime("crdate"));
				text.setCrdateOri(rs.getDate("crdate"));
				text.setCount(rs.getInt("tcount"));
				text.setLike(rs.getInt("tlike"));
				set.add(text);
			}
			return set;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			db.close(pstmt);
			db.close(con);
		}
		
		return set;
	}
	
	/*
	 * �Խ����� �ؽ�Ʈ �󼼳��� ȭ�鿡 �����
	 * �����ͺ��̽� ������ �����´�.
	 * ������ ������ TextDTO Ÿ������ ��ȯ�Ѵ�.
	 */
	public TextDTO selectTextForIn(int tno) {
		TextDTO text = new TextDTO();
		con = db.getCon();
		selectSql = new DataSelectSql();
		String sql = selectSql.getSelectSql(selectSql.SELECT_TEXT_FOR_IN);
		pstmt = db.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, tno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				text.setTno(rs.getInt("tno"));
				text.setTitle(rs.getString("title"));
				text.setWriter(rs.getString("writer"));
				text.setCrtimeOri(rs.getTime("crdate"));
				text.setCrdateOri(rs.getDate("crdate"));
				text.setCount(rs.getInt("tcount"));
				text.setLike(rs.getInt("tlike"));
				text.setText(rs.getString("text"));
			}
			return text;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			db.close(pstmt);
			db.close(con);
		}
		return text;
	}
	
	
}























