package event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import DAO.BoardDAO;
import DAO.TextDAO;
import DTO.BoardDTO;
import DTO.TextDTO;
import Frame.Board2;

public class SetBoardEvent implements MouseListener{
	Board2 b2;
	
	public SetBoardEvent(Board2 b) {
		b2 = b;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JButton btn = (JButton)e.getSource();
//		JOptionPane.showMessageDialog(null, btn.getText());
		TextDAO dao = new TextDAO();
		
		
		Vector<String> nav = new Vector<String>();
		nav.add("글번호");
		nav.add("제목");
		nav.add("작성자");
		nav.add("작성일");
		nav.add("조회수");
		nav.add("좋아요");
		
		Vector value = new Vector();
		TreeSet<TextDTO> set = dao.selectTextForMain(btn.getText());
		Iterator<TextDTO> itr = set.iterator();
		while(itr.hasNext()) {
			TextDTO inst = itr.next();
			Vector<String> data = new Vector<String>();
			data.add(inst.getTno()+"");
			data.add(inst.getTitle());
			data.add(inst.getWriter());
			data.add(inst.getCrdate());
			data.add(inst.getCount()+"");
			data.add(inst.getLike()+"");
			value.add(data);
		}
		
		// 해당 게시판 프레임 생성
		b2.boardF = new JFrame();
		b2.boardF.setBounds(500, 50, 800, 900);
		b2.boardF.setLayout(null);
		
		DefaultTableModel model = new DefaultTableModel(value, nav);
		JTable table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jsc = new JScrollPane(table);
		jsc.setBounds(20, 120, 750, 690);
		
		
		BoardDAO bdao = new BoardDAO();
		BoardDTO bdto = bdao.selectBoard(btn.getText());
		
		
		JPanel info = new JPanel();
		info.setBounds(0, 10, 800, 100);
		info.setLayout(null);
//		info.setBorder(new LineBorder(new Color(100, 102, 100)));
		JLabel l1 = new JLabel();
		l1.setBounds(50, 0, 700, 40);
		l1.setText(bdto.getBname());
		JLabel l2 = new JLabel();
		l2.setBounds(50, 30, 700, 40);
		l2.setText(bdto.getbInfo());
		info.add(l1);
		info.add(l2);
		
		
		
		
		b2.boardF.add(info);
		b2.boardF.add(jsc);
		
		b2.boardF.setVisible(true);
		
		
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

}
