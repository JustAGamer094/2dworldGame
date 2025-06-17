package justagen;
import java.util.function.Supplier;

public class worldGen {
    public int[][] worldGrid;
    final int worldSize = 60;
    final int worldHeight = 25;
    private final int radiusBig = 5;
    private final int radiusSmall = 2;

    public worldGen(){
        createWorldGrid();
        createLandScape();
    }

    public void createWorldGrid(){
        worldGrid = new int[worldSize][worldHeight];
        for (int wx = 0; wx < worldSize; wx++){
            for(int wy = 9; wy < worldHeight; wy++){
                worldGrid[wx][wy] = blocks.STONE.ordinal();
            }
        }
    }
    public void setBedrock(){
        for (int sx = 0; sx < worldSize; sx++){
            worldGrid[sx][worldHeight-1] = blocks.BEDROCK.ordinal();
        }
        Runnable randomBedrock = () -> worldGrid[getRandom.get()][worldHeight-2] = blocks.BEDROCK.ordinal();
        for (int rx = 0; rx < worldSize/2; rx++){
            randomBedrock.run();
        }
    }
    public void displayWorld(){
        for(int dy = 0; dy < worldHeight; dy++){
            for(int dx = 0; dx < worldSize; dx++){
                String character = getChar.getCharacter(worldGrid[dx][dy]);
                System.out.print(character);
            }
            System.out.println();
        }
    }

    public void createLandScape(){
        this.setSun.set();
        setRandomDirt();
        this.circle.circle(getRandom.get(),(int)(worldHeight/2 + worldHeight/2*Math.random()),radiusBig,blocks.CAVEAIR.ordinal());
        this.circle.circle(getRandom.get(),(int)(worldHeight/2 + worldHeight/2*Math.random()),radiusSmall,blocks.CAVEAIR.ordinal());
        this.setBedrock();
        this.setLava.setLava();
        this.createTree.setTree();
        this.fixAir.fix();
    }
    public void setRandomDirt(){
        this.fillBox.fill(0,7,worldSize,9,blocks.GRASS.ordinal());
        int random = (int)(4*(Math.random()));
        int random1 = (int)(10*(Math.random()));

        for(int i = 0; i < random; i++){
            int randomX = getRandom.get();
            fillBox.fill(randomX-random1,7,randomX+random1, 7, blocks.SKY.ordinal());
            fillBox.fill(randomX-random1+1,8,randomX+random1-1, 8, blocks.SKY.ordinal());

        }
    }

    public double getDistance(int cx, int x1, int cy, int y1){
        return (Math.pow(cx - x1,2)+Math.pow(cy-y1,2));
    }
    Supplier<Integer> getRandom = () -> (int)(getWorldSize() * Math.random());

    public int getWorldSize() {
        return worldSize;
    }
    setBlock setBlock = (x, y, blockType) -> {
        worldGrid[x][y] = blockType;
    };
    fillSqaure fillBox = (x1, y1, x2, y2, typeBlock) -> {
        for(int i = x1; i <= x2; i++){
            for(int o = y1; o <= y2; o++){
                if(i >= 0 && i < worldSize && o >= 0 && o < worldHeight){
                    worldGrid[i][o] = typeBlock;
                }
            }
        }
    };

    createCircle circle = (x1,y1,radius, typeBlock) -> {
        for(int cx = x1 - radius; cx <= x1 + radius; cx++){
            for(int cy = y1 - radius; cy <= y1 + radius; cy++){
                if(cx >=0 && cx <  worldSize && cy >=0 && cy < worldHeight){
                    if(getDistance(cx, x1, cy, y1) <= Math.pow(radius,2.05)){
                        worldGrid[cx][cy] = typeBlock;
                    }
                }
            }
        }
    };

    fixAir fixAir = () -> {
      for(int x = 0; x < worldSize; x++){
          for(int y = 0; y < 10; y++){
              if(worldGrid[x][y] == blocks.CAVEAIR.ordinal() && !(worldGrid[x][y-1] == blocks.GRASS.ordinal() || worldGrid[x][y-2] == blocks.GRASS.ordinal())){
                  setBlock.set(x,y,blocks.SKY.ordinal());
              }
          }
      }
    };

    getBlock getBlock =(x,y) -> {
        if(x >= 0 && x < worldSize && y >= 0 && y < worldHeight) {
            return worldGrid[x][y];
        }else{
            return blocks.BORDERBLOCK.ordinal();
        }
    };

    getCharacterFromNumber getChar = (blockType) -> blocks.values()[blockType].getPrintedColour();

    setToLava setLava = () -> {
        for(int x = 0; x < worldSize; x++){
            for(int y = worldHeight - 3; y< worldHeight; y++){
                if(worldGrid[x][y] == blocks.CAVEAIR.ordinal()){
                    setBlock.set(x,y,blocks.LAVA.ordinal());
                }
            }
        }
    };

    generateTree createTree = () ->{
        for(int x = 0; x < worldSize; x++){
            for(int y = 0; y < worldHeight; y++){
                double treeChance = Math.random();
                if(worldGrid[x][y] == blocks.SKY.ordinal() && worldGrid[x][y+1] == blocks.GRASS.ordinal() && treeChance < 0.05){
                    worldGrid[x][y] = blocks.LOG.ordinal();
                    generateTree(x,y);
                }
            }
        }
    };
    setSun setSun = () -> {
        this.fillBox.fill(0,0,1,1,blocks.SUN.ordinal());
    };

    public void generateTree(int x, int y){
        if(worldGrid[x-1][y] != blocks.LOG.ordinal() && worldGrid[x+1][y] != blocks.LOG.ordinal()) {
            this.setBlock.set(x, y - 1, blocks.LOG.ordinal());
            this.setBlock.set(x, y - 2, blocks.LOG.ordinal());
            this.fillBox.fill(x - 2, y - 4, x + 2, y - 3, blocks.LEAVES.ordinal());
            this.fillBox.fill(x - 1, y - 6, x + 1, y - 5, blocks.LEAVES.ordinal());
            generateBeeNest(x, y);
        } else {
            setBlock.set(x,y,blocks.SKY.ordinal());

        }
    }
    public void  generateBeeNest(int x, int y){
        double coinFlip = Math.random();
        double anotherCoinFlip = Math.random();
        if(anotherCoinFlip < 0.35){
            if(coinFlip < 0.5){
                if(x+1 < worldSize && worldGrid[x+1][y-2] ==blocks.SKY.ordinal()){
                    this.setBlock.set(x+1,y-2,blocks.BEENEST.ordinal());
                }
            } else {
                if (x - 1 > 0 && worldGrid[x - 1][y - 2] == blocks.SKY.ordinal()) {
                    this.setBlock.set(x - 1, y - 2, blocks.BEENEST.ordinal());
                }
            }
        }
    }
    public void clear(){
       for(int i = 0; i < 50; i++){System.out.println();}
    }

}