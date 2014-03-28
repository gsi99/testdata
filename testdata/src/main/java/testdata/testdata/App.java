package testdata.testdata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import org.fluttercode.datafactory.impl.DataFactory;

import testdata.testdata.datafactory.impl.TestDataFactory;

/**
 * Hello world!
 * 
 */
public class App {
//	private static Random random = new Random(1234567890);
	
/* To Do
 * Config values for  preadvice file:
 * number of rows
 * account number
 * postcode list
 * product types
 * location list
*/
	private class PreadviceFileConfig {
		int numberOfRows;
		String accountNumber;
		String[] productGroupCodeList;
	}

	public static void main(String[] args) {
		TestDataFactory df = new TestDataFactory();
		
		// Setup array of preadvice file configs
		Collection<App.PreadviceFileConfig> preadviceFileConfigs = new ArrayList<App.PreadviceFileConfig>();
		App app = new App();
		PreadviceFileConfig fileConfig1 = app.new PreadviceFileConfig();
		fileConfig1.accountNumber = df.getAccountNumber();
		fileConfig1.numberOfRows = 10;
		fileConfig1.productGroupCodeList = new String[] {"SD01","SF01","RM24","RM48","RM24+","RM48+"};

		PreadviceFileConfig fileConfig2 = app.new PreadviceFileConfig();
		fileConfig2.accountNumber = df.getAccountNumber();
		fileConfig2.numberOfRows = 10;
		fileConfig2.productGroupCodeList = new String[] {"SD01","SF01","RM24+","RM48+"};

		preadviceFileConfigs.add(fileConfig1);
		preadviceFileConfigs.add(fileConfig2);
		
		
		Collection<Collection> preadviceCollections = new ArrayList<Collection>();
		// loop through each config element
		for (Iterator iterator = preadviceFileConfigs.iterator(); iterator.hasNext();) {
			PreadviceFileConfig preadviceFileConfig = (PreadviceFileConfig) iterator.next();
			
			Collection preadviceDetails = df.createPreadviceDetails(preadviceFileConfig.numberOfRows, preadviceFileConfig.accountNumber, preadviceFileConfig.productGroupCodeList);
			preadviceCollections.add(preadviceDetails);
		}
		
		for (Iterator iterator = preadviceCollections.iterator(); iterator.hasNext();) {
			Collection collection = (Collection) iterator.next();
			
			for (Iterator iterator2 = collection.iterator(); iterator2.hasNext();) {
				String preadviceLine = (String) iterator2.next();
				System.out.println(preadviceLine);
			}
			
		}
		
	}

//	private static void generateAccountNumbersToTerminal() {
//		System.out.print("{");
//		for (int i = 0; i < 1000; i++) {
//			for (int j = 0; j < 10; j++) {
//				System.out.print("\""
//						+ String.format("%010d", random.nextInt(1234567890))
//						+ "\",");
//			}
//			System.out.println("");
//		}
//		System.out.print("};");
//	}
}
