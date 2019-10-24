package ToDoList;

import java.util.Date;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class ToDo {
	
	Scanner input = new Scanner (System.in);
	Project project = new Project();
	Task task = new Task();
	private ArrayList<Task> TaskList = new ArrayList<Task>();
	
	// Input of task and adding to file

	public void addTask() throws ParseException {
			
		int i=0;
		int number;
		System.out.println("How many task do you want to enter ?");
		number = input.nextInt();
			
		while (i<number){
				
			//Generation of auto task id
			String tmpId = getTask();
			String tid;
				if (tmpId.equals("")){
					tid = "1001";
					try
					  {
					   File f=new File("Task.txt");
					   PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
					   pw.close();
					  }
					  catch(Exception e){}  							
				}
				else {
					tid = Integer.toString((Integer.parseInt(tmpId)+1));
				}
				System.out.println("Task id : " +tid);
				//input from user
				String tname;
				String tdesc;
				String tstatus;
				boolean result;
			//Checking null input from user
			do {
				System.out.println("Enter task title:");
				tname=input.next();
			
				System.out.println("Enter task desc:");
				tdesc=input.next();
				result = isNullOrEmpty(tname, tdesc);
			}while(result);
			
			tstatus="Open";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date ();
			String tsdate = sdf.format(date);
			boolean valid;
			String tdate;
			//Checking date format && compare with current date
			do {
				System.out.println("Enter task due date (dd/mm/yyyy) :");
				tdate=input.next(); 
				valid = dateFormat(tdate);				
			}while(!valid);
			
			//assigning project with task
				
			System.out.println("assign task with project id:");
			project.displayProject();
			String tpid=input.next(); 
			String assignPrj = project.findProject(tpid);
			System.out.print(assignPrj + " assigned to task id " + tid + ".\n");
			System.out.println();
				try
				{
					File f=new File("Task.txt");
					PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
					pw.append("\n"+tid+","+tname+","+tdesc+","+tsdate +","+tdate + ","+assignPrj+","+tstatus);
					pw.close();
				}
				catch(Exception e){} 
				i++;
				
				System.out.println("Successfully added in file: \n");
				System.out.println(tid+" "+tname+" "+tdesc+" "+tsdate +" "+tdate + " "+assignPrj+" "+tstatus);
				
			}
			
	}
			
	//Find task based on id number and return status
	public String findTask(String tid) {
		String tmp ="";
			try {
				BufferedReader br=new BufferedReader(new FileReader("Task.txt"));	 
				String display="";
				boolean find = false;
				while( (display=br.readLine()) !=null && find ==false) {
					   
					 String data[]=new String[2];
					 data=display.split(",");
					    for(int i=0;i<2;i++) {				    	
					    	if (tid.equals(data[i])) {
					    		System.out.println("Task No - "+data[i]+" details : \n");
					    		System.out.print(data[i+1]+" "+data[i+2]+" "+data[i+3]+
					    			" "+data[i+4]+" "+data[i+5]+" "+data[i+6]+"\n");
					    		tmp=data[i+6];
					    		find = true;
					    	}
					     
					    }
					   
				}
		}
		catch(Exception e){}
			return tmp;
	}
		
		
	//Adding data to ArrayList
	public void addTaskToArrayList() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("task.txt")); 
		
		//Reading Task records one by one
		String currentLine = reader.readLine();
	    
		while (currentLine != null) {
			String[] TaskDetail = currentLine.split(",");
			String id = TaskDetail[0];
			String title=TaskDetail[1];
			String desc=TaskDetail[2];
			String sdate=TaskDetail[3];
			String date=TaskDetail[4];
			String project = TaskDetail[5];
			String status = TaskDetail[6];
	        TaskList.add(new Task(id,title,desc,sdate,date,project,status));
			currentLine = reader.readLine();
	       
		}
	}
	   	
	public void updateStatus() throws IOException {
		// Displaying current list of task with details to make easy for user input
		dispById();
		
		//Create new temporary file
		File f=new File("Temp.txt");
		PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
			
		System.out.println("\nEnter task id :");
		String tmpId = input.next();
		String tStatus=findTask(tmpId);
			
		//Checking task status
		if (tStatus.equals("Close")) {
			System.out.println("Updation denied as task is closed");
			return;
		}
		System.out.println("\nSelect progress id: \n");
		
		//Display progress list so user can select one 
		displayProgress();
		//Input from user for progress id
		String psid = input.next();
		String tmpId1 = getStatus(psid);		
			
		try {
			BufferedReader br=new BufferedReader(new FileReader("Task.txt"));
			String display="";
			while( (display=br.readLine()) !=null ) {	  
				String data[]=new String[7];
				data=display.split(",");
				for(int i=0;i<1;i++) {					    	
					if (tmpId.equals(data[i])) {
					    String tmp=data[i+5];
					    pw.append(data[i]+","+data[i+1]+","+data[i+2]+","+data[i+3] +","
					    +data[i+4] + ","+data[i+5]+","+tmpId1+"\n");
					    
					    System.out.println("Status updated of " + data[i]);
					    System.out.println(data[i]+","+data[i+1]+","+data[i+2]+","+data[i+3] +","
					    +data[i+4] + ","+data[i+5]+","+tmpId1+"\n");
					    	
					 }
					 else {
					    pw.append(data[i]+","+data[i+1]+","+data[i+2]+","+data[i+3] +","
					    +data[i+4] + ","+data[i+5]+","+data[i+6]+"\n");
					}
					     
				}
					    
			}
			br.close();
			pw.flush();
			pw.close();
					
			//Deleting old task file
			File Task=new File ("Task.txt");
			Task.delete();
			//Renaming Temp.txt to task.txt
			new File("Temp.txt").renameTo(Task);
					  
		}
		catch(Exception e){}
				
	}
	
	// Description update
	public void updateDesc() throws IOException {
		dispById();
		String tmp ="";
		File f=new File("Temp.txt");
		PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
		System.out.println("Enter task id :");
		String tmpId = input.next();
		String tStatus=findTask(tmpId);
			
		//Checking task status
			
		if (tStatus.equals("Close")) {
			System.out.println("Updation denied as task is closed");
			return;
		}
			
		System.out.println("Enter new description: ");
		String tmpDesc = input.next();
		
			try {
				BufferedReader br=new BufferedReader(new FileReader("Task.txt"));
				String display="";
				while( (display=br.readLine()) !=null ) { 
					String data[]=new String[7];
					data=display.split(",");
					for(int i=0;i<1;i++){    	
					    if (tmpId.equals(data[i])) {
					    	System.out.print(data[i]+" updated successfully with description " + tmpDesc+"\n");
					    	
					    	pw.append(data[i]+","+data[i+1]+","+tmpDesc+","+data[i+3] +","
					    	+data[i+4] + ","+data[i+5]+","+data[i+6]+"\n");    	
					    }
					    else {
					    	pw.append(data[i]+","+data[i+1]+","+data[i+2]+","+data[i+3] +","
							    	+data[i+4] + ","+data[i+5]+","+data[i+6]+"\n");
					    }
					     
					}
					    
				}
				br.close();
				pw.flush();
				pw.close();
				//Deleting old Task file  	
				File Task=new File ("Task.txt");
				Task.delete();
				//Renaming temp file to Task file
				new File("Temp.txt").renameTo(Task);
					  
			}
			catch(Exception e){}
				
	}	

	// Task remove from file
	public void removeTask() throws IOException {
		// Displaying current list of task with details to make easy for user input
		dispById();
		
		//Create new temporary file
		File f=new File("Temp.txt");
		PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
			
		System.out.println("\nEnter task id :");
		String tmpId = input.next();
		
			
		try {
			BufferedReader br=new BufferedReader(new FileReader("Task.txt"));
			String display="";
			while( (display=br.readLine()) !=null ) {	  
				String data[]=new String[7];
				data=display.split(",");
				for(int i=0;i<1;i++) {					    	
					if (tmpId.equals(data[i])) {
						System.out.println(tmpId + " removed successfully ");
					  //  String tmp=data[i+5];
					    pw.append("");
					    	
					 }
					 else {
					    pw.append(data[i]+","+data[i+1]+","+data[i+2]+","+data[i+3] +","
					    +data[i+4] + ","+data[i+5]+","+data[i+6]+"\n");
					}
					     
				}
					    
			}
			
			br.close();
			pw.flush();
			pw.close();
					
			//Deleting old task file
			File Task=new File ("Task.txt");
			Task.delete();
			//Renaming Temp.txt to task.txt
			new File("Temp.txt").renameTo(Task);
					  
		}
		catch(Exception e){}
				
	}
		
	//Project comparison
	class projectCompare implements Comparator<Task>{

		@Override
		 public int compare(Task p1, Task p2)
		    {
		        return p1.getTproject().compareTo(p2.getTproject());
		    }
	}
		
	//Status comparison
	class statusCompare implements Comparator<Task>{
		    
		@Override
		 public int compare(Task s1, Task s2)
		  	{
				return s1.getTstatus().compareTo(s2.getTstatus());
		    }
	}
		
	//Date comparison
	class dateCompare implements Comparator<Task>{
		@Override
		public int compare(Task d1, Task d2)
			{
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date date1=new Date();
			Date date2= new Date();
			try {
				 date1 = format.parse(d1.getTdate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				date2 = format.parse(d2.getTdate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				return date1.compareTo(date2);
			}
	}
		
	//Sorting project-wise
	public void dispByProject() throws IOException {
		TaskList.clear();		
		addTaskToArrayList();       
	    Collections.sort(TaskList, new projectCompare());
	    System.out.println("Task List (Project-wise) :");
		System.out.println("**************************"+"\n");
	    format();	
	}
		
	//Sorting status-wise	
	public void dispByStatus() throws IOException {
		TaskList.clear();
		addTaskToArrayList();      
	    Collections.sort(TaskList, new statusCompare());
	    System.out.println("Task List (Status-wise) :");
		System.out.println("**************************"+"\n");
	    format();	      			
	}
		
	//Sorting date-wise	
	public void dispByDate() throws IOException {	
		TaskList.clear();
		addTaskToArrayList();    
		Collections.sort(TaskList, new dateCompare());
		System.out.println("Task List (Due date-wise) :");
		System.out.println("**************************"+"\n");
		format();		
	}
		
	//Sorting by Task Id
	public void dispById() throws IOException {
		TaskList.clear();
		addTaskToArrayList();
		System.out.println("Task List (Task Id-wise) :");
		System.out.println("**************************"+"\n");
	    format();	       		
	}
			
		
	//Display method for printing format
	public void format() {
		
		//calling method to count open and close tasks
		int oc = openCount();
		int cc= count();

		//Format
		String specifiers = "%-20s %-20s %-20s %-20s %-20s %-20s %-20s%n";
		
		
		System.out.println("You have " + oc + " opend task & " + cc + 
				" closed task \n");
	    	
	    System.out.format(specifiers, "Id", "Title", "Description", "Start Date", "Due Date",
	    		"Project","Status\n");
	    for(Task tmpTask: TaskList){    	
				System.out.format(specifiers, tmpTask.getTid(), tmpTask.getTtitle(), 
						tmpTask.getTdesc(), tmpTask.getTsdate(), tmpTask.getTdate(),
						tmpTask.getTproject(), tmpTask.getTstatus() );
				
	     }
	}
		
	//Counting close task
		
	public int count() {
		int closeCnt = 0;
		int size = TaskList.size();
				
		for (int i=0; i<size; i++) {
			if (TaskList.get(i).getTstatus().equals("Close")) {
				closeCnt++;
			}
					
		}
		return closeCnt;

	}
			
	//Counting open task
			
	public int openCount() {
		int openCnt = 0;
		int size = TaskList.size();
		for (int i=0; i<size; i++) {
			if (!TaskList.get(i).getTstatus().equals("Close")) {
				openCnt++;
			}
						
		}
		return openCnt;
	}
			
			
	
		
		
	// Searching last id no for generation of auto-id in addTask()
		
	public String getTask() {
		String tid = "";
		
			try {
				BufferedReader br=new BufferedReader(new FileReader("task.txt"));
	  			String display="";
				while( (display=br.readLine()) !=null ) {
					String data[]=new String[7];
					data=display.split(",");
					for(int i=0;i<1;i++) {  
						if (i==0) {
							tid = data[i];
						}
						else {
							tid = "";
						}    
					}
						   
				}
			}
			catch(Exception e){}
			
			
			 return tid;
	}
	
	//	getting task status
	public String getStatus(String id) {
			 
		String tmp ="";
			 
			try {
				BufferedReader br=new BufferedReader(new FileReader("status.txt"));
				String display="";
				while( (display=br.readLine()) !=null ) {
					 String data[]=new String[2];
					 data=display.split(",");
					  for(int i=0;i<2;i++)
					    {
					    	if (id.equals(data[i])) {
					    	System.out.print(data[i]+" " + data[i+1]);
					    	tmp=data[i+1];
					    	
					    	}
					     
					    }
					    System.out.println();
					 }
				}
				catch(Exception e){}
				return tmp;
	}
				 
	// Date format validation && past date validation
	public boolean dateFormat(String value) {		
		boolean checkFormat;
		boolean checkDate;
		if (value.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
			    checkFormat=true;
			   
			    DateTimeFormatter f = DateTimeFormatter.ofPattern( "dd/MM/uuuu" );
				ZoneId z = ZoneId.of( "Europe/Stockholm" );
				LocalDate today = LocalDate.now( z );
				LocalDate ld = LocalDate.parse( value , f );
				
				if( ld.isBefore( today ) ) {  // Before today.
					  System.out.println("The date: " + ld + 
						" is in the past, before today: " + today );
					  checkDate = false;
					  value = null;
					  return checkDate;
					}
				else {	
					   checkDate=true;		
						return checkDate;
						
				}
				
		}
		else {	
			   checkFormat=false;
				System.out.println("Invalid date format.\n");
				value = null;
				return checkFormat;
				
		}	
	}
		
	//Blank value validation
		
	public static boolean isNullOrEmpty(String... strArr) {
	     for (String st : strArr) {
	         if  (st==null || st.equals(""))
	             return true;

	       } 
	       return false;
	}

			   
	
		
	// Progress list display
	public void displayProgress() {
		try {
			BufferedReader br=new BufferedReader(new FileReader("status.txt"));
			String display="";
			while( (display=br.readLine()) !=null ){
				String data[]=new String[2];
				data=display.split(",");
				for(int i=0;i<2;i++){
					  System.out.print(data[i]+" ");
				}
					  System.out.println();
			}
		}
		catch(Exception e){}
				  
	}	
			
	
}