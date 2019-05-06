package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modelo.CostosApp;

public class EdicionCIFRealController implements Initializable {

    @FXML
    private ChoiceBox<String> cifChoiceBox;
    @FXML
    private GridPane edicionGridPane;
    @FXML
    private Button butCancelar;
    @FXML
    private Button butEditar;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtValor;
    
    private CostosApp app;
    
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	public void init(CostosApp app) {
    	this.app = app;
    	ObservableList<String> listCIF = FXCollections.observableArrayList();
    	for(int i = 0; i < app.getCifreal().size(); i++) {
    		listCIF.add(app.getCifreal().get(i).getNombre());
    	}
    	cifChoiceBox.setItems(listCIF);
    }
	
    @FXML
    void editar(ActionEvent event) {
    	if(cifChoiceBox.getSelectionModel().isEmpty()) {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Debe seleccionar el CIF que desea editar de la lista.");
			alert.showAndWait();
    	}
    	else {
    		showValues();
    		cifChoiceBox.setDisable(true);
    		butEditar.setDisable(true);
    		edicionGridPane.setVisible(true);
    	}
    }
    
    private void showValues() {
    	int indice = cifChoiceBox.getSelectionModel().getSelectedIndex();
    	txtNombre.setText(app.getCifreal().get(indice).getNombre());
    	txtValor.setText(String.valueOf(app.getCifreal().get(indice).getValor()));
    }
    
    @FXML
	void guardarCambios(ActionEvent event) {
    	if(txtNombre.getText().isEmpty() || txtValor.getText().isEmpty()) {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Ningún campo debe quedar vacío.");
			alert.showAndWait();
    	}
    	else {
    		int indice = cifChoiceBox.getSelectionModel().getSelectedIndex();
    		app.getCifreal().get(indice).setNombre(txtNombre.getText());
    		app.getCifreal().get(indice).setValor(Double.parseDouble(txtValor.getText()));
    		regresar();
    	}    	
	}

	@FXML
    void cancelar(ActionEvent event) {
		regresar();
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

}
