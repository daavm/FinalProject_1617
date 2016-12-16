/**
 * @author David and Sara
 * @since December 2016
 * @version 1.0
 */
public
class Player {
	private int health, maxHealth, power, perception, food, gold;
	
	Player(){
		setHealth(20);
		setMaxHealth(20);
		setPower(1);
		setPerception(1);
		setFood(500);
	}
	
	public void setHealth(int health){
		this.health = health;
	}
	public void setMaxHealth(int maxHealth){
		this.maxHealth = maxHealth;
	}
	public void setPower(int power){
		this.power = power;
	}
	public void setPerception(int perception){
		this.perception = perception;
	}
	public void setFood(int food){
		this.food = food;
	}
	public void setGold(int gold){
		this.gold = gold;
	}
	public int getGold(){
		return gold;
	}
	public int getHealth(){
		return health;
	}
	public int getMaxHealth(){
		return maxHealth;
	}
	public int getPower(){
		return power;
	}
	public int getPerception(){
		return perception;
	}
	public int getFood(){
		return food;
	}
	
}
