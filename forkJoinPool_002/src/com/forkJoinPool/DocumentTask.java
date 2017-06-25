package com.forkJoinPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class DocumentTask extends RecursiveTask<Integer> {
	
	private static final long serialVersionUID = -4246672334581230357L;
	
	private String document[][];
	private int start, end;
	private String word;

	public DocumentTask(String document[][], int start, int end, String word) {
		this.document = document;
		this.start = start;
		this.end = end;
		this.word = word;
	}

	@Override
	protected Integer compute() {
		int result = 0;
		if (end - start < 10) {
			result = processLines(document, start, end, word);
		} else {
			int mid = (start + end) / 2;
			DocumentTask task1 = new DocumentTask(document, start, mid, word);
			DocumentTask task2 = new DocumentTask(document, mid, end, word);
			invokeAll(task1, task2);//线程阻塞这段时间可以使用窃取算法
			try {
				result = groupResults(task1.get(), task2.get());//等待任务完成再返回这个值
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private Integer processLines(String[][] document, int start, int
			end,String word) {
		
		List<LineTask> tasks=new ArrayList<LineTask>();
		for (int i=start; i < end; i++){
			LineTask task=new LineTask(document[i], 0, document[i].
			length, word);
			tasks.add(task);
		}
		invokeAll(tasks);
		
		int result=0;
		for (int i=0; i<tasks.size(); i++) {
			LineTask task=tasks.get(i);//获得返回值
			try {
			result=result+task.get();
			} catch (Exception e) {
			e.printStackTrace();
			}
		}
		return new Integer(result);}

	private Integer groupResults(Integer number1, Integer number2) {
		Integer result;
		result = number1 + number2;
		return result;
	}

	public class Main {
		public static void main(String[] args) {
			
			DocumentMock mock=new DocumentMock();
			String[][] document=mock.generateDocument(100, 1000, "the");
			DocumentTask task=new DocumentTask(document, 0, 100, &quot;the&quot;);
			ForkJoinPool pool=new ForkJoinPool();
			pool.execute(task);
			do {
				System.out.printf("******************************************\n");
				System.out.printf("Main: Parallelism: %d\n",pool.getParallelism());
				System.out.printf("Main: Active Threads: %d\n",pool.getActiveThreadCount());
				System.out.printf("Main: Task Count: %d\n",pool.getQueuedTaskCount());
				System.out.printf("Main: Steal Count: %d\n",pool.getStealCount());
				System.out.printf("******************************************\n");
				try {
				TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
				e.printStackTrace();
				}
				} while (!task.isDone());
			
			pool.shutdown();
			try {
				System.out.printf("Main: The word appears %d in the document",task.get());
				} catch (Exception e) {
				e.printStackTrace();
				}
			try {
				System.out.printf("Main: The word appears %d in the document",task.get());
				} catch (Exception e) {
				e.printStackTrace();
				}
		}

	}
}
