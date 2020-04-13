
import java.util.Iterator;
import java.util.TreeSet;

import DAO.BoardDAO;
import DAO.TextDAO;
import DTO.BoardDTO;
import DTO.TextDTO;

public class Test01 {

	public Test01() {
		selectText();
//		insertText();
//		CreateBoard();
//		UpdateBoard();
//		deleteBoard();
		
//		showBoardInfo();
	}
	public void selectText() {
		TextDAO dao = new TextDAO();
		TreeSet<TextDTO> set = dao.selectTextForMain(11);
		
		Iterator<TextDTO> itr = set.iterator();
		while(itr.hasNext()) {
			TextDTO dto = itr.next();
			System.out.println(dto.getCrdate());
			
		}
	}
	
	public void insertText() {
		TextDTO dto = new TextDTO();
		dto.setTitle("ù��°�� �׽�Ʈ");
		dto.setWriter("blue");
		dto.setText("ù��°���� �׽�Ʈ �մϴ�. �̰��� �����Է��ϴ°� �Դϴ�.");
		dto.setBno(11);
		
		TextDAO dao = new TextDAO();
		int result = dao.createText(dto);
		
		if(result >0) {
			System.out.println("�Է¼���");
		} else {
			System.out.println("�Է½���");			
		}
	}
	
	public void showBoardInfo() {
		BoardDAO dao = new BoardDAO();
		BoardDTO board = dao.selectBoard("����");
		
		System.out.println(board.getBno());
		System.out.println(board.getBname());
		System.out.println(board.getbInfo());
	}
	
	public void CreateBoard() {
		BoardDTO b1 = new BoardDTO();
		b1.setBname("��ȭ����");
		b1.setbInfo("�� �Խ����� �ݰ��� �λ縦 �ְ�ޱ����� �����Դϴ�.");
		BoardDAO dao = new BoardDAO();
		int result = dao.createBoard(b1);
		if(result >0) {
			System.out.println("�Է¼���");
		} else {
			System.out.println("�Է½���");			
		}
	}
	
	public void UpdateBoard() {
		BoardDAO dao = new BoardDAO();
		int result = dao.updateBoardName("�Ⱥ��λ�", "����");
		if(result >0) {
			System.out.println("��������");
		} else {
			System.out.println("��������");			
		}
	}
	
	public void deleteBoard() {
		BoardDAO dao = new BoardDAO();
		int result = dao.deleteBoard("�Ⱥ��λ�");
		if(result >0) {
			System.out.println("��������");
		} else {
			System.out.println("��������");			
		}
	}

	public static void main(String[] args) {
		new Test01();

	}

}
