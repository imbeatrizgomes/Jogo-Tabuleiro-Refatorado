package entities;

public enum TipoJogador {
    NORMAL,
    SORTUDO,
    AZARADO;

    @Override
    public String toString() {
        switch (this) {
            case SORTUDO: return "Sortudo";
            case AZARADO: return "Azarado";
            default: return "Normal";
        }
    }
}
