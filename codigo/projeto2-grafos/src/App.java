import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    static Scanner teclado = new Scanner(System.in);

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void pausa() {
        System.out.println("Enter para continuar.");
        teclado.nextLine();
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
        System.out.println("7 - Fazer pesquisa DFS");
        System.out.println("8 - Fazer pesquisa BFS");
        System.out.println("0 - Sair");
        System.out.print("\nDigite sua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());

        return opcao;
    }

    public static void main(String[] args) throws Exception {
        limparTela();

        String verticeEntradaTeclado, nomeArquivo;
        Grafo grafo = new Grafo("");
        GrafoMutavel grafoMutavel = new GrafoMutavel("");

        int opcao = -1;

        do {
            opcao = menuGrafos();
            limparTela();
            switch (opcao) {
                case 1:
                    System.out.println("Nome do arquivo a ser carregado: ");
                    nomeArquivo = teclado.nextLine();

                    try {
                        grafoMutavel.carregar(nomeArquivo);
                    } catch (FileNotFoundException e) {
                        System.out.println("Arquivo não existente, você deve primeiro gerar e salvar o grafo " + e);
                    }
                    break;

                case 2:
                    System.out.println("Nome do arquivo: ");
                    nomeArquivo = teclado.nextLine();
                    try {
                        grafoMutavel.salvar(nomeArquivo);
                    } catch (Exception e) {
                        System.out.println("Não existe grafo para ser salvo, você deve primeiro gerar o grafo " + e);
                    }

                    break;
                
                    case 3:
                    System.out.println("Grafo COM aresta ponderada\n");
                    grafoMutavel.addVertice(1);
                    grafoMutavel.addVertice(2);
                    grafoMutavel.addVertice(3);
                    grafoMutavel.addVertice(4);
                    grafoMutavel.addVertice(5);
                    grafoMutavel.addVertice(6);
                    grafoMutavel.addAresta(1, 2, 3);
                    grafoMutavel.addAresta(4, 5, 6);
                    System.out.println(grafoMutavel.toString());

                    break;

                case 4:
                    grafoMutavel.addVertice(7);
                    grafoMutavel.addVertice(8);
                    grafoMutavel.addVertice(9);
                    grafoMutavel.addVertice(10);
                    grafoMutavel.addVertice(11);
                    grafoMutavel.addVertice(12);
                    grafoMutavel.addAresta(7, 8, 0);
                    grafoMutavel.addAresta(10, 11, 0);
                    System.out.println(grafoMutavel.toString());
                    break;

                case 5:
                    System.out.println("Ordem do grafo: ");
                    int ordem = Integer.parseInt(teclado.nextLine());
                    grafo = Grafo.grafoCompleto(ordem);
                    System.out.println(grafo.toString());
                    break;

                case 6:
                    grafoMutavel.addVertice(1);
                    grafoMutavel.addVertice(2);
                    grafoMutavel.addVertice(3);
                    grafoMutavel.addVertice(4);
                    grafoMutavel.addVertice(5);

                    grafoMutavel.addAresta(1, 2, 0);
                    grafoMutavel.addAresta(1, 3, 0);
                    grafoMutavel.addAresta(1, 4, 0);
                    grafoMutavel.addAresta(1, 5, 0);
                    grafoMutavel.addAresta(2, 3, 0);
                    grafoMutavel.addAresta(2, 4, 0);
                    grafoMutavel.addAresta(2, 5, 0);
                    grafoMutavel.addAresta(3, 4, 0);
                    grafoMutavel.addAresta(3, 5, 0);
                    grafoMutavel.addAresta(4, 5, 0);

                    grafoMutavel.salvar("GrafoMutavel");

                    gerarSubGrafo(grafoMutavel);
                    break;

                case 7:
                    if(grafo.vertices.size() == 0){
                        System.out.println("Selecione primeiro a opção 5 para gerar um grafo");
                        break;
                    }
                    verticeEntradaTeclado = "";
                    System.out.println("\nDigite o vertice: ");
                    verticeEntradaTeclado = teclado.nextLine();
                    System.out.println("Retorno da pesquisa bfs");
                    System.out.println(grafo.dfs(Integer.parseInt(verticeEntradaTeclado)).toString());
                    break;

                case 8:
                    if(grafo.vertices.size() == 0){
                        System.out.println("Selecione primeiro a opção 5 para gerar um grafo");
                        break;
                    }                
                    System.out.println("\nDigite o vertice: ");
                    verticeEntradaTeclado = teclado.nextLine();
                    System.out.println("Retorno da pesquisa bfs");
                    System.out.println(grafo.bfs(Integer.parseInt(verticeEntradaTeclado)).toString());
                    break;
            }
            pausa();
        } while (opcao != 0);
        System.out.println("Saindo...");
    }

    private static void gerarSubGrafo(GrafoMutavel grafo) {
        Lista<Integer> verticesSubGrafo = new Lista<>();
        int opcao, vertice;
        do {
            System.out.println("Vértice que deseja para gerar o subgrafo: ");
            vertice = Integer.parseInt(teclado.nextLine());
            verticesSubGrafo.add(vertice);

            System.out.println("Digite um número para continuar a selecionar vértices ou 0 para sair: ");
            opcao = Integer.parseInt(teclado.nextLine());
        } while (opcao != 0);

        System.out.println(grafo.subGrafo(verticesSubGrafo).toString());
    }
}
