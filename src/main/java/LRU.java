import java.util.Optional;
import java.util.Vector;

public class LRU<T> {
	LRU() {
		this.entries = new Vector<>(2);
	}

	public void add(int key, T item) {
		// Replace if key is present
		for (int i = 0; i < entries.size(); i++) {
			if (entries.get(i).key == key) {
				entries.remove(i);
				entries.add(new Entry<>(key, item));
				return;
			}
		}

		// Replace entry if full
		if (entries.size() == CAPACITY) {
			entries.remove(0);
		}

		entries.add(new Entry<>(key, item));
	}

	public Optional<T> get(int key) {
		for (int i = 0; i < entries.size(); i++) {
			if (entries.get(i).key == key) {
				var entry = entries.remove(i);
				entries.add(entry);
				return Optional.of(entry.item);
			}
		}

		return Optional.empty();
	}

	static class Entry<T> {
		Entry(int key, T item) {
			this.key = key;
			this.item = item;
		}

		int key;
		T item;
	}

	private static final int CAPACITY = 2;

	// First entry is least recently used
	private final Vector<Entry<T>> entries;
}
