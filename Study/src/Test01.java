
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
		dto.setTitle("첫번째글 테스트");
		dto.setWriter("blue");
		dto.setText("첫번째글을 테스트 합니다. 이곳은 내용입력하는곳 입니다.");
		dto.setBno(11);
		
		TextDAO dao = new TextDAO();
		int result = dao.createText(dto);
		
		if(result >0) {
			System.out.println("입력성공");
		} else {
			System.out.println("입력실패");			
		}
	}
	
	public void showBoardInfo() {
		BoardDAO dao = new BoardDAO();
		BoardDTO board = dao.selectBoard("방명록");
		
		System.out.println(board.getBno());
		System.out.println(board.getBname());
		System.out.println(board.getbInfo());
	}
	
	public void CreateBoard() {
		BoardDTO b1 = new BoardDTO();
		b1.setBname("대화나눔");
		b1.setbInfo("이 게시판은 반갑게 인사를 주고받기위한 공간입니다.");
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
