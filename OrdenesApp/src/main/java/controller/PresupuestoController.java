package controller;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    	double cif = Double.parseDouble(txtCIF.getText());
    	int unidades = Integer.parseInt(txtBase.getText());
    	String nombre = txtNombre.getText();
    	app.addPresupuesto(cif, unidades, nombre);
    	
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

