package main;

import java.util.Scanner;

public class Question_Name {
	public static void main(String[] args) { 
		System.out.println("Telling you questions?"); 
		Scanner input = new Scanner(System.in);
		while (input.hasNextLine()) { 
			String questionLine = input.nextLine();
			if(questionLine.length()>5) {
				if(questionLine.substring(0,4).matches("\\d\\d\\d\\p{Punct}") || questionLine.substring(0,5).matches("\\d\\d\\d\\d\\p{Punct}")) { 
				System.out.println("-- " + questionLine);
				}
			}
		}
	}
}
