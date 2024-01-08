package com.catherine.demo;

import java.util.concurrent.CompletableFuture;


public class CompletableFutureAndFunctionalInterfaces {
public static void main(String[] args) throws InterruptedException {
	//Below line demonstrates the use of supplier functional interface
	CompletableFuture<Integer> supplierFI = CompletableFuture.supplyAsync(()->2);
	//consumer FI. here then is used with accept because the accept can be done only after
	CompletableFuture<Void> consumerFI = supplierFI.thenAccept(s -> System.out.println(s+2));
	//in the above line, the consumer doesnt return anything, 
	//then you can have a cascading function which accepts nothing, returns nothing. ie a runnable, hence use run
	CompletableFuture<Void> runnableFI = consumerFI.thenRun(()->System.out.println("Running"));
	CompletableFuture<Void> runnableFI1 = runnableFI.thenRun(()->System.out.println("Still Running"));
	CompletableFuture<Void> runnableFI2 = runnableFI1.thenRun(()->System.out.println("STILL Still Running"));
	
	//AS SEEN ABOVE, you can have as many functions as you want on a completable future. It always returns another completablefuture and doesnt really need to terminate
	
Thread.sleep(100);

System.out.println(Thread.currentThread());

CompletableFuture.supplyAsync(CompletableFutureAndFunctionalInterfaces::supplierWithSleep).
thenRun(()->System.out.println("Thread in thenRun: "+ Thread.currentThread()));


runnableFI2.supplyAsync(CompletableFutureAndFunctionalInterfaces::supplierWithOutSleep).
thenRun(()->System.out.println("Thread in thenRun without sleep: "+ Thread.currentThread()));


Thread.sleep(100);
//if we omit this sleep, the main thread might terminate before the completablefuture completes,
//hence stopping the execution of the supplierWithSleep function midway. Uncomment this and observe the difference in op
}


public static int supplierWithSleep() {
	try {
		System.out.println("Thread executing supplierWithSleep "+ Thread.currentThread());
		Thread.sleep(100);
		System.out.println("Sleep ended");
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return 2;
}


public static int supplierWithOutSleep() {
	System.out.println("Thread executing supplierWithOutSleep "+ Thread.currentThread());
	
	return 2;
}

}
