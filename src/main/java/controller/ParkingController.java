package controller;

import db.DataBaseHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Carro;
import model.Usuario;
import model.Usuario_Carro;
import view.InsertarCarroView;
import view.ParckingView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

public class ParkingController implements EventHandler<ActionEvent>, InsertarCarroView.OnCloseListener {
    private ParckingView view;
    private DataBaseHandler dataBaseHandler;
    public ParkingController(ParckingView view){
        this.view = view;
        dataBaseHandler = DataBaseHandler.getInstance();
        view.getInsertarUsuarioBtn().setOnAction(this);
        view.getInsertarCarroBtn().setOnAction(this);
        cargarUsuarios();
        cargarCarros();
        cargarUsuariosCombo();
    }
    public void cargarUsuarios(){
        ArrayList<Usuario> usuarios = dataBaseHandler.getAllUsuarios();
        ArrayList<Carro> carros = dataBaseHandler.getAllCarros();
        view.getResultadosTA().setText("");
        for(int i = 0; i < usuarios.size(); i++){
            Usuario usuario = usuarios.get(i);
            ArrayList<Usuario_Carro> ucarros = dataBaseHandler.getCarrosFromUsuarioID(usuario.getId());
            for(int j = 0; j < ucarros.size(); j++){
                view.getResultadosTA().appendText(usuario.getNombre() + "\t" + usuario.getApellido() + "\t" + usuario.getId() + "\n" + "Carros: " + "\n" + ucarros.get(j).getIdCarro() + "\n");
            }
        }
        for(int k = 0; k < carros.size(); k++){
            Carro carro = carros.get(k);
            ArrayList<Usuario_Carro> uucarros = dataBaseHandler.getUsuariosFromCarroID(carro.getId());
            for(int l = 0; l < uucarros.size(); l++){
                view.getResultadosTA().appendText(carro.getMarca() + "\t" + carro.getPlaca() + "\t" + carro.getId() + "\n" + "Usuarios: " + "\n" + uucarros.get(l).getIdUsuario() + "\n");
            }
        }
    }

    public void handle(ActionEvent event) {
        if(event.getSource().equals(view.getInsertarUsuarioBtn())){
            String nombre = view.getNombreTF().getText();
            String apellido = view.getApellidoTF().getText();
            if(view.getCarroCombo().getSelectionModel().getSelectedItem() != null) {
                Usuario usuario = new Usuario(UUID.randomUUID().toString(), nombre, apellido);
                Carro carro = (Carro) view.getCarroCombo().getSelectionModel().getSelectedItem();
                dataBaseHandler.insertarUsuario(usuario);
                dataBaseHandler.pivote(carro, usuario);
                cargarUsuarios();
            }else{
                Usuario usuario = new Usuario(UUID.randomUUID().toString(), nombre, apellido);
                dataBaseHandler.insertarUsuario(usuario);
            }
        }else if(event.getSource().equals(view.getEliminar())){
            Usuario usuario = (Usuario) view.getUsuarioCombo().getSelectionModel().getSelectedItem();
            dataBaseHandler.eliminarUsuario(usuario.getId());
            cargarUsuarios();
        }else if(event.getSource().equals(view.getInsertarCarroBtn())) {
            InsertarCarroView carroView = new InsertarCarroView();
            carroView.setListener(this);
            carroView.show();
            cargarUsuarios();
        }
    }

    public void onClose() {
        System.out.println("Ventana de inserción de carro cerrada");
        cargarUsuarios();
    }
    public void cargarCarros(){
        ArrayList<Carro> carros = dataBaseHandler.getAllCarros();
        view.getCarroCombo().getItems().addAll(carros);
    }
    public void cargarUsuariosCombo(){
        ArrayList<Usuario> usuarios = dataBaseHandler.getAllUsuarios();
        view.getUsuarioCombo().getItems().addAll(usuarios);
    }
}
