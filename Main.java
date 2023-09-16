import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

    public static int RR_QUANTUM = 2;
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Passe o nome do arquivo de entrada");
            return;
        }
        BufferedReader leitor = new BufferedReader(new FileReader(args[0]));
        String temp;
        ArrayList<Processo> processos = new ArrayList<>();
        
        int i = 1;
        while ((temp = leitor.readLine()) != null) {
            String[] dados = temp.split(" ");
            processos.add(new Processo(Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), "P"+ (i++)));
        }
        
        FCFS fcfs = new FCFS(processos);
        fcfs.printarMedias(fcfs.rodar(), "FCFS");

        SJF sjf = new SJF(processos);
        sjf.printarMedias(sjf.rodar(), "SJF");

        RR rr = new RR(processos, RR_QUANTUM);
        rr.printarMedias(rr.rodar(), "RR");

        leitor.close();
    }
}