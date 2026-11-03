package Projeto;

public class Carro {

    private String placa;
    private String modelo;
    private String proprietario;
    private int horaEntrada;

    public Carro(String placa, String modelo, String proprietario, int horaEntrada) {
        this.placa = placa;
        this.modelo = modelo;
        this.proprietario = proprietario;
        this.horaEntrada = horaEntrada;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public String getProprietario() {
        return proprietario;
    }

    public int getHoraEntrada() {
        return horaEntrada;
    }
}