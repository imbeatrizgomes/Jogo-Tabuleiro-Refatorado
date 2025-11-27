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

            System.out.println(DesenharTabuleiro.desenhar(tabuleiro.jogadores()));

            if (atual.isPulaProximaRodada()) {
                System.out.println(atual.getNome() + " vai pular esta rodada!");
                atual.setPulaProximaRodada(false);
                tabuleiro.proximoTurno();
                continue;
            }

            System.out.println();
            System.out.println("Vez de " + atual.getNome() + " (" + atual.getTipo() + ") - Casa " + atual.getPosicao());
            System.out.print("Pressione ENTER para jogar...");
            sc.nextLine();

            if (debug) {
                System.out.print("Informe a casa para onde deseja mover (0 a " + Tabuleiro.CASA_FINAL + "): ");

                while (!sc.hasNextInt()) {
                    System.out.print("Digite um número válido: ");
                    sc.nextLine();
                }

                int novaPosicao = sc.nextInt();
                sc.nextLine();
                
                if (novaPosicao < 0) novaPosicao = 0;
                if (novaPosicao > Tabuleiro.CASA_FINAL) novaPosicao = Tabuleiro.CASA_FINAL;
                atual.setPosicao(novaPosicao);
                System.out.println("(DEBUG) " + atual.getNome() + " foi movido para a casa " + novaPosicao + ".");
            } else {
                int soma = atual.rolarDados();
                atual.mover(soma);
                System.out.println(atual.getNome() + " tirou e avançou " + soma + " casas.");
                if (atual.getPosicao() > Tabuleiro.CASA_FINAL) atual.setPosicao(Tabuleiro.CASA_FINAL);
            }
            
            System.out.println(DesenharTabuleiro.desenhar(tabuleiro.jogadores()));

            String evento = regras.aplicarRegrasEspeciais(atual);
            if ("ESCOLHER_JOGADOR".equals(evento)) {
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
            } else if (!evento.isEmpty()) {
                System.out.println(evento);
            }

            if (tabuleiro.verificarVencedor(atual)) {
                System.out.println("Parabéns! " + atual.getNome() + " é o vencedor!");
                jogoAtivo = false;
            }

            tabuleiro.proximoTurno();
        }
        
        sc.close();
    }   
}
