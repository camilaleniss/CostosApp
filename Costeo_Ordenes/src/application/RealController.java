package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.CostosApp;

public class RealController {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtReal;

    @FXML
    private Button butAtras;

    private CostosApp app;

	
	
	 public void init(CostosApp app) {
	    	this.app=app;
	    }
	 
	 	 
	   @FXML
	    void butAtras() {
	    	FXMLLoader loader = new FXMLLoader();
	    	loader.setLocation(getClass().getResource("Principal.fxml"));
	    	Parent root;
			try {
				root = loader.load();
				Scene scene = new Scene(root);		
		    	Stage stage = (Stage)butAtras.getScene().getWindow();
		    	stage.setScene(scene);
		    	PrincipalController contr = loader.getController();
				contr.initReal(app);
				stage.show();
			} catch (IOException e) {
	
				e.printStackTrace();
			}
	    }

	    @FXML
	    void butGuardar(ActionEvent event) {
	    	try {
	    	String name = (String) (txtName.getText().equals("") ? new Exception() : txtName.getText());
	    	Double real = (Double) ((boolean) (Double.parseDouble(txtReal.getText())<0) ? new NumberFormatException() : Double.parseDouble(txtReal.getText()));
	    	
	    	app.addCIFReal(name,real);
	    	txtReal.setText("");
	    	txtName.setText("");
	    	
	    	}catch (NumberFormatException e) {
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
	    	
	    }
}
