package model;


import java.util.Objects;

public class Room implements IRoom {
     private String roomNumber;
     private Double price;
     RoomType enumeration;
     Boolean isFree;

     public Room(String roomNumber, Double price, RoomType enumeration, Boolean isFree) {
         super();
         this.roomNumber = roomNumber;
         this.price = price;
         this.enumeration = enumeration;
         this.isFree = isFree;
     }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
      return enumeration;
    }

    @Override
    public Boolean isFree() {
        return isFree;
    }

    @Override
     public String toString() {
         return "These are the room details : \n"
                 + " Room number : " + roomNumber + "\n"
                 + " Room type : " + enumeration.toString().toLowerCase() + " bed room" + "\n"
                 + " Cost of room per night : $" + (isFree == true ? "0 (*free room)" : price) + "\n"
                 + " Free/Paid : " + (isFree == true ? "Free room" : "Paid room");

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Room)) return false;
        Room room = (Room) obj;
        return getRoomNumber().equals(room.getRoomNumber()) && enumeration == room.enumeration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomNumber(), enumeration);
    }
}
