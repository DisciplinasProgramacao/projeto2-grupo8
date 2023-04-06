

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;

public class GrafoTest {
    Grafo meuGrafo;

    @BeforeEach
    public void prepare() {
        meuGrafo = new Grafo(" ");    
    }

    /*@AfterEach
    public void limparSaida()  {
        output.reset();
    }    */

    @Test
    public void deveRetornarNuloCasoOrdemDoGrafoMenorQueZero(){        
        assertNull(Grafo.grafoCompleto(-4));
    }

    @Test
    public void deveRetornarFalseCasoExistaAresta(){
        meuGrafo.addAresta(4,5,1);
        assertFalse(meuGrafo.addAresta(4,5,5));
    }

   /* @Test
    public void deveRetornarTrueCasoNaoExistaAresta(){
        meuGrafo..
        assertTrue(meuGrafo.addAresta(2,3,1));
    }*/

    @Test
    public void deveRetornarFalsoCasoExistaVertice(){
        meuGrafo.addVertice(1);
        assertFalse(meuGrafo.addVertice(1));
    }

    @Test
    public void deveRetornarVerdadeiroCasoNaoExistaVertice(){
        assertTrue(meuGrafo.addVertice(900));
    }

    @Test 
    public void deveAdicionarUmVerticeComIdUm() {        
        assertTrue(meuGrafo.addVertice(1));
    }

    @Test
    public void deveAdicionarArestaPonderadaComSucesso() {
        meuGrafo.addVertice(1);
        meuGrafo.addVertice(2);
        meuGrafo.addAresta(1, 2, 5);
        
        Aresta arestaAdicionada = meuGrafo.existeAresta(1, 2);

        assertEquals(5, arestaAdicionada.peso());;
    }

    @Test
    public void deveRetornarPesoZeroCasoAdicioneArestaNaoPonderada() {
        meuGrafo.addVertice(1);
        meuGrafo.addVertice(2);
        meuGrafo.addAresta(1, 2, 0);
        
        Aresta arestaAdicionada = meuGrafo.existeAresta(1, 2);

        assertEquals(0, arestaAdicionada.peso());;
    }    

    @Test 
    public void naoDeveRetornarNuloCasoArestasEVerticesSejamAdicionadosNoSubgrafo(){
        Lista<Integer> minhaLista = new Lista<>();
        minhaLista.add(1);
        minhaLista.add(2);
        minhaLista.add(3);
        //Cria grafo

        meuGrafo.addVertice(1);
        meuGrafo.addVertice(2);
        meuGrafo.addVertice(3);
        meuGrafo.addAresta(1, 2, 0);
        meuGrafo.addAresta(1, 4, 0);
        meuGrafo.addAresta(2, 3, 0);
        meuGrafo.addAresta(2, 4, 0);
    
        meuGrafo = meuGrafo.subGrafo(minhaLista);

        //TO DO olhar isso
        assertNotNull(meuGrafo.existeVertice(1));
        assertNotNull(meuGrafo.existeVertice(2));
        assertNotNull(meuGrafo.existeVertice(3));

        assertNotNull(meuGrafo.existeAresta(1, 2));
        assertNotNull(meuGrafo.existeAresta(2, 3));
    }

}
