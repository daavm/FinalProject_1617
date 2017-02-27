/**
 * @author David Marcos Mazón and Sara Timermans Pastor
 * @since December 2016
 * @version 1.0
 */
public class Enemy {
    private String name;
    private int health, power, x, y, xDeath = 0, yDeath = 0, id, gen= 0;
    private boolean visible = true, seen = false;
    private boolean test = false;
    //power puede ser 1.5

    Enemy(int ii){
        switch (ii){
            //When an enemy is generated, a random number between 0 and 3 is generated as well. Depending on it, we will get an
            //enemy or another
            case 0:
                this.name = "black-bishop.png";
                setHealth(10);
                this.gen = ii;
                setPower(1);
                break;
            case 1:
                this.name = "blue-king.png";
                setHealth(20);
                this.gen = ii;
                setPower(3);
                break;
            case 2:
                this.name = "white-king.png";
                setHealth(10);
                this.gen = ii;
                setPower(1);
                break;
        }
    }

    //getters and setters
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
    public int getGen(){ return gen;}
    public int getId(){ return id;}
    public int getY(){
        return y;
    }
    public int getxDeath(){ return xDeath;}
    public int getyDeath(){return yDeath;}
    public void setVisible(boolean visible){ this.visible = visible;}
    public boolean isSeen(){ return seen;}

    public void setX(int x){
        this.x = x;
    }
    public boolean isVisible(){ return visible;}
    public void setSeen(boolean seen){ this.seen = seen;}
    public void setY(int y){
        this.y = y;
    }
    public void setId(int id){ this.id = id;}
    public void setxDeath(int xDeath){ this.xDeath = xDeath;}
    public void setyDeath(int yDeath){ this.yDeath = yDeath;}
    public void setHealth(int health){
        this.health = health;
    }
    public void setPower(int power){
        this.power = power;
    }
}
