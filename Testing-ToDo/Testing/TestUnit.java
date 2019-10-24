package Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestUnit {

	@Test
	void testFindTask() {
		//fail("Not yet implemented");
		
		ToDo todoTest = new ToDo();
		String output = todoTest.findTask("1012");
		assertEquals("Open", output);
	}
	
	@Test
	void testGetStatus() {
		
		ToDo todoTest = new ToDo();
		String output = todoTest.getStatus("3");
		assertEquals("Cancel", output);
	}
	
	
	
}
