package testdata.testdata.model;

import java.util.Date;

public abstract class OperationsEvent {
	
	private Date eventDateTime;

	public Date getEventDateTime() {
		return eventDateTime;
	}

	public void setEventDateTime(Date eventDateTime) {
		this.eventDateTime = eventDateTime;
	}
	
}
