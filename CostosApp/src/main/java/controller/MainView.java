package controller;

import javafx.fxml.FXML;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXListView;

import model.Orden;

public class MainView {

    @FXML
    private JFXButton butAddPresupuesto;

    @FXML
    private JFXButton butAddOrden;

    @FXML
    private JFXButton butVariacion;

    @FXML
    private JFXButton butAddCIFReal;

    @FXML
    private JFXTextField txtNOrden;

    @FXML
    private JFXTextField txtMD;

    @FXML
    private JFXTextField txtMOD;

    @FXML
    private JFXComboBox<?> comboCIF;

    @FXML
    private JFXButton butSubmitCIF;

    @FXML
    private JFXTextField txtCIF;

    @FXML
    private JFXTextField txtVariacion;

    @FXML
    private JFXListView<Orden> listOrdenes;

}

