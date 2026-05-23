import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║         MENU PRINCIPAL - A3              ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.println("1 - Parte 1: Sistemas Lineares (Exato ou Iterativo)");
            System.out.println("2 - Parte 2: Raízes (Método da Bisseção)");
            System.out.println("3 - Parte 3: Geometria (Cálculo Triângulo 3D)");
            System.out.println("4 - Parte 4: Cálculo Numérico (Derivação e Integração)");
            System.out.println("5 - Parte 5: Interpolação (Lagrange)");
            System.out.println("6 - Calculadora metodo exato ");
            System.out.println("7 - Calculadora metodo iterativo ");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            // Validação de entrada para evitar erros se o usuário digitar letras
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consome a quebra de linha
            } else {
                System.out.println("Entrada inválida. Digite um número correspondente ao menu.");
                scanner.nextLine(); // Limpa a entrada incorreta
                continue;
            }

            switch (opcao) {
                case 1:
                    menuSistemasLineares(scanner);
                    break;
                case 2:
                    System.out.println("\n>>> Executando Parte 2 - Método da Bisseção...");
                    MetodoBissecao.main(null);
                    break;
                case 3:
                    System.out.println("\n>>> Executando Parte 3 - Cálculo Triângulo 3D...");
                    CalculoTriangulo3D.main(null);
                    break;
                case 4:
                    System.out.println("\n>>> Executando Parte 4 - Derivação e Integração...");
                    DerivacaoIntegracao.main(null);
                    break;
                case 5:
                    System.out.println("\n>>> Executando Parte 5 - Interpolação de Lagrange...");
                    InterpolacaoLagrange.main(null);
                    break;
                case 6:
                    System.out.println("\n>>> Executando calculadora Exato...");
                    CalculadoraExato.main(null);
                    break;
                case 7:
                    System.out.println("\n>>> Executando calculadora Iterativa...");
                    CalculadoraIterativo.main(null);
                    break;
                case 0:
                    System.out.println("\nEncerrando o programa...");
                    break;
                default:
                    System.out.println("\nOpção inválida! Escolha um número de 0 a 7.");
            }
        }
    }

    // Submenu para a Parte 1
    private static void menuSistemasLineares(Scanner scanner) {
        int subOpcao = -1;

        while (subOpcao != 0) {
            System.out.println("\n--- Parte 1: Sistemas Lineares ---");
            System.out.println("1 - Método Exato (Eliminação de Gauss)");
            System.out.println("2 - Método Iterativo (Gauss-Seidel)");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha o método desejado: ");

            if (scanner.hasNextInt()) {
                subOpcao = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Entrada inválida. Digite um número.");
                scanner.nextLine();
                continue;
            }

            switch (subOpcao) {
                case 1:
                    System.out.println("\n>>> Executando Método Exato...");
                    MetodoExato.main(null);
                    break;
                case 2:
                    System.out.println("\n>>> Executando Método Iterativo...");
                    MetodoIterativo.main(null);
                    break;
                case 0:
                    System.out.println("\nVoltando ao menu principal...");
                    break;
                default:
                    System.out.println("\nOpção inválida! Tente novamente.");
            }
        }
    }
}