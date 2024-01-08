package com.catherine.demo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class ThreadsAndForkJoinPool {
	public static void main(String[] args) throws InterruptedException {
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		System.out.println("No of available processors: " + availableProcessors);

		int parallelism = ForkJoinPool.commonPool().getParallelism();
		System.out.println("Parallelism achievable by this forkjoinpool is: " + parallelism);
		// the abv stmt gives op 7 since main is running on 1
		List<Integer> l = Arrays.asList(1, 2, 3, 4, 5, 6, 7,8,9,10,11,12,13,14);
		
		//as it is sequential only main thread executes despite the new forkjoinpool
		process(l.stream().map(e -> transform(e)));
		
		//Note that although the new  forkjoinpool is specified in process only, the same is used in transform as well
		//ie the forkjoinpool created in the terminal operation is used in the intermediate operations as well
				process(l.parallelStream().map(e -> transform(e)));

	}

	public static int transform(int e) {
		System.out.println(e+"In Transform : "+Thread.currentThread());
		return e;
	}

public static void process(Stream<Integer> stream) throws InterruptedException {
	ForkJoinPool pool = new ForkJoinPool(100);
	pool.submit(()-> stream.forEach(s->
	{
		System.out.println(s +"In Process : "+Thread.currentThread());
	}
	));
	pool.shutdown();
	pool.awaitTermination(10, TimeUnit.SECONDS);
}
}
