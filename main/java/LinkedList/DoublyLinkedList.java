package LinkedList;

/**
 * Doubly Linked List implementation
 * <p>
 * Each node inside the list has a reference to the previous and next node.
 * </p>
 * @param <T> value stored in the list
 */

public class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;



    /**
     * Checks if the list is empty
     *
     * @return true if there are no elements, otherwise false
     */
    public boolean isEmpty() {
        return head == null; //checks if the list is empty, if the head is null list is empty
    }

    public int getSize() {
        return size;
    }


    /**
     * Inserts a new element at the beginning of the list.
     * @param data the data to insert into the list
     */
    public void insertAtHead(T data){
        Node<T> newNode = new Node<T>(data);
        if(isEmpty()){
            head = tail = newNode;
        }
        else{
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }
        size++;



    }

    /**
     * Inserts a new element at the end of the list
     * @param data the data to insert into the list
     */
    public void insertAtTail(T data){
        Node<T> newNode = new Node<T>(data);
        if(isEmpty()){
            head = tail = newNode;
        }
        else{
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
        }
        size++;

    }

    /**
     * Returns the first node in the list
     * @return the head (first) node, null if the list is empty
     */
    public Node<T> getHead() {
        return head;
    }

    /**
     * Returns the last node in the list
     * @return the tail (last) node, null if list is empty
     */
    public Node<T> getTail() {
        return tail;
    }

    /**
     * Deletes a given node.
     * <p>If the node is the head or tail a new head or tail will be set with the next or previous node.</p>
     * <p>The size field is updated to show the node was deleted.</p>
     * @param node the node that will be deleted from the doublyLinkedList.
     */
    public void deleteNode(Node<T> node){
        if(isEmpty() || node == null) return;
        if(node == head){
            head = head.getNext();
            if(head != null) head.setPrevious(null);
        }
        else if(node == tail){
            tail = tail.getPrevious();
            if(tail != null) tail.setNext(null);
        }
        else{
            node.getPrevious().setNext(node.getNext());
            node.getNext().setPrevious(node.getPrevious());
        }
        size--;



    }

    /**
     * Deletes a node using the value stored in the node as a reference.
     * <p>This method calls the original deleteNode to delete the node that is equal to the specified value given.</p>
     * @param value the value of the node specified by the user to be deleted.
     */
    public void deleteByValue(T value) {
        if (isEmpty()) return;
        deleteNode(search(value));
    }

    /**
     * Resets linked lists by removing all references to nodes inside the list.
     */
    public void resetAll(){
        head = null;
        tail = null;
    }

    /**
     * Search through LinkedList to find a node by matching the value with a specified value.
     * @param value - the value of the node to be found.
     * @return null
     */

    public Node<T> search(T value){
        Node<T> current = head;
        while (current != null) {
            if (current.getValue().equals(value)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

}
