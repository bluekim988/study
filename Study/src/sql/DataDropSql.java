package sql;
/**
 * 
 * @author	김종형
 * @since	2020.04.12
 * @see		BoardDTO, ReplyDTO, TextDTO
 * 						
 * 					이 클래스는 데이터베이스에서 선택한 데이터를
 * 					완전하게 삭제하는 sql 명령을 반환한다.
 * 					
 * 					Edit
 * 					2020.04.12	 김종형
 */
public class DataDropSql {
	public final int DROP_BOARD = 1001;
	
	public String getDropSql(int code) {
		StringBuffer sql = new StringBuffer();
		switch (code) {
		case DROP_BOARD:
			sql.append("DELETE FROM board ");
			sql.append("WHERE bname = ?  ");
			break;
		}
		return sql.toString();
	}
}
