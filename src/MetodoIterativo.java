import java.util.Scanner;
public class MetodoIterativo {


        // Metodo para pausar e esperar o usuário apertar ENTER
        public static void pausar(Scanner sc) {
            System.out.println("\nPressione ENTER para continuar...");
            sc.nextLine();
        }

         //
         // Resolve o sistema linear pelo metodo iterativo de Gauss-Seidel.

         //  A Matriz de coeficientes
         //  b Vetor de termos independentes
         //  maxIteracoes Limite de vezes que o laço vai rodar
         //  tolerancia O quão pequeno deve ser o erro para parar
         //  Vetor com as soluções
         //
        public static double[] resolverGaussSeidel(double[][] A, double[] b, int maxIteracoes, double tolerancia) {
            int n = b.length;
            double[] x = new double[n]; // O Java já inicia esse vetor com zeros (nosso "chute" inicial)
            double[] xAnterior = new double[n]; // Para guardar os valores da rodada passada e comparar o erro

            // Repete as contas até o limite máximo de vezes estipulado
            for (int iter = 1; iter <= maxIteracoes; iter++) {

                // Fazemos uma cópia do 'x' atual para calcular a diferença (erro) depois
                for (int i = 0; i < n; i++) {
                    xAnterior[i] = x[i];
                }

                // Calculando o novo valor de cada variável (x1, x2, x3, x4)
                for (int i = 0; i < n; i++) {
                    double soma = 0.0;

                    // Multiplica as variáveis pelos coeficientes, exceto o elemento da diagonal principal
                    for (int j = 0; j < n; j++) {
                        if (j != i) {
                            soma += A[i][j] * x[j]; // ele usa o x[j] mais atualizado que tiver!
                        }
                    }

                    // Isola a variável atual dividindo pelo coeficiente da diagonal
                    x[i] = (b[i] - soma) / A[i][i];
                }

                // Calculando o erro (a maior diferença entre o 'x' novo e o 'x' da rodada anterior)
                double maxErro = 0.0;
                for (int i = 0; i < n; i++) {
                    double erro = Math.abs(x[i] - xAnterior[i]);
                    if (erro > maxErro) {
                        maxErro = erro;
                    }
                }

                // Mostrando o que ele calculou nesta iteração
                System.out.printf("Iteração %2d | x1: %.4f | x2: %.4f | x3: %.4f | x4: %.4f | Erro: %.6f\n",
                        iter, x[0], x[1], x[2], x[3], maxErro);

                // Condição de parada: se o erro for menor que a tolerância, achamos a resposta!
                if (maxErro < tolerancia) {
                    System.out.println("\nO método convergiu na iteração " + iter + "!");
                    return x;
                }
            }

            // Se o laço terminar e não atingir a tolerância
            throw new ArithmeticException("O método não convergiu após " + maxIteracoes + " iterações.");
        }

        public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);

            // Seus dados
            double[][] A = {
                    { 4, -1, -1,  0},
                    {-1,  4,  0, -1},
                    {-1,  0,  4, -1},
                    { 0, -1, -1,  4}
            };

            double[] b = {10, 12, 8, 15};

            // PASSO 1
            System.out.println("\n=========================================================");

            System.out.println("Este é o sistema a ser resolvido:");
            System.out.println(" 4x1 -  x2 -  x3       = 10");
            System.out.println("- x1 + 4x2       -  x4 = 12");
            System.out.println("- x1       + 4x3 -  x4 = 8");
            System.out.println("     -  x2 -  x3 + 4x4 = 15");
            System.out.println("=========================================================");

            pausar(sc);

            // PASSO 2
            System.out.println("No método de Gauss-Seidel, o computador isola cada variável.");
            System.out.println("A primeira equação (4x1 - x2 - x3 = 10) vira:");
            System.out.println("x1 = (10 + x2 + x3) / 4");
            System.out.println("E ele chuta zero para tudo e começa a calcular várias vezes seguidas.");

            pausar(sc);

            // PASSO 3

            System.out.println("Iniciando as iterações do Gauss-Seidel...\n");

            try {
                // Chamamos a calculadora passando: matriz A, vetor b, máximo de 50 repetições, e tolerancia de 0.001
                double[] respostas = resolverGaussSeidel(A, b, 50, 0.001);
                System.out.println("\n=========================================================");

                System.out.println("As soluções finais aproximadas são:");
                for (int i = 0; i < respostas.length; i++) {
                    System.out.printf("x%d = %.4f\n", (i + 1), respostas[i]);
                }
                System.out.println("=========================================================");

            } catch (ArithmeticException e) {
                System.out.println("Erro: " + e.getMessage());
            }


        }
    }

