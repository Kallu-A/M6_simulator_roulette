package move;

/**Liste tous les coups multiple et leur coefficient */
public enum Multiple implements Move {

    PLEIN(35),
    CHEVAL(17),
    TRANSVERSALE(11),
    CARRE(8),
    SIXAIN(5),
    DOUZAINE(2),
    COLONNE24(0.5);

    private final double amount;

    Multiple(double amount) {
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }
}