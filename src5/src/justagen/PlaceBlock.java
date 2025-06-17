package justagen;

public class PlaceBlock {
    private Player player;
    private worldGen worldGen;

    public PlaceBlock(Player player, worldGen worldGen) {
        this.player = player;
        this.worldGen = worldGen;
    }


    public void placeBlock() {
        this.player.getFacingCoordinates();
        int targetX = this.player.facingBlock.facingX;
        int targetY = this.player.facingBlock.facingY;
        int toBePlaced = -1;

        if (!player.inventory.inventory.isEmpty()) {
            toBePlaced = player.inventory.getAvailableBlock();
        } else {
            System.err.println("Inventory empty, cant place!");
        }

        if (targetX > 0 && targetX <= worldGen.worldSize && targetY > 0 && targetY <= worldGen.worldHeight) {
            if (worldGen.worldGrid[targetX][targetY] == blocks.SKY.ordinal() || worldGen.worldGrid[targetX][targetY] == blocks.CAVEAIR.ordinal()) {
                if (toBePlaced >= 0) {
                    worldGen.setBlock.set(targetX, targetY, toBePlaced);
                    this.player.inventory.removeFromInventory(toBePlaced);
                }
            }
        }

    }

}