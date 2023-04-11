import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;

public class GrafoDirecionadoTest {

    GrafoDirecionado meuGrafo;

    @BeforeEach
    public void prepare(){
        meuGrafo = new GrafoDirecionado("");
    }
    @Test
    public void adicionaArestaDirecionada(){
        meuGrafo.addVertice(1);
        meuGrafo.addVertice(2);
        meuGrafo.addVertice(3);
        meuGrafo.addAresta(1, 2, 0);

        assertNotNull(meuGrafo.existeAresta(1, 2));
        assertNull(meuGrafo.existeAresta(2, 1));
    }
    @Test
    public void removeArestaDirecionada(){
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
     public void adicionaArestaDirecionadaPonderada(){
         meuGrafo.addVertice(1);
         meuGrafo.addVertice(2);
         meuGrafo.addVertice(3);
         meuGrafo.addAresta(1, 2, 5);
 
         assertEquals(5, meuGrafo.existeAresta(1, 2).peso());
         assertNull(meuGrafo.existeAresta(2, 1));
         assertNotNull(meuGrafo.existeAresta(1, 2));
     }
}
