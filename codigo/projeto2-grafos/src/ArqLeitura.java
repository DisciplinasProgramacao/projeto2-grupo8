import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class ArqLeitura {
        Scanner entrada;
    
	ArqLeitura(String nomeArquivo) throws FileNotFoundException {	
        File file = new File("./codigo/projeto2-grafos/arquivos/" + nomeArquivo);
        entrada = new Scanner(file, "UTF-8");

	}

	public void fecharArquivo() throws FileNotFoundException {
		entrada.close();
	}

	@SuppressWarnings("finally")
	public String ler() throws FileNotFoundException, EOFException {

		String textoEntrada = null;

		try {
			textoEntrada = entrada.nextLine();
            System.out.println(textoEntrada);
		}
		finally {
			return textoEntrada;
		}
	}
}