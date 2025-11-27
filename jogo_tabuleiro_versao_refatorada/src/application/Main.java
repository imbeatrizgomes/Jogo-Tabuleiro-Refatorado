package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import entities.Jogador;
import entities.JogadorComSorte;
import entities.JogadorNormal;
import entities.JogadorSemSorte;
import entities.Jogo;
import entities.JogoRegras;
import entities.Tabuleiro;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Modo DEBUG (S/N)? ");
        String resp = sc.next();
        boolean debug = resp.equalsIgnoreCase("S");
        sc.nextLine(); 

        System.out.print("Quantos jogadores participarão (2 a 6)? ");
        int qtd = sc.nextInt();
        sc.nextLine();
        if (qtd < 2) qtd = 2; 
        if (qtd > 6) qtd = 6;


        List<Jogador> jogadores = new ArrayList<>();

        for (int i = 1; i <= qtd; i++) {
            System.out.println("\nJogador " + i + ":");
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("Cor: ");
            String cor = sc.nextLine();
            
            System.out.println("Tipo 1 - Normal");
            System.out.println("Tipo 2 - Sortudo");
            System.out.println("Tipo 3 - Azarado");
            System.out.print("Escolha uma das opções (1/2/3): ");
            String tipo = sc.next();
            sc.nextLine();

            Jogador p;
            if (tipo.equals("2")) {
                p = new JogadorComSorte(nome, cor);
            } else if (tipo.equals("3")) {
                p = new JogadorSemSorte(nome, cor);
            } else {
                p = new JogadorNormal(nome, cor);
            }
            jogadores.add(p);
        }

        Tabuleiro tabuleiro = new Tabuleiro(jogadores);
        JogoRegras regras = new JogoRegras(tabuleiro.jogadores());
        Jogo jogo = new Jogo(tabuleiro, regras, debug);
        jogo.loopDoJogo();

        sc.close();
    
    }
}
