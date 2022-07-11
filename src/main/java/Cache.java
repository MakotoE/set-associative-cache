import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cache<K, T> {
	Cache() {
		this.sets = Stream.generate(LRU<T>::new).limit(4).collect(Collectors.toList());
	}

	public void add(K key, T item) {
		var hash = key.hashCode();
		sets.get(get_set_id(hash)).add(hash, item);
	}

	public Optional<T> get(K key) {
		var hash = key.hashCode();
		return sets.get(get_set_id(hash)).get(hash);
	}

	private static int get_set_id(int hash) {
		return hash & 0b11;
	}

	private final List<ReplacementAlgorithm<T>> sets;
}
