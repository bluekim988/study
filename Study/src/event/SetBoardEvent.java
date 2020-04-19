package event;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import Frame.Board2;

public class SetBoardEvent implements MouseListener{
	Board2 b2;
	JTable t2;
	
	public SetBoardEvent() {
	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JButton btn = (JButton)e.getSource();
//		JOptionPane.showMessageDialog(null, btn.getText());
		TextDAO dao = new TextDAO();
		
		
		Vector<String> nav = new Vector<String>();
		nav.add("�۹�ȣ");
		nav.add("����");
		nav.add("�ۼ���");
		nav.add("�ۼ���");
		nav.add("��ȸ��");
		nav.add("���ƿ�");
		
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
		
		// �ش� �Խ��� ������ ����
		JFrame boardF = new JFrame();
		boardF.setBounds(500, 50, 800, 900);
		boardF.setLayout(null);
		
		DefaultTableModel model = new DefaultTableModel(value, nav);
		t2 = new JTable(model);
		t2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jsc = new JScrollPane(t2);
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
		
		
		// �۾��� ��ư ����
		JButton wtbtn = new JButton("�۾���");
		wtbtn.setBounds(650, 50, 80, 30);
		
		
	
		boardF.add(info);
		boardF.add(jsc);
		boardF.add(wtbtn);
		
		boardF.setVisible(true);
		
		// �۾��� ��ư �̺�Ʈ
		wtbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame wt = new JFrame();
				wt.setBounds(500, 50, 700, 900);
				wt.setLayout(null);
				
				JLabel l1 = new JLabel("�Խ���");
				l1.setBounds(20, 20, 50, 50);
				JLabel l2 = new JLabel("����");
				l2.setBounds(20, 70, 50, 50);
				JLabel l3 = new JLabel("�г���");
				l3.setBounds(20, 120, 50, 50);
				
				BoardDAO bd = new BoardDAO();
				TreeSet<BoardDTO> set = bd.selectBoardAll();
				String[] blist = new String[set.size()]; 
				Iterator<BoardDTO> itr = set.iterator();
				for(int i = 0; i < blist.length; i++) {
					BoardDTO inst = itr.next();
					blist[i] = inst.getBname();
				}
				
				JComboBox cb = new JComboBox(blist);
				cb.setBounds(70, 35, 100, 20);	//�Խ��Ǽ���
				JTextField f1 = new JTextField();
				f1.setBounds(70, 80, 400, 30);	//����
				JTextField f2 = new JTextField();
				f2.setBounds(70, 130, 200, 30);	//�г���
				JTextArea text = new JTextArea();	//�����ۼ�
				text.setLineWrap(true);
				text.setBounds(45, 180, 600, 550);
				
				JButton submit = new JButton("����");
				submit.setBounds(530, 800, 60, 30);
				JButton close = new JButton("���");
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
				
				// ���ۼ� �Ϸ��ư Ŭ���� �̺�Ʈ �߻�
				submit.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						TextDTO inst = new TextDTO();
						TextDAO dao = new TextDAO();
						
						String title = f1.getText();
						String writer = f2.getText();
						String stext = text.getText();
						int bno = 0;
						String bd = (String)cb.getSelectedItem();
						if(bd.equals("����")) {
							bno = 11;
						} else if(bd.equals("�ڷ����")) {
							bno = 12;
						} else if(bd.equals("��ȭ����")) {
							bno = 13;
						}
						
						inst.setTitle(title);
						inst.setWriter(writer);
						inst.setText(stext);
						inst.setBno(bno);
						
						dao.createText(inst);
						
						wt.dispose();
						
					}
				});
				//�� �ۼ� ��� ��ư Ŭ���� �̺�Ʈ �߻�
				close.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						wt.dispose();
						
					}
				});
			}
		});
		
		t2.addMouseListener(new MouseListener() {
			
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
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				 
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = t2.getSelectedRow();
				int col = t2.getSelectedColumn();
				Object value = t2.getValueAt(row, col);
				String st = value.toString();
				int tno = Integer.parseInt(st);
				TextDAO dao = new TextDAO();
				TextDTO data = dao.selectTextForIn(tno);
//				JOptionPane.showMessageDialog(null, data.getText());
				JFrame textFrame = new JFrame();
				textFrame.setBounds(500, 50, 800, 900);
				textFrame.setLayout(null);
				
				JPanel p1 = new JPanel();
				JPanel p2 = new JPanel();
				p1.setLayout(new GridLayout(3, 1, 10, 10));
				p1.setBounds(10, 10, 730, 100);
				p1.add(new JLabel("���� : " + data.getTitle()));
				p1.add(new JLabel("�۾��� : " + data.getWriter()));
				p1.add(new JLabel("�ۼ��� : " + data.getCrdate()));
//				p1.setBorder(new LineBorder(new Color(100, 102, 100)));
				JTextArea area = new JTextArea(data.getText());
				area.setLineWrap(true);
				area.setSize(720, 640);
				JScrollPane scl = new JScrollPane(area);
				p2.add(scl);
				p2.setBounds(10, 160, 730, 650);
//				p2.setBorder(new LineBorder(new Color(100, 102, 100)));
				
				textFrame.add(p1);
				textFrame.add(p2);
				
				
				textFrame.setVisible(true);
				
				
				
			}
		});
		
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
