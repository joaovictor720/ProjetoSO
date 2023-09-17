import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FCFS extends AlgoritmoDeEscalonamento {

    public FCFS(List<Processo> processos) {
        Comparator<Processo> comparadorPorTempoDeChegada = Comparator.comparingInt(Processo::getTempoDeChegada);

        // Ordenando, pelo tempo de chegada, os processos que serão simulados
        Collections.sort(processos, comparadorPorTempoDeChegada);
        this.processosDisponiveis = processos;
    }

    public List<Processo> rodar() {
        int tempoAtual = 0;
        for (Processo p : this.processosDisponiveis) {
            p.tempoDeResposta = tempoAtual - p.tempoDeChegada;
            p.tempoDeEspera = tempoAtual - p.tempoDeChegada;
            p.tempoDeRetorno = p.tempoDeEspera + p.tempoDePico;
            tempoAtual += p.tempoDePico;
        }
        return this.processosDisponiveis;
    }
}
