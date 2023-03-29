import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ArqCriar {

    public static void criar(String nomeArquivo) throws IOException {

        FileWriter arq = new FileWriter("./codigo/projeto2-grafos/arquivos/" + nomeArquivo + ".csv");
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.write("aresta;11,12,13;");
        gravarArq.write("\nvertice;22;");

        arq.close();

    }

}
