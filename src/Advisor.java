// commented by sham dere (1001574763)
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Advisor {
	private static RMIInterface look_up;

	public static void searchMsgForAdvisor(ArrayList<StudInfo> studInfoList) throws RemoteException{										// search for the message for advisor
		Iterator<StudInfo> it = studInfoList.iterator();
		StudInfo si = new StudInfo();
		ArrayList<StudInfo> studInfLst = new ArrayList<StudInfo>();
		while(it.hasNext()){																												// iterate over the list	
			si = it.next();
			if(si.getMessage() == null){																									// condition for the student waiting for advisory decision	
				si.setCurrentProcess("Advisor");																					
				si.setMessage(new Random().nextBoolean()? "approve" : "disapprove");														// randomly approve disapprove 
				System.out.println("Student Name: "+si.getStudentName()+"  Course Name: "+si.getCourseName()+"   Decision: "+si.getMessage());
			}
			studInfLst.add(si);																	
		}
		look_up.storeStudentInfo(studInfLst);																								// store the value into the file 
	}

	public static int getRecursiveCall() throws RemoteException, InterruptedException{														// recursive call after every 3 seconds
		ArrayList<StudInfo> studInfo = look_up.fetchMessage();																				// fetch all the data 
		searchMsgForAdvisor(studInfo);																										// search for the advisor data
		TimeUnit.SECONDS.sleep(3);																											// wait for 3 seconds	
		return getRecursiveCall();																											// recursive call to method
	}

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException, InterruptedException {
		// TODO Auto-generated method stub
		look_up = (RMIInterface) Naming.lookup("MessageQueue");																				// connect with server
		getRecursiveCall();																													// call recursive method
	}
}
