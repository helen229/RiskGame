package fortification_phase;

import static org.junit.Assert.*;

import org.junit.Test;

public class FortificationTest2 {

	@Test
	public void testfortify() {
	
		String sourceCountryName= "Iran";
		String destinationCountryName= "India";
		Fortification test=new Fortification();
		assertNotNull(sourceCountryName);
		assertNotNull(destinationCountryName);
		assertNull(sourceCountryName);
		
		
		
	}
	
	public void testfortify2() {
		int army = 1;
		Fortification test=new Fortification();
		assertFalse(army<1);
		assertTrue(army>1);
		
		
		
	}

}
