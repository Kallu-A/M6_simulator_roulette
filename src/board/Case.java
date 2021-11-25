package board;

/** Correspond a une case */
public class Case {

    private final int value;
    private final Color color;

    private int isFall;

    public Case(int value, Color color) {
        this.value = value;
        this.color = color;
        isFall = 0;
    }

    /** Incrémente si la case est tombé */
    public void isFall() {
        isFall++;
    }

    /** renvoie la couleur de la case */
    public Color getColor() {
        return color;
    }

    public int getIsFall() {
        return isFall;
    }

    /** getter de value */
    public int getValue() {
        return value;
    }

    /** si la couleur est noir */
    public boolean isBlack() {
        return (color == Color.BLACK);
    }

    /** si la couleur est rouge */
    public boolean isRed() {
        return (color == Color.RED);
    }

    /** si valeur est impaire */
    public boolean isOdd() {
        return (value %2 == 1);
    }

    /** si la valeur est paire */
    public boolean isPair() {
        return (value %2 == 0);
    }

    /** si la valeur est la passe */
    public boolean isPasse() {
        return ( 19 <= value && value <= 36 );
    }

    /** si la valeur est le manque */
    public boolean isManque() {
        return ( 1 <= value && value <= 18);
    }

    /** si la valeur est dans le multiple */
    public boolean isMultiple(int[] value) {
        for (int j : value) if (j == this.value) return true;
        return false;
    }





}
