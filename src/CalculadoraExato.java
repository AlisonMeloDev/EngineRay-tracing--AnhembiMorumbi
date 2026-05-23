    import java.util.Scanner;
    public class CalculadoraExato {

        public static void pausar(Scanner sc) {
            System.out.println("\nPressione ENTER para continuar...");
            sc.nextLine();
        }

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

        // ─────────────────────────────────────────────────────────────
        // ELIMINAÇÃO DE GAUSS COM PIVOTAMENTO PARCIAL
        // Retorna o vetor solução x[]
        // ─────────────────────────────────────────────────────────────
        public static double[] resolverGauss(double[][] A, double[] b, int n) {

            // Monta a matriz aumentada [A | b]
            double[][] M = new double[n][n + 1];
            for (int i = 0; i < n; i++) {
                System.arraycopy(A[i], 0, M[i], 0, n);
                M[i][n] = b[i];
            }

            System.out.println("\n--- Matriz Aumentada Inicial [A|b] ---");
            imprimirMatriz(M, n);

            // ── FASE 1: Escalonamento (eliminação para frente) ──────────
            for (int col = 0; col < n; col++) {

                // Pivotamento parcial: troca a linha atual pela de maior valor absoluto na coluna
                int maxLinha = col;
                for (int linha = col + 1; linha < n; linha++) {
                    if (Math.abs(M[linha][col]) > Math.abs(M[maxLinha][col])) {
                        maxLinha = linha;
                    }
                }
                double[] temp = M[col];
                M[col] = M[maxLinha];
                M[maxLinha] = temp;

                if (maxLinha != col) {
                    System.out.printf("\n  -> Troca: linha %d <-> linha %d (pivotamento)\n", col + 1, maxLinha + 1);
                }

                // Verifica se o pivô é zero (sistema sem solução única)
                if (Math.abs(M[col][col]) < 1e-12) {
                    throw new ArithmeticException("Sistema não tem solução única (pivô zero na coluna " + (col + 1) + ").");
                }

                double pivo = M[col][col];
                System.out.printf("\n  Pivô da coluna %d: %.4f\n", col + 1, pivo);

                // Zera os elementos abaixo do pivô
                for (int linha = col + 1; linha < n; linha++) {
                    double fator = M[linha][col] / pivo;
                    System.out.printf("  L%d <- L%d - (%.4f) * L%d\n", linha + 1, linha + 1, fator, col + 1);
                    for (int j = col; j <= n; j++) {
                        M[linha][j] -= fator * M[col][j];
                    }
                }

                System.out.println("\n  Matriz após eliminar coluna " + (col + 1) + ":");
                imprimirMatriz(M, n);
                pausar(new Scanner(System.in)); // pausa a cada passo
            }

            // ── FASE 2: Substituição Regressiva ─────────────────────────
            System.out.println("--- Substituição Regressiva ---");
            double[] x = new double[n];

            for (int i = n - 1; i >= 0; i--) {
                double soma = 0.0;
                for (int j = i + 1; j < n; j++) {
                    soma += M[i][j] * x[j];
                }
                x[i] = (M[i][n] - soma) / M[i][i];
                System.out.printf("  x%d = (%.4f - %.4f) / %.4f = %.6f\n",
                        i + 1, M[i][n], soma, M[i][i], x[i]);
            }

            return x;
        }

        public static void imprimirMatriz(double[][] M, int n) {
            for (int i = 0; i < n; i++) {
                System.out.print("  |");
                for (int j = 0; j <= n; j++) {
                    if (j == n) System.out.print(" |");
                    System.out.printf(" %8.4f", M[i][j]);
                }
                System.out.println(" |");
            }
        }

        public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);

            System.out.println("╔══════════════════════════════════════════╗");
            System.out.println("║   CALCULADORA DE ELIMINAÇÃO DE GAUSS    ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.println("Método EXATO — encontra a solução precisa do sistema.\n");
            pausar(sc);

            // ── Leitura do tamanho do sistema ────────────────────────────
            int n = lerInt(sc, "Quantas variáveis/equações o sistema tem? (ex: 4): ");

            double[][] A = new double[n][n];
            double[] b   = new double[n];
            String[] varNomes = new String[n];
            for (int i = 0; i < n; i++) varNomes[i] = "x" + (i + 1);

            // ── Leitura das equações ─────────────────────────────────────
            for (int i = 0; i < n; i++) {
                System.out.println("\n─────────────────────────────────────────");
                System.out.println("  EQUAÇÃO " + (i + 1));
                System.out.println("─────────────────────────────────────────");

                for (int j = 0; j < n; j++) {
                    A[i][j] = lerDouble(sc, "  Coeficiente de " + varNomes[j] + ": ");
                }
                b[i] = lerDouble(sc, "  Termo independente (=): ");
            }

            // ── Exibe o sistema montado ──────────────────────────────────
            System.out.println("\n═══════════════════════════════════════════");
            System.out.println("  SISTEMA MONTADO:");
            System.out.println("═══════════════════════════════════════════");
            for (int i = 0; i < n; i++) {
                StringBuilder eq = new StringBuilder("  ");
                for (int j = 0; j < n; j++) {
                    if (j == 0) eq.append(String.format("%.4g·%s", A[i][j], varNomes[j]));
                    else        eq.append(String.format(" %+.4g·%s", A[i][j], varNomes[j]));
                }
                eq.append(String.format(" = %.4g", b[i]));
                System.out.println(eq);
            }
            System.out.println();
            pausar(sc);

            // ── Resolução ────────────────────────────────────────────────
            System.out.println("Iniciando a Eliminação de Gauss...\n");

            try {
                double[] respostas = resolverGauss(A, b, n);

                System.out.println("\n╔══════════════════════════════════════════╗");
                System.out.println("║          SOLUÇÃO EXATA ENCONTRADA        ║");
                System.out.println("╚══════════════════════════════════════════╝");
                for (int i = 0; i < respostas.length; i++) {
                    System.out.printf("  x%d = %.6f\n", (i + 1), respostas[i]);
                }

            } catch (ArithmeticException e) {
                System.out.println("\n⚠ Erro: " + e.getMessage());
            }

        }
    }

