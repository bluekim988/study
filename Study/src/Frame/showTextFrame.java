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
		// 이벤트 발생한 페널 가져오기
		JPanel panel = (JPanel) e.getSource();

		// 페널안의 컴포넌트 요소 가져오기 ==> 텍스트 번호
		JLabel jl1 = (JLabel) panel.getComponent(0);
		String st = jl1.getText();
		int tno = Integer.parseInt(st);

		// 게시글 클릭시마다 조회수 증가 처리
		TextDAO tDao = new TextDAO();
		tDao.updateTextCount(tno);
		
		TextDAO dao = new TextDAO();
		TextDTO data = dao.selectTextForIn(tno);
//		JOptionPane.showMessageDialog(null, data.getText());
		
		
		
		// 본문 출력할 프레임 새로 생성
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
		p1.add(new JLabel("제목 : " + data.getTitle()));
		p1.add(new JLabel("글쓴이 : " + data.getWriter()));
		p1.add(new JLabel("작성일 : " + data.getCrdate()));
//		p1.setBorder(new LineBorder(new Color(100, 102, 100)));

		JTextArea area = new JTextArea(data.getText());
		area.setEditable(false);
		area.setBackground(bgColor);
		area.setLineWrap(true);
		area.setSize(720, 640);
		JScrollPane scl = new JScrollPane(area);
		scl.setBorder(null);	// 텍스트 Area 테두리 삭제.
		p2.add(scl);
		p2.setBounds(10, 160, 730, 650);
//		p2.setBorder(new LineBorder(new Color(100, 102, 100)));

		textFrame.add(p1);
		textFrame.add(p2);

		textFrame.setVisible(true);
	}

}
