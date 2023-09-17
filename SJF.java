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
        List<Processo> copia = new ArrayList<>(processos);
        Collections.sort(copia, comparador);

        this.processosDisponiveis = copia;
    }
    
    public List<Processo> rodar() {
        int tempoAtual = 0;
        List<Processo> processosRestantes = new LinkedList<>(this.processosDisponiveis);
        List<Processo> ordemDeExecucao = new ArrayList<>();
        
        while (!processosRestantes.isEmpty()) {
            Queue<Processo> processosProntos = new PriorityQueue<>(Comparator.comparingInt(Processo::getTempoDePico));
            // Juntando todos os processos que chegaram até o tempo atual, e ordenando pelo tempo de 
            // pico (fila de prioridade em relação ao tempo de restante)
            for (Processo p : processosRestantes) {
                if (p.tempoDeChegada <= tempoAtual) {
                    processosProntos.add(p);
                } else {
                    // A lista já está ordenada em relação aos tempos de chegada, então se entrou 
                    // aqui, os seguintes terão chegado garantidamente depois do tempo atual
                    break;
                }
            }
            // Pegando o menor processo, que estará na frente da fila de prioridade
            Processo menorProcesso = processosProntos.poll();
            if (menorProcesso == null) {
                tempoAtual++;
                continue;
            }
            processosRestantes.remove(menorProcesso);

            // Análogo ao FCFS
            menorProcesso.tempoDeResposta = tempoAtual - menorProcesso.tempoDeChegada;
            menorProcesso.tempoDeEspera = tempoAtual - menorProcesso.tempoDeChegada;
            menorProcesso.tempoDeRetorno = menorProcesso.tempoDeEspera + menorProcesso.tempoDePico;
            ordemDeExecucao.add(menorProcesso);
            tempoAtual += menorProcesso.tempoDePico;
        }
        return ordemDeExecucao;
    }
}
