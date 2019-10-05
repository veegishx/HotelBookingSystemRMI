package RoomManagerInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Hashtable;

import Objects.Guest;
import Objects.Room;

public interface RoomManager extends Remote {
    public default  void bookRoom(Hashtable<Integer, Integer> availableRooms, int choice, String fname, String lname, int duration) throws RemoteException {}
    public ArrayList<Room> getRooms()throws RemoteException;
    public ArrayList<Guest> getGuests() throws RemoteException;
}