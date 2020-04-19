package Frame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import DAO.TextDAO;
import DTO.TextDTO;

public class showTextFrame extends MouseAdapter {

	Color bgColor = new Color(255, 244, 212);
	
	public showTextFrame() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// �̺�Ʈ �߻��� ��� ��������
		JPanel panel = (JPanel) e.getSource();

		// ��ξ��� ������Ʈ ��� �������� ==> �ؽ�Ʈ ��ȣ
		JLabel jl1 = (JLabel) panel.getComponent(0);
		String st = jl1.getText();
		int tno = Integer.parseInt(st);

		// �Խñ� Ŭ���ø��� ��ȸ�� ���� ó��
		TextDAO tDao = new TextDAO();
		tDao.updateTextCount(tno);
		
		TextDAO dao = new TextDAO();
		TextDTO data = dao.selectTextForIn(tno);
//		JOptionPane.showMessageDialog(null, data.getText());
		
		
		
		// ���� ����� ������ ���� ����
		JFrame textFrame = new JFrame();
		textFrame.setBounds(500, 50, 800, 900);
		textFrame.setLayout(null);

		textFrame.getContentPane().setBackground(bgColor);

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p1.setBackground(bgColor);
		p2.setBackground(bgColor);

		p1.setLayout(new GridLayout(3, 1, 10, 10));
		p1.setBounds(10, 10, 730, 100);
		p1.add(new JLabel("���� : " + data.getTitle()));
		p1.add(new JLabel("�۾��� : " + data.getWriter()));
		p1.add(new JLabel("�ۼ��� : " + data.getCrdate()));
//		p1.setBorder(new LineBorder(new Color(100, 102, 100)));

		JTextArea area = new JTextArea(data.getText());
		area.setEditable(false);
		area.setBackground(bgColor);
		area.setLineWrap(true);
		area.setSize(720, 640);
		JScrollPane scl = new JScrollPane(area);
		scl.setBorder(null);	// �ؽ�Ʈ Area �׵θ� ����.
		p2.add(scl);
		p2.setBounds(10, 160, 730, 650);
//		p2.setBorder(new LineBorder(new Color(100, 102, 100)));

		textFrame.add(p1);
		textFrame.add(p2);

		textFrame.setVisible(true);
	}

}
