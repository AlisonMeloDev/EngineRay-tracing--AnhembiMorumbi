// Importa a classe Scanner, que permite ler dados do teclado
import java.util.Scanner;

public class InterpolacaoLagrange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Cria o leitor de teclado


        // Vetores com os pontos conhecidos (x, Intensidade)
// São os 4 pares de valores que definem a curva a ser interpolada
        double[] x = {0.0, 0.5, 1.0, 1.5};
        double[] I = {255, 200, 120, 50};
// Ponto onde queremos descobrir o valor de I (valor alvo)
        double xAlvo = 0.8;


        // Exibe no console o cabeçalho e os pontos da tabela
        System.out.println("=========================================================");
        System.out.println("=== INTERPOLAÇÃO POLINOMIAL DE LAGRANGE =================");
        System.out.println("=========================================================");
        System.out.println("Objetivo: Calcular a intensidade I para x = " + xAlvo);
        System.out.println("Pontos conhecidos:");
// Loop de 0 a 3: percorre cada um dos 4 pontos e imprime suas coordenadas
        for (int i = 0; i < 4; i++) {
            System.out.println("  P" + i + ": (x = " + x[i] + ", I = " + I[i] + ")");
        }
// Pausa e espera o usuário pressionar ENTER para continuar
        System.out.println("\n[Aperte ENTER para continuar...]");
        scanner.nextLine();


        // Variável que vai acumular a soma de todos os termos de Lagrange
        double intensidadeFinal = 0.0;
// Para cada ponto Pi da tabela, calculamos seu coeficiente Li(x)
        for (int i = 0; i < 4; i++) {
            System.out.println("=========================================================");
            System.out.println("--- Calculando para o ponto P" + i + " ---");
            System.out.println("=========================================================");
            // Numerador e denominador do coeficiente Li(x)
            // Começam em 1.0 pois são produtos acumulados (multiplicação)
            double numerador = 1.0;
            double denominador = 1.0;
            // Strings para montar visualmente a fórmula na tela
            String textoNumerador = "";
            String textoDenominador = "";



            // Para cada j ≠ i, multiplicamos os fatores da fórmula de Lagrange:
            // Numerador:   (xAlvo - x[j])    ← usa o ponto onde queremos calcular
            // Denominador: (x[i]  - x[j])    ← usa apenas os pontos da tabela
            for (int j = 0; j < 4; j++) {
                if (i != j) { // Pula o caso j == i (não multiplica o ponto por ele mesmo)
                    numerador   = numerador   * (xAlvo - x[j]);
                    denominador = denominador * (x[i]  - x[j]);
                    // Concatena na string o fator atual, para exibir a fórmula completa depois
                    textoNumerador   = textoNumerador   + "(" + xAlvo + " - " + x[j] + ") ";
                    textoDenominador = textoDenominador + "(" + x[i]  + " - " + x[j] + ") ";
                }
            }


            // Li é o coeficiente de Lagrange para o ponto i: numerador dividido pelo denominador
            double L = numerador / denominador;
            // Cada ponto contribui com I[i] * Li para o resultado final
            // Isso é a fórmula: P(x) = Σ I[i] * Li(x)
            double termo = I[i] * L;
            // Acumula a contribuição deste ponto na soma total
            intensidadeFinal = intensidadeFinal + termo;
            // Imprime a fórmula montada, os valores numéricos e a contribuição
            System.out.println("Fórmula: [" + textoNumerador + "] / [" + textoDenominador + "]");
            System.out.println("Valores: " + numerador + " / " + denominador);
            System.out.println("Resultado de L: " + L);
            System.out.println("Contribuição deste ponto (I * L): " + I[i] + " * " + L + " = " + termo);
            // Pausa entre cada ponto para o usuário acompanhar passo a passo
            System.out.println("\n[Aperte ENTER para continuar...]");
            scanner.nextLine();
        }


        // Após somar as contribuições dos 4 pontos, exibe o valor interpolado
        System.out.println("=========================================================");
        System.out.println("=== RESULTADO FINAL =====================================");
        System.out.println("=========================================================");
        System.out.println("A intensidade interpolada no x = " + xAlvo + " é: " + intensidadeFinal);

    }
}