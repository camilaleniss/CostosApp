package application;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modelo.CIFReal;
import modelo.CostosApp;

public class PrincipalController {
	
	@FXML
    private TextField txtNorden;
    @FXML
    private TextField txtMD;
    @FXML
    private TextField txtMOD;
    @FXML
    private TextField txtCIF;
    @FXML
    private Button butAddPresupuesto;
    @FXML
    private Button butAddCIFReal;
    @FXML
    private Button butVariacion;
    @FXML
    private Button butAddOrden;
    @FXML
    private ListView<GridPane> listOrdenes;    
    @FXML
    private GridPane listReales;    
    @FXML
    private TextField txtTasaCIF;    
    @FXML
    private HBox opcionesReal;    
    @FXML
    private HBox opcionOrdenes;
    @FXML
    private Label labCIFAplicados;
    @FXML
    private Label labCIFReales;
    @FXML
    private Label labVariacion;    
    @FXML
    private GridPane PaneVariacion;
    
    private CostosApp app;
	    
    @FXML
    void initialize() {
    	if(app == null) {
    		app = new CostosApp();
    	}
    	if (app.getTasasCif() == null) {
    		PaneVariacion.setVisible(false);
    		opcionesReal.setVisible(false);
    		opcionOrdenes.setVisible(false);
    		txtCIF.setEditable(false);
    		txtMD.setEditable(false);
    		txtMOD.setEditable(false);
    		txtNorden.setEditable(false);
    	}
    	updateList();
    }  
    
    @FXML
    void addPresupuesto(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("Presupuesto.fxml"));
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
    
    public void updateList() {
    	ObservableList<GridPane> list = FXCollections.observableArrayList();
    	ArrayList<String> ordenes = app.toArrayListOrdenes();
    	for (int i = 0; i < ordenes.size(); i++) {
    		String[] ord=ordenes.get(i).split(";");
    		list.add(panelOrden(ord));
    	}
    	listOrdenes.setItems(list);
    	if (app.getCifreal().size()>0 && app.getOrdenes().size()>0) {
			PaneVariacion.setVisible(true);
		}
    	if (app.getTasasCif()!=null) {
    		txtTasaCIF.setText(""+app.getTasasCif().toString());
	    	txtCIF.setEditable(true);
			txtMD.setEditable(true);
			txtMOD.setEditable(true);
			txtNorden.setEditable(true);
			butAddOrden.setDisable(false);
    	}
		if(app.getOrdenes().size()>0) {
			opcionOrdenes.setVisible(true);
		}
	    else {
    		opcionOrdenes.setVisible(false);
		    PaneVariacion.setVisible(false);
	    }
    }
    
    public GridPane panelOrden(String[] ord) {
    	GridPane orden = new GridPane();
    	//DecimalFormat formato1 = new DecimalFormat("#,###.00");
    		orden.add(new Label("Orden # "+ord[0]), 0, 0);
    		orden.add(new Label("Material Directo: "), 0, 1);
    		orden.add(new Label (ord[1]), 1, 1);
    		orden.add(new Label("Mano de Obra Directa: "), 0, 2);
    		orden.add(new Label(ord[2]), 1, 2);
    		orden.add(new Label("CIF: " ), 0, 3);
    		orden.add(new Label(ord[3]), 1, 3);
    		orden.add(new Label("Total Orden: " ), 0, 4);
    		orden.add(new Label(ord[4]), 1, 4);
    		orden.add(new Label("----------------------------"), 0, 5);
    		orden.add(new Label("-----------------------------"), 1, 5);
    	return orden;
    }
    
    @FXML
    void addOrdenes(ActionEvent event) {
    	try {
	    	String id = (txtNorden.getText());
	    	double md = Double.parseDouble(txtMD.getText());
	    	double mod = Double.parseDouble(txtMOD.getText());
	    	double cif = Double.parseDouble(txtCIF.getText());
	    	if(md<0 || mod<0 || cif<0)
	    		throw new NumberFormatException ();
	    	boolean ya=app.addOrden(id, md, mod, cif);
	    	if (!ya)
	    		throw new NullPointerException();
    	}catch(NumberFormatException e) {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Ingrese un número válido");
			alert.showAndWait();
    	}catch(NullPointerException e) {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Orden repetida");
			alert.showAndWait();
    	}catch(Exception e) {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Datos inválidos, vuelva a intentarlo");
			alert.showAndWait();
    	}
    	updateList();
    	txtNorden.setText("");
    	txtMD.setText("");
    	txtMOD.setText("");
    	txtCIF.setText("");
    }

