package entities;

public class JogadorSemSorte extends Jogador {
    
	public JogadorSemSorte(String nome, String cor) {
        super(nome, cor);
        this.tipo = TipoJogador.AZARADO;
    }

    @Override
    public int rolarDados() {
        int soma = dados.rolarDoisDados();
        if (soma > 6) { 
        	soma = 6;
        }
        return soma;
    }

    @Override
    public boolean azarado() {
        return true;
    }
}
