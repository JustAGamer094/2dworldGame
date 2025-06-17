package justagen;

@FunctionalInterface
interface setBlock {
    void set(int x, int y, int blockType);
}
@FunctionalInterface
interface getBlock{
    int get(int x, int y);
}

@FunctionalInterface
interface fillSqaure{
    void fill(int x1,  int y1, int x2, int y2, int typeBlock);
}

@FunctionalInterface
interface createCircle{
    void circle(int x1, int y1, int radius, int typeBlock);
}
@FunctionalInterface
interface getCharacterFromNumber{
    String getCharacter(int blockType);
}
@FunctionalInterface
interface setToLava{
    void setLava();
}
@FunctionalInterface
interface generateTree{
    void setTree();
}
@FunctionalInterface
interface setSun{
    void set();
}
@FunctionalInterface
interface fixAir{
    void fix();
}