package it.polimi.ingsw.View;

import it.polimi.ingsw.Model.*;

import java.io.Serializable;

public class LiteBoard implements Serializable {
    private static final long serialVersionUID = 36347531L;

    private final int[][] lv;     // [row][column] = level
    private final int[][] posWorker;/*
     * [worker] [0] = row
     * [worker] [1] = column
     * [worker] [2] = color
     * */
    private final String message;
    private final int numAllWorker;

    public LiteBoard(String input){
        message = input;
        lv = new int[0][0];
        posWorker = new int[0][0];
        numAllWorker = 0;
    }

    public LiteBoard(String message, Board board, Game game) {

        numAllWorker = game.getNumWorkers();
        lv = new int[board.getNumRow()][board.getNumColumn()];
        posWorker = new int[numAllWorker][3];

        for (Cell[] c: board.getMap()) {
            for (Cell c1:c) {
                int level = c1.getLevel();
                int row = c1.getNumRow();
                int column = c1.getNumColumn();
                lv[row][column] = level;
            }
        }

        int numPosWorker = 0;
        for (Player p : game.getPlayers()) {
            for (Worker worker : p.getWorkers()) {
                posWorker[numPosWorker][0] = worker.getPosition().getNumRow();
                posWorker[numPosWorker][1] = worker.getPosition().getNumColumn();
                posWorker[numPosWorker][2] = p.getColor().ordinal();
                numPosWorker++;
            }
        }
        this.message = message;
    }



    public String getMessage() {
        return message;
    }

    public void printBoardCLI(){
        printBoardLine();
        for(int boardRow = 4; boardRow >= -1; boardRow--) {
            for(int cellRow = 0; cellRow < 3; cellRow++) {
                for (int col = -1; col < 5; col++) {
                    if (col == -1) {
                        // Print the number of the board rows
                        if (cellRow == 0 || cellRow == 2) System.out.print("      ");
                        else if (boardRow >= 0) System.out.print("  " + (boardRow + 1) + "   ");
                        else System.out.print("      ");

                    } else if (boardRow == -1) {
                        // Print the number of the board columns
                        if (cellRow == 1) System.out.print("    " + (col + 1) + "    ");
                    } else {
                        if (cellRow == 0 || cellRow == 2) System.out.print("|" + getLevelTD(boardRow, col) + "|");
                        else System.out.print("|" + getWorker(boardRow, col) + "|");
                    }
                }
                System.out.print('\n');
            }
            if(boardRow != -1) printBoardLine();
        }
    }

    private String getWorker(int row, int col) {
        for (int worker = 0; worker < numAllWorker; worker++) {
            if (posWorker[worker][0] == row) {
                if (posWorker[worker][1] == col) {
                    int[] colorList = {Color.Green.ordinal(), Color.Yellow.ordinal(), Color.Red.ordinal()};
                    for (int color : colorList) {
                        if (posWorker[worker][2] == color) {

                            worker = worker%2 + 1;    // Show the number of worker that will be 1 or 2

                            return getLevelLR(row, col) + " " +  Color.toFirstLetter(color) + "W" + worker + ' ' + getLevelLR(row, col);
                        }
                    }

                }

            }

        } return getLevelLR(row, col) + "     " + getLevelLR(row, col);
    }

    /**
     * return 7 numbers to fill the Top and Down of the board cell
     */
    public String getLevelTD(int row, int col){
        return String.valueOf(lv[row][col]).repeat(7);
    }
    /**
     * return a number to fill the Left and Right of the board cell
     */
    public String getLevelLR(int row, int col){
        return String.valueOf(lv[row][col]);
    }

    public void printBoardLine(){
        System.out.println("      +-------++-------++-------++-------++-------+");
    }

}
