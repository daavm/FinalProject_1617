import minidungeon.MiniDungeonGUI;

public class Main {

	public static void main(String[] args) throws InterruptedException {
        Floor[] floors = new Floor[5];
        MiniDungeonGUI gui = new MiniDungeonGUI(50, 50);
        gui.setVisible(true);
        int eyeCount = 0; //eyecount goes here because there can only be 1 eye per 5 levels, and this game has only 5 levels(floors), so will only appear once
        for (int ii = 0; ii < floors.length; ii++) {
            floors[ii] = new Floor(ii);
        }
        Player player = new Player();
        for (int ii = 0; ii < floors.length; ii++) {
            int swordCount = 0, heartCount = 0, id_count = 1;
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

            //if(floor.isPassed()){

                for (int x1 = 1; x1 > 0; x1++) { //set start coordinates
                    floors[ii].setStartX((int) (Math.random() * 50));
                    floors[ii].setStartY((int) (Math.random() * 50));
                    if (floors[ii].getCells()[floors[ii].getStartX()][floors[ii].getStartY()].getWall()) {
                        x1 = -1;
                    }
                }

            //show alive enemies, onthefloor items,etc...
            //seeing first if the boss is killed so the floor is explored...etc floor.isExplored() <-- add
            //Generate valid start coordinates

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
            for (int jj = 2; jj < 8; jj++) { //number of items to generate
                id_count++;
                String id = floors[ii].getItems()[jj].getName();
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
                        eyeCount++;
                        break;
                    case "Gold":
                        gui.md_addSprite(id_count, "gold.png", true);
                        break;
                    case "Heart":
                        gui.md_addSprite(id_count, "heart.png", true);
                        heartCount++;
                        break;
                    case "Sword":
                        gui.md_addSprite(id_count, "sword.png", true);
                        swordCount++;
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
                //TODO only to test maps
                gui.md_setSpriteVisible(id_count, true);

            }
            int x = floors[ii].getStartX(), y = floors[ii].getStartY();
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
            gui.md_setSquareColor(x, y, (int)floors[ii].getCells()[x][y].getRed(), (int)floors[ii].getCells()[x][y].getGreen(), (int)floors[ii].getCells()[x][y].getBlue());



            //MOVE

            do {
                String lastAction = gui.md_getLastAction().toLowerCase();

                if (lastAction.equals("left") && (x-1) >= 0) {
                    Cell location = floors[ii].getCells()[x-1][y];
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

                    if (x > 0 && floors[ii].getCells()[x - 1][y].getWall()) {
                        gui.md_moveSprite(1, (x - 1), y);
                        x--;
                        player.setFood((player.getFood() - 1));
                        gui.md_setTextFood(player.getFood());
                    }
                } else if (lastAction.equals("right")) {
                    Cell location = floors[ii].getCells()[x+1][y];
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
                    if (x < 49 && floors[ii].getCells()[x + 1][y].getWall()) {
                        gui.md_moveSprite(1, (x + 1), y);
                        x++;
                        player.setFood((player.getFood() - 1));
                        gui.md_setTextFood(player.getFood());
                    }
                } else if (lastAction.equals("up")) {
                    Cell location = floors[ii].getCells()[x][y-1];
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
                    if (y > 0 && floors[ii].getCells()[x][y - 1].getWall()) {
                        gui.md_moveSprite(1, x, (y - 1));
                        y--;
                        player.setFood((player.getFood() - 1));
                        gui.md_setTextFood(player.getFood());
                    }
                } else if (lastAction.equals("down")) {
                    Cell location = floors[ii].getCells()[x][y+1];
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
                    if (y < 49 && floors[ii].getCells()[x][y + 1].getWall()) {
                        gui.md_moveSprite(1, x, (y + 1));
                        gui.md_repaintBoard();
                        y++;
                        player.setFood((player.getFood() - 1));
                        gui.md_setTextFood(player.getFood());
                    }
                }
                //Show and set cells explored by perception
                if (x < 49) {
                    for(int perception = player.getPerception(); perception > 0; perception -= 1) {
                        Cell location = floors[ii].getCells()[x + perception][y];
                        if (location.getWall()) {
                            gui.md_setSquareColor(x + perception, y, (int)location.getRed(), (int)location.getGreen(), (int)location.getBlue());
                        } else {
                            gui.md_setSquareColor((x + perception), y, 112, 112, 112);
                        }
                        location.setExplored(true);
                        if (location.getHaveItem() && !floors[ii].getItems()[location.getItemId()].getTaken()) {
                            gui.md_setSpriteVisible((location.getItemId()), true);
                        }
                    }
                }
                if (x - player.getPerception() >= 0) {
                    for(int perception = player.getPerception(); perception > 0; perception -= 1) {
                        Cell location = floors[ii].getCells()[x - perception][y];
                        if (location.getWall()) {
                            gui.md_setSquareColor(x - perception, y, (int)location.getRed(), (int)location.getGreen(), (int)location.getBlue());
                        } else {
                            gui.md_setSquareColor((x - perception), y, 112, 112, 112);
                        }
                        location.setExplored(true);
                        if (location.getHaveItem() && !floors[ii].getItems()[location.getItemId()].getTaken()) {
                            gui.md_setSpriteVisible(location.getItemId(), true);
                        }
                    }
                }
                if (y + player.getPerception() <  49) {
                    for(int perception = player.getPerception(); perception > 0; perception -= 1) {
                        Cell location = floors[ii].getCells()[x][y + perception];
                        if (location.getWall()) {
                            gui.md_setSquareColor(x, (y + perception), (int)location.getRed(), (int)location.getGreen(), (int)location.getBlue());
                        } else {
                            gui.md_setSquareColor(x, (y + perception), 112, 112, 112);
                        }
                        location.setExplored(true);

                        if (location.getHaveItem() && !floors[ii].getItems()[location.getItemId()].getTaken()) {
                            gui.md_setSpriteVisible(location.getItemId(), true);
                        }
                    }
                }
                if (y > 0) {
                    for(int perception = player.getPerception(); perception > 0; perception -= 1) {
                        Cell location = floors[ii].getCells()[x][y - perception]; //Cell data type to avoid typing a lot of code many times
                        if (location.getWall()) {
                            gui.md_setSquareColor(x, (y - perception), (int)location.getRed(), (int)location.getGreen(), (int)location.getBlue());
                        } else {
                            gui.md_setSquareColor(x, (y - perception), 112, 112, 112);
                        }
                        location.setExplored(true);
                        if (location.getHaveItem() && !floors[ii].getItems()[location.getItemId()].getTaken()) {
                            gui.md_setSpriteVisible(location.getItemId(), true);
                        }
                    }
                }
                //paint extra cells when perception is 2
                if(player.getPerception() > 1) {
                    Cell loc = floors[ii].getCells()[x-1][y-1];
                    int perception = player.getPerception() - 1;
                    if (loc.getWall()) {
                        gui.md_setSquareColor(x - 1, (y - 1), (int) loc.getRed(), (int) loc.getGreen(), (int) loc.getBlue());
                    } else {
                        gui.md_setSquareColor(x - 1, (y - 1), (int) loc.getRed(), (int) loc.getGreen(), (int) loc.getBlue());
                    }
                    loc = floors[ii].getCells()[x+1][y-1];
                    if (loc.getWall()) {
                        gui.md_setSquareColor(x + 1, (y - 1), (int) loc.getRed(), (int) loc.getGreen(), (int) loc.getBlue());
                    } else {
                        gui.md_setSquareColor(x + 1, (y - 1), (int) loc.getRed(), (int) loc.getGreen(), (int) loc.getBlue());
                    }
                    loc = floors[ii].getCells()[x-1][y+1];
                    if (loc.getWall()) {
                        gui.md_setSquareColor(x - 1, (y + 1), (int) loc.getRed(), (int) loc.getGreen(), (int) loc.getBlue());
                    } else {
                        gui.md_setSquareColor(x - 1, (y + 1), (int) loc.getRed(), (int) loc.getGreen(), (int) loc.getBlue());
                    }
                    loc = floors[ii].getCells()[x+1][y+1];
                    if (loc.getWall()) {
                        gui.md_setSquareColor(x + 1, (y + 1), (int) loc.getRed(), (int) loc.getGreen(), (int) loc.getBlue());
                    } else {
                        gui.md_setSquareColor(x + 1, (y + 1), (int) loc.getRed(), (int) loc.getGreen(), (int) loc.getBlue());
                    }
                }
                Thread.sleep(1);
                if(floors[ii].getCells()[x][y].getRed() == 112){
                    floors[ii].setPassed(true);
                }
            } while (floors[ii].getPassed() == false);

        }
    }
    public static void getNewItem(int jj){

    }
}

