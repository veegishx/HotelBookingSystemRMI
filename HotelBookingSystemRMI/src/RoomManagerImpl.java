import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;

import Objects.Guest;
import Objects.Room;
import RoomManagerInterface.RoomManager;

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
    		Class.forName("com.mysql.cj.jdbc.Driver");  
    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_booking","root","1234567890");  
    		Statement stmt = con.createStatement();  
    		ResultSet rs = stmt.executeQuery("select * from room");  
    		while(rs.next()) {
    			Room room = new Room(1, 1, 1, "", false, 1);
    			room.setRoomId(rs.getInt("roomId"));
    			room.setRoomType(rs.getInt("roomType"));
    			room.setRoomPrice(rs.getInt("roomPrice")); 
    			room.setRoomDescription(rs.getString("roomDescription"));
    			room.setBookingStatus(rs.getBoolean("roomBookingStatus"));
    			room.setNumAvailable(rs.getInt("numAvailable"));
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

	public void bookRoom(Hashtable<Integer, Integer> availableRooms, int choice, String fname, String lname, int duration) throws RemoteException {
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		Connection con = null;
		try
		  {
			Class.forName("com.mysql.cj.jdbc.Driver");  
    		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_booking","root","1234567890");  
    		
    		con.setAutoCommit(false);
		    stmt1 = con.prepareStatement("UPDATE room SET numAvailable = ? WHERE roomType = ?");
		    
		    String registerGuest = "insert into guest(firstName, lastName, roomId,stayDuration) values(?, ?, ?, ?)";
		    stmt2 = con.prepareStatement(registerGuest);


		    // Set Prepared Statement Parameters [RoomType] & prepare for batch update
		    int dbRoomsAvailableValue = availableRooms.get(choice);
		    stmt1.setInt(1, dbRoomsAvailableValue - 1);
		    stmt1.setInt(2, choice);

		    stmt2.setString(1, fname); 
		    stmt2.setString(2, lname);
		    stmt2.setInt(3, choice++);
		    stmt2.setInt(4, duration);
		   
		    stmt1.execute();
		    stmt2.execute();
		    System.out.println(stmt1.toString());
		    con.commit();
		    
        } catch (ClassNotFoundException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
	}
}