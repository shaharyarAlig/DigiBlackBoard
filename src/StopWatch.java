import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class StopWatch extends JPanel {
	
	private JLabel displayTimeLabel;

	// The variables that keep track of start and stopTimerButton
	private long watchStart, watchEnd;

	// The Threaded Swing widget
	private Timer theChronometer;

	// Keeps track of time when pausing the Timer.
	private long pausedTime;

	// Lets the program know if starting or resuming
	private boolean paused = false;

	// Button that changes from "Start" to "Resume" depending on pause status.
	private JButton activateTimerButton;
	
	public StopWatch(){
		
		displayTimeLabel = new JLabel("00 min : 00 sec");
    	displayTimeLabel.setHorizontalAlignment(JLabel.CENTER);
    	displayTimeLabel.setOpaque(false);
    	displayTimeLabel.setForeground(new Color(107,173,246));
    	displayTimeLabel.setFont(new Font("DS-Digital", Font.BOLD, 25));
    	add(displayTimeLabel);
    	theChronometer =
    	        new Timer(1000,new ActionListener(){
    	        		public void actionPerformed(ActionEvent e){
    	        			int seconds = (int)(System.currentTimeMillis()-watchStart)/1000;
    	        			int days = seconds / 86400;
    						int hours = (seconds / 3600) - (days * 24);
    						int min = (seconds / 60) - (days * 1440) - (hours * 60);
    						int sec = seconds % 60;
    	        			String s = new String(min+ " min  : "+sec+" sec ");
    	        	        displayTimeLabel.setText(s);
    	        		}
    	        });
	}

	public void start(){
		 watchStart = System.currentTimeMillis();
  	     theChronometer.start();
	}
	
	public void stop(){
		theChronometer.stop();
		displayTimeLabel.setText("00 min : 00 sec");
	}
	
	public void pause(){
		long now = System.currentTimeMillis();
    	pausedTime -= (now - watchStart);
    	theChronometer.stop();
    	paused = true;
	}
	
	public void resume(){
	   watchStart = System.currentTimeMillis()+pausedTime;
 	   pausedTime = 0;
 	   theChronometer.start();
 	   paused = false;
	}

}
