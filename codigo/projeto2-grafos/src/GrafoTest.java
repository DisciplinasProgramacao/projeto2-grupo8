import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;

public class GrafoTest {
    GrafoMutavel meuGrafo;
    Grafo grafo;

    @BeforeEach
    public void prepare() {
        grafo = new Grafo("Meu Grafo Para Testes");
        meuGrafo = new GrafoMutavel(" ");    
    }
    /* 
    @AfterEach
    public void limparSaida()  {
        output.reset();
    }  */

    @Test
    public void deveRetornarNuloCasoTenteGerarGrafoComOrdemMenorOuIgualAZero(){        
        assertNull(Grafo.grafoCompleto(-4));
    }
    @Test
    public void deveCriarumGrafoCompletoDaClasse(){        
        GrafoCompleto grafoT = new GrafoCompleto(4);
        assertTrue(grafoT.completo());
    }
    @Test
    public void deveRetornarTrueCasoGrafoCompletoSejaGeradoComSucesso(){
        grafo = Grafo.grafoCompleto(6);
        assertTrue(grafo.completo());
    }

    @Test
    public void deveGerarGrafoComNomeBaseCasoNaoSejaInformadoPeloUsuario(){
        Grafo grafoNomeVazio = new Grafo("");
        assertEquals(grafoNomeVazio.nome(), "Grafo");
    }

    @Test
    public void deveGerarGrafoComNomeInformadoPeloUsuario(){
        assertEquals(grafo.nome(), "Meu Grafo Para Testes");
    }

    @Test
    public void deveRetornarOrdemCorreta(){
        meuGrafo.addVertice(1);
        meuGrafo.addVertice(2);

        assertEquals(2, meuGrafo.ordem());
    }

    @Test
    public void deveRetornarFalsoCasoTenteAdicionarArestaQueJaExista(){
        meuGrafo.addVertice(4);
        meuGrafo.addVertice(5);
        meuGrafo.addAresta(4,5,1);

        assertFalse(meuGrafo.addAresta(4,5,5));
    }

    @Test
    public void deveRetornarFalsoCasoTenteAdicionarArestaEmUmVerticeInexistente(){
        meuGrafo.addVertice(5);
        assertFalse(meuGrafo.addAresta(4,5,5));
    }

   @Test
    public void deveRetornarTrueCasoTenteAdicionarNovaAresta(){
        meuGrafo.addVertice(2);
        meuGrafo.addVertice(3);
        assertTrue(meuGrafo.addAresta(2,3,1));
    }

    @Test
    public void deveRetornarFalsoCasoTenteAdicionarVerticeComMesmoId(){
        meuGrafo.addVertice(1);
        assertFalse(meuGrafo.addVertice(1));
    }

    @Test 
    public void deveAdicionarUmVerticeComIdUnico() {        
        assertTrue(meuGrafo.addVertice(9));
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
    public void deveRemoverVertice(){
        meuGrafo.addVertice(1);
        meuGrafo.addVertice(2);
        meuGrafo.removeVertice(2);
        assertNull(meuGrafo.existeVertice(2));
    }
    
    @Test
    public void deveRemoverAresta(){
        meuGrafo.addVertice(1);
        meuGrafo.addVertice(2);
        meuGrafo.addAresta(1,2, 0);
        meuGrafo.removeAresta(1,2);
        assertNull(meuGrafo.existeAresta(1,2));
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
        meuGrafo.addVertice(4);
        meuGrafo.addAresta(1, 2, 0);
        meuGrafo.addAresta(1, 4, 0);
        meuGrafo.addAresta(2, 3, 0);
        meuGrafo.addAresta(2, 4, 0);
    
        meuGrafo = (GrafoMutavel) meuGrafo.subGrafo(minhaLista);

        assertNotNull(meuGrafo.existeVertice(1));
        assertNotNull(meuGrafo.existeVertice(2));
        assertNotNull(meuGrafo.existeVertice(3));

        assertNotNull(meuGrafo.existeAresta(1, 2));
        assertNotNull(meuGrafo.existeAresta(2, 3));
    }
    @Test
    public void calculaTamanho(){
        meuGrafo.addVertice(1);
        meuGrafo.addVertice(2);
        meuGrafo.addVertice(3);

        meuGrafo.addAresta(1,2, 0);
        meuGrafo.addAresta(1,3, 0);

        assertEquals(5, meuGrafo.tamanho());
    }
    @Test   
    public void buscaDFS(){
        GrafoMutavel grafoB = new GrafoMutavel("4");
        GrafoMutavel grafoF = new GrafoMutavel("4");

        grafoB.addVertice(1);
        grafoB.addVertice(2);
        grafoB.addVertice(3);

        grafoB.addAresta(1, 2, 0);
        grafoB.addAresta(2, 3, 0);
        grafoF = (GrafoMutavel) grafoB.dfs(2);

        assertNotNull(grafoF.existeAresta(1, 2));
    }

    @Test
    public void retornaGraudoVertice3(){
        meuGrafo.addVertice(1);
        meuGrafo.addVertice(2);
        meuGrafo.addVertice(3);
        meuGrafo.addVertice(4);

        meuGrafo.addAresta(1, 2, 0);
        meuGrafo.addAresta(1, 3, 0);
        meuGrafo.addAresta(1, 4, 0);

        assertEquals(3, meuGrafo.existeVertice(1).grau());


    } 
    
}
