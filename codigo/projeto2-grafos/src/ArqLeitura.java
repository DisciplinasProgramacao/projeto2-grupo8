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
	public String ler(int params) throws FileNotFoundException, EOFException {

		String []  textoArquivo = null;
		int count = 0;

		try {
			while (count != params){
				textoArquivo = null;
				textoArquivo = entrada.nextLine().split(";");
				count++;
			}
			return textoArquivo[1];
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		return null;

	}

}