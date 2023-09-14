import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FCFS extends AlgoritmoDeEscalonamento {

    public List<Processo> fila;

    public FCFS(List<Processo> processos) {
        Comparator<Processo> comparadorPorTempoDeChegada = Comparator.comparingInt(Processo::getTempoDeChegada);
        Collections.sort(processos, comparadorPorTempoDeChegada);
        this.fila = processos;
    }

    public List<Processo> rodar() {
        int tempoAtual = 0;
        for (Processo p : fila) {
            p.tempoDeResposta = tempoAtual - p.tempoDeChegada;
            p.tempoDeEspera = tempoAtual - p.tempoDeChegada;
            p.tempoDeRetorno = p.tempoDeEspera + p.tempoDePico;
            tempoAtual += p.tempoDePico;
        }
        return this.fila;
    }
}
