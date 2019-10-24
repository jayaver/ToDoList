package Testing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;

public class Test {
	
	// Main Menu function
	public static void main(String[] args) throws ParseException, IOException {
		// TODO Auto-generated method stub
		Project project = new Project();
		Task task = new Task();
		ToDo todo = new ToDo();	
		int menuItem = -1;
		while (menuItem !=0) {
			menuItem = menu();
			switch (menuItem) {
			case 1:
				project.addProject();
				break;
			case 2:
				project.displayProject();
				break;
			case 3:
				todo.addTask();	
				break;			
			case 4:
				editTask();
				break;
			case 5:
				displayTask();
				break;
			case 0:
				default:
					System.out.println("Don't recognize input." );
				
			}
		}	 
	}



	//Main Menu display
	public static int menu() {
		Scanner input = new Scanner (System.in);
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

	// Edit task options
	public static void editTask() throws IOException {
		Scanner input = new Scanner (System.in);
		int selection;
		Task task = new Task();
		ToDo todo = new ToDo();
		System.out.println("\nEdit Option\n");
		System.out.println("0. Exit from edit");
		System.out.println("1. Description");
		System.out.println("2. Update Status");
		selection = input.nextInt();
	
		if (selection == 0) {
			menu();	
		}
	
		if (selection ==1) {
			todo.updateDesc();
		}
	
		if (selection == 2) {
			todo.updateStatus();	
		}

	}
	
	// Display task option
	public static void displayTask() throws IOException {
		Scanner input = new Scanner (System.in);
		int selection;
		Task task = new Task();
		ToDo todo = new ToDo();
		System.out.println("\nDisplay Option\n");
		System.out.println("0. Exit from display");
		System.out.println("1. Project-wise");
		System.out.println("2. Status-wise");
		System.out.println("3. Date-wise");
	
		selection = input.nextInt();
	
		if (selection == 0) {
			menu();	
		}
	
		if (selection ==1) {
			todo.dispByProject();	
		}
	
		if (selection == 2) {
			todo.dispByStatus();
		}
	
		if (selection == 3) {
			todo.dispByDate();	
		}
	
	}
}
	