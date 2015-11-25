import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JComponent;


public class BackgroundImage extends JComponent {
	Image i;
	public BackgroundImage(Image i){
		this.i=i;
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(i, 0, 0, null);  // Drawing image using drawImage method
		 
		}
	

}
