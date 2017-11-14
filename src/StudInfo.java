// commented by sham dere (1001574763)
import java.io.Serializable;

public class StudInfo implements Serializable{																		// file to be saved 	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String studentName;																						// 
	private String courseName;																						//variable declared for student data
	private String message;																							//
	private String currentProcess;																					//

	public String getStudentName() {																				//
		return studentName;
	}
	public void setStudentName(String studentName) {																//
		this.studentName = studentName;
	}
	public String getCourseName() {																					//	
		return courseName;
	}
	public void setCourseName(String courseName) {																	//  getter setter for the declared variables
		this.courseName = courseName;
	}
	public String getMessage() {																					//
		return message;
	}
	public void setMessage(String message) {																		//
		this.message = message;
	}

	public String getCurrentProcess() {																				//
		return currentProcess;
	}

	public void setCurrentProcess(String currentProcess) {															//
		this.currentProcess = currentProcess;
	}

}
