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
 * @author	김종형
 * @since	2020.04.12
 * @see		ORCLJDBC, sql.*
 * 
 * 				이 클래스는 게시판 테이블에 관련된 데이터베이스 작업을 이루는 클래스이다.
 * 				현재 데이터베이스 유저는 study 이다.
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
	 	생성자 함수를 통해
	 	데이터베이스 유저 셋팅 작업을 한다.
	 	디폴트값으로 study 유저를 셋팅한다.
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
	 * 	게시판 생성을위한 데이터베이스 작업을 요청한다.
	 * 	데이터 입력에 성공하면 1이상의 정수를 반환하고,
	 *  입력에 실패시 0을 반환한다.
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
	 * 	게시판 이름 수정을위한 데이터베이스 작업을 요청한다.
	 * 	데이터 입력에 성공하면 1이상의 정수를 반환하고,
	 *  입력에 실패시 0을 반환한다.
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
	 * 	게시판 표시정보 수정을위한 데이터베이스 작업을 요청한다.
	 * 	데이터 입력에 성공하면 1이상의 정수를 반환하고,
	 *  입력에 실패시 0을 반환한다.
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
	 * 	게시판 삭제를위한 데이터베이스 작업을 요청한다.
	 * 	데이터 입력에 성공하면 1이상의 정수를 반환하고,
	 *  입력에 실패시 0을 반환한다.
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
	 * 	게시판 정보 가져오는 데이터베이스 작업을 요청한다.
	 * 	결과는 BoardDTO 객체 타입으로 반환한다.
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






















