import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SJF {
    public List<Processo> fila;

    public SJF(List<Processo> processos) {
        Comparator<Processo> comparadorPorTempoDeChegada = Comparator.comparingInt(Processo::getTempoDeChegada);
        // Ordenando pelo tempo de chegada
        Collections.sort(processos, comparadorPorTempoDeChegada);

        /**
         * TODO: Ordenar pelos tempos de pico, com respeito aos tempos de chegada
         */

        this.fila = processos;
    }
}
