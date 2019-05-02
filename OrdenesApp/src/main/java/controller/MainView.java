package controller;

import java.io.IOException;
import java.text.DecimalFormat;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
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
    private Label labVariacion;

    @FXML
    private JFXListView<String> listOrdenes;
    
    @FXML
    private JFXTextField txtTasaCIF;
    
    private CostosApp app;
    

    @FXML
    void addCIFREAL(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/view/RealController.fxml"));
    	Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
	    	Stage stage = (Stage) butAddCIFReal.getScene().getWindow();
	    	stage.setScene(scene);
	    	RealController contr = loader.getController();
	    	contr.init(app);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void addOrdenes(ActionEvent event) {
    	try {
    	int id = Integer.parseInt(txtNOrden.getText());
    	double md = Double.parseDouble(txtMD.getText());
    	double mod = Double.parseDouble(txtMOD.getText());
    	double cif = Double.parseDouble(txtCIF.getText());
    	
    	
    	if(id<0 || md<0 || mod<0 || cif<0)
    		throw new NumberFormatException ();
    	
    	
    		app.addOrden(id, md, mod, cif);
    	
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
			alert.setContentText("Datos inválidos, vuelva a intentarlo");

			alert.showAndWait();
    	}
    	updateList();
    	txtNOrden.setText("");
    	txtMD.setText("");
    	txtMOD.setText("");
    	txtCIF.setText("");
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
    	app.calcularVariacion();
    	double variacion = app.getVariacion();
    	DecimalFormat formato1 = new DecimalFormat("#.0");
    	txtVariacion.setText("$"+formato1.format(variacion));
    	String var = (variacion<0) ? "Cif subaplicado " : "Cif sobreaplicado";
    	labVariacion.setText(var);
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
    		labVariacion.setVisible(false);
    		txtVariacion.setVisible(false);
    	}
    	txtTasaCIF.setEditable(false);
    	txtVariacion.setEditable(false);
    	updateList();
    }
    
    public void updateList() {
    	ObservableList<String> list = FXCollections.observableArrayList();
    	ArrayList<String> ordenes = app.toArrayListOrdenes();
    	
    	for (int i = 0; i < ordenes.size(); i++) {
			list.add(ordenes.get(i));
		}
    	
    	listOrdenes.setItems(list);
    	
    	if (app.getCifreal().size()>0 && app.getOrdenes().size()>0) {
			butVariacion.setDisable(false);
			labVariacion.setVisible(true);
			txtVariacion.setVisible(true);
		}
    	
    	if (app.getTasasCif()!=null)
    		txtTasaCIF.setText(""+app.getTasasCif().toString());
		
    }
    
    public void init(CostosApp app) {
    	this.app=app;
    	butAddCIFReal.setDisable(false);
		butAddOrden.setDisable(false);
		labVariacion.setVisible(false);
		txtVariacion.setVisible(false);
		updateList();
    }
    
}
