package com.catherine.demo;

import java.util.Arrays;
import java.util.List;

public class Main {
public static void main(String[] args) {
	List<Integer> num = Arrays.asList(1,2,3,4,5,6,7,8,9);
	
	System.out.println("Sequential stream");
	num.stream().map(e -> transform(e)).forEach(e -> System.out.println( e));
	System.out.println("When sequential is followed by parallel, The latter always overrides the former. : ");
	num.stream().map(e -> transform(e)).parallel().forEach(e ->System.out.println("ForEach thread"+ e +": "+ Thread.currentThread()));
	
	System.out.println("parallel: ");
	num.parallelStream().map(e -> transform(e)).forEach(e -> System.out.println("ForEach thread"+ e +": "+ Thread.currentThread()));
	
	System.out.println("When parallel is followed by sequential, the latter always overrides the former.  ");
	
	num.parallelStream().map(e -> transform(e)).sequential().forEach(e -> System.out.println("ForEach thread"+ e + Thread.currentThread()));
	
	System.out.println("parallel followed by forEachOrdered");
	
	num.parallelStream().map(e -> transform(e)).forEachOrdered(e -> System.out.println("ForEach thread"+ e + Thread.currentThread()));
System.out.println("====================WITH FILTER===========");
	
	System.out.println("Sequential stream");
	num.stream().map(e -> transform(e)).forEach(e -> System.out.println( e));
	System.out.println("When sequential is followed by parallel, The latter always overrides the former. : ");
	num.stream().map(e -> transform(e)).filter(s-> s%2==0).parallel().forEach(e ->System.out.println("ForEach thread"+ e +": "+ Thread.currentThread()));
	
	System.out.println("parallel: ");
	num.parallelStream().map(e -> transform(e)).filter(s-> s%2==0).forEach(e -> System.out.println("ForEach thread"+ e +": "+ Thread.currentThread()));
	
	System.out.println("When parallel is followed by sequential, the latter always overrides the former.  ");
	
	num.parallelStream().map(e -> transform(e)).filter(s-> s%2==0).sequential().forEach(e -> System.out.println("ForEach thread"+ e + Thread.currentThread()));
	
	System.out.println("parallel followed by forEachOrdered");
	
	num.parallelStream().map(e -> transform(e)).filter(s-> s%2==0).forEachOrdered(e -> System.out.println("ForEach thread"+ e + Thread.currentThread()));

}

static int transform(int e) {
	System.out.println("Transform thread "+e+ ": "+ Thread.currentThread());
	return e;
}
}
/* Generally for  parallel streams, all the stream based operations ie intermediate ones like map and terminal operation like foreach 
 * are all performed end to end on the same thread. 
 *   
 * However for foreach ordered, the threads in forachOrdered maybe different from those in the intermediate operations as can be seen from op
Transform thread 8: Thread[main,5,main]
8
Transform thread 9: Thread[main,5,main]
9
When sequential is followed by parallel, The latter always overrides the former. : 
Transform thread 6: Thread[main,5,main]
ForEach thread6: Thread[main,5,main]
Transform thread 5: Thread[main,5,main]
ForEach thread5: Thread[main,5,main]
Transform thread 8: Thread[main,5,main]
ForEach thread8: Thread[main,5,main]
Transform thread 9: Thread[main,5,main]
ForEach thread9: Thread[main,5,main]
Transform thread 7: Thread[ForkJoinPool.commonPool-worker-2,5,main]
Transform thread 1: Thread[ForkJoinPool.commonPool-worker-4,5,main]
ForEach thread7: Thread[ForkJoinPool.commonPool-worker-2,5,main]
Transform thread 4: Thread[main,5,main]
Transform thread 3: Thread[ForkJoinPool.commonPool-worker-1,5,main]
ForEach thread1: Thread[ForkJoinPool.commonPool-worker-4,5,main]
ForEach thread3: Thread[ForkJoinPool.commonPool-worker-1,5,main]
Transform thread 2: Thread[ForkJoinPool.commonPool-worker-3,5,main]
ForEach thread4: Thread[main,5,main]
ForEach thread2: Thread[ForkJoinPool.commonPool-worker-3,5,main]
parallel: 
Transform thread 6: Thread[main,5,main]
Transform thread 3: Thread[ForkJoinPool.commonPool-worker-3,5,main]
Transform thread 4: Thread[ForkJoinPool.commonPool-worker-7,5,main]
ForEach thread3: Thread[ForkJoinPool.commonPool-worker-3,5,main]
Transform thread 1: Thread[ForkJoinPool.commonPool-worker-6,5,main]
ForEach thread6: Thread[main,5,main]
ForEach thread1: Thread[ForkJoinPool.commonPool-worker-6,5,main]
Transform thread 9: Thread[ForkJoinPool.commonPool-worker-5,5,main]
Transform thread 5: Thread[ForkJoinPool.commonPool-worker-3,5,main]
Transform thread 7: Thread[ForkJoinPool.commonPool-worker-2,5,main]
ForEach thread4: Thread[ForkJoinPool.commonPool-worker-7,5,main]
Transform thread 8: Thread[ForkJoinPool.commonPool-worker-4,5,main]
Transform thread 2: Thread[ForkJoinPool.commonPool-worker-1,5,main]
ForEach thread8: Thread[ForkJoinPool.commonPool-worker-4,5,main]
ForEach thread7: Thread[ForkJoinPool.commonPool-worker-2,5,main]
ForEach thread5: Thread[ForkJoinPool.commonPool-worker-3,5,main]
ForEach thread9: Thread[ForkJoinPool.commonPool-worker-5,5,main]
ForEach thread2: Thread[ForkJoinPool.commonPool-worker-1,5,main]
When parallel is followed by sequential, the latter always overrides the former.  
Transform thread 1: Thread[main,5,main]
ForEach thread1Thread[main,5,main]
Transform thread 2: Thread[main,5,main]
ForEach thread2Thread[main,5,main]
Transform thread 3: Thread[main,5,main]
ForEach thread3Thread[main,5,main]
Transform thread 4: Thread[main,5,main]
ForEach thread4Thread[main,5,main]
Transform thread 5: Thread[main,5,main]
ForEach thread5Thread[main,5,main]
Transform thread 6: Thread[main,5,main]
ForEach thread6Thread[main,5,main]
Transform thread 7: Thread[main,5,main]
ForEach thread7Thread[main,5,main]
Transform thread 8: Thread[main,5,main]
ForEach thread8Thread[main,5,main]
Transform thread 9: Thread[main,5,main]
ForEach thread9Thread[main,5,main]
parallel followed by forEachOrdered
Transform thread 1: Thread[ForkJoinPool.commonPool-worker-4,5,main]
ForEach thread1Thread[ForkJoinPool.commonPool-worker-4,5,main]
Transform thread 9: Thread[ForkJoinPool.commonPool-worker-4,5,main]
Transform thread 8: Thread[ForkJoinPool.commonPool-worker-3,5,main]
Transform thread 4: Thread[ForkJoinPool.commonPool-worker-2,5,main]
Transform thread 5: Thread[ForkJoinPool.commonPool-worker-6,5,main]
Transform thread 6: Thread[main,5,main]
Transform thread 7: Thread[ForkJoinPool.commonPool-worker-7,5,main]
Transform thread 3: Thread[ForkJoinPool.commonPool-worker-1,5,main]
Transform thread 2: Thread[ForkJoinPool.commonPool-worker-5,5,main]
ForEach thread2Thread[ForkJoinPool.commonPool-worker-5,5,main]
ForEach thread3Thread[ForkJoinPool.commonPool-worker-5,5,main]
ForEach thread4Thread[ForkJoinPool.commonPool-worker-5,5,main]
ForEach thread5Thread[ForkJoinPool.commonPool-worker-5,5,main]
ForEach thread6Thread[ForkJoinPool.commonPool-worker-5,5,main]
ForEach thread7Thread[ForkJoinPool.commonPool-worker-5,5,main]
ForEach thread8Thread[ForkJoinPool.commonPool-worker-5,5,main]
ForEach thread9Thread[ForkJoinPool.commonPool-worker-5,5,main]*/
 