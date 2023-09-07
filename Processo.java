public class Processo {
    public int tempoDeChegada;
    public int tempoRestante;
    public int tempoDePico;
    public int tempoDeResposta = 0;
    public int tempoDeEspera = 0;
    public int tempoDeRetorno = 0;
    public boolean foiRespondido = false;
    
    public Processo(int tempoDeChegada, int tempoDePico) {
        this.tempoDeChegada = tempoDeChegada;
        this.tempoRestante = tempoDePico;
        this.tempoDePico = tempoDePico;
    }

    public int getTempoDeChegada() {
        return tempoDeChegada;
    }

    public void setTempoDeChegada(int tempoDeChegada) {
        this.tempoDeChegada = tempoDeChegada;
    }

    public int getTempoRestante() {
        return tempoRestante;
    }

    public void setTempoRestante(int tempoRestante) {
        this.tempoRestante = tempoRestante;
    }

    public int getTempoDePico() {
        return tempoDePico;
    }

    public void setTempoDePico(int tempoDePico) {
        this.tempoDePico = tempoDePico;
    }

    public int getTempoDeResposta() {
        return tempoDeResposta;
    }

    public void setTempoDeResposta(int tempoDeResposta) {
        this.tempoDeResposta = tempoDeResposta;
    }

    public int getTempoDeEspera() {
        return tempoDeEspera;
    }

    public void setTempoDeEspera(int tempoDeEspera) {
        this.tempoDeEspera = tempoDeEspera;
    }

    public int getTempoDeRetorno() {
        return tempoDeRetorno;
    }

    public void setTempoDeRetorno(int tempoDeRetorno) {
        this.tempoDeRetorno = tempoDeRetorno;
    }

    
}
