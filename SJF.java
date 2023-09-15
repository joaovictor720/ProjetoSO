import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class SJF extends AlgoritmoDeEscalonamento {

    public SJF(List<Processo> processos) {
        Comparator<Processo> comparador = Comparator
        .comparingInt(Processo::getTempoDeChegada)
        .thenComparingInt(Processo::getTempoDePico);
        
        // Ordenando pelo tempo de chegada, e então pelo tempo de pico
        Collections.sort(processos, comparador);

        this.processosDisponiveis = processos;
    }
    
    public List<Processo> rodar() {
        int tempoAtual = 0;
        List<Processo> processosRestantes = new LinkedList<>(this.processosDisponiveis);
        List<Processo> ordemDeExecucao = new ArrayList<>();
        
        while (!processosRestantes.isEmpty()) {
            Queue<Processo> processosProntos = new PriorityQueue<>(Comparator.comparingInt(Processo::getTempoDePico));
            for (Processo p : processosRestantes) {
                if (p.tempoDeChegada <= tempoAtual) {
                    processosProntos.add(p);
                } else {
                    break;
                }
            }
            Processo menorProcesso = processosProntos.poll();
            if (menorProcesso == null) {
                tempoAtual++;
                continue;
            }
            processosRestantes.remove(menorProcesso);
            menorProcesso.tempoDeResposta = tempoAtual - menorProcesso.tempoDeChegada;
            menorProcesso.tempoDeEspera = tempoAtual - menorProcesso.tempoDeChegada;
            menorProcesso.tempoDeRetorno = menorProcesso.tempoDeEspera + menorProcesso.tempoDePico;
            ordemDeExecucao.add(menorProcesso);
            tempoAtual += menorProcesso.tempoDePico;
        }
        return ordemDeExecucao;
    }
}
