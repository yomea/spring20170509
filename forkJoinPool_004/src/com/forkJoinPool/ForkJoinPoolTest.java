package com.forkJoinPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class ForkJoinPoolTest extends RecursiveTask<Integer> {
	
	private static final long serialVersionUID = -2235002516852745904L;
	
	private int[] arr;
	
	private int start;
	
	private int end;
	
	public ForkJoinPoolTest(int[] arr, int start, int end) {
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
			ForkJoinPoolTest task1 = new ForkJoinPoolTest(arr, start, middle);
			ForkJoinPoolTest task2 = new ForkJoinPoolTest(arr, middle + 1, end);
			
			invokeAll(task1, task2);
			
			try {
				sum = task1.get() + task2.get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return sum;
	}
	
	public static void main(String[] args) throws Exception {
		
		int[] arr = new int[] {1,2,3,4,5,6,7};
		
		ForkJoinPool fjp = new ForkJoinPool();
		
		ForkJoinPoolTest fjpt = new ForkJoinPoolTest(arr, 0, 6);
		
		fjp.execute(fjpt);
		
		do {
			
			//System.out.println("...........");
			
			TimeUnit.SECONDS.sleep(1);
			
		} while(!fjpt.isDone());
		
		System.out.println(fjpt.join());
		
		
	}


}
