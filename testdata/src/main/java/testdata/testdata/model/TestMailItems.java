package testdata.testdata.model;

import java.util.ArrayList;
import java.util.Collection;

public class TestMailItems {
	String configName;
	Collection<TestMailItem> testMailItems;
	public String getConfigName() {
		return configName;
	}
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	public Collection<TestMailItem> getTestMailItems() {
		return testMailItems;
	}
	public void setTestMailItem(TestMailItem testMailItem) {
		if (testMailItems == null) {
			this.testMailItems = new ArrayList<TestMailItem>();
		}	
		this.testMailItems.add(testMailItem);
	}
}