    @FXML
    void addCIFREAL(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("Real.fxml"));
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
    
    public void updateListReales() {
    	listReales.getChildren().clear();
    	ArrayList<CIFReal> reales = app.getCifreal();
    	for (int i = 0; i < reales.size(); i++) {
    		Label a=new Label("   CIF Real");
    		a.setTextFill(Color.web("#0076a3"));
    		a.setScaleX(1.5);
            a.setScaleY(1.5);
    		listReales.add(a, 0, 0);
    		Label b=new Label("    Valor");
    		b.setTextFill(Color.web("#0076a3"));
    		b.setScaleX(1.5);
            b.setScaleY(1.5);
    		listReales.add(b, 1, 0);
			listReales.add(new Label(reales.get(i).getNombre()), 0, i+1);
			listReales.add(new Label("$" + reales.get(i).getValor() + ""), 1, i+1);
		}
    	if(app.getCifreal().size()>0) {
	    	opcionesReal.setVisible(true);
    	}
    	else {
			opcionesReal.setVisible(false);
	    	PaneVariacion.setVisible(false);

    	}
    }
    
    public void initOrdenes(CostosApp app) {
    	this.app = app;
    	updateList();
		updateListReales();
    }
    
    public void initReal(CostosApp app) {
    	this.app=app;
		updateList();
		updateListReales();
    }
    
    public void initPresupuesto(CostosApp app) {
    	this.app=app;
    	butAddCIFReal.setDisable(false);
		PaneVariacion.setVisible(false);		
		
		updateListReales();
		updateList();
    }
    
    @FXML
    void showVariacion(ActionEvent event) {
    	app.calcularVariacion();
    	double tcosto = app.calcularCifAplicado();
    	double tReal = app.calcularCIFReal();
    	double variacion = app.getVariacion();

    	DecimalFormat formato1 = new DecimalFormat("#,###.00");
    	String var = (variacion<0) ? "Cif subaplicado " : "Cif sobreaplicado";
    	labCIFReales.setText(formato1.format(tReal));
    	labCIFAplicados.setText(formato1.format(tcosto));
    	labVariacion.setText(formato1.format(variacion)+" "+var);
    }
    
    public void eliminarOrden() {
    	List<String> choices = new ArrayList<>() ;
	    for (int i = 0; i < app.getOrdenes().size(); i++) {
			choices.add(""+app.getOrdenes().get(i).getNum());
		}
    	ChoiceDialog<String> dialog = new ChoiceDialog<>("Orden #", choices);
    	dialog.setTitle("Eliminar Orden");
    	dialog.setContentText("Escoge una de las ordenes");
    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent()){
    		app.eliminarOrden((result.get()));
    		updateList();
    		if(app.getOrdenes().size()==0) {
    			opcionOrdenes.setVisible(false);
    		}
    	}
	    else {
	    	dialog.close();
	    }
    }
    
    public void eliminarCifReal() {
    	List<String> choices = new ArrayList<>() ;
    	for (int i = 0; i < app.getCifreal().size(); i++) {
			choices.add(""+app.getCifreal().get(i).getNombre());
		}
    	ChoiceDialog<String> dialog = new ChoiceDialog<>("Cif Real", choices);
    	dialog.setTitle("Eliminar CifReal");
    	dialog.setContentText("Escoge uno de los nombres para eliminar");

    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent()){
    		app.eliminarCifreal((result.get()));
    	}
    	else {
    		dialog.close();
    	}
		updateListReales();
    }
    
    @FXML
    void editarOrden(ActionEvent event) {
    	if(listOrdenes.getSelectionModel().isEmpty()) {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Debe seleccionar primero una orden de la lista.");
			alert.showAndWait();
    	}
    	else {
    		app.setOrdenSeleccionada(app.buscarOrden(listOrdenes.getSelectionModel().getSelectedIndex()));
    		abrirEdicionOrden();
    	}    	
    }
    
    private void abrirEdicionOrden() {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("EdicionOrden.fxml"));
    	Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
	    	Stage stage = (Stage) butAddCIFReal.getScene().getWindow();
	    	stage.setScene(scene);
	    	EdicionOrdenController contr = loader.getController();
	    	contr.init(app);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    

    @FXML
    void editarCIFReal(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("EdicionCIFReal.fxml"));
    	Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
	    	Stage stage = (Stage) butAddCIFReal.getScene().getWindow();
	    	stage.setScene(scene);
	    	EdicionCIFRealController contr = loader.getController();
	    	contr.init(app);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	    
}
