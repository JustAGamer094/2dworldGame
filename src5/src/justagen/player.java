package justagen;

public class player {
    int x;
    int y;
    int blockType;
    private worldGen worldGen;
    int backgroundBlockPlayer;
    int facingDirection;
    facingBlock facingBlock;

    public player (int initialX, int initialY, int blockType, worldGen worldGen, facingBlock facingBlock){
        this.x = initialX;
        this.y = initialY;
        this.blockType = blockType;
        this.worldGen = worldGen;
        this.facingBlock = facingBlock;
    }


    public void spawn(){
        this.backgroundBlockPlayer = worldGen.getBlock.get(this.x, this.y);
        this.worldGen.worldGrid[this.x][this.y] = blocks.PLAYER.ordinal();
    }
    public void onAPressed(){
        int newX = this.x -1;
        int newY = this.y;
        if(x > 0) {
            if(worldGen.worldGrid[newX][newY] == 0) {
                moveHelper(newX, newY);
            }
        }
        facingLeft();
    }
    public void onDPressed(){
        int newX = this.x +1;
        int newY = this.y;
        if(x< worldGen.worldSize -1){
            if(worldGen.worldGrid[newX][newY] == 0) {
                moveHelper(newX, newY);
            }
        }
        facingRight();
    }
    public void onWPressed(){
        int newX = this.x;
        int newY = this.y -1;
        if(y>0){
            if(worldGen.worldGrid[newX][newY] == 0) {
                moveHelper(newX, newY);
            }
        }
        facingUp();
    }
    public void onSPressed(){
        int newX = this.x;
        int newY = this.y +1;
        if(y < worldGen.worldHeight -1){
            if(worldGen.worldGrid[newX][newY] ==0 ) {
                moveHelper(newX, newY);
            }
        }
        facingDown();
    }
    public void onQPressed(){
        int newX = this.x -1;
        int newY = this.y -1;
        if(y < worldGen.worldHeight -1 && y > -1 && x < worldGen.worldSize -1 && x > 0){
            if(worldGen.worldGrid[newX][newY] ==0 ) {
                moveHelper(newX, newY);
            }
        }
        facingLeftUp();
    }
    public void onEPressed(){
        int newX = this.x +1;
        int newY = this.y -1;
        if(y < worldGen.worldHeight -1 && y > -1 && x < worldGen.worldSize -1 && x > 0){
            if(worldGen.worldGrid[newX][newY] ==0 ) {
                moveHelper(newX, newY);
            }
        }
        facingRightUp();
    }
    public void moveHelper(int newX, int newY){
        worldGen.setBlock.set(this.x, this.y, this.backgroundBlockPlayer);
        this.backgroundBlockPlayer = worldGen.getBlock.get(newX, newY);
        this.x = newX;
        this.y = newY;
        worldGen.setBlock.set(newX, newY,blocks.PLAYER.ordinal());
    }

   public void setPlayer(int newX, int newY){
        worldGen.worldGrid[newX][newY] = this.blockType;
    }

    public void gravity(){
        int newX = this.x;
        int newY = this.y + 1;
        if(y < worldGen.worldHeight -1){
            if(worldGen.worldGrid[newX][newY] ==blocks.SKY.ordinal() || worldGen.worldGrid[newX][newY] == blocks.CAVEAIR.ordinal() ) {
                moveHelper(newX, newY);
            }
        }
    }
    public void facingUp(){
        this.facingDirection = 0;
    }
    public void facingRight(){
        this.facingDirection = 90;
    }
    public void facingDown(){
        this.facingDirection = 180;
    }
    public void facingLeft(){
        this.facingDirection = 270;
    }
    public void facingRightUp(){
        this.facingDirection = 35;
    }
    public void facingLeftUp(){
        this.facingDirection = 305;
    }

    public void getFacingCoordinates(){
        this.facingBlock.facingX = this.x;
        this.facingBlock.facingY = this.y;

        if(this.facingDirection == 0){
            this.facingBlock.facingY -=1;
        }else if (this.facingDirection == 90){
            this.facingBlock.facingX +=1;
        }else if(this.facingDirection == 180){
            this.facingBlock.facingY +=1;
        }else if(this.facingDirection == 270){
            this.facingBlock.facingX -=1;
        }else if(this.facingDirection == 35){
            this.facingBlock.facingX +=1;
            this.facingBlock.facingY -=1;
        }else if(this.facingDirection == 305){
            this.facingBlock.facingX -=1;
            this.facingBlock.facingY -=1;
        }
    }
    public void mine(){
        getFacingCoordinates();
        int targetMineX = this.facingBlock.facingX;
        int targetMineY = this.facingBlock.facingY;

        if(targetMineX >= 0 && targetMineX < worldGen.worldSize && targetMineY > 0 && targetMineY < worldGen.worldHeight) {
            int toBeMined = worldGen.getBlock.get(targetMineX, targetMineY);
            if(toBeMined != blocks.CAVEAIR.ordinal() && toBeMined != blocks.SKY.ordinal() && toBeMined !=blocks.BEDROCK.ordinal() && toBeMined != blocks.PLAYER.ordinal() && toBeMined != blocks.LAVA.ordinal()) {
                worldGen.setBlock.set(targetMineX, targetMineY, blocks.SKY.ordinal());
            }
        }
    }

}