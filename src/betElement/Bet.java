package betElement;

import move.FalseMoveException;
import move.Move;
import move.Multiple;
import move.Simple;
import player.Player;

/** paris du jeu */
public class Bet {

    /** somme misée*/
    private final double sum;

    /** coup fait */
    private final Move selected;

    /** joueur qui la fait */
    private final Player owner;

    /** valeur choisie */
    private int[] valueSelected;

    private Boolean isSucessFul;

    public Bet(double sum, Move selected, Player owner, int[] valueSelected ) throws ToMuchOnBet, FalseMoveException {
        this.sum = sum;
        this.selected = selected;
        this.owner = owner;
        this.valueSelected = valueSelected;
        if (! owner.makeBet(sum)) throw new ToMuchOnBet();
        if ( selected instanceof Simple && valueSelected != null) throw new FalseMoveException();

        if (selected instanceof Multiple) {
            Multiple m = (Multiple) selected;
            switch (m) {
                case PLEIN -> { if (valueSelected.length != 1) throw new FalseMoveException();}
                case CHEVAL -> {if (valueSelected.length != 2) throw new FalseMoveException();}
                case TRANSVERSALE -> {if (valueSelected.length != 3) throw new FalseMoveException();}
                case CARRE -> {if (valueSelected.length != 4) throw new FalseMoveException();}
                case SIXAIN -> {if (valueSelected.length != 6) throw new FalseMoveException();}
                case DOUZAINE -> {if (valueSelected.length != 12) throw new FalseMoveException();}
                case COLONNE24 -> {if (valueSelected.length != 24) throw new FalseMoveException();}
            }
        }
    }

    public int[] getValueSelected() {
        return valueSelected;
    }

    public Move getSelected() {
        return selected;
    }

    /** test si bet est réussi ou non et renvoie la somme perdu par le paris */
    public double betDone(Boolean sucessful) {
        if (sucessful) {
            isSucessFul = true;
            // ajoute la somme au compte du gagnant
            owner.addWalletBet( sum + (sum * selected.getAmount()));
            return - (sum * selected.getAmount() );
        } else {
            isSucessFul = false;
            return sum;
        }
    }

    @Override
    public String toString() {
        double sumRound = Math.round(sum * 1000.0) / 1000.0;
        StringBuilder str = new StringBuilder(" Bet | joueur " + owner.getName() + " | move : "  + selected + " | misé : " + sumRound);
        if (valueSelected == null) return str.toString();
        str.append(" liste des coups : ");
        for (int j : valueSelected) str.append(j).append(", ");
        return str.toString();
    }
}
