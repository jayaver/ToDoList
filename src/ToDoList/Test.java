package ToDoList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;

public class Test {
	 
	public static void main(String[] args) throws ParseException, IOException {
		// TODO Auto-generated method stub
		
		Project project = new Project();
		Task task = new Task();
		ToDo todo = new ToDo();
		
		
		int menuItem = -1;
		while (menuItem !=0) {
			menuItem = todo.menu();
			switch (menuItem) {
			case 1:
				project.addProject();
				break;
			case 2:
				project.displayProject();
				break;
			case 3:
				task.addTask();
				
				break;
				
			case 4:
				todo.editTask();
				
				break;
			case 5:
				todo.displayTask();;
				
				break;
			case 0:
				default:
					System.out.println("Don't recognize input." );
				
			}
		}
	 
		 
	}
}


