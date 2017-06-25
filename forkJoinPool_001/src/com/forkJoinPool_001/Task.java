package com.forkJoinPool_001;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
//RecursiveAction是ForkJoinTask的子类
//work-stealing算法 工作窃取算法，只适用ForkJoinTask类的对象，如果传入的是Runable或者Callable类的对象就不会使用这个算法
public class Task extends RecursiveAction {
	private static final long serialVersionUID = 1L;
	private List<Product> products;
	private int first;//用于分割任务
	private int last;
	private double increment;//进行价格的增长

	public Task(List<Product> products, int first, int last, double increment) {
		this.products = products;
		this.first = first;
		this.last = last;
		this.increment = increment;
	}

	@Override
	protected void compute() {
		if (last - first < 10) {//如果任务量小于10个时就执行操作，否者继续分割任务
			updatePrices();
		} else {//如果不小于10，那么就建立两个任务
			int middle = (last + first) / 2;//分割
			System.out.printf("Task: Pending tasks:%s\n", getQueuedTaskCount());
			Task t1 = new Task(products, first, middle + 1, increment);
			Task t2 = new Task(products, middle + 1, last, increment);
			invokeAll(t1, t2);//这是个同步方法，必须等待他的子任务执行完成或者出现异常，取消才会返回。调用这个同步方法将会使用work-stealing算法
			//this.adapt(runnable)适配器，将runnable对象或者callable对象转换成ForkJoinTask对象
		}
	}

	private void updatePrices() {
		for (int i = first; i < last; i++) {
			Product product = products.get(i);
			product.setPrice(product.getPrice() * (1 + increment));
		}
	}

	public static void main(String[] args) {
		ProductListGenerator generator = new ProductListGenerator();
		List<Product> products = generator.generate(10000);
		Task task = new Task(products, 0, products.size(), 0.20);
		//如果没有指定参数，则默认线程数是电脑cpu的数量
		ForkJoinPool pool = new ForkJoinPool();
		pool.execute(task);
		do {
			System.out.printf("Main: Thread Count: %d\n", pool.getActiveThreadCount());//获取活跃的线程
			System.out.printf("Main: Thread Steal: %d\n", pool.getStealCount());//获取一个线程从另一个线程中窃取的任务数
			System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());//返回并行度级别
			try {
				TimeUnit.MILLISECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!task.isDone());//任务完成或者出现异常退出，取消时返回true
		pool.shutdown();//关闭线程池
		if (task.isCompletedNormally()) {//正常完成，不是异常或者取消退出时返回true
			System.out.printf("Main: The process has completednormally.\n");
		}
		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			if (product.getPrice() != 12) {
				System.out.printf("Product %s: %f\n", product.getName(), product.getPrice());
			}
		}
		System.out.println("Main: End of the program.\n");

	}

}