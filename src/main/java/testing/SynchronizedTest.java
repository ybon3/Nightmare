package testing;

/**
 * 測試 synchronized
 * <p>
 * 用兩個 Thread 對同一個 instance 的兩個 synchronized method invoke
 * <p>
 * 觀察下列設定的執行結果：
 * 1. sub()、add() 都不加 synchronized
 * <p>
 * 2. sub()、add() 都加 synchronized
 * <p>
 * 3. sub()、add() 其中一個加 synchronized
 *
 */
public class SynchronizedTest {

	public static void main(String[] args) {
		final SynchronizedTest prog = new SynchronizedTest();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 500000; i++) {
					prog.add();
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 500000; i++) {
					prog.sub();
				}
			}
		}).start();
	}

	int count = 0;

	synchronized public void add() {
		int t = count;
		count++;
		int t2 = count;
		if (t+1 != t2) {
			System.out.println(t + " add " + t2);
		}
	}

	synchronized public void sub() {
		int t = count;
		count--;
		int t2 = count;
		if (t-1 != t2) {
			System.out.println(t + " sub " + t2);
		}
	}
}
