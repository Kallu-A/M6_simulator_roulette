package player;

public enum Crainte {
    TRES_CRAINTIF(15),
    CRAINTIF(13),
    NORMAL(10),
    PARIEUR(8),
    RIEN_A_PERDRE(5);

    public final int value;

    Crainte(int value) {
        this.value = value;
    }
}