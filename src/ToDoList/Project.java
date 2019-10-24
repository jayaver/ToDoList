package ToDoList;


import java.io.*;
import java.util.*;

public class Project {
		 
	Scanner input=new Scanner(System.in);
	
	
	//add project
	
	public void addProject() {
		
		String tmpId = getProject();
		String id;
		
		if (tmpId.equals("")){
			id = "101";
			try {
			   File f=new File("Project.txt");
			   PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
			   pw.close();
			}
			catch(Exception e){}  	
		}
		else {
			id = Integer.toString((Integer.parseInt(tmpId)+1));
		}
		
		System.out.println("Project id : " + id);
		
		System.out.println("Enter project name:");
		String pname=input.nextLine();  
		  try{
		   
			  File f=new File("Project.txt");
			  PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
			  pw.append(id+","+pname+"\n");
			  pw.close();
		  }
		  catch(Exception e){}  
	}
	
	
	//display project list
	public void displayProject() {
			 
		try {
			BufferedReader br=new BufferedReader(new FileReader("Project.txt"));
			String display="";
			System.out.printf("%-20s %-20s", "Project id", "Project Name");
			System.out.println();
			while( (display=br.readLine()) !=null ){
				 String data[]=new String[2];
				 data=display.split(",");
				 for(int i=0;i<2;i++) {
				    
				     System.out.printf("%-20s",data[i]);
				 }
				    System.out.println();
			}
		}
		catch(Exception e){}
			  
	}	
	
	
//Find project for assigning with task
	
	public String findProject(String tid) {
		String tmp ="";
		
			try {
				BufferedReader br=new BufferedReader(new FileReader("Project.txt"));
				String display="";
				boolean find = false;
				while( (display=br.readLine()) !=null && find ==false)
				   {
				    String data[]=new String[2];
				    data=display.split(",");
				    for(int i=0;i<2;i++)
				    {
				    	
				    	if (tid.equals(data[i])) {
				    		System.out.print("Project assigned : " +data[i+1]);
				    		tmp=data[i+1];
				    		find = true;
				    	
				    	}
				     
				    }
				    System.out.println();
				 }
			}
			catch(Exception e){}
			return tmp;
	}

	
	// Searching last id no for generation of auto-id in addProject()
	public String getProject() {
		 String pid = "";
		{		
		try {
			BufferedReader br=new BufferedReader(new FileReader("Project.txt"));
			String display="";
			   while( (display=br.readLine()) !=null ) {
						String data[]=new String[2];
					    data=display.split(",");
					    for(int i=0;i<1;i++) {  
					    	if (i==0) {
								pid = data[i];
							}
							else {
								pid = "";
							}
						     
						}
						   
				}
		}
		catch(Exception e){}
					 	
		}
		return pid;
			 
	}
}
