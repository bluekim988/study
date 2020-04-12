package sql;
/**
 * 
 * @author	김종형
 * @since	2020.04.12
 * @see		BoardDTO, ReplyDTO, TextDTO
 * 						
 * 					이 클래스는 게시판 관리자 혹은 유저의 요청에 따라서
 * 					데이터베이스에 새로운 데이터를 추가하기 위해 제작되었다.
 * 					
 * 					Edit
 * 					2020.04.12	 김종형
 */

public class DataAddSql {
	public final int INSERT_TEXT = 1001;
	public final int INSERT_REPLY = 1002;
	public final int INSERT_BOARD = 1003;
	
	public String getSql(int code) {
		StringBuffer sql = new StringBuffer();
		
		switch (code) {
		
		/*
		 * 파라미터<?> 순서 정리	- 4개
		 * title, writer, text, bno
		 */
		case INSERT_TEXT:
			sql.append( "INSERT into text VALUES ( " );
			sql.append( " getTno.nextval , ? , ?, sysdate , 0 , 0 ,  ? , ?, 'Y'  ) " );
			break;
		
		/*
		 * 파라미터<?> 순서 정리	- 3개
		 * writer, textno, text
		 */	
		case INSERT_REPLY:
			sql.append( "INSERT into reply VALUES ( " );
			sql.append( " getRno.nextval , ? ,sysdate , ? , ?, 'Y' ) " );
			break;
		
		/*
		 * 파라미터<?> 순서 정리	- 2개
		 * bname, bInfo
		 */	
		case INSERT_BOARD:
			sql.append( "INSERT into board VALUES ( " );
			sql.append( " getBno.nextval , ? , ?, 'Y' ) " );
			break;
		}
		
		return sql.toString();
	}

}
