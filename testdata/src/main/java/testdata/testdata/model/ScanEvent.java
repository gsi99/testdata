package testdata.testdata.model;

import java.util.Date;

public class ScanEvent extends OperationsEvent {

	private String eventCode;
	private Date scanDateTime;
	private String barcocdeID;
	private int itemLength;
	private int itemWidth;
	private int itemDepth;
	private int itemWeight;
	private String scannerID;
	private String location;
	
	public String getEventCode() {
		return eventCode;
	}
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
	public Date getScanDateTime() {
		return scanDateTime;
	}
	public void setScanDateTime(Date scanDateTime) {
		this.scanDateTime = scanDateTime;
	}
	public String getBarcocdeID() {
		return barcocdeID;
	}
	public void setBarcocdeID(String barcocdeID) {
		this.barcocdeID = barcocdeID;
	}
	public int getItemLength() {
		return itemLength;
	}
	public void setItemLength(int itemLength) {
		this.itemLength = itemLength;
	}
	public int getItemWidth() {
		return itemWidth;
	}
	public void setItemWidth(int itemWidth) {
		this.itemWidth = itemWidth;
	}
	public int getItemDepth() {
		return itemDepth;
	}
	public void setItemDepth(int itemDepth) {
		this.itemDepth = itemDepth;
	}
	public int getItemWeight() {
		return itemWeight;
	}
	public void setItemWeight(int itemWeight) {
		this.itemWeight = itemWeight;
	}
	public String getScannerID() {
		return scannerID;
	}
	public void setScannerID(String scannerID) {
		this.scannerID = scannerID;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
