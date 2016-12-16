/**
 * @author David and Sara
 * @since December 2016
 * @version 1.0
 */
public class Item {
	private String name;
	private int id, x, y;
	private boolean visible = true, taken = false, seen = false;

	
	Item(int item){
		String[] names = new String[] {"Sword", "Heart", "Eye", "Potion", "Gold", "Apple"};
		this.name = names[item];
		x = -1; y = -1;
	}
	
	public void setId(int id){
		this.id = id;
	}
	public void setX(int x){ this.x = x;}
	public void setY(int y){ this.y = y;}
	public void setSeen(boolean seen){ this.seen = seen;}
	public boolean getVisible(){
		return visible;
	}
	public int getX(){ return x;}
	public int getY(){ return y;}
	public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public boolean getTaken(){
        return taken;
    }
    public boolean getSeen(){ return seen;}
    public void setTaken(boolean taken){
        this.taken = taken;
    }
	public int getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
}
