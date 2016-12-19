/**
 * @author David Marcos Mazón and Sara Timermans Pastor
 * @since December 2016
 * @version 1.0
 */
public class Item {
	private String name;
	private int id, x, y;
	private boolean taken = false, seen = false;

	
	Item(int item){
		String[] names = new String[] {"Sword", "Heart", "Eye", "Potion", "Gold", "Apple"};
		//the item will be one of the specified in the list depending on the "item" random number generated in the main class
		this.name = names[item];
		x = -1; y = -1;
	}
	
	public void setId(int id){
		this.id = id;
	}
	public void setX(int x){ this.x = x;}
	public void setY(int y){ this.y = y;}
	public void setSeen(boolean seen){ this.seen = seen;}
	public int getX(){ return x;}
	public int getY(){ return y;}
    public boolean getTaken(){
        return taken;
    }
    public boolean getSeen(){ return seen;}
    public void setTaken(boolean taken){
        this.taken = taken;
    }
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
}
