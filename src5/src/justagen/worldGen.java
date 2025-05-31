package justagen;
import static justagen.colours.*;

public class worldGen {
    public int[][] worldGrid;
    final int worldSize = 35;
    final int worldHeight = 25;
    private final int radiusBig = 5;
    private final int radiusSmall = 2;

    public worldGen(){
        createWorldGrid();
        createLandScape();

        if(getRandom()<0.35 * worldHeight){
        }
    }

    public void createWorldGrid(){
        worldGrid = new int[worldSize][worldHeight];
        for (int wx = 0; wx < worldSize; wx++){
            for(int wy = 7; wy < worldHeight; wy++){
                worldGrid[wx][wy] = 1;
            }
        }
    }
    public void setBedrock(){
        for (int sx = 0; sx < worldSize; sx++){
            worldGrid[sx][worldHeight-1] = 2;
        }
        Runnable randomBedrock = () -> worldGrid[getRandom()][worldHeight-2] = 2;
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
        this.fillBox.fill(0,7,worldSize,7,0);
        this.fillBox.fill(0,7,5,8,0);
        this.fillBox.fill(0,9,5,9,3);
        this.fillBox.fill(6,8,worldSize,8,3);
        this.circle.circle(getRandom(),(int)(worldHeight/2 + worldHeight/2*Math.random()),radiusBig,6);
        this.circle.circle(getRandom(),(int)(worldHeight/2 + worldHeight/2*Math.random()),radiusSmall,6);
        this.setBedrock();
        this.setLava.setLava();
        this.createTree.setTree();
    }

    public double getDistance(int cx, int x1, int cy, int y1){
        return (Math.pow(cx - x1,2)+Math.pow(cy-y1,2));
    }
    public int getRandom(){
        return (int)(worldSize * Math.random());
    }
    public int getWorldSize() {
        return worldSize;
    }
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

    setBlock setBlock = (x, y, blockType) -> {
        worldGrid[x][y] = blockType;
    };
    checkBlock getBlock =(x,y) -> {
        return worldGrid[x][y];
    };
    getCharacterFromNumber getChar = (blockType) -> {
      switch (blockType){
          case 0: return ANSI_BG_BLUE + "  "  + ANSI_RESET;     // sky
          case 1: return ANSI_BG_BRIGHT_BLACK + "  " + ANSI_RESET;   // stone
          case 2: return ANSI_BG_BLACK + "  " + ANSI_RESET;   // bedrock
          case 3: return ANSI_BG_GREEN + "  " + ANSI_RESET;    //grass
          case 4: return ANSI_BG_ORANGE + "  " + ANSI_RESET;    //lava
          case 5: return ANSI_BG_BROWN + "  " + ANSI_RESET;     //tree
          case 6: return ANSI_BG_WHITE + "  " + ANSI_RESET;     //cave air
          case 7: return ANSI_BG_DARK_GREEN + "  " + ANSI_RESET;    //leaves
          case 8: return ANSI_BG_YELLOW + "  " + ANSI_RESET;    //beenest
          case 9: return ANSI_BG_BRIGHT_YELLOW + "  " + ANSI_RESET;    //sun
          case 10: return ANSI_BG_WHITE + "P " + ANSI_RESET;    //player
          default: return "";
      }
    };

    setToLava setLava = () -> {
        for(int x = 0; x < worldSize; x++){
            for(int y = worldHeight - 3; y< worldHeight; y++){
                if(worldGrid[x][y] == 6){
                    setBlock.set(x,y,4);
                }
            }
        }
    };

    generateTree createTree = () ->{
        for(int x = 0; x < worldSize; x++){
            for(int y = 0; y < worldHeight; y++){
                double treeChance = Math.random();
                if(worldGrid[x][y] == 0 && worldGrid[x][y+1] == 3 && treeChance < 0.05){
                    worldGrid[x][y] = 5;
                    generateTree(x,y);
                }
            }
        }
    };
    setSun setSun = () -> {
        this.fillBox.fill(0,0,1,1,9);
    };

    public void generateTree(int x, int y){
        this.setBlock.set(x,y-1,5);
        this.setBlock.set(x,y-2,5);
        this.fillBox.fill(x-2,y-4,x+2,y-3,7);
        this.fillBox.fill(x-1,y-6,x+1,y-5,7);
        generateBeeNest(x,y);
    }
    public void  generateBeeNest(int x, int y){
        double coinFlip = Math.random();
        double anotherCoinFlip = Math.random();
        if(anotherCoinFlip < 0.35){
            if(coinFlip < 0.5){
                if(x+1 < worldSize && worldGrid[x+1][y-2] ==0){
                    this.setBlock.set(x+1,y-2,8);
                }
            } else {
                if (x - 1 > 0 && worldGrid[x - 1][y - 2] == 0) {
                    this.setBlock.set(x - 1, y - 2, 8);
                }
            }
        }
    }
    public void clear(){
       for(int i = 0; i < 50; i++){System.out.println();}
    }

}