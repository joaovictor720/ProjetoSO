import java.util.List;

public abstract class AlgoritmoDeEscalonamento {
    List<Processo> processosDisponiveis;

    abstract public List<Processo> rodar();

    public void printarMedias(List<Processo> processos, String nomeDoAlgoritmo) {
        double totalRetorno = 0, totalResposta = 0, totalEspera = 0;
        for (Processo p : processos) {
            //System.out.println("p " + p.tempoDeChegada + " " + p.tempoDePico);
            //System.out.println("ret = " + p.tempoDeRetorno);
            //System.out.println("res = " + p.tempoDeResposta);
            //System.out.println("esp = " + p.tempoDeEspera);

            totalRetorno += p.tempoDeRetorno;
            totalResposta += p.tempoDeResposta;
            totalEspera += p.tempoDeEspera;
        }
        String mediaRetorno = String.format("%,.1f", totalRetorno/processos.size());
        String medioResposta = String.format("%,.1f", totalResposta/processos.size());
        String mediaEspera = String.format("%,.1f", totalEspera/processos.size());

        System.out.println(nomeDoAlgoritmo + " " + mediaRetorno + " " + medioResposta + " " + mediaEspera);
    }
}
