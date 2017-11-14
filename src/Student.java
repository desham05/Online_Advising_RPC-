// commented by sham dere (1001574763)

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
//Coded by Student Name- sham, student Id- 1001574763	
public class Student extends JFrame implements ActionListener{
	Socket clSocket;
	DataInputStream inFromServer;
	DataOutputStream outToServer;
	JLabel lblStudName;
	JTextField txtStudName;
	JLabel lblCourseName;
	JTextField txtCourseName;
	JButton btnSearch;
	private static RMIInterface look_up;

	public Student(){																				// Initialised at the time of main method call
		this.setTitle("Student");																	// --
		this.setSize(1366, 768);																	// -- Set all the GUI properties(reference fro GUI link - https://stackoverflow.com/questions/15247752/gui-client-server-in-java) 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);										// --
		getContentPane().setLayout(null);															// --

		lblStudName = new JLabel("Student Name : ");													// creating label for user to write a student name
		lblStudName.setBounds(15, 15, 100, 25);
		add(lblStudName);

		txtStudName = new JTextField();																	// creating text for user to write a student name
		txtStudName.setBounds(150, 15, 250, 25);
		add(txtStudName);

		lblCourseName = new JLabel("Course Name: ");													// creating label for user to write a course name
		lblCourseName.setBounds(15, 35, 100, 25);
		add(lblCourseName);

		txtCourseName = new JTextField();																	// creating text for user to write a course name
		txtCourseName.setBounds(150, 35, 250, 25);
		add(txtCourseName);

		btnSearch = new JButton("send for clearance");									// creating label button for user to send for clearance
		btnSearch.setBounds(500, 15, 250, 25);
		btnSearch.addActionListener(this);
		add(btnSearch);

		this.setVisible(true);																		// making GUI visible
	}
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		// TODO Auto-generated method stub
		look_up = (RMIInterface) Naming.lookup("MessageQueue");																	  // connecting with server 
		new Student();																											  // constructor call to show client GUI 
	}

	@Override
	public void actionPerformed(ActionEvent ae) {																				  // method called on click of any action, here buttons
		if (ae.getSource().equals(btnSearch)) {																					  // condition is true when user clicks on seacrh for alternative words
			if(txtStudName.getText().trim().isEmpty() == false && txtCourseName.getText().trim().isEmpty() == false){
				try {
					ArrayList<StudInfo>	siList = look_up.fetchMessage();															  // fetch all the data from file
					StudInfo si = new StudInfo();
					si.setStudentName(txtStudName.getText().trim());																  // 
					si.setCourseName(txtCourseName.getText().trim());																  //   get data from student 
					si.setCurrentProcess("Student");
					siList.add(si);
					look_up.storeStudentInfo(siList);																				  // method to get alternative words from server file
					txtStudName.setText("");																						  //	
					txtCourseName.setText("");																						  // set blank variable		
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				System.out.println("please enter student name and course name");
			}
		}
	}
}