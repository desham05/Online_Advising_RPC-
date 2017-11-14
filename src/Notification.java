
// commented by sham dere (1001574763)
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class Notification {
	private static RMIInterface look_up;

	public static void searchMsgForNotifier(ArrayList<StudInfo> studInfo) throws RemoteException{											// search for the notification message
		Iterator<StudInfo> it = studInfo.iterator();																						// iterate over the data	
		StudInfo si = new StudInfo();
		ArrayList<StudInfo> studInfLst = new ArrayList<StudInfo>();
		while(it.hasNext()){
			si = it.next();
			if(si.getCurrentProcess().equals("Advisor")){																					// condition if the advisor has given the decision or not 		
				si.setCurrentProcess("Notification");
				System.out.println("Student Name: "+si.getStudentName()+"  Course Name: "+si.getCourseName()+"   Decision: "+si.getMessage());
				studInfLst.remove(si);																										// remove from the list once decision passed to student
			}else{
				studInfLst.add(si);
			}
		}
		look_up.storeStudentInfo(studInfLst);																								// store the file with remaining data
	}

	public static int getRecursiveCall() throws RemoteException, InterruptedException{														// recursive method call
		ArrayList<StudInfo> studInfo = look_up.fetchMessage();																				// fetch the data
		searchMsgForNotifier(studInfo);
		TimeUnit.SECONDS.sleep(7); 																											// wait for 7 seconds
		return getRecursiveCall();																											// recursively call method
	}

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException, InterruptedException  {
		// TODO Auto-generated method stub
		look_up = (RMIInterface) Naming.lookup("MessageQueue");																				// connect to server			
		getRecursiveCall();
	}
}
