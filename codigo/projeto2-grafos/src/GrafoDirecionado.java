
public class GrafoDirecionado extends GrafoMutavel {

    public GrafoDirecionado(String nome) {
        super(nome);
    }
    
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

}
