import java.util.Optional;
import java.util.Vector;

public class LRU {
	LRU() {
		this.entries = new Vector<>(2);
	}

	public void add(int key, int item) {
		if (entries.size() == CAPACITY) {
			entries.remove(0);
		}

		entries.add(new Entry(key, item));
	}

	public Optional<Integer> get(int key) {
		for (int i = 0; i < entries.size(); i++) {
			if (entries.get(i).key == key) {
				var entry = entries.remove(i);
				entries.add(entry);
				return Optional.of(entry.item);
			}
		}

		return Optional.empty();
	}

	static class Entry {
		Entry(int key, int item) {
			this.key = key;
			this.item = item;
		}

		int key;
		int item;
	}

	private static final int CAPACITY = 2;

	// First entry is least recently used
	private final Vector<Entry> entries;
}
