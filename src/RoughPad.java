import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;


public class RoughPad extends JComponent {
	
	int c=0;
	int erase=0;
	//BufferedImage img[];
	BufferedImage image;
	//BufferedImage canvas[];
	//Image background;
	//this is gonna be your image that you draw on
	Graphics2D graphics2D;
	BasicStroke stroke;
	//this is what we'll be using to draw on
	int currentX, currentY, oldX, oldY;
	//these are gonna hold our mouse coordinates

	//Now for the constructors
	public RoughPad(){
		
		 //background = Toolkit.getDefaultToolkit().createImage("sample.jpg");
		stroke=new BasicStroke(2);
		//setDoubleBuffered(false);
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				oldX = e.getX();
				oldY = e.getY();
			}
		});
		//if the mouse is pressed it sets the oldX & oldY
		//coordinates as the mouses x & y coordinates
		addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e){
				currentX = e.getX();
				currentY = e.getY();
				if(graphics2D != null){
					if(erase==0){
						
						graphics2D.setStroke(stroke);
						graphics2D.drawLine(oldX, oldY, currentX, currentY);
					}
					if(erase==1){
					
						graphics2D.setPaint(Color.white);
						graphics2D.fillOval(currentX-5, currentY-5,10, 10);
						
					}
				//graphics2D.drawLine(oldX, oldY, currentX, currentY);
				repaint();
				oldX = currentX;
				oldY = currentY;
				}
			}

		});
		//while the mouse is dragged it sets currentX & currentY as the mouses x and y
		//then it draws a line at the coordinates
		//it repaints it and sets oldX and oldY as currentX and currentY
	}
	
	

	public void paintComponent(Graphics g){
		if(image == null){
			//image = createImage(getSize().width, getSize().height);
			image= new BufferedImage(350, 540, BufferedImage.TYPE_INT_ARGB);
			graphics2D = (Graphics2D)image.getGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			graphics2D.setPaint(Color.white);
			graphics2D.fillRect(0, 0, 350, 540);
			graphics2D.setPaint(Color.black);
			graphics2D.setStroke(stroke);
			//clear();

		}
		g.drawImage(image, 0, 0, null);
		
		
	}
	//this is the painting bit
	//if it has nothing on it then
	//it creates an image the size of the window
	//sets the value of Graphics as the image
	//sets the rendering
	//runs the clear() method
	//then it draws the image
	


	public void clear(){
		    
		graphics2D.setPaint(Color.white);
		graphics2D.fillRect(0, 0, 350, 540);
		graphics2D.setPaint(Color.black);	
			repaint();		
	}
	//this is the clear
	//it sets the colors as white
	//then it fills the window with white
	//thin it sets the color back to black
	
	//green paint
	public void updateStroke(BasicStroke s){
		stroke=s;
		repaint();
	}
	
	public void setRubber(){
		erase=1;
		repaint();
	}
	public void setPencil(){
		erase=0;
		repaint();
	}

}
