import java.util.Scanner;
public class App {
        static Scanner teclado = new Scanner(System.in);

        public static void limparTela() {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        public static int menuGrafos() {
            limparTela();
            System.out.println("Menu");
            System.out.println("==========================");
            System.out.println("1 - Carregar Grafo");
            System.out.println("2 - Salvar Grafo");
            System.out.println("3 - Criar Grafo com aresta ponderada");
            System.out.println("4 - Criar Grafo com aresta nao ponderada");
            System.out.println("5 - Gerar Grafo Completo");
            System.out.println("6 - Gerar Subgrafo ");
            System.out.println("0 - Sair");
            System.out.print("\nDigite sua opção: ");
            int opcao = Integer.parseInt(teclado.nextLine());
    
            return opcao;
        }

        public static void main(String[] args) throws Exception {
            ArqLeitura arquivo = new ArqLeitura("Arquivo.csv");
            
            // Caso queira retornar arresta basta digitar 1 vertice 2 e assim sucessivamente
            System.out.println(arquivo.ler(1));

            int opcao = -1;

            do {
                opcao = menuGrafos();
                limparTela();
                switch (opcao) {
                    case 1:
                        
                        break;
                    case 2:
                       
                        break;
                    case 3:
                       
                        break;
                    case 4:
                       
                        break;
                    case 5:
                       
                        break;
                    case 6:
                       
                        break;
                }
                
            } while (opcao != 0);
            System.out.println("Saindo...");
        }
    
}
