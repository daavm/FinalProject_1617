/**
 * Created by david on 11/30/2016.
 */
public class Enemy {
    String name;
    int health, power, x, y;
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
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
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