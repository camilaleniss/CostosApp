package controller;

import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.CostosApp;

public class PresupuestoController {

    @FXML
    private JFXTextField txtCIF;

    @FXML
    private JFXTextField txtBase;

    @FXML
    private JFXTextField txtNombre;

    @FXML
    private JFXButton butSave;

    private CostosApp app;
    
    @FXML
    void butGuardar(ActionEvent event) {
    	try {
    	double cif = (Double) ((boolean) (Double.parseDouble(txtCIF.getText())<0) ? new NumberFormatException() : Double.parseDouble(txtCIF.getText()));
    	int unidades = (Integer) ((boolean) (Integer.parseInt(txtBase.getText())<0) ? new NumberFormatException() : Integer.parseInt(txtBase.getText()));
    	String nombre =  (String) (txtNombre.getText().equals("") ? new Exception() : txtNombre.getText());
    	app.addPresupuesto(cif, unidades, nombre);
    	getBack();
    	}catch(NumberFormatException e) {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Ingrese un número válido");
			alert.showAndWait();
    	}catch(Exception e) {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Datos inválidos, por favor vuelva a intentarlo");
			alert.showAndWait();
    	}
    	update();
    }

    void update() {
    	txtNombre.setText("");
    	txtBase.setText("");
    	txtCIF.setText("");
    }
    
    void getBack() {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/view/MainView.fxml"));
    	Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);		
	    	Stage stage = (Stage)butSave.getScene().getWindow();
	    	stage.setScene(scene);
	    	MainView contr = loader.getController();
			contr.init(app);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void init(CostosApp app) {
    	this.app=app;
    }

}

