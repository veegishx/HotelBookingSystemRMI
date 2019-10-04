import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import RoomManagerInterface.RoomManager;

/**
 * The HotelServer class will create and initialize an implementation of the hotel booking RMI service
 * @author veegish
 * @throws MalformedURLException
 * @throws RemoteException
 *
 */
public class HotelServer {
	public HotelServer() throws MalformedURLException {
		try {
			Registry r = LocateRegistry.createRegistry(1099);
			RoomManager rm = new RoomManagerImpl();
			Naming.rebind("rmi://localhost:1099/BookingService", rm);
			System.out.println("Starting Booking Service server...");
		} catch(RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This function will initialize the server with a given number of rooms for each room type
	 * @return void
	 * @throws ClassNotFoundException 
	 * @throws SQLException
	 */
	public static void initializeRooms() throws ClassNotFoundException {
		// Initialize number of available rooms
		int type0Room = 10;
		int type1Room = 20;
		int type2Room = 5;
		int type3Room = 3;
		int type4Room = 2;
		
		PreparedStatement stmt = null;
		Connection con = null;
		try
		  {
			Class.forName("com.mysql.cj.jdbc.Driver");  
    		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_booking","root","1234567890");  
    		
    		con.setAutoCommit(false);
		    stmt = con.prepareStatement("UPDATE room SET numAvailable = ? WHERE roomId = ?");


		    // Set Prepared Statement Parameters [RoomType] & prepare for batch update
		    stmt.setInt(1, type0Room);
		    stmt.setInt(2, 1);
		    stmt.addBatch();
		    
		    stmt.setInt(1, type1Room);
		    stmt.setInt(2, 2);
		    stmt.addBatch();
		    
		    stmt.setInt(1, type2Room);
		    stmt.setInt(2, 3);
		    stmt.addBatch();
		    
		    stmt.setInt(1, type3Room);
		    stmt.setInt(2, 4);
		    stmt.addBatch();
		    
		    stmt.setInt(1, type4Room);
		    stmt.setInt(2, 5);
		    stmt.addBatch();
		    
		    int count[] = stmt.executeBatch();
            for(int i = 0; i < count.length; i++){
                System.out.println("Query " + i + " has effected " + count[i] + " times");
            }
            con.commit();
            System.out.println("Rooms have been successfully initialized!");
            System.out.println("Server is ready.");
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
        } finally{
            try{
                if(stmt != null) stmt.close();
                if(con != null) con.close();
            } catch(Exception ex){}
        }
	}
	
	public static void main(String args[])throws Exception{
	   new HotelServer();
	   initializeRooms();
	}
}
