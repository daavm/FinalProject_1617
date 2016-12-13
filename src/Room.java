/**
 * Created by david on 12/13/2016.
 */
public class Room {
        private int originX;
        private int originY;
        private int id;

        Room(){
            originX = (int)(Math.random()*50);
            originY = (int)(Math.random()*50);
        }

        public Room[] getNearestRooms(Room[] rooms){
            Room[] nearest = new Room[2];
            int nearestId1 = 0;
            double nearestDistance1 = 0;
            int nearestId2 = 0;
            double nearestDistance2= 0;
            for(int ii = 0; ii < rooms.length; ii++){
                if(!(this.id == rooms[ii].getId())){
                    double distance = Math.sqrt(((this.originX-rooms[ii].getOriginX())*(this.originX-rooms[ii].getOriginX())+((this.originY-rooms[ii].getOriginY())*(this.originY-rooms[ii].getOriginY()))));
                    if(distance > nearestDistance1){
                        nearestId1 = rooms[ii].getId();
                        nearestDistance1 = distance;
                    } else if (distance > nearestDistance2){
                        nearestId2 = rooms[ii].getId();
                        nearestDistance2 = distance;
                    }
                }
            }
            return nearest;
        }
        public int getOriginX(){
            return  originX;
        }
        public int getOriginY(){
            return originY;
        }
        public int getId(){
            return id;
        }
}
