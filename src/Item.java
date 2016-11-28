public class Item {
	private String name;
	private int id;
	private boolean visible = true, taken = false;

	
	Item(int item){
		String[] names = new String[] {"Sword", "Heart", "Eye", "Potion", "Gold", "Apple"};
		this.name = names[item];
	}
	
	public void setId(int id){
		this.id = id;
	}
	public boolean getVisible(){
		return visible;
	}
	public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public boolean getTaken(){
        return taken;
    }
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
