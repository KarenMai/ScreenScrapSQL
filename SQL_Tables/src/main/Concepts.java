package main;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Concepts {
	public static void main(String[] args){
        String str = "597. Friend Requests I: Overall Acceptance Rate";
        boolean result = str.substring(0,4).matches("\\d\\d\\d\\p{Punct}");
        System.out.println("Original String : " + str);
        System.out.println("Does string contain only Alphabets? : " + result);
    }
}





