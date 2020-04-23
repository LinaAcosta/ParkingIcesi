package view;

import controller.InsertarCarroController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InsertarCarroView extends Stage {
    private Label insertarCarroLabel;
    private TextField placa;
    private TextField marca;
    private ComboBox usuarioCombo;
    private Button insertarCarroBtn;
    private InsertarCarroController controller;
    private OnCloseListener listener;

    public Label getInsertarCarroLabel() {
        return insertarCarroLabel;
    }

    public TextField getPlaca() {
        return placa;
    }
    public TextField getMarca() {
        return marca;
    }

    public ComboBox getUsuarioCombo() {
        return usuarioCombo;
    }

    public Button getInsertarCarroBtn() {
        return insertarCarroBtn;
    }

    public InsertarCarroController getController() {
        return controller;
    }

    public InsertarCarroView(){
        VBox container = new VBox();
        insertarCarroLabel = new Label("Insertar carro");
        placa = new TextField();
        marca = new TextField();
        usuarioCombo = new ComboBox();
        insertarCarroBtn = new Button("Insertar");

        container.getChildren().add(insertarCarroLabel);
        container.getChildren().add(placa);
        container.getChildren().add(marca);
        container.getChildren().add(usuarioCombo);
        container.getChildren().add(insertarCarroBtn);

        Scene scene = new Scene(container);
        this.setScene(scene);

        controller = new InsertarCarroController(this);
    }
    public void closeWindow(){
        if(listener != null) listener.onClose();
        this.close();
    }
    public interface OnCloseListener{
        void onClose();
    }

    public void setListener(OnCloseListener listener) {
        this.listener = listener;
    }
}
