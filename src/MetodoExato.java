import java.util.Scanner;

public class MetodoExato
{

    public static double[] resolver(double[][] A, double[] b) {
        int n = b.length;

        // Prepara matrizes vazias
        double[][] matriz = new double[n][n];
        double[] vetor = new double[n];

        // Copia os dados
        for (int i = 0; i < n; i++) {
            matriz[i] = A[i].clone();
            vetor[i] = b[i];
        }

        // FASE 1: Pivotamento e Eliminação
        for (int p = 0; p < n; p++) {
            int max = p;
            for (int i = p + 1; i < n; i++) {
                if (Math.abs(matriz[i][p]) > Math.abs(matriz[max][p])) {
                    max = i;
                }
            }

            double[] temp = matriz[p];
            matriz[p] = matriz[max];
            matriz[max] = temp;

            double t = vetor[p];
            vetor[p] = vetor[max];
            vetor[max] = t;

            if (Math.abs(matriz[p][p]) <= EPSILON) {
                throw new ArithmeticException("Sistema sem solução única.");
            }

            for (int i = p + 1; i < n; i++) {
                double fator = matriz[i][p] / matriz[p][p];
                vetor[i] -= fator * vetor[p];
                for (int j = p; j < n; j++) {
                    matriz[i][j] -= fator * matriz[p][j];
                }
            }
        }

        // FASE 2: Substituição Reversa
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double soma = 0.0;
            for (int j = i + 1; j < n; j++) {
                soma += matriz[i][j] * x[j];
            }
            x[i] = (vetor[i] - soma) / matriz[i][i];
        }

        return x;
    }
    private static final double EPSILON = 1e-10;



    // NOVO METODO: Criamos um "pausador" para não repetir código
    public static void pausar(Scanner sc) {
        System.out.println("\nPressione ENTER para continuar...");
        sc.nextLine(); // O programa congela aqui até o usuário apertar Enter
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


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
        pausar(sc); // Chamamos a nossa função de pausa!

        // PASSO 2
        System.out.println("Transformando em matriz aumentada [A | b]:");
        System.out.println(" 4 -1 -1  0 | 10");
        System.out.println("-1  4  0 -1 | 12");
        System.out.println("-1  0  4 -1 | 8");
        System.out.println(" 0 -1 -1  4 | 15");

        pausar(sc); // Chamamos a pausa de novo!

        // PASSO 3
        System.out.println("Calculando o sistema por Eliminação de Gauss...\n");

        try {
            double[] respostas = resolver(A, b);
            System.out.println("=========================================================");

            System.out.println("As soluções são:");
            for (int i = 0; i < respostas.length; i++) {
                System.out.printf("x%d = %.4f\n", (i + 1), respostas[i]);
            }
            System.out.println("=========================================================");

        } catch (ArithmeticException e) {
            System.out.println("Deu erro matemático: " + e.getMessage());
        }


    }
}