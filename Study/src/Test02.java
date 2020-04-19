import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Test02 {
	
	public static void main(String[] args)  {
		JFrame fmain = new JFrame();
		fmain.setBounds(10, 10, 1000, 1000);
		fmain.setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.add(new JLabel("label1"));
		panel1.add(new JLabel("label2"));
		panel1.setBounds(10, 10, 100, 100);
		
		JPanel panel2 = new JPanel();
		panel2.add(new JLabel("label2"));
		panel2.setBounds(100, 10, 100, 100);
		ImageIcon logo = new ImageIcon("../Frame/board_logo2.png");
		
		JLabel label2 = new JLabel(logo);
		label2.setBounds(0, 0, 100 , 100);
		
		
		
		JButton btn1 = new JButton("btn1");
		btn1.setBounds(10, 250, 50, 50);
		
		fmain.add(panel1);
		fmain.add(panel2);
		fmain.add(btn1);
		fmain.add(label2);
		
		
		fmain.setExtendedState(fmain.EXIT_ON_CLOSE);
		fmain.setVisible(true);
		
		
		
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				
			}
		});
	}

}
