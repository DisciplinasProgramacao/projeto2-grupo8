

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;



public class GrafoTest {
    Grafo meuGrafo;

    @BeforeEach
    public void prepare() {
        meuGrafo = new Grafo();    
    }

    /*@AfterEach
    public void limparSaida()  {
        output.reset();
    }    */

    @Test
    public void deveRetornarMensagemCasoOrdemDoGrafoMenorQueZero(){
        meuGrafo.grafoCompleto(-4);
        assertEquals(output.toString(), "Ordem do grafo deve ser maior que zero");
    }

    @Test
    public void deveRetornarFalseCasoExistaAresta(){
        meuGrafo.addAresta(4,5,1);
        assertFalse(meuGrafo.addAresta());
    }

    @Test
    public void deveRetornarTrueCasoNaoExistaAresta(){
        meuGrafo.addAresta(2,3,1);
        assertTrue(meuGrafo.addAresta());
    }

    @Test
    public void deveRetornarFalsoCasoExistaVertice(){
        meuGrafo.addAresta(1,3,1);
        assertFalse(meuGrafo.addVertice());
    }

    @Test
    public void deveRetornarFalsoCasoNaoExistaVertice(){
        meuGrafo.addAresta(1,3,1);
        assertTrue(meuGrafo.addVertice());
    }

    @Test
    public void deveRetornarTrueCasoNaoExistaVertice(){
        meuGrafo.addAresta(3,4,1);
        assertTrue(meuGrafo.addVertice());
    }
}
