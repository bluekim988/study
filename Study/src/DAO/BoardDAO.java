package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.ORCLJDBC;
import DTO.BoardDTO;
import sql.*;

/**
 * 
 * @author	������
 * @since	2020.04.12
 * @see		ORCLJDBC, sql.*
 * 
 * 				�� Ŭ������ �Խ��� ���̺� ���õ� �����ͺ��̽� �۾��� �̷�� Ŭ�����̴�.
 * 				���� �����ͺ��̽� ������ study �̴�.
 * 				
 */

public class BoardDAO {
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
	public BoardDAO() {
		this("study", "study");
	}
	
	public BoardDAO(String user, String pw) {
		this.user = user;
		this.pw = pw;
		db = new ORCLJDBC(user, pw);
	}
	
	/*
	 * 	�Խ��� ���������� �����ͺ��̽� �۾��� ��û�Ѵ�.
	 * 	������ �Է¿� �����ϸ� 1�̻��� ������ ��ȯ�ϰ�,
	 *  �Է¿� ���н� 0�� ��ȯ�Ѵ�.
	 */
	public int createBoard(BoardDTO data) { 
		con = db.getCon();
		addSql = new DataAddSql();
		String sql = addSql.getSql(addSql.INSERT_BOARD);
		pstmt = db.getPreparedStatement(sql);
		try {
			pstmt.setString(1, data.getBname());
			pstmt.setString(2, data.getbInfo());
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
	 * 	�Խ��� �̸� ���������� �����ͺ��̽� �۾��� ��û�Ѵ�.
	 * 	������ �Է¿� �����ϸ� 1�̻��� ������ ��ȯ�ϰ�,
	 *  �Է¿� ���н� 0�� ��ȯ�Ѵ�.
	 */
	public int updateBoardName(String toBname, String fromBname) {
		con = db.getCon();
		editSql = new DataEditSql();
		String sql = editSql.getUpdateSql(editSql.UPDATE_BOARD_BNAME);
		pstmt = db.getPreparedStatement(sql);
		try {
			pstmt.setString(1, toBname);
			pstmt.setString(2, fromBname);
			int result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		return 0;
	}
	
	/*
	 * 	�Խ��� ǥ������ ���������� �����ͺ��̽� �۾��� ��û�Ѵ�.
	 * 	������ �Է¿� �����ϸ� 1�̻��� ������ ��ȯ�ϰ�,
	 *  �Է¿� ���н� 0�� ��ȯ�Ѵ�.
	 */
	public int updateBoardInfo(String toInfo, String bname) {
		con = db.getCon();
		editSql = new DataEditSql();
		String sql = editSql.getUpdateSql(editSql.UPDATE_BOARD_BINFO);
		pstmt = db.getPreparedStatement(sql);
		try {
			pstmt.setString(1, toInfo);
			pstmt.setString(2, bname);
			int result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		return 0;
	}
	
	/*
	 * 	�Խ��� ���������� �����ͺ��̽� �۾��� ��û�Ѵ�.
	 * 	������ �Է¿� �����ϸ� 1�̻��� ������ ��ȯ�ϰ�,
	 *  �Է¿� ���н� 0�� ��ȯ�Ѵ�.
	 */
	public int deleteBoard(String bname) {
		con = db.getCon();
		editSql = new DataEditSql();
		String sql = editSql.getDeleteSql(editSql.DELETE_BOARD);
		pstmt = db.getPreparedStatement(sql);
		try {
			pstmt.setString(1, bname);
			int result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		return 0;
	}
	
	/*
	 * 	�Խ��� ���� �������� �����ͺ��̽� �۾��� ��û�Ѵ�.
	 * 	����� BoardDTO ��ü Ÿ������ ��ȯ�Ѵ�.
	 */
	public BoardDTO selectBoard(String bname) {
		BoardDTO board = new BoardDTO();
		con = db.getCon();
		selectSql = new DataSelectSql();
		String sql = selectSql.getSelectSql(selectSql.SELECT_BOARD_THIS);
		pstmt = db.getPreparedStatement(sql);
		try {
			pstmt.setString(1, bname);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board.setBno(rs.getInt("bno"));
				board.setBname(rs.getString("bname"));
				board.setbInfo(rs.getString("binfo"));
			}
		
			return board;

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			db.close(pstmt);
			db.close(con);
		}
		return board;
	}
}






















