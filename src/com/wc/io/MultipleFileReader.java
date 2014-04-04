package com.wc.io;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map; 
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.codehaus.jettison.json.JSONObject;

/**
 * This class is used for word Counter API. This class mainly forms the I/O functioning which involves reading of the csv file,
 * read of the individual files derived out of the csv filename, processing the file's data into the MAP which has the keyword and
 * the count mapping
 * 
 * http://localhost:8080/Counter/api/v1/wordcounter?query=TEST
 * @author Smruthi A
 */
public class MultipleFileReader {

	static String csvFile = "C:\\Users\\smrurao\\Desktop\\files\\FileNameList.csv"; //location from where the csv will be read
	
	/**
	 * The method which reads the CSV file specified and create a SET(non
	 * duplicate) filenames to be processed for the word count api
	 * 
	 * @return -Set of filenames
	 */
	public static Set readFileNamesFromCSV() {
		String line = "";
		Set<String> filesList = new HashSet<String>();

		try {

			FileInputStream fis = new FileInputStream(csvFile);
			DataInputStream in = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			//Loop through the CSV using BufferedReader for every line and perform split for the comma delimiter
			//and store in the HashSet datastructure  
			while ((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, ",");
				while (st.hasMoreTokens()) {
					String nextElement = (String) st.nextElement();
					System.out.println(nextElement);
					filesList.add(nextElement.replace("\"", "")); //eg. "abc" the set should have only abc
				}

			}
			br.close(); //close the bufferedReader connection

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done Reading the fileList from CSV File");
		return filesList;
	}

	/**
	 *readFile: This method reads each file obtainted after parsing the CSV and 
	 *creates a MAP for word and number of occurrences
	 *
	 * @param keyword - String array of all the filename to be read
	 * @return -map of the keyyword and respective counts
	 * 
	 */
	public static Map readFile(String args[]) {

		Set<String> filesList = new HashSet<String>();
		Map<String, Integer> filesMap = new TreeMap<String, Integer>();
		
		//Call made to read all the comma delimited filenames from the CSV and store in SET 
		//SET is used to avoid files being read multiple times which can happen by uses entering into CVS file
		filesList = readFileNamesFromCSV();
		Iterator<String> itr = filesList.iterator();
		
		//Loop through the CSV using BufferedReader for every line and perform split for word
		//and store in the TreeMap datastructure using StringTokenizer
		while (itr.hasNext()) {
			try {

				FileInputStream fis = new FileInputStream(itr.next().toString());
				DataInputStream in = new DataInputStream(fis);
				BufferedReader br = new BufferedReader(
						new InputStreamReader(in));
				String line;
				
				
				while ((line = br.readLine()) != null) {
					line = line.replaceAll("[-+.^:;,()\"\\[\\]]", ""); //remove all the special character if any
					StringTokenizer st = new StringTokenizer(line, " "); //tokenize the line into words
					while (st.hasMoreTokens()) {
						String nextElement = (String) st.nextElement();

						if (filesMap.size() > 0 && filesMap.containsKey(nextElement)) {
							int val = 0;
							if (filesMap.get(nextElement) != null) { //find if the word is already present in the map->1) if yes increment the counter 
								val = (Integer) filesMap.get(nextElement);
								val = val + 1;
							}
							filesMap.put(nextElement, val);
						} else {
							filesMap.put(nextElement, 1); //find if the word is already present in the map->2 add new work and put the intial count value as 1)
						}

					}
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				
			}
		}

		for (Map.Entry<String, Integer> entry : filesMap.entrySet()) { //for debugging purpose only
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		return filesMap;
	}

	/**
	 * getCount- This method is used by the rest call to actually fetch the count value
	 * from the preloaded HashMap
	 * 
	 * @param keyword - keyword for which the count data needs to be retrived
	 * @return -JSONObject of the keyyword and respective counts
	 * 
	 */
	public JSONObject getCount(String keyword) {
		
		Map<String, Integer> tm = readFile(null);
		JSONObject jsonObj = new JSONObject();
		
		try {
			System.out.println("keyword "+keyword.toLowerCase());
			
			if (!tm.containsKey(keyword)) { //if keyword to be searched in not present in the datastructure put the count of the keyword as 0
				jsonObj.put("keyword", keyword.toLowerCase());
				jsonObj.put("count", new Integer(0));
			} else {
				jsonObj.put("keyword", keyword); //if keyword to be searched in present in the datastructure fetch the count of the keyword
				jsonObj.put("count", new Integer(tm.get(keyword.toLowerCase())));
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObj;
	}

	/**
	 * Main Method- To test the basic functionality
	 */
	public static void main(String args[]) {

		
		// MultipleFileReader.readFileNamesFromCSV();
		MultipleFileReader.readFile(null);
	}
}