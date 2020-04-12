package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeSet;

import DB.ORCLJDBC;
import DTO.ReplyDTO;
import sql.DataAddSql;
import sql.DataEditSql;
import sql.DataSelectSql;

/**
 * 
 * @author	������
 * @since	2020.04.12
 * @see		ORCLJDBC, sql.*
 * 
 * 				�� Ŭ������ ��� ���̺� ���õ� �����ͺ��̽� �۾��� �̷�� Ŭ�����̴�.
 * 				���� �����ͺ��̽� ������ study �̴�.
 * 				
 */

public class ReplyDAO {
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
	public ReplyDAO() {
		this("study", "study");
	}
	
	public ReplyDAO(String user, String pw) {
		this.user = user;
		this.pw = pw;
		db = new ORCLJDBC(this.user, this.pw);
	}
	
	/*
	 * 	��� �߰� �����ͺ��̽� �۾��� ��û�Ѵ�.
	 * 	������ �Է¿� �����ϸ� 1�̻��� ������ ��ȯ�ϰ�,
	 *  �Է¿� ���н� 0�� ��ȯ�Ѵ�.
	 */
	public int createReply(ReplyDTO data) { 
		con = db.getCon();
		addSql = new DataAddSql();
		String sql = addSql.getSql(addSql.INSERT_REPLY);
		pstmt = db.getPreparedStatement(sql);
		try {
			pstmt.setString(1, data.getWriter());
			pstmt.setInt(2, data.getTextno());
			pstmt.setString(3, data.getText());
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
	 * 	��� �ؽ�Ʈ ���� �����ͺ��̽� �۾��� ��û�Ѵ�.
	 * 	������ �Է¿� �����ϸ� 1�̻��� ������ ��ȯ�ϰ�,
	 *  �Է¿� ���н� 0�� ��ȯ�Ѵ�.
	 */
	public int updateReply(String text, int rno) { 
		con = db.getCon();
		editSql = new DataEditSql();
		String sql = editSql.getUpdateSql(editSql.UPDATE_REPLY_TEXT);
		pstmt = db.getPreparedStatement(sql);
		try {
			pstmt.setString(1, text);
			pstmt.setInt(2, rno);
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
	 * 	��� ���� �����ͺ��̽� �۾��� ��û�Ѵ�.
	 * 	������ �Է¿� �����ϸ� 1�̻��� ������ ��ȯ�ϰ�,
	 *  �Է¿� ���н� 0�� ��ȯ�Ѵ�.
	 */
	public int deleteReply(int rno) { 
		con = db.getCon();
		editSql = new DataEditSql();
		String sql = editSql.getUpdateSql(editSql.DELETE_REPLY);
		pstmt = db.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, rno);
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
	 * 	�ش� �ؽ�Ʈ�� ���Ե� ��� ��� ���� �������� 
	 * 	�����ͺ��̽� �۾��� ��û�Ѵ�.
	 * 	����� ReplyDTO ��ü�� ���� TreeSet<ReplyDTO> Ÿ������ ��ȯ�Ѵ�.
	 */
	public TreeSet<ReplyDTO> selectReply(int tno) {
		TreeSet<ReplyDTO> set = new TreeSet<ReplyDTO>();
		con = db.getCon();
		selectSql = new DataSelectSql();
		String sql = selectSql.getSelectSql(selectSql.SELECT_REPLY_INCLUDE_TNO);
		pstmt = db.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, tno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReplyDTO reply = new ReplyDTO();
				reply.setRno(rs.getInt("rno"));
				reply.setWriter(rs.getString("writer"));
				reply.setCrdateOri(rs.getDate("crdate"));
				reply.setText(rs.getString("text"));
				set.add(reply);
			}
			return set;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return set;
	}
}

























