package entities;

public class JogadorNormal extends Jogador {

    public JogadorNormal(String nome, String cor) {
        super(nome, cor);
        this.tipo = TipoJogador.NORMAL;
    }

    @Override
    public int rolarDados() {
        return dados.rolarDoisDados();
    }

    @Override
    public boolean azarado() {
        return false;
    }
}

