package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.CostosApp;

public class MainView {

    @FXML
    private JFXTextField txtNOrden;

    @FXML
    private JFXTextField txtMD;

    @FXML
    private JFXTextField txtMOD;

    @FXML
    private JFXTextField txtCIF;

    @FXML
    private JFXTextField txtVariacion;

    @FXML
    private JFXButton butAddPresupuesto;

    @FXML
    private JFXButton butAddCIFReal;

    @FXML
    private JFXButton butVariacion;

    @FXML
    private JFXButton butAddOrden;

    @FXML
    private JFXListView<Label> listOrdenes;
    
    private CostosApp app;
    

    @FXML
    void addCIFREAL(ActionEvent event) {

    }

    @FXML
    void addOrdenes(ActionEvent event) {

    }

    @FXML
    void addPresupuesto(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/view/PresupuestoController.fxml"));
    	Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
	    	Stage stage = (Stage)butAddPresupuesto.getScene().getWindow();
	    	stage.setScene(scene);
	    	PresupuestoController contr = loader.getController();
	    	contr.init(app);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }

    @FXML
    void showVariacion(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	if(app==null) {
    		app = new CostosApp();
    	}
    	
    	if (app.getTasasCif()==null) {
    		butAddCIFReal.setDisable(true);
    		butAddOrden.setDisable(true);
    		butVariacion.setDisable(true);
    	}
    }
    
    @FXML
    void refresh(ActionEvent event) {
    	ObservableList<Label> list = FXCollections.observableArrayList();
    	ArrayList<String> ordenes = app.toArrayListOrdenes();
    	
    	for (int i = 0; i < ordenes.size(); i++) {
			list.add(new Label(ordenes.get(i)));
		}
    	
    	listOrdenes.setItems(list);
    }

    public void init(CostosApp app) {
    	this.app=app;
    	butAddCIFReal.setDisable(false);
		butAddOrden.setDisable(false);
		butVariacion.setDisable(false);
    }
    
}
