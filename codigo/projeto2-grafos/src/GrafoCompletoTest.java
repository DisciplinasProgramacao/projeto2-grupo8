import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;

public class GrafoCompletoTest {

    GrafoCompleto meuGrafo;

    @BeforeEach
    public void prepare(){
        meuGrafo = new GrafoCompleto(5);
    }

    @Test
    public void deveCriarumGrafoCompletoDaClasse(){      
    
        assertTrue(meuGrafo.completo());
    
    }
    @Test
    public void criaComNomeeOrdem(){
        assertEquals( meuGrafo.nome(), "Grafo completo de ordem 5");
    }
}
