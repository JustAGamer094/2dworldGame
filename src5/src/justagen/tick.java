package justagen;

import java.util.HashMap;
import java.util.Scanner;

public class tick {
    private worldGen worldGen;
    private Player player;
    private PlaceBlock placeBlock;

    public tick(worldGen worldGen, Player player, PlaceBlock placeBlock) {
        this.worldGen = worldGen;
        this.player = player;
        this.placeBlock = placeBlock;
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
                case "r":
                    player.mine();
                    break;
                case "f":
                    placeBlock.placeBlock();
                    break;
                default:
                    System.out.println("wrong key");
                    break;
            }
            try {
                Thread.sleep(50);
                player.gravity();
                this.worldGen.clear();
                this.worldGen.displayWorld();
                this.player.inventory.displayInventory();

            } catch (InterruptedException e1) {
                System.out.println("crash!");
            }
        }
    }
    public static void startWorld(){
        worldGen worldGen = new worldGen();

        facingBlock facingBlock = new facingBlock(10,7);
        Inventory inventory = new Inventory(new HashMap<>(), worldGen);
        Player player = new Player(10, 7, blocks.PLAYER.ordinal(), worldGen, facingBlock, inventory);
        PlaceBlock placeBlock = new PlaceBlock(player,worldGen);
        player.spawn();
        tick worldStart = new tick(worldGen, player, placeBlock);
        Scanner scanner = new Scanner(System.in);
        worldStart.initializeAndStart(scanner);
    }

}