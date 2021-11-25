package roulette;

import board.Case;
import board.Color;

public class RouletteAmericaine extends Roulette {

    public RouletteAmericaine() {
        MAX_VALUE = 38;
        board = new Case[MAX_VALUE];
        initTabl();
    }

    @Override
    public String getHistory(Boolean petit) {
        return getHistoryP(petit, "Américaine");
    }

    @Override
    public void initTabl() {
        board[0] = new Case(0, Color.GREEN);
        board[1] = new Case(28, Color.BLACK);
        board[2] = new Case(9, Color.RED);
        board[3] = new Case(26, Color.BLACK);
        board[4] = new Case(30, Color.RED);
        board[5] = new Case(11, Color.BLACK);
        board[6] = new Case(7, Color.RED);
        board[7] = new Case(20, Color.BLACK);
        board[8] = new Case(32, Color.RED);
        board[9] = new Case(17, Color.BLACK);
        board[10] = new Case(5, Color.RED);
        board[11] = new Case(22, Color.BLACK);
        board[12] = new Case(34, Color.RED);
        board[13] = new Case(15, Color.BLACK);
        board[14] = new Case(3, Color.RED);
        board[15] = new Case(24, Color.BLACK);
        board[16] = new Case(36, Color.RED);
        board[17] = new Case(13, Color.BLACK);
        board[18] = new Case(1, Color.RED);
        board[19] = new Case(100, Color.GREEN);
        board[20] = new Case(27, Color.RED);
        board[21] = new Case(10, Color.BLACK);
        board[22] = new Case(25, Color.RED);
        board[23] = new Case(29, Color.BLACK);
        board[24] = new Case(12, Color.RED);
        board[25] = new Case(8, Color.BLACK);
        board[26] = new Case(19, Color.RED);
        board[27] = new Case(31, Color.BLACK);
        board[28] = new Case(18, Color.RED);
        board[29] = new Case(6, Color.BLACK);
        board[30] = new Case(21, Color.RED);
        board[31] = new Case(33, Color.BLACK);
        board[32] = new Case(16, Color.RED);
        board[33] = new Case(4, Color.BLACK);
        board[34] = new Case(23, Color.RED);
        board[35] = new Case(35, Color.BLACK);
        board[36] = new Case(14, Color.BLACK);
        board[37] = new Case(2, Color.BLACK);
    }
}
