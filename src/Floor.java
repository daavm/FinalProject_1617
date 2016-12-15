public class Floor {
    private int startX, startY, number, trapdoorX, trapdoorY, heartCount, swordCount, eyeCount;
    private Cell[][] cells = new Cell[50][50];
    private Item[] items = new Item[40];
    private Enemy[] enemies = new Enemy[10];
    private Room[] rooms = new Room[(int)Math.random()*4+7];
    private boolean passed = false, eye, explored;

    Floor(int number) {
        this.number = number;
        for (int ii = 0; ii < 50; ii++) {
            for (int jj = 0; jj < 50; jj++) {
                cells[ii][jj] = new Cell();
            }
        }
        for (int ii = 0; ii < rooms.length; ii++) {
            rooms[ii] = new Room(rooms, ii);
        }
        for (int ii = 0; ii < rooms.length; ii++) {
            int x = rooms[ii].getOriginX(), y = rooms[ii].getOriginY();
            for (int kk = 0; kk < rooms[ii].getHeight(); kk++) {
                for (int jj = 0; jj < rooms[ii].getBase(); jj++) {
                    if (x + kk < 50 && y + jj < 50) {
                        setCellsTrue((x + kk), (y + jj));
                        if (x - 1 >= 0) {
                            cells[(x - 1)][(jj)].setRoom(true);
                        }
                        if (x + rooms[ii].getBase() < 50) {
                            cells[(x + rooms[ii].getBase())][(jj)].setRoom(true);
                        }
                        if (y - 1 >= 0) {
                            cells[kk][(y - 1)].setRoom(true);
                        }
                        if (y + rooms[ii].getHeight() < 50) {
                            cells[kk][(y + rooms[ii].getHeight())].setRoom(true);
                        }
                    }
                }
            }
        }
        int eyeIf = (int) (Math.random() * 2);
        switch (eyeIf) {
            case 0:
                eye = true;
                break;
            case 1:
                eye = false;
                break;
        }

   /*    for (int kk = 0; kk < 50; kk++) { //TODO just for testing maps
            for (int jj = 0; jj < 50; jj++) {
                cells[kk][jj].setExplored(true);
            }
        }*/

        for (int ii = 0; ii < rooms.length; ii++) {
            int x = 0, y = 0;
            for (int jj = 1; jj > 0; jj++) {
                int addX = 0, addY = 0;
                for (int kk = 1; kk > 0; kk++) {
                    addX = (int) (Math.random() * 5 - 2);
                    addY = (int) (Math.random() * 5 - 2);
                    if (addX != 0 && addY != 0) {
                        kk = -1;
                    }
                }

                x = (rooms[ii].getOriginX() + addX);//TODO use these for corridors entry (+height/base and -1/+1)
                y = (rooms[ii].getOriginY() + addY);
                if (x >= 0 && x < 50 && y >= 0 && y < 50) {
                    jj = -1;
                }
            }

          /*  for(int jj = 0; jj < 50; jj++){
                setCellsTrue(jj, y);
                setCellsTrue(x, jj);
                cells[jj][(y)].setCorridor(true);
                cells[x][(jj)].setCorridor(true);

            }*/
          for(int cc = 0; cc < rooms.length; cc++){
              rooms[cc].getNearestRoom(rooms);
              int nearest = 0;
              if(cc != 0){ nearest = cc-1;} else {
                  nearest = cc+2;
              }
              int finalX = 0;
              if (rooms[nearest].getOriginX() > rooms[cc].getOriginX() && rooms[nearest].getOriginY() > rooms[cc].getOriginY()) {
                  for(int jj = rooms[cc].getOriginX(); jj <= rooms[nearest].getOriginX(); jj++){
                      setCellsTrue(jj, rooms[cc].getOriginY());
                      finalX = jj;
                  }
                  for(int kk = rooms[cc].getOriginY(); kk <= rooms[nearest].getOriginY(); kk++){
                      setCellsTrue(finalX,kk);
                  }

              } else if (rooms[nearest].getOriginX() > rooms[cc].getOriginX() && rooms[nearest].getOriginY() < rooms[cc].getOriginY()) {
                  for(int jj = rooms[cc].getOriginX(); jj <= rooms[nearest].getOriginX(); jj++){
                      setCellsTrue(jj, rooms[cc].getOriginY());
                      finalX = jj;
                  }
                  for(int kk = rooms[cc].getOriginY(); kk >= rooms[nearest].getOriginY(); kk--){
                      setCellsTrue(finalX,kk);
                  }
              } else if (rooms[nearest].getOriginX() < rooms[cc].getOriginX() && rooms[nearest].getOriginY() > rooms[cc].getOriginY()) {
                  for(int jj = rooms[cc].getOriginX(); jj >= rooms[nearest].getOriginX(); jj--){
                      setCellsTrue(jj, rooms[cc].getOriginY());
                      finalX = jj;
                  }
                  for(int kk = rooms[cc].getOriginY(); kk <= rooms[nearest].getOriginY(); kk++){
                      setCellsTrue(finalX,kk);
                  }
              } else if (rooms[nearest].getOriginX() < rooms[cc].getOriginX() && rooms[nearest].getOriginY() < rooms[cc].getOriginY()) {
                  for(int jj = rooms[cc].getOriginX(); jj >= rooms[nearest].getOriginX(); jj--){
                      setCellsTrue(jj, rooms[cc].getOriginY());
                      finalX = jj;
                  }
                  for(int kk = rooms[cc].getOriginY(); kk >= rooms[nearest].getOriginY(); kk--){
                      setCellsTrue(finalX,kk);
                  }
              }
              if(cc != (rooms.length-1)){
                  nearest = cc+1;
              } else {
                  nearest = cc-2;
              }
              finalX = 0;
              if (rooms[nearest].getOriginX() > rooms[cc].getOriginX() && rooms[nearest].getOriginY() > rooms[cc].getOriginY()) {
                  for(int jj = rooms[cc].getOriginX(); jj <= rooms[nearest].getOriginX(); jj++){
                      setCellsTrue(jj, rooms[cc].getOriginY());
                      finalX = jj;
                  }
                  for(int kk = rooms[cc].getOriginY(); kk <= rooms[nearest].getOriginY(); kk++){
                      setCellsTrue(finalX,kk);
                  }

              } else if (rooms[nearest].getOriginX() > rooms[cc].getOriginX() && rooms[nearest].getOriginY() < rooms[cc].getOriginY()) {
                  for(int jj = rooms[cc].getOriginX(); jj <= rooms[nearest].getOriginX(); jj++){
                      setCellsTrue(jj, rooms[cc].getOriginY());
                      finalX = jj;
                  }
                  for(int kk = rooms[cc].getOriginY(); kk >= rooms[nearest].getOriginY(); kk--){
                      setCellsTrue(finalX,kk);
                  }
              } else if (rooms[nearest].getOriginX() < rooms[cc].getOriginX() && rooms[nearest].getOriginY() > rooms[cc].getOriginY()) {
                  for(int jj = rooms[cc].getOriginX(); jj >= rooms[nearest].getOriginX(); jj--){
                      setCellsTrue(jj, rooms[cc].getOriginY());
                      finalX = jj;
                  }
                  for(int kk = rooms[cc].getOriginY(); kk <= rooms[nearest].getOriginY(); kk++){
                      setCellsTrue(finalX,kk);
                  }
              } else if (rooms[nearest].getOriginX() < rooms[cc].getOriginX() && rooms[nearest].getOriginY() < rooms[cc].getOriginY()) {
                  for(int jj = rooms[cc].getOriginX(); jj >= rooms[nearest].getOriginX(); jj--){
                      setCellsTrue(jj, rooms[cc].getOriginY());
                      finalX = jj;
                  }
                  for(int kk = rooms[cc].getOriginY(); kk >= rooms[nearest].getOriginY(); kk--){
                      setCellsTrue(finalX,kk);
                  }
              }
              if(cc != (rooms.length-1) && cc != (rooms.length-2)){
                  nearest = cc+2;
              } else {
                  nearest = cc-3;
              }
              finalX = 0;
              if (rooms[nearest].getOriginX() > rooms[cc].getOriginX() && rooms[nearest].getOriginY() > rooms[cc].getOriginY()) {
                  for(int jj = rooms[cc].getOriginX(); jj <= rooms[nearest].getOriginX(); jj++){
                      setCellsTrue(jj, rooms[cc].getOriginY());
                      finalX = jj;
                  }
                  for(int kk = rooms[cc].getOriginY(); kk <= rooms[nearest].getOriginY(); kk++){
                      setCellsTrue(finalX,kk);
                  }

              } else if (rooms[nearest].getOriginX() > rooms[cc].getOriginX() && rooms[nearest].getOriginY() < rooms[cc].getOriginY()) {
                  for(int jj = rooms[cc].getOriginX(); jj <= rooms[nearest].getOriginX(); jj++){
                      setCellsTrue(jj, rooms[cc].getOriginY());
                      finalX = jj;
                  }
                  for(int kk = rooms[cc].getOriginY(); kk >= rooms[nearest].getOriginY(); kk--){
                      setCellsTrue(finalX,kk);
                  }
              } else if (rooms[nearest].getOriginX() < rooms[cc].getOriginX() && rooms[nearest].getOriginY() > rooms[cc].getOriginY()) {
                  for(int jj = rooms[cc].getOriginX(); jj >= rooms[nearest].getOriginX(); jj--){
                      setCellsTrue(jj, rooms[cc].getOriginY());
                      finalX = jj;
                  }
                  for(int kk = rooms[cc].getOriginY(); kk <= rooms[nearest].getOriginY(); kk++){
                      setCellsTrue(finalX,kk);
                  }
              } else if (rooms[nearest].getOriginX() < rooms[cc].getOriginX() && rooms[nearest].getOriginY() < rooms[cc].getOriginY()) {
                  for(int jj = rooms[cc].getOriginX(); jj >= rooms[nearest].getOriginX(); jj--){
                      setCellsTrue(jj, rooms[cc].getOriginY());
                      finalX = jj;
                  }
                  for(int kk = rooms[cc].getOriginY(); kk >= rooms[nearest].getOriginY(); kk--){
                      setCellsTrue(finalX,kk);
                  }
              }
          }
        }


        for (int ii = 0; ii < 40; ii++) {
            items[ii] = new Item((int) (Math.random() * 6));
        }
        for (int ii = 0; ii < 10; ii++) {
            enemies[ii] = new Enemy((int) (Math.random() * 2));
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
    public int getHeartCount(){
        return heartCount;
    }
    public int getSwordCount(){
        return swordCount;
    }
    public void setHeartCount(int heartCount){
        this.heartCount = heartCount;
    }
    public void setExplored(boolean explored){
        this.explored = explored;
    }
    public boolean getExplored(){ return explored;}
    public void setSwordCount(int swordCount){
        this.swordCount = swordCount;
    }
    public int getTrapdoorY(){
        return trapdoorY;
    }
    public int getStartX() {
        return startX;
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
    public boolean isPassed() {
        return passed;
    }
    public boolean getEye(){
        return eye;
    }
    public Enemy[] getEnemies(){
        return enemies;
    }
    public void setTrapdoorX(int x){
        trapdoorX = x;
    }
    public void setTrapdoorY(int y){
        trapdoorY = y;
    }
    public void setEye(boolean eye){
        this.eye = eye;
        if(eye == false){
            eyeCount = 1;
        }
    }
    public int getEyeCount(){
        return eyeCount;
    }
    public void setEyeCount(int eyeCount){
        this.eyeCount = eyeCount;
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