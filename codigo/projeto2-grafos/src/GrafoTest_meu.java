import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



public class GrafoTest_meu {
/* 
    • Carregamento de um grafo a partir de um arquivo texto (txt, csv, json...);
• Salvamento de um grafo no mesmo formato em que se dará a leitura;
• Geração de um grafo completo;
• Geração de um subgrafo a partir da indicação dos vértices do grafo original;
• Permitir a criação de grafos com arestas ponderadas e não ponderadas.
• Busca em largura/amplitude;
• Busca em profundidade;
• Criação e execução de um sistema simples para manipulação de grafos.
  */
  
  String nomeArquivo = "";
  static Lista<Integer> lista = new Lista<Integer>();
  static Grafo completo;
  GrafoMutavel sub;
  static GrafoNaoDirecionado grafoTesteNaoDir = new GrafoNaoDirecionado("gteste");
  static GrafoDirecionado grafoTesteDirecionado = new GrafoDirecionado("gtesteDir");
 
  @BeforeAll
  public static void prepareAll(){
    lista.add(4);
    lista.add(6);
    lista.add(9);
    /*
     *                      0
     *          1    -->      2           3    
     *      4     5             8 -->       7
     */
    for (int i = 0; i <= 5; i++) {
      grafoTesteNaoDir.addVertice(i); 
      grafoTesteDirecionado.addVertice(i);
    }
    grafoTesteNaoDir.addVertice(7); 
    grafoTesteDirecionado.addVertice(7);
    grafoTesteNaoDir.addVertice(8); 
    grafoTesteDirecionado.addVertice(8);

    grafoTesteNaoDir.addAresta(0,1, 0);
    grafoTesteNaoDir.addAresta(0,2, 0);
    grafoTesteNaoDir.addAresta(0,3, 0);
    grafoTesteNaoDir.addAresta(1,2, 0);
    grafoTesteNaoDir.addAresta(1,4, 0);
    grafoTesteNaoDir.addAresta(1,5, 0);
    grafoTesteNaoDir.addAresta(2,8, 0);
    grafoTesteNaoDir.addAresta(3,7, 0);
    grafoTesteNaoDir.addAresta(8,7, 32);

    grafoTesteDirecionado.addAresta(0,1, 0);
    grafoTesteDirecionado.addAresta(0,2, 0);
    grafoTesteDirecionado.addAresta(0,3, 0);
    grafoTesteDirecionado.addAresta(1,2, 0);
    grafoTesteDirecionado.addAresta(1,4, 0);
    grafoTesteDirecionado.addAresta(1,5, 42);
    grafoTesteDirecionado.addAresta(2,8, 0);
    grafoTesteDirecionado.addAresta(3,7, 0);
    grafoTesteDirecionado.addAresta(8,7, 32);

    completo = new GrafoCompleto(10);
  }
  
  @Test
  public void criacaoEBasicos(){
    Vertice v = grafoTesteNaoDir.existeVertice(0);
    assertEquals(3,v.grau());
    v = grafoTesteNaoDir.existeVertice(8);
    assertEquals(2,v.grau());
    Aresta a = grafoTesteNaoDir.existeAresta(7, 8);
    assertNotNull(a);
    assertEquals(32, a.peso());

    Vertice vd = grafoTesteDirecionado.existeVertice(0);
    assertEquals(3,vd.grau());
    vd = grafoTesteDirecionado.existeVertice(8);
    assertEquals(1,vd.grau());
    Aresta ad= grafoTesteDirecionado.existeAresta(7, 8);
    assertNull(ad);
    ad= grafoTesteDirecionado.existeAresta(1, 5);
    assertEquals(42, ad.peso());
  }

  @Test
  public void salvamento()throws IOException{
    grafoTesteDirecionado.addVertice(77);
    grafoTesteDirecionado.addVertice(26);
    grafoTesteDirecionado.addAresta(77,26,19);
    grafoTesteDirecionado.salvar("C:\\temp\\LPM-N\\projeto2-grupo8\\codigo\\projeto2-grafos\\testeSalvar.txt");
  }

  @Test
  public void carregaGrafo() throws IOException{
    GrafoMutavel direcionado = new GrafoDirecionado("gnd");
    String nomearq = "C:\\temp\\LPM-N\\projeto2-grupo8\\codigo\\projeto2-grafos\\testeSalvar.txt";
    direcionado.carregar(nomearq);
    Vertice v = direcionado.existeVertice(2);
    assertEquals(1, v.grau());
    Aresta a = direcionado.existeAresta(77,26);
    assertEquals(19, a.peso());
  }

  @Test
  public void completo(){
    assertEquals(10,completo.ordem());
    assertEquals(55, completo.tamanho());
    
  }

  @Test
  public void subgrafo(){
    sub = (GrafoMutavel)completo.subGrafo(lista);
    assertEquals(3, sub.ordem());
    assertEquals(6, sub.tamanho());
    assertNotNull(sub.existeAresta(4, 9));
    assertNull(sub.existeAresta(4,7));
  }



  

  @Test
  public void largura(){
    /*
     *                      0
     *          1    -->      2           3    
     *      4     5             8 -->       7
     */

     Grafo g =     grafoTesteNaoDir.bfs(0);
     Vertice v = g.existeVertice(7);
     assertNull(g.existeAresta(1,2));
     assertNotNull(g.existeAresta(2,8));
     assertNull(g.existeAresta(8,7));
     assertNotNull(g.existeAresta(3,7));
  }

  @Test
  public void profundidade(){
    /*
     *                      0
     *          1    -->      2           3    
     *      4     5             8 -->       7
     */

     Grafo g =   grafoTesteNaoDir.dfs(0);
     assertNull(g.existeAresta(0,2));
     assertNotNull(g.existeAresta(3,7));
     assertNotNull(g.existeAresta(8,7));
  }

  @Test
  public void naoPodeRemoverVerticeSemExistir(){
      Vertice v = grafoTesteNaoDir.removeVertice(6);
      assertNull(v);
  }

  @Test
  public void naoPodeRemoverArestaSemExistir(){
      
      Aresta a = grafoTesteNaoDir.removeAresta(0,6);
      assertNull(a);
  }
}
