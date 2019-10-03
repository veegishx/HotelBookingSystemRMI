package RoomManagerInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Objects.Guest;
import Objects.Room;

public interface RoomManager extends Remote {
    public ArrayList<Room> bookRoom() throws RemoteException;
    public ArrayList<Room> getRooms()throws RemoteException;
    public ArrayList<Guest> getGuests() throws RemoteException;
}