package entities;

import java.util.List;

public class DesenharTabuleiro {

    public static String desenhar(List<Jogador> jogadores) {
        StringBuilder sb = new StringBuilder();

        int casasPorLinha = 10;
        int totalCasas = 40;
        
        sb.append("\n============================================================\n");
        sb.append(String.format("%35s%n", " TABULEIRO"));
        sb.append("============================================================\n");

        sb.append("Casa inicial (0): [");
        sb.append(jogadoresNaCasa(jogadores, 0));
        sb.append("]\n\n");

        for (int inicio = 1; inicio <= totalCasas; inicio += casasPorLinha) {
            int fim = inicio + casasPorLinha - 1;

            sb.append(gerarLinhaDeCasas(jogadores, inicio, fim));
            sb.append(gerarLinhaDeNumeros(inicio, fim));
            sb.append("\n");
        }

        sb.append("============================================================\n");
        return sb.toString();
    }

    private static String jogadoresNaCasa(List<Jogador> jogadores, int casa) {
        StringBuilder sb = new StringBuilder();
        for (Jogador p : jogadores) {
            if (p.getPosicao() == casa) {
                sb.append(Character.toUpperCase(p.getCor().charAt(0)));
            }
        }
        return sb.toString();
    }

    private static String gerarLinhaDeCasas(List<Jogador> jogadores, int inicio, int fim) {
        StringBuilder sb = new StringBuilder();
        for (int i = inicio; i <= fim; i++) {
            String dentro = jogadoresNaCasa(jogadores, i);
            String conteudo = dentro;
            if (conteudo.isEmpty()) {
                conteudo = " ";
            }
            
            sb.append("[").append(conteudo).append("] ");
        }
        
        sb.append("\n");
        return sb.toString();
    }

    private static String gerarLinhaDeNumeros(int inicio, int fim) {
        StringBuilder sb = new StringBuilder();
        for (int i = inicio; i <= fim; i++) {
            sb.append(String.format("%3d ", i));
        }
        sb.append("\n");
        return sb.toString();
    }
}
