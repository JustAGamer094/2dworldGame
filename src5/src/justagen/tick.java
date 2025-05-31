package justagen;

import java.util.Scanner;

public class tick {
    private worldGen worldGen;
    private player player;

    public tick(worldGen worldGen, player player) {
        this.worldGen = worldGen;
        this.player = player;
    }

    public void initializeAndStart(Scanner scanner) {
        this.worldGen.displayWorld();
        while (true) {
            String s = scanner.next();
            switch (s) {
                case "a":
                    player.onAPressed();
                    break;
                case "d":
                    player.onDPressed();
                    break;
                case "w":
                    player.onWPressed();
                    break;
                case "s":
                    player.onSPressed();
                    break;
                case "q":
                    player.onQPressed();
                    break;
                case "e":
                    player.onEPressed();
                    break;
                default:
                    System.out.println("wrong key");
                    break;
            }
            try {
                Thread.sleep(50);
                player.gravity();
                player.setPlayer();
                this.worldGen.clear();
                this.worldGen.displayWorld();

            } catch (InterruptedException e1) {
                System.out.println("crash!");
            }
        }
    }

}