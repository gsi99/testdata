package testdata.testdata.model;

import junit.framework.TestCase;

public class ScanEventTest extends TestCase {

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
	
	public void testSetEventLocation() {
		
		ScanEvent scanEvent = new ScanEvent();
		
		double longitude = 53.812453;
		double latitude = -1.711460;
		
		GeoLocation location = scanEvent.getEventLocation();
		assertNull("Null location expected from scan event", location);
		
		scanEvent.setEventLocation(longitude, latitude);
		location = scanEvent.getEventLocation();
		
		assertNotNull("Null location returned from scan event was not expected", location);
		assertEquals("Geolocation longitude was not correct.", longitude, location.getLongitude(), 0);
		assertEquals("Geolocation latitude was not correct.", latitude, location.getLatitude(), 0);
	
		double longitude2 = 3.812453;
		double latitude2 = -11.711460;
		
		scanEvent.setEventLocation(longitude2, latitude2);

		assertNotNull("Null location returned from scan event was not expected", location);
		assertEquals("Geolocation longitude was not correct.", longitude2, location.getLongitude(), 0);
		assertEquals("Geolocation latitude was not correct.", latitude2, location.getLatitude(), 0);

	}
}
