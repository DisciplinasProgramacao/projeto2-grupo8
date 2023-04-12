import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/** 
 * MIT License
 *
 * Copyright(c) 2021-23 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/**
 * Classe básica para um Grafo simples não direcionado.
 */
public class Grafo {
    private static final Logger logger = Logger.getLogger(Grafo.class.getName());

    private final String nome;
    protected ABB<Vertice> vertices;

    /*
     * Cria e retorna o grafo completo de acordo com a ordem recebida. Caso a ordem
     * seja menor ou igual a zero ignora e exibe um warning
     * 
     * @param ordem (Ordem do grafo - quantidade de vértices)
     * 
     * @return Grafo (Grafo completo criado)
     */
    public static Grafo grafoCompleto(int ordem) {
        if (ordem <= 0) {
            logger.log(Level.WARNING, "Ordem do grafo deve ser maior que zero");
            return null;
        }

        return new GrafoCompleto(ordem);
    }

    /**
     * Construtor. Cria um grafo vazio com um nome escolhido pelo usuário. Em caso
     * de nome não informado
     * (string vazia), recebe o nome genérico "Grafo"
     */
    public Grafo(String nome) {
        if (nome.length() == 0)
            this.nome = "Grafo";
        else
            this.nome = nome;
        this.vertices = new ABB<>();
    }

    /**
     * Retorna o nome do grafo (string), caso seja necessário em outras
     * classes/sistemas
     * 
     * @return O nome do grafo (uma string)
     */
    public String nome() {
        return this.nome;
    }

    /**
     * Verifica se existe vértice com o id passado como parametro
     * 
     * @param idVertice
     * @return null caso não exista vértice e o vertice caso exista
     */
    public Vertice existeVertice(int idVertice) {
        Vertice vertice = vertices.find(idVertice);
        if (vertice != null)
            return vertice;
        return null;
    }

    /**
     * Valida se existe aresta entre o vertice A e B
     * @param verticeA
     * @param verticeB
     * @return Aresta caso exista e null caso não exista
     */
    public Aresta existeAresta(int verticeA, int verticeB) {
        Vertice verA = this.existeVertice(verticeA);
        Vertice verB = this.existeVertice(verticeB);

        if (verA != null && verB != null) {
            return verA.existeAresta(verticeB);
        }
        return null;
    }

