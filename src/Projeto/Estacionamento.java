
package Projeto;

public class Estacionamento {

    private Carro[] carros;
    private int capacidade;
    private double arrecadacao;

    public Estacionamento(int capacidade) {
        this.capacidade = capacidade;
        carros = new Carro[0];
        arrecadacao = 0;
    }

    private void expandirVetor() {

        Carro[] novoVetor = new Carro[carros.length + 1];

        for (int i = 0; i < carros.length; i++) {
            novoVetor[i] = carros[i];
        }

        carros = novoVetor;
    }

    public boolean adicionarCarro(String placa, String modelo,
            String proprietario, int horaEntrada) {

        if (carros.length >= capacidade) {
            return false;
        }

        if (horaEntrada < 0 || horaEntrada > 23) {
            System.out.println("Hora de entrada invalida!");
            return false;
        }

        for (int i = 0; i < carros.length; i++) {

            if (carros[i].getPlaca().equalsIgnoreCase(placa)) {
                return false;
            }
        }

        expandirVetor();

        Carro carro = new Carro(placa, modelo, proprietario, horaEntrada);

        carros[carros.length - 1] = carro;

        return true;
    }

    public double removerCarro(String placa, int horaSaida) {

        int posicao = -1;

        for (int i = 0; i < carros.length; i++) {

            if (carros[i].getPlaca().equalsIgnoreCase(placa)) {
                posicao = i;
                break;
            }
        }

        if (posicao == -1) {
            return -1.0;
        }

        if (horaSaida < 0 || horaSaida > 23) {
            System.out.println("Hora de saida invalida!");
            return -1.0;
        }

        int horas = horaSaida - carros[posicao].getHoraEntrada();

        if (horas <= 0) {
            System.out.println("Nao e possivel!");
            System.out.println("Informe uma hora maior que a hora de entrada.");
            return -1.0;
        }

        double valor = calcularValor(horas);

        arrecadacao = arrecadacao + valor;

        System.out.println("Tempo estacionado: " + horas + " hora(s)");

        Carro[] novoVetor = new Carro[carros.length - 1];

        for (int i = 0; i < novoVetor.length; i++) {

            if (i < posicao) {
                novoVetor[i] = carros[i];
            } else {
                novoVetor[i] = carros[i + 1];
            }
        }

        carros = novoVetor;

        return valor;
    }

    private double calcularValor(int horas) {

        if (horas <= 1) {
            return 5.00;
        } else {
            return 5.00 + (horas - 1) * 3.00;
        }
    }

    public void listarCarros() {

        if (carros.length == 0) {
            System.out.println("Estacionamento vazio.");
            return;
        }

        for (int i = 0; i < carros.length; i++) {

            System.out.println("\nPlaca: " + carros[i].getPlaca());
            System.out.println("Modelo: " + carros[i].getModelo());
            System.out.println("Proprietario: " + carros[i].getProprietario());
            System.out.println("Hora de entrada: " + carros[i].getHoraEntrada());

            System.out.println("-------------------------");
        }
    }

    public void mostrarTabelaPrecos() {

        System.out.println("\n===== TABELA DE PRECOS =====");
        System.out.println("1 hora: R$ 5,00");
        System.out.println("2 horas: R$ 8,00");
        System.out.println("3 horas: R$ 11,00");
        System.out.println("4 horas: R$ 14,00");
        System.out.println("Cada hora adicional: R$ 3,00");
    }

    public int getQtdCarros() {
        return carros.length;
    }

    public int getVagasDisponiveis() {
        return capacidade - carros.length;
    }

    public double getArrecadacao() {
        return arrecadacao;
    }

    public Carro[] getCarros() {
        return carros;
    }
}
