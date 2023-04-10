import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GrafoMutavel extends Grafo {

    public GrafoMutavel(String nome) {
        super(nome);
    }

    /**
     * Adiciona um vértice com o id especificado. Ignora a ação e retorna false se
     * já existir um vértice com este id
     * 
     * @param id O identificador do vértice a ser criado/adicionado
     * @return TRUE se houve a inclusão do vértice, FALSE se já existia vértice com
     *         este id
     */
    public boolean addVertice(int id) {
        Vertice novo = new Vertice(id);
        return this.vertices.add(id, novo);
    }    
    
    public Vertice removeVertice(int id) {
        Vertice vertice = vertices.find(id);
        if (vertice != null) {
            vertices.remove(id);
            return vertice;
        }
        return null;
    }

    /**
     * Adiciona uma aresta entre dois vértices do grafo, caso os dois vértices
     * existam no grafo.
     * Caso a aresta já exista, ou algum dos vértices não existir, o comando é
     * ignorado e retorna FALSE.
     * 
     * @param origem  Vértice de origem
     * @param destino Vértice de destino
     * @param peso    Peso da aresta
     * @return TRUE se foi inserida, FALSE caso contrário
     */
    public boolean addAresta(int origem, int destino, int peso) {
        boolean adicionou = false;
        Vertice saida = this.existeVertice(origem);
        Vertice chegada = this.existeVertice(destino);
        if (saida != null && chegada != null) {
            adicionou = (saida.addAresta(destino, peso) && chegada.addAresta(origem, peso));
        }
        return adicionou;
    }

    public Aresta removeAresta(int origem, int destino) {
        Vertice verticeO = vertices.find(origem);
        Aresta arestaR = this.existeAresta(origem, destino);

        if (arestaR == null) {
            System.out.println("A aresta não existe no grafo.");
            return null;
        }else{
           return verticeO.removeAresta(destino);
        }
    }
 
    public void carregar(String nomeArquivo) throws FileNotFoundException, EOFException {
        File file = new File("./codigo/projeto2-grafos/arquivos/" + nomeArquivo + ".csv");
        Scanner entrada = new Scanner(file, "UTF-8");

        String leitura = entrada.nextLine();
        String vertices, linhaArestas;
        String[] array_vertice, array_aresta, arestas;
        int origem, destino, peso;

        vertices = leitura.split(";")[1];
        array_vertice = vertices.split(",");

        for (int i = 0; i < array_vertice.length; i++) {
            this.addVertice(Integer.parseInt(array_vertice[i]));
        }

        leitura = entrada.nextLine();

        linhaArestas = leitura.split(";")[1];
        array_aresta = linhaArestas.split(",");

        for (int j = 0; j < array_aresta.length; j++) {
            arestas = array_aresta[j].split("-");
            origem = Integer.parseInt(arestas[0]);
            destino = Integer.parseInt(arestas[1]);
            peso = Integer.parseInt(arestas[2]);

            this.addAresta(origem, destino, peso);
        }
        entrada.close();
    }

    /**
     * Salvar grafo em um arquivo
     * 
     * @param nomeArquivo nome do arquivo de destino
     * @throws IOException
     */
    public void salvar(String nomeArquivo) throws IOException {
        FileWriter arq = new FileWriter("./codigo/projeto2-grafos/arquivos/" + nomeArquivo + ".csv");
        PrintWriter gravarArq = new PrintWriter(arq);

        StringBuilder idVert = new StringBuilder();
        StringBuilder idArest = new StringBuilder();

        for (int i = 1; i <= this.ordem(); i++) {
            Vertice vertice = vertices.find(i);
            if(vertice != null)
                idVert.append(vertice.getId());
            if (i < this.ordem())
                idVert.append(",");

            /*
             * Pega o vértice da iteração e verifica se ele tem aresta com outros
             * ex: para o vértice 1, vai verificar se existe aresta dele com o vértice 2 (e
             * faz essa verificação para todos os outras vertics)
             */
            for (int j = i + 1; j <= this.ordem(); j++) {
                Aresta aresta = vertice.existeAresta(j);
                if (aresta != null) {
                    idArest.append(vertice.getId());
                    idArest.append("-");
                    idArest.append(aresta.destino());
                    idArest.append("-");
                    idArest.append(aresta.peso());
                    idArest.append(",");
                }
            }
        }
        String idArestStr = idArest.toString();

        gravarArq.write("vertice;");
        gravarArq.write(idVert.toString() + ";");
        gravarArq.write("\naresta;");
        gravarArq.write(idArestStr.substring(0, idArestStr.length() - 1) + ";");

        arq.close();
    }

}