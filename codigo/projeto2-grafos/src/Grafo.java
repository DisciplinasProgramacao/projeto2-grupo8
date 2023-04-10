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
    protected static final Logger logger = Logger.getLogger(Grafo.class.getName());
    public final String nome;
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

        //Grafo grafoCompleto = new Grafo("grafoCompleto");
        GrafoMutavel grafoMutav = new GrafoMutavel("grafoMutav");

        for (int i = 1; i <= ordem; i++) {
            grafoMutav.addVertice(i);
        }

        for (int origem = 1; origem <= ordem; origem++) {
            for (int destino = origem + 1; destino <= ordem + 1; destino++) {
                grafoMutav.addAresta(origem, destino, 0);
            }
        }

        return grafoMutav;
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

    public Vertice existeVertice(int idVertice) {
        Vertice vertice = vertices.find(idVertice);
        if (vertice != null)
            return vertice;
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
     * 
     * @param vertices Recebe uma lista de vértices, para criar o subgrafo
     * @return Subgrafo
     */

    public Grafo subGrafo(Lista<Integer> vertices) {
        //Grafo subgrafo = new Grafo("Subgrafo de " + this.nome);
        GrafoMutavel subGrafoMutavel = new GrafoMutavel("Subgrafo de" + this.nome);
        Integer vetor[] = new Integer[vertices.size()];
        vetor = vertices.allElements(vetor);

        for (int i = 0; i < vetor.length; i++) {
            subGrafoMutavel.addVertice(vetor[i]);
        }
        for (int i = 0; i < vetor.length; i++) {
            for (int x = 0; x < vetor.length; x++) {
                if ((this.existeAresta(vetor[i], vetor[x]) != null) && (subGrafoMutavel.existeVertice(vetor[x]) != null)) {
                    subGrafoMutavel.addAresta(vetor[i], vetor[x], 0); // Se sim, adiciona essa aresta no subgrafo
                }
            }
        }
        return subGrafoMutavel;
    }

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

    public int ordem() {
        return this.vertices.size();
    }

}
