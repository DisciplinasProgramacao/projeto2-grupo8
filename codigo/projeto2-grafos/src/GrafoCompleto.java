import java.util.logging.Level;

public class GrafoCompleto extends Grafo {
    public GrafoCompleto(String nome) {
        super(nome);
    }

    public Grafo GrafoCompleto(int ordem){
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
}
