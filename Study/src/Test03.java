import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

class LogoPanel extends JPanel{
	private Image img;
	
	public LogoPanel(Image img) {
		this.img = img;
		setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setLayout(null);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
}

public class Test03 {

	public Test03() {

		JFrame mFrame = new JFrame();
		mFrame.setSize(640, 460);
		mFrame.setLocationRelativeTo(null);
		mFrame.setExtendedState(mFrame.EXIT_ON_CLOSE);
		String str = "D:\\class\\Java\\git\\Study\\study\\Study\\src\\board_logo1.jpg";
//		File f = new File("D:\\class\\Java\\git\\Study\\study\\Study\\src\\board_logo1.jpg");

		LogoPanel lpanel = new LogoPanel(new ImageIcon(str).getImage());
		mFrame.pack();
		
		mFrame.add(lpanel);

		mFrame.setVisible(true);
	}

	public static void main(String[] args) {
		new Test03();

	}

}
