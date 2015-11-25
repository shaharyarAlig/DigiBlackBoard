import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;


public class DigiClassBoard {
	
	//
	public JFrame frame;
	public JLabel FrameDrag,FrameClose,FrameMin;
	public JPanel ButtonPanel,navgPanel,ButtonPanel2,ButtonPanel3,RoughPadPanel,SmallViewPanel;
	public myButton btnpen,btnopen,btncolor,btneraser,btnclear,btnprevs,btnnext,btnpdf,btntext,btninfo,btnplay,btnsave,btnstop,btncal,btnnet,btnpad,btnfont,btnclear2;
	int xMouse,yMouse;
	RoughPad roughPad;
	PPTxPad pptxPad;
	StopWatch stopwatch;
	Loading loadingFrame;
	CountDown countdown;
	SplashScreen splashscreen;
	public Border bGreyLine;
	public JSlider jslider;
	public Cursor cursorpen,cursorrubber;
	public SmallView smallview[]; 
	JScrollPane jsp;
	public boolean load=false;
	boolean record=false,state=false;
	public DigiClassBoard(){
		frame = new JFrame("DigiClassBoard Version 1.0 Developed Shaharyar Shaukat");
		splashscreen = new SplashScreen();
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				splashscreen.Load();
				splashscreen.Display();
				try {
					Thread.sleep(2800);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.setVisible(true);
				splashscreen.Hide();
				//countdown.Hide();
			}
			
		}).start();
		
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
		//
		frame.setLayout(null);
		frame.setSize(1350, 720);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RubberCursor();
		PenCursor();
		pptxPad=new  PPTxPad();
		pptxPad.setBounds(80, 35, 720, 540);
		initializeMyButton();
		pptxPad.setBorder(bGreyLine);
		pptxPad.setCursor(cursorpen);
		//initializeMyButton();
		try{
			
			BufferedImage bf = ImageIO.read(getClass().getResourceAsStream("BG2.png")); 
			frame.setContentPane(new BackgroundImage(bf));
		}catch(Exception e){
			
		}
		// Animation
		loadingFrame =new Loading();
		countdown=new CountDown();
		//
		FrameMin=new JLabel();
		FrameMin.setBounds(1268,0,30,30);
		FrameMin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		frame.add(FrameMin);
		FrameMin.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				
				frame.setState(JFrame.ICONIFIED);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				//btnopen.setIcon(new ImageIcon("icon//open.png"));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
				
			}

			
			
		});
		FrameClose=new JLabel();
		FrameClose.setBounds(1305,0,30,30);
		FrameClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
		frame.add(FrameClose);
		FrameClose.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.exit(0);
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				//btnopen.setIcon(new ImageIcon("icon//open.png"));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
				
			}

			
			
		});
		FrameDrag=new JLabel();
		FrameDrag.setBounds(0, 0, 1350, 25);
		//FrameDrag.setForeground(Color.blue);
		//FrameDrag.setText("DIGI CLASS BOARD | DEVELOPED BY MD. SHAHRYAR SHAUKAT");
		FrameDrag.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		FrameDrag.addMouseMotionListener(new MouseMotionAdapter() {
			
			 public void mouseMoved(MouseEvent evt) {
				 
			 }
			 public void mouseDragged(MouseEvent evt) {
				 int x=evt.getXOnScreen();
				 int y=evt.getYOnScreen();
				 frame.setLocation(x -xMouse, y- yMouse);
			 }
		});
		FrameDrag.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				//btnopen.setIcon(new ImageIcon("icon//openg.png"));
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				//btnopen.setIcon(new ImageIcon("icon//open.png"));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				xMouse=e.getX();
				yMouse=e.getY();
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
				
			}

			
			
		});
		stopwatch =new StopWatch();
		stopwatch.setBounds(800, 50, 170,50);
		stopwatch.setBackground(new Color(26,26,26));
		//
		// JSLIDER
				jslider=new JSlider(JSlider.HORIZONTAL, 1, 5, 2);
				jslider.setMinorTickSpacing(1);  
				jslider.setMajorTickSpacing(5); 
				jslider.setBounds(800, 200, 100, 20);
				jslider.setOpaque(false);
				frame.add(jslider);
				jslider.addChangeListener(new ChangeListener() {
			         public void stateChanged(ChangeEvent e) {
			            BasicStroke s= new BasicStroke(((JSlider)e.getSource()).getValue());
			            pptxPad.updateStroke(s);
			            roughPad.updateStroke(s);
			         }
			      });
		//
		JPanel jp=new JPanel();
		jp.setOpaque(false);
		jp.setBounds(900, 175, 80, 80);
		btnclear2=new myButton(new ImageIcon(getClass().getResource("clear.png")),new ImageIcon(getClass().getResource("clearg.png")));
		jp.add(btnclear2);
		btnclear2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				roughPad.clear();
			}
		});//
		frame.add(jp);
		frame.add(FrameDrag);
		frame.add(ButtonPanel);
		frame.add(navgPanel);
		frame.add(pptxPad);
		frame.add(roughPad);
		frame.add(ButtonPanel2);
		frame.add(ButtonPanel3);
		frame.add(jsp);
		frame.add(stopwatch);
		
		frame.validate();
	}
	//
	
	//Load Image
	public void LoadImage(){
		    
		    
		
	}
	//initialize
	public void initializeMyButton(){
		bGreyLine = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true);
		SmallViewPanel=new JPanel();
		SmallViewPanel.setLayout(new FlowLayout());
		SmallViewPanel.setBackground(new Color(26,26,26));
		int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		jsp=new JScrollPane(SmallViewPanel,v,h);
		jsp.setBounds(25, 585, 1300, 115);
		ButtonPanel=new JPanel();
		ButtonPanel.setOpaque(false);
		ButtonPanel2=new JPanel();
		ButtonPanel2.setOpaque(false);
		ButtonPanel3=new JPanel();
		ButtonPanel3.setOpaque(false);
		ButtonPanel.setBounds(15, 15, 60, 565);
		ButtonPanel.setLayout(new FlowLayout());
		btnopen=new myButton(new ImageIcon(getClass().getResource("open.png")),new ImageIcon(getClass().getResource("openg.png")));
		ButtonPanel.add(btnopen);
		btnopen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				String url="";
				final String path;
				JFileChooser fileChooser = new JFileChooser();
		        int returnValue = fileChooser.showOpenDialog(null);
		        if (returnValue == JFileChooser.APPROVE_OPTION) {
		          File selectedFile = fileChooser.getSelectedFile();
		           url=selectedFile.getName();
		           path=selectedFile.getAbsolutePath();
		           //System.out.println(str.substring(str.length()-5, str.length()));
		           if(url.substring(url.length()-5, url.length()).equals(".pptx")){
		        	   //
		        	   new Thread(new Runnable(){

							@Override
							public void run() {
								// TODO Auto-generated method stub
								loadingFrame.Load();
								loadingFrame.Display();
								
								//countdown.Hide();
							}
							
						}).start();
		        	   
		        	   //
		        	   new Thread( new Runnable() {
		   		            @Override
		   		            public void run() {
		   		            	
								try {
									//loadingFrame.Display();
									LoadPPT(path);
									pptxPad.loadTrue();
									frame.validate();
									load=true; // ppt loaded in 
									loadingFrame.Hide();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
		   		            	
		   		            	
		   		            }
		   		        }).start();
		        	   //JOptionPane.showMessageDialog(frame,"  Loading... Pls wait  ","",JOptionPane.INFORMATION_MESSAGE);
						
		           }else{
		        	   JOptionPane.showMessageDialog(frame,"   \n .pptx EXTENSION FILE SUPPORTED","INVALID FILE FORMAT",JOptionPane.ERROR_MESSAGE);
		           }
		           
		          
		        }
		        //
		        
		       
		       
			    
			    
		        //
			    //f.setVisible(true);
				//
			}
		});
		//
		btnpen=new myButton(new ImageIcon(getClass().getResource("pencil.png")),new ImageIcon(getClass().getResource("pencilg.png")));
		ButtonPanel.add(btnpen);
		btnpen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pptxPad.setPencil();
				roughPad.setPencil();
				pptxPad.setCursor(cursorpen);
				roughPad.setCursor(cursorpen);
			}
		});
		//
		// BtnFont
		btnfont=new myButton(new ImageIcon(getClass().getResource("font.png")),new ImageIcon(getClass().getResource("fontg.png")));
		ButtonPanel.add(btnfont);
		btnfont.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				GraphicsEnvironment ge = 
			            GraphicsEnvironment.getLocalGraphicsEnvironment();
			        //ge.registerFont(font);
			        JList fonts = new JList( ge.getAvailableFontFamilyNames() );
			        String[] items = new String[]
			                {
			                        "10", "12", "14", "16","18","20","22","24","25","26","28","30","32","34","36","38","40"
			                };
			        JList size = new JList(items);
			        
			        JOptionPane.showConfirmDialog(frame, new JScrollPane(fonts), "SELECT FONT",
		                    JOptionPane.PLAIN_MESSAGE);
			        String str=(String) fonts.getSelectedValue();
			        JOptionPane.showConfirmDialog(frame, new JScrollPane(size), "SELECT SIZE",
		                    JOptionPane.PLAIN_MESSAGE);
			        Font font = new Font(str,Font.BOLD, (size.getSelectedIndex()*2)+10);
			        pptxPad.loadFont(font);
			}
		});
		
		btncolor=new myButton(new ImageIcon(getClass().getResource("color.png")),new ImageIcon(getClass().getResource("colorg.png")));
		ButtonPanel.add(btncolor);
		btncolor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Color c = JColorChooser.showDialog(null, "Choose a Color",Color.BLACK);
				pptxPad.loadColor(c);
			}
		});//
		btneraser=new myButton(new ImageIcon(getClass().getResource("rubber.png")),new ImageIcon(getClass().getResource("rubberg.png")));
		ButtonPanel.add(btneraser);
		btneraser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pptxPad.setRubber();
				roughPad.setRubber();
				roughPad.setCursor(cursorrubber);
				pptxPad.setCursor(cursorrubber);
				
			}
		});//
		btnclear=new myButton(new ImageIcon(getClass().getResource("clear.png")),new ImageIcon(getClass().getResource("clearg.png")));
		ButtonPanel.add(btnclear);
		btnclear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pptxPad.clear();
			}
		});//
		btntext=new myButton(new ImageIcon(getClass().getResource("Text.png")),new ImageIcon(getClass().getResource("Textg.png")));
		ButtonPanel.add(btntext);
		btntext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pptxPad.writeText();
			}
		});//
		btnpdf=new myButton(new ImageIcon(getClass().getResource("pdf.png")),new ImageIcon(getClass().getResource("pdfg.png")));
		ButtonPanel.add(btnpdf);
		btnpdf.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if(load){
					new Thread( new Runnable() {
			            @Override
			            public void run() {
			            	try {
								boolean test =pptxPad.convertToPDF();
								if(test){
									JOptionPane.showMessageDialog(frame,"  COMPLETED SUCCESSFULY!  ","PPT TO PDF",JOptionPane.INFORMATION_MESSAGE);
								}
							} catch (MalformedURLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					       
					        	   
					       	  
					     
					        }
			        }).start();
					
						
				}else{
					JOptionPane.showMessageDialog(frame,"    FIRST OPEN PPT FILE","NO FILE SELECTED",JOptionPane.ERROR_MESSAGE);
			           
				}
				
			}
		});//
		btninfo=new myButton(new ImageIcon(getClass().getResource("info.png")),new ImageIcon(getClass().getResource("infog.png")));
		ButtonPanel.add(btninfo);
		btninfo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(frame,"  DEVELOPED BY MD. SHAHARYAR SHAUKAT  ","DIGI BLACK BOARD v1.0",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		//
		navgPanel=new JPanel();
		navgPanel.setOpaque(false);
		navgPanel.setBounds(850, 300, 64,160);
		btnprevs=new myButton(new ImageIcon(getClass().getResource("up.png")),new ImageIcon(getClass().getResource("upg.png")));
		navgPanel.add(btnprevs);
		btnprevs.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pptxPad.up();
			}
		});//
		btnnext=new myButton(new ImageIcon(getClass().getResource("down.png")),new ImageIcon(getClass().getResource("downg.png")));
		navgPanel.add(btnnext);
		btnnext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pptxPad.down();
			}
		});//
		
		//Rough Pad
		roughPad=new RoughPad();
		roughPad.setBounds(970, 40, 350, 540);
		roughPad.setBorder(bGreyLine);
		roughPad.setCursor(cursorpen);
		//
		//Button Panel2
		ButtonPanel2=new JPanel();
		ButtonPanel2.setLayout(new GridLayout(1,3));
		ButtonPanel2.setOpaque(false);
		ButtonPanel2.setBounds(800, 500, 170, 65);
		btnpad=new myButton(new ImageIcon(getClass().getResource("notepad.png")),new ImageIcon(getClass().getResource("notepadg.png")));
		ButtonPanel2.add(btnpad);
		btnpad.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					Runtime rs = Runtime.getRuntime();
				      rs.exec("notepad");
				    	
				    }
				    catch (IOException ex) {
				      System.out.println(ex);
				    }  
			}
		});//
		btnnet=new myButton(new ImageIcon(getClass().getResource("net.png")),new ImageIcon(getClass().getResource("netg.png")));
		ButtonPanel2.add(btnnet);
		btnnet.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(Desktop.isDesktopSupported())
				{
				  try {
					Desktop.getDesktop().browse(new URI("http://www.google.com"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		});//
		btncal=new myButton(new ImageIcon(getClass().getResource("cal.png")),new ImageIcon(getClass().getResource("calg.png")));
		ButtonPanel2.add(btncal);
		btncal.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					Runtime rs = Runtime.getRuntime();
				      rs.exec("calc");
				    	
				    }
				    catch (IOException ex) {
				      System.out.println(ex);
				    }  
			}
		});//
		//Button Panel3
		ButtonPanel3=new JPanel();
		ButtonPanel3.setLayout(new GridLayout(1,3));
		ButtonPanel3.setOpaque(false);
		ButtonPanel3.setBounds(800, 100, 170, 65);
		final JButton btn=new JButton();
		//btnplay=new myButton(new ImageIcon(getClass().getResource("play.png")),new ImageIcon(getClass().getResource("play.png")));
		final ImageIcon pauseicon=new ImageIcon(getClass().getResource("pause.png"));
		final ImageIcon playicon=new ImageIcon(getClass().getResource("play.png"));
		btn.setSize(48, 48);
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setBorderPainted(false);
		btn.setIcon(playicon);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		ButtonPanel3.add(btn);
		btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			
				if(!record){
					btn.setIcon(pauseicon);
					if(!state){
						// start
						new Thread(new Runnable(){

							@Override
							public void run() {
								// TODO Auto-generated method stub
								countdown.Load();
								countdown.Display();
								try {
									Thread.sleep(3700);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								countdown.Hide();
								record=true;
								stopwatch.start();
							}
							
						}).start();
						
	
					}else{
						//resume
						record=true;
						stopwatch.resume();
					}
					
				}else{
					//pause
					btn.setIcon(playicon);
					
					stopwatch.pause();
					record=false;
					state=true;
				}
				
				
				
				
			}
		});//
		btnstop=new myButton(new ImageIcon(getClass().getResource("stop.png")),new ImageIcon(getClass().getResource("stopg.png")));
		ButtonPanel3.add(btnstop);
		btnstop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				btn.setIcon(playicon);
				stopwatch.stop();
				record=false;
				state=false;
			}
		});//
		btnsave=new myButton(new ImageIcon(getClass().getResource("save.png")),new ImageIcon(getClass().getResource("saveg.png")));
		ButtonPanel3.add(btnsave);
		btnsave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});//
	}
	
	public void LoadPPT(String url) throws FileNotFoundException, IOException{
		  System.out.println(url);
		  File file=new File(url);
	      XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(file));
	      //getting the dimensions and size of the slide 
	      Dimension pgsize = ppt.getPageSize();
	      XSLFSlide[] slide = ppt.getSlides();
	      smallview=new SmallView[slide.length];
	      pptxPad.ArrayLenght(slide.length);
	      for (int i = 0; i < slide.length; i++) {
		         BufferedImage img = new BufferedImage(pgsize.width, pgsize.height,BufferedImage.TYPE_INT_RGB);
		         //canvas[i]= new BufferedImage(720, 540, BufferedImage.TYPE_INT_ARGB);
		         Graphics2D graphics = img.createGraphics();
		         

		         //clear the drawing area
		         graphics.setPaint(Color.white);
		         graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
		         //render
		         slide[i].draw(graphics);
		         pptxPad.loadImage(img, i);
		         
		         java.awt.Image newimg = img.getScaledInstance( 100, 75,  java.awt.Image.SCALE_SMOOTH ) ;  
		         ImageIcon imageIcon = new ImageIcon(newimg);
		         //BufferedImage resizedImage = new BufferedImage(80, 75, BufferedImage.TYPE_INT_ARGB);
		         
		     	 //Graphics2D g = resizedImage.createGraphics();
		     	 //g.drawImage(img, 0, 0, 80, 75, null);
		     	 //ImageIcon imageIcon = new ImageIcon(resizedImage);
		         smallview[i]=new SmallView(i,imageIcon);
		         //smallView[i].setSize(75, 75);
		         //smallview[i].setIcon(imageIcon);
		         //smallview[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
		         //smallview[i].addMouseListener(smallview[i]);
		         smallview[i].addActionListener(new ActionListener(){
		 			public void actionPerformed(ActionEvent e){
		 				//drawPad.setCvalue(smallView[0].value);
		 				//System.out.println(smallview[0].value);
		 				pptxPad.setCvalue(smallview[0].value);
		 			}
		 		});
		         SmallViewPanel.add(smallview[i]);
	      }	
		
	}
	//
	public void PenCursor(){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		java.awt.Image  img=toolkit.getImage(getClass().getResource("cursorp.png"));
		Point point=new Point(0,0);
		 cursorpen= toolkit.createCustomCursor(img, point, "cursor");
		
	}
	//
	public void RubberCursor(){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		java.awt.Image  img=toolkit.getImage(getClass().getResource("cursorr.png"));
		Point point=new Point(0,28);
		cursorrubber= toolkit.createCustomCursor(img, point, "cursor");
		
	}
	public static void main(String args[]){
		new DigiClassBoard();
		
	}

}
