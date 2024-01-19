package tempus.model;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import javafx.scene.control.TextField;

public class Temporizador {

	private int hours;
	private int minutes;
	private int seconds;
	private Timer timer;

	public Temporizador() {
		// TODO Auto-generated constructor stub
	}

	public Temporizador(int hours, int minutes, int seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	
	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(TextField hoursField, TextField minutesField, TextField secondsField) {
		timer = new Timer(500, new ActionListener() {
	    	@Override
	    	public void actionPerformed(java.awt.event.ActionEvent e) {
	    		if(hours == 0 && minutes == 0 && seconds == 0) {
	    			timer.stop();
	    			System.out.println("Alarme");
	    		} else {
	    			if(seconds <= 0) {
	    				seconds = 60;
	    				minutes--;
	    			}
	    			if(minutes < 0) {
	    				minutes = 1;
	    				hours--;
	    			}

		    		seconds--;
	    		}
	    		secondsField.setText(Integer.toString(seconds));
	    		minutesField.setText(Integer.toString(minutes));
	    		hoursField.setText(Integer.toString(hours));
	    	}
	    });
	}



}
