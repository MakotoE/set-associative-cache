import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cache<T> {
	Cache() {
		this.sets = Stream.generate(LRU<T>::new).limit(4).collect(Collectors.toList());
	}

	public void add(int key, T item) {
		var hash = Integer.hashCode(key);
		sets.get(get_set_id(hash)).add(hash, item);
	}

	public Optional<T> get(int key) {
		var hash = Integer.hashCode(key);
		return sets.get(get_set_id(hash)).get(hash);
	}

	private static int get_set_id(int hash) {
		return hash & 0b11;
	}

	private final List<LRU<T>> sets;
}
