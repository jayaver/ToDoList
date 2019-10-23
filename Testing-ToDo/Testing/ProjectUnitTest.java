package Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProjectUnitTest {

	@Test
	void testFindProject() {
		
		Project projectTest = new Project();
		String output = projectTest.findProject("107");
		assertEquals("test6", output);
	}

}
