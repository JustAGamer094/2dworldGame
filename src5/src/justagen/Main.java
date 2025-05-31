package justagen;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        worldGen worldGen = new worldGen();

        player player = new player(10, 7, 10, worldGen);
        player.spawn();

        tick worldStart = new tick(worldGen, player);

        Scanner scanner = new Scanner(System.in);
        worldStart.initializeAndStart(scanner);

    }
}