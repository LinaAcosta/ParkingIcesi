package model;

public class Usuario_Carro {
    private String idUsuario;
    private String idCarro;

    public Usuario_Carro() {
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(String idCarro) {
        this.idCarro = idCarro;
    }

    public Usuario_Carro(String idUsuario, String idCarro) {
        this.idUsuario = idUsuario;
        this.idCarro = idCarro;
    }
}
