package de.fh_rosenheim.algorithmen.lecture10;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Fibonacci {

	// TOP-DOWN / RECURSION
	private static long fibTopDown(int n) {
        if (n <=1) {
            return n;
        }
        return fibTopDown(n-1) + fibTopDown(n-2);

	}



	public static void main(String[] args)  {
	
		// read in value from user
		System.out.print("Compute Fibonacci number F_n with n = ");
		Scanner sc = null; 
		int n = 0; 
		try {
			sc = new Scanner(System.in);
			if (!sc.hasNextInt()) {
				return;
			}
			n = sc.nextInt();
		} finally{
			sc.close();
		}
		
		long time = System.currentTimeMillis();
		long result = fibTopDown(n);
		time = System.currentTimeMillis() - time;
		System.out.println("Fibonacci top-down / recursive:\t\t\t result=" + result + ", duration= " + time);



	}	
	
	
}
