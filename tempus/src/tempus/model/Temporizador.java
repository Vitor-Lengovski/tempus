package tempus.model;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import javafx.scene.control.TextField;
import javafx.scene.media.AudioClip;

public class Temporizador {

	private int hours;
	private int minutes;
	private int seconds;
	private boolean pomodoroMode1;

	private Timer timer;

	// TODO implements milliseconds. In the actual code, user can change start/pause
	// quickly, making the timer not run

	public Temporizador() {

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

	public boolean isPomodoroMode1() {
		return pomodoroMode1;
	}

	public void setPomodoroMode1(boolean pomodoroMode1) {
		this.pomodoroMode1 = pomodoroMode1;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(TextField hoursField, TextField minutesField, TextField secondsField) {
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {

				if (seconds == 0) {
					if (minutes > 0) {
						minutes--;
					} else {
						hours--;
						updateText(hoursField, hours);
						minutes = 59;
					}
					updateText(minutesField, minutes);
					seconds = 60;
				}
				seconds--;
				updateText(secondsField, seconds);

				if (hours == 0 && minutes == 0 && seconds == 0) {
					timer.stop();
					playSound();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}

					if (pomodoroMode1) {
						minutes = 5;
						updateText(minutesField, minutes);
						timer.start();
					}
				}
			}

		});
	}

	public void updateText(TextField field, int value) {
		String newText = value < 10 ? "0" + value : Integer.toString(value);
		field.setText(newText);
	}

	public void playSound() {
		String sound = "../resources/" + (pomodoroMode1 ? "break.mp3" : "bell.mp3");

		AudioClip buzzer = new AudioClip(getClass().getResource(sound).toExternalForm());
		buzzer.play();
	}

}
