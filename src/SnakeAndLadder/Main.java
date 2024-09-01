package SnakeAndLadder;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Game game = new Game(101, 1, 2);

        ArrayList<ArrayList<Integer>> entities = new ArrayList<ArrayList<Integer>>();

        int cntSnakes = Integer.parseInt(br.readLine());

        while (cntSnakes-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            ArrayList<Integer> entityPosition = new ArrayList<Integer>();
            entityPosition.add(start);
            entityPosition.add(end);
            entities.add(entityPosition);
        }

        int cntLadders = Integer.parseInt(br.readLine());
        while (cntLadders-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            ArrayList<Integer> entityPosition = new ArrayList<Integer>();
            entityPosition.add(start);
            entityPosition.add(end);
            entities.add(entityPosition);
        }

        game.addEntities(entities);

        game.startGame();
    }
}
