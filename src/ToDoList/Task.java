package ToDoList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;

public class Task {
	
	Project project = new Project();
	Scanner input=new Scanner(System.in);
	
	private String tid;
	private String ttitle;
	private String tdesc;
	private String tsdate;
	private String tdate;
	private String tstatus;
	private String tproject;
	
	
	public Task() {
		
	}
	public Task(String tid, String title, String tdesc, String tsdate, 
			String tdate, String tproject, String tstatus ) {
		
		this.tid=tid;
		this.ttitle=title;
		this.tdesc=tdesc;
		this.tsdate=tsdate;
		this.tdate=tdate;
		this.tproject=tproject;
		this.tstatus=tstatus;
	}
	
	private ArrayList<Task> TaskList = new ArrayList<Task>();
	
	
// Input of task and adding to file

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
				   		"Due_Date"+","+","+"Project"+"Status");
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
				pw.append(tid+","+tname+","+tdesc+","+tsdate +","+tdate + ","+assignPrj+","+tstatus+"\n");
				pw.close();
			}
			catch(Exception e){} 
			i++;
		}
	}
		
	
	public String findTask(String tid) {
		String tmp ="";
		
		 {
			try {
				BufferedReader br=new BufferedReader(new FileReader("Task.txt"));
				  String display="";
				  boolean find = false;
				   while( (display=br.readLine()) !=null && find ==false)
				   {
				    String data[]=new String[2];
				    data=display.split(",");
				    for(int i=0;i<2;i++)
				    {
				    	
				    if (tid.equals(data[i])) {
				    	System.out.println("Task No - "+data[i]+" details : ");
				    	System.out.print(data[i+1]+" "+data[i+2]+" "+data[i+3]+
				    			" "+data[i+4]+" "+data[i+5]+" "+data[i+6]);
				    	System.out.println();
				    	tmp=data[i+6];
				    	find = true;
				    }
				     
				    }
				    System.out.println();
				   }
				  }
				  catch(Exception e){}
			 return tmp;
			 }
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
    

	
	
	
	public void updateStatus() throws FileNotFoundException {
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
	
	//Project comparison
	class projectCompare implements Comparator<Task>
	{
	    @Override
	    public int compare(Task p1, Task p2)
	    {
	        return p1.tproject.compareTo(p2.tproject);
	    }
	}
	
	//Status comparison
	class statusCompare implements Comparator<Task>
	{
	    @Override
	    public int compare(Task s1, Task s2)
	    {
	        return s1.tstatus.compareTo(s2.tstatus);
	    }
	}
	
	@Override
    public String toString() {
		return tid + "\t"+ "\t" + ttitle + "\t"+ "\t" + tdesc + "\t"+ "\t" + tsdate + "\t"+ "\t" +
				tdate+"\t"+ "\t"+tproject+"\t"+ "\t"+tstatus;
		
	}
	
	
	//Sorting project-wise
	public void dispByProject() throws IOException {
		TaskList.clear();
		
		addTaskToArrayList();
        
        Collections.sort(TaskList, new projectCompare());
        display();
        
		
	}
	
	//Sorting status-wise	
	public void dispByStatus() throws IOException {
		
		TaskList.clear();
		addTaskToArrayList();
        
        Collections.sort(TaskList, new statusCompare());
        display();
        
		
	}
	
	//Display format for screen
	public void display() {
		System.out.println("Task List (Project-wise) :");
    	System.out.println("**************************"+"\n");
    	System.out.println("Id"+"\t"+"\t"+"Title"+"\t"+"\t"+"Description"+
    	"\t"+"\t"+"Start Date"+"\t"+"\t"+"Due Date"+"\t"+"\t"+"Project" +
    			"\t"+"\t"+"Status"+"\n");
        for(Task tmpTask: TaskList){

			System.out.println(tmpTask);
        }
	}
		
	
	//Update Description
	public void updateDesc() throws FileNotFoundException {
		
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
				    	pw.append(data[i]+","+data[i+1]+","+tmpDesc+","+data[i+3] +","
				    	+data[i+4] + ","+data[i+5]+","+data[i+6]+"\n");
				    	
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
