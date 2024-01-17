package tempus;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TempusApp extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		URL arquivoFXML = getClass().getResource("view/home.fxml");
		VBox raiz = FXMLLoader.load(arquivoFXML);
		
		Scene cena = new Scene(raiz);
		primaryStage.setResizable(true);
		primaryStage.setScene(cena);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}