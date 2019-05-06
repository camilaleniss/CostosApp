package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import modelo.CostosApp;

public class PresupuestoController {
	
	
	 @FXML
	    private TextField txtCIF;

	    @FXML
	    private TextField txtBase;

	    @FXML
	    private MenuButton mbUnidades;

	    @FXML
	    private Button butSave;

	    private CostosApp app;


	  public void init(CostosApp app) {
	    	this.app=app;
	    	this.app.unidadesTasa();
	    	mostrarTasas();
	    }
	  
	  
	  public void mostrarTasas() {
		  mbUnidades.getItems().clear();
		  for (int i = 0; i < app.getUnidadesBase().size(); i++) {
			  String und=app.getUnidadesBase().get(i);
			  MenuItem t1 = new MenuItem(und);
				mbUnidades.getItems().add(t1);
				EventHandler<ActionEvent> a = new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						  for (int i = 0; i < app.getUnidadesBase().size(); i++) {
						String opcion = ((MenuItem) event.getSource()).getText();

						if (opcion.equals(app.getUnidadesBase().get(i))) {
							mbUnidades.setText(t1.getText());
						}
						  }
					}
				};

				t1.setOnAction(a);
		}

	  }
	
	    @FXML
	    void butGuardar(ActionEvent event) {
	    	try {

	    	mbUnidades.getText();
	    	double cif = (Double) ((boolean) (Double.parseDouble(txtCIF.getText())<0) ? new NumberFormatException() : Double.parseDouble(txtCIF.getText()));
	    	int unidades = (Integer) ((boolean) (Integer.parseInt(txtBase.getText())<0) ? new NumberFormatException() : Integer.parseInt(txtBase.getText()));
	    	String nombre =  (String) ((mbUnidades.getText()).equals("Base") ? new Exception() : mbUnidades.getText());

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
	    	txtBase.setText("");
	    	txtCIF.setText("");
	    }
	    
	    void getBack() {
	    	FXMLLoader loader = new FXMLLoader();
	    	loader.setLocation(getClass().getResource("Principal.fxml"));
	    	Parent root;
			try {
				root = loader.load();
				Scene scene = new Scene(root);		
		    	Stage stage = (Stage)butSave.getScene().getWindow();
		    	stage.setScene(scene);
		    	PrincipalController contr = loader.getController();
				contr.initPresupuesto(app);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

	    public void addTasa() {
	    	TextInputDialog dialog = new TextInputDialog("");
	    	dialog.setTitle("Añadir Tasa");
	    	dialog.setContentText("Escriba las unidades de la tasa");

	    	Optional<String> result = dialog.showAndWait();
	    	if (result.isPresent()){
	    			app.addUnidadesTasa(result.get());
	    			mostrarTasas();
	    	}
	    	else {
	    		dialog.close();
	    	}

	    		    }
	  
	    public void eliminarTasa() {
	    	List<String> choices = app.getUnidadesBase();
	    	
	    	ChoiceDialog<String> dialog = new ChoiceDialog<>("Tasas", choices);
	    	dialog.setTitle("Eliminar Tasa");
	    	dialog.setContentText("Escoge una de las tasas");

	    	Optional<String> result = dialog.showAndWait();
	    	if (result.isPresent()){
	    		app.eliminarTasa(result.get());
	    		mostrarTasas();
	    	}
	    	else {
	    		dialog.close();
	    	}

	    }
}
