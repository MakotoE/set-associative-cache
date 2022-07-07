import java.util.Optional;

public class Cache {
	Cache() {
		this.sets = new LRU[4];
		for (int i = 0; i < this.sets.length; i++) {
			this.sets[i] = new LRU();
		}
	}

	public void add(int key, int item) {
		var hash = Integer.hashCode(key);
		sets[get_set_id(hash)].add(hash, item);
	}

	public Optional<Integer> get(int key) {
		var hash = Integer.hashCode(key);
		return sets[get_set_id(hash)].get(hash);
	}

	private static int get_set_id(int hash) {
		return hash & 0b11;
	}

	private final LRU[] sets;
}
