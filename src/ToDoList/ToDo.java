package ToDoList;

import java.util.Date;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class ToDo {
	
	Scanner input = new Scanner (System.in);
	
	Task task = new Task();
	
	public int menu() {
		int choice;
		System.out.println("\nMain Menu\n");
		System.out.println("0. Exit the program");
		System.out.println("1. Add Project");
		System.out.println("2. Display Project");
		System.out.println("3. Add Task");
		System.out.println("4. Edit Task");
		System.out.println("5. Display Task");
		System.out.println();
		System.out.println("Enter choice :");
		choice = input.nextInt();
		return choice;
	}
	
	
	public void editTask() throws FileNotFoundException {
		int selection;
		System.out.println("\nEdit Option\n");
		System.out.println("0. Exit from edit");
		System.out.println("1. Title");
		System.out.println("2. Description");
		System.out.println("3. Due Date");
		System.out.println("4. Update Status");
		System.out.println("5. All");
		selection = input.nextInt();
		
		if (selection == 0) {
			menu();
			
		}
		
		if (selection ==1) {
			
		}
		
		if (selection == 2) {
			
		}
		
		if (selection == 3) {
			
		}
		
		if (selection == 4) {
			task.updateStatus();
		}
		
		if (selection == 5) {
			
		}
	}
	
	
}