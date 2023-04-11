import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;

public class GrafoNaoDirecionadoTest {
    
    GrafoNaoDirecionado meuGrafo = new GrafoNaoDirecionado("null");    

    @BeforeEach
    public void prepare(){

        meuGrafo = new GrafoNaoDirecionado("");
    }
    @Test
    public void adicionaArestaNaoDirecionada(){
        meuGrafo.addVertice(1);
        meuGrafo.addVertice(2);
        meuGrafo.addVertice(3);
        meuGrafo.addAresta(1, 2, 0);

        assertNotNull(meuGrafo.existeAresta(1, 2));
        assertNotNull(meuGrafo.existeAresta(2, 1));
    }
    @Test
    public void removeArestaNaoDirecionada(){
        meuGrafo.addVertice(1);
        meuGrafo.addVertice(2);
        meuGrafo.addVertice(3);
        meuGrafo.addAresta(1, 2, 0);
        meuGrafo.addAresta(1, 3, 0);

        assertNotNull(meuGrafo.removeAresta(1, 2));
        assertNull(meuGrafo.existeAresta(1, 2));
        assertNotNull(meuGrafo.existeAresta(1, 3));
        
    }
    @Test
    public void adicionaArestaNaoDirecionadaPonderada(){
        meuGrafo.addVertice(1);
        meuGrafo.addVertice(2);
        meuGrafo.addVertice(3);
        meuGrafo.addAresta(1, 2, 5);

        assertEquals(5, meuGrafo.existeAresta(1, 2).peso());
        assertEquals(5, meuGrafo.existeAresta(2, 1).peso());
    }
}
