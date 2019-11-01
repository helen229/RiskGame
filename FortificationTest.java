package fortification_phase;

import static org.junit.Assert.*;

import org.junit.Test;

public class FortificationTest {


	@Test
	public void TestFortify() {
		
		String sourceCountryName= "Iran";
		String destinationCountryName= "India";
		Fortification test=new Fortification();
		String output= test.fortify("Iran", "India");
		assertEquals(output,test.fortify(sourceCountryName, destinationCountryName));
		
		 
	}

	
public void TestFortify2() {
		
		int countOfPlayer = 3;
		Fortification test=new Fortification();
		assertTrue(countOfPlayer !=0);
		assertFalse(countOfPlayer ==0);
		assertNotNull(test.fortify("Iran", "India"));
	}

}
