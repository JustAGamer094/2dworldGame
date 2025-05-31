package justagen;

public class player {
    int x;
    int y;
    int blockType;
    private worldGen worldGen;
    int backgroundBlockPlayer;

    public player (int initialX, int initialY, int blockType, worldGen worldGen){
        this.x = initialX;
        this.y = initialY;
        this.blockType = blockType;
        this.worldGen = worldGen;
    }


    public void spawn(){
        this.backgroundBlockPlayer = worldGen.getBlock.check(this.x, this.y);
        this.worldGen.worldGrid[this.x][this.y] = 10;
    }
    public void onAPressed(){
        int newX = this.x -1;
        int newY = this.y;
        if(x > 0) {
            if(worldGen.worldGrid[newX][newY] == 0) {
                moveHelper(newX, newY);
                this.x = this.x - 1;
            }
        }
    }
    public void onDPressed(){
        int newX = this.x +1;
        int newY = this.y;
        if(x< worldGen.worldSize -1){
            if(worldGen.worldGrid[newX][newY] == 0) {
                moveHelper(newX, newY);
                this.x = this.x + 1;
            }
        }
    }
    public void onWPressed(){
        int newX = this.x;
        int newY = this.y -1;
        if(y>0){
            if(worldGen.worldGrid[newX][newY] == 0) {
                moveHelper(newX, newY);
                this.y = this.y - 1;
            }
        }
    }
    public void onSPressed(){
        int newX = this.x;
        int newY = this.y +1;
        if(y < worldGen.worldHeight -1){
            if(worldGen.worldGrid[newX][newY] ==0 ) {
                moveHelper(newX, newY);
                this.y = this.y + 1;
            }
        }
    }
    public void onQPressed(){
        int newX = this.x -1;
        int newY = this.y -2;
        if(y < worldGen.worldHeight -1 && y > 1){
            if(worldGen.worldGrid[newX][newY] ==0 ) {
                moveHelper(newX, newY);
                this.y = this.y - 2;
                this.x = this.x - 1;
            }
        }
    }
    public void onEPressed(){
        int newX = this.x +1;
        int newY = this.y -2;
        if(y < worldGen.worldHeight -1 && y > 1){
            if(worldGen.worldGrid[newX][newY] ==0 ) {
                moveHelper(newX, newY);
                this.y = this.y - 2;
                this.x = this.x + 1;
            }
        }
    }
    public void moveHelper(int newX, int newY){
        worldGen.setBlock.set(this.x, this.y, this.backgroundBlockPlayer);
        this.backgroundBlockPlayer = worldGen.getBlock.check(newX, newY);

    }

   public void setPlayer(){
        worldGen.worldGrid[this.x][this.y] = this.blockType;
    }

    public void gravity(){
        int newX = this.x;
        int newY = this.y + 1;
        if(y < worldGen.worldHeight -1){
            if(worldGen.worldGrid[newX][newY] ==0 ) {
                moveHelper(newX, newY);
                this.y = (this.y + 1);
            }
        }
    }

}