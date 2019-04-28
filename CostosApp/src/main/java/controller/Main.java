package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception { 

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/MainView.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("COSTOS APP");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
		MainView contr = loader.getController();
		primaryStage.show();

		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
