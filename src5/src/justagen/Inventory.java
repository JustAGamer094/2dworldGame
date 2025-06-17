package justagen;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    Map<Integer, Integer> inventory = new HashMap<>();
    private worldGen worldGen;

    public Inventory(Map<Integer, Integer> inventory,worldGen worldGen){
      this.inventory = inventory;
      this.worldGen = worldGen;
    }


    public void removeFromInventory(int toBePlaced){
        this.inventory.put(toBePlaced, inventory.getOrDefault(toBePlaced, 0) - 1);
        if(inventory.get(toBePlaced) == 0){
            inventory.remove(toBePlaced);
        }
    }

    public int getAvailableBlock(){
        if(!inventory.isEmpty()){
            for(Map.Entry<Integer, Integer> entry : inventory.entrySet()){
                return entry.getKey();
            }
        }
    return -1;
    }

    public void putToInventory(int targetX, int targetY){
        int minedBlock = this.worldGen.getBlock.get(targetX, targetY);

        if(minedBlock != blocks.SKY.ordinal() && minedBlock != blocks.CAVEAIR.ordinal() &&
           minedBlock !=blocks.BEDROCK.ordinal() && minedBlock != blocks.LAVA.ordinal() &&
           minedBlock != blocks.BORDERBLOCK.ordinal()){


            this.inventory.put(minedBlock, inventory.getOrDefault(minedBlock, 0) + 1);
        }
    }
    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("empty inventory");
        } else {
            System.out.println("_________");
            for (Map.Entry<Integer, Integer> entry : inventory.entrySet()) {
                System.out.println(blocks.values()[entry.getKey()] + " : " + entry.getValue());
            }
        }
    }


}