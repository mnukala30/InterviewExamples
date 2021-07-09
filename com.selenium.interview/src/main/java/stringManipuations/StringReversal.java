package stringManipuations;

import java.util.Scanner;

public class StringReversal {


     
	public static void main(String[] args) {
		String name;
		System.out.println("Enter a string");
		Scanner s = new Scanner(System.in);
		name = s.nextLine();
		StringBuffer sb = new StringBuffer(name);

		System.out.println(sb.reverse());
		}
	
	
}
