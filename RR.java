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
        
        while (!processosRestantes.isEmpty()) {
            Queue<Processo> processosProntos = new ArrayBlockingQueue<>(processosRestantes.size());
            for (Processo p : processosRestantes) {
                if (p.tempoDeChegada <= tempoAtual) {
                    processosProntos.add(p);
                }
            }
            Processo primeiroDaFila = processosProntos.poll();
            if (primeiroDaFila == null) {
                //tempoAtual += this.quantum;
                tempoAtual++;
                continue;
            }
            
            if (!primeiroDaFila.foiAlocado) {
                primeiroDaFila.tempoDeResposta = tempoAtual - primeiroDaFila.tempoDeChegada;
                primeiroDaFila.foiAlocado = true;
                System.out.println("tResposta de " + primeiroDaFila.toString() + ": " + primeiroDaFila.tempoDeResposta + " no tempo " + tempoAtual);
            }

            primeiroDaFila.tempoDeEspera += tempoAtual - primeiroDaFila.tempoDeChegadaNaFila;
            
            // Simulando o gasto de tempo na vez do primeiro da fila
            int tempoGasto = Math.min(this.quantum, primeiroDaFila.tempoRestante);
            primeiroDaFila.tempoRestante -= tempoGasto;
            tempoAtual += tempoGasto;
            
            if (primeiroDaFila.tempoRestante == 0) {
                // processo terminou
                primeiroDaFila.tempoDeRetorno = primeiroDaFila.tempoDeEspera + primeiroDaFila.tempoDePico;
                processosRestantes.remove(primeiroDaFila); 
                processosExecutados.add(primeiroDaFila);
            } else {
                // mandando pra o final da fila
                primeiroDaFila.tempoDeChegadaNaFila = tempoAtual;
                processosRestantes.remove(primeiroDaFila);
                processosRestantes.add(primeiroDaFila);
                //System.out.println("mandando " + primeiroDaFila.toString() + " pro final da fila em " + tempoAtual);
            }
        }
        return processosExecutados;
    }
}
