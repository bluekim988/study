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
 * @author	김종형
 * @since	2020.04.12
 * @see		ORCLJDBC, sql.*
 * 
 * 				이 클래스는 댓글 테이블에 관련된 데이터베이스 작업을 이루는 클래스이다.
 * 				현재 데이터베이스 유저는 study 이다.
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
	 	생성자 함수를 통해
	 	데이터베이스 유저 셋팅 작업을 한다.
	 	디폴트값으로 study 유저를 셋팅한다.
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
	 * 	댓글 추가 데이터베이스 작업을 요청한다.
	 * 	데이터 입력에 성공하면 1이상의 정수를 반환하고,
	 *  입력에 실패시 0을 반환한다.
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
	 * 	댓글 텍스트 편집 데이터베이스 작업을 요청한다.
	 * 	데이터 입력에 성공하면 1이상의 정수를 반환하고,
	 *  입력에 실패시 0을 반환한다.
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
	 * 	댓글 삭제 데이터베이스 작업을 요청한다.
	 * 	데이터 입력에 성공하면 1이상의 정수를 반환하고,
	 *  입력에 실패시 0을 반환한다.
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
	 * 	해당 텍스트에 포함된 모든 댓글 정보 가져오는 
	 * 	데이터베이스 작업을 요청한다.
	 * 	결과는 ReplyDTO 객체를 담은 TreeSet<ReplyDTO> 타입으로 반환한다.
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

























