package testing;

import java.text.ParseException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 使用 {@link ScheduledExecutorService} 固定時間叫 {@link ExecutorService} 做事情，
 * 用來觀察 Schedule、Thread、Thread pool 之間的狀態
 * <p>
 * extractExecutor 有限制 Thread 的數量，從測試程式可以觀察到：
 * 不論 scanService 叫 extractExecutor 執行多少 Task，礙於 extractExecutor 有上限，
 * 其實工作都會被暫時擺在旁邊堆疊著。
 */
public class OverThread {
	private static final int PERIOD = 1;

	public static void main(String[] args) throws ParseException {
		final ExecutorService extractExecutor = Executors.newFixedThreadPool(3);

		ScheduledExecutorService scanService = Executors.newSingleThreadScheduledExecutor();
		scanService.scheduleAtFixedRate(new Runnable(){
			@Override
			public void run() {
				extractExecutor.execute(new Task());
				extractExecutor.execute(new Task());
				extractExecutor.execute(new Task());
				extractExecutor.execute(new Task());
				extractExecutor.execute(new Task());
				extractExecutor.execute(new Task());
				System.out.println("SingleThreadScheduledExecutor 增加工作");
			}
		}, 0, PERIOD, TimeUnit.SECONDS);
		//extractExecutor.shutdown();
		try {
			Thread.sleep(60 * 1000 * PERIOD);
		} catch(Exception e) {
			e.printStackTrace();
		}

		//理論上 scanService 執行了 60 次，
		//但因為事情做不完，即使 shutdown 了 extractExecutor 還是一直加班 ...
		scanService.shutdown();
		System.out.println("下班啦，但沒做完的繼續做");
	}

	private static class Task implements Runnable {
		private static int count = 0;
		private final int id = count++;

		@Override
		public void run() {
			try {
				System.out.println("==== START " + id);
				Thread.sleep(3000);
				System.out.println("==== FINISH " + id);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
