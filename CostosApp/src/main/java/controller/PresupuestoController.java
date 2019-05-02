package controller;

import com.gluonhq.charm.glisten.control.TextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class PresupuestoController {

    @FXML
    private TextField txtCIFPresupuestado;

    @FXML
    private TextField txtBase;

    @FXML
    private Button butGuardar;

    @FXML
    private TextField txtPorcentaje;

    @FXML
    private ComboBox<?> comboNombre;

    @FXML
    void Guardar(ActionEvent event) {

    }

}


