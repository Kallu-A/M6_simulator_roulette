package player;

/** Correspond a un joueur */
public class Player {

    /** Argent possédé par le joueur */
    private double wallet;

    /** % par rapport a l'argent mis ex 0.05 signifie 5% de gain ect.. */
    private double percentChange;

    /** Argent de la poche du joueur */
    private double valueInvested;

    /** facteur de mise plus petit plus il mise grosse partie de son wallet */
    private Crainte isSafe;

    /** Nom du joueur */
    private final String name;

    /** permet de faire le ratio de paris réussi */
    private int nombreBet;
    private int nombreBetReussi;


    public Player(double wallet, String name) {
        this.wallet = wallet;
        percentChange = 0;
        int val = (int) (Math.random() * 5);
        switch (val) {
            case 0 -> isSafe = Crainte.CRAINTIF;
            case 1 -> isSafe = Crainte.TRES_CRAINTIF;
            case 2 -> isSafe = Crainte.PARIEUR;
            case 3 -> isSafe = Crainte.NORMAL;
            case 4 -> isSafe = Crainte.RIEN_A_PERDRE;
        }
        this.name = name;
        valueInvested = wallet;
    }

    public void setIsSafe(Crainte isSafe) {
        this.isSafe = isSafe;
    }

    /** ajoute de l'argent */
    public void addWallet(double value) {
        this.wallet += value;
        valueInvested += value;
        actualisePercentChange();
    }

    /** gain grace a un paris */
    public void addWalletBet( double gain) {
        nombreBetReussi++;
        this.wallet += gain;
        actualisePercentChange();
    }

    /** retire de l'argent pour un bet */
    public boolean makeBet( double valueBet) {
        wallet -= valueBet;
        actualisePercentChange();
        nombreBet++;
        return true;
    }

    /** calcule le pourcentage gain / perte */
    private void actualisePercentChange() {
        double tampon = wallet / valueInvested;
        if (tampon < 1) tampon = (-1 + tampon) * 100;
        else tampon = (tampon * 100) - 100;
        percentChange = tampon;
    }

    public double getWallet() {
        return wallet;
    }

    public String getName() {
        return name;
    }

    public int getIsSafe() {
        return isSafe.value;
    }

    @Override
    public String toString() {
        double walletRound = Math.round(wallet * 1000.0) / 1000.0;
        double percentChangeRound = Math.round( percentChange * 1000.0) / 1000.0;
        return "\n" + name + " investit : " + valueInvested + " |  finale : " + walletRound + " | modification " + percentChangeRound +
                        " % | " + isSafe  + " | mise réussi : " + nombreBetReussi + "/" + nombreBet;
    }
}


