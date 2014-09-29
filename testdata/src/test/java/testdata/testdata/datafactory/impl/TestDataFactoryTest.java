package testdata.testdata.datafactory.impl;

import java.util.Calendar;
import java.util.Date;

import testdata.testdata.model.ProcessingUnit;
import testdata.testdata.model.TestMailItem;
import junit.framework.TestCase;

public class TestDataFactoryTest extends TestCase {
	
	TestDataFactory df = new TestDataFactory();

	protected static void setUpBeforeClass() throws Exception {
	}

	protected static void tearDownAfterClass() throws Exception {
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCreatePreadviceDetail() {
		//fail("Not yet implemented");
	}
	
	public void testCreateProcessItemEventDetailBasic() {
		
//		Event scan format expected is csv with,
//		Numeric VersionNumber 
//		String eventCode;
//		Date eventDateTime;
//		String scannerID;
//		String unitCode;
//		Date timestamp;
//		double longitude;
//		double latitude;
//		String locationUnitCode;
//		String uniqueItemId;
//		short weightGrams;
//		short declaredWeightGrams;
//		short lenghMillimetres;
//		short widthMillimetres;
//		short heighMillimetres;
//		short declaredLenghMillimetres;
//		short declaredWidthMillimetres;
//		short declaredHeighMillimetres;	
//		String shapeType;
//		String destinationPostcode;
//		String sourcePostCode;
//		String customerAccountNumber;
//		 * 
		
//		TO DO
//			date format should be a UTF format
//			
		
		
//		*/
		
		TestMailItem testMailItem = new TestMailItem();
		testMailItem.setAccountNumber("1234567");
		testMailItem.setUniqueItemId("UIID-" + df.getRandomChars(10));
		double longitude = 53.812453;
		double latitude = -1.711460;
		String locationUnitCode = "OrgUnit1";
		String eventCode = "EV01";

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, -4);
		Date dt = cal.getTime();
		
		// Call the detail method
		String response = df.createProcessItemEventDetail(testMailItem, longitude, latitude, locationUnitCode, eventCode, dt);
		
		assertNotNull("Null returned from process item event detail creation", response);
		// Check OrgUnit Id is set - first value
		// Check long / lat is set
		// Check uiid is set
		// check event code is set
		
		System.out.println(response);
	}

}
