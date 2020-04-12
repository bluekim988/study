
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
		board = dao.selectBoard("방명록");
		
		System.out.println(board.getBno());
		System.out.println(board.getBname());
		System.out.println(board.getbInfo());
	}
	
	public void CreateBoard() {
		BoardDTO b1 = new BoardDTO();
		b1.setBname("방명록");
		b1.setbInfo("함께 안부인사를 주고받는 공간입니다.");
		BoardDAO dao = new BoardDAO();
		int result = dao.createBoard(b1);
		if(result >0) {
			System.out.println("입력성공");
		} else {
			System.out.println("입력실패");			
		}
	}
	
	public void UpdateBoard() {
		BoardDAO dao = new BoardDAO();
		int result = dao.updateBoardName("안부인사", "방명록");
		if(result >0) {
			System.out.println("수정성공");
		} else {
			System.out.println("수정실패");			
		}
	}
	
	public void deleteBoard() {
		BoardDAO dao = new BoardDAO();
		int result = dao.deleteBoard("안부인사");
		if(result >0) {
			System.out.println("삭제성공");
		} else {
			System.out.println("삭제실패");			
		}
	}

	public static void main(String[] args) {
		new Test01();

	}

}
