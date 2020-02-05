package de.fh_rosenheim.algorithmen.lecture10;

import java.util.concurrent.RecursiveTask;


public class FibonacciTask extends RecursiveTask<Long> {
    private final Long n;

    public FibonacciTask(Long n) {
        this.n = n;
    }

    @Override
    public Long compute() {
        if (n <= 1) {
            return n;
        }
        // TODO
        FibonacciTask f1 = new FibonacciTask(n - 1);
        f1.fork();

        //System.out.println("Current Therad Name = "+Thread.currentThread().getName());

        FibonacciTask f2 = new FibonacciTask(n - 2);
        return f2.compute() + (Long) f1.join();

    }
}
