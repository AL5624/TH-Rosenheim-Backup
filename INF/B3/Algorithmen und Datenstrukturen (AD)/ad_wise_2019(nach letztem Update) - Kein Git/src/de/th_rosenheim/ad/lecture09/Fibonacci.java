package de.th_rosenheim.ad.lecture09;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Fibonacci {

	// TOP-DOWN / RECURSION
	private static long fibTopDown(int n) {
		// TODO
        if (n <=1) {
            return n;
        }
        return fibTopDown(n-1) + fibTopDown(n-2);

	}



    // TOP-DOWN WITH MEMOIZATION / dynamic programming
    private static Map<Integer,Long> memo = new HashMap<>();
    // top-down with memoization
    private static long fibTopDownMemo(int n) {
        // TODO
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        else {
            long result = fibTopDownMemo(n - 1) + fibTopDownMemo(n - 2);
            memo.put(n, result);
            return result;
        }

    }



 	// BOTTOM UP / ITERATIVE / DYNAMIC PROGRAMMING
	private static long fibBottomUp(int n) {
		if (n <= 1) {
			return n;
		}
		// TODO
		long prev = 1;
		long prevprev = 0;
        for (int i=2; i<=n; i++ ) {
            long temp = prev;
            prev = prev + prevprev;
            prevprev = temp;
        }
        return prev;
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


        memo = new HashMap<>();             // delete all values in memo map
        memo.put(0,0L); //fibonacci(0)
        memo.put(1,1L); //fibonacci(1)
        time = System.currentTimeMillis();
        result = fibTopDownMemo(n);
        time = System.currentTimeMillis() - time;
        System.out.println("Fibonacci top-down / memoization: \tresult=" + result + ", duration= " + time);


        time = System.currentTimeMillis();
		result = fibBottomUp(n);
		time = System.currentTimeMillis() - time;
		System.out.println("Fibonacci bottom-up / iterative: \tresult=" + result + ", duration= " + time);



	}	
	
	
}
