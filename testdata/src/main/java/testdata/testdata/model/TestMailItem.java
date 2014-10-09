package testdata.testdata.model;

public class TestMailItem {
	
	private String uniqueItemId;
	private String productCode;
	private String accountNumber;
	private String destinationPostCode;
	private String sourcePostCodeString;
	private String processingUnitCode;

	public String getUniqueItemId() {
		return uniqueItemId;
	}
	public void setUniqueItemId(String uniqueItemId) {
		this.uniqueItemId = uniqueItemId;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getDestinationPostCode() {
		return destinationPostCode;
	}
	public void setDestinationPostCode(String destinationPostCode) {
		this.destinationPostCode = destinationPostCode;
	}
	public String getProcessingUnitCode() {
		return processingUnitCode;
	}
	public void setProcessingUnitCode(String procesingUnitCode) {
		this.processingUnitCode = procesingUnitCode;
	}
	public String getSourcePostCodeString() {
		return sourcePostCodeString;
	}
	public void setSourcePostCodeString(String sourcePostCodeString) {
		this.sourcePostCodeString = sourcePostCodeString;
	}
}
