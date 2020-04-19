package Panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class WtPanel extends JPanel {

	public WtPanel(String str) {
		setBackground(new Color(49, 124, 245));
		
		JLabel label = new JLabel(str);
		label.setForeground(Color.white);
		label.setFont(label.getFont().deriveFont(68.0f));
//		setBorder(new LineBorder(new Color(100, 102, 100)));
		add(label);
	}

}
