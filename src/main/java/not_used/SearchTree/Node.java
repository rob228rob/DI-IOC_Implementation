package not_used.SearchTree;

public class Node<K, V> {

    public Node(K key, V value) {
        this.value = value;
        this.key = key;
    }

    private V value;

    private K key;

    public K getKey() {
        return key;
    }

    public Node<K, V> left;

    public Node<K, V> right;

    public V getValue()
    {
        return value;
    }

    public void setValue(V value)
    {
        this.value = value;
    }

    public void copyFrom(Node<K, V> other)
    {
        this.value = other.value;
    }
}
