package entities;

import java.util.List;
import java.util.Random;

public class JogoRegras {
    private final List<Jogador> jogadores;
    private final Random random;

    public JogoRegras(List<Jogador> jogadores) {
        this.jogadores = jogadores;
        this.random = new Random();
    }

    public String aplicarRegrasEspeciais(Jogador jogador) {
        int pos = jogador.getPosicao();

        if (isCasaDaSorte(pos)) {
            return aplicarCasaDaSorte(jogador);
        } else if (isCasaPula(pos)) {
            jogador.setPulaProximaRodada(true);
            return jogador.getNome() + " caiu em uma casa especial e perderá a próxima rodada!";
        } else if (pos == 13) {
            return aplicarCasaSurpresa(jogador);
        } else if (pos == 17 || pos == 27) {
            return "ESCOLHER_JOGADOR";
        } else if (pos == 20 || pos == 35) {
            return trocarComMaisAtras(jogador);
        }

        return "";
    }

    private boolean isCasaDaSorte(int pos) {
        return pos == 5 || pos == 15 || pos == 30;
    }

    private boolean isCasaPula(int pos) {
        return pos == 10 || pos == 25 || pos == 38;
    }

    private String aplicarCasaDaSorte(Jogador jogador) {
        if (jogador.getTipo() == TipoJogador.AZARADO) {
            return "Que pena! " + jogador.getNome() + " é azarado e não pode avançar na casa da sorte. Continua na casa " + jogador.getPosicao() + ".";
        } else {
            jogador.mover(3);
            return "Que sorte! " + jogador.getNome() + " caiu em uma casa da sorte e avançou 3 casas!";
        }
    }

    private String aplicarCasaSurpresa(Jogador jogador) {
        int tipo = random.nextInt(3);
        switch (tipo) {
            case 0: jogador.mudarTipo(TipoJogador.NORMAL); break;
            case 1: jogador.mudarTipo(TipoJogador.AZARADO); break;
            default: jogador.mudarTipo(TipoJogador.SORTUDO); break;
        }
        return jogador.getNome() + " caiu na Casa Surpresa! Agora é um jogador " + jogador.getTipo().toString() + ".";
    }

    private String trocarComMaisAtras(Jogador jogador) {
        Jogador atras = jogadores.stream()
                .filter(j -> j != jogador)
                .min((a, b) -> Integer.compare(a.getPosicao(), b.getPosicao()))
                .orElse(null);

        if (atras != null && atras.getPosicao() < jogador.getPosicao()) {
            int temp = jogador.getPosicao();
            jogador.setPosicao(atras.getPosicao());
            atras.setPosicao(temp);
            return jogador.getNome() + " caiu em uma casa mágica e trocou de lugar com " + atras.getNome() + "!";
        } else {
            return jogador.getNome() + " caiu em uma casa mágica, mas já é o último";
        }
    }
}
