import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import Objects.Guest;
import Objects.Room;

public class RoomManagerImpl extends UnicastRemoteObject implements RoomManagerInterface.RoomManager {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoomManagerImpl() throws RemoteException{
		super();
		
	}

    public ArrayList<Room> getRooms() throws RemoteException {
    	ArrayList<Room> roomList = new ArrayList<>();
    	try {
    		Class.forName("com.mysql.jdbc.Driver");  
    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_booking","root","1234567890");  
    		Statement stmt = con.createStatement();  
    		ResultSet rs = stmt.executeQuery("select * from room");  
    		while(rs.next()) {
    			Room room = new Room(1, 1, 1, "", false);
    			room.setRoomId(rs.getInt("roomId"));
    			room.setRoomType(rs.getInt("roomType"));
    			room.setRoomPrice(rs.getInt("roomPrice"));
    			room.setRoomDescription(rs.getString("roomDescription"));
    			room.setBookingStatus(rs.getBoolean("roomBookingStatus"));
    			roomList.add(room);
    		}
    		con.close();  
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
        return roomList;
    }

	public ArrayList<Guest> getGuests() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Room> bookRoom() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}