package main;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Full_Transform {
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("This program looks at tables from LeetCode and turns them into SQL code to produce tables.");
		System.out.println("Please input the table that you want me to start looking: ");
		Scanner input = new Scanner(System.in);
		String questionNumber = null;
		String questionName;
		String nameTable = null; 
		while (input.hasNextLine()) {
// QUESTION # LABEL 
			String line = input.nextLine();
			if(!line.equals("Output:")) {
			Scanner lookInLine = new Scanner(line);
			boolean moveNext = false; 
			while (lookInLine.hasNext() && !moveNext) {
				if(line.substring(0,1).equals("+") || line.substring(0,1).equals("|")) {
					moveNext = true; 
				}
				if(moveNext == false) {
				String nameCheck = lookInLine.next();
				if (nameCheck.charAt(0) >= '0' && nameCheck.charAt(0) <= '9' && nameCheck.length() >= 3 && nameCheck.charAt(nameCheck.length() - 1) == '.') {
					questionNumber = nameCheck.substring(0, nameCheck.length() - 1);
					questionName = line.substring(questionNumber.length()+2); 
					System.out.println("-- " + questionNumber + ". " + questionName);
					boolean dropped = false; 
					while(input.hasNextLine() && dropped != true) { 
						String nextLines = input.nextLine();
						if(nextLines.length() > 6) {
// DROP THE TABLE 
							if(nextLines.substring(0,6).equals("Table:")) { 
								nameTable =tableName(questionNumber, nextLines.substring(7));
								System.out.println(drop(nameTable));
								dropped = true; 
							}
						}
					}
				}
				}
			}
			if(line.length()>0) {
			if(line.substring(0, 1).equals("+")) { // +----------------+---------+
				if(ParseColumns(input.nextLine()).contains("Column")) { // | Column Name    | Type    |
				input.nextLine(); // +----------------+---------+
				String columnNames = input.nextLine(); // First instance of: | sender_id      | int     |
				String toSubmitForColumn = null;  
				while(columnNames.substring(0,1).equals("|")) { 
					toSubmitForColumn += columnNames + ","; 
					// System.out.println((toSubmitForColumn));
					columnNames = input.nextLine(); 
				}
// CREATE 
				toSubmitForColumn = toSubmitForColumn.replace("|","");
				toSubmitForColumn = toSubmitForColumn.replace("null ","");
				System.out.println(add(nameTable,ParseColumns(toSubmitForColumn)));
				}
				else { 
					input.nextLine(); // +----------------+---------+
					String columnNames = input.nextLine(); // First row of data 
					while(columnNames.substring(0,1).equals("|")) { 
// INSERT 
						System.out.println(insert(nameTable,ParseColumns(columnNames)));
						columnNames = input.nextLine();
					}
				}
			
			}
			}
		}
		}
			} // This is where the while loop ends

	public static ArrayList<String> ParseColumns(String columnNames) {
		Scanner lookThrough = new Scanner(columnNames);
		ArrayList<String> column = new ArrayList<String>();
		while (lookThrough.hasNext()) {
			String possibleColumn = lookThrough.next();
			if (!possibleColumn.equals("|")) {
				if(possibleColumn.contains("/") || possibleColumn.contains("-") || possibleColumn.matches(".*[A-Za-z#-].*")) {
					if(possibleColumn.contains("-") && possibleColumn.length()>8) { 
						possibleColumn.replace("/","-");
					}
					possibleColumn = "'" + possibleColumn + "'";
				}
				column.add(possibleColumn);
			}
		}
		return column;
	}
	

	public static String tableName(String questionNum, String tableName) {
		return "q_" + questionNum + "_" + tableName;
	}

	public static String drop(String tableName) {
		return ("DROP TABLE IF IT EXISTS " + tableName);
	}

	public static String add(String table, ArrayList<String> columns) {
		String createStatement =  "CREATE TABLE " + table + " ("; 
		for(String column: columns) { 
			createStatement +=  column + " "; 
		}
		return createStatement.substring(0,createStatement.length()-3) + ")"; 
	}

	public static String insert(String table, ArrayList<String> values) {
		return ("INSERT INTO " + table + " VALUES " + values);
	}
}
