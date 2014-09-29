package testdata.testdata.datafactory.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import testdata.testdata.model.ProcessingUnit;
import testdata.testdata.model.TestMailItem;
import junit.framework.TestCase;

public class TestDataFactoryTest extends TestCase {
	
	TestDataFactory df = new TestDataFactory();
	
	List<String> eventDetailFieldList = Arrays.asList("VersionNumber","","world"); ;
	List<String> processItemEventDetailList = null;

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

/*
		Event scan format expected is csv with,
		0 Numeric VersionNumber 
		1 String ScannerID
		2 String LocationUnitCode
		3 Date EventDateTime
		4 String EventCode
		5 double Longitude
		6 double Latitude
		7 String UniqueItemId
		8 8short WeightGrams
		9 short LenghMillimetres
		10 short WidthMillimetres
		11 short HeighMillimetres
		12 short DeclaredWeightGrams
		13 short DeclaredLenghMillimetres
		14 short DeclaredWidthMillimetres
		15 short DeclaredHeighMillimetres
		16 String ShapeType
		17 String DestinationPostcode
		18 String SourcePostCode
		19 String CustomerAccountNumber
		ArrayList ValueList
		
		*/
		
		TestMailItem testMailItem = new TestMailItem();
		testMailItem.setAccountNumber("1234567");
		testMailItem.setUniqueItemId("UIID-" + df.getRandomChars(10));
		testMailItem.setDestinationPostCode("DESTPOST");
		double longitude = 53.812453;
		double latitude = -1.711460;
		String locationUnitCode = "OrgUnit1";
		String eventCode = "EV01";
		String productCode = "PROD01";
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, -4);
		Date dt = cal.getTime();
		
		// Call the detail method
		String response = df.createProcessItemEventDetail(testMailItem, "v1.0", "scanner001", longitude, latitude, locationUnitCode, eventCode, dt);
		
		assertNotNull("Null returned from process item event detail creation", response);
		//validateField(response, 0, "v1.0");
		//validateField(response, 1, "ScannerId");
		validateField(response, 2, "OrgUnit1");
		
		DateTime dateTime = new DateTime(dt);
		DateTimeFormatter fmt = ISODateTimeFormat.dateTime(); 
		String dateStr = fmt.withZoneUTC().print(dateTime);
		validateField(response, 3, dateStr);
		validateField(response, 4, "EV01");
		validateField(response, 5, "53.812453");
		validateField(response, 6, "-1.71146");
		validateField(response, 7, testMailItem.getUniqueItemId());
		validateField(response, 17, testMailItem.getDestinationPostCode());
		validateField(response, 19, testMailItem.getAccountNumber());
		
		// check the testMailItem is set with processUnitCode
		assertEquals("OrgUnit1", testMailItem.getProcessingUnitCode());
		
		System.out.println(response);
	}

	private void validateField(String response, int i, String expected) {
		String actual = getValue(response, i);
		assertEquals(expected, actual);
		
	}

	private String getValue(String response, int i) {
		
		if (processItemEventDetailList  == null) {
			processItemEventDetailList = loadList(response);
		}
		return processItemEventDetailList.get(i);
	}

	private List<String> loadList(String response) {
		return Arrays.asList(response.split("\\s*,\\s*"));
	}

}
