package Projeto;

import java.util.Scanner;

public class Main {
	
	public static void mostrarMenu() {

        System.out.println("\n===== ESTACIONAMENTO =====");
        System.out.println("1 - Registrar entrada de carro");
        System.out.println("2 - Registrar saida de carro");
        System.out.println("3 - Consultar carros estacionados");
        System.out.println("4 - Consultar vagas");
        System.out.println("5 - Mostrar tabela de precos");
        System.out.println("6 - Consultar arrecadacao");
        System.out.println("0 - Encerrar sistema");
    }

    public static void adicionarCarro(Scanner scanner, Estacionamento estacionamento) {

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

            System.out.println("Horario invalido! Digite entre 0 e 23.");

            System.out.print("Hora de entrada: ");
            hora = scanner.nextInt();
            scanner.nextLine();
        }

        if (estacionamento.adicionarCarro(placa, modelo, proprietario, hora)) {

            System.out.println("Carro cadastrado com sucesso!");

        } else {

            System.out.println("Nao foi possivel cadastrar o carro.");
        }
    }


    public static void removerCarro(Scanner scanner, Estacionamento estacionamento) {

    	System.out.print("Placa do carro: ");
    	String placa = scanner.nextLine();

    	System.out.print("Hora de saida (0 a 23): ");
    	int hora = scanner.nextInt();
    	scanner.nextLine();

    	while (hora < 0 || hora > 23) {
    		System.out.println("Horario invalido! Digite entre 0 e 23.");
    		System.out.print("Hora de saida: ");
    		hora = scanner.nextInt();
    		scanner.nextLine();
    }

    	boolean resultado = estacionamento.removerCarro(placa, hora);

    	if (resultado) {
    		System.out.println("Saida registrada com sucesso!");
    	}
    }


    public static void consultarVagas(Estacionamento estacionamento) {

        System.out.println("Carros estacionados: " + estacionamento.getQtdCarros());

        System.out.println("Vagas disponiveis: " + estacionamento.getVagasDisponiveis());
    }

    public static void consultarArrecadacao(Estacionamento estacionamento) {

        System.out.println("Arrecadacao total: R$ " + estacionamento.getArrecadacao());
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Estacionamento estacionamento = new Estacionamento(20);

        estacionamento.mostrarTabelaPrecos();

        System.out.print("\nDeseja estacionar? (S/N): ");
        String resposta = scanner.nextLine();

        int opcao = -1;

        if (resposta.equalsIgnoreCase("S")) {

            while (opcao != 0) {

                mostrarMenu();

                System.out.print("Opcao: ");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {

                    case 1:
                        adicionarCarro(scanner, estacionamento);
                        break;

                    case 2:
                        removerCarro(scanner, estacionamento);
                        break;

                    case 3:
                        estacionamento.listarCarros();
                        break;

                    case 4:
                        consultarVagas(estacionamento);
                        break;

                    case 5:
                        estacionamento.mostrarTabelaPrecos();
                        break;

                    case 6:
                        consultarArrecadacao(estacionamento);
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
}
