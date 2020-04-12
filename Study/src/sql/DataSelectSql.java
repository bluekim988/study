package sql;
/**
 * 
 * @author	김종형
 * @since	2020.04.12
 * @see		BoardDTO, ReplyDTO, TextDTO
 * 						
 * 					이 클래스는 게시판 관리자 혹은 유저의 요청에 따라서
 * 					해당 데이터베이스 정보를 꺼내오기 위해 제작되었다.
 * 					
 * 					Edit
 * 					2020.04.12	 김종형
 */

public class DataSelectSql {
	public final int SELECT_TEXT_FOR_NAV = 1001;
	public final int SELECT_TEXT_FOR_IN = 1002;
	public final int SELECT_REPLY_INCLUDE_TNO = 2001;
	public final int SELECT_BOARD_THIS = 3001;
	
	
	public String getSelectSql(int code) {
		StringBuffer sql = new StringBuffer();
		switch (code) {
		case SELECT_TEXT_FOR_NAV:
			sql.append("SELECT tno, title, writer, crdate, tcount, tlike");
			sql.append("FROM text ");
			sql.append("WHERE isShow = 'Y' ");
			break;
		case SELECT_TEXT_FOR_IN:
			sql.append("SELECT tno, title, writer, crdate, tcount, tlike, text");
			sql.append("FROM text ");
			sql.append("WHERE isShow = 'Y' AND tno = ? ");
			break;
		case SELECT_REPLY_INCLUDE_TNO:
			sql.append("SELECT rno, text, writer, crdate ");
			sql.append("FROM reply ");
			sql.append("WHERE isShow = 'Y' AND textno = ? ");
			break;
		case SELECT_BOARD_THIS:
			sql.append("SELECT bno, bname, binfo ");
			sql.append("FROM board ");
			sql.append("WHERE isShow = 'Y' AND bname = ? ");
			break;
		}
		return sql.toString();
	}
	
}
