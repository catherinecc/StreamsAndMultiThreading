package com.catherine.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamReduce {
public static void main(String[] args) {
	List<Integer> asList = Arrays.asList(1,2,3,4,5);
	
	Optional<Integer> reduce = asList.stream().reduce((s1,s2)->s1+s2);
	System.out.println(reduce.get());
	Optional<Integer> reduce2 = asList.parallelStream().reduce((s1,s2)->s1+s2);
	System.out.println(reduce2.get());
	Integer reduce3 = asList.stream().reduce(0,(s1,s2)->s1+s2);
	System.out.println(reduce3);
	Integer reduce4 = asList.parallelStream().reduce(0,(s1,s2)->s1+s2);
	System.out.println(reduce4);
	
	//While using parallel streams, the identity used should always be MONOID. 
	//Otherwise the result becomes erroneous. 
	//In the below line, 30 gets used as the starting point in many of the parallel streams,
	//resulting in 30 getting repeatedly added to the result, 
	//causing the resultant value to be very much larger than expected.
	Integer reduce5 = asList.parallelStream().reduce(30,(s1,s2)->s1+s2);
	System.out.println(reduce5);
	
}
}
