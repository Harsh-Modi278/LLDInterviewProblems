package LRUCache.algorithms;

public class DoublyLinkedListNode<E> {
    DoublyLinkedListNode<E> next;
    DoublyLinkedListNode<E> prev;
    E data;

    public DoublyLinkedListNode(E data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
