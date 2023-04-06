import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class GrafoTeste {
    private Grafo grafo;

    @BeforeEach

    public void prepare() throws Exception {
        this.grafo = new Grafo("teste");
    }

    @Test 
    public void deveAdicionarUmVerticeComIdUm() {        
        assertTrue(grafo.addVertice(1));
    }

    @Test
    public void adicionaArestaPonderada() {
        

        this.grafo.addVertice(1);
        this.grafo.addVertice(2);
        this.grafo.addAresta(1, 2, 5);

        assertEquals(2, this.grafo.numVertices());
        assertEquals(1, this.grafo.numArestas());
    }

    @Test 
    public void testaSubGrado(){

        Lista<Integer> minhaLista = new Lista<>();
        minhaLista.add(1);
        minhaLista.add(2);
        minhaLista.add(3);
        //Cria grafo
        Grafo grafo = new Grafo();
        grafo.addAresta(1, 2, 0);
        grafo.addAresta(1, 4, 0);
        grafo.addAresta(2, 3, 0);
        grafo.addAresta(2, 4, 0);
        grafo.addAresta(2, 5, 0);
        grafo.addAresta(3, 5, 0);
        grafo.addAresta(3, 6, 0);
        grafo.addAresta(5, 6, 0);
        //Cria Subgrafo e confirma
        Grafo subgrafo = grafo.subgrafo(minhaLista);

        assertTrue(subgrafo.temVertice(1));
        assertTrue(subgrafo.temVertice(2));
        assertTrue(subgrafo.temVertice(3));
        assertFalse(subgrafo.temVertice(4));

        assertTrue(subgrafo.existeAresta(1, 2, 0));
        assertFalse(subgrafo.existeAresta(5, 6, 0));

    }
}
