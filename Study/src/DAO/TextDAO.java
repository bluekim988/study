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
 * @author	김종형
 * @since	2020.04.12
 * @see		ORCLJDBC, sql.*
 * 
 * 				이 클래스는 텍스트 테이블에 관련된 데이터베이스 작업을 이루는 클래스이다.
 * 				현재 데이터베이스 유저는 study 이다.
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
	 	생성자 함수를 통해
	 	데이터베이스 유저 셋팅 작업을 한다.
	 	디폴트값으로 study 유저를 셋팅한다.
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
	 * 	텍스트 추가 데이터베이스 작업을 요청한다.
	 * 	데이터 입력에 성공하면 1이상의 정수를 반환하고,
	 *  입력에 실패시 0을 반환한다.
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
	 * 	텍스트 타이틀 편집 데이터베이스 작업을 요청한다.
	 * 	데이터 입력에 성공하면 1이상의 정수를 반환하고,
	 *  입력에 실패시 0을 반환한다.
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
	 * 	텍스트 본문 편집 데이터베이스 작업을 요청한다.
	 * 	데이터 입력에 성공하면 1이상의 정수를 반환하고,
	 *  입력에 실패시 0을 반환한다.
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
	 * 	텍스트 삭제 데이터베이스 작업을 요청한다.
	 * 	데이터 입력에 성공하면 1이상의 정수를 반환하고,
	 *  입력에 실패시 0을 반환한다.
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
	 * 	텍스트 조회수 증가 데이터베이스 작업을 요청한다.
	 * 	데이터 입력에 성공하면 1이상의 정수를 반환하고,
	 *  입력에 실패시 0을 반환한다.
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
	 * 	텍스트 좋아요 수 증가 데이터베이스 작업을 요청한다.
	 * 	데이터 입력에 성공하면 1이상의 정수를 반환하고,
	 *  입력에 실패시 0을 반환한다.
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
	 * 게시판의 게시글 목록 화면에 나열할 데이터베이스 정보를 가져온다.
	 * 가져온 데이터는 TextDTO객체형태로 가공한뒤,
	 * TreeSet<TextDTO> 타입으로 반환한다.
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
	 * 게시판의 텍스트 상세내용 화면에 출력할
	 * 데이터베이스 정보를 가져온다.
	 * 가져온 정보는 TextDTO 타입으로 반환한다.
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























