package move;

/**Liste tous les coups simple et leur coefficient */
public enum Simple implements Move {

    NOIR(1),
    ROUGE(1),
    IMPAIR(1),
    PAIR(1),
    PASSE(1),
    MANQUE(1);

    private final double amount;

    Simple(double amount) {
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }
}