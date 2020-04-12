package Frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import DAO.BoardDAO;
import DTO.BoardDTO;

public class MyBoard extends JFrame implements ActionListener, MouseListener{

	JPanel list1, list2, list3;
	JPanel pp = new JPanel();
	JLabel list1_item, list2_item, list3_item, l1, l2;
	public void setboard1() {
		BoardDAO dao = new BoardDAO();
		BoardDTO board = dao.selectBoard("방명록");
		l1.setText(board.getBname());
		l2.setText(board.getbInfo());

	}
	
	public MyBoard() {
		super("테스트");
		setLayout(null);
		setBounds(400, 0, 900, 1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	

		
		JPanel p1 = new JPanel();
		p1.setLayout(null);
		p1.setBounds(40, 10, 800, 100);
		p1.setBorder(new LineBorder(new Color(66, 135, 245)));
		list1 = new JPanel();
		list2 = new JPanel();
		list3 = new JPanel();
		JLabel list1_item = new JLabel("방명록");
		JLabel list2_item = new JLabel("자료공유");
		JLabel list3_item = new JLabel("대화나눔");
		list1.setBounds(230, 40, 80, 30);
		list2.setBounds(350, 40, 80, 30);
		list3.setBounds(480, 40, 80, 30);
		list1.setLayout(null);
		list2.setLayout(null);
		list3.setLayout(null);
		list1_item.setBounds(20, 0, 60, 20);
		list2_item.setBounds(15, 0, 60, 20);
		list3_item.setBounds(15, 0, 60, 20);
		list1_item.setFont(new Font("고딕", Font.BOLD, 12));
		list2_item.setFont(new Font("고딕", Font.BOLD, 12));
		list3_item.setFont(new Font("고딕", Font.BOLD, 12));
		list1.add(list1_item);
		list2.add(list2_item);
		list3.add(list3_item);
		p1.add(list1);
		p1.add(list2);
		p1.add(list3);
		p1.add(list1);

		
		
		pp = new JPanel();
		l1 = new JLabel("");
		l2 = new JLabel("");
		l1.setFont(new Font("고딕", Font.BOLD, 15));
		l2.setFont(new Font("고딕", Font.PLAIN,10));
		
		pp.setBounds(40, 120, 800 , 80);
		pp.setBorder(new LineBorder(new Color(66, 135, 245)));
		pp.setLayout(null);
		l1.setBounds(0, 0, 200, 60);
		l2.setBounds(0, 20, 300, 60);
		
		pp.add(l1);
		pp.add(l2);
		
		
		
		
		add(p1);
		add(pp);
		
		
		list1.addMouseListener(this);
		list2.addMouseListener(this);
		list3.addMouseListener(this);
		
	}

	public static void main(String[] args) {
		new MyBoard();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
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
		JPanel eve = (JPanel)e.getSource();
		
		if(eve == list1) {
			list1.setBackground(null);		
		} if(eve == list2) {
			list2.setBackground(null);			
		} else if(eve == list3) {
			list3.setBackground(null);			
		}
	
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		JPanel eve = (JPanel)e.getSource();
		
		if(eve == list1) {
			list1.setBackground(new Color(168, 195, 237));		
		} if(eve == list2) {
			list2.setBackground(new Color(168, 195, 237));			
		} else if(eve == list3) {
			list3.setBackground(new Color(168, 195, 237));			
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JPanel eve = (JPanel)e.getSource();
		
		if(eve == list1) {
			setboard1();	
		} if(eve == list2) {
			list2.setBackground(new Color(168, 195, 237));			
		} else if(eve == list3) {
			list3.setBackground(new Color(168, 195, 237));			
		}
		
	}

}
