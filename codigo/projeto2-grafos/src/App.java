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
        System.out.println("0 - Sair");
        System.out.print("\nDigite sua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());

        return opcao;
    }

    public static void main(String[] args) throws Exception {
        limparTela();

        Grafo grafo = new Grafo("");
        int opcao = -1;

        do {
            opcao = menuGrafos();
            limparTela();
            switch (opcao) {
                case 1:
                    System.out.println("Nome do arquivo: ");
                    String nomeArquivo = teclado.nextLine();

                    try{
                        grafo.carregar(nomeArquivo); //precisa acessar o método carregar que tá dentro de grafoMutavel
                    }catch(FileNotFoundException e){
                        System.out.println("Arquivo não existente, você deve primeiro gerar e salvar o grafo" + e);
                    }
                    break;
                case 2:
                    //montarGrafo(grafo);
                    //grafo.salvar(nomeArquivo);
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    System.out.println("Ordem do grafo: ");
                    int ordem = Integer.parseInt(teclado.nextLine());
                    grafo = Grafo.grafoCompleto(ordem);
                    break;
                case 6:
                    Grafo subGrafo = gerarSubGrafo(grafo); //Verificar como preencher o arquivo nesse caso
                    //subGrafo.salvar("subgrafo");
                    System.out.println("Arquivo de subgrafo criado"); //Posteriormente verificar a criação de um método para imprimir o arq
                    break;
            }
            pausa();
        } while (opcao != 0);
        System.out.println("Saindo...");
    }

    private static Grafo gerarSubGrafo(Grafo grafo){
        Lista<Integer> verticesSubGrafo = new Lista<>();
        int opcao, vertice;
        do{
            System.out.println("Vértice que deseja para gerar o subgrafo: ");
            vertice = Integer.parseInt(teclado.nextLine());
            verticesSubGrafo.add(vertice);
            
            System.out.println("Digite um número para continuar a selecionar vértices ou 0 para sair: ");
            opcao = Integer.parseInt(teclado.nextLine());
        }while(opcao != 0);

        return grafo.subGrafo(verticesSubGrafo);
    }
}
