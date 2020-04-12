
import DTO.*;
import DAO.*;
import DB.*;
import sql.*;

public class Test01 {

	public Test01() {
//		CreateBoard();
//		UpdateBoard();
//		deleteBoard();
		
		showBoardInfo();
	}
	
	public void showBoardInfo() {
		BoardDAO dao = new BoardDAO();
		BoardDTO board = new BoardDTO();
		board = dao.selectBoard("����");
		
		System.out.println(board.getBno());
		System.out.println(board.getBname());
		System.out.println(board.getbInfo());
	}
	
	public void CreateBoard() {
		BoardDTO b1 = new BoardDTO();
		b1.setBname("����");
		b1.setbInfo("�Բ� �Ⱥ��λ縦 �ְ�޴� �����Դϴ�.");
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
