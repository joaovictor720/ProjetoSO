import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class RR extends AlgoritmoDeEscalonamento {

    private int quantum;

    public RR(List<Processo> processos, int quantum) {
        Comparator<Processo> comparadorPorTempoDeChegada = Comparator.comparingInt(Processo::getTempoDeChegada);
        Collections.sort(processos, comparadorPorTempoDeChegada);
        this.processosDisponiveis = processos;
        this.quantum = quantum;
    }

    public List<Processo> rodar() {
        int tempoAtual = 0;
        List<Processo> processosRestantes = new ArrayList<>(this.processosDisponiveis);
        List<Processo> processosExecutados = new ArrayList<>();
        Queue<Processo> filaDeProntos = new ArrayBlockingQueue<>(processosRestantes.size());
        Processo p = null;
        
        // Enquanto processosRestantes e "filaReal" não estiverem vazias
        while (!processosRestantes.isEmpty() || !filaDeProntos.isEmpty()) {
            // Tirando da fila de restantes (que não estão no sistema), e colocando na fila de prontos
            for (int i = 0; i < processosRestantes.size(); ++i) {
                p = processosRestantes.get(i);
                if (p.tempoDeChegada <= tempoAtual) {
                    System.out.println("adicionando " + p.toString() + "(tirando dos restantes)");
                    filaDeProntos.add(p);
                    processosRestantes.remove(p);
                } else {
                    break;
                }
            }
            Processo primeiroDaFila = filaDeProntos.poll();
            if (primeiroDaFila == null) {
                //tempoAtual += this.quantum;
                tempoAtual++;
                continue;
            }
            //System.out.println("executando o " + primeiroDaFila.toString());
            if (!primeiroDaFila.foiAlocado) {
                primeiroDaFila.tempoDeResposta = tempoAtual - primeiroDaFila.tempoDeChegada;
                primeiroDaFila.foiAlocado = true;
                //System.out.println("tResposta de " + primeiroDaFila.toString() + ": " + primeiroDaFila.tempoDeResposta + " no tempo " + tempoAtual);
            }

            primeiroDaFila.tempoDeEspera += tempoAtual - primeiroDaFila.tempoDeChegadaNaFila;
            
            // Simulando o gasto de tempo na vez do primeiro da fila
            int tempoGasto = Math.min(this.quantum, primeiroDaFila.tempoRestante);
            primeiroDaFila.tempoRestante -= tempoGasto;
            tempoAtual += tempoGasto;
            
            if (primeiroDaFila.tempoRestante == 0) {
                // Processo terminou
                primeiroDaFila.tempoDeRetorno = primeiroDaFila.tempoDeEspera + primeiroDaFila.tempoDePico;
                //processosRestantes.remove(primeiroDaFila);
                processosExecutados.add(primeiroDaFila);
            } else {
                primeiroDaFila.tempoDeChegadaNaFila = tempoAtual;
                // Juntando todos os processos que possivelmente tenham chegado depois da execução do processo atual
                for (int i = 0; i < processosRestantes.size(); ++i) {
                    p = processosRestantes.get(i);
                    if (p.tempoDeChegada <= tempoAtual) {
                        System.out.println("adicionando " + p.toString() + "(tirando dos restantes embaixo)");
                        filaDeProntos.add(p);
                        processosRestantes.remove(p);
                    } else {
                        break;
                    }
                }
                System.out.println("adicionando " + p.toString());
                // Mandando pra o final da fila
                filaDeProntos.add(primeiroDaFila);
                //processosRestantes.remove(primeiroDaFila);
                //processosRestantes.add(primeiroDaFila);
                //System.out.println("mandando " + primeiroDaFila.toString() + " pro final da fila em " + tempoAtual);
            }
        }
        return processosExecutados;
    }
}
