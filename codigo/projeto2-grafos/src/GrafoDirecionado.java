
public class GrafoDirecionado extends GrafoMutavel {

    /**
     * Construtor que cria grafo direcionado de acordo com seu nome
     * @param nome
     */
    public GrafoDirecionado(String nome) {
        super(nome);
    }
    
    /**
     * Adiciona aresta ao grafo direcionado
     * 
     * @param origem Id do Vértice de origem
     * @param destino Id do Vértice de destindo
     * @param destino Peso da aresta
     * 
     * @return TRUE caso aresta adicionada com sucesso, FALSE caso contrário
     */    
    @Override
    public boolean addAresta(int origem, int destino, int peso) {
        boolean adicionou = false;
        Vertice saida = this.existeVertice(origem);
        Vertice chegada = this.existeVertice(destino);
        if (saida != null && chegada != null) {
            adicionou = (saida.addAresta(destino, peso));
        }
        return adicionou;
    }
    
    /**
     * Retorna a aresta removida e null caso não encontrar aresta
     * 
     * @param origem Id do Vértice de origem
     * @param destino Id do Vértice de destindo
     * 
     * @return Aresta removida e null caso não encontre aresta ou não exista o vértice
     */
    @Override
    public Aresta removeAresta(int origem, int destino) {
        Vertice verticeO = this.existeVertice(origem);
        Vertice verticeD = this.existeVertice(destino);

        if(verticeD != null && verticeO != null){
            return verticeO.removeAresta(destino);
        }

        return null;
    }

}
