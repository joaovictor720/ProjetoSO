import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FCFS {

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
            //System.out.println("tempoAtual = " + tempoAtual);
        }
        return this.fila;
    }

    public void printarMedias() {
        int totalRetorno = 0, totalResposta = 0, totalEspera = 0;
        for (Processo p : fila) {
            //System.out.println("ret = " + p.tempoDeRetorno);
            //System.out.println("res = " + p.tempoDeResposta);
            //System.out.println("esp = " + p.tempoDeEspera);

            totalRetorno += p.tempoDeRetorno;
            totalResposta += p.tempoDeResposta;
            totalEspera += p.tempoDeEspera;
        }
        System.out.println("FCFS " + ((float) totalRetorno) / fila.size() + " " + ((float) totalResposta) / fila.size() + " " + ((float)totalEspera) / fila.size());
    }
    
}
