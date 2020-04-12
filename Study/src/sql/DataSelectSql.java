package sql;
/**
 * 
 * @author	������
 * @since	2020.04.12
 * @see		BoardDTO, ReplyDTO, TextDTO
 * 						
 * 					�� Ŭ������ �Խ��� ������ Ȥ�� ������ ��û�� ����
 * 					�ش� �����ͺ��̽� ������ �������� ���� ���۵Ǿ���.
 * 					
 * 					Edit
 * 					2020.04.12	 ������
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
