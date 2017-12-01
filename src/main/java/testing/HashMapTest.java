package testing;

import java.util.HashMap;

/**
 * {@link HashMap} 相關的測試
 * <p>
 * 1. 測試 remove 之後的情況
 * <p>
 * 2. 測試 put(xxx, null) 與 containsKey(xxx) 的情況
 */
public class HashMapTest {

	public static void main(String[] args) {
		HashMap<String,String> map = new HashMap<>();

		String key = "123";
		System.out.println(map.containsKey(key));
		map.put(key, "456");
		System.out.println(map.containsKey(key));
		map.remove(key);
		System.out.println(map.containsKey(key));

		System.out.println(map.containsKey("zxc"));
		map.put("zxc", null);
		System.out.println(map.containsKey("zxc"));
	}

}
