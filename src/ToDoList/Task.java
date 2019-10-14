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
			
			//Generation of auto task id
			String tmpId = getTask();
			String tid;
			System.out.println(tmpId);
			if (tmpId.equals("")){
				tid = "1001";
				try
				  {
				   File f=new File("Task.txt");
				   PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
				   pw.append("Task_id"+","+"Task_Title"+","+"Task_Desc"+","+"Task_Date"+","+
				   		"Due_Date"+","+ "Status"+","+"Project");
				   pw.close();
				  }
				  catch(Exception e){}  							
			}
			else {
				tid = Integer.toString((Integer.parseInt(tmpId)+1));
			}
			
			//input from user
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
				pw.append("\n"+tid+","+tname+","+tdesc+","+tsdate +","+tdate + ","+assignPrj+","+tstatus);
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
	
	public void updateStatus() throws FileNotFoundException {
		String tmp ="";
		File f=new File("Temp.txt");
		PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
		System.out.println("Enter task id :");
		String tmpId = input.next();
		System.out.println("Select progress id: ");
		 displayProgress();
		 String psid = input.next();
		 String tmpId1 = getStatus(psid);
	
		
		 {
			try {
				BufferedReader br=new BufferedReader(new FileReader("Task.txt"));
				  String display="";
				  while( (display=br.readLine()) !=null )
				  {
				    String data[]=new String[7];
				    data=display.split(",");
				    for(int i=0;i<1;i++)
				    {
				    	
				    if (tmpId.equals(data[i])) {
				    	System.out.print(data[i]+" " + data[i+1]);
				    	tmp=data[i+5];
				    	pw.append(data[i]+","+data[i+1]+","+data[i+2]+","+data[i+3] +","
				    	+data[i+4] + ","+data[i+5]+","+tmpId1+"\n");
				    	
				    }
				    else {
				    	pw.append(data[i]+","+data[i+1]+","+data[i+2]+","+data[i+3] +","
						    	+data[i+4] + ","+data[i+5]+","+data[i+6]+"\n");
				    }
				     
				    }
				    System.out.println();
				   }
				  	br.close();
				  	pw.flush();
				  	pw.close();
				  	
				  	File Task=new File ("Task.txt");
				  	Task.delete();
				  	new File("Temp.txt").renameTo(Task);
				  
				  }
				  catch(Exception e){}
			
			 }
	}
	
	
	//Function for generation of auto Task Id
	
	 public String getTask() {
		 String tid = "";
		 {		
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
				 	
		}
		 System.out.println(tid);
		 return tid;
		 
	 }
	 
	 public String getStatus(String id) {
		 
		 String tmp ="";
			
		 {
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
		 
		 
	 }
	 
	 
		 
	 
	 
	 

	// Date format validation
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
	
	//Blank value validation
	
	public void emptyCheck(String value) {
		
		    if (value.isEmpty()) {
		    	System.out.println("Invalid input. Run program again");
		    	System.exit(0);
		    	
		    }
		   
	}
	
	public void displayProgress() {
		
		 {
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
}
