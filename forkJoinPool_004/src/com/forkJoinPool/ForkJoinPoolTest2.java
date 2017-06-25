package com.forkJoinPool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class ForkJoinPoolTest2 extends RecursiveTask<Integer> {
	
	private static final long serialVersionUID = -2235002516852745904L;
	
	private int[] arr;
	
	private int start;
	
	private int end;
	
	public ForkJoinPoolTest2(int[] arr, int start, int end) {
		this.arr = arr;
		this.start = start;
		this.end = end;
	}


	@Override
	protected Integer compute() {
		int sum = 0;
		if(end - start < 2) {
			for(int i = start; i <= end; i++) {
				
				sum += arr[i];
			}
			
		} else {
			int middle = (start + end) / 2;
			ForkJoinPoolTest2 task1 = new ForkJoinPoolTest2(arr, start, middle);
			ForkJoinPoolTest2 task2 = new ForkJoinPoolTest2(arr, middle + 1, end);
			
			task1.fork();
			task2.fork();
			
			sum = task1.join() + task2.join();
			
		}
		
		return sum;
	}
	
	public static void main(String[] args) throws Exception {
		
		int[] arr = new int[] {1,2,3,4,5,6,7};
		
		ForkJoinPool fjp = new ForkJoinPool();
		
		ForkJoinPoolTest2 fjpt = new ForkJoinPoolTest2(arr, 0, 6);
		
		fjp.execute(fjpt);
		
		do {
			
			//System.out.println("...........");
			
			TimeUnit.SECONDS.sleep(1);
			
		} while(!fjpt.isDone());
		
		fjp.shutdown();
		
		System.out.println(fjpt.join());
		
	}


}
