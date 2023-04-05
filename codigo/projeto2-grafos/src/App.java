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
        limparTela();
        System.out.println("Nome do arquivo: ");
        String nomeArquivo = teclado.nextLine();

        Grafo grafo = new Grafo(nomeArquivo);
        int opcao = -1;

        do {
            opcao = menuGrafos();
            limparTela();
            switch (opcao) {
                case 1:
                    grafo.carregar(nomeArquivo); //verificar se o arq ja existe
                    break;
                case 2:
                    montarGrafo(grafo);
                    grafo.salvar(nomeArquivo);
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

    private static void montarGrafo(Grafo grafo){
        System.out.println("Tamanho do grafo: ");
        int tamanho = Integer.parseInt(teclado.nextLine());
        int opcao;

        for(int i = 1; i <= tamanho; i++){
            grafo.addVertice(i);
        }

        do{
            System.out.println("Arestas (Formato: 1 2 3 [origem destino peso]): ");
            String arestas = teclado.nextLine();

            while(arestas.split(" ").length != 3){
                System.out.println("Formato de aresta inválido, digite novamente: ");
                System.out.println("Arestas (Formato: 1 2 3 [origem destino peso]): ");
                arestas = teclado.nextLine();
            }

            String[] dadosAresta = arestas.split(" ");
            int origem = Integer.parseInt(dadosAresta[0]);
            int destino = Integer.parseInt(dadosAresta[1]);
            int peso = Integer.parseInt(dadosAresta[2]);

            grafo.addAresta(origem, destino, peso);

            limparTela();
            System.out.println("Digite um número para continuar a adicionar arestas ou 0 para sair: ");
            opcao = Integer.parseInt(teclado.nextLine());

        }while(opcao != 0);
    }

}
