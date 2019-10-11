package ToDoList;

//import java.text.ParseException;
import java.io.*;
import java.util.*;

public class Project {
		 
	Scanner input=new Scanner(System.in);
	
	//add project
	
	public void addProject() {
			
		System.out.println("Enter project id:");
		String id=input.nextLine();
		System.out.println("Enter project name:");
		String pname=input.nextLine();  
		  try
		  {
		   File f=new File("Project.txt");
		   PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
		   pw.append("\n"+id+","+pname);
		   pw.close();
		  }
		  catch(Exception e){}  
		 }
	
	
	//display project list
	public void displayProject() {
		
		 {
			try {
				BufferedReader br=new BufferedReader(new FileReader("Project.txt"));
				  String display="";
				   while( (display=br.readLine()) !=null )
				   {
				    String data[]=new String[2];
				    data=display.split(",");
				    for(int i=0;i<2;i++)
				    {
				     System.out.print(data[i]+" ");
				    }
				    System.out.println();
				   }
				  }
				  catch(Exception e){}
			  
			 }	
	}
	
	//Find project 
	public String findProject(String tid) {
		String tmp ="";
		
		 {
			try {
				BufferedReader br=new BufferedReader(new FileReader("Project.txt"));
				  String display="";
				   while( (display=br.readLine()) !=null )
				   {
				    String data[]=new String[2];
				    data=display.split(",");
				    for(int i=0;i<2;i++)
				    {
				    	
				    if (tid.equals(data[i])) {
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
}
