package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jogo {
    private final Tabuleiro tabuleiro;
    private final JogoRegras regras;
    private final boolean debug;

    public Jogo(Tabuleiro tabuleiro, JogoRegras regras, boolean debug) {
        this.tabuleiro = tabuleiro;
        this.regras = regras;
        this.debug = debug;
    }

    public void loopDoJogo() {
        Scanner sc = new Scanner(System.in);
        boolean jogoAtivo = true;

        while (jogoAtivo) {
            Jogador atual = tabuleiro.jogadorAtual();

            mostrarTabuleiro();

            if (tratarPularTurno(atual)) {
                tabuleiro.proximoTurno();
                continue;
            }

            aguardarEnter(sc, atual);

            if (debug) {
                moverDebug(sc, atual);
            } else {
                moverNormal(atual);
            }

            mostrarTabuleiro();

            String evento = regras.aplicarRegrasEspeciais(atual);
            if ("ESCOLHER_JOGADOR".equals(evento)) {
                tratarEventoEscolherJogador(sc, atual);
            } else if (!evento.isEmpty()) {
                System.out.println(evento);
            }

            if (anunciarVencedor(atual)) {
                jogoAtivo = false;
            } else {
                tabuleiro.proximoTurno();
            }
        }

        sc.close();
    }

    private void mostrarTabuleiro() {
        System.out.println(DesenharTabuleiro.desenhar(tabuleiro.jogadores()));
    }

    private boolean tratarPularTurno(Jogador atual) {
        if (atual.isPulaProximaRodada()) {
            System.out.println(atual.getNome() + " vai pular esta rodada!");
            atual.setPulaProximaRodada(false);
            return true;
        }
        return false;
    }

    private void aguardarEnter(Scanner sc, Jogador atual) {
        System.out.println();
        System.out.println("Vez de " + atual.getNome() + " (" + atual.getTipo() + ") - Casa " + atual.getPosicao());
        System.out.print("Pressione ENTER para jogar...");
        sc.nextLine();
    }

    private void moverDebug(Scanner sc, Jogador atual) {
        System.out.print("Informe a casa para onde deseja mover (0 a " + Tabuleiro.CASA_FINAL + "): ");

        if (!sc.hasNextInt()) {
            sc.nextLine(); 
            System.out.println("Entrada inválida. Mantendo posição atual.");
            return;
        }

        int novaPosicao = sc.nextInt();
        sc.nextLine();

        if (novaPosicao < 0) novaPosicao = 0;
        if (novaPosicao > Tabuleiro.CASA_FINAL) novaPosicao = Tabuleiro.CASA_FINAL;

        atual.setPosicao(novaPosicao);
        System.out.println("(DEBUG) " + atual.getNome() + " foi movido para a casa " + novaPosicao + ".");
    }
    
    private void moverNormal(Jogador atual) {
        int soma = atual.rolarDados();
        atual.mover(soma);
        System.out.println(atual.getNome() + " tirou e avançou " + soma + " casas.");
        if (atual.getPosicao() > Tabuleiro.CASA_FINAL) {
            atual.setPosicao(Tabuleiro.CASA_FINAL);
        }
    }

    private void tratarEventoEscolherJogador(Scanner sc, Jogador atual) {
        List<Jogador> lista = new ArrayList<>(tabuleiro.jogadores());
        List<Jogador> oponentes = new ArrayList<>();
        System.out.println("\n" + atual.getNome() + " pode escolher alguém para voltar ao início!");
        for (Jogador j : lista) {
            if (j != atual) {
                oponentes.add(j);
                System.out.println("- " + j.getNome() + " (" + j.getCor() + "), casa " + j.getPosicao());
            }
        }

        Jogador escolhido = null;
        while (escolhido == null) {
            System.out.print("Digite o nome do jogador que deseja mandar ao início: ");
            String nomeEscolhido = sc.nextLine();

            for (Jogador j : oponentes) {
                if (j.getNome().equalsIgnoreCase(nomeEscolhido)) {
                    escolhido = j;
                    break;
                }
            }
            if (escolhido == null) {
                System.out.println("Nome inválido. Tente novamente.");
            }
        }

        escolhido.setPosicao(0);
        System.out.println(escolhido.getNome() + " foi mandado de volta ao início!");
    }

    private boolean anunciarVencedor(Jogador atual) {
        if (tabuleiro.verificarVencedor(atual)) {
            mostrarTabuleiro(); 
            System.out.println("Parabéns! " + atual.getNome() + " é o vencedor!");
            return true;
        }
        return false;
    }
}
