import minidungeon.MiniDungeonGUI;

public class Main {

	public static void main(String[] args) throws InterruptedException {
        Floor[] floors = new Floor[5];
        MiniDungeonGUI gui = new MiniDungeonGUI(50, 50);
        gui.setVisible(true);
        int eyeCount = 0; //eyecount goes here because there can only be 1 eye per 5 levels, and this game has only 5 levels(floors), so will only appear once
        for (int ii = 0; ii < floors.length; ii++) {
            floors[ii] = new Floor(ii);
            for (int x1 = 1; x1 > 0; x1++) { //set start coordinates
                floors[ii].setStartX((int) (Math.random() * 50));
                floors[ii].setStartY((int) (Math.random() * 50));
                if (floors[ii].getCells()[floors[ii].getStartX()][floors[ii].getStartY()].getWall()) {
                    x1 = -1;
                }
            }
            floors[ii].getCells()[floors[ii].getStartX()][floors[ii].getStartY()].setWall(true);
        }
        Player player = new Player();
        for (int ii = 0; ii < floors.length; ii++) {
            int movements = 0;
            boolean finish = false;
            //colorize stock board (black)
            for (int kk = 0; kk < 50; kk++) {
                for (int jj = 0; jj < 50; jj++) {
                    Cell location = floors[ii].getCells()[kk][jj];
                    if (floors[ii].getCells()[kk][jj].getExplored()) {
                        gui.md_setSquareColor(kk, jj, location.getRed(), location.getGreen(), location.getBlue());
                    } else {
                        gui.md_setSquareColor(kk, jj, 64, 64, 64);
                    }
                }
            }

            //RE-EXPLORE
            if(floors[ii].getExplored()) {
                for(int jj = 0; jj < 50; jj++){
                    for(int kk = 0; kk <50; kk++){
                        if(floors[ii].getCells()[jj][kk].getExplored()){
                            gui.md_setSquareColor(jj, kk, floors[ii].getCells()[jj][kk].getRed(), floors[ii].getCells()[jj][kk].getGreen(), floors[ii].getCells()[jj][kk].getBlue());
                        }
                    }
                }
            }
            //show alive enemies, onthefloor items,etc...
            //seeing first if the boss is killed so the floor is explored...etc floor.isExplored() <-- add

            for (int x1 = 1; x1 > 0; x1++) {
                floors[ii].setTrapdoorX((int) (Math.random() * 50));
                floors[ii].setTrapdoorY((int) (Math.random() * 50));
                if (floors[ii].getCells()[floors[ii].getTrapdoorX()][floors[ii].getTrapdoorY()].getWall() && floors[ii].getTrapdoorX() != floors[ii].getStartX() ) {
                    x1 = -1;
                    floors[ii].getCells()[floors[ii].getTrapdoorX()][floors[ii].getTrapdoorY()].setRed(112);
                    floors[ii].getCells()[floors[ii].getTrapdoorX()][floors[ii].getTrapdoorY()].setGreen(65);
                    floors[ii].getCells()[floors[ii].getTrapdoorX()][floors[ii].getTrapdoorY()].setBlue(34);
                }
            }

            //Decide if a floor has an Eye item or not
            int countEye;
            if ((ii - 4) >= 0) {
                countEye = (ii - 4);
            } else {
                countEye = 0;
            }
            for (int kk = countEye; kk < ii; kk++) {
                if (floors[kk].getEye()) {
                    floors[ii].setEye(false);
                    kk += ii;
                }
            }
            //ITEMS GENERATION
            int id_count = 1;
            for (int jj = 2; jj < 30; jj++) { //number of items to generate
                int swordCount = 0, heartCount = 0;
                id_count++;
                if(generateItems(gui, floors, ii, jj, id_count, eyeCount)==1) {
                    eyeCount++;
                }
                //TODO only to test maps
                gui.md_setSpriteVisible(id_count, true);
            }
            id_count++;
            for (int jj = 0; jj < 10; jj++){
                generateEnemies(gui, floors, ii, jj, id_count);
                id_count++;
            }
            gui.md_setSquareColor(floors[ii].getTrapdoorX(), floors[ii].getTrapdoorY(), 23, 43, 75);
            int x = floors[ii].getStartX(), y = floors[ii].getStartY();
            if(ii > 0){
                floors[ii].getCells()[x][y].setRed(198);
                floors[ii].getCells()[x][y].setGreen(103);
                floors[ii].getCells()[x][y].setBlue(190);
            }
            gui.md_setTextFood(player.getFood());
            gui.md_setTextHealthCurrent(player.getHealth());
            gui.md_setTextFloor(-ii);
            gui.md_setTextHealthMax(player.getHealth());
            gui.md_setTextPerception(player.getPerception());
            gui.md_setTextStrength(player.getPower());
            //sprite of the player generated
            gui.md_addSprite(1, "white-queen.png", true);
            gui.md_setSpriteVisible(1, true);

            gui.md_moveSprite(1, floors[ii].getStartX(), floors[ii].getStartY());

            //paint start coordinates
            gui.md_setSquareColor(x, y, floors[ii].getCells()[x][y].getRed(), floors[ii].getCells()[x][y].getGreen(), floors[ii].getCells()[x][y].getBlue());



            //MOVE
            int moveCount = 749;
            int floorNumber;
            do {
                String lastAction = gui.md_getLastAction().toLowerCase();

                if (lastAction.equals("left") && (x-1) >= 0) {
                    catchItem(gui, player, (x-1), y, ii, floors);
                    if (x > 0 && floors[ii].getCells()[x - 1][y].getWall()) {
                        gui.md_moveSprite(1, (x - 1), y);
                        x--;
                        player.setFood((player.getFood() - 1));
                        gui.md_setTextFood(player.getFood());
                        movements++;
                    }
                } else if (lastAction.equals("right") && (x+1) < 50) {
                    catchItem(gui, player, (x+1), y, ii, floors);
                    if (x < 49 && floors[ii].getCells()[x + 1][y].getWall()) {
                        gui.md_moveSprite(1, (x + 1), y);
                        x++;
                        player.setFood((player.getFood() - 1));
                        gui.md_setTextFood(player.getFood());
                        movements++;
                    }
                } else if (lastAction.equals("up") && (y-1) >= 0) {
                    catchItem(gui, player, x, (y-1), ii, floors);
                    if (y > 0 && floors[ii].getCells()[x][y - 1].getWall()) {
                        gui.md_moveSprite(1, x, (y - 1));
                        y--;
                        player.setFood((player.getFood() - 1));
                        gui.md_setTextFood(player.getFood());
                        movements++;
                    }
                } else if (lastAction.equals("down") && (y+1) < 50) {
                    catchItem(gui, player, x, (y+1), ii, floors);
                    if (y < 49 && floors[ii].getCells()[x][y + 1].getWall()) {
                        gui.md_moveSprite(1, x, (y + 1));
                        gui.md_repaintBoard();
                        y++;
                        player.setFood((player.getFood() - 1));
                        gui.md_setTextFood(player.getFood());
                        movements++;
                    }
                }
                //Show and set cells explored by perception
                if (x + player.getPerception() <= 49) {
                    for(int perception = player.getPerception(); perception > 0; perception -= 1) {
                        paintPerception(x + perception, y, gui, floors, ii);
                    }
                }
                if (x - player.getPerception() >= 0) {
                    for(int perception = player.getPerception(); perception > 0; perception -= 1) {
                        paintPerception(x - perception, y, gui, floors, ii);
                    }
                }
                if (y + player.getPerception() <=  49) {
                    for(int perception = player.getPerception(); perception > 0; perception -= 1) {
                        paintPerception(x, y + perception, gui, floors, ii);
                    }
                }
                if (y - player.getPerception() >= 0) {
                    for(int perception = player.getPerception(); perception > 0; perception -= 1) {
                        paintPerception(x, y - perception, gui, floors, ii);
                    }
                }
                //paint extra cells when perception is 2
                if(player.getPerception() > 1) {
                    Cell loc = floors[ii].getCells()[x][y];
                    if(x - 1 >= 0 && y -1 >= 0){
                        paintCells(x-1, y-1, gui, floors, ii);
                    }
                    if(x + 1 <= 49 && y -1 >= 0) {
                        paintCells(x+1, y-1, gui, floors, ii);
                    }
                    if(x - 1 >= 0 && y + 1 <= 49) {
                        paintCells(x-1, y+1, gui, floors, ii);

                    }
                    if(x + 1 <= 49 && y + 1 <= 49) {
                        paintCells(x+1, y+1, gui, floors, ii);
                    }
                }
                moveCount++;
                if(moveCount == 750) {
                    for (int kk = 0; kk < 10; kk++) { //TODO MOVE ENEMIES
                        int move = (int) (Math.random() * 4);
                        int enemX = floors[ii].getEnemies()[kk].getX(), enemY = floors[ii].getEnemies()[kk].getY();
                        if (move == 0 && (enemX + 1) >= 0 && (enemX+1) < 50 && floors[ii].getCells()[enemX+1][enemY].getWall()) {
                            gui.md_moveSprite(kk + 30,
                                    (enemX + 1),
                                    (enemY));
                            floors[ii].getEnemies()[kk].setX((floors[ii].getEnemies()[kk].getX() + 1));
                        } else if (move == 1 && (enemX - 1) >= 0 && (enemX - 1) < 50 && floors[ii].getCells()[enemX-1][enemY].getWall()) {
                            gui.md_moveSprite(kk + 30,
                                    (enemX - 1),
                                    enemY);
                            floors[ii].getEnemies()[kk].setX((floors[ii].getEnemies()[kk].getX() - 1));
                        } else if (move == 2 && enemY+1 >= 0 && enemY+1 < 50 && floors[ii].getCells()[enemX][enemY+1].getWall()) {
                            gui.md_moveSprite(kk + 30,
                                    enemX,
                                    (enemY + 1));
                            floors[ii].getEnemies()[kk].setY((floors[ii].getEnemies()[kk].getY() + 1));
                        } else if (move == 3 && enemY-1 >= 0 && enemY-1 < 50&& floors[ii].getCells()[enemX][enemY-1].getWall()) {
                            gui.md_moveSprite(kk + 30,
                                    enemX,
                                    (enemY - 1));
                            floors[ii].getEnemies()[kk].setY((floors[ii].getEnemies()[kk].getY() - 1));
                        }
                    }
                    moveCount = 0;
                }
                Thread.sleep(1);
                floorNumber = ii;
                if(floors[ii].getCells()[x][y].getRed() == 112 && movements > 0){
                    floors[ii].setPassed(true);
                    floors[ii].setExplored(true);
                    for(int jj = 2; jj < 10; jj++){
                        gui.md_setSpriteVisible(jj, false);
                    }
                } else if(floors[ii].getCells()[x][y].getRed() == 198 && movements > 0){

                    finish = true;
                    floors[ii-1].setPassed(false);
                    ii -= 2;
                    for(int jj = 2; jj < 10; jj++){
                        gui.md_setSpriteVisible(jj, false);
                    }
                }
            } while (!floors[floorNumber].isPassed() && !finish);

        }
    }
    public static void catchItem(MiniDungeonGUI gui, Player player, int x, int y, int ii, Floor[] floors){
        Cell location = floors[ii].getCells()[x][y];
        if (location.getHaveItem() && !floors[ii].getItems()[location.getItemId()].getTaken()) {
            floors[ii].getItems()[location.getItemId()].setTaken(true);
            gui.md_setSpriteVisible((location.getItemId()), false);
            String item_name = floors[ii].getItems()[location.getItemId()].getName();
            switch (item_name){
                case "Sword":
                    player.setPower(player.getPower()+1);
                    gui.md_setTextStrength(player.getPower());
                    break;
                case "Heart":
                    player.setMaxHealth(player.getMaxHealth()+5);
                    gui.md_setTextHealthMax(player.getMaxHealth());
                    gui.md_setTextHealthCurrent(player.getHealth());
                    break;
                case "Eye":
                    player.setPerception(player.getPerception()+1);
                    gui.md_setTextPerception(player.getPerception());
                    break;
                case "Potion":
                    if(player.getHealth() <= (player.getMaxHealth()-10)){
                        player.setHealth(player.getHealth() + 10);
                    } else {
                        player.setHealth(player.getHealth() + (player.getMaxHealth() - player.getHealth()));
                    }
                    gui.md_setTextHealthCurrent(player.getHealth());
                    break;
                case "Gold":
                    player.setGold(player.getGold() + ((int)(Math.random()*901 + 100)));
                    gui.md_setTextGold(player.getGold());
                    break;
                case "Apple":
                    player.setFood(player.getFood() + ((int)(Math.random()*141 + 40)));
                    gui.md_setTextFood(player.getFood());
                    break;
            }
        }
    }
    public static void paintCells(int x, int y, MiniDungeonGUI gui, Floor[] floors, int ii){
        if(x >= 0 && x < 50 && y >= 0 && y < 50) {
            Cell loc = floors[ii].getCells()[x][y];
            if (loc.getWall()) {
                gui.md_setSquareColor(x, y, loc.getRed(), loc.getGreen(), loc.getBlue());
            } else {
                gui.md_setSquareColor(x, y, loc.getRed(), loc.getGreen(), loc.getBlue());
            }
        }
    }
    public static void paintPerception(int x, int y, MiniDungeonGUI gui, Floor[] floors, int ii){
        if(x >= 0 && x < 50 && y >= 0 && y < 50) {
            Cell location = floors[ii].getCells()[x][y]; //Cell data type to avoid typing a lot of code many times
            if (location.getWall()) {
                gui.md_setSquareColor(x, (y), location.getRed(), location.getGreen(), location.getBlue());
            } else {
                gui.md_setSquareColor(x, (y), 112, 112, 112);
            }
            location.setExplored(true);
            if (location.getHaveItem() && !floors[ii].getItems()[location.getItemId()].getTaken()) {
                gui.md_setSpriteVisible(location.getItemId(), true);
            }
        }
    }
    public static int generateItems(MiniDungeonGUI gui, Floor[] floors, int ii, int jj, int id_count, int eyeCount){
        String id = floors[ii].getItems()[jj].getName();
        int swordCount = floors[ii].getSwordCount(), heartCount = floors[ii].getHeartCount(), returned = 0;
        boolean repeated = true;
        do{
            if ((id.equals("Sword") && swordCount > 0)) {
                id = floors[ii].getNewItem(jj);
                if(!id.equals("Sword")){
                    if(id.equals("Heart") && heartCount == 0){
                        repeated = false;
                    } else if(id.equals("Eye") && eyeCount == 0){
                        repeated = false;
                    } else if(!id.equals("Heart") && !id.equals("Eye")){
                        repeated = false;
                    }
                }
            } else if(id.equals("Heart") && heartCount > 0){
                id = floors[ii].getNewItem(jj);
                if(!id.equals("Heart")){
                    if(id.equals("Sword") && heartCount == 0){
                        repeated = false;
                    } else if(id.equals("Eye") && eyeCount == 0){
                        repeated = false;
                    } else if(!id.equals("Sword") && !id.equals("Eye")){
                        repeated = false;
                    }
                }
            } else if(id.equals("Eye") && eyeCount > 0){
                id = floors[ii].getNewItem(jj);
                if(!id.equals("Eye")){
                    if(id.equals("Sword") && heartCount == 0){
                        repeated = false;
                    } else if(id.equals("Heart") && eyeCount == 0){
                        repeated = false;
                    } else if(!id.equals("Sword") && !id.equals("Heart")){
                        repeated = false;
                    }
                }
            } else {
                repeated = false;
            }
        }while(repeated == true);
        //gui.md_addSprite(id_count, id.toLowerCase() + ".png", true); works but it does not add eye, heart and sword count
        switch (id) {
            case "Apple":
                gui.md_addSprite(id_count, "apple.png", true);
                break;
            case "Eye":
                gui.md_addSprite(id_count, "eye.png", true);
                returned = 1;
                break;
            case "Gold":
                gui.md_addSprite(id_count, "gold.png", true);
                break;
            case "Heart":
                gui.md_addSprite(id_count, "heart.png", true);
                floors[ii].setHeartCount(1);
                break;
            case "Sword":
                gui.md_addSprite(id_count, "sword.png", true);
                floors[ii].setSwordCount(1);
                break;
            case "Potion":
                gui.md_addSprite(id_count, "potion.png", true);
                break;
            default:
                break;
        }

        floors[ii].getItems()[jj].setId(jj);
        for (int x1 = 1; x1 > 0; x1++) {
            int Ix = (int) (Math.random() * 50), Iy = (int) (Math.random() * 50);
            if (floors[ii].getCells()[Ix][Iy].getWall() && !floors[ii].getCells()[Ix][Iy].getHaveItem() && Ix != floors[ii].getStartX() && Iy != floors[ii].getStartY()) { //activate when having more cells than items created
                x1 = -1;
                gui.md_moveSprite(id_count, Ix, Iy);
                floors[ii].getCells()[Ix][Iy].setHaveItem(true);
                floors[ii].getCells()[Ix][Iy].setItemId(jj);
            }
        }
        return returned;
    }
    public static void generateEnemies(MiniDungeonGUI gui, Floor[] floors, int ii, int jj, int id_count){
        String enemy = floors[ii].getEnemies()[jj].getName();
        gui.md_addSprite(id_count, enemy, true);
       gui.md_setSpriteVisible(id_count, true);
        for (int x1 = 1; x1 > 0; x1++) {
            int Ix = (int) (Math.random() * 50), Iy = (int) (Math.random() * 50);
            if (floors[ii].getCells()[Ix][Iy].getWall() && Ix != floors[ii].getStartX() && Iy != floors[ii].getStartY()) { //activate when having more cells than items created
                x1 = -1;
                floors[ii].getEnemies()[jj].setX(Ix);
                floors[ii].getEnemies()[jj].setY(Iy);
                gui.md_moveSprite(id_count, Ix, Iy);
            }
        }
    }
}

