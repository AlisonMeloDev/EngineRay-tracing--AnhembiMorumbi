import java.util.Scanner; // Importa a classe Scanner para leitura de entrada do usuário

public class CalculoTriangulo3D {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Cria o scanner que vai "ouvir" o teclado

        // ---------------------------------------------------------------
        // DEFINIÇÃO DOS PONTOS DO TRIÂNGULO NO ESPAÇO 3D
        // Cada ponto tem 3 coordenadas: x, y, z
        // Usamos arrays de double (números decimais) para armazenar cada ponto
        // ---------------------------------------------------------------
        double[] A = {0, 0, 0}; // Ponto A na origem
        double[] B = {1, 2, 0}; // Ponto B com coordenadas (1, 2, 0)
        double[] C = {3, 1, 1}; // Ponto C com coordenadas (3, 1, 1)

        // Exibe o cabeçalho e os pontos fornecidos
        System.out.println("=========================================================");
        System.out.println("  RESOLUÇÃO PASSO A PASSO: TRIÂNGULO NA MALHA 3D       ");
        System.out.println("=========================================================");
        System.out.println("Pontos fornecidos:");
        System.out.printf("A = (%.1f, %.1f, %.1f)\n", A[0], A[1], A[2]); // A[0]=x, A[1]=y, A[2]=z
        System.out.printf("B = (%.1f, %.1f, %.1f)\n", B[0], B[1], B[2]);
        System.out.printf("C = (%.1f, %.1f, %.1f)\n", C[0], C[1], C[2]);

        // ---------------------------------------------------------------
        // PASSO 1: CÁLCULO DOS VETORES AB E AC
        // Um vetor entre dois pontos é calculado subtraindo as coordenadas:
        // Vetor AB = B - A (ponto de chegada menos ponto de saída)
        // Isso nos dá a "direção e distância" de A até B
        // ---------------------------------------------------------------
        aguardarEnter(scanner, "\n[Passo 1] Pressione ENTER para determinar os vetores AB e AC...");

        double[] AB = {B[0] - A[0], B[1] - A[1], B[2] - A[2]}; // Vetor de A até B
        double[] AC = {C[0] - A[0], C[1] - A[1], C[2] - A[2]}; // Vetor de A até C

        System.out.println("\n-> Vetores resultantes:");
        System.out.printf("   Vetor AB (B - A) = (%.1f, %.1f, %.1f)\n", AB[0], AB[1], AB[2]);
        System.out.printf("   Vetor AC (C - A) = (%.1f, %.1f, %.1f)\n", AC[0], AC[1], AC[2]);

        // ---------------------------------------------------------------
        // PASSO 2: PRODUTO VETORIAL (AB x AC) = VETOR NORMAL
        // O produto vetorial de dois vetores gera um TERCEIRO vetor
        // que é perpendicular (90°) ao plano formado pelos dois primeiros.
        // Esse vetor resultante é chamado de "Vetor Normal" da superfície.
        //
        // A fórmula é baseada no determinante de uma matriz 3x3:
        //   | i    j    k   |
        //   | AB.x AB.y AB.z|
        //   | AC.x AC.y AC.z|
        //
        // Componente X (i): (AB.y * AC.z) - (AB.z * AC.y)
        // Componente Y (j): (AB.z * AC.x) - (AB.x * AC.z)  <- sinal invertido do j
        // Componente Z (k): (AB.x * AC.y) - (AB.y * AC.x)
        // ---------------------------------------------------------------
        aguardarEnter(scanner, "\n[Passo 2] Pressione ENTER para calcular o produto vetorial (Vetor Normal)...");

        double nx = (AB[1] * AC[2]) - (AB[2] * AC[1]); // Componente X do vetor normal
        double ny = (AB[2] * AC[0]) - (AB[0] * AC[2]); // Componente Y do vetor normal
        double nz = (AB[0] * AC[1]) - (AB[1] * AC[0]); // Componente Z do vetor normal

        double[] normal = {nx, ny, nz}; // Agrupa as 3 componentes no vetor normal final

        System.out.println("\n-> O produto vetorial AB x AC é dado pelo determinante das componentes i, j, k:");
        System.out.printf("   N_x = (%.1f * %.1f) - (%.1f * %.1f) = %.1f\n", AB[1], AC[2], AB[2], AC[1], nx);
        System.out.printf("   N_y = (%.1f * %.1f) - (%.1f * %.1f) = %.1f\n", AB[2], AC[0], AB[0], AC[2], ny);
        System.out.printf("   N_z = (%.1f * %.1f) - (%.1f * %.1f) = %.1f\n", AB[0], AC[1], AB[1], AC[0], nz);
        System.out.printf("\n-> Vetor Normal à face: N = (%.1f, %.1f, %.1f)\n", normal[0], normal[1], normal[2]);

        // ---------------------------------------------------------------
        // PASSO 3: CÁLCULO DA ÁREA DO TRIÂNGULO
        // A NORMA (módulo/comprimento) do vetor normal é igual à
        // área do PARALELOGRAMO formado por AB e AC.
        // Como o triângulo é METADE do paralelogramo, dividimos por 2.
        //
        // Fórmula da norma: ||N|| = sqrt(nx² + ny² + nz²)
        // Área do triângulo = ||N|| / 2
        // ---------------------------------------------------------------
        aguardarEnter(scanner, "\n[Passo 3] Pressione ENTER para calcular a área da superfície do triângulo...");

        // Soma dos quadrados de cada componente (antes de tirar a raiz)
        double normaAoQuadrado = (normal[0] * normal[0]) + (normal[1] * normal[1]) + (normal[2] * normal[2]);

        double normaNormal = Math.sqrt(normaAoQuadrado); // Raiz quadrada = comprimento real do vetor normal

        double areaTriangulo = normaNormal / 2.0; // Área do triângulo = metade da área do paralelogramo

        System.out.println("\n-> Etapas finais da Área:");
        System.out.printf("   1. Norma do Vetor Normal ||N|| = sqrt((%.1f)² + (%.1f)² + (%.1f)²)\n", normal[0], normal[1], normal[2]);
        System.out.printf("   2. ||N|| = sqrt(%.1f) = %.4f (Esta é a área do paralelogramo)\n", normaAoQuadrado, normaNormal);
        System.out.printf("   3. Área do Triângulo = ||N|| / 2 = %.4f / 2\n", normaNormal);

        System.out.println("\n=========================================================");
        System.out.printf("  RESULTADO FINAL: Área do triângulo = %.4f\n", areaTriangulo);
        System.out.println("=========================================================");

    }
    static void aguardarEnter(Scanner scanner, String mensagem) {
        System.out.println(mensagem);
        scanner.nextLine();
    }
}