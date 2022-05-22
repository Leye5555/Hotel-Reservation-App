package model;

public class FreeRoom extends Room{
    private static Double price = 0.0;
    public FreeRoom(String roomNumber, RoomType enumeration, Boolean isFree) {
        super(roomNumber, price, enumeration, isFree);
    }

    @Override()
    public String toString(){
        return super.toString();
    }
}
