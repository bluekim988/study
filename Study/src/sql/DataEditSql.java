package sql;
/**
 * 
 * @author	������
 * @since	2020.04.12
 * @see		BoardDTO, ReplyDTO, TextDTO
 * 						
 * 					�� Ŭ������ �Խ��� ������ Ȥ�� ������ ��û�� ����
 * 					�����ͺ��̽� ������ ���� ���۵Ǿ���.
 * 					
 * 					Edit
 * 					2020.04.12	 ������
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
	 	���õ� �������� Ư�� ������ �����ϴ� ���ǹ��� ��ȯ�Ѵ�.
	 	
	 	���� ���Ե� ������ ��ü ���ǹ� ����Ʈ
	 	-text ���̺� title
	 	-text ���̺� text
	 	-reply ���̺� text
	 	-board ���̺� bname
	 	-board ���̺� binfo
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
	  	������ ������ ���� slq ���ǹ��� ��ȯ�Ѵ�.
	  	��, ��ȯ�� ���ǹ��� DB�󿡼� �����͸� �����ϴ°��� �ƴ�,
	  	�������� ���� ���θ� ������� ��ü�� ���̴�.
	  	(�̴� �������� ����� �����Ⱓ �����ϱ� �����̴�.)
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
	 	�������� ī��Ʈ�� ������Ű������ ���ǹ��� ��ȯ�Ѵ�.
	 	ī��Ʈ�� �ѹ� ��ɿ�û�� 1�� �����Ѵ�.
	 	�Ķ����<?> ���� �ش��ϴ� �ؽ�Ʈ��ȣ�� �Է��Ѵ�.
	 	�� ���� �� �����ؾ� �Ѵ�.
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
	 	�������� ���ƿ並 ������Ű������ ���ǹ��� ��ȯ�Ѵ�.
	 	ī��Ʈ�� �ѹ� ��ɿ�û�� 1�� �����Ѵ�.
	 	�Ķ����<?> ���� �ش��ϴ� �ؽ�Ʈ��ȣ�� �Է��Ѵ�.
	 	�� ���� �� �����ؾ� �Ѵ�.
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
