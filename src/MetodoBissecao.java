public class MetodoBissecao {


    // Define a função f(x) = x³ - 6x² + 10x - 4

    public static double f(double x) {
        return Math.pow(x, 3) - 6 * Math.pow(x, 2) + 10 * x - 4;
    }

    public static void main(String[] args) {
        // Dados iniciais do problema
        double a = 0.5;
        double b = 1.5;
        double tolerancia = 1e-6; // Representação de 10^-6

        double m = 0.0;
        int iteracao = 1;

        // Validação inicial baseada no Teorema do Valor Intermediário
        if (f(a) * f(b) >= 0) {
            System.out.println("\nO método da bisseção não garante uma raiz neste intervalo. Os sinais de f(a) e f(b) devem ser opostos.");
            return;
        }

        // Cabeçalho da tabela
        System.out.printf("\n%-10s %-12s %-12s %-12s %-13s %-13s %-13s %-12s\n",
                "Iteração", "a", "m", "b", "f(a)", "f(m)", "f(b)", "Erro |b-a|");
        System.out.println("-".repeat(105));

        // O loop continua enquanto o tamanho do intervalo for maior ou igual à tolerância
        while ((b - a) >= tolerancia) {
            // 1. Calcula o ponto médio
            m = (a + b) / 2.0;

            // Calcula os valores da função para a, b e m
            double fa = f(a);
            double fb = f(b);
            double fm = f(m);
            double erro = b - a;

            // Imprime os dados com 7 casas decimais
            System.out.printf("%-10d %-12.7f %-12.7f %-12.7f %-13.7f %-13.7f %-13.7f %-12.7f\n",
                    iteracao, a, m, b, fa, fm, fb, erro);

            // 2. Verifica se achou a raiz exata acidentalmente
            if (fm == 0.0) {
                break;
            }

            // 3. Redefine os limites do intervalo
            if (fa * fm < 0) {
                b = m; // A raiz está na primeira metade [a, m]
            } else {
                a = m; // A raiz está na segunda metade [m, b]
            }

            iteracao++;
        }

        // Resultado final
        System.out.println("-".repeat(105));
        System.out.printf("Raiz aproximada encontrada: %.7f\n", m);
        System.out.println("Total de iterações realizadas: " + (iteracao - 1));
    }
}