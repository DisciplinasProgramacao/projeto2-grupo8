public class App {
    public static void main(String[] args) throws Exception {
        ArqLeitura arquivo = new ArqLeitura("Arquivo.csv");
        arquivo.ler();
    }
}
