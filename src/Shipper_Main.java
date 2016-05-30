/*
 * @author : Aditya Dwivedi (2014128), Nishant Yadav(2014067)
 */
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Shipper_Main extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

	@Override
	public void start(Stage stage){
		// TODO Auto-generated method stub
		stage.setTitle("Shipper");
		try {
			AnchorPane pane=FXMLLoader.load(Shipper_Main.class.getResource("Shipper.fxml"));
			stage.setScene(new Scene(pane));
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
