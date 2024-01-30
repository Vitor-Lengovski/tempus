package tempus.model;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import javafx.scene.control.TextField;
import javafx.scene.media.AudioClip;

public class Temporizador {

	private int hours;
	private int minutes;
	private int seconds;
	private Timer timer;

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

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(TextField hoursField, TextField minutesField, TextField secondsField) {
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if (hours == 0 && minutes == 0 && seconds == 0) {
					timer.stop();
					System.out.println("Alarme");
					playSound();
				} else {
					if (seconds == 0) {
						if (minutes > 0) {
							minutes--;
						} else {
							hours--;
							minutes = 1;
							updateText(hoursField, hours);
						}
						updateText(minutesField, minutes);
						seconds = 60;
					}
					seconds--;

					updateText(secondsField, seconds);

				}
			}
		});
	}

	public void updateText(TextField field, int value) {
		if (value < 10) {
			field.setText("0" + value);
		} else {
			field.setText(Integer.toString(value));
		}

	}

	public void playSound() {
		AudioClip buzzer = new AudioClip(getClass().getResource("../resources/bell.mp3").toExternalForm());
		buzzer.play();
	}

}
