package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import modelo.CostosApp;
import modelo.Orden;

public class EdicionOrdenController implements Initializable {

    @FXML
    private TextField mdTextField;
    @FXML
    private TextField modTextField;
    @FXML
    private TextField unidadesBaseTextField;
    @FXML
    private Label numOrdenLabel;
    @FXML
    private Button butGuardar;
    @FXML
    private Button butCancelar;
    
    private CostosApp app;
    private Orden ordenSeleccionada;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	public void init(CostosApp app) {
    	this.app = app;
    	ordenSeleccionada = app.getOrdenSeleccionada();
    	numOrdenLabel.setText(String.valueOf(ordenSeleccionada.getNum()));
    	mdTextField.setText(String.valueOf(ordenSeleccionada.getMd()));
    	modTextField.setText(String.valueOf(ordenSeleccionada.getMod()));
    	unidadesBaseTextField.setText(String.valueOf(ordenSeleccionada.getCif() / app.getTasasCif().getTasaCif()));
    }
	
	private void regresar() {
		app.setOrdenSeleccionada(null);
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("Principal.fxml"));
    	Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);		
	    	Stage stage = (Stage)butCancelar.getScene().getWindow();
	    	stage.setScene(scene);
	    	PrincipalController contr = loader.getController();
			contr.initOrdenes(app);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    @FXML
	void guardarCambios(ActionEvent event) {
    	if(mdTextField.getText().isEmpty() || modTextField.getText().isEmpty() || unidadesBaseTextField.getText().isEmpty()) {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Ningún campo debe quedar vacío.");
			alert.showAndWait();
    	}
    	else {
    		ordenSeleccionada.setMd(Double.parseDouble(mdTextField.getText()));
			ordenSeleccionada.setMod(Double.parseDouble(modTextField.getText()));
			ordenSeleccionada.setCif(Double.parseDouble(unidadesBaseTextField.getText()), app.getTasasCif().getTasaCif());
			regresar();
    	}
	}

	@FXML
    void cancelar(ActionEvent event) {
    	regresar();
    }

}

