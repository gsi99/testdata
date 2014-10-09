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
import java.util.List;
import java.util.Random;

import javax.print.DocFlavor.STRING;

import org.fluttercode.datafactory.impl.DataFactory;

import testdata.testdata.App.ItemProcessEventsList;
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
		String[] processingLocationList;
	}
	
	class ItemProcessEventsList {
		String configNameString;
		ArrayList<Collection<String>> itemProcessEventsArrayList;
		
		public ItemProcessEventsList() {
			itemProcessEventsArrayList = new ArrayList<Collection<String>>(); 
		}
		
		public void addItemProcessEvents(Collection<String> itemProcessEvents) {
			itemProcessEventsArrayList.add(itemProcessEvents);
		}
		
		public ArrayList<Collection<String>> getItemProcessEventsArrayList() {
			return itemProcessEventsArrayList;
		}
	}

	static TestDataFactory df = null;

	public static void main(String[] args) {
		df = new TestDataFactory();

		List<App.PreadviceFileConfig> preadviceFileConfigs = createPreadviceConfigData();

		List<Collection<String>> preadviceLists = new ArrayList<Collection<String>>();
		List<ItemProcessEventsList> itemProcessEventsLists = new ArrayList<ItemProcessEventsList>();
		//List<List<Collection<String>>> itemProcessEventsList = new ArrayList<List<Collection<String>>>(); 
		List<TestMailItems> testMailItemsList = new ArrayList<TestMailItems>();
		
		createPreadviceDetailsForEachPreadviceConfig(preadviceFileConfigs,
				preadviceLists, testMailItemsList);

		createItemProcessEventsForEachTestMailItemList(itemProcessEventsLists, testMailItemsList, "EV01");
		
		writeEachPreadviceDataSetToFile(preadviceLists, testMailItemsList);
		
		writeItemProcessEventsDataToFiles(itemProcessEventsLists);
		
		printTestMailItems(testMailItemsList);

	}

	private static void writeEachPreadviceDataSetToFile(
			List<Collection<String>> preadviceLists, List<TestMailItems> testMailItemsList) {
		for (int i = 0; i < preadviceLists.size(); i++) {
			List<String> preadviceLines = (List<String>) preadviceLists.get(i);
			String configName = ((TestMailItems) testMailItemsList.get(i)).getConfigName();
			writeCollection(preadviceLines, configName);
		}
	}
	
	private static List<App.PreadviceFileConfig> createPreadviceConfigData() {
		List<App.PreadviceFileConfig> preadviceFileConfigs = new ArrayList<App.PreadviceFileConfig>();
		App app = new App();
		PreadviceFileConfig fileConfig1 = app.new PreadviceFileConfig();
		fileConfig1.configName = "fileConfig1";
		fileConfig1.accountNumber = df.getAccountNumber();
		fileConfig1.numberOfRows = 10;
		fileConfig1.productGroupCodeList = new String[] { "SD01", "SF01",
				"RM24", "RM48", "RM24+", "RM48+" };
		fileConfig1.processingLocationList = new String[] { "LON01", "LDS01", "COV01", "EDIN01", "LON02", "LON03"};

		PreadviceFileConfig fileConfig2 = app.new PreadviceFileConfig();
		fileConfig2.configName = "fileConfig2";
		fileConfig2.accountNumber = df.getAccountNumber();
		fileConfig2.numberOfRows = 10;
		fileConfig2.productGroupCodeList = new String[] { "SD01", "SF01",
				"RM24+", "RM48+" };
		fileConfig2.processingLocationList = new String[] { "LON01", "LDS01", "COV01", "EDIN01", "LON02", "LON03"};

		preadviceFileConfigs.add(fileConfig1);
		preadviceFileConfigs.add(fileConfig2);
		return preadviceFileConfigs;
	}
	

	private static void createItemProcessEventsForEachTestMailItemList(
			List<ItemProcessEventsList> itemProcessEventsLists,
			List<TestMailItems> testMailItemsList,
			String eventCode) {
		for (Iterator<TestMailItems> iterator = testMailItemsList.iterator(); iterator
				.hasNext();) {
			TestMailItems testMailItems = (TestMailItems) iterator.next();
			
			App app = new App();
			App.ItemProcessEventsList itemProcessEventsList = app.new ItemProcessEventsList();
			itemProcessEventsList.configNameString = testMailItems.getConfigName();
			itemProcessEventsLists.add(itemProcessEventsList);
			itemProcessEventsList.addItemProcessEvents(createItemProcessEventsForEachTestMailItemList(testMailItems, eventCode));
		}
		
	}	

	private static Collection<String> createItemProcessEventsForEachTestMailItemList(
			TestMailItems testMailItems, String eventCode) {
		
		String versionNumber = "v1.0";
		String scannerId = "Scanner01";
		Double longitude = 1.0;
		Double latitude = -1.0;
		Date scanDateTime = new Date();
		Collection<String> itemProcessEvents = new ArrayList<String>(); 
		for (Iterator<TestMailItem> iterator = testMailItems.getTestMailItems().iterator(); iterator.hasNext();) {
			TestMailItem testMailItem = (TestMailItem) iterator.next();	
			itemProcessEvents.add(df.createProcessItemEventDetail(testMailItem, versionNumber, scannerId, longitude, latitude, df.getItem(testMailItems.getProcessingLocationList()), eventCode, scanDateTime));
		}
		return itemProcessEvents;
	}

	private static void createPreadviceDetailsForEachPreadviceConfig(
			Collection<App.PreadviceFileConfig> preadviceFileConfigs,
			Collection<Collection<String>> preadviceCollections,
			Collection<TestMailItems> testMailItemsList) {
		// loop through each config element
		for (Iterator<App.PreadviceFileConfig> iterator = preadviceFileConfigs
				.iterator(); iterator.hasNext();) {
			PreadviceFileConfig preadviceFileConfig = (PreadviceFileConfig) iterator
					.next();

			TestMailItems testMailItems = new TestMailItems();
			testMailItems.setConfigName(preadviceFileConfig.configName);
			testMailItems.setProcessingLocationList(preadviceFileConfig.processingLocationList);
			testMailItemsList.add(testMailItems);
			
			createAndAddPreadviceDetails(preadviceCollections,
					preadviceFileConfig,
					testMailItems);
		}
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
	
	private static void writeItemProcessEventsDataToFiles(
			List<App.ItemProcessEventsList> itemProcessEventsLists) {
		String suffix = "itemProcessEvents"
				+ new SimpleDateFormat("-yyyyMMddhhmm").format(new Date())
				+ "-";

		for (int i = 0; i < itemProcessEventsLists.size(); i++) {
			App.ItemProcessEventsList itemProcessEventsList = (App.ItemProcessEventsList) itemProcessEventsLists
					.get(i);
			StringBuffer fileName = new StringBuffer();
			fileName.append(suffix)
					.append(itemProcessEventsList.configNameString).append("-");

			for (int j = 0; j < itemProcessEventsList
					.getItemProcessEventsArrayList().size(); j++) {
				Collection<String> itemProcessEvents = (Collection<String>) itemProcessEventsList
						.getItemProcessEventsArrayList().get(j);
				fileName.append(j).append(".txt");
				try {
					File file = new File("/tmp/" + fileName.toString());
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					for (Iterator<String> iterator3 = itemProcessEvents
							.iterator(); iterator3.hasNext();) {
						String itemProcessEventLine = (String) iterator3.next();
						bw.write(itemProcessEventLine);
						bw.newLine();
						System.out.println(itemProcessEventLine);
					}
					bw.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	}

	private static void writeCollection(List<String> preadviceRows,
			String configName) {
		String suffix = new SimpleDateFormat("-yyyyMMddhhmm")
				.format(new Date()) + "-" + configName;
		File file = new File("/tmp/preadvice" + suffix + ".txt");
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for (Iterator<String> iterator2 = preadviceRows.iterator(); iterator2.hasNext();) {
				String preadviceLine = (String) iterator2.next();
				bw.write(preadviceLine);
				bw.newLine();
				System.out.println(preadviceLine);
			}
			bw.close();
		} catch (Exception e) {
			System.out.println(e);
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
