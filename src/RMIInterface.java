// commented by sham dere (1001574763)
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RMIInterface extends Remote {
	public void storeStudentInfo(ArrayList<StudInfo> si) throws RemoteException;							// save the data to the file
	public ArrayList<StudInfo> fetchMessage()throws RemoteException;										// fetch all data from the file			
}
