import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class SmallView  extends JButton implements MouseListener {
	public int id;
	public static int value=0;
	
	public SmallView(int id,ImageIcon img){
		this.id=id;
		setOpaque(false);
		setContentAreaFilled(false);
		//setBorderPainted(false);
		setIcon(img);
		setSize(100, 75);
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
		value=id;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
