public class App {
    public static void main(String[] args) throws Exception {
        ArqLeitura arquivo = new ArqLeitura("Arquivo.csv");
        
        // Caso queira retornar arresta basta digitar 1 vertice 2 e assim sucessivamente
        System.out.println(arquivo.ler(1));
    }
}
