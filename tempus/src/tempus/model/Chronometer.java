package tempus.model;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import javafx.scene.control.TextField;
import javafx.scene.media.AudioClip;

public class Chronometer extends Timekeeper{

	private Timer cronometer;

	public Chronometer() {
		// TODO Auto-generated constructor stub
	}

	public Chronometer(int hours, int minutes, int seconds) {
		super(hours, minutes, seconds);
	}

	public Timer getCronometer() {
		return cronometer;
	}

	public void setCronometer(TextField hoursField, TextField minutesField, TextField secondsField) {
		cronometer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				seconds++;

				if (seconds == 60) {
					seconds = 0;
					minutes++;
					updateText(minutesField, minutes);
					if (minutes == 60) {
						minutes = 0;
						hours++;
						updateText(minutesField, minutes);
						updateText(hoursField, hours);
					}
				}

				updateText(secondsField, seconds);

			}

		});
	}

	public void updateText(TextField field, int value) {
		String newText = value < 10 ? "0" + value : Integer.toString(value);
		field.setText(newText);
	}
	
	public void playSound() {
		String sound = "../resources/bell.mp3";
		AudioClip buzzer = new AudioClip(getClass().getResource(sound).toExternalForm());
		buzzer.play();
	}

}
