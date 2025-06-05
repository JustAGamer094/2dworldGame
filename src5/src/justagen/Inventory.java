package justagen;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    Map<String, Integer> inventory = new HashMap<>();
    private worldGen worldGen;

    public Inventory(Map<String, Integer> inventory,worldGen worldGen){
      this.inventory = inventory;
      this.worldGen = worldGen;
    }


    public void putToInventory(int targetX, int targetY){
        int minedBlock = this.worldGen.getBlock.get(targetX, targetY);

        if(minedBlock != blocks.SKY.ordinal() && minedBlock != blocks.CAVEAIR.ordinal() &&
           minedBlock !=blocks.BEDROCK.ordinal() && minedBlock != blocks.LAVA.ordinal() &&
           minedBlock != blocks.BORDERBLOCK.ordinal()){

            String blockName = blocks.values()[minedBlock].name();
            this.inventory.put(blockName, inventory.getOrDefault(blockName, 0) + 1);
        }
    }
    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("empty inventory");
        } else {
            System.out.println("_________");
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }


}
