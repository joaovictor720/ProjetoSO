import java.util.List;

public abstract class AlgoritmoDeEscalonamento {
    List<Processo> fila;

    abstract public List<Processo> rodar();

    public void printarMedias(List<Processo> processos, String nomeDoAlgoritmo) {
        int totalRetorno = 0, totalResposta = 0, totalEspera = 0;
        for (Processo p : processos) {
            //System.out.println("p " + p.tempoDeChegada + " " + p.tempoDePico);
            //System.out.println("ret = " + p.tempoDeRetorno);
            //System.out.println("res = " + p.tempoDeResposta);
            //System.out.println("esp = " + p.tempoDeEspera);

            totalRetorno += p.tempoDeRetorno;
            totalResposta += p.tempoDeResposta;
            totalEspera += p.tempoDeEspera;
        }
        System.out.println(nomeDoAlgoritmo + " " + ((float) totalRetorno) / processos.size() + " " + ((float) totalResposta) / processos.size() + " " + ((float)totalEspera) / processos.size());
    }
}