    /**
     * Valida se o grafo gerado é completo ou não
     * 
     * @return TRUE caso grafo completo e FALSE caso contrário
     */
    public boolean completo() {
        int ordem = this.ordem();

        for (int i = 1; i <= ordem; i++) {
            for (int j = i + 1; j <= ordem; j++) {
                if (this.existeAresta(i, j) == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Cria um subgrafo de um grafo criado anteriormente a partir de uma lista de vértices passados como parâmetro
     * 
     * @param vertices Recebe uma lista de vértices, para criar o subgrafo
     * @return Subgrafo
     */
    public Grafo subGrafo(Lista<Integer> vertices) {
        GrafoMutavel subGrafoMutavel = new GrafoMutavel("Subgrafo de" + this.nome);
        Integer vetor[] = new Integer[vertices.size()];
        vetor = vertices.allElements(vetor);

        for (int i = 0; i < vetor.length; i++) {
            subGrafoMutavel.addVertice(vetor[i]);
        }
        for (int i = 0; i < vetor.length; i++) {
            for (int x = 0; x < vetor.length; x++) {
                if ((this.existeAresta(vetor[i], vetor[x]) != null)
                        && (subGrafoMutavel.existeVertice(vetor[x]) != null)) {
                    subGrafoMutavel.addAresta(vetor[i], vetor[x], 0); // Se sim, adiciona essa aresta no subgrafo
                }
            }
        }
        return subGrafoMutavel;
    }

    /**
     * Retorna o tamanho do grafo
     * 
     * @return tamanho do grafo
     */
    public int tamanho() {
        int vertices = this.ordem();
        int arestas = 0;

        for (int i = 1; i <= vertices; i++) {
            for (int j = i + 1; j <= vertices; j++) {
                if (this.existeAresta(i, j) != null) {
                    arestas++;
                }
            }
        }
        return arestas + vertices;
    }

    /**
     * Método que retorna a ordem do grafo (quantidade de vértices)
     * 
     * @return ordem do grafo
     */
    public int ordem() {
        return this.vertices.size();
    }

    /**
     * Realiza a busca em largura no grafo a partir de um vértice de inicio
     * 
     * @param idVerticeInicio Vértice a partir do qual a busca ira iniciar
     * @return Grafo com o resultado da busca (Representação da busca)
     */
    public GrafoMutavel bfs(int idVerticeInicio) {
        GrafoMutavel grafoRetorno = new GrafoMutavel(this.nome + " BFS");
        Queue<Vertice> queue = new LinkedList<>();

        queue.add(this.vertices.find(idVerticeInicio));
        this.vertices.find(idVerticeInicio).visitar();
        grafoRetorno.addVertice(idVerticeInicio);

        while (!queue.isEmpty()) {
            Lista<Integer> vizinhosList = queue.remove().vizinhos();
            Integer[] vizinhosListArray = new Integer[vizinhosList.size()];
            vizinhosListArray = vizinhosList.allElements(vizinhosListArray);

            for (int i = 0; i < vizinhosList.size(); i++) {
                if (!vertices.find(vizinhosListArray[i]).visitado()) {
                    vertices.find(vizinhosListArray[i]).visitar();
                    queue.add(vertices.find(vizinhosListArray[i]));
                    grafoRetorno.addVertice(vizinhosListArray[i]);
                    grafoRetorno.addAresta(vertices.find(vizinhosListArray[i]).getId(), idVerticeInicio, 0);
                }
            }
            idVerticeInicio = vizinhosListArray[0];
        }

        return grafoRetorno;
    }

    /**
     * Realiza a busca em profundidade no grafo a partir de um vértice de inicio
     * 
     * @param idVerticeInicio Vértice a partir do qual a busca ira iniciar
     * @return Grafo com o resultado da busca (Representação da busca)
     */    
    public Grafo dfs(int idVerticeInicio) {
        GrafoMutavel grafoRetorno = new GrafoMutavel(this.nome + " DFS");

        this.vertices.find(idVerticeInicio).visitar();

        // se cria uma lista de vizinhos do vertice inicial
        Lista<Integer> vizinhosList = this.vertices.find(idVerticeInicio).vizinhos();
        for (int i = 1; i <= vizinhosList.size();) { // percorre essa lista de vizinhos
            search_dfs(this.vertices.find(i), grafoRetorno, i); // entra no metodo e faz ele percorrer cada vizinho do vizinho
            grafoRetorno.addAresta(this.vertices.find(idVerticeInicio).getId(), i, 0); // adiciona as arestas  do vertice original para seus vizinhos
            vizinhosList.remove(i); // remove da lista o vizinho já percorrido
        }        
        this.vertices.find(idVerticeInicio).visitar(); // mostra que o vertice foi visitado já
        grafoRetorno.addVertice(idVerticeInicio); // adiciona o vertice original

        for (int i = 1; i < this.vertices.size(); i++) {
            this.vertices.find(i).limparVisita();
        }
        return grafoRetorno;
    }

    /**
     * Método recursivo para realizar a busca em profundidade
     * @param vertice
     * @param grafoRetorno
     * @param i
     */    
    public void search_dfs(Vertice vertice, GrafoMutavel grafoRetorno, int i) {
        if (!vertice.visitado()) { // se não foi visitado ele entra
            vertice.visitar(); // adiciona que foi visitado
            Lista<Integer> vizinhosList = vertice.vizinhos(); // preenche uma list com os seus vizinhos
            for (int x = 1; x <= vizinhosList.size();) { // percorre essa lista de vizinhos 
                search_dfs(this.vertices.find(x), grafoRetorno, x); // entra no metodo e faz ele percorrer cada vizinho do vizinho
                vizinhosList.remove(x);  // remove da lista o vizinho já percorrido
                grafoRetorno.addAresta(vertice.getId(), x, 0);// adiciona as arestas  do vertice original para seus vizinhos
            }
        }
        grafoRetorno.addVertice(i);
    }

    /**
     * Método toString para converter um grafo em uma string
    * @return Vértices e arestas do grafo em uma string ex:
    *    Grafo: vertice;1,2,3
    *    aresta;1-2-0,1-3-0
    */
    public String toString(){
        StringBuilder idVert = new StringBuilder();
        StringBuilder idArest = new StringBuilder();

        StringBuilder grafoString = new StringBuilder("Grafo: ");

        Vertice arrayVertice[] = new Vertice[vertices.size()];
        vertices.allElements(arrayVertice);
    
        for (int i = 0; i < arrayVertice.length; i++) {
            Vertice vertice = vertices.find(arrayVertice[i].getId());
            idVert.append(vertice.getId());
            if (i+1 < arrayVertice.length)
                idVert.append(",");

            Aresta arestas[] = new Aresta[vertice.getAresta().size()];
            vertice.getAresta().allElements(arestas);

            for (int j = 0; j < arestas.length; j++) {
                Aresta aresta = vertice.existeAresta(arestas[j].destino());
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

        grafoString.append("vertice;");
        grafoString.append(idVert.toString() + ";");
        grafoString.append("\naresta;");
        if(idArestStr.length() > 0)
            grafoString.append(idArestStr.substring(0, idArestStr.length() - 1) + ";");

        return grafoString.toString();
    }

}