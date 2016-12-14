/**
 * Created by david on 11/23/2016.
 */
public class Cell {
    private boolean explored, wall, haveItem; //wall means it's actually NOT wall TODO change name ¬¬
    private int itemId;
    private long red, green, blue;
    private boolean room, corridor;

    Cell(){
        this.red = 112;
        this.green = 112;
        this.blue = 112;
    }

    public boolean getHaveItem(){
        return haveItem;
    }
    public boolean getExplored(){
        return explored;
    }
    public boolean getWall(){
        return wall;
    }
    public boolean getRoom(){ return room;}
    public boolean getCorridor(){ return corridor;}
    public int getRed(){return (int)red;};
    public int getGreen(){return (int)green;};
    public int getBlue(){return (int)blue;};
    public int getItemId(){
        return itemId;
    }

    public void setRoom(boolean room){
        this.room = room;
    }
    public void setCorridor(boolean corridor){
        this.corridor = corridor;
    }
    public void setItemId(int Id){
        this.itemId = Id;
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
