public class GrafoCompleto extends Grafo {
    public GrafoCompleto(int ordem){
        super("Grafo completo de ordem " + ordem);

        for (int i = 1; i <= ordem; i++) {
            Vertice vertice = new Vertice(i);
            vertices.add(i, vertice);
        }

        for (int origem = 1; origem <= ordem; origem++) {
            for (int destino = origem + 1; destino <= ordem; destino++) {
                vertices.find(origem).addAresta(destino);
            }
        }
    }
}
