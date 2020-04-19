package Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import DAO.BoardDAO;
import DAO.TextDAO;
import DTO.BoardDTO;
import DTO.TextDTO;
import event.RefreshFrameForList1;

/*
 * �ش� �Խ����� �Խù� ����Ʈ ������ִ� Frame
 */
public class List1 extends MouseAdapter {
	TreeSet<TextDTO> txSet;
	BoardDTO bDto;
	public String bname;
	ArrayList<JPanel> list = new ArrayList<JPanel>();
	Color bgColor = new Color(255, 244, 212);
	JPanel cen1;
	public JFrame f1;
	
	public List1(String bname) {
		this.bname = bname;
	}
	
	public void reClickedEvent() {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		TextDAO dao = new TextDAO();
		txSet = dao.selectTextForMain(bname);
		BoardDAO bDao = new BoardDAO();
		bDto = bDao.selectBoard(bname);
		// ������ ����
		f1 = new JFrame();
		f1.getContentPane().setBackground(Color.white);
//		f1.setSize(600, 1000);
		f1.setBounds(500, 20, 600, 1000);
		f1.setLayout(new BorderLayout());
		
		JPanel pmain = new JPanel();
		pmain.setLayout(null);
		f1.add(pmain, BorderLayout.CENTER);
		

		// �Խ��� ����ǥ�� ���
		JPanel info = new JPanel();
		info.setBackground(bgColor);
		info.setBounds(10, 10, 380, 50);
		info.setLayout(new GridLayout(2, 1));
		JLabel bname1 = new JLabel(bDto.getBname());
		JLabel binfo1 = new JLabel(bDto.getbInfo());
		bname1.setFont(new Font("����", Font.BOLD, 20));
		binfo1.setFont(new Font("����", Font.PLAIN, 13));
		info.add(bname1);
		info.add(binfo1);
		info.addMouseListener(new RefreshFrameForList1(this));

		// �Խù� ����Ʈ ���

		// �Խñ� ����Ʈ ���� ��̸���Ʈ
		ArrayList<Integer> pageList = new ArrayList<Integer>();
		ArrayList<JPanel> plist = new ArrayList<JPanel>();
		Iterator<TextDTO> itr = txSet.iterator();

		JPanel cen = null;
		
		// �Խñ� ���� 10������ ���ο� ��� �����Ͽ�
		// ��̸���Ʈ�� ��� �����ϱ�
		ot: for (int i = 0; i < txSet.size(); i++) {
			// �Խñ� 10������ ���ο� ��� �����ϱ�
			if (i % 10 == 0) {
				pageList.add(i / 10 + 1);
				cen = new JPanel();
				cen.setBackground(Color.white);
//					cen.setBorder(new LineBorder(Color.gray));
				cen.setBounds(10, 110, 566, 750);
				cen.setLayout(new GridLayout(10, 1, 0, 10));
				plist.add(cen);
			} else {
				continue ot;
			}

		}

		// ����Ʈ�� ��� ��ο� �Խñ� 10���� ©�� ���
		JPanel p1 = null;
		for (int i = 0; i < plist.size(); i++) {
			for (int j = 0; j < 10; j++) {
				if (itr.hasNext() == false) {
					break;
				}
				p1 = new JPanel();
				p1.setBackground(bgColor);
				TextDTO dto = itr.next();
				Color bdc = new Color(252, 219, 0);
				p1.setBorder(new EtchedBorder(EtchedBorder.RAISED, bdc, bdc));
				p1.setLayout(new GridLayout(3, 1));

				p1.add(new JLabel(dto.getTno() + ""));
				JLabel title = new JLabel(dto.getTitle());
				title.setFont(new Font("����", Font.BOLD, 16));
				p1.add(title);
				p1.add(new JLabel("�ۼ��� : " + dto.getWriter() + "          " + dto.getCrdate() + "       ��ȸ��: " + dto.getCount()));
							
				list.add(p1);   // �ش�� Ŭ���� �̺�Ʈ �߻��ϱ����� ����Ʈ�� �����ϱ�
				plist.get(i).add(p1);
			}
		}



		int no = pageList.size();
		JPanel page = new JPanel();
		page.setLayout(new FlowLayout(FlowLayout.LEFT));
		page.setBounds(100, 900, 400, 40);

		ArrayList<JPanel> pagenum = new ArrayList<JPanel>();
		for (int i = 0; i < no; i++) {
			JPanel ppp = new JPanel();
			ppp.add(new JLabel(pageList.get(i) + ""));
			pagenum.add(ppp);
			page.add(ppp);
		}

		// �۾��� ��ư ����
		JButton wtbtn = new JButton("�۾���");
		wtbtn.setBackground(bgColor);
		wtbtn.setBounds(455, 30, 80, 30);

		// �۾��� ��ư �̺�Ʈ
		wtbtn.addActionListener(new WriteBtnFrame());

		// �Խñ� 10�������� ��� ������ �������� ����Ʈ������ ���� �ֱ� 10���� �� ���
		cen1 = plist.get(0);
		pmain.add(cen1);
		pmain.add(info);
		pmain.add(page);
		pmain.add(wtbtn);
		f1.setVisible(true);

		// ������ ��ư ������ ������ ��ȯ �̺�Ʈ ����
		if (pagenum.size() != 0) {
			for (int i = 0; i < pagenum.size(); i++) {
				pagenum.get(i).addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent e) {
						JPanel jp = (JPanel) e.getSource();
						JLabel jl = (JLabel) jp.getComponent(0);
						int no = Integer.parseInt(jl.getText());
//						System.out.println(no-1);;
						int index1 = no - 1;
						
						Component[] carr = pmain.getComponents();
						for(int i=0; i<carr.length; i++) {
							Component comp = carr[i];
							if(comp == cen1) {
								pmain.remove(carr[i]);
								break;
							}
						}
						cen1 = plist.get(index1);
						pmain.add(cen1);
						
						f1.dispose();
						f1.setVisible(true);

					}
				});
			}
		}
		// �ش� �� Ŭ���� �̺�Ʈ �ڵ鸵
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).addMouseListener(new showTextFrame());
			}
		}

	}

}
