package ToDoList;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;

public class Task {
	
	Project project = new Project();
	Scanner input=new Scanner(System.in);
	private String lid;
	
	// Task creation
	public void addTask() throws ParseException {
		
		int i=0;
		int number;
		System.out.println("How many task do you want to enter ;");
		number = input.nextInt();
		
		while (i<number){	
			
			System.out.println("Enter task id:");
			String tid=input.next();
			emptyCheck(tid);
			System.out.println("Enter task title:");
			String tname=input.next();
			emptyCheck(tname);
			System.out.println("Enter task desc:");
			String tdesc=input.next();
			emptyCheck(tdesc);
			String tstatus="Open";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date ();
			String tsdate = sdf.format(date);
			System.out.println("Enter task due date (dd/mm/yyyy) :");
			String tdate=input.next(); 
			dateFormat(tdate);	
		
			//assigning project with task
			System.out.println("assign task with project id:");
			project.displayProject();
			String tpid=input.next(); 
			String assignPrj = project.findProject(tpid);
			try
			{
				File f=new File("Task.txt");
				PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
				pw.append("\n"+tid+","+tname+","+tdesc+","+tstatus+","+tsdate +","+tdate + ","+assignPrj);
				pw.close();
			}
			catch(Exception e){} 
			i++;
		}
		 }
		
		
	
	public void displayTask() {
		
		 {
			try {
			  
				BufferedReader br=new BufferedReader(new FileReader("Task.txt"));
				String display="";
				while( (display=br.readLine()) !=null ) {
					String data[]=new String[7];
					data=display.split(",");
					for(int i=0;i<7;i++) {  
						System.out.print(data[i]+" ");
						if (i==0) {
							 lid = data[i];
						}
					}
					System.out.println(lid);
			   }
			 }
			  catch(Exception e){}
			  
			 }	
	}
	
	
	
	
	public void dateFormat(String value) {
		
		
		boolean checkFormat;

		if (value.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
		    checkFormat=true;
		}
		else {
		   checkFormat=false;
			System.out.println("Invalid date format. Run program again");
			value = null;
			System.exit(0);
		}	
		}
	
	public void emptyCheck(String value) {
		
		    if (value.isEmpty()) {
		    	System.out.println("Invalid input. Run program again");
		    	System.exit(0);
		    	
		    }
		   
	}
}
