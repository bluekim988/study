package Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DAO.BoardDAO;
import DAO.TextDAO;
import DTO.BoardDTO;
import DTO.TextDTO;

public class WriteBtnFrame implements ActionListener {

	public WriteBtnFrame() {
		
	}

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
		for (int i = 0; i < blist.length; i++) {
			BoardDTO inst = itr.next();
			blist[i] = inst.getBname();
		}

		JComboBox cb = new JComboBox(blist);
		cb.setBounds(70, 35, 100, 20); // �Խ��Ǽ���
		JTextField tf1 = new JTextField();
		tf1.setBounds(70, 80, 400, 30); // ����
		JTextField tf2 = new JTextField();
		tf2.setBounds(70, 130, 200, 30); // �г���
		JTextArea text = new JTextArea(); // �����ۼ�
		text.setLineWrap(true);
		text.setBounds(45, 180, 600, 550);

		JButton submit = new JButton("����");
		submit.setBounds(530, 800, 60, 30);
		JButton close = new JButton("���");
		close.setBounds(600, 800, 60, 30);

		wt.add(l1);
		wt.add(l2);
		wt.add(l3);
		wt.add(tf1);
		wt.add(tf2);
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

				String title = tf1.getText();
				String writer = tf2.getText();
				String stext = text.getText();
				int bno = 0;
				String bd = (String) cb.getSelectedItem();
				
				BoardDAO bDao = new BoardDAO();
				TreeSet<BoardDTO> bSet = bDao.selectBoardAll();
				Iterator<BoardDTO> itr = bSet.iterator();
				while(itr.hasNext()) {
					BoardDTO bDto = itr.next();
					String instBname = bDto.getBname();
					if(bd.equals(instBname)) {
						bno = bDto.getBno();
						break;
					}
				}

				inst.setTitle(title);
				inst.setWriter(writer);
				inst.setText(stext);
				inst.setBno(bno);

				dao.createText(inst);

				wt.dispose();

			}
		});
		// �� �ۼ� ��� ��ư Ŭ���� �̺�Ʈ �߻�
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				wt.dispose();

			}
		});
	}

}
