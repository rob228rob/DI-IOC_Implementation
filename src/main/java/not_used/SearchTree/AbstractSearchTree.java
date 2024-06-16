package not_used.SearchTree;

public abstract class AbstractSearchTree<T extends Comparable<T>, V> {

    protected Node<T, V> _root;

    public abstract void insertNode(Node<T, V> node);

    public abstract void removeNode(T key);

    public abstract Node<T, V> findNode(T key);

}