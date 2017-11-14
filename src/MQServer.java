// commented by sham dere (1001574763)
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MQServer extends UnicastRemoteObject implements RMIInterface{
	String fileName = "studentInfo.ser";																		// file name to be created
	private static final long serialVersionUID = 1L;
	protected MQServer() throws RemoteException {
		super();
		Registry registry= LocateRegistry.createRegistry(1099);													// creating registry using port 1099
		registry.rebind("MessageQueue", this);																	// bind given object with recently created registry
	}

	@Override
	public void storeStudentInfo(ArrayList<StudInfo> si) throws RemoteException{								// Store the data into the file
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(si);																				// storing object into file
			//out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public ArrayList<StudInfo> fetchMessage()throws RemoteException{											// get all the previous data present in file
		ArrayList<StudInfo> studInfo = new ArrayList<StudInfo>();
		ArrayList<StudInfo> studInfo1 = new ArrayList<StudInfo>();
		try {
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fis);
			studInfo = (ArrayList<StudInfo>) in.readObject();													// read file object as a array list
			//in.close();
			for (StudInfo studInf : studInfo) {																	// iterate over array list
				studInfo1.add(studInf);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return studInfo1;																						// return file data as array list
	}

	public static void main(String[] args){
		try {
			MQServer m = new MQServer();																		// starting the server
			System.err.println("Server ready");

		}catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}