package com.catherine.demo;
import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

public class ThreadAllocationExample {
    public static void main(String[] args) {
        // Simulating a system with a common fork-join pool of 8 threads
        int commonPoolSize = 8;

        System.out.println("Main Thread: " + Thread.currentThread().getName());

        CompletableFuture<BigInteger> factorialFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("calculateFactorial Thread: " + Thread.currentThread().getName());
            return calculateFactorial(100); // Adjust the value based on your computation
        });

        CompletableFuture<String> resultFuture = factorialFuture.thenApplyAsync(result -> {
            System.out.println("thenApplyAsync Thread: " + Thread.currentThread().getName());
            return "Factorial result: " + result;
        });

        // Joining to get the final result
        String result = resultFuture.join();
        System.out.println(result);
    }

    private static BigInteger calculateFactorial(int n) {
        System.out.println("calculateFactorial Inside Thread: " + Thread.currentThread().getName());
        // Simulating a resource-intensive computation: calculating factorial
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
        	 System.out.println("calculateFactorial Inside for loop: " + Thread.currentThread().getName());
             
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
