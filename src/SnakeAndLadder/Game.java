package SnakeAndLadder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Game {
    Board board;
    Dice dice;
    Queue<Player> players = new LinkedList<>();
    Player winner;

    Game(int n, int diceCount, int playersCount) {
        initBoard(n, diceCount, playersCount);
    }

    public void startGame() {
        System.out.println("Welcome to Snake and Ladder!");
        while (winner == null && !players.isEmpty()) {
            Player currentPlayer = players.poll();

            System.out.println("current player: " + currentPlayer.getId() + " position: " + currentPlayer.getPosition());

            int diceValue = dice.rollDice();
            int newPosition = Math.min(100, currentPlayer.getPosition() + diceValue);
            Cell cell = board.getCell(newPosition);
            if (cell.getEntity() != null && newPosition == cell.getEntity().getStart()) {
                String jumpBy = (cell.getEntity().getStart() < cell.getEntity().getEnd())? "ladder" : "snake";
                System.out.println("jump done by: " + jumpBy);
                newPosition = cell.getEntity().getEnd();
            }

            currentPlayer.setPosition(newPosition);

            if (newPosition >= 100 - 1) {
                winner = currentPlayer;
            }

            players.add(currentPlayer);
        }

        System.out.println("Winner is " + winner.getId());
    }

    private void initBoard(int n, int diceCount, int playersCount) {
        board = new Board(n);
        dice = new Dice(diceCount);
        for (int i = 0; i < playersCount; i++) {
            Player p = new Player(i, 0);
            players.add(p);
        }
    }

    public void addEntities(ArrayList<ArrayList<Integer>> entityPositions) {
        for (ArrayList<Integer> entityPosition : entityPositions) {
            board.addGameEntity(entityPosition.get(0), entityPosition.get(1));
        }
    }
}
