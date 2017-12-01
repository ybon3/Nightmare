package jb.lazywork.dtc.archive;

import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FlowManagerSimulator {


	public static void main(String[] args) {
		FlowManagerSimulator sim = new FlowManagerSimulator(1, 2);

		for (int c = 0; c < 10; c++) {

			for (int i = 0; i < 30; i++) {
				File f = new File("File[" + i + "]");
				sim.standInQueue(f);
			}

			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private final ConcurrentHashMap<File, Boolean> queue = new ConcurrentHashMap<>();
//	private final HashMap<File, Boolean> queue = new HashMap<>();
	private final Executor extractExecutor;
	private final long maxQuota = 100; //50MB

	public void standInQueue(File f) {
		queue.put(f, false);
	}

	private int count = 1;
	protected FlowManagerSimulator(int scanPeriod, int threadAmount) {
		extractExecutor = Executors.newFixedThreadPool(threadAmount);
		ScheduledExecutorService scanService = Executors.newSingleThreadScheduledExecutor();
		scanService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("run() " + count++);
					//先掃一遍，看看現在還有多少扣打可以用、以及有哪些還沒做
					long quota = 0;
					final HashSet<File> needProcess = new HashSet<>();

					for (File file : queue.keySet()) {
						if (queue.get(file)) {
							quota += file.length();
						} else {
							needProcess.add(file);
						}
					}
					System.out.println("status:  " + needProcess.size() + " / " + queue.size() + "["+quota+"]");

					for (final File file : needProcess) {
						//尋找不會爆 quota 的檔案
						if (quota + file.length() > maxQuota) { continue; }

						queue.put(file, true);	//在這裡就要標記 true，以免重複加入
						quota += file.length();
						extractExecutor.execute(new ExtractProcess(file));

						System.out.println("execute " + file.getName());
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}, 0, scanPeriod, TimeUnit.SECONDS);
	}

	private class ExtractProcess implements Runnable {
		private final File file;

		ExtractProcess(File file) {
			this.file = file;
		}

		@Override
		public void run() {
			System.out.println(new Date() + "：" + file.getName() + " 開始 run 啦");	//Delete
			try {
				file.run();
			} finally {
				queue.remove(file);
			}
			System.out.println(new Date() + "：" + file.getName() + " 處理完畢");	//Delete
		}
	}
}

class File {
	private static Random random = new Random();
	private String name;
	private long length = random.nextInt(10) + 1;

	public File(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public long length() {
		return length;
	}

	public void run() {
		//running
		try {
			if (random.nextBoolean()) {
				Thread.sleep(2000);
			} else {
				throw new Error();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
