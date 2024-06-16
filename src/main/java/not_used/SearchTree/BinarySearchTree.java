package not_used.SearchTree;

import java.util.Optional;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<T>, V> extends AbstractSearchTree<T, V> {

    public BinarySearchTree() {
        this._root = null;
    }

    public BinarySearchTree(T key, V value) {
        this._root = new Node<T, V>(key, value);
    }

    public void insert(T key, V value) {
        Node<T, V> insertionNode = new Node<T, V>(key, value);
        insertNode(insertionNode);
    }

    @Override
    public void insertNode(Node<T, V> node) throws IllegalArgumentException {
        if (node == null)
        {
            throw new IllegalArgumentException();
        }

        Node<T, V> parentNode = null;
        Node<T, V> currentNode = this._root;
        var targetKey = node.getKey();

        while (currentNode != null) {
            parentNode = currentNode;
            if (targetKey.compareTo(currentNode.getKey()) < 0) {
                currentNode = currentNode.left;
            }
            else if (targetKey.compareTo(currentNode.getKey()) > 0) {
                currentNode = currentNode.right;
            }
            else {
                throw new IllegalArgumentException("A node with the same key already exists.");
            }
        }

        if (parentNode == null) {
            this._root = node;
        }
        else if (targetKey.compareTo(parentNode.getKey()) < 0) {
            parentNode.left = node;
        }
        else {
            parentNode.right = node;
        }
    }

    public Optional<Stack<Node<T, V>>> findPath(Node<T, V> target) throws IllegalArgumentException {
        if (target == null) {
            throw new IllegalArgumentException("Target is null.");
        }
        
        Node<T, V> currentNode = this._root;
        if (currentNode == null) {
            return Optional.empty();
        }

        Stack<Node<T, V>> path = new Stack<>();
        path.push(currentNode);

        while (currentNode != null)
        {
            int comparison = target.getKey().compareTo(currentNode.getKey());

            if (comparison > 0) {
                currentNode = currentNode.left;
            }
            else if (comparison < 0) {
                currentNode = currentNode.right;
            }
            else
            {
                break;
            }

            path.push(currentNode);
        }

        return Optional.ofNullable(path.peek() == null ? null : path);
    }

    @Override
    public void removeNode(T key) throws IllegalArgumentException {
        Node<T, V> parentNode = null;
        Node<T, V> currentNode = this._root;

        while (currentNode != null && !currentNode.getKey().equals(key)) {
            parentNode = currentNode;
            if (key.compareTo(currentNode.getKey()) < 0) {
                currentNode = currentNode.left;
            }
            else if (key.compareTo(currentNode.getKey()) > 0){
                currentNode = currentNode.right;
            }
            else
            {
                break;
            }
        }

        if (currentNode == null) {
            throw new IllegalArgumentException("Key does not exist");
        }


        if (currentNode.left == null && currentNode.right == null) {
            if (parentNode == null) {
                this._root = null;
            }
            else if (parentNode.left == currentNode) {
                parentNode.left = null;
            }
            else {
                parentNode.right = null;
            }

            currentNode = null;
        }

        else if (currentNode.left != null && currentNode.right != null) {
            Node<T, V> parentNodeOfSuccessor = currentNode;
            Node<T, V> successor = currentNode.right;

            while (successor.left != null) {
                parentNodeOfSuccessor = successor;
                successor = successor.left;
            }

            currentNode.copyFrom(successor);

            if (parentNodeOfSuccessor != currentNode) {
                parentNodeOfSuccessor.left = successor.right;
            } else {
                parentNodeOfSuccessor.right = null;
            }
        }

        else {
            Node<T, V> child = (currentNode.left != null) ? currentNode.left : currentNode.right;

            if (currentNode != this._root) {
                if (parentNode.left == currentNode) {
                    parentNode.left = child;
                } else {
                    parentNode.right = child;
                }
            } else {
                this._root = child;
            }
        }
    }
    @Override
    public Node<T, V> findNode(T key) throws IllegalArgumentException {
        var currentNode = this._root;

        while (currentNode != null) {

            if (key.compareTo(currentNode.getKey()) < 0) {
                currentNode = currentNode.left;
            }
            else if (key.compareTo(currentNode.getKey()) > 0) {
                currentNode = currentNode.right;
            }
            else {
                return currentNode;
            }

        }

        throw new IllegalArgumentException("A node with the same key does not exists.");
    }
}
