import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArqCriar {

    public static void criar(String nomeArquivo, ABB<Vertice> vertices) throws FileNotFoundException {
        File file = new File("./codigo/projeto2-grafos/arquivos/" + nomeArquivo);
        Scanner entrada = new Scanner(file, "UTF-8");
    }

}
