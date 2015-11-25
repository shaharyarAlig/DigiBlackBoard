import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class PPTxPad extends JComponent{
	
	int c=0;
	int erase=0;
	boolean load=false;
	BufferedImage img[];
	BufferedImage image;
	BufferedImage canvas[];
	Image background;
	Color bgColor;
	BasicStroke stroke;
	Font font;
	String strText="";
	boolean writetext=false;
	//this is gonna be your image that you draw on
	Graphics2D graphics2D;
	//this is what we'll be using to draw on
	int currentX, currentY, oldX, oldY;
	//these are gonna hold our mouse coordinates
	//Now for the constructors
	public PPTxPad(){
		bgColor=Color.BLACK;
		stroke=new BasicStroke(2);
		 //background = Toolkit.getDefaultToolkit().createImage("sample.jpg");
		font = new Font("Serif",Font.BOLD, 35);
		//setDoubleBuffered(false);
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				oldX = e.getX();
				oldY = e.getY();
				if(writetext){
					graphics2D.setPaint(bgColor);
					graphics2D.setFont(font);
					graphics2D.drawString(strText, oldX, oldY);
					writetext=false;
				}
				repaint();
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
						graphics2D.setPaint(bgColor);
						graphics2D.setStroke(stroke);
						graphics2D.drawLine(oldX, oldY, currentX, currentY);
					}
					if(erase==1){
						//graphics2D.setComposite(AlphaComposite.Src);
						//graphics2D.set 
						for(int i=0;i<10;i++){
						
							if(currentX-i>=0 && currentX+i<720 && currentY-i>=0 &&currentY+i<540){
								
								if(load){
									canvas[c].setRGB(currentX, currentY, 0x8F1C1C);
									canvas[c].setRGB(currentX+i, currentY, 0x8F1C1C);
									canvas[c].setRGB(currentX-i, currentY, 0x8F1C1C);
									canvas[c].setRGB(currentX, currentY+i, 0x8F1C1C);
									canvas[c].setRGB(currentX, currentY-i, 0x8F1C1C);
									canvas[c].setRGB(currentX-i, currentY-i, 0x8F1C1C);
									canvas[c].setRGB(currentX+i, currentY+i, 0x8F1C1C);
								}else{
									image.setRGB(currentX, currentY, 0x8F1C1C);
									image.setRGB(currentX+i, currentY, 0x8F1C1C);
									image.setRGB(currentX-i, currentY, 0x8F1C1C);
									image.setRGB(currentX, currentY+i, 0x8F1C1C);
									image.setRGB(currentX, currentY-i, 0x8F1C1C);
									image.setRGB(currentX-i, currentY-i, 0x8F1C1C);
									image.setRGB(currentX+i, currentY+i, 0x8F1C1C);
								}
								
							}
						}
						
						
						
						
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
	public void ArrayLenght(int length){
		img = new BufferedImage[length];
	    canvas=new BufferedImage[length];
	}
	public void loadImage(BufferedImage I ,int Index){
		img[Index]=I;
		canvas[Index]= new BufferedImage(720, 540, BufferedImage.TYPE_INT_ARGB);
	}
	

	public void paintComponent(Graphics g){
		if(image == null){
			//image = createImage(getSize().width, getSize().height);
			image= new BufferedImage(720, 540, BufferedImage.TYPE_INT_ARGB);
			graphics2D = (Graphics2D)image.getGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			//graphics2D.drawImage(background,0 , 0, 800, 600, null);
			graphics2D.setPaint(new Color(0,0,0,0));
			graphics2D.fillRect(0, 0, getSize().width, getSize().height);
			graphics2D.setPaint(bgColor);
			graphics2D.setStroke(stroke);
			//clear();

		}
		if(load==true){
			g.drawImage(img[c], 0, 0, null);
			g.drawImage(canvas[c], 0, 0, null);	
		}else{
			g.drawImage(image, 0, 0, null);
		}
		
		
	}
	//this is the painting bit
	//if it has nothing on it then
	//it creates an image the size of the window
	//sets the value of Graphics as the image
	//sets the rendering
	//runs the clear() method
	//then it draws the image
	public void loadTrue(){
		load=true;
		graphics2D = (Graphics2D)canvas[0].getGraphics();
		repaint();
	}
	public void up(){
		if(load==true){
			c--;
			if(c<0){
				c=0;
			}
			graphics2D = (Graphics2D)canvas[c].getGraphics();
			repaint();
		}
		
	}
	public void down(){
		if(load==true){
			c++;
			if(c>img.length-1){
				c=img.length-1;
			}
			graphics2D = (Graphics2D)canvas[c].getGraphics();
			repaint();
		}
		
	}
	public void setRubber(){
		erase=1;
		repaint();
	}
	public void setPencil(){
		erase=0;
		repaint();
	}
	
	public void setCvalue(int index){
		c=index;
		graphics2D = (Graphics2D)canvas[c].getGraphics();
		repaint();
		
	}
	
	public void loadColor(Color c){
		bgColor=c;
		repaint();
	}
	public void updateStroke(BasicStroke s){
		stroke=s;
	}
	public void loadFont(Font f){
		font=f;
	}
	
	public void writeText(){
		//String prompt = "Please add text to display";
		 int messageType = JOptionPane.INFORMATION_MESSAGE;
	      strText = JOptionPane.showInputDialog(this, 
	         "ENTER TEXT", 
	         "Input Dialog Box", messageType);
	      //System.out.println("Answer: "+answer);
	      if(strText==null){
	    	  writetext=false;
	      }else{
	    	  writetext=true;  
	      }
	      
	}
	
	public boolean convertToPDF() throws MalformedURLException, IOException{
		 JFileChooser filechooser = new JFileChooser();
	      FileNameExtensionFilter filter = new FileNameExtensionFilter(
	               ".pdf", "pdf");
	      filechooser.setFileFilter(filter);
	      int result = filechooser.showSaveDialog(this);
	      if (result == JFileChooser.APPROVE_OPTION) {
	         File saveFile = filechooser.getSelectedFile();
	         Document pdfDocument = new Document();
	         PdfPTable table = new PdfPTable(1);
	         com.itextpdf.text.Rectangle r=new com.itextpdf.text.Rectangle(780,580);
	         Image slideImage = null;
	         PdfWriter pdfWriter;
	         try {
				 pdfWriter = PdfWriter.getInstance(pdfDocument, new FileOutputStream(saveFile.getAbsolutePath()+".pdf"));
				 pdfWriter.open();
				 pdfDocument.open();
				 //pdfDocument.setPageSize(r);
				 for(int i=0;i<canvas.length;i++){
					 
					 BufferedImage tempimg= new BufferedImage(720, 540, BufferedImage.TYPE_INT_ARGB);
					 Graphics2D temgraphics = (Graphics2D)tempimg.getGraphics();
					 temgraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					 temgraphics.drawImage(img[i],0 , 0, 720, 540, null);
					 temgraphics.drawImage(canvas[i],0 , 0, 720, 540, null);
					 //slideImage = Image.getInstance(img[i], null);
					 slideImage=com.itextpdf.text.Image.getInstance(tempimg, null);
					 //table.addCell(new PdfPCell(slideImage, true));
					 //Image imagex = com.itextpdf.text.Image.getInstance("");
					 pdfDocument.setPageSize(r); 
					 pdfDocument.newPage();
					 pdfDocument.add(slideImage);		 
				 }
				    pdfDocument.close();
				    pdfWriter.close();
				    System.out.println("Powerpoint file converted to PDF successfully");
			        return true;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         
	      }else{
	    	    return false;
	      }
		return false;
		
	}


	public void clear(){
		//graphics2D.setPaint(Color.red);
		//graphics2D.setStroke(new BasicStroke(3));
		    //graphics2D.setPaint(new Color(255, 255, 255, 0));
			//graphics2D.fillRect(0, 0, getSize().width, getSize().height);
			//graphics2D.drawImage(background,0 , 0, 800, 600, null);
			//graphics2D.setPaint(Color.black);
			//Graphics2D graphics = img[0].createGraphics();
			//graphics.setPaint(Color.white);
	        //graphics.fill(new Rectangle2D.Float(0, 0, 720, 540));
	        //graphics.drawImage(image, 720, 540, null);
		   // img[0]=image;
			for(int i=0;i<720;i++){
				for(int j=0;j<540;j++){
					if(load){
						canvas[c].setRGB(i, j, 0x8F1C1C);
					}else{
						image.setRGB(i, j, 0x8F1C1C);
					}
					
				}
			}
			repaint();	
		
		
	}
	//this is the clear
	//it sets the colors as white
	//then it fills the window with white
	//thin it sets the color back to black
	public void red(){
		graphics2D.setPaint(Color.red);
		graphics2D.setStroke(new BasicStroke(3));
		//graphics2D.drawImage(img[0],0 , 0, 800, 600, null);
		graphics2D = (Graphics2D)canvas[0].getGraphics();
		c=0;
		erase=0;
		repaint();
	}
	//this is the red paint
	public void black(){
		graphics2D.setPaint(Color.black);
		graphics2D.setStroke(new BasicStroke(3));
		graphics2D = (Graphics2D)canvas[1].getGraphics();
		c=1;
		erase=0;
		//graphics2D.drawImage(img[1],0 , 0, 800, 600, null);
		repaint();
	}
	//black paint
	public void magenta(){
		graphics2D.setPaint(Color.magenta);
		graphics2D.setStroke(new BasicStroke(3));
		graphics2D = (Graphics2D)canvas[2].getGraphics();
		//graphics2D.drawImage(img[2],0 , 0, 800, 600, null);
		c=2;
		erase=0;
		repaint();
	}
	//magenta paint
	public void blue(){
		graphics2D.setPaint(Color.blue);
		graphics2D.setStroke(new BasicStroke(3));
		erase=0;
		c=3;
		graphics2D = (Graphics2D)canvas[3].getGraphics();
		//graphics2D.drawImage(img[3],0 , 0, 800, 600, null);
		repaint();
	}
	//blue paint
	public void green(){
		graphics2D.setPaint(Color.RED);
		erase=1;
		//graphics2D.drawImage(img[4],0 , 0, 800, 600, null);
		repaint();
		//add();
	}
	//green paint


}
