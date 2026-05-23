import java.util.Scanner;

public class CalculoTriangulo3D {

    // Metodo auxiliar: pausa o programa e espera o usuário pressionar ENTER
    static void aguardarEnter(Scanner scanner, String mensagem) {
        System.out.println(mensagem);
        scanner.nextLine();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // ---------------------------------------------------------------
        // DEFINIÇÃO DOS PONTOS DO TRIÂNGULO NO ESPAÇO 3D
        // Versão A: A(0,0,0)  B(1,2,0)  C(3,1,1)
        // ---------------------------------------------------------------
        double[] A = {0, 0, 0};
        double[] B = {1, 2, 0};
        double[] C = {3, 1, 1};

        System.out.println("=========================================================");
        System.out.println("  RESOLUCAO PASSO A PASSO: TRIANGULO NA MALHA 3D        ");
        System.out.println("=========================================================");
        System.out.println("Pontos fornecidos:");
        System.out.printf("  A = (%.1f, %.1f, %.1f)\n", A[0], A[1], A[2]);
        System.out.printf("  B = (%.1f, %.1f, %.1f)\n", B[0], B[1], B[2]);
        System.out.printf("  C = (%.1f, %.1f, %.1f)\n", C[0], C[1], C[2]);

        // ---------------------------------------------------------------
        // PASSO 1: VETORES ENTRE AS ARESTAS (AB e AC)
        // Vetor = ponto de chegada - ponto de saida
        // AB representa a aresta de A até B
        // AC representa a aresta de A até C
        // ---------------------------------------------------------------
        aguardarEnter(scanner, "\n[Passo 1] Pressione ENTER para calcular os vetores das arestas AB e AC...");

        double[] AB = {B[0]-A[0], B[1]-A[1], B[2]-A[2]};
        double[] AC = {C[0]-A[0], C[1]-A[1], C[2]-A[2]};

        System.out.println("\n-> Vetor AB = B - A:");
        System.out.printf("   AB = (%.1f-%.1f, %.1f-%.1f, %.1f-%.1f) = (%.1f, %.1f, %.1f)\n",
                B[0],A[0], B[1],A[1], B[2],A[2], AB[0],AB[1],AB[2]);

        System.out.println("\n-> Vetor AC = C - A:");
        System.out.printf("   AC = (%.1f-%.1f, %.1f-%.1f, %.1f-%.1f) = (%.1f, %.1f, %.1f)\n",
                C[0],A[0], C[1],A[1], C[2],A[2], AC[0],AC[1],AC[2]);

        // ---------------------------------------------------------------
        // PASSO 2: PRODUTO VETORIAL (AB x AC) = VETOR NORMAL
        // O produto vetorial gera um vetor perpendicular ao plano do triângulo.
        // Formula pelo determinante da matriz 3x3:
        //   Nx = (AB.y * AC.z) - (AB.z * AC.y)
        //   Ny = (AB.z * AC.x) - (AB.x * AC.z)
        //   Nz = (AB.x * AC.y) - (AB.y * AC.x)
        // ---------------------------------------------------------------
        aguardarEnter(scanner, "\n[Passo 2] Pressione ENTER para calcular o produto vetorial AB x AC...");

        double nx = (AB[1]*AC[2]) - (AB[2]*AC[1]);
        double ny = (AB[2]*AC[0]) - (AB[0]*AC[2]);
        double nz = (AB[0]*AC[1]) - (AB[1]*AC[0]);
        double[] N = {nx, ny, nz};

        System.out.println("\n-> Produto vetorial N = AB x AC:");
        System.out.printf("   Nx = (%.1f * %.1f) - (%.1f * %.1f) = %.1f\n", AB[1],AC[2], AB[2],AC[1], nx);
        System.out.printf("   Ny = (%.1f * %.1f) - (%.1f * %.1f) = %.1f\n", AB[2],AC[0], AB[0],AC[2], ny);
        System.out.printf("   Nz = (%.1f * %.1f) - (%.1f * %.1f) = %.1f\n", AB[0],AC[1], AB[1],AC[0], nz);
        System.out.printf("\n-> Vetor Normal N = (%.1f, %.1f, %.1f)\n", N[0], N[1], N[2]);

        // ---------------------------------------------------------------
        // PASSO 3: VETOR NORMAL UNITÁRIO — "normal à face"
        // O vetor unitário tem comprimento 1 e aponta perpendicular à face.
        // Obtido dividindo cada componente de N pela norma (comprimento) de N:
        //   ||N|| = sqrt(Nx² + Ny² + Nz²)
        //   n = N / ||N||
        // ---------------------------------------------------------------
        aguardarEnter(scanner, "\n[Passo 3] Pressione ENTER para calcular o vetor normal unitario a face...");

        double norma = Math.sqrt(N[0]*N[0] + N[1]*N[1] + N[2]*N[2]);
        double[] nUnit = {N[0]/norma, N[1]/norma, N[2]/norma};

        System.out.printf("\n-> Norma de N: ||N|| = sqrt((%.1f)^2 + (%.1f)^2 + (%.1f)^2)\n", N[0],N[1],N[2]);
        System.out.printf("             ||N|| = sqrt(%.4f) = %.4f\n",
                N[0]*N[0] + N[1]*N[1] + N[2]*N[2], norma);
        System.out.println("\n-> Vetor Normal Unitario (normal a face) = N / ||N||:");
        System.out.printf("   n = (%.1f/%.4f, %.1f/%.4f, %.1f/%.4f)\n",
                N[0],norma, N[1],norma, N[2],norma);
        System.out.printf("   n = (%.4f, %.4f, %.4f)\n", nUnit[0], nUnit[1], nUnit[2]);
        System.out.printf("\n   Verificacao: ||n|| = %.4f (deve ser exatamente 1)\n",
                Math.sqrt(nUnit[0]*nUnit[0] + nUnit[1]*nUnit[1] + nUnit[2]*nUnit[2]));

        // ---------------------------------------------------------------
        // PASSO 4: ÁREA DA SUPERFÍCIE DO TRIÂNGULO
        // A norma do produto vetorial equivale à área do paralelogramo
        // formado por AB e AC. O triângulo ocupa metade desse paralelogramo.
        //   Area = ||N|| / 2
        // ---------------------------------------------------------------
        aguardarEnter(scanner, "\n[Passo 4] Pressione ENTER para calcular a area da superficie do triangulo...");

        double area = norma / 2.0;

        System.out.printf("\n-> Area do paralelogramo (AB e AC) = ||N|| = %.4f\n", norma);
        System.out.printf("-> Area do triangulo = ||N|| / 2 = %.4f / 2 = %.4f\n", norma, area);

        // ---------------------------------------------------------------
        // RESUMO FINAL
        // ---------------------------------------------------------------
        System.out.println("\n=========================================================");
        System.out.println("  RESUMO FINAL DOS RESULTADOS");
        System.out.println("=========================================================");
        System.out.printf("  1. Vetor AB (aresta)          = (%.1f, %.1f, %.1f)\n", AB[0],AB[1],AB[2]);
        System.out.printf("  2. Vetor AC (aresta)          = (%.1f, %.1f, %.1f)\n", AC[0],AC[1],AC[2]);
        System.out.println("=========================================================");
        System.out.printf("  3. Vetor Normal N = AB x AC   = (%.1f, %.1f, %.1f)\n", N[0],N[1],N[2]);
        System.out.println("=========================================================");
        System.out.printf("  4. Area da Superficie         = %.4f\n", area);
        System.out.println("=========================================================");

    }
}
