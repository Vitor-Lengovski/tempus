package tempus.controller;

import java.net.URL;
import java.util.ResourceBundle;

import java.awt.event.*;
import javax.swing.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class HomeController implements Initializable{

	@FXML private Text hoursText;

    @FXML private Text minutesText;

    @FXML private Text secondsText;

    @FXML private Label objectivesLabel;

    @FXML private Button startButton;

    @FXML private Button stopButton;
    
    int seconds = 0;
    int minutes = 1;
    int hours = 0;

    Timer timer = new Timer(250, new ActionListener() {
    	@Override
    	public void actionPerformed(java.awt.event.ActionEvent e) {
    		if(seconds <= 0) {
    			seconds = 60;
    			minutes--;
    		}
    		if(minutes <= 0) {
    			minutes = 1;
    			hours--;
    		}
    		
    		if(hours == 0) {
    			
    		}
    		seconds--;
    		secondsText.setText(Integer.toString(seconds));
    		minutesText.setText(Integer.toString(minutes));
    		hoursText.setText(Integer.toString(hours));

    	}
    });
    @FXML
    void startTimer(ActionEvent event) {
    	timer.start();
    }

    @FXML
    void stopTimer(ActionEvent event) {
    	timer.stop();
    	startButton.setText("Retomar");
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			
	}
	
	
}


