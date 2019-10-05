import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import Objects.Guest;
import Objects.Room;
import RoomManagerInterface.RoomManager;

public class HotelClient {
	public static void main(String[] args) throws MalformedURLException {
		menu();
	}
	
	public static void menu() throws MalformedURLException {
		Scanner in = new Scanner(System.in);
		System.out.println("VBK Hotel Room Booking System v1.0");
		System.out.println("1. List Available Rooms");
		System.out.println("2. Book Room");
		System.out.println("3. List Registered Guests");
		System.out.println("Enter choice: ");
		int choice = in.nextInt();
		switch (choice) {
		case 1: listAvailableRooms();
		break;
		case 2: bookRoom();
		break;
		case 3: listRegisteredGuests();
		break;
		}	
	}
	
	public static void listAvailableRooms() throws MalformedURLException {
		//RoomManagerImpl roomManager = null;
		Scanner in = new Scanner(System.in);
		try {
			Registry r = LocateRegistry.getRegistry("127.0.0.1", 1099);
			RoomManager rm = (RoomManager) Naming.lookup("rmi://localhost:1099/BookingService");

			ArrayList<Room> roomList = rm.getRooms();
			
			for (Room room : roomList) {
				System.out.println(room.getNumAvailable() + " rooms of type " + room.getRoomType() + " are available for Rs" + room.getRoomPrice() + " per night.");
			}
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.print("Go back? [Y/n]: ");
		String answer = in.nextLine();
		if(answer.equalsIgnoreCase("y")) {
			menu();
		}
	}
	
	public static void bookRoom() throws MalformedURLException {
		//RoomManagerImpl roomManager = null;
		Scanner in = new Scanner(System.in);
		int booked = 0;
		Hashtable<Integer, Integer> availableRooms = new Hashtable<Integer, Integer>();
		System.out.println("Types of rooms currently available");
		System.out.println("------------------------------------");
		try {
			Registry r = LocateRegistry.getRegistry("127.0.0.1", 1099);
			RoomManager rm = (RoomManager) Naming.lookup("rmi://localhost:1099/BookingService");

			ArrayList<Room> roomList = rm.getRooms();
			for (Room room : roomList) {
				if(room.getNumAvailable() > 0) {
					System.out.println("TYPE " + room.getRoomType() + ": " + room.getRoomDescription());
					availableRooms.put(room.getRoomType(), room.getNumAvailable());
				} else {
					booked++;
				}
				
				if(booked == 5) {
					System.out.println("Sorry, all rooms have been booked! Please check in another time.");
				}
			}
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		System.out.println("------------------------------------");
		
		System.out.println("Enter the type you want to book: ");
		int choice = in.nextInt();
		
		System.out.println("Enter Your First Name: ");
		String fname = in.next();
		
		System.out.println("Enter Your Last Name: ");
		String lname = in.next();
		
		System.out.println("Enter Your Stay Duration (DAYS): ");
		int duration = in.nextInt();
		System.out.println("CHOICE:" + choice);
		System.out.println("ROOM:" + availableRooms.get(choice));
		try {
			Registry r = LocateRegistry.getRegistry("127.0.0.1", 1099);
			RoomManager rm = (RoomManager) Naming.lookup("rmi://localhost:1099/BookingService");

			rm.bookRoom(availableRooms, choice, fname, lname, duration);
			System.out.println("Room successfully booked!");
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.print("Go back? [Y/n]: ");
		String answer = in.nextLine();
		if(answer.equalsIgnoreCase("y")) {
			menu();
		}
		
	}
	
	public static void listRegisteredGuests() throws MalformedURLException {
		//RoomManagerImpl roomManager = null;
		Scanner in = new Scanner(System.in);
		System.out.print("Option 3 Selected");
//		try {
//			Registry r = LocateRegistry.getRegistry("127.0.0.1", 1099);
//			RoomManager rm = (RoomManager) Naming.lookup("rmi://localhost:1099/BookingService");
//
//			ArrayList<Room> roomList = rm.getRooms();
//			
//			for (Room room : roomList) {
//				System.out.println("ID: " + room.getRoomId());
//				System.out.println("TYPE: " + room.getRoomType());
//				System.out.println("PRICE " + room.getRoomPrice());
//				System.out.println("DESC " + room.getRoomDescription());
//				System.out.println("STATUS " + room.getBookingStatus());
//			}
//		} catch (RemoteException | NotBoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		System.out.print("Go back? [Y/n]: ");
		String answer = in.nextLine();
		if(answer.equalsIgnoreCase("y")) {
			menu();
		}
	}
}