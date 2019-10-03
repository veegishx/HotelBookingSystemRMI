import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import Objects.Guest;
import Objects.Room;
import RoomManagerInterface.RoomManager;

public class HotelClient {
	public static void main(String[] args) throws MalformedURLException {
		//RoomManagerImpl roomManager = null;
		try {
			Registry r = LocateRegistry.getRegistry("127.0.0.1", 1099);
			RoomManager rm = (RoomManager) Naming.lookup("rmi://localhost:1099/BookingService");

			ArrayList<Room> roomList = rm.getRooms();
			
			for (Room room : roomList) {
				System.out.println("ID: " + room.getRoomId());
				System.out.println("TYPE: " + room.getRoomType());
				System.out.println("PRICE " + room.getRoomPrice());
				System.out.println("DESC " + room.getRoomDescription());
				System.out.println("STATUS " + room.getBookingStatus());
			}
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}