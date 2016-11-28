import com.sun.javafx.runtime.SystemProperties;

public class Floor {
    private int startX, startY, number, trapdoorX, trapdoorY;
    private Cell[][] cells = new Cell[50][50];
    private Item[] items = new Item[40];
    private boolean passed = false, eye;

    Floor(int number) {
        this.number = number;
        for (int ii = 0; ii < 50; ii++) {
            for (int jj = 0; jj < 50; jj++) {
                cells[ii][jj] = new Cell();
            }
        }
        int eyeIf = (int)(Math.random()*2);
        switch (eyeIf){
            case 0: eye = true;
                break;
            case 1: eye = false;
                break;
        }

        /*for (int xx = 9; xx < 14; xx++) {
            for (int yy = 4; yy < 8; yy++) { //Code to create rooms and corridors
                setCellsTrue(xx, yy);
            }
        }
        for (int xx = 14; xx < 23; xx++) {
            int yy = 6;
            setCellsTrue(xx, yy);
        }
        for (int xx = 3; xx <= 9; xx++) {
            int yy = 7;
            setCellsTrue(xx, yy);
        }
        for (int xx = 3; xx <= 17; xx++) {
            int yy = 4;
            setCellsTrue(yy, xx);
        }
*/
        /*for (int kk = 0; kk < 50; kk++) { //TODO just for testing maps
            for (int jj = 0; jj < 50; jj++) {
                cells[kk][jj].setExplored(true);
            }
        }*/
        int x = (int)(Math.random()*30), y = (int)(Math.random()*30);
        int lastDirection = 1; //let's just start with north- 1:north 2: South 3: East 4: west
        for(int ii = 0; ii < 60; ii++) {
            int roomCorridor = (int)(Math.random()*7); //0-6 corridor, 7 room
            if(roomCorridor == 7){ //ROOM
                int height = (int)(Math.random()*6+1);
                int base = (int)(Math.random()*5+1);

                int corridorNumber = (int)(Math.random()*3+1);
                int firstCorridor = (int)(Math.random()*3+1);
                int secondCorridor = (int)(Math.random()*3+1);
                for(int jj = 1; jj > 0; jj++){
                    if(secondCorridor != firstCorridor){
                        jj = -1;
                    } else {
                        secondCorridor = (int)(Math.random()*3+1);
                    }
                }
                int thirdCorridor = (int)(Math.random()*3+1);
                for(int jj = 1; jj > 0; jj++){
                    if(thirdCorridor != firstCorridor && thirdCorridor != secondCorridor){
                        jj = -1;
                    } else {
                        thirdCorridor = (int)(Math.random()*3+1);
                    }
                }
            } else { //CORRIDOR //TODO don't generate 2 corridors together (x - x+-1 or y - y+-1)
                //TODO sometimes corridors are not accesible (not connected)
                int length = (int)(Math.random()*15+1);
                for(int jj = 1; jj > 0; jj++){
                    int direction = (int)(Math.random()*4);

                    switch (direction){
                        case 0:
                            if(direction != lastDirection){
                                lastDirection = direction;
                                jj = -1;
                            }
                            break;
                        case 1:
                            if(direction != lastDirection){
                                lastDirection = direction;
                                jj = -1;
                            }
                            break;
                        case 2:
                            if(direction != lastDirection){
                                lastDirection = direction;
                                jj = -1;
                            }
                            break;
                        case 3:
                            if(direction != lastDirection){
                                lastDirection = direction;
                                jj = -1;
                            }
                            break;
                    }
                }
                for(int jj = 0; jj < length; jj++){
                    System.out.println(x + y);
                    switch (lastDirection){
                        case 0:
                            y += 1;
                            if(y < 50 && y >= 0 && x >= 0 && x < 50){
                                setCellsTrue(x, y);
                            }
                            break;
                        case 1:
                            y -=1;
                            if(y < 50 && y >= 0 && x >= 0 && x < 50){
                                setCellsTrue(x, y);
                            }
                            break;
                        case 2:
                            x += 1;
                            if(y < 50 && y >= 0 && x >= 0 && x < 50){
                                setCellsTrue(x, y);
                            }
                            break;
                        case 3:
                            x -= 1;
                            if(y < 50 && y >= 0 && x >= 0 && x < 50){
                                setCellsTrue(x, y);
                            }
                            break;
                    }
                }
            }
        }

        for (int ii = 0; ii < 40; ii++) {
            items[ii] = new Item((int) (Math.random() * 6));
        }

    }
    public void setCellsTrue(int x, int y){
        cells[x][y].setWall(true);
        cells[x][y].setBlue(226);
        cells[x][y].setGreen(191);
        cells[x][y].setRed(170);
    }
    public int getTrapdoorX(){
        return trapdoorX;
    }
    public int getTrapdoorY(){
        return trapdoorY;
    }
    public int getStartX() {
        return startX;
    }
    public int getNumber(){
        return number;
    }
    public Item[] getItems(){
        return items;
    }
    public int getStartY() {
        return startY;
    }
    public String getNewItem(int jj){ //Method to get a new/different item
        String id = "Apple";
        int getItem = (int)(Math.random()*6);
        String[] names = new String[] {"Sword", "Heart", "Eye", "Potion", "Gold", "Apple"};
        this.getItems()[jj].setName(names[getItem]);
        return this.getItems()[jj].getName();
    }
    public boolean getPassed() {
        return passed;
    }
    public boolean getEye(){
        return eye;
    }

    public void setTrapdoorX(int x){
        trapdoorX = x;
    }
    public void setTrapdoorY(int y){
        trapdoorY = y;
    }
    public void setEye(boolean eye){
        this.eye = eye;
    }
    public Cell[][] getCells() {
        return cells;
    }
    public void setPassed(boolean passed){
        this.passed =passed;
    }
	public void setStartX(int startX){
		this.startX = startX;
	}
	public void setStartY(int startY){
		this.startY = startY;
	}

}
