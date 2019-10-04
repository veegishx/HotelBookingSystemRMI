package Objects;

import java.io.Serializable;

public class Room implements Serializable {
	int roomId;
    int roomType;
    int roomPrice;
    String roomDescription;
    boolean bookingStatus;
    int numAvailable;

    public Room(int roomId, int roomType, int roomPrice, String roomDescription, boolean bookingStatus, int numAvailable) {
        this.roomId = roomId;
    	this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.roomDescription = roomDescription;
        this.bookingStatus = bookingStatus;
        this.numAvailable = numAvailable;
    }

    public int getNumAvailable() {
		return numAvailable;
	}

	public void setNumAvailable(int numAvailable) {
		this.numAvailable = numAvailable;
	}

	public boolean getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(boolean bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    public int getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(int roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }
}
