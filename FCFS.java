import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FCFS {

    public List<Processo> fila;

    public FCFS(List<Processo> processos) {
        Comparator<Processo> comparador = Comparator.comparingInt(Processo::getTempoDeChegada);
        Collections.sort(processos, comparador);
        this.fila = processos;
    }

    public void rodar() {
        int tempoAtual = 0;
        for (Processo p : fila) {
            p.tempoDeResposta = tempoAtual - p.tempoDeChegada;
            p.tempoDeEspera = tempoAtual - p.tempoDeChegada;
            p.tempoDeRetorno = p.tempoDeEspera + p.tempoDePico;
            tempoAtual += p.tempoDePico;
        }
    }

    public void printarMedias() {
        int totalRetorno = 0, totalResposta = 0, totalEspera = 0;
        for (Processo p : fila) {
            totalRetorno += p.tempoDeRetorno;
            totalResposta += p.tempoDeResposta;
            totalEspera += p.tempoDeEspera;
        }
        System.out.println("FCFS " + ((float) totalRetorno) / fila.size() + " " + totalResposta/fila.size() + " " + totalEspera/fila.size());
    }
    
}
