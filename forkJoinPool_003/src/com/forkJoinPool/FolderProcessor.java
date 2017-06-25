package com.forkJoinPool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class FolderProcessor extends RecursiveTask<List<String>> {
	private static final long serialVersionUID = 1L;
	private String path;
	private String extension;

	public FolderProcessor(String path, String extension) {
		this.path = path;
		this.extension = extension;
	}

	@Override
	protected List<String> compute() {

		List<String> list = new ArrayList<>();
		List<FolderProcessor> tasks = new ArrayList<>();
		File file = new File(path);
		File content[] = file.listFiles();
		if (content != null) {
			for (int i = 0; i < content.length; i++) {
				if (content[i].isDirectory()) {
					FolderProcessor task = new FolderProcessor(content[i].getAbsolutePath(), extension);
					task.fork();//异步调用
					tasks.add(task);
				} else {
					if (checkFile(content[i].getName())) {
						list.add(content[i].getAbsolutePath());
					}
				}
			}
		} //////////////////////////
		if (tasks.size() > 50) {
			System.out.printf("%s: %d tasks ran.\n", file.getAbsolutePath(), tasks.size());
		}
		addResultsFromTasks(list, tasks);
		return list;
	}

	private void addResultsFromTasks(List<String> list, List<FolderProcessor> tasks) {
		for (FolderProcessor item : tasks) {
			list.addAll(item.join());
		}
	}

	private boolean checkFile(String name) {
		return name.endsWith(extension);
	}

	public static class Main {
		public static void main(String[] args) {
			ForkJoinPool pool = new ForkJoinPool();//定义一个ForkJoin线程池

			FolderProcessor system = new FolderProcessor("C:\\Windows", "log");//创建一个任务
			FolderProcessor apps = new FolderProcessor("C:\\Program Files", "log");
			FolderProcessor documents = new FolderProcessor("C:\\Documents And Settings", "log");
			pool.execute(system);//扔到线程池中执行
			pool.execute(apps);
			pool.execute(documents);

			do {
				System.out.printf("******************************************\n");
				System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
				System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
				System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
				System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
				System.out.printf("******************************************\n");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} while ((!system.isDone()) || (!apps.isDone()) || (!documents.isDone()));
			pool.shutdown();//关闭线程池，不过会等待任务的结束
			List<String> results;
			results = system.join();//join获得任务完成后的返回值，与get()方法不同，join()方法不能被中断。如果你中断调用join()方法的线程，这个方法将抛出InterruptedException异常。
			//如果任务抛出任何未受检异常，get()方法将返回一个ExecutionException异常，而join()方法将返回一个RuntimeException异常。
			System.out.printf("System: %d files found.\n", results.size());
			results = apps.join();
			System.out.printf("Apps: %d files found.\n", results.size());
			results = documents.join();
			System.out.printf("Documents: %d files found.\n", results.size());

		}

	}
}