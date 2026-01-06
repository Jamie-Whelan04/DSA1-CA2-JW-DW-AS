package LinkedList;

/**
 * Node used in a doubly linked list.
 * <p>
 * Each node stores a value of type T and references
 * the next and previous node in the list
 * </p>
 * @param <T> value stored inside node
 *
 */

public class Node<T> {
    private T value;
    private Node<T> next=null, previous=null;

    /**
     * Constructs a new node with given value.
     * Next and previous are initialised to null.
     * @param value value to be stored in the node
     */
    public Node(T value) {
        this.value = value;

    }

    /**
     * Return value stored in node.
     * @return value of type T
     */
    public T getValue() {
        return value;
    }

    /**
     * Sets value to be stored in the node.
     * @param value new value to be stored
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Return the value of the next node.
     * @return value of next node
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Sets the value of the next node.
     * @param next the next node in the list
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * Returns the value of the previous node.
     * @return value of previous node
     */
    public Node<T> getPrevious() {
        return previous;
    }

    /**
     * Sets the value of the previous node.
     * @param previous the previous node in the list.
     */
    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

}
