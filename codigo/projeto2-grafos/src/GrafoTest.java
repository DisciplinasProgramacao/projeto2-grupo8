

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
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

    @Test
    public void deveRetornarTrueCasoNaoExistaAresta(){
        meuGrafo.addAresta(2,3,1);
        assertTrue(meuGrafo.addAresta(2,3,1));
    }

    @Test
    public void deveRetornarFalsoCasoExistaVertice(){
        meuGrafo.addVertice(1);
        assertFalse(meuGrafo.addVertice(1));
    }

    @Test
    public void deveRetornarVerdadeiroCasoNaoExistaVertice(){
        assertTrue(meuGrafo.addVertice(900));
    }

}
