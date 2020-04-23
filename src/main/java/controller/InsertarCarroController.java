package controller;

import db.DataBaseHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Carro;
import model.Usuario;
import view.InsertarCarroView;

import java.util.ArrayList;
import java.util.UUID;

public class InsertarCarroController implements EventHandler<ActionEvent> {
    private InsertarCarroView view;
    private DataBaseHandler database;
    public InsertarCarroController(InsertarCarroView view){
        this.view = view;
        view.getInsertarCarroBtn().setOnAction(this);
        database = DataBaseHandler.getInstance();
        cargarUsuarios();
    }
    public void cargarUsuarios(){
        ArrayList<Usuario> usuarios = database.getAllUsuarios();
        view.getUsuarioCombo().getItems().addAll(usuarios);
    }

    public void handle(ActionEvent event) {
        if(event.getSource().equals(view.getInsertarCarroBtn())){
            if(view.getUsuarioCombo().getSelectionModel().getSelectedItem() != null){
                Usuario usuario = (Usuario) view.getUsuarioCombo().getSelectionModel().getSelectedItem();
                Carro carro = new Carro(UUID.randomUUID().toString(), view.getPlaca().getText(),view.getMarca().getText());
                database.insertarCarro(carro);
                database.pivote(carro, usuario);
            }else{
                Carro carro = new Carro(UUID.randomUUID().toString(), view.getPlaca().getText(),view.getMarca().getText());
                database.insertarCarro(carro);
            }
            view.closeWindow();
        }
    }
}
