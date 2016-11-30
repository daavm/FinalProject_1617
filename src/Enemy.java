/**
 * Created by david on 11/30/2016.
 */
public class Enemy {
    String name;
    int health, power;
    //power puede ser 1.5

    Enemy(int ii){
        switch (ii){
            case 0:
                this.name = "black-bishop.png";
                break;
            case 1:
                this.name = "blue-king.png";
                break;
        }
    }

    public String getName(){
        return name;
    }
    public int getHealth(){
        return health;
    }
    public int getPower(){
        return power;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setHealth(int health){
        this.health = health;
    }
    public void setPower(int power){
        this.power = power;
    }
}
