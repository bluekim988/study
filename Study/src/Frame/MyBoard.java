package Frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import DAO.BoardDAO;
import DAO.TextDAO;
import DTO.BoardDTO;
import DTO.TextDTO;
import oracle.jdbc.proxy.annotation.GetCreator;

public class MyBoard extends JFrame implements ActionListener, MouseListener{
	JFrame wt, showT;
	JPanel list1, list2, list3;
	JPanel pp ;
	JButton write, submit, close, crBoard;
	JLabel list1_item, list2_item, list3_item, l1, l2;
	JPanel pmain ;
	JTable table1, table2, table3;
	Vector value;
	Vector<String> nav;
	String[] nav1 = {"글번호", "제목", "작성자", "작성일", "조회수", "좋아요"};
	DefaultTableModel model;
	JScrollPane jsc;
	JComboBox cb;
	JTextField f1, f2;
	JTextArea text;

	
	public MyBoard() {
		super("테스트");
		setLayout(null);
		setBounds(400, 0, 900, 1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		// nav바
//		JPanel p1 = new JPanel();
//		p1.setLayout(null);
//		p1.setBounds(40, 10, 800, 100);
//		p1.setBorder(new LineBorder(new Color(66, 135, 245)));
		list1 = new JPanel();
		list2 = new JPanel();
		list3 = new JPanel();
		list1_item = new JLabel("방명록");
		list2_item = new JLabel("자료공유");
		list3_item = new JLabel("대화나눔");
		list1.setBounds(230, 40, 80, 30);
		list2.setBounds(350, 40, 80, 30);
		list3.setBounds(480, 40, 80, 30);
		list1.setLayout(null);
		list2.setLayout(null);
		list3.setLayout(null);
		list1_item.setBounds(20, 0, 60, 20);
		list2_item.setBounds(15, 0, 60, 20);
		list3_item.setBounds(15, 0, 60, 20);
		list1_item.setFont(new Font("고딕", Font.BOLD, 15));
		list2_item.setFont(new Font("고딕", Font.BOLD, 15));
		list3_item.setFont(new Font("고딕", Font.BOLD, 15));
		list1.add(list1_item);
		list2.add(list2_item);
		list3.add(list3_item);
//		p1.add(list1);
//		p1.add(list2);
//		p1.add(list3);
//		p1.add(list1);

		
		// 게시판 정보 표시 구역
		pp = new JPanel();
		l1 = new JLabel("");
		l2 = new JLabel("");
		l1.setFont(new Font("고딕", Font.BOLD, 15));
		l2.setFont(new Font("고딕", Font.PLAIN,10));
		
		pp.setBounds(80, 120, 500 , 80);
//		pp.setBorder(new LineBorder(new Color(66, 135, 245)));
		pp.setLayout(null);
		l1.setBounds(0, 0, 200, 60);
		l2.setBounds(0, 20, 300, 60);
		
		pp.add(l1);
		pp.add(l2);
		
		write = new JButton("글쓰기");
		write.setBorderPainted(false);
		write.setBackground(new Color(132, 227, 157));
		write.setBounds(750, 160, 80, 40);
		
		
		crBoard = new JButton("new게시판");
		crBoard.setBorderPainted(false);
		crBoard.setBackground(new Color(132, 227, 157));
		crBoard.setBounds(740, 100, 100, 40);
		
		
		// 게시물 목록 표시 구역
//		pmain = new JPanel();
//		pmain.setLayout(null);
//		pmain.setBounds(50, 230, 780, 700);
//		pmain.setBorder(new LineBorder(new Color(66, 135, 245)));

		// 네브 목록 벡터
//		nav = new Vector<String>();
//		nav.add("글번호");
//		nav.add("제목");
//		nav.add("작성자");
//		nav.add("작성일");
//		nav.add("조회수");
//		nav.add("좋아요");
		
		BoardDAO dao = new BoardDAO();
		String name = list1_item.getText();
		
		BoardDTO board = dao.selectBoard(name);

		l1.setText(board.getBname());
		l2.setText(board.getbInfo());
		// 게시글 목록 저장한 벡터
//		value = new Vector();
//		
//		model = new DefaultTableModel(value, nav);
//		table = new JTable(model);
//		jsc = new JScrollPane(table);
//		jsc.setBounds(50, 230, 780, 700);
//		pmain.add(table);
		
		TextDAO tdao = new TextDAO();
		nav = new Vector<String>();
		nav.add("글번호");
		nav.add("제목");
		nav.add("작성자");
		nav.add("작성일");
		nav.add("조회수");
		nav.add("좋아요");
		// 게시글 목록 저장한 벡터
		value = new Vector();
		TreeSet<TextDTO> set = tdao.selectTextForMain("방명록");
		Iterator<TextDTO> itr = set.iterator();
		while(itr.hasNext()) {
			TextDTO inst = itr.next();
			Vector<String> info = new Vector<String>();
			info.add(inst.getTno()+"");
			info.add(inst.getTitle());
			info.add(inst.getWriter());
			info.add(inst.getCrdate());
			info.add(inst.getCount()+"");
			info.add(inst.getLike()+"");
			value.add(info);
		}
		model = new DefaultTableModel(value, nav);
		table1 = new JTable(model);
		table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jsc = new JScrollPane(table1);
		jsc.setBounds(50, 230, 780, 700);

//		add(p1);
		add(list1);
		add(list2);
		add(list3);
		add(pp);
		add(jsc);
		add(write);
		add(crBoard);
		
		
		
		
		list1.addMouseListener(this);
		list2.addMouseListener(this);
		list3.addMouseListener(this);
		write.addActionListener(this);
		crBoard.addActionListener(this);
		
		table1.addMouseListener(this);
//		table2.addMouseListener(this);
//		table3.addMouseListener(this);

		
	}
	
	
	/*
	 * 게시판 버튼 선택하면 게시판 정보 출력 화면 바꾸기
	 */
	public void setboard1() {
		BoardDAO dao = new BoardDAO();
		String name = list1_item.getText();
		
		BoardDTO board = dao.selectBoard(name);

		l1.setText(board.getBname());
		l2.setText(board.getbInfo());
	}
	
	public void setboard2() {
		BoardDAO dao = new BoardDAO();
		String name = list2_item.getText();
		
		BoardDTO board = dao.selectBoard(name);

		l1.setText(board.getBname());
		l2.setText(board.getbInfo());
	}
	
	public void setboard3() {
		BoardDAO dao = new BoardDAO();
		String name = list3_item.getText();
		
		BoardDTO board = dao.selectBoard(name);

		l1.setText(board.getBname());
		l2.setText(board.getbInfo());
	}
	
	
	/*
	 * 게시판 버튼 선택하면 게시판 목록 출력 화면 바꾸기
	 */
	
	public void setList1() {
		TextDAO tdao = new TextDAO();

		// 게시글 목록 저장한 벡터
		value = new Vector();
		TreeSet<TextDTO> set = tdao.selectTextForMain("방명록");
		Iterator<TextDTO> itr = set.iterator();
		while(itr.hasNext()) {
			TextDTO inst = itr.next();
			Vector<String> info = new Vector<String>();
			info.add(inst.getTno()+"");
			info.add(inst.getTitle());
			info.add(inst.getWriter());
			info.add(inst.getCrdate());
			info.add(inst.getCount()+"");
			info.add(inst.getLike()+"");
			value.add(info);
		}
		model = new DefaultTableModel(value, nav);
		table1 = new JTable(model);
		table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jsc = new JScrollPane(table1);
		jsc.setBounds(50, 230, 780, 700);
		add(jsc);

	}
	
	public void setList2() {
		TextDAO tdao = new TextDAO();

		// 게시글 목록 저장한 벡터
		value = new Vector();
		TreeSet<TextDTO> set = tdao.selectTextForMain("자료공유");
		Iterator<TextDTO> itr = set.iterator();
		while(itr.hasNext()) {
			TextDTO inst = itr.next();
			Vector<String> info = new Vector<String>();
			info.add(inst.getTno()+"");
			info.add(inst.getTitle());
			info.add(inst.getWriter());
			info.add(inst.getCrdate());
			info.add(inst.getCount()+"");
			info.add(inst.getLike()+"");
			value.add(info);
		}
		model = new DefaultTableModel(value, nav);
		table2 = new JTable(model);
		table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jsc = new JScrollPane(table2);
		jsc.setBounds(50, 230, 780, 700);
		add(jsc);
	}
	
	public void setList3() {
		TextDAO tdao = new TextDAO();

		// 게시글 목록 저장한 벡터
		value = new Vector();
		TreeSet<TextDTO> set = tdao.selectTextForMain("대화나눔");
		Iterator<TextDTO> itr = set.iterator();
		while(itr.hasNext()) {
			TextDTO inst = itr.next();
			Vector<String> info = new Vector<String>();
			info.add(inst.getTno()+"");
			info.add(inst.getTitle());
			info.add(inst.getWriter());
			info.add(inst.getCrdate());
			info.add(inst.getCount()+"");
			info.add(inst.getLike()+"");
			value.add(info);
		}
		model = new DefaultTableModel(value, nav);
		table3 = new JTable(model);
		table3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jsc = new JScrollPane(table3);
		jsc.setBounds(50, 230, 780, 700);
		add(jsc);
	}
	
	/*
	 * 글쓰기 버튼 이벤트 기능
	 */
	public void writeText() {
		wt = new JFrame();
		wt.setBounds(500, 50, 700, 900);
		wt.setLayout(null);
		
		JLabel l1 = new JLabel("게시판");
		l1.setBounds(20, 20, 50, 50);
		JLabel l2 = new JLabel("제목");
		l2.setBounds(20, 70, 50, 50);
		JLabel l3 = new JLabel("닉네임");
		l3.setBounds(20, 120, 50, 50);
		
		String[] blist = {"방명록", "자료공유", "대화나눔"};
		cb = new JComboBox(blist);
		cb.setBounds(70, 35, 100, 20);	//게시판선택
		f1 = new JTextField();
		f1.setBounds(70, 80, 400, 30);	//제목
		f2 = new JTextField();
		f2.setBounds(70, 130, 200, 30);	//닉네임
		text = new JTextArea();	//본문작성
		text.setBounds(45, 180, 600, 550);
		
		
		submit = new JButton("저장");
		submit.setBounds(530, 800, 60, 30);
		close = new JButton("취소");
		close.setBounds(600, 800, 60, 30);
		
		wt.add(l1);
		wt.add(l2);
		wt.add(l3);
		wt.add(f1);
		wt.add(f2);
		wt.add(cb);
		wt.add(text);
		wt.add(submit);
		wt.add(close);
		
		wt.setVisible(true);
		
		
		submit.addActionListener(this);
		close.addActionListener(this);
	}
	
	/*
	 * 입력된 Text submit 기능
	 */
	public void submitText() {
		TextDTO inst = new TextDTO();
		TextDAO dao = new TextDAO();
		
		String title = f1.getText();
		String writer = f2.getText();
		String stext = text.getText();
		int bno = 0;
		String bd = (String)cb.getSelectedItem();
		if(bd.equals("방명록")) {
			bno = 11;
		} else if(bd.equals("자료공유")) {
			bno = 12;
		} else if(bd.equals("대화나눔")) {
			bno = 13;
		}
		
		inst.setTitle(title);
		inst.setWriter(writer);
		inst.setText(stext);
		inst.setBno(bno);
		
		dao.createText(inst);
	}
	
	
	/*
	 * new게시판 버튼 이벤트 처리
	 */
	public void createBoard() {
		String bname = JOptionPane.showInputDialog("게시판 이름 : ");
		String bInfo = JOptionPane.showInputDialog("게시판 소개 : ");
		
		BoardDTO b1 = new BoardDTO();
		b1.setBname(bname);
		b1.setbInfo(bInfo);
		BoardDAO dao = new BoardDAO();
		int result = dao.createBoard(b1);
		String str = "생성 실패";
		if(result >0) {
			str = "생성 완료";
		}
		JOptionPane.showMessageDialog(null, str);		
	}
	
	// 게시글 불러오기 이벤트 처리 
	public void showText(Object value) {
		showT = new JFrame();
		showT.setBounds(500, 50, 700, 900);
		showT.setLayout(null);
//		showT.setVisible(true);
		String st = value.toString();
		int tno = Integer.parseInt(st);
		TextDAO dao = new TextDAO();
		TextDTO data = dao.selectTextForIn(tno);
		JOptionPane.showMessageDialog(null, data.getText());
		

	}
	
	/*
	 * 메인함수
	 */
	public static void main(String[] args) {
		new MyBoard();
	}

	
	/*
	 * 액션리스너 처리하기
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		
		if(btn == write) {
			writeText();
		} else if(btn == submit) {
			submitText();
			wt.dispose();		
		} else if(btn == close) {
			wt.dispose();
		} else if(btn == crBoard) {
			createBoard();
		}
		
	}
	
	/*
	 * 마우스 리스너
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
//		JPanel eve = (JPanel)e.getSource();
//		
//		if(eve == list1) {
//			list1.setBackground(null);		
//		} if(eve == list2) {
//			list2.setBackground(null);			
//		} else if(eve == list3) {
//			list3.setBackground(null);			
//		}
	
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
//		JPanel eve = (JPanel)e.getSource();
//		
//		if(eve == list1) {
//			list1.setBackground(new Color(245, 105, 142));		
//		} if(eve == list2) {
//			list2.setBackground(new Color(245, 105, 142));			
//		} else if(eve == list3) {
//			list3.setBackground(new Color(245, 105, 142));			
//		}
//		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JComponent eve = (JComponent)e.getSource();
		JPanel li = null;
		JTable tb = null;
		if(eve instanceof JPanel) {
			li = (JPanel) eve;
		} else if (eve instanceof JTable) {
			tb = (JTable)eve;
		}
		
		
		if(li == list1) {
			setboard1();
			setList1();
		} else if(li == list2) {
			setboard2();			
			setList2();
		} else if(li == list3) {
			setboard3();			
			setList3();
		} else if (tb == table1) {
			int row = table1.getSelectedRow();
			int col = table1.getSelectedColumn();
			Object value = table1.getValueAt(row, col);
			showText(value);
		}
		
	}

}
