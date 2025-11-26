package entities;

public class JogadorComSorte extends Jogador {
    
	public JogadorComSorte(String nome, String cor) {
        super(nome, cor);
        this.tipo = TipoJogador.SORTUDO;
    }

    @Override
    public int rolarDados() {
        int soma = dados.rolarDoisDados();
        if (soma < 7) {
        	soma = 7;
        }
        return soma;
    }

    @Override
    public boolean azarado() {
        return false;
    }
}
