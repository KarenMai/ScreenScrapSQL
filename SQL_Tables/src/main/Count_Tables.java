package main;

import java.util.Scanner;

public class Count_Tables {
	public static void main(String[] args) { 
		System.out.println("Telling you questions?"); 
		Scanner input = new Scanner(System.in);
		int count = 0;
		while (input.hasNextLine()) { 
			input.nextLine();
			count++; 
			System.out.println(count);
		}
	}
}
