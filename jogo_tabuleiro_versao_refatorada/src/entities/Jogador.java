package entities;

public abstract class Jogador {
    protected String nome;
    protected String cor;
    protected int posicao;
    protected int jogadas;
    protected boolean pulaProximaRodada;
    protected TipoJogador tipo;
    protected Dados dados;

    public Jogador(String nome, String cor) {
        this.nome = nome;
        this.cor = cor;
        this.posicao = 0;
        this.jogadas = 0;
        this.tipo = TipoJogador.NORMAL;
        this.pulaProximaRodada = false;
        this.dados = new Dados();
    }

    public String getNome() {
        return nome;
    }

    public String getCor() {
        return cor;
    }

    public int getPosicao() {
        return posicao;
    }

    public int getJogadas() {
        return jogadas;
    }

    public TipoJogador getTipo() {
        return tipo;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public boolean isPulaProximaRodada() {
        return pulaProximaRodada;
    }

    public void setPulaProximaRodada(boolean pula) {
        this.pulaProximaRodada = pula;
    }

    public void mover(int somaDeDados) {
        this.posicao += somaDeDados;
        jogadas++;
    }

    public void mudarTipo(TipoJogador novoTipo) {
        this.tipo = novoTipo;
    }

    public abstract int rolarDados();

    public abstract boolean azarado();
}

