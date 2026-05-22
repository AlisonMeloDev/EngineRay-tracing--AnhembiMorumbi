import java.util.Scanner;

public class CalculoTriangulo3D {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Definição dos pontos da Versão A
        double[] A = {0, 0, 0};
        double[] B = {1, 2, 0};
        double[] C = {3, 1, 1};

        System.out.println("=========================================================");
        System.out.println("  RESOLUÇÃO PASSO A PASSO: TRIÂNGULO NA MALHA 3D       ");
        System.out.println("=========================================================");
        System.out.println("Pontos fornecidos:");
        System.out.printf("A = (%.1f, %.1f, %.1f)\n", A[0], A[1], A[2]);
        System.out.printf("B = (%.1f, %.1f, %.1f)\n", B[0], B[1], B[2]);
        System.out.printf("C = (%.1f, %.1f, %.1f)\n", C[0], C[1], C[2]);

        // -----------------------------------------------------------------
        // PASSO 1: Determinar os vetores a partir dos vértices
        // -----------------------------------------------------------------
        aguardarEnter(scanner, "\n[Passo 1] Pressione ENTER para determinar os vetores AB e AC...");

        // Vetor AB = B - A
        double[] AB = {B[0] - A[0], B[1] - A[1], B[2] - A[2]};
        // Vetor AC = C - A
        double[] AC = {C[0] - A[0], C[1] - A[1], C[2] - A[2]};

        System.out.println("\n-> Vetores resultantes:");
        System.out.printf("   Vetor AB (B - A) = (%.1f, %.1f, %.1f)\n", AB[0], AB[1], AB[2]);
        System.out.printf("   Vetor AC (C - A) = (%.1f, %.1f, %.1f)\n", AC[0], AC[1], AC[2]);

        // -----------------------------------------------------------------
        // PASSO 2: Produto Vetorial para encontrar o vetor normal
        // -----------------------------------------------------------------
        aguardarEnter(scanner, "\n[Passo 2] Pressione ENTER para calcular o produto vetorial (Vetor Normal)...");

        // Cálculo do produto vetorial N = AB x AC usando a regra do determinante:
        // i * (AB[1]*AC[2] - AB[2]*AC[1]) - j * (AB[0]*AC[2] - AB[2]*AC[0]) + k * (AB[0]*AC[1] - AB[1]*AC[0])
        double nx = (AB[1] * AC[2]) - (AB[2] * AC[1]);
        double ny = (AB[2] * AC[0]) - (AB[0] * AC[2]); // invertido para compensar o sinal do j
        double nz = (AB[0] * AC[1]) - (AB[1] * AC[0]);

        double[] normal = {nx, ny, nz};

        System.out.println("\n-> O produto vetorial AB x AC é dado pelo determinante das componentes i, j, k:");
        System.out.printf("   N_x = (%.1f * %.1f) - (%.1f * %.1f) = %.1f\n", AB[1], AC[2], AB[2], AC[1], nx);
        System.out.printf("   N_y = (%.1f * %.1f) - (%.1f * %.1f) = %.1f\n", AB[2], AC[0], AB[0], AC[2], ny);
        System.out.printf("   N_z = (%.1f * %.1f) - (%.1f * %.1f) = %.1f\n", AB[0], AC[1], AB[1], AC[0], nz);
        System.out.printf("\n-> Vetor Normal à face: N = (%.1f, %.1f, %.1f)\n", normal[0], normal[1], normal[2]);

        // -----------------------------------------------------------------
        // PASSO 3: Cálculo da Área da Superfície
        // -----------------------------------------------------------------
        aguardarEnter(scanner, "\n[Passo 3] Pressione ENTER para calcular a área da superfície do triângulo...");

        // A norma (módulo) do vetor normal é igual à área do paralelogramo formado por AB e AC.
        // A área do triângulo é a metade desse valor.
        double normaAoQuadrado = (normal[0] * normal[0]) + (normal[1] * normal[1]) + (normal[2] * normal[2]);
        double normaNormal = Math.sqrt(normaAoQuadrado);
        double areaTriangulo = normaNormal / 2.0;

        System.out.println("\n-> Etapas finais da Área:");
        System.out.printf("   1. Norma do Vetor Normal ||N|| = sqrt((%.1f)² + (%.1f)² + (%.1f)²)\n", normal[0], normal[1], normal[2]);
        System.out.printf("   2. ||N|| = sqrt(%.1f) = %.4f (Esta é a área do paralelogramo)\n", normaAoQuadrado, normaNormal);
        System.out.printf("   3. Área do Triângulo = ||N|| / 2 = %.4f / 2\n", normaNormal);

        System.out.println("\n=========================================================");
        System.out.printf("  RESULTADO FINAL: Área do triângulo = %.4f\n", areaTriangulo);
        System.out.println("=========================================================");


    }

    /**
     * Metodo auxiliar para exibir uma mensagem e pausar até o usuário apertar Enter.
     */
    private static void aguardarEnter(Scanner scanner, String mensagem) {
        System.out.println(mensagem);
        scanner.nextLine();
    }
}