import java.util.Scanner;

public class InterpolacaoLagrange {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Dados da tabela
        double[] x = {0.0, 0.5, 1.0, 1.5};
        double[] I = {255, 200, 120, 50};
        double xAlvo = 0.8;

        System.out.println("=== INTERPOLAÇÃO POLINOMIAL DE LAGRANGE ===");
        System.out.printf("Objetivo: Calcular a intensidade I para x = %.1f%n", xAlvo);
        System.out.println("Pontos conhecidos:");
        for (int i = 0; i < x.length; i++) {
            System.out.printf("  P%d: (x = %.1f, I = %.1f)%n", i, x[i], I[i]);
        }

        pausar(scanner);

        double intensidadeFinal = 0.0;

        // Loop para calcular cada termo L_i(x)
        for (int i = 0; i < x.length; i++) {
            System.out.printf("--- Calculando o termo L_%d(x) para o ponto P%d ---%n", i, i);

            double numerador = 1.0;
            double denominador = 1.0;
            StringBuilder expressaoNumerador = new StringBuilder();
            StringBuilder expressaoDenominador = new StringBuilder();

            for (int j = 0; j < x.length; j++) {
                if (i != j) {
                    numerador *= (xAlvo - x[j]);
                    denominador *= (x[i] - x[j]);

                    expressaoNumerador.append(String.format("(%.1f - %.1f)", xAlvo, x[j]));
                    expressaoDenominador.append(String.format("(%.1f - %.1f)", x[i], x[j]));

                    if (j < x.length - 1 && !(i == x.length - 1 && j == x.length - 2)) {
                        expressaoNumerador.append(" * ");
                        expressaoDenominador.append(" * ");
                    }
                }
            }

            double Li = numerador / denominador;
            double termoPolinomio = I[i] * Li;
            intensidadeFinal += termoPolinomio;

            // Remove multiplicadores extras visualmente feios no final das strings se houver
            String expNum = expressaoNumerador.toString().replaceAll(" \\* $", "");
            String expDen = expressaoDenominador.toString().replaceAll(" \\* $", "");

            System.out.printf("L_%d(%.1f) = [%s] / [%s]%n", i, xAlvo, expNum, expDen);
            System.out.printf("L_%d(%.1f) = %.4f / %.4f = %.4f%n", i, xAlvo, numerador, denominador, Li);
            System.out.printf("Contribuição do termo %d (I_%d * L_%d) = %.1f * %.4f = %.4f%n", i, i, i, I[i], Li, termoPolinomio);

            pausar(scanner);
        }

        System.out.println("=== RESULTADO FINAL ===");
        System.out.printf("A intensidade interpolada no sub-pixel x = %.1f é: %.4f%n", xAlvo, intensidadeFinal);


    }

    // Método auxiliar para pausar a execução até o usuário apertar Enter
    private static void pausar(Scanner scanner) {
        System.out.println("\n[Pressione ENTER para continuar para o próximo passo...]");
        scanner.nextLine();
    }
}
