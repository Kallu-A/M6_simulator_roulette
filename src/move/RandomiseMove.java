package move;

/** Permet de générer des coups aléatoire et valide */
public class RandomiseMove {

    private static int maxValue;
    /** pour récuperer le move */
    private static Move move;

    private static Move[] moveTabl = { Simple.NOIR, Simple.ROUGE, Simple.IMPAIR, Simple.PAIR, Simple.MANQUE, Simple.PASSE,
                                        Multiple.COLONNE24, Multiple.DOUZAINE, Multiple.SIXAIN, Multiple.CARRE,
                                        Multiple.TRANSVERSALE, Multiple.CHEVAL, Multiple.PLEIN};

    /** pour récuperer les valeurs associés */
    private static int[] valueGet;

    /** créer un move avec des coups aléatoires */
    public static void generateMove() {
        int index = (int) ( Math.random() * moveTabl.length);
        move = moveTabl[index];
        if (move instanceof Simple) {
            valueGet = null;
        } else {
            Multiple m = (Multiple) move;
            switch (m) {
                case PLEIN -> {
                    valueGet = new int[1];
                    valueGet[0] = (int) (Math.random() * maxValue);
                }
                case CHEVAL -> {
                    valueGet = new int[2];
                    int value = (int) (Math.random() * (maxValue - 1));
                    valueGet[0] = value;
                    valueGet[1] = value + 1;
                }
                case TRANSVERSALE -> {
                    valueGet = new int[3];
                    int value = (int) (Math.random() * 12);
                    value ++;
                    int i = (value * 3) + 1;
                    valueGet[0] = i;
                    valueGet[1] = i+1;
                    valueGet[2] = i+2;
                }
                case CARRE -> {
                    valueGet = new int[4];
                    int value = (int) (Math.random() * 9);
                    value++;
                    int I = value * 4 + 1;
                            for (int i = 0; i < 4; i++)
                                valueGet[i] = I + i;

                }
                case SIXAIN -> {
                    valueGet = new int[6];
                    int value = (int) (Math.random() * 6);
                    value++;
                    int I = value * 6 + 1;
                    for (int i = 0; i < 6; i++)
                        valueGet[i] = I + i;
                }
                case DOUZAINE -> {
                    valueGet = new int[12];
                    int value = (int) (Math.random() * 3);
                    value++;
                    int I = value * 12 + 1;
                    for (int i = 0; i < 12; i++)
                        valueGet[i] = I + i;
                }
                case COLONNE24 -> {
                    valueGet = new int[24];
                    int value = (int) (Math.random() * 2);
                    int I = value * 12 + 1;
                    for (int i = 0; i < 24; i++)
                        valueGet[i] =  I + i;
                }
            }
        }
    }


    public static void setMax_value(int max_value) {
        RandomiseMove.maxValue = max_value;
    }

    public static Move getMove() {
        return move;
    }

    public static int[] getValueGet() {
        return valueGet;
    }
}
