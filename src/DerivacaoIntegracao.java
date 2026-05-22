import java.util.function.Function;

public class DerivacaoIntegracao {

    // 1. Função I(x) para o cálculo do gradiente (Derivada)
    public static double funcaoI(double x) {
        return -2 * Math.pow(x, 3) + 10 * Math.pow(x, 2) - 5 * x + 50;
    }

    // 2. Função V(x) para o cálculo da área (Integral)
    public static double funcaoV(double x) {
        return -0.5 * Math.pow(x, 4) + 2 * Math.pow(x, 3) - Math.pow(x, 2) + 5;
    }


     // Aplica o metodo da Diferença Centrada para encontrar a derivada.

    public static double calcularDiferencaCentrada(Function<Double, Double> f, double x, double h) {
        return (f.apply(x + h) - f.apply(x - h)) / (2 * h);
    }


     // Aplica a Regra dos Trapézios (Composta) para encontrar a integral definida.

    public static double calcularRegraTrapezios(Function<Double, Double> f, double a, double b, int n) {
        double h = (b - a) / n;
        double soma = f.apply(a) + f.apply(b);

        for (int i = 1; i < n; i++) {
            double xi = a + i * h;
            soma += 2 * f.apply(xi);
        }

        return (h / 2.0) * soma;
    }


      //Aplica a Regra de Simpson 1/3 (Composta) para encontrar a integral definida.
      //Obs: 'n' obrigatoriamente deve ser um número par.

    public static double calcularRegraSimpson(Function<Double, Double> f, double a, double b, int n) {
        if (n % 2 != 0) {
            throw new IllegalArgumentException("O número de subintervalos (n) deve ser par para Simpson 1/3.");
        }

        double h = (b - a) / n;
        double soma = f.apply(a) + f.apply(b);

        for (int i = 1; i < n; i++) {
            double xi = a + i * h;
            if (i % 2 == 0) {
                // Índices pares multiplicam por 2
                soma += 2 * f.apply(xi);
            } else {
                // Índices ímpares multiplicam por 4
                soma += 4 * f.apply(xi);
            }
        }

        return (h / 3.0) * soma;
    }

    public static void main(String[] args) {
        System.out.println("\n=========================================================");

        System.out.println("--- 1. Derivação Numérica (Diferença Centrada) ---");
        double hDerivada = 0.1; // Passo utilizado na resolução


        double gradiente1 = calcularDiferencaCentrada(DerivacaoIntegracao::funcaoI, 1.0, hDerivada);
        System.out.printf("Gradiente I'(1.0) com h=%.1f : %.3f%n", hDerivada, gradiente1);

        double gradiente2 = calcularDiferencaCentrada(DerivacaoIntegracao::funcaoI, 2.0, hDerivada);
        System.out.printf("Gradiente I'(2.0) com h=%.1f : %.3f%n", hDerivada, gradiente2);
        System.out.println("=========================================================\n");

        System.out.println("\n=========================================================");
        System.out.println("--- 2. Integração Numérica (Oclusão Ambiente) ---");
        double a = 0.0;
        double b = 2.0; // Distância D
        int n = 2;      // Número de subintervalos usado na conta manual

        double areaTrapezios = calcularRegraTrapezios(DerivacaoIntegracao::funcaoV, a, b, n);
        System.out.printf("Área V(x) via Trapézios (n=%d): %.3f%n", n, areaTrapezios);

        double areaSimpson = calcularRegraSimpson(DerivacaoIntegracao::funcaoV, a, b, n);
        System.out.printf("Área V(x) via Simpson 1/3 (n=%d): %.3f%n", n, areaSimpson);
        System.out.println("=========================================================");

    }
}