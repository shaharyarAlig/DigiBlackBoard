import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class CountDown extends JFrame {
	
	public void Load(){
		ImageIcon loading = new ImageIcon(getClass().getResource("newgif.gif"));
		setLocation(400, 100);
		add(new JLabel( loading, JLabel.CENTER));
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0));
		setSize(400, 400);
	}
	
	public void Display(){
		this.setVisible(true);
	}
	
	public void Hide(){
		this.dispose();
	}

}
