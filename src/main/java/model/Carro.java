package model;

public class Carro {
    private String id;
    private String placa;
    private String marca;

    public String getId() {
        return id;
    }

    public Carro() {
    }

    public Carro(String id, String placa, String marca) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    @Override
    public String toString() {
        return this.placa;
    }
}
