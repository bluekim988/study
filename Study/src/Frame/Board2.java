package Frame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import DAO.BoardDAO;
import DTO.BoardDTO;
import event.SetBoardEvent;

public class Board2 implements ActionListener, MouseListener {
	JPanel nav = new JPanel();
	JPanel list = new JPanel();
	JFrame home = new JFrame("홈 화면");
	JButton b1 = new JButton("new 게시판");
	JButton b2 = new JButton("drop 게시판");
	public JFrame boardF;
	public JTable table;
	
	ArrayList<JButton> arr = new ArrayList<JButton>();

	
	public Board2() {
		
		home.setLayout(null);
		home.setSize(800, 900);
		home.setDefaultCloseOperation(home.EXIT_ON_CLOSE);
		
		nav.setBounds(0, 0, 800, 50);
		nav.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
//		nav.setBorder(new LineBorder(new Color(100, 102, 100)));
		
		nav.add(b1);
		nav.add(b2);
		
		
		list.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		list.setBounds(0, 50, 800, 120);
//		list.setBorder(new LineBorder(new Color(100, 102, 100)));
		
		
		// 게시판 리스트 호출
		setBtn();
		
		
		
		home.add(nav);
		home.add(list);
		
		home.setVisible(true);
		
		
		
		
		
		//이벤트 핸들링
		b1.addMouseListener(this);
		b2.addMouseListener(this);
		
		
		
		
		
		// 게시판 선택 이벤트 핸들링
		for(int i=0; i<arr.size(); i++) {
			arr.get(i).addMouseListener(new SetBoardEvent(this));;
		}
		
	}

	
	
	/*
	 * 데이터베이스에 접속해
	 * 게시판 버튼 리스트 가져오기
	 */
	public void setBtn() {
		BoardDAO bd = new BoardDAO();
		TreeSet<BoardDTO> set = bd.selectBoardAll();
		Iterator<BoardDTO> itr = set.iterator();
		while(itr.hasNext()) {
			BoardDTO inst = itr.next();
			getBtn(inst.getBname());
		}
	}
	/*
	 * 버튼 이름 입력시 버튼 생성하여 list에 추가
	 */
	public void getBtn(String name) {
		JButton board = new JButton();
		board.setText(name);
		arr.add(board);
		list.add(board);
	}
	
	/*
	 * 게시판 생성
	 * 데이터 베이스에 정보를 저장한 뒤
	 * refresh 함수를 통해 프레임 새로고침
	 */
	public void crBoard() {
		String bname = JOptionPane.showInputDialog("게시판 이름 : ");
		String bInfo = JOptionPane.showInputDialog("게시판 소개 : ");
		String re = JOptionPane.showInputDialog("취소 하시려면 [ N ] 입력!\n계속 진행 하시려면 [ 엔터 ]");
		if(re.equals("N") || re.equals("n")) {
			return;
		}
		
		BoardDTO dto = new BoardDTO();
		dto.setBname(bname);
		dto.setbInfo(bInfo);
		BoardDAO dao = new BoardDAO();
		int result = dao.createBoard(dto);
		if(result > 0) {
			getBtn(bname);
		}
		
//		removeNavBtn();
//		setBtn();
		refresh();
	}
	
	
	/*
	 * 게시판 삭제
	 * 데이터 베이스에 정보를 완전드랍한 뒤
	 * refresh 함수를 통해 프레임 새로고침
	 */
	public void dpBoard() {
		String bname = JOptionPane.showInputDialog("삭제할 게시판 이름 : ");
		String re = JOptionPane.showInputDialog("취소 하시려면 [ N ] 입력!\n계속 진행 하시려면 [ 엔터 ]");
		
		if(re.equals("N") || re.equals("n")) {
			return;
		}
		BoardDAO dao = new BoardDAO();
		dao.dropBoard(bname);
		
		removeListBtn(bname);
//		setBtn();
//		refresh();
	}
	
	public void removeListBtn(String bname) {
		int index = -1;
		ot:
		for(int i=0; i<arr.size(); i++) {
			JButton inst = arr.get(i);
			if(inst.getText().equals(bname)) {				
				index = i;
				break ot;
			}
		}
		
		list.remove(index);
		refresh();
	}
	
	/*
	 * home 프레임 새로고침
	 */
	public void refresh() {
		home.dispose();
		new Board2();
	}
	
	
	
	

	
	
	
	/*
	 * 메인함수
	 */
	public static void main(String[] args) {
		new Board2();
	}

	
	
	/*
	 * 리스너 오버라이딩 구문
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		Object inst = e.getSource();
		if(inst instanceof JButton) {
			JButton btn = (JButton)inst;
			if(btn == b1) {
				crBoard();
			} else if (btn == b2) {
				dpBoard();
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
