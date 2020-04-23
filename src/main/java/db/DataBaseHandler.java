package db;

import model.Carro;
import model.Usuario;
import model.Usuario_Carro;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseHandler {
    private static DataBaseHandler instance;
    public static DataBaseHandler getInstance(){
        if(instance == null){
            instance = new DataBaseHandler();
        }
        return instance;
    }

    private Connection connection;
    private Statement statement;
    private DataBaseHandler(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/parkingicesi2","root","");
            statement = connection.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void insertarUsuario(Usuario usuario){
        try{
            String sql = "INSERT INTO usuarios(id,nombre,apellido) VALUES ('%ID%','%NOMBRE%','%APELLIDO%')";
            sql = sql.replace("%ID%",usuario.getId()).replace("%NOMBRE%",usuario.getNombre()).replace("%APELLIDO%",usuario.getApellido());
            statement.execute(sql);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public ArrayList<Usuario> getAllUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            ResultSet resultados = statement.executeQuery("SELECT*FROM usuarios");
            while(resultados.next()){
                String id = resultados.getString(1);
                String nombre = resultados.getString(2);
                String apellido = resultados.getString(3);
                Usuario usuario = new Usuario(id,nombre,apellido);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
    public ArrayList<Carro> getAllCarros(){
        ArrayList<Carro> carros = new ArrayList<Carro>();
        try {
            ResultSet resultados = statement.executeQuery("SELECT*FROM carros");
            while(resultados.next()){
                String id = resultados.getString(1);
                String placa = resultados.getString(2);
                String marca = resultados.getString(3);
                Carro carro = new Carro(id,placa,marca);
                carros.add(carro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carros;
    }
    public void eliminarUsuario(String iduser){
        try{
            String sql = "DELETE FROM usuarios WHERE id = '%ID%'";
            sql = sql.replace("%ID%",iduser);
            statement.execute(sql);
            eliminarUsuarioPivote(iduser);
            System.out.println("Usuario eliminado");
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public void eliminarUsuarioPivote(String iduser){
        try{
            String sql = "DELETE FROM usuarios_carros WHERE idUsuario = '%ID%'";
            sql = sql.replace("%ID%",iduser);
            statement.execute(sql);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public void insertarCarro(Carro carro){
        try{
            String sql = "INSERT INTO carros(id, placa, marca) VALUES ('%ID%','%PLACA%','%MARCA%')";
            sql = sql.replace("%ID%",carro.getId()).replace("%PLACA%",carro.getPlaca()).replace("%MARCA%",carro.getMarca());
            statement.execute(sql);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public void pivote(Carro carro, Usuario usuario){
        try{
            String sql = "INSERT INTO usuarios_carros(idUsuario, idCarro) VALUES ('%IDUSUARIO%','%IDCARRO%')";
            sql = sql.replace("%IDUSUARIO%",usuario.getId()).replace("%IDCARRO%",carro.getId());
            statement.execute(sql);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public ArrayList<Usuario_Carro> getCarrosFromUsuarioID(String id){
        ArrayList<Usuario_Carro> ucarros = new ArrayList<Usuario_Carro>();
        String sql = "SELECT*FROM usuarios_carros WHERE idUsuario = '%ID%'";
        sql = sql.replace("%ID%",id);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String idCar = resultSet.getString(3);
                String idUser = resultSet.getString(2);
                Usuario_Carro ucarro = new Usuario_Carro(idUser,idCar);
                ucarros.add(ucarro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ucarros;
    }
    public ArrayList<Usuario_Carro> getUsuariosFromCarroID(String id){
        ArrayList<Usuario_Carro> ucarros = new ArrayList<Usuario_Carro>();
        String sql = "SELECT*FROM usuarios_carros WHERE idCarro = '%ID%'";
        sql = sql.replace("%ID%",id);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String idCar = resultSet.getString(3);
                String idUser = resultSet.getString(2);
                Usuario_Carro ucarro = new Usuario_Carro(idUser,idCar);
                ucarros.add(ucarro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ucarros;
    }
}
