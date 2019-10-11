package ToDoList;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;

public class Task {
	
	Project project = new Project();
	Scanner input=new Scanner(System.in);
	public void addtask() throws ParseException {
			
		System.out.println("Enter task id:");
		String tid=input.nextLine();
		System.out.println("Enter task title:");
		String tname=input.nextLine();  
		System.out.println("Enter task desc:");
		String tdesc=input.nextLine(); 
		String tstatus="Open";
		System.out.println("Enter task due date:");
		String date1=input.nextLine(); 
		Date tdate=new SimpleDateFormat("dd/MM/yyyy").parse(date1);
		System.out.println("assign task with project id:");
		project.displayProject();
		String tpid=input.nextLine(); 
		String tmp = project.findProject(tpid);
		  try
		  {
		   File f=new File("Task.txt");
		   PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
		   pw.append("\n"+tid+","+tname+","+tdesc+","+tstatus+","+tdate + ","+tmp);
		   pw.close();
		  }
		  catch(Exception e){}  
		 }
	
	public void displayTask() {
		
		 {
			try {
			  
				BufferedReader br=new BufferedReader(new FileReader("Task.txt"));
				String display="";
				while( (display=br.readLine()) !=null ) {
					String data[]=new String[6];
					data=display.split(",");
					for(int i=0;i<6;i++) {  
						System.out.print(data[i]+" ");
					}
					System.out.println();
			   }
			 }
			  catch(Exception e){}
			  
			 }	
	}
		

}
