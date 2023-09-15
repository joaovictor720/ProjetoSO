import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RR extends AlgoritmoDeEscalonamento {

    public RR(List<Processo> processos) {
        Comparator<Processo> comparadorPorTempoDeChegada = Comparator.comparingInt(Processo::getTempoDeChegada);
        Collections.sort(processos, comparadorPorTempoDeChegada);
        this.processosDisponiveis = processos;
    }

    public List<Processo> rodar() {
        
        return null;
    }
}
