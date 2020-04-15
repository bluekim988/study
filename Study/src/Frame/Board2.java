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
import javax.swing.border.LineBorder;

import DAO.BoardDAO;
import DTO.BoardDTO;
import event.SetBoardEvent;

public class Board2 implements ActionListener, MouseListener {
	JPanel nav = new JPanel();
	JPanel list = new JPanel();
	JFrame home = new JFrame("Ȩ ȭ��");
	JButton b1 = new JButton("new �Խ���");
	JButton b2 = new JButton("drop �Խ���");
	public JFrame boardF;
	
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
		list.setBorder(new LineBorder(new Color(100, 102, 100)));
		
		
		// �Խ��� ����Ʈ ȣ��
		setBtn();
		
		
		
		home.add(nav);
		home.add(list);
		
		home.setVisible(true);
		
		
		
		
		
		//�̺�Ʈ �ڵ鸵
		b1.addMouseListener(this);
		b2.addMouseListener(this);
		
		for(int i=0; i<arr.size(); i++) {
			arr.get(i).addMouseListener(new SetBoardEvent(this));;
		}
		
	}

	
	
	/*
	 * �����ͺ��̽��� ������
	 * �Խ��� ��ư ����Ʈ ��������
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
	 * �Խ��� ����
	 * ������ ���̽��� ������ ������ ��
	 * refresh �Լ��� ���� ������ ���ΰ�ħ
	 */
	public void crBoard() {
		String bname = JOptionPane.showInputDialog("�Խ��� �̸� : ");
		String bInfo = JOptionPane.showInputDialog("�Խ��� �Ұ� : ");
		
		BoardDTO dto = new BoardDTO();
		dto.setBname(bname);
		dto.setbInfo(bInfo);
		BoardDAO dao = new BoardDAO();
		int result = dao.createBoard(dto);
		if(result > 0) {
			getBtn(bname);
		}
		refresh();
	}
	
	/*
	 * ��ư �̸� �Է½� ��ư �����Ͽ� list�� �߰�
	 */
	public void getBtn(String name) {
		JButton board = new JButton();
		arr.add(board);
		board.setText(name);
		list.add(board);
	}
	
	/*
	 * �Խ��� ����
	 * ������ ���̽��� ������ ��������� ��
	 * refresh �Լ��� ���� ������ ���ΰ�ħ
	 */
	public void dpBoard() {
		String bname = JOptionPane.showInputDialog("������ �Խ��� �̸� : ");
		String re = JOptionPane.showInputDialog("��� �Ͻ÷��� [ 'N' ] �Է�!");
		
		if(re.equals("N") || re.equals("n")) {
			return;
		}
		BoardDAO dao = new BoardDAO();
		dao.dropBoard(bname);
		refresh();
	}
	
	/*
	 * home ������ ���ΰ�ħ
	 */
	public void refresh() {
		home.dispose();
		home.setVisible(true);
	}
	
	
	
	

	
	
	
	/*
	 * �����Լ�
	 */
	public static void main(String[] args) {
		new Board2();
	}

	
	
	/*
	 * ������ �������̵� ����
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
