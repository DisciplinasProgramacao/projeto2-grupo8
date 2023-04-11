import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VerticeTest {
    Vertice vertice, vertice2, vertice3, vertice4;

    @BeforeEach
    public void prepare() {
        vertice = new Vertice(1);
        vertice2 = new Vertice(2);
        vertice3 = new Vertice(3);
        vertice4 = new Vertice(4);
    }

    @Test
    public void deveRetornarIdCorretoDoVertice(){
        assertEquals(vertice.getId(), 1);
    }

    @Test
    public void deveRetornarTrueCasoArestaSejaAdicionadaComSucesso(){
        assertTrue(vertice.addAresta(2));
    }

    @Test
    public void deveRetornarFalseCasoTenteAdicionarArestaQueJaExista(){
        vertice2.addAresta(1);
        assertFalse(vertice2.addAresta(1));
    }

    @Test
    public void deveAdicionarArestaPonderadaComSucesso(){
        assertTrue(vertice.addAresta(2, 5));
    }

    @Test
    public void deveRetornarArestaCasoArestaJaExista(){
        vertice.addAresta(2, 6);

        assertNotNull(vertice.existeAresta(2));
    }

    @Test
    public void deveRetornarNullCasoArestaNaoExista(){
        assertEquals(null, vertice.existeAresta(2));
    }

    @Test
    public void deveRetornarArestaCasoSejaRemovidaComSucesso(){
        vertice2.addAresta(1);

        assertNotNull(vertice2.removeAresta(1));
    }

    @Test
    public void deveRetornarArestaNullCasoTenteRemoverArestaInexistente(){
        assertEquals(null, vertice2.removeAresta(1));
    }

    @Test
    public void deveRetornarGrauCorretoDoVertice(){
        vertice.addAresta(1, 2);
        vertice.addAresta(2, 2);
        vertice.addAresta(3, 2);
        vertice.addAresta(4, 2);

        assertEquals(vertice.grau(), 4);
    }

    @Test
    public void deveRetornarTrueCasoVerticeTenhaSidoVisitada(){
        vertice.visitar();
        assertTrue(vertice.visitado());
    }

    @Test
    public void deveRetornarFalseCasoVerticeAindaNaoTenhaSidoVisitada(){
        assertFalse(vertice4.visitado());
    }

    @Test
    public void deveLimparVisitaAoVertice(){
        vertice.visitar();
        vertice.limparVisita();
        assertFalse(vertice.visitado());
    }

    @Test
    public void deveRetornarTodosOsVizinhosDeUmVertice(){
        vertice.addAresta(1);
        vertice.addAresta(2);
        vertice.addAresta(3);
        vertice.addAresta(4);

        Lista<Integer> listaVizinhos = new Lista<Integer>();

        listaVizinhos.add(2);
        listaVizinhos.add(3);
        listaVizinhos.add(4);

        assertEquals(listaVizinhos, vertice.vizinhos());
    }

    @Test
    public void deveRetornarListaVaziaCasoVerticeNaoPossuaVizinhos(){
        assertEquals(vertice.vizinhos().size(), 0);
    }
}
