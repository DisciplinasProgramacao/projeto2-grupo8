import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArestaTest {
    Aresta arestaPonderada;
    Aresta arestaNaoPonderada;

    @BeforeEach
    public void prepare() {
        arestaPonderada = new Aresta(2,1);
        arestaNaoPonderada = new Aresta(3);
    }

    @Test
    public void deveRetornarPesoZeroCasoCrieArestaNaoPonderada(){
        assertEquals(arestaNaoPonderada.peso(), 0);
    }

    @Test
    public void deveRetornarPesoCorretoCasoCrieArestaPonderada(){
        assertEquals(arestaPonderada.peso(), 2);
    }

    @Test
    public void deveRetornarIdDoVerticeDeDestinoDaAresta(){
        assertEquals(arestaPonderada.destino(), 1);
        assertEquals(arestaNaoPonderada.destino(), 3);
    }

    @Test
    public void deveRetornarTrueCasoArestaTenhaSidoVisitada(){
        arestaNaoPonderada.visitar();
        assertTrue(arestaNaoPonderada.visitada());
    }

    @Test
    public void deveRetornarFalseCasoArestaAindaNaoTenhaSidoVisitada(){
        assertFalse(arestaPonderada.visitada());
    }

    @Test
    public void deveLimparVisitaAAresta(){
        arestaNaoPonderada.visitar();
        arestaNaoPonderada.limparVisita();
        assertFalse(arestaNaoPonderada.visitada());
    }
}
