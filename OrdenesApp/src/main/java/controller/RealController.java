package controller;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import model.CostosApp;

public class RealController {

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtReal;

    @FXML
    private JFXButton txtAtras;

    @FXML
    private JFXButton butCargar;
    
    private CostosApp app;

    public void init(CostosApp app) {
    	this.app=app;
    }
    
    @FXML
    void butAtras(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/view/MainView.fxml"));
    	Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);		
	    	Stage stage = (Stage)txtAtras.getScene().getWindow();
	    	stage.setScene(scene);
	    	MainView contr = loader.getController();
			contr.init(app);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void butGuardar(ActionEvent event) {
    	Double real = Double.parseDouble(txtReal.getText());
    	app.addCIFReal(real);
    }

}

