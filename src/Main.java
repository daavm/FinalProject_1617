/**
* @author David and Sara
* @since December 2016
* @version 1.0
*/
import minidungeon.MiniDungeonGUI;

public class Main {

	public static void main(String[] args) throws InterruptedException {
        Floor[] floors = new Floor[5];
        MiniDungeonGUI gui = new MiniDungeonGUI(50, 50);
        gui.setVisible(true);
        int time = 0;
        int eyeCount = 0; //eyecount goes here because there can only be 1 eye per 5 levels, and this game has only 5 levels(floors), so will only appear once

        //Choose which floor containt an eye.
        int hasEye = (int)(Math.random()*5), reachedFloor = 0;
        System.out.println(hasEye);
        for (int ii = 0; ii < floors.length; ii++) {
            floors[ii] = new Floor(ii);
            if(ii == hasEye){
                floors[ii].setEye(true);
            } else {
                floors[ii].setEye(false);
            }
            for (int x1 = 1; x1 > 0; x1++) { //set start coordinates
                floors[ii].setStartX((int) (Math.random() * 50));
                floors[ii].setStartY((int) (Math.random() * 50));
                if (floors[ii].getCells()[floors[ii].getStartX()][floors[ii].getStartY()].getWall()) {
                    x1 = -1;
                }
            }
            floors[ii].getCells()[floors[ii].getStartX()][floors[ii].getStartY()].setWall(true);
        }

        //Create player
        Player player = new Player();

        //Game starts here
        for (int ii = 0; ii < floors.length; ii++) {
            gui.md_setTextFloor(-ii);
            int movements = 0;
            boolean finish = false;

            //Check if the current floor contains an eye. If it doesn't, put eyeCount on 1, so no eyes are generated
            if(!floors[ii].getEye()){
                eyeCount = 1;
            }

            //Color stock board, checking which cells have already been explored and which not
            for (int kk = 0; kk < 50; kk++) {
                for (int jj = 0; jj < 50; jj++) {
                    Cell location = floors[ii].getCells()[kk][jj];
                    gui.md_setSquareImage(kk, jj, null);
                    if (floors[ii].getCells()[kk][jj].getExplored()) {
                            gui.md_setSquareColor(kk, jj, location.getRed(), location.getGreen(), location.getBlue());

                    } else {
                        gui.md_setSquareColor(kk, jj, 64, 64, 64);
                    }
                }
            }
            //ITEMS GENERATION
            int id_count = 1;
            for (int jj = 2; jj < 30; jj++) { //number of items to generate
                id_count++;
                if (generateItems(gui, floors, ii, jj, id_count, eyeCount) != 0) {
                    eyeCount++;
                }
                //move every item to its position, even if it is still invisible
                gui.md_moveSprite(id_count, floors[ii].getItems()[jj].getX(),floors[ii].getItems()[jj].getY());

                if(floors[ii].getItems()[jj].getSeen() && !floors[ii].getItems()[jj].getTaken()){
                    gui.md_setSpriteVisible(id_count, true);
                    gui.md_moveSprite(id_count, floors[ii].getItems()[id_count].getX(), floors[ii].getItems()[id_count].getY());
                }
            }
            //RE-EXPLORE a floor, when going back to it through a 'back trapdoor'
            if(floors[ii].getExplored()) {
                for(int jj = 0; jj < 50; jj++){
                    for(int kk = 0; kk <50; kk++){
                        if(floors[ii].getCells()[jj][kk].getExplored()){
                            gui.md_setSquareColor(jj, kk, floors[ii].getCells()[jj][kk].getRed(), floors[ii].getCells()[jj][kk].getGreen(), floors[ii].getCells()[jj][kk].getBlue());
                        }
                    }
                }
                for(int jj = 0; jj < 10; jj++){
                    if(floors[ii].getEnemies()[jj].isSeen() && floors[ii].getEnemies()[jj].getHealth() > 0){
                        gui.md_setSpriteVisible(floors[ii].getEnemies()[jj].getId(), true);
                        gui.md_moveSprite(floors[ii].getEnemies()[jj].getId(), floors[ii].getEnemies()[jj].getX(), floors[ii].getEnemies()[jj].getY());
                    }
                    if(floors[ii].getEnemies()[jj].getxDeath() != 0 && floors[ii].getEnemies()[jj].getyDeath() != 0){
                        gui.md_setSquareImage(floors[ii].getEnemies()[jj].getxDeath(), floors[ii].getEnemies()[jj].getyDeath(), "bones.png");
                    }
                }
            } else{
                int x = 1;
                do {
                        floors[ii].setTrapdoorX((int) (Math.random() * 50));
                        floors[ii].setTrapdoorY((int) (Math.random() * 50));
                        int trapX = floors[ii].getTrapdoorX(), trapY = floors[ii].getTrapdoorY();
                        if (floors[ii].getCells()[trapX][trapY].getWall()) {
                            x = -1;
                            floors[ii].getCells()[trapX][trapY].setRed(112);
                            floors[ii].getCells()[trapX][trapY].setGreen(65);
                            floors[ii].getCells()[trapX][trapY].setBlue(34);
                        }
                }while(x == 1);
            }

            //create and move Player's sprite
            int x = floors[ii].getStartX(), y = floors[ii].getStartY();
            gui.md_addSprite(1, "white-queen.png", true);
            gui.md_setSpriteVisible(1, true);

            id_count++;
                if(!floors[ii].getExplored()) {
                    for (int jj = 0; jj < 10; jj++) {
                        generateEnemies(gui, floors, ii, jj, id_count);
                        id_count++;
                    }
                    gui.md_setSquareColor(x, y, floors[ii].getCells()[x][y].getRed(), floors[ii].getCells()[x][y].getGreen(), floors[ii].getCells()[x][y].getBlue());
                    gui.md_moveSprite(1, floors[ii].getStartX(), floors[ii].getStartY());
                } else {
                    if (ii == 0) {
                        gui.md_moveSprite(1, floors[ii].getTrapdoorX(), floors[ii].getTrapdoorY());
                        x = floors[ii].getTrapdoorX();
                        y = floors[ii].getTrapdoorY();
                    } else {
                        gui.md_moveSprite(1, floors[ii].getStartX(), floors[ii].getStartY());
                    }
                }

            //Color start cell for floors 2nd to 5th. It's also the back trapdoor, as it's where it appears when going through the
            //  previous floor's trapdoor
            if(ii > 0){
                floors[ii].getCells()[x][y].setRed(198);
                floors[ii].getCells()[x][y].setGreen(103);
                floors[ii].getCells()[x][y].setBlue(190);
            }

            //paint start coordinates


            //MOVE
            int moveCount = 349;
            int floorNumber;
            do {
                String lastAction = gui.md_getLastAction().toLowerCase();
                //Move player 'sprite' depending on last action (left, right, up or down)
                if (lastAction.equals("left") && (x-1) >= 0) {
                    catchItem(gui, player, x-1, y, ii, floors);
                    if(thereIsEnemy(gui, floors, ii, (x-1), y) != -1){
                        Enemy enemy = floors[ii].getEnemies()[thereIsEnemy(gui, floors, ii, x-1, y)];
                        if(enemy.getHealth() <= 0 && x > 0 && floors[ii].getCells()[x-1][y].getWall()){
                            gui.md_setSpriteVisible(enemy.getId(), false);
                            gui.md_moveSprite(1, (x-1), y);
                            x--;
                            movements++;
                        } else{
                            doEnemy(gui, player, floors, x-1, y, ii, enemy);
                            gui.md_setSpriteVisible(enemy.getId(), true);
                            enemy.setSeen(true);
                        }
                    } else if (x > 0 && floors[ii].getCells()[x-1][y].getWall()) {
                        moveSprite(gui, player, x-1, y);
                        x--;
                        movements++;
                    }
                } else if (lastAction.equals("right") && (x+1) < 50) {
                    catchItem(gui, player, x+1, y, ii, floors);
                    if(thereIsEnemy(gui, floors, ii, (x+1), y) != -1){
                        Enemy enemy = floors[ii].getEnemies()[thereIsEnemy(gui, floors, ii, x+1, y)];
                        if(enemy.getHealth() <= 0 && x > 0 && floors[ii].getCells()[x+1][y].getWall()){
                            gui.md_setSpriteVisible(enemy.getId(), false);
                            gui.md_moveSprite(1, (x+1), y);
                            x++;
                            movements++;
                        } else{
                            doEnemy(gui, player, floors, x+1, y, ii, enemy);
                            gui.md_setSpriteVisible(enemy.getId(), true);
                            enemy.setSeen(true);
                        }
                    } else if (x < 49 && floors[ii].getCells()[x+1][y].getWall()) {
                        moveSprite(gui, player, x+1, y);
                        x++;
                        movements++;
                    }
                } else if (lastAction.equals("up") && (y-1) >= 0) {
                    catchItem(gui, player, x, y-1, ii, floors);
                    if(thereIsEnemy(gui, floors, ii, x, y-1) != -1){
                        Enemy enemy = floors[ii].getEnemies()[thereIsEnemy(gui, floors, ii, x, y-1)];
                        if(enemy.getHealth() <= 0 && x > 0 && floors[ii].getCells()[x][y-1].getWall()){
                            gui.md_setSpriteVisible(enemy.getId(), false);
                            gui.md_moveSprite(1, x, y-1);
                            y--;
                            movements++;
                        } else{
                            doEnemy(gui, player, floors, x, y-1, ii, enemy);
                            gui.md_setSpriteVisible(enemy.getId(), true);
                            enemy.setSeen(true);
                        }
                    } else if (y > 0 && floors[ii].getCells()[x][y-1].getWall()) {
                        moveSprite(gui, player, x, y-1);
                        y--;
                        movements++;
                    }
                } else if (lastAction.equals("down") && (y+1) < 50) {
                    catchItem(gui, player, x, y+1, ii, floors);
                    if(thereIsEnemy(gui, floors, ii, x, y+1) != -1){
                        Enemy enemy = floors[ii].getEnemies()[thereIsEnemy(gui, floors, ii, x, y+1)];
                        if(enemy.getHealth() <= 0 && x > 0 && floors[ii].getCells()[x][y+1].getWall()){
                            gui.md_setSpriteVisible(enemy.getId(), false);
                            gui.md_moveSprite(1, x, y+1);
                            y++;
                            movements++;
                        } else{
                            doEnemy(gui, player, floors, x, y+1, ii, enemy);
                            gui.md_setSpriteVisible(enemy.getId(), true);
                            enemy.setSeen(true);
                        }
                    } else if (y < 49 && floors[ii].getCells()[x][y+1].getWall()) {
                        moveSprite(gui, player, x, y+1);
                        y++;
                        movements++;
                    }
                }
                //Show and set cells explored depending on perception
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

                //Paint extra cells when perception is 2 (maximum in this gamer is perception = 2, as it has 5 levels and
                //  there can only be 1 eye per 5 levels
                if(player.getPerception() > 1) {
                    Cell loc = floors[ii].getCells()[x][y];
                    paintCells(x-1, y-1, gui, floors, ii);
                    paintCells(x+1, y-1, gui, floors, ii);
                    paintCells(x-1, y+1, gui, floors, ii);
                    paintCells(x+1, y+1, gui, floors, ii);
                }

                //Used moveCount to move enemies every 450 milliseconds
                moveCount++;

                //MOVE ENEMIES
                if(moveCount == 350) {
                    for (int kk = 0; kk < 10; kk++) {
                        int move = (int) (Math.random() * 4);
                        Enemy enemy = floors[ii].getEnemies()[kk];
                        int enemX = enemy.getX(), enemY = enemy.getY();


                        if(move == 0)enemX += 1;
                        else if(move == 1)enemX -= 1;
                        else if(move == 2)enemY += 1;
                        else if(move == 3)enemY -= 1;

                        if(enemX >= 0 && enemX < 49 && enemY >= 0 && enemY < 49 && floors[ii].getCells()[x][y] != floors[ii].getCells()[enemX][enemY] && floors[ii].getCells()[enemX][enemY].getWall() && enemy.getHealth() > 0){
                            gui.md_moveSprite(kk + 30,
                                    (enemX),
                                    (enemY));
                            enemy.setX(enemX);
                            enemy.setY(enemY);
                            floors[ii].getCells()[enemy.getX()][enemy.getY()].setHaveEnemy(false);
                            floors[ii].getCells()[enemy.getX()][enemy.getY()].setEnemyId(0);
                            floors[ii].getCells()[enemX][enemY].setHaveEnemy(true);
                            floors[ii].getCells()[enemX][enemY].setEnemyId(enemy.getId());
                        }
                    }
                    moveCount = 0;
                }
                Thread.sleep(1);
                time += 1;

                //When going through a back-trapdoor, we reduce ii variable by '-2', so we save the previous ii value to be able
                //  to 'close' the floor when exiting the do-while
                floorNumber = ii;

                //Trapdoor exit
                if(floors[ii].getCells()[x][y].getRed() == 112 && movements > 0){
                    floors[ii].setPassed(true);
                    floors[ii].setExplored(true);
                    gui.md_setSpriteVisible(1, false);
                    reachedFloor = ii;
                    //Passed boolean is used only to make the do-while, and explored boolean is to tell the program the player has
                    //  already been in that floor before
                    if(ii < 4) { //If you go further than level 4, you win!
                        floors[ii + 1].setPassed(false);
                    }
                    for(int jj = 2; jj < floors[ii].getItems().length; jj++){
                        gui.md_setSpriteVisible(jj, false);
                    }
                    if(player.getHealth() <= 0){
                        floors[ii].setPassed(true);
                        reachedFloor = ii;
                        ii = 7;
                    }
                    //Back trapdoor exit
                } else if(floors[ii].getCells()[x][y].getRed() == 198 && movements > 0){
                    floors[ii].setPassed(true);
                    gui.md_setSpriteVisible(1, false);
                    //We set previous floor passed state to false in order to make its do-while loop work again
                    floors[ii-1].setPassed(false);
                    gui.md_repaintBoard();
                    reachedFloor = ii;
                    floors[ii].setExplored(true);
                    for(int jj = 2; jj < floors[ii].getItems().length; jj++){
                        gui.md_setSpriteVisible(jj, false);
                    }
                    ii -= 2;
                    if(player.getHealth() <= 0){
                        floors[ii].setPassed(true);
                        reachedFloor = ii;
                        ii = 7;
                    }
                }
                if(player.getHealth() <= 0){
                    floors[ii].setPassed(true);
                    reachedFloor = ii;
                    ii = 7;
                }
            } while (!floors[floorNumber].isPassed());

        }
        time = time/1000 + 13;
        int timeH = 0, timeM = 0, timeS = 0;
        do{
            if(time - 60 >= 0){
                timeS += 1;
                time -= 1;
            } else{
                timeS += time;
                time = 0;
            }
            if(timeS >= 60){
                timeM += 1;
                timeS -= 60;
            }
            if(timeM == 60){
                timeH += 1;
                timeM -= 60;
            }
        }while(time > 0);
        //Message the game shows when the player finishes last level
        gui.md_showMessageDialog("Game over!\nYou reached level " + (-reachedFloor) + "!\nScore: " + player.getGold() + "\nTime: " + timeH + ":" + timeM + ":" + timeS);
    }
    public static void catchItem(MiniDungeonGUI gui, Player player, int x, int y, int ii, Floor[] floors){
        if(x >= 0 && x < 50 && y >= 0 && y < 50) {
            Cell location = floors[ii].getCells()[x][y];
            if (location.getHaveItem() && !floors[ii].getItems()[location.getItemId()].getTaken()) {
                floors[ii].getItems()[location.getItemId()].setTaken(true);
                gui.md_setSpriteVisible((location.getItemId()), false);
                String item_name = floors[ii].getItems()[location.getItemId()].getName();
                switch (item_name) {
                    case "Sword":
                        player.setPower(player.getPower() + 1);
                        gui.md_setTextStrength(player.getPower());
                        break;
                    case "Heart":
                        player.setMaxHealth(player.getMaxHealth() + 5);
                        gui.md_setTextHealthMax(player.getMaxHealth());
                        gui.md_setTextHealthCurrent(player.getHealth());
                        break;
                    case "Eye":
                        player.setPerception(player.getPerception() + 1);
                        gui.md_setTextPerception(player.getPerception());
                        break;
                    case "Potion":
                        if (player.getHealth() <= (player.getMaxHealth() - 10)) {
                            player.setHealth(player.getHealth() + 10);
                        } else {
                            player.setHealth(player.getHealth() + (player.getMaxHealth() - player.getHealth()));
                        }
                        gui.md_setTextHealthCurrent(player.getHealth());
                        break;
                    case "Gold":
                        player.setGold(player.getGold() + ((int) (Math.random() * 901 + 100)));
                        gui.md_setTextGold(player.getGold());
                        break;
                    case "Apple":
                        player.setFood(player.getFood() + ((int) (Math.random() * 141 + 40)));
                        gui.md_setTextFood(player.getFood());
                        break;
                }
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
            loc.setExplored(true);
            floors[ii].getItems()[loc.getItemId()].setSeen(true);
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
                floors[ii].getItems()[location.getItemId()].setSeen(true);
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
                    } else if(id.equals("Eye") && floors[ii].getEyeCount() == 0){
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
                    } else if(id.equals("Eye") && floors[ii].getEyeCount() == 0){
                        repeated = false;
                    } else if(!id.equals("Sword") && !id.equals("Eye")){
                        repeated = false;
                    }
                }
            } else if(id.equals("Eye") && floors[ii].getEyeCount() != 0){
                id = floors[ii].getNewItem(jj);
                if(!id.equals("Eye")){
                    if(id.equals("Sword") && heartCount == 0){
                        repeated = false;
                    } else if(id.equals("Heart") && floors[ii].getHeartCount() == 0){
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
                floors[ii].setEyeCount(1);
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
        if(!floors[ii].getExplored()) {
            for (int x1 = 1; x1 > 0; x1++) {
                int Ix = (int) (Math.random() * 50), Iy = (int) (Math.random() * 50);
                if (floors[ii].getCells()[Ix][Iy].getWall() && !floors[ii].getCells()[Ix][Iy].getHaveItem() && Ix != floors[ii].getStartX() && Iy != floors[ii].getStartY()) { //activate when having more cells than items created
                    x1 = -1;
                    gui.md_moveSprite(id_count, Ix, Iy);
                    floors[ii].getItems()[jj].setX(Ix);
                    floors[ii].getItems()[jj].setY(Iy);
                    floors[ii].getCells()[Ix][Iy].setHaveItem(true);
                    floors[ii].getCells()[Ix][Iy].setItemId(jj);
                }
            }
        } else {
            gui.md_moveSprite(id_count, floors[ii].getItems()[jj].getX(), floors[ii].getItems()[jj].getY());
        }
        return returned;
    }
    public static void generateEnemies(MiniDungeonGUI gui, Floor[] floors, int ii, int jj, int id_count){
        String enemy = floors[ii].getEnemies()[jj].getName();
        if(floors[ii].getEnemies()[jj].isVisible() && floors[ii].getEnemies()[jj].getHealth() > 0){
            gui.md_addSprite(id_count, enemy, true);
            gui.md_setSpriteVisible(id_count, false);
        } else {
            gui.md_addSprite(id_count, "bones.png", false);
            gui.md_setSpriteVisible(id_count, false);
        }
        for (int x1 = 1; x1 > 0; x1++) {
            int Ix = (int) (Math.random() * 50), Iy = (int) (Math.random() * 50);
            if (floors[ii].getCells()[Ix][Iy].getWall() && Ix != floors[ii].getStartX() && Iy != floors[ii].getStartY()) { //activate when having more cells than items created
                x1 = -1;
                floors[ii].getEnemies()[jj].setX(Ix);
                floors[ii].getEnemies()[jj].setY(Iy);
                floors[ii].getCells()[Ix][Iy].setHaveEnemy(true);
                floors[ii].getCells()[Ix][Iy].setEnemyId(jj);
                floors[ii].getEnemies()[jj].setId(id_count);
                gui.md_moveSprite(id_count, Ix, Iy);
            }
        }
    }
    public static void setGuiText(MiniDungeonGUI gui, Player player){
        gui.md_setTextHealthMax(player.getMaxHealth());
        gui.md_setTextPerception(player.getPerception());
        gui.md_setTextStrength(player.getPower());
        gui.md_setTextFood(player.getFood());
        gui.md_setTextHealthCurrent(player.getHealth());
    }
    public static int thereIsEnemy(MiniDungeonGUI gui, Floor[] floors, int ii, int x, int y){
        int enemy = -1;
        for (int jj = 0; jj < 10; jj++) {
            if(floors[ii].getEnemies()[jj].getX() == x && floors[ii].getEnemies()[jj].getY() == y){
                enemy = jj;
            }
        }

        return enemy;
    }
    public static void doEnemy(MiniDungeonGUI gui, Player player, Floor[] floors, int x, int y, int ii, Enemy enemy){
        if(enemy.getHealth() > 0) {
            enemy.setHealth((enemy.getHealth() - player.getPower()));
            gui.md_println("Enemy loses " + player.getPower() + " health (" + enemy.getHealth() + " remaining)");
            if (enemy.getHealth() <= 0) {
                gui.md_setSpriteVisible(enemy.getId(), false);
                if(enemy.getGen() == 1){
                    exploreAll(gui, floors, ii);
                }
                enemy.setxDeath(x);
                enemy.setyDeath(y);
                gui.md_moveSprite(enemy.getId(), 0, 0);
                gui.md_setSquareImage(enemy.getX(), enemy.getY(), "bones.png");
            } else {
                int attack = (int) (Math.random()*10 + 1);
                switch (attack) {
                    case 1:
                        player.setHealth(player.getHealth() - enemy.getPower());
                        gui.md_println("Player loses " + enemy.getPower() + " health");
                        break;
                    default:
                        break;
                }
            }
            changeFood(gui, player, 3);
            setGuiText(gui, player);
        } else if (x > 0 && floors[ii].getCells()[x][y].getWall()) {
            moveSprite(gui, player, x, y);
        }
    }
    public static void moveSprite(MiniDungeonGUI gui, Player player, int x, int y){
        gui.md_moveSprite(1, x, y);
        changeFood(gui, player, 1);
    }
    public static void changeFood(MiniDungeonGUI gui, Player player, int change){
        if(player.getFood() != 0){
            player.setFood((player.getFood() - change));
            if(player.getFood() <= 0){
                player.setFood(0);
                player.setPower(player.getPower()/2);
                player.setPerception(player.getPerception()/2);
                if(player.getPerception() == 0){
                    player.setPerception(1);
                }
            }
            setGuiText(gui, player);
        }
    }
    public static void exploreAll(MiniDungeonGUI gui, Floor[] floors, int ii){
        for(int jj = 0; jj < 50; jj++){
            for (int kk = 0; kk < 50; kk++){
                Cell location =  floors[ii].getCells()[jj][kk];
                location.setExplored(true);
                if(location.getWall() && location != floors[ii].getCells()[floors[ii].getStartX()][floors[ii].getStartY()] &&    location != floors[ii].getCells()[floors[ii].getTrapdoorX()][floors[ii].getTrapdoorY()]){
                    location.setRed(170);
                    location.setGreen(191);
                    location.setBlue(226);
                }
                gui.md_setSquareColor(jj, kk, location.getRed(), location.getGreen(), location.getBlue());
            }
        }
    }
}

