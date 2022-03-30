package main;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Transform {
	
	public static String questionNumber; 
	public static String tableName; 
	public static String insertLine; 
	public static boolean dropDone; 
	public static boolean insertDone; 
	public static String insertLines; 
	public static String insertValues; 
	
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("This program looks at tables from LeetCode and turns them into SQL code to produce tables.");
		System.out.println("Please input the table that you want me to start looking: ");
		Scanner input = new Scanner(System.in);
		
		// EVERY QUESTION WE RUN OUTSIDE WE MUST HAVE CONSTANTS TO STORE AND UPDATE
		
		// BEGIN OF THE WHOLE QUESTION
		while (input.hasNextLine()) { 
			// QUESTION RETREVIAL 
			String qName = questionName(input);
			System.out.println(qName);
			
			// DROP
			while(!dropDone) {
				String dTable = dropTable(input);
				System.out.println(dTable);
				if(input.nextLine().equals("Input:")) { // "INPUT appears" - no more drop, now insert 
					dropDone = true; 
				}
			}
			
			System.out.println(input.nextLine().equals("Input"));

			// INSERT 
			while(!insertDone) {
				String iTable = insertTable(input); 
				System.out.println(iTable);
				if(input.nextLine().equals("Output: ")) { // "OUTPUT" appears - BACK TO TOP OF WHILE LOOP 
					insertDone = true; 
				}	
			}
					
		// Set these up to be null so that for the next time we are running this it will be refreshed for next question
		questionNumber = "";
		tableName = "";
		insertLines = "";
		}
	}
	
	// Adding the title that will let users know that there is this title coming along the way
	public static String title(String questionNumber, String questionName) { 
		return "-- " + questionNumber + ". " + questionName; 
		
	}
	
	public static String questionName(Scanner input) { 
		String questionName;
		String line = input.nextLine();
		Scanner lookInLine = new Scanner(line); 
		questionNumber = lookInLine.next();
		questionNumber = questionNumber.substring(0,questionNumber.length()-1);
		questionName = line.substring(questionNumber.length()+2);
		return title(questionNumber,questionName);
	}
	
	public static String dropTable(Scanner input) { 
		boolean tableRetrieved = false; 
		while(input.hasNextLine() && tableRetrieved != true) { 
			tableName = input.nextLine();
			if(tableName.length()>6) { 
				if(tableName.substring(0,6).equals("Table:")) {
					tableRetrieved = true;
				}
			}
		}
		return "DROP TABLE IF IT EXISTS " + "q_" + questionNumber + "_" + (tableName.substring(7));
	}
	
	/// STILL NEED TO IMPLEMENT THIS THOROUGHLY
	public static String insertTable(Scanner input) { 
		String tableStart; 
		boolean tableRetrieved = false; 
		while(input.hasNextLine() && !tableRetrieved) { 
			tableStart = input.nextLine();
			if(tableStart.length()>1) { 
				while(tableStart.substring(0,1).equals("+")) { // +-----------+------------+--------------+
					input.nextLine(); // | sender_id | send_to_id | request_date |
					input.nextLine(); // +-----------+------------+--------------+
					tableRetrieved = true;
					String rowData = input.nextLine(); 						
					while(rowData.substring(0,1).equals("|")) { 
						insertLines = parseRowData(rowData) + "\n";
						rowData = input.nextLine(); 	
					}
				}
			}
		}
		return insertLines;
	}
	
	public static String parseRowData(String row) { 
		Scanner lookThru = new Scanner(row);
		ArrayList<String> values = new ArrayList<String>(); 
		while(lookThru.hasNext()) {
			String possibleValue = lookThru.next(); 
			if (!possibleValue.equals("|")) { 
				if (possibleValue.contains("/") || (possibleValue.contains("-") & !possibleValue.contains("."))) { 
					possibleValue +=  "'" + possibleValue + "'";
				}
				if (possibleValue.matches(".*[A-Za-z#-].*")) {
					possibleValue += "'" + possibleValue + "'";
				}
			}
			values.add(possibleValue);
		}
		
		for(String value: values) { 
			insertValues +=  value + " "; 
		}
		return insertValues.substring(0,insertValues.length()-3) + ")"; 
		}
	
}
