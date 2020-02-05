package de.fh_rosenheim.algorithmen.lecture10;

import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class FibonacciParallel {


	public static void main(String[] args)  {

		// read in value from user
		System.out.print("Compute Fibonacci number F_n with n = ");
		Scanner sc = null;
		Long n = 0l;
		try {
			sc = new Scanner(System.in);
			if (!sc.hasNextInt()) {
				return;
			}
			n = sc.nextLong();
		} finally{
			sc.close();
		}

		int poolSize = Runtime.getRuntime().availableProcessors();
		//int poolSize = 4;
        ForkJoinPool pool = new ForkJoinPool(poolSize);


		long time = System.currentTimeMillis();
		Long result = pool.invoke(new FibonacciTask(n));
		time = System.currentTimeMillis() - time;
		System.out.println("Fibonacci parallel version=" + result + ", duration= " + time);

	}	
	
	
}
