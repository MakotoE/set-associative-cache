@SuppressWarnings("OptionalGetWithoutIsPresent")
public class Test {
	public static void main(String[] args) {
		{
			var cache = new LRU<Integer>();
			assert(cache.get(0).isEmpty());
			cache.add(0, 0);
			assert(cache.get(0).get() == 0);
			cache.add(1, 1);
			assert(cache.get(1).get() == 1);
			cache.add(2, 2);
			assert(cache.get(2).get() == 2);
			assert(cache.get(0).isEmpty());

			// cache contains 1 and 2
			cache.get(1);
			cache.add(3, 3);
			assert(cache.get(2).isEmpty());
		}
		{
			var cache = new LRU<Integer>();
			cache.add(0, 0);
			cache.add(0, 1);
			assert(cache.get(0).get() == 1);
			cache.add(2, 2);
			assert(cache.get(0).get() == 1);
			assert(cache.get(2).get() == 2);
		}
		{
			var cache = new Cache<Integer, Integer>();
			assert(cache.get(0).isEmpty());
			cache.add(0, 0);
			assert(cache.get(0).get() == 0);
			cache.add(1, 1);
			assert(cache.get(1).get() == 1);
			cache.add(2, 2);
			assert(cache.get(2).get() == 2);
			assert(cache.get(0).isPresent());

			cache.add(1 << (32 - 2), 0);
			cache.add(1 << (32 - 3), 0);
			assert(cache.get(0).isEmpty());
		}
	}
}
