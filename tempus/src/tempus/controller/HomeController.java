package tempus.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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
	private Label objectivesLabel;

	Temporizador timer;
	int seconds;
	int minutes;
	int hours;

	@FXML
	void startTimer(ActionEvent event) {
		seconds = Integer.parseInt(secondsField.getText());
		minutes = Integer.parseInt(minutesField.getText());
		hours = Integer.parseInt(hoursField.getText());

		if (hours != 0 || minutes != 0 || seconds != 0) {
			timer = new Temporizador(hours, minutes, seconds);
			timer.setPomodoroMode1(true);			

			startBtn.setText("Parar");
			startBtn.setOnAction(startEvent -> {
				stopTimer(startEvent);
			});

			timer.setTimer(hoursField, minutesField, secondsField);

			changeFieldsEditability(false);
			timer.getTimer().start();
		}

	}

	@FXML
	void stopTimer(ActionEvent event) {
		if (timer.getTimer() != null && timer.getTimer().isRunning()) {
			timer.getTimer().stop();
			startBtn.setText("Retomar");
			startBtn.setOnAction(startEvent -> {
				startTimer(startEvent);
			});

			changeFieldsEditability(true);

		}
	}

	@FXML
	void resetTimer(ActionEvent event) {
		if (timer.getTimer() != null) {
			timer.getTimer().stop();
			startBtn.setText("Iniciar");
			startBtn.setOnAction(startEvent -> {
				startTimer(startEvent);
			});

			changeFieldsEditability(true);

			timer = new Temporizador(0, 0, 0);
			timer.updateText(hoursField, 0);
			timer.updateText(minutesField, 0);
			timer.updateText(secondsField, 0);
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
		if (fieldValue > 60) {
			field.setText("0" + field.getText().charAt(1));
		}

		if (fieldValue < 10 && field.getText().length() < 2) {
			field.setText("0" + field.getText());
		}

		field.end();
	}

	@FXML
	void choosePomodoroMode(ActionEvent event) {
		timer.updateText(hoursField, 0);
		timer.updateText(minutesField, 25);
		timer.updateText(secondsField, 0);
		pomodoroModeBtn.setStyle("-fx-background-color: rgb(133, 255, 255)");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		seconds = Integer.parseInt(secondsField.getText());
		minutes = Integer.parseInt(minutesField.getText());
		hours = Integer.parseInt(hoursField.getText());
		timer = new Temporizador(hours, minutes, seconds);
		timer.setTimer(hoursField, minutesField, secondsField);
	}

	public void changeFieldsEditability(boolean isEditable) {
		hoursField.setEditable(isEditable);
		minutesField.setEditable(isEditable);
		secondsField.setEditable(isEditable);
	}

}
