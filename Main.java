import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader leitor = new BufferedReader(new FileReader(args[0]));
        String temp;
        ArrayList<Processo> processos = new ArrayList<>();
        
        while ((temp = leitor.readLine()) != null) {
            String[] dados = temp.split(" ");
            processos.add(new Processo(Integer.parseInt(dados[0]), Integer.parseInt(dados[1])));
        }
        

        leitor.close();
    }
}