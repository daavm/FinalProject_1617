/**
 * @author David Marcos Mazón and Sara Timermans Pastor
 * @since December 2016
 * @version 1.0
 */
public class Room {
    private int originX;
    private int originY, height, base;
    private int id2;

    Room(Room[] rooms, int id){
        height = (int)(Math.random()*4 + 3);
        base = (int)(Math.random()*4 + 3);
        for(int ii = 1; ii > 0; ii++) {
            //get random origin point for the room that is being created
            originX = (int) (Math.random() * 50);
            originY = (int) (Math.random() * 50);
            int distance = 4;
            switch (id) {
                //this method is a bit long, and we know it could be optimized. What it does is just to check the generated room does
                //not overlap previously created rooms. It basically checks distance with the room that is being created and the others
                //previously generated is bigger than a given number (4 in this case)
                case 0:
                    ii = -1;
                    break;
                case 1:
                    if ((Math.abs(rooms[0].getOriginX() - originX) > distance) && (Math.abs(rooms[0].getOriginY() - originY) > distance)) {
                        ii = -1;
                    }
                    break;
                case 2:
                    if ((Math.abs(rooms[0].getOriginX() - originX) > distance) && (Math.abs(rooms[0].getOriginY() - originY) > distance) && (Math.abs(rooms[1].getOriginX() - originX) > distance) && (Math.abs(rooms[1].getOriginY() - originY) > distance)) {
                        ii = -1;
                    }
                    break;
                case 3:
                    if ((Math.abs(rooms[0].getOriginX() - originX) > distance) && (Math.abs(rooms[0].getOriginY() - originY) > distance) && (Math.abs(rooms[1].getOriginX() - originX) > distance) && (Math.abs(rooms[1].getOriginY() - originY) > distance) && (Math.abs(rooms[2].getOriginX() - originX) > distance) && (Math.abs(rooms[2].getOriginY() - originY) > distance)) {
                        ii = -1;
                    }
                    break;
                case 4:
                    if ((Math.abs(rooms[0].getOriginX() - originX) > distance) && (Math.abs(rooms[0].getOriginY() - originY) > distance) && (Math.abs(rooms[1].getOriginX() - originX) > distance) && (Math.abs(rooms[1].getOriginY() - originY) > distance) && (Math.abs(rooms[2].getOriginX() - originX) > distance) && (Math.abs(rooms[2].getOriginY() - originY) > distance) && (Math.abs(rooms[3].getOriginX() - originX) > distance) && (Math.abs(rooms[3].getOriginY() - originY) > distance)) {
                        ii = -1;
                    }
                    break;
                case 5:
                    if ((Math.abs(rooms[0].getOriginX() - originX) > distance) && (Math.abs(rooms[0].getOriginY() - originY) > distance) && (Math.abs(rooms[1].getOriginX() - originX) > distance) && (Math.abs(rooms[1].getOriginY() - originY) > distance) && (Math.abs(rooms[2].getOriginX() - originX) > distance) && (Math.abs(rooms[2].getOriginY() - originY) > distance) && (Math.abs(rooms[3].getOriginX() - originX) > distance) && (Math.abs(rooms[3].getOriginY() - originY) > distance) && (Math.abs(rooms[4].getOriginX() - originX) > distance) && (Math.abs(rooms[4].getOriginY() - originY) > distance)) {
                        ii = -1;
                    }
                    break;
                case 6:
                    if ((Math.abs(rooms[0].getOriginX() - originX) > distance) && (Math.abs(rooms[0].getOriginY() - originY) > distance) && (Math.abs(rooms[1].getOriginX() - originX) > distance) && (Math.abs(rooms[1].getOriginY() - originY) > distance) && (Math.abs(rooms[2].getOriginX() - originX) > distance) && (Math.abs(rooms[2].getOriginY() - originY) > distance) && (Math.abs(rooms[3].getOriginX() - originX) > distance) && (Math.abs(rooms[3].getOriginY() - originY) > distance) && (Math.abs(rooms[4].getOriginX() - originX) > distance) && (Math.abs(rooms[4].getOriginY() - originY) > distance) && (Math.abs(rooms[5].getOriginX() - originX) > distance) && (Math.abs(rooms[5].getOriginY() - originY) > distance)) {
                        ii = -1;
                    }
                    break;
                case 7:
                    if ((Math.abs(rooms[0].getOriginX() - originX) > distance) && (Math.abs(rooms[0].getOriginY() - originY) > distance) && (Math.abs(rooms[1].getOriginX() - originX) > distance) && (Math.abs(rooms[1].getOriginY() - originY) > distance) && (Math.abs(rooms[2].getOriginX() - originX) > distance) && (Math.abs(rooms[2].getOriginY() - originY) > distance) && (Math.abs(rooms[3].getOriginX() - originX) > distance) && (Math.abs(rooms[3].getOriginY() - originY) > distance) && (Math.abs(rooms[4].getOriginX() - originX) > distance) && (Math.abs(rooms[4].getOriginY() - originY) > distance) && (Math.abs(rooms[5].getOriginX() - originX) > distance) && (Math.abs(rooms[5].getOriginY() - originY) > distance) && (Math.abs(rooms[6].getOriginX() - originX) > distance) && (Math.abs(rooms[6].getOriginY() - originY) > distance)) {
                        ii = -1;
                    }
                    break;
                case 8:
                    if ((Math.abs(rooms[0].getOriginX() - originX) > distance) && (Math.abs(rooms[0].getOriginY() - originY) > distance) && (Math.abs(rooms[1].getOriginX() - originX) > distance) && (Math.abs(rooms[1].getOriginY() - originY) > distance) && (Math.abs(rooms[2].getOriginX() - originX) > distance) && (Math.abs(rooms[2].getOriginY() - originY) > distance) && (Math.abs(rooms[3].getOriginX() - originX) > distance) && (Math.abs(rooms[3].getOriginY() - originY) > distance) && (Math.abs(rooms[4].getOriginX() - originX) > distance) && (Math.abs(rooms[4].getOriginY() - originY) > distance) && (Math.abs(rooms[5].getOriginX() - originX) > distance) && (Math.abs(rooms[5].getOriginY() - originY) > distance) && (Math.abs(rooms[6].getOriginX() - originX) > distance) && (Math.abs(rooms[6].getOriginY() - originY) > distance) && (Math.abs(rooms[7].getOriginX() - originX) > distance) && (Math.abs(rooms[7].getOriginY() - originY) > distance)) {
                        ii = -1;
                    }
                    break;
                case 9:
                    if ((Math.abs(rooms[0].getOriginX() - originX) > distance) && (Math.abs(rooms[0].getOriginY() - originY) > distance) && (Math.abs(rooms[1].getOriginX() - originX) > distance) && (Math.abs(rooms[1].getOriginY() - originY) > distance) && (Math.abs(rooms[2].getOriginX() - originX) > distance) && (Math.abs(rooms[2].getOriginY() - originY) > distance) && (Math.abs(rooms[3].getOriginX() - originX) > distance) && (Math.abs(rooms[3].getOriginY() - originY) > distance) && (Math.abs(rooms[4].getOriginX() - originX) > distance) && (Math.abs(rooms[4].getOriginY() - originY) > distance) && (Math.abs(rooms[5].getOriginX() - originX) > distance) && (Math.abs(rooms[5].getOriginY() - originY) > distance) && (Math.abs(rooms[6].getOriginX() - originX) > distance) && (Math.abs(rooms[6].getOriginY() - originY) > distance) && (Math.abs(rooms[7].getOriginX() - originX) > distance) && (Math.abs(rooms[7].getOriginY() - originY) > distance) && (Math.abs(rooms[8].getOriginX() - originX) > distance) && (Math.abs(rooms[8].getOriginY() - originY) > distance)) {
                        ii = -1;
                    }
                    break;
                default:
                    ii = -1;
                    break;
            }
        }
    }

    //getters, we did not need setters in this case
    public int getHeight(){ return height;}
    public int getBase(){ return base;}
    public int getOriginX(){
        return  originX;
    }
    public int getOriginY(){
        return originY;
    }
}
