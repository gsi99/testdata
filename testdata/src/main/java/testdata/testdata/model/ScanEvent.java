package testdata.testdata.model;

import java.util.Date;

public class ScanEvent extends OperationsEvent {

	private String eventCode;
	private String scannerID;
	private String processingUnitCode;
	private GeoLocation eventLocation;
	
	public String getEventCode() {
		return eventCode;
	}
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public String getScannerID() {
		return scannerID;
	}
	public void setScannerID(String scannerID) {
		this.scannerID = scannerID;
	}
	public String getProcessingUnitCode() {
		return processingUnitCode;
	}
	public void setProcessingUnitCode(String processingUnitCode) {
		this.processingUnitCode = processingUnitCode;
	}
	public GeoLocation getEventLocation() {
		return eventLocation;
	}
	
	public void setEventLocation(double longitude, double latitude) {
		if (eventLocation == null) {
			eventLocation = new GeoLocation();
		}
		eventLocation.setLongitude(longitude);
		eventLocation.setLatitude(latitude);
	}
	
}
