package entities;

import java.util.List;

public class Tabuleiro {
    private final List<Jogador> jogadores;
    private int turno;

    public static final int CASA_FINAL = 40;

    public Tabuleiro(List<Jogador> jogadores) {
        this.jogadores = jogadores;
        this.turno = 0;
    }

    // De quem é a vez de jogar
    public Jogador jogadorAtual() {
        return jogadores.get(turno % jogadores.size());
    }

    public void proximoTurno() {
        turno++;
    }

    // Verifica vencedor (>= para segurança)
    public boolean verificarVencedor(Jogador jogador) {
        return jogador.getPosicao() >= CASA_FINAL;
    }

    public List<Jogador> jogadores() {
        return jogadores;
    }

    public int getTurno() {
        return turno;
    }
}
