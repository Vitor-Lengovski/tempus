package tempus.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tempus.model.Temporizador;

public class HomeController implements Initializable{

	@FXML private TextField hoursField;

	@FXML private TextField minutesField;

    @FXML private TextField secondsField;

    @FXML private Label objectivesLabel;

    @FXML private Button startButton;

    @FXML private Button stopButton;
    
    @FXML private Button resetButton;
    
    int seconds;
    int minutes;
    int hours;
    Temporizador temporizador;
 
    @FXML
    void startTimer(ActionEvent event) {
    	seconds = Integer.parseInt(secondsField.getText());
	    minutes = Integer.parseInt(minutesField.getText());
	    hours = Integer.parseInt(hoursField.getText());
	    temporizador = new Temporizador(hours, minutes, seconds);
	    temporizador.setTimer(hoursField, minutesField, secondsField);
    	temporizador.getTimer().start();
    	hoursField.setEditable(false);
    	minutesField.setEditable(false);
    	secondsField.setEditable(false);
    }

    @FXML
    void stopTimer(ActionEvent event) {
    	temporizador.getTimer().stop();
    	hoursField.setEditable(true);
    	minutesField.setEditable(true);
    	secondsField.setEditable(true);
    }
    
    @FXML
    void resetTimer(ActionEvent event) {
    	temporizador.getTimer().stop();
    	temporizador = new  Temporizador(0, 0, 0);
    	hoursField.setText(Integer.toString(0));
    	minutesField.setText(Integer.toString(0));
    	secondsField.setText(Integer.toString(0));
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		seconds = Integer.parseInt(secondsField.getText());
	    minutes = Integer.parseInt(minutesField.getText());
	    hours = Integer.parseInt(hoursField.getText());
	    temporizador = new Temporizador(hours, minutes, seconds);
	    temporizador.setTimer(hoursField, minutesField, secondsField);
	}
	
	
}


