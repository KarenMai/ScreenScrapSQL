package main;

// for File
import java.io.FileNotFoundException;
// for Scanner
import java.util.ArrayList;
import java.util.Scanner;

public class ProcessingLeetCodeQuestions {
  public static void main(String[] args) throws FileNotFoundException {
    System.out.println(
        "This program looks at tables from LeetCode and turns them into SQL code to produce tables.");
    Scanner input = new Scanner(System.in);
    while (input.hasNextLine()) {
      String line = input.nextLine();
      Scanner lookInLine = new Scanner(line);
      while (lookInLine.hasNext()) {
        String nameCheck = lookInLine.next();
        if (nameCheck.charAt(nameCheck.length() - 1) == '.') {
          String questionNum = nameCheck.substring(0, nameCheck.length() - 1);
          System.out.println(questionNum);
        }
      }
      if (line.length() != 0) {
        if (line.substring(0, 1).equals("+")) {
          String columnNames = input.nextLine();
          System.out.println(ParseColumns(columnNames));
          String ignoreBorder = input.nextLine();
        }
      }
    }
  }

  public static ArrayList<String> ParseColumns(String columnNames) {
    Scanner lookThrough = new Scanner(columnNames);
    ArrayList<String> column = new ArrayList<String>();
    while (lookThrough.hasNext()) {
      String possibleColumn = lookThrough.next();
      if (!possibleColumn.equals("|")) {
        column.add(possibleColumn);
      }
    }
    return column;
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


//

/**
 * -- 627. Swap Salary drop table if exists q_627_Salary; CREATE TABLE q_627_Salary(id int, name
 * varchar(255), sex ENUM(“m”,”f”), salary int); insert into q_627_Salary values(1, "A", "m",2500);
 * insert into q_627_Salary values(2, "B", "f",1500); insert into q_627_Salary values(3, "C",
 * "m",5500); insert into q_627_Salary values(4, "D", "f",500 );
 */
