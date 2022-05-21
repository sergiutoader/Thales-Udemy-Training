package behavioral.iterator.codingexercise;

import java.util.Iterator;
import java.util.Stack;

class Node<T>
{
    public T value;
    public Node<T> left, right, parent;

    public Node(T value)
    {
        this.value = value;
    }

    public Node(T value, Node<T> left, Node<T> right)
    {
        this.value = value;
        this.left = left;
        this.right = right;

        left.parent = right.parent = this;
    }

    public Iterator<Node<T>> preOrder()
    {
        // todo
        return new PreoderIterator<>(this);
    }
}

class PreoderIterator<T> implements Iterator<Node<T>> {

    private Node<T> current, root;

    private Stack<Node<T>> nodeStack = new Stack<>();

    public PreoderIterator(Node<T> root) {
        this.root = current = root;
        nodeStack.push(root);
    }


    @Override
    public boolean hasNext() {
        return current != null && !nodeStack.isEmpty();
    }

    @Override
    public Node<T> next() {
        Node curr = nodeStack.pop();
        if (curr.right != null) {
            nodeStack.push(curr.right);
        }
        if (curr.left != null) {
            nodeStack.push(curr.left);
        }

        return curr;

    }
}

public class Demo {
    public static void main(String[] args) {

    }
}
