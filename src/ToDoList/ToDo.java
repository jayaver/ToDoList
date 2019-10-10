package ToDoList;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class ToDo {
	
	Scanner input = new Scanner (System.in);
	
	public int menu() {
		int choice;
		System.out.println("\nMain Menu\n");
		System.out.println("0. Exit the program");
		System.out.println("1. Add Project");
		System.out.println("2. Display Project");
		System.out.println("3. Add Task");
		System.out.println("4. Display Task");
		System.out.println();
		System.out.println("Enter choice :");
		choice = input.nextInt();
		return choice;
	}
	
}