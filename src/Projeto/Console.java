package Projeto;

import java.util.Scanner;

public class Console {

    private Scanner scanner;
    private Estacionamento estacionamento;

    public Console() {
        this.scanner = new Scanner(System.in);
        this.estacionamento = new Estacionamento(20);
    }

    public void iniciar() {

        estacionamento.mostrarTabelaPrecos();

        System.out.print("\nDeseja estacionar? (S/N): ");
        String resposta = scanner.nextLine();

        if (resposta.equalsIgnoreCase("S")) {

            int opcao = -1;

            while (opcao != 0) {

                mostrarMenu();

                System.out.print("Opcao: ");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {

                    case 1:
                        adicionarCarro();
                        break;

                    case 2:
                        removerCarro();
                        break;

                    case 3:
                        estacionamento.listarCarros();
                        break;

                    case 4:
                        consultarVagas();
                        break;

                    case 5:
                        estacionamento.mostrarTabelaPrecos();
                        break;

                    case 6:
                        consultarArrecadacao();
                        break;

                    case 0:
                        System.out.println("Sistema encerrado!");
                        break;

                    default:
                        System.out.println("Opcao invalida.");
                }
            }

        } else {
            System.out.println("Obrigado pela visita!");
        }

        scanner.close();
    }

    private void mostrarMenu() {

        System.out.println("\n===== ESTACIONAMENTO =====");
        System.out.println("1 - Registrar entrada de carro");
        System.out.println("2 - Registrar saida de carro");
        System.out.println("3 - Consultar carros estacionados");
        System.out.println("4 - Consultar vagas");
        System.out.println("5 - Mostrar tabela de precos");
        System.out.println("6 - Consultar arrecadacao");
        System.out.println("0 - Encerrar sistema");
    }

    private void adicionarCarro() {

        System.out.print("Placa: ");
        String placa = scanner.nextLine();

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Proprietario: ");
        String proprietario = scanner.nextLine();

        System.out.print("Hora de entrada (0 a 23): ");
        int hora = scanner.nextInt();
        scanner.nextLine();

        while (hora < 0 || hora > 23) {

            System.out.println("Horario invalido!");

            System.out.print("Hora de entrada: ");
            hora = scanner.nextInt();
            scanner.nextLine();
        }

        boolean sucesso = estacionamento.adicionarCarro(
                placa, modelo, proprietario, hora);

        if (sucesso) {
            System.out.println("Carro cadastrado com sucesso!");
        } else {
            System.out.println("Nao foi possivel cadastrar o carro.");
            System.out.println("(Sem vagas ou placa ja cadastrada)");
        }
    }

    private void removerCarro() {

        System.out.print("Placa do carro: ");
        String placa = scanner.nextLine();

        System.out.print("Hora de saida (0 a 23): ");
        int hora = scanner.nextInt();
        scanner.nextLine();

        while (hora < 0 || hora > 23) {

            System.out.println("Horario invalido!");

            System.out.print("Hora de saida: ");
            hora = scanner.nextInt();
            scanner.nextLine();
        }

        double valorPago = estacionamento.removerCarro(placa, hora);

        if (valorPago == -1.0) {

            System.out.println("Nao foi possivel registrar a saida.");

        } else {

            System.out.println("Saida registrada com sucesso!");

            System.out.printf("Valor a pagar: R$ %.2f%n", valorPago);
        }
    }

    private void consultarVagas() {

        System.out.println("Carros estacionados: " + estacionamento.getQtdCarros());

        System.out.println("Vagas disponiveis: " + estacionamento.getVagasDisponiveis());
    }

    private void consultarArrecadacao() {

        System.out.printf("Arrecadacao total: R$ %.2f%n",
                estacionamento.getArrecadacao());
    }
}