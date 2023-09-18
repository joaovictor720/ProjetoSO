import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RR extends AlgoritmoDeEscalonamento {

    private int quantum;

    public RR(List<Processo> processos, int quantum) {
        Comparator<Processo> comparadorPorTempoDeChegada = Comparator.comparingInt(Processo::getTempoDeChegada);
        
        // Ordenando, pelo tempo de chegada, os processos que serão simulados
        Collections.sort(processos, comparadorPorTempoDeChegada);
        this.processosDisponiveis = processos;
        this.quantum = quantum;
    }

    public List<Processo> rodar() {
        int tempoAtual = 0;
        List<Processo> processosRestantes = this.processosDisponiveis;
        List<Processo> processosExecutados = new ArrayList<>();
        Queue<Processo> filaDeProntos = new LinkedList<>();
        Processo temp = null;

        // Enquanto houver processos que ainda vão chegar na fila de prontos, ou chegaram e não terminaram
        while (!processosRestantes.isEmpty() || !filaDeProntos.isEmpty()) {
            // Tirando da fila de restantes (que não estão no sistema), e colocando na fila de prontos
            for (int i = 0; i < processosRestantes.size(); ++i) {
                temp = processosRestantes.get(i);
                if (temp.tempoDeChegada <= tempoAtual) {
                    filaDeProntos.add(temp); // Colocando no final da fila de prontos
                    processosRestantes.remove(temp); // E tirando da lista de processos que não chegaram
                    --i;
                } else {
                    // A lista já está ordenada em relação aos tempos de pico, então se entrou aqui,
                    // os seguintes terão chegado garantidamente depois do tempo atual
                    break;
                }
            }
            // Pegando o próximo da fila de prontos para alocar na CPU
            Processo primeiroDaFila = filaDeProntos.poll();
            
            // Se nenhum processo tiver chegado até o tempo atual (fila estiver vazia por enquanto), passar o tempo
            if (primeiroDaFila == null) {
                ++tempoAtual;
                continue;
            }

            // Somando o tempo de resposta apenas na primeira vez que o processo está sendo alocado
            if (!primeiroDaFila.foiAlocado) {
                primeiroDaFila.tempoDeResposta = tempoAtual - primeiroDaFila.tempoDeChegada;
                primeiroDaFila.foiAlocado = true;
            }

            // Somando o último tempo de espera
            primeiroDaFila.tempoDeEspera += tempoAtual - primeiroDaFila.tempoDeChegadaNaFila;
            
            // Simulando o gasto de tempo na alocação do processo atual (primeiroDaFila)
            int tempoGasto = Math.min(this.quantum, primeiroDaFila.tempoRestante);
            primeiroDaFila.tempoRestante -= tempoGasto;
            tempoAtual += tempoGasto;
            
            if (primeiroDaFila.tempoRestante == 0) {
                // Processo terminou
                primeiroDaFila.tempoDeRetorno = tempoAtual - primeiroDaFila.tempoDeChegada;
                processosExecutados.add(primeiroDaFila);
            } else {
                primeiroDaFila.tempoDeChegadaNaFila = tempoAtual;
                // Juntando todos os processos que possivelmente tenham chegado depois da "execução" do processo atual
                for (int i = 0; i < processosRestantes.size(); ++i) {
                    temp = processosRestantes.get(i);
                    if (temp.tempoDeChegada <= tempoAtual) {
                        filaDeProntos.add(temp);
                        processosRestantes.remove(temp);
                        --i;
                    } else {
                        break;
                    }
                }
                // Mandando pra o processo que foi alocado agora para o final da fila de prontos
                filaDeProntos.add(primeiroDaFila);
            }
        }
        return processosExecutados;
    }
}
