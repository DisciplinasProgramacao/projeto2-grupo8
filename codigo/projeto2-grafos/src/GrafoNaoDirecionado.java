public class GrafoNaoDirecionado extends GrafoMutavel{
   
    public GrafoNaoDirecionado(String nome) {
        super(nome);
    }

    /**
     * Adiciona aresta ao grafo não direcionado
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
            adicionou = (saida.addAresta(destino, peso) && chegada.addAresta(origem, peso));
        }
        return adicionou;
    }

    /**
     * Retorna a aresta removida e null caso não encontrar aresta
     * 
     * @param origem Id do Vértice de origem
     * @param destino Id do Vértice de destindo
     * 
     * @return Aresta removida e null caso não encontre aresta
     */
    @Override
    public Aresta removeAresta(int origem, int destino) {
        Vertice verticeO = this.existeVertice(origem);
        Vertice verticeD = this.existeVertice(destino);

        Aresta arestaR = this.existeAresta(origem, destino);

        if (arestaR == null) {
            System.out.println("A aresta não existe no grafo.");
            return null;
        }else{
           verticeO.removeAresta(destino);
           verticeD.removeAresta(origem);
           return arestaR;
        }
    }
}
