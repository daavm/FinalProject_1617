public class Room {
    private int originX;
    private int originY, height, base;
    private int id, id2;

    Room(Room[] rooms, int id){
        height = (int)(Math.random()*4 + 3);
        base = (int)(Math.random()*4 + 3);
        for(int ii = 1; ii > 0; ii++) {
            originX = (int) (Math.random() * 50);
            originY = (int) (Math.random() * 50);
            int distance = 4;
            switch (id) {
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

    public void getNearestRoom(Room[] rooms){
        int nearestId1 = 0;
        double nearestDistance1 = 999999999;
        int nearestId2 = 0;
        double nearestDistance2= 999999999;
        for(int ii = 0; ii < rooms.length; ii++){
            if(!(this.id == rooms[ii].getId())){
                double distance = Math.sqrt(((this.originX-rooms[ii].getOriginX())*(this.originX-rooms[ii].getOriginX())+((this.originY-rooms[ii].getOriginY())*(this.originY-rooms[ii].getOriginY()))));
                if(distance > nearestDistance1){
                    nearestId1 = rooms[ii].getId();
                    this.id2 = ii;
                    nearestDistance1 = distance;
                } else if (distance > nearestDistance2){
                    nearestId2 = rooms[ii].getId();
                    this.id2 = ii;
                    nearestDistance2 = distance;
                }
            }
        }
    }
    public int getHeight(){ return height;}
    public int getBase(){ return base;}
    public int getOriginX(){
        return  originX;
    }
    public int getOriginY(){
        return originY;
    }
    public int getId(){
        return id;
    }
    public int getId2(){
        return id2;
    }
}
