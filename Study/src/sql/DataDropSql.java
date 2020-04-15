package sql;
/**
 * 
 * @author	������
 * @since	2020.04.12
 * @see		BoardDTO, ReplyDTO, TextDTO
 * 						
 * 					�� Ŭ������ �����ͺ��̽����� ������ �����͸�
 * 					�����ϰ� �����ϴ� sql ����� ��ȯ�Ѵ�.
 * 					
 * 					Edit
 * 					2020.04.12	 ������
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
