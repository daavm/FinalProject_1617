/**
 * Created by david on 11/30/2016.
 */
public class Enemy {
    private String name;
    private int health, power, x, y, xDeath, yDeath, id;
    private boolean visible = true;
    //power puede ser 1.5

    Enemy(int ii){
        switch (ii){
            case 0:
                this.name = "black-bishop.png";
                setHealth(10);
                setPower(1);
                break;
            case 1:
                this.name = "blue-king.png";
                setHealth(20);
                setPower(3);
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
    public int getX(){ return x; }
    public int getId(){ return id;}
    public int getY(){
        return y;
    }
    public int getxDeath(){ return xDeath;}
    public int getyDeath(){return yDeath;}
    public void setVisible(boolean visible){ this.visible = visible;}

    public void setX(int x){
        this.x = x;
    }
    public boolean isVisible(){ return visible;}
    public void setY(int y){
        this.y = y;
    }
    public void setId(int id){ this.id = id;}
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
