 import java.util.Scanner;
    public class CalculadoraIterativo {


        public static void pausar(Scanner sc) {
            System.out.println("\nPressione ENTER para continuar...");
            sc.nextLine();
        }


        public static double[] resolverGaussSeidel(double[][] A, double[] b, int maxIteracoes, double tolerancia) {
            int n = b.length;
            double[] x = new double[n];
            double[] xAnterior = new double[n];

            for (int iter = 1; iter <= maxIteracoes; iter++) {

                System.arraycopy(x, 0, xAnterior, 0, n);

                for (int i = 0; i < n; i++) {
                    double soma = 0.0;
                    for (int j = 0; j < n; j++) {
                        if (j != i) {
                            soma += A[i][j] * x[j];
                        }
                    }
                    x[i] = (b[i] - soma) / A[i][i];
                }

                double maxErro = 0.0;
                for (int i = 0; i < n; i++) {
                    double erro = Math.abs(x[i] - xAnterior[i]);
                    if (erro > maxErro) {
                        maxErro = erro;
                    }
                }

                System.out.printf("Iteração %2d | x1: %.4f | x2: %.4f | x3: %.4f | x4: %.4f | Erro: %.6f\n",
                        iter, x[0], x[1], x[2], x[3], maxErro);

                if (maxErro < tolerancia) {
                    System.out.println("\nO método convergiu na iteração " + iter + "!");
                    return x;
                }
            }

            throw new ArithmeticException("O método não convergiu após " + maxIteracoes + " iterações.");
        }

        // Lê um número double do usuário, repetindo até receber um valor válido
        public static double lerDouble(Scanner sc, String mensagem) {
            while (true) {
                System.out.print(mensagem);
                try {
                    return Double.parseDouble(sc.nextLine().trim().replace(",", "."));
                } catch (NumberFormatException e) {
                    System.out.println("  ⚠ Valor inválido. Digite um número (ex: 4 ou -1.5).");
                }
            }
        }

        // Lê um número inteiro positivo do usuário
        public static int lerInt(Scanner sc, String mensagem) {
            while (true) {
                System.out.print(mensagem);
                try {
                    int val = Integer.parseInt(sc.nextLine().trim());
                    if (val > 0) return val;
                    System.out.println("  ⚠ Digite um valor maior que zero.");
                } catch (NumberFormatException e) {
                    System.out.println("  ⚠ Valor inválido. Digite um número inteiro.");
                }
            }
        }

        public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);

            System.out.println("╔══════════════════════════════════════════╗");
            System.out.println("║     CALCULADORA DE GAUSS-SEIDEL (4x4)   ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.println("""
                    Você vai montar o sistema linear respondendo
                    as perguntas abaixo. O computador resolve o
                    resto pelo método iterativo de Gauss-Seidel.
                    """);
            pausar(sc);

            // ── Leitura da matriz A ──────────────────────────────────────
            double[][] A = new double[4][4];
            double[] b   = new double[4];

            String[] varNomes = {"x1", "x2", "x3", "x4"};

            for (int i = 0; i < 4; i++) {
                System.out.println("─────────────────────────────────────────");
                System.out.println("  EQUAÇÃO " + (i + 1));
                System.out.println("  Formato: a*x1 + b*x2 + c*x3 + d*x4 = e");
                System.out.println("─────────────────────────────────────────");

                for (int j = 0; j < 4; j++) {
                    A[i][j] = lerDouble(sc,
                            "  Coeficiente de " + varNomes[j] + " na equação " + (i + 1) + ": ");
                }

                // Valida que a diagonal principal não é zero (evita divisão por zero)
                if (A[i][i] == 0) {
                    System.out.println("\n  ⚠ ATENÇÃO: O coeficiente da diagonal (a" + (i+1) + (i+1) + ") é zero!");
                    System.out.println("  Isso causa divisão por zero. Reordene suas equações.");
                    System.out.println("  Relançando a equação " + (i + 1) + "...\n");
                    i--; // Refaz essa equação
                    continue;
                }

                b[i] = lerDouble(sc, "  Termo independente (lado direito '='): ");
                System.out.println();
            }

            // ── Parâmetros do metodo
            System.out.println("─────────────────────────────────────────");
            System.out.println("  PARÂMETROS DO MÉTODO");
            System.out.println("─────────────────────────────────────────");
            int maxIter   = lerInt(sc,    "  Máximo de iterações (ex: 50): ");
            double tolStr = lerDouble(sc, "  Tolerância/erro desejado (ex: 0.001): ");
            System.out.println();

            // ── Exibe o sistema montado ──────────────────────────────────
            System.out.println("═══════════════════════════════════════════");
            System.out.println("  SISTEMA MONTADO:");
            System.out.println("═══════════════════════════════════════════");
            for (int i = 0; i < 4; i++) {
                StringBuilder eq = new StringBuilder("  ");
                for (int j = 0; j < 4; j++) {
                    if (j == 0) {
                        eq.append(String.format("%.4g·%s", A[i][j], varNomes[j]));
                    } else {
                        eq.append(String.format(" %+.4g·%s", A[i][j], varNomes[j]));
                    }
                }
                eq.append(String.format(" = %.4g", b[i]));
                System.out.println(eq);
            }
            System.out.println();
            pausar(sc);

            // ── Execução do metodo ───────────────────────────────────────
            System.out.println("Iniciando as iterações do Gauss-Seidel...\n");

            try {
                double[] respostas = resolverGaussSeidel(A, b, maxIter, tolStr);

                System.out.println("\n╔══════════════════════════════════════════╗");
                System.out.println("║            SOLUÇÕES ENCONTRADAS          ║");
                System.out.println("╚══════════════════════════════════════════╝");
                for (int i = 0; i < respostas.length; i++) {
                    System.out.printf("  x%d = %.6f\n", (i + 1), respostas[i]);
                }

            } catch (ArithmeticException e) {
                System.out.println("\n⚠ Erro: " + e.getMessage());
                System.out.println("Dica: verifique se a diagonal principal é dominante.");
            }

        }
    }

