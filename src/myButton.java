import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;


public class myButton extends JButton implements MouseListener{
	ImageIcon img,imgG;
	public myButton(ImageIcon img,ImageIcon imgG){
		this.img=img;
		this.imgG=imgG;
		this.setSize(48, 48);
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setIcon(img);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		super.addMouseListener(this);
	}
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		setIcon(imgG);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		setIcon(img);
	}

}
