import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class SJF {
    public List<Processo> fila;

    public SJF(List<Processo> processos) {
        Comparator<Processo> comparador = Comparator.comparingInt(Processo::getTempoDeChegada).thenComparingInt(Processo::getTempoDePico);
        
        // Ordenando pelo tempo de chegada, e então pelo tempo de pico
        Collections.sort(processos, comparador);

        this.fila = processos;
    }
    
    public List<Processo> rodar() {
        int tempoAtual = 0;
        List<Processo> processosRestantes = this.fila;
        ArrayList<Processo> ordemDeExecucao = new ArrayList<>();
        
        while (!processosRestantes.isEmpty()) {
            Queue<Processo> processosProntos = new PriorityQueue<>(Comparator.comparingInt(Processo::getTempoDePico));
            for (Processo p : processosRestantes) {
                if (p.tempoDeChegada <= tempoAtual) {
                    //System.out.println("chegou processo " + p.tempoDeChegada + " " + p.tempoDePico);
                    processosProntos.add(p);
                } else {
                    break;
                }
            }
            Processo menorProcesso = processosProntos.poll();
            if (menorProcesso == null) {
                //System.out.println("sem processos prontos agora");
                tempoAtual++;
                continue;
            }
            //System.out.println("menor processo = " + menorProcesso.tempoDeChegada + " " + menorProcesso.tempoDePico);
            processosRestantes.remove(menorProcesso);
            menorProcesso.tempoDeResposta = tempoAtual - menorProcesso.tempoDeChegada;
            menorProcesso.tempoDeEspera = tempoAtual - menorProcesso.tempoDeChegada;
            menorProcesso.tempoDeRetorno = menorProcesso.tempoDeEspera + menorProcesso.tempoDePico;
            tempoAtual += menorProcesso.tempoDePico;
            ordemDeExecucao.add(menorProcesso);
            //System.out.println("tempoAtual = " + tempoAtual);
        }
        for (Processo p : ordemDeExecucao) {
            System.out.println("p " + p.tempoDeChegada + " " + p.tempoDePico);
        }
        return ordemDeExecucao;
    }

    public void printarMedias(List<Processo> processos) {
        int totalRetorno = 0, totalResposta = 0, totalEspera = 0;
        for (Processo p : processos) {
            System.out.println("p " + p.tempoDeChegada + " " + p.tempoDePico);
            System.out.println("ret = " + p.tempoDeRetorno);
            System.out.println("res = " + p.tempoDeResposta);
            System.out.println("esp = " + p.tempoDeEspera);

            totalRetorno += p.tempoDeRetorno;
            totalResposta += p.tempoDeResposta;
            totalEspera += p.tempoDeEspera;
        }
        System.out.println("SJF " + ((float) totalRetorno) / processos.size() + " " + ((float) totalResposta) / processos.size() + " " + ((float)totalEspera) / processos.size());
    }
}
