import java.util.Optional;

public interface ReplacementAlgorithm<T> {
	void add(int key, T item);
	Optional<T> get(int key);
}
