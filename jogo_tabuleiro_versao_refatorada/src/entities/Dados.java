package entities;

import java.util.Random;

public class Dados {
    private final Random random;

    public Dados() {
        this.random = new Random();
    }

    public int rolar() {
        return random.nextInt(6) + 1;
    }

    public int rolarDoisDados() {
        return rolar() + rolar();
    }
}