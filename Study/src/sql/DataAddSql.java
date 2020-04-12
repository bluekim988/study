package sql;
/**
 * 
 * @author	������
 * @since	2020.04.12
 * @see		BoardDTO, ReplyDTO, TextDTO
 * 						
 * 					�� Ŭ������ �Խ��� ������ Ȥ�� ������ ��û�� ����
 * 					�����ͺ��̽��� ���ο� �����͸� �߰��ϱ� ���� ���۵Ǿ���.
 * 					
 * 					Edit
 * 					2020.04.12	 ������
 */

public class DataAddSql {
	public final int INSERT_TEXT = 1001;
	public final int INSERT_REPLY = 1002;
	public final int INSERT_BOARD = 1003;
	
	public String getSql(int code) {
		StringBuffer sql = new StringBuffer();
		
		switch (code) {
		
		/*
		 * �Ķ����<?> ���� ����	- 4��
		 * title, writer, text, bno
		 */
		case INSERT_TEXT:
			sql.append( "INSERT into text VALUES ( " );
			sql.append( " getTno.nextval , ? , ?, sysdate , 0 , 0 ,  ? , ?, 'Y'  ) " );
			break;
		
		/*
		 * �Ķ����<?> ���� ����	- 3��
		 * writer, textno, text
		 */	
		case INSERT_REPLY:
			sql.append( "INSERT into reply VALUES ( " );
			sql.append( " getRno.nextval , ? ,sysdate , ? , ?, 'Y' ) " );
			break;
		
		/*
		 * �Ķ����<?> ���� ����	- 2��
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
