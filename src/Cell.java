/**
 * @author David Marcos Mazón and Sara Timermans Pastor
 * @since December 2016
 * @version 1.0
 */
public class Cell {
    private boolean explored, wall, haveItem, haveEnemy; //wall means it's actually NOT wall TODO change name ¬¬
    private int itemId, enemyId;
    private long red, green, blue;
    private boolean room;

    Cell(){
        this.red = 112;
        this.green = 112;
        this.blue = 112;
    }


    //getters and setter for all the fields
    public boolean getHaveItem(){
        return haveItem;
    }
    public boolean getExplored(){
        return explored;
    }
    public boolean getWall(){
        return wall;
    }
    public int getRed(){return (int)red;};
    public int getGreen(){return (int)green;};
    public int getBlue(){return (int)blue;};
    public int getItemId(){
        return itemId;
    }

    public void setHaveEnemy(boolean haveEnemy){
        this.haveEnemy = haveEnemy;
    }
    public void setRoom(boolean room){
        this.room = room;
    }
    public void setItemId(int Id){
        this.itemId = Id;
    }
    public void setEnemyId(int Id){
        this.enemyId = Id;
    }
    public void setHaveItem(boolean item){
        haveItem = item;
    }
    public void setWall(boolean wall){
        this.wall = wall;
    }
    public void setExplored(boolean explored){
        this.explored = explored;
    }
    public void setRed(long red){
        this.red = red;
    }
    public void setBlue(long blue){
        this.blue = blue;
    }
    public void setGreen(long green){
        this.green = green;
    }
}
