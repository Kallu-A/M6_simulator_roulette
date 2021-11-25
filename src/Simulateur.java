import betElement.Bet;
import betElement.ToMuchOnBet;
import move.FalseMoveException;
import move.Move;
import move.RandomiseMove;
import player.Crainte;
import player.Player;
import roulette.Roulette;
import roulette.RouletteAmericaine;
import roulette.RouletteEur;

import java.util.ArrayList;
import java.util.List;

public class Simulateur {

    public static void main(String[] args) {


        // définie le type d'affichage
        Boolean petit = true;
        //permet de définire le même facteur crainte a tous
        Boolean sameCrainte = true;
        // nombre de bille lancé
        int nbrBille = 100;
        // [0; 200[

        RouletteEur r = new RouletteEur();
        launchGame(r, nbrBille, petit, sameCrainte);

        RouletteAmericaine rA = new RouletteAmericaine();

        launchGame(rA, nbrBille, petit, sameCrainte);
    }

    private static void launchGame(Roulette r, int nombreBilleLancer, Boolean petit, Boolean sameCrainte) {
        Player[] p = givePlayers(sameCrainte);
        int mult = p.length;
        List<Bet> list = new ArrayList<>();
        Player px;
        Move m;
        int[] tab;

        for (int i = 0; i <=  nombreBilleLancer * mult; i++) {
            px = p[i % mult];

            double sum = (Math.random() * (px.getWallet() / px.getIsSafe()));
            RandomiseMove.generateMove();
            m = RandomiseMove.getMove();
            tab = RandomiseMove.getValueGet();

            try {
                list.add(new Bet(sum,m , px, tab));
            } catch (ToMuchOnBet e) {
                System.out.println("Somme misé trop haute ! ");
            } catch (FalseMoveException e) {
                System.out.println("Coup impossible");
            }

            if (i != 0 && (i % mult == 0) ) {
                r.play(list, petit);
                list.clear();

            }
        }

        display(p, r, petit);
    }

    /** créer un tableau de 8 joueur et le renvoie */
    private static Player[] givePlayers(Boolean predefinedCraint) {
        Player p1 = new Player(400, "Lucas");
        Player p2 = new Player(100, "Lili");
        Player p3 = new Player(500, "Patrick");
        Player p4 = new Player(100, "Nassim");
        Player p5 = new Player(200, "Maël");
        Player p6 = new Player(800, "Olivier");
        Player p7 = new Player(150, "Nicolas");
        Player p8 = new Player(50, "Clement");
        if (predefinedCraint) {
            p1.setIsSafe(Crainte.NORMAL);
            p2.setIsSafe(Crainte.TRES_CRAINTIF);
            p3.setIsSafe(Crainte.RIEN_A_PERDRE);
            p4.setIsSafe(Crainte.PARIEUR);
            p5.setIsSafe(Crainte.CRAINTIF);
            p6.setIsSafe(Crainte.NORMAL);
            p7.setIsSafe(Crainte.CRAINTIF);
            p8.setIsSafe(Crainte.PARIEUR);

        }
        return new Player[]{p1, p2, p3, p4, p5, p6, p7, p8};
    }

    /** affiche les résultat de fin de partie */
    private static void display(Player[] p, Roulette r , Boolean petit) {
        System.out.println(r.getHistory(petit));
        for (Player p1: p) System.out.println(p1);
    }
}
