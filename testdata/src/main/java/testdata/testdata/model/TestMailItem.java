package testdata.testdata.model;

public class TestMailItem {
	
	private String uniqueItemId;
	private String ProductCode;
	private String AccountNumber;
	private String DestinationPostCode;
	private String ProcessingUnitCode;

	public String getUniqueItemId() {
		return uniqueItemId;
	}
	public void setUniqueItemId(String uniqueItemId) {
		this.uniqueItemId = uniqueItemId;
	}
	public String getProductCode() {
		return ProductCode;
	}
	public void setProductCode(String productCode) {
		ProductCode = productCode;
	}
	public String getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}
	public String getDestinationPostCode() {
		return DestinationPostCode;
	}
	public void setDestinationPostCode(String destinationPostCode) {
		DestinationPostCode = destinationPostCode;
	}
	public String getProcessingUnitCode() {
		return ProcessingUnitCode;
	}
	public void setProcessingUnitCode(String procesingUnitCode) {
		ProcessingUnitCode = procesingUnitCode;
	}
}
