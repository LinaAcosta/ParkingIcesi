package view;

import controller.ParkingController;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ParckingView extends Stage {
    private TextField nombreTF;
    private TextField apellidoTF;
    private Button insertarUsuarioBtn;
    private Label insertarLabel;
    private TextArea resultadosTA;
    private Label resultadosLabel;
    private Label eliminarLabel;
    private Button insertarCarroBtn;
    private Button eliminar;
    private ParkingController controller;
    private ComboBox carroCombo;
    private ComboBox usuarioCombo;
    public ParckingView(){
        VBox container = new VBox();
        insertarLabel = new Label("Insertar Usuario");
        nombreTF = new TextField();
        apellidoTF = new TextField();
        insertarUsuarioBtn = new Button("Insertar");
        eliminar = new Button("Eliminar");
        resultadosLabel = new Label("Resultados");
        eliminarLabel = new Label("Eliminar Usuario");
        carroCombo = new ComboBox();
        usuarioCombo = new ComboBox();
        resultadosTA = new TextArea();
        insertarCarroBtn = new Button("Insertar Carro");

        container.getChildren().add(insertarLabel);
        container.getChildren().add(nombreTF);
        container.getChildren().add(apellidoTF);
        container.getChildren().add(carroCombo);
        container.getChildren().add(insertarUsuarioBtn);
        container.getChildren().add(eliminarLabel);
        container.getChildren().add(usuarioCombo);
        container.getChildren().add(eliminar);
        container.getChildren().add(resultadosLabel);
        container.getChildren().add(resultadosTA);
        container.getChildren().add(insertarCarroBtn);
        Scene scene = new Scene(container);
        this.setScene(scene);

        controller = new ParkingController(this);
    }

    public TextField getNombreTF() {
        return nombreTF;
    }

    public TextField getApellidoTF() {
        return apellidoTF;
    }
    public Button getEliminar() {
        return eliminar;
    }
    public ComboBox getUsuarioCombo() {
        return usuarioCombo;
    }

    public Button getInsertarUsuarioBtn() {
        return insertarUsuarioBtn;
    }

    public Label getInsertarLabel() {
        return insertarLabel;
    }

    public TextArea getResultadosTA() {
        return resultadosTA;
    }

    public Label getResultadosLabel() {
        return resultadosLabel;
    }

    public Button getInsertarCarroBtn() {
        return insertarCarroBtn;
    }
    public ComboBox getCarroCombo() {
        return carroCombo;
    }
}
