package entities;

import java.util.List;

public class DesenharTabuleiro {

    public static String render(List<Jogador> jogadores) {
        StringBuilder sb = new StringBuilder();
        int totalDeCasas = 40;
        int casasPorLinha = 10;
        int linhas = totalDeCasas / casasPorLinha; // 4 linhas

        sb.append("\n============================================================\n");
        sb.append(String.format("%35s%n", " TABULEIRO"));
        sb.append("============================================================\n");

        // Casa inicial
        sb.append("Casa inicial (0): [");
        StringBuilder casaInicial = new StringBuilder();
        for (Jogador p : jogadores) {
            if (p.getPosicao() == 0) {
                casaInicial.append(Character.toUpperCase(p.getCor().charAt(0)));
            }
        }
        casaInicial.append("]");
        sb.append(casaInicial.toString()).append("\n\n");

        for (int linha = 0; linha < linhas; linha++) {
            int inicio = linha * casasPorLinha + 1;
            int fim = inicio + casasPorLinha - 1;

            for (int i = inicio; i <= fim; i++) {
                StringBuilder simbolos = new StringBuilder();
                for (Jogador p : jogadores) {
                    if (p.getPosicao() == i) {
                        simbolos.append(Character.toUpperCase(p.getCor().charAt(0)));
                    }
                }
                if (simbolos.length() == 0) {
                    sb.append("[ ] ");
                } else {
                    sb.append("[").append(simbolos).append("] ");
                }
            }
            sb.append("\n");
            for (int i = inicio; i <= fim; i++) {
                sb.append(String.format("%3d ", i));
            }
            sb.append("\n\n");
        }

        sb.append("============================================================\n");
        return sb.toString();
    }
}

