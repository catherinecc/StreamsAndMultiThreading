package com.catherine.demo;

import java.util.concurrent.CompletableFuture;

public class CompletableFuturePipelineCompletionAndExceptionHandling {
	
	public static void main(String[] args) throws InterruptedException {
		CompletableFuture<Integer> future = new CompletableFuture<Integer>();
		future.thenApply(s -> s*2)
		.thenAccept(s ->{ 
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(s+3 + "" +Thread.currentThread());
		
		})
		.thenRun(()-> System.out.println("Pipeline done"+Thread.currentThread()))
		.exceptionally(throwable -> {System.out.println(throwable.getLocalizedMessage());
		return null;}).thenRun(()->System.out.println("Back after exception"));
		;
		
		CompletableFuture<Integer> future1 = new CompletableFuture<Integer>();
		future.thenApply(s -> s*2)
		.thenAccept(s ->{ 
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(s+3 + "" +Thread.currentThread());
		
		})
		.thenRun(()-> {System.out.println("Pipeline done"+Thread.currentThread()
		);
		throw new RuntimeException();})
		.exceptionally(throwable -> {System.out.println(throwable.getLocalizedMessage());
		return null;}).thenRun(()->System.out.println("Back after exception"));
		;
		
		
		future.complete(2);
		future1.complete(3);
		
		//calling complete multiple times has no effect, it only takes the first value privided
		Thread.sleep(1000);
	}

}
