package Testing;

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
	public Project getProject() {
		return project;
	}
	public Scanner getInput() {
		return input;
	}
	public String getTid() {
		return tid;
	}
	public String getTtitle() {
		return ttitle;
	}
	public String getTdesc() {
		return tdesc;
	}
	public String getTsdate() {
		return tsdate;
	}
	public String getTdate() {
		return tdate;
	}
	public String getTstatus() {
		return tstatus;
	}
	public String getTproject() {
		return tproject;
	}
	

	
}
