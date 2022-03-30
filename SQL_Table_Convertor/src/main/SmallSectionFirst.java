package main;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SmallSectionFirst {
  public static void main(String[] args) {
    System.out.println(
        "This program looks at tables from LeetCode and turns them into SQL code to produce tables.");
    Scanner input = new Scanner(System.in);
    while (input.hasNextLine()) {
      String line = input.nextLine();
      Scanner find = new Scanner(line);
      if (line.))
      if (line.charAt(0) == '+') {
        String columnNames = input.nextLine();
        System.out.println(ParseColumns(columnNames));
        String ignoreBorder = input.nextLine();
        
      }
    }
  }

  public static String getNumbers(String numbers) {
    return numbers.replace(".", "");
  }

  public static ArrayList<String> ParseColumns(String column) {
    ArrayList<String> columns = new ArrayList<String>();
    Scanner names = new Scanner(column);
    while (names.hasNext()) {
      String potentialName = names.next();
      if (potentialName != "|") {
        columns.add(potentialName);
      }
    }
    return columns;
  }

  public static String name(String nameLine) {
    return nameLine.substring(7);
  }

  public static String tableName(int questionNumber, String arguments) {
    return "q_" + questionNumber + "_" + arguments;
  }

  public static String drop(String table) {
    return ("DROP TABLE IF IT EXISTS " + table);
  }

  public static String add(String table, String columnTypes) {
    return ("CREATE TABLE " + table + columnTypes);
  }

  public static String insert(String table, String element) {
    return ("INSERT INTO " + table + "values");
  }
}
