package SnakeAndLadder;

public class Player {
    private int id;

    public void setPosition(int position) {
        this.position = position;
    }

    private int position;

    public Player(int id, int position) {
        this.id = id;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public int getId() {
        return id;
    }
}
