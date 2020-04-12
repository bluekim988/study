package sql;
/**
 * 
 * @author	김종형
 * @since	2020.04.12
 * @see		BoardDTO, ReplyDTO, TextDTO
 * 						
 * 					이 클래스는 게시판 관리자 혹은 유저의 요청에 따라서
 * 					데이터베이스 편집을 위해 제작되었다.
 * 					
 * 					Edit
 * 					2020.04.12	 김종형
 */
 
public class DataEditSql {
	public final int UPDATE_TEXT_TITLE = 1001;
	public final int UPDATE_TEXT_TEXT = 1002;
	public final int UPDATE_REPLY_TEXT = 1003;
	public final int UPDATE_BOARD_BNAME = 1004;
	public final int UPDATE_BOARD_BINFO = 1005;
	
	public final int DELETE_TEXT = 2001;
	public final int DELETE_REPLY = 2002;
	public final int DELETE_BOARD = 2003;
	
	public final int UPDATE_TEXT_COUNT = 3001;
	public final int UPDATE_TEXT_LIKE = 3002;
	
	/*
	 	선택된 데이터의 특정 정보를 수정하는 질의문을 반환한다.
	 	
	 	현재 포함된 데이터 교체 질의문 리스트
	 	-text 테이블 title
	 	-text 테이블 text
	 	-reply 테이블 text
	 	-board 테이블 bname
	 	-board 테이블 binfo
	 */
	public String getUpdateSql(int code) {
		StringBuffer sql = new StringBuffer();
		
		switch (code) {
		case UPDATE_TEXT_TITLE:
			sql.append("UPDATE text SET title = ? WHERE tno = ? ");
			break;
			
		case UPDATE_TEXT_TEXT:
			sql.append("UPDATE text SET text = ? WHERE tno = ? ");
			break;
			
		case UPDATE_REPLY_TEXT:
			sql.append("UPDATE reply SET text = ? WHERE rno = ? ");
			break;
			
		case UPDATE_BOARD_BNAME:
			sql.append("UPDATE board SET bname = ? WHERE bname = ? ");
			break;
		case UPDATE_BOARD_BINFO:
			sql.append("UPDATE board SET binfo = ? WHERE bname = ? ");			
			break;
		}
		
		return sql.toString();
	}

	/*
	  	데이터 삭제를 위한 slq 질의문을 반환한다.
	  	단, 반환된 질의문은 DB상에서 데이터를 삭제하는것이 아닌,
	  	데이터의 공개 여부를 비공개로 교체할 뿐이다.
	  	(이는 데이터의 기록을 일정기간 유지하기 위함이다.)
	 */
	public String getDeleteSql(int code) {
		StringBuffer sql = new StringBuffer();
		
		switch (code) {
		case DELETE_TEXT:
			sql.append("UPDATE text SET isShow = 'N' WHERE tno = ? ");
			break;
			
		case DELETE_REPLY:
			sql.append("UPDATE reply SET isShow = 'N' WHERE rno = ? ");
			break;
			
		case DELETE_BOARD:
			sql.append("UPDATE board SET isShow = 'N' WHERE bname = ? ");	
			break;
		}
		
		return sql.toString();
	}
	
	/*
	 	본문글의 카운트를 증가시키기위한 질의문을 반환한다.
	 	카운트는 한번 명령요청시 1씩 증가한다.
	 	파라미터<?> 값에 해당하는 텍스트번호를 입력한다.
	 	두 값은 꼭 동일해야 한다.
	 */
	public String updateTextCount(int code) {
		StringBuffer sql = new StringBuffer();
		
		switch (code) {
		case UPDATE_TEXT_COUNT:
			sql.append("UPDATE text SET ");
			sql.append("tcount = ( SELECT tcount FROM text WHERE tno = ? ) + 1 ");
			sql.append("WHERE tno = ? ");
			break;

		}
		
		return sql.toString();
	}
	
	/*
	 	본문글의 좋아요를 증가시키기위한 질의문을 반환한다.
	 	카운트는 한번 명령요청시 1씩 증가한다.
	 	파라미터<?> 값에 해당하는 텍스트번호를 입력한다.
	 	두 값은 꼭 동일해야 한다.
	 */
	public String updateTextLike(int code) {
		StringBuffer sql = new StringBuffer();
		
		switch (code) {
		case UPDATE_TEXT_LIKE:
			sql.append("UPDATE text SET ");
			sql.append("tlike = ( SELECT tlike FROM text WHERE tno = ? ) + 1 ");
			sql.append("WHERE tno = ? ");
			break;

		}
		
		return sql.toString();
	}
}
