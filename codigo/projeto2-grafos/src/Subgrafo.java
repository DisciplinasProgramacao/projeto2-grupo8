import static java.lang.Integer.*;

public class Subgrafo{
    private int vertices [];

    
    public Subgrafo(String v){

        String[] individual = v.split(",|;|\s");

        for (int i = 0; i < individual.length(); i++){
            vertices[i] = Integer.parseInt(individual[i]);
        }

    }

    void exibeSubGrafo(){

        for (int i = 0; i < vertices.length(); i++){
            System.out.println(vertices[i]);
        }

    }
    public void criaSubGrafo(int vertices[]){


    }
}