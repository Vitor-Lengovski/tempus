package tempus.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.Timer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import tempus.model.Chronometer;
import tempus.model.Temporizador;

public class HomeController implements Initializable {

	@FXML
	private TextField hoursField;
	@FXML
	private TextField minutesField;
	@FXML
	private TextField secondsField;

	@FXML
	private Button startBtn;
	@FXML
	private Button resetBtn;
	@FXML
	private Button pomodoroModeBtn;
	@FXML
	private Button cronometerBtn;

	@FXML
	private Label objectivesLabel;

	Timer tempo;
	Temporizador timer;
	Chronometer cronometer;

	int mode;	//0 = standard timer, 1 = pomodoro mode, 2 = chronometer mode
	int seconds;
	int minutes;
	int hours;

	@FXML
	void chooseCronometerMode(ActionEvent event) {

		if (tempo != null && tempo.isRunning()) {
			tempo.stop();
		}
		mode = 2;
		changeStartButton("Iniciar");
		cronometer = new Chronometer(0, 0, 0);
		updateAllFields(0, 0, 0);
		tempo = cronometer.getCronometer();
		cronometerBtn.setStyle("-fx-background-color: rgb(133, 255, 255)");
		pomodoroModeBtn.setStyle("-fx-background-color: transparent");

	}

	@FXML
	void choosePomodoroMode(ActionEvent event) {

		if (tempo != null && tempo.isRunning()) {
			tempo.stop();
		}
		mode = 1;
		changeStartButton("Iniciar");
		timer = new Temporizador(0, 25, 0, true);
		updateAllFields(0, 25, 0);
		tempo = timer.getTimer();
		pomodoroModeBtn.setStyle("-fx-background-color: rgb(133, 255, 255)");
		cronometerBtn.setStyle("-fx-background-color: transparent");

	}

	@FXML
	void startTimer(ActionEvent event) {
		seconds = Integer.parseInt(secondsField.getText());
		minutes = Integer.parseInt(minutesField.getText());
		hours = Integer.parseInt(hoursField.getText());

		if (mode == 1 || mode == 0) {
			if (hours != 0 || minutes != 0 || seconds != 0) {
				timer.setHours(hours);
				timer.setMinutes(minutes);
				timer.setSeconds(seconds);
				timer.setTimer(hoursField, minutesField, secondsField);
				tempo = timer.getTimer();
			}
		} else if (mode == 2) {
			cronometer = new Chronometer(hours, minutes, seconds);
			cronometer.setCronometer(hoursField, minutesField, secondsField);
			tempo = cronometer.getCronometer();
			
		}
		changeFieldsEditability(false);
		tempo.start();
		changeStartButton("Pausar");
	}

	@FXML
	void stopTimer(ActionEvent event) {
		if (tempo != null && tempo.isRunning()) {
			tempo.stop();
			changeStartButton("Retomar");
			changeFieldsEditability(true);
		}
	}

	@FXML
	void resetTimer(ActionEvent event) {
		if (tempo != null) {
			tempo.stop();

			changeStartButton("Iniciar");
			changeFieldsEditability(true);

			timer = new Temporizador(0, 0, 0, false);
			updateAllFields(0, 0, 0);
		}
	}

	@FXML
	void validate(KeyEvent event) {
		TextField field = ((TextField) event.getSource());
		String allowedChars = "0123456789";

		if (!allowedChars.contains(event.getCharacter())) {
			field.setText(field.getText().replace(event.getCharacter(), ""));
		}

		if (field.getText().length() > 2) {
			field.setText(field.getText().substring(1, 3));
		}

		int fieldValue = Integer.parseInt(field.getText());
		if (fieldValue > 60 && !field.getId().equals("hoursField")) {
			field.setText("0" + field.getText().charAt(1));
		}

		if (fieldValue < 10 && field.getText().length() <= 2) {
			field.setText("0" + field.getText());
		}

		field.end();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		seconds = Integer.parseInt(secondsField.getText());
		minutes = Integer.parseInt(minutesField.getText());
		hours = Integer.parseInt(hoursField.getText());
		mode = 0;
		timer = new Temporizador(hours, minutes, seconds, false);
		timer.setTimer(hoursField, minutesField, secondsField);
		tempo = timer.getTimer();
	}

	public void changeFieldsEditability(boolean isEditable) {
		hoursField.setEditable(isEditable);
		minutesField.setEditable(isEditable);
		secondsField.setEditable(isEditable);
	}

	public void changeStartButton(String text) {
		startBtn.setText(text);
		switch (text) {
		case "Pausar":
			startBtn.setOnAction(startEvent -> {
				stopTimer(startEvent);
			});
			break;

		case "Retomar", "Iniciar":
			startBtn.setOnAction(startEvent -> {
				startTimer(startEvent);
			});
			break;
		}
	}

	public void updateAllFields(int hours, int minutes, int seconds) {
		String hoursContent = hours < 10 ? "0" + hours : Integer.toString(hours);
		String minutesContent = minutes < 10 ? "0" + minutes : Integer.toString(minutes);
		String secondsContent = seconds < 10 ? "0" + seconds : Integer.toString(seconds);

		hoursField.setText(hoursContent);
		minutesField.setText(minutesContent);
		secondsField.setText(secondsContent);
	}

}
