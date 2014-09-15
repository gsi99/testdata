package testdata.testdata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import org.fluttercode.datafactory.impl.DataFactory;

import testdata.testdata.datafactory.impl.TestDataFactory;
import testdata.testdata.model.TestMailItem;
import testdata.testdata.model.TestMailItems;

/**
 * Hello world!
 * 
 */
public class App {
	// private static Random random = new Random(1234567890);

	private class PreadviceFileConfig {
		String configName;
		int numberOfRows;
		String accountNumber;
		String[] productGroupCodeList;
	}

	static TestDataFactory df = null;

	public static void main(String[] args) {
		df = new TestDataFactory();

		// Setup array of preadvice file configs
		Collection<App.PreadviceFileConfig> preadviceFileConfigs = new ArrayList<App.PreadviceFileConfig>();
		App app = new App();
		PreadviceFileConfig fileConfig1 = app.new PreadviceFileConfig();
		fileConfig1.configName = "fileConfig1";
		fileConfig1.accountNumber = df.getAccountNumber();
		fileConfig1.numberOfRows = 10;
		fileConfig1.productGroupCodeList = new String[] { "SD01", "SF01",
				"RM24", "RM48", "RM24+", "RM48+" };

		PreadviceFileConfig fileConfig2 = app.new PreadviceFileConfig();
		fileConfig2.configName = "fileConfig2";
		fileConfig2.accountNumber = df.getAccountNumber();
		fileConfig2.numberOfRows = 10;
		fileConfig2.productGroupCodeList = new String[] { "SD01", "SF01",
				"RM24+", "RM48+" };

		preadviceFileConfigs.add(fileConfig1);
		preadviceFileConfigs.add(fileConfig2);

		Collection<Collection<String>> preadviceCollections = new ArrayList<Collection<String>>();
		Collection<TestMailItems> testMailItemsList = new ArrayList<TestMailItems>();
		
		// loop through each config element
		for (Iterator<App.PreadviceFileConfig> iterator = preadviceFileConfigs
				.iterator(); iterator.hasNext();) {
			PreadviceFileConfig preadviceFileConfig = (PreadviceFileConfig) iterator
					.next();

			TestMailItems testMailItems = new TestMailItems();
			testMailItems.setConfigName(preadviceFileConfig.configName);
			testMailItemsList.add(testMailItems);
			
			createAndAddPreadviceDetails(preadviceCollections,
					preadviceFileConfig,
					testMailItems);
		}

		for (Iterator<Collection<String>> iterator = preadviceCollections
				.iterator(); iterator.hasNext();) {
			Collection<String> collection = (Collection<String>) iterator
					.next();
			writeCollection(collection);
		}
		
		printTestMailItems(testMailItemsList);

	}

	private static void createAndAddPreadviceDetails(Collection<Collection<String>> preadviceCollections,
			PreadviceFileConfig preadviceFileConfig,
			TestMailItems testMailItems) {
		
		Collection<String> preadviceDetails = df.createPreadviceDetails(
				preadviceFileConfig.numberOfRows,
				preadviceFileConfig.accountNumber,
				preadviceFileConfig.productGroupCodeList,
				testMailItems);
		preadviceCollections.add(preadviceDetails);

	}
	
	private static void printTestMailItems(
			Collection<TestMailItems> testMailItemsList) {
		System.out.println(" ========= TestMailItems =======");
		for (Iterator<TestMailItems> iterator = testMailItemsList.iterator(); iterator.hasNext();) {
			TestMailItems testMailItems = iterator.next();
			System.out.println(testMailItems.getConfigName());
			for (Iterator<TestMailItem> iterator2 = testMailItems.getTestMailItems().iterator(); iterator2.hasNext();) {
				TestMailItem testMailItem = iterator2.next();
				System.out.println("accountNumber=" + testMailItem.getAccountNumber() +
									", uniqueItemId=" + testMailItem.getUniqueItemId() +
									", productCode=" + testMailItem.getProductCode() +
									", destinationPostCode=" + testMailItem.getDestinationPostCode()
									);
			}
		}
		System.out.println(" ========= TestMailItems Finished=======");		
	}

	private static void writeCollection(Collection<String> collection) {
		String suffix = new SimpleDateFormat("yyyyMMddhhmm").format(new Date());
		File file = new File("/tmp/preadvice" + suffix + ".txt");
		for (Iterator<String> iterator2 = collection.iterator(); iterator2
				.hasNext();) {
			String preadviceLine = (String) iterator2.next();
			try {
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(preadviceLine);
				bw.close();
				System.out.println(preadviceLine);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	// private static void generateAccountNumbersToTerminal() {
	// System.out.print("{");
	// for (int i = 0; i < 1000; i++) {
	// for (int j = 0; j < 10; j++) {
	// System.out.print("\""
	// + String.format("%010d", random.nextInt(1234567890))
	// + "\",");
	// }
	// System.out.println("");
	// }
	// System.out.print("};");
	// }
}
