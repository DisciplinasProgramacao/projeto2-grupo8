import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
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
    public final String nome;
    private ABB<Vertice> vertices;

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

        Grafo grafoCompleto = new Grafo("grafoCompleto");

        for (int i = 1; i <= ordem; i++) {
            grafoCompleto.addVertice(i);
        }

        for (int origem = 1; origem <= ordem; origem++) {
            for (int destino = origem + 1; destino <= ordem + 1; destino++) {
                grafoCompleto.addAresta(origem, destino, 0);
            }
        }

        return grafoCompleto;
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

    public Vertice existeVertice(int idVertice) {
        Vertice vertice = vertices.find(idVertice);
        if (vertice != null)
            return vertice;
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
        return null;
    }

    public Aresta existeAresta(int verticeA, int verticeB) {
        Vertice verA = this.existeVertice(verticeA);
        Vertice verB = this.existeVertice(verticeB);

        if (verA != null && verB != null) {
            return verA.existeAresta(verticeB);
        }
        return null;
    }

    public boolean completo() {
        return false;
    }

    /**
     * 
     * @param vertices Recebe uma lista de vértices, para criar o subgrafo
     * @return Subgrafo
     */

    public Grafo subGrafo(Lista<Integer> vertices) {
        Grafo subgrafo = new Grafo("Subgrafo de " + this.nome);
        Integer vetor[] = new Integer[vertices.size()];
        vetor = vertices.allElements(vetor);

        for (int i = 0; i < vetor.length; i++) {
            subgrafo.addVertice(vetor[i]);
        }
        for (int i = 0; i < vetor.length; i++) {
            for (int x = 0; x < vetor.length; x++) {
                if ((this.existeAresta(vetor[i], vetor[x]) != null) && (subgrafo.existeVertice(vetor[x]) != null)) {
                    subgrafo.addAresta(vetor[i], vetor[x], 0); // Se sim, adiciona essa aresta no subgrafo
                }
            }
        }
        return subgrafo;
    }

    public int tamanho() {
        return Integer.MIN_VALUE;
    }

    public int ordem() {
        return this.vertices.size();
    }

}
