package roulette;

import betElement.Bet;
import board.Case;
import move.FalseMoveException;
import move.Multiple;
import move.Simple;

import java.util.List;

public abstract class Roulette {

    public static final int INCORECT_VALUE = -1;
    protected int MAX_VALUE = INCORECT_VALUE;

    protected double gainHouse = 0;
    protected Case valueBall = null;
    protected Case[] board;

    protected int parisGagneJouer = 0;
    protected int parisGagneMaison = 0;

    public abstract void initTabl();
    public abstract String getHistory(Boolean petit);

    /** renvoie la somme n des coups */
    protected int sumNLaunch() {
        int sum = 0;
        for (int i = 0; i < MAX_VALUE; i++) sum += board[i].getIsFall();
        return sum;
    }

    /** fait un tour */
    protected void getTurnBall() {
        int valeur = (int) (Math.random() * MAX_VALUE);
        board[valeur].isFall();
        valueBall = board[valeur];

    }

    /** fait un tour avec la liste des paris et se charge de fournir les récompenses*/
    public void play(List<Bet> list, Boolean petit) {
        getTurnBall();
        for (Bet bet: list) {
            try {
                makeBet(bet, petit);
            } catch (FalseMoveException e) {System.out.println("Paris invalide ! ");}
        }

    }
    /** sélectionne le coup et renvoie la somme finale */
    protected void makeBet(Bet bet, Boolean petit) throws FalseMoveException {

        double gainHouseBefore = gainHouse;
        if (bet.getSelected() instanceof Simple) {
            Simple simple = (Simple) bet.getSelected();
            switch (simple) {
                case NOIR -> gainHouse += bet.betDone(valueBall.isBlack());
                case ROUGE -> gainHouse += bet.betDone(valueBall.isRed());
                case IMPAIR -> gainHouse += bet.betDone(valueBall.isOdd());
                case PAIR -> gainHouse += bet.betDone(valueBall.isPair());
                case PASSE -> gainHouse += bet.betDone(valueBall.isPasse());
                case MANQUE -> gainHouse += bet.betDone(valueBall.isManque());
            };
        } else {
            if (! (bet.getSelected() instanceof Multiple ) ) throw new FalseMoveException();
            if (bet.getValueSelected() == null) throw new FalseMoveException();
            gainHouse += bet.betDone(valueBall.isMultiple(bet.getValueSelected()));
        }

        if (gainHouseBefore < gainHouse) {
            if (!petit) System.out.println("PERDU " + bet);
            parisGagneMaison++;
        }
        else {
            if (!petit) System.out.println("GAGNÉ " + bet);
            parisGagneJouer++;
        }
    }

    /** renvoie historique des coups */
    protected String getHistoryP(Boolean petit, String nom) {
        StringBuilder str = new StringBuilder("\n-----------------------Roulette " + nom + " --------------------- \n");
        if (!petit) {
            int sum = sumNLaunch();
            double percentRound;
            str.append(" : \n [ ");
            for (int i = 0; i < MAX_VALUE; i++) {
                percentRound = Math.round((((double) board[i].getIsFall() / sum) * 100 ) * 1000.0) / 1000.0;
                str.append("\n    numéro ")
                        .append(board[i].getValue())
                        .append(" | couleur : ")
                        .append(board[i].getColor())
                        .append(" | tombé  : ")
                        .append(board[i].getIsFall())
                        .append(" fois | moyenne : ")
                        .append( percentRound )
                        .append(" %");
            }
            str.append("\n ]");
        }

        double gainHouseRound = Math.round(gainHouse * 1000.0) / 1000.0;
        str.append("\n\nGain de la maison : ").append(gainHouseRound).append(" sur ").append(sumNLaunch()).append(" lancé de bille\n");
        str.append("\nParis gagné par les joueurs : ").append(parisGagneJouer).append(" / ").append(parisGagneJouer+parisGagneMaison);
        return str.toString();
    }

}
