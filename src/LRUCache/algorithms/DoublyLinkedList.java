package LRUCache.algorithms;

import LRUCache.algorithms.exceptions.InvalidElementException;

public class DoublyLinkedList<E> {
    DoublyLinkedListNode<E> dummyHead;
    DoublyLinkedListNode<E> dummyTail;

    public DoublyLinkedList() {
        dummyHead = new DoublyLinkedListNode<>(null);
        dummyTail = new DoublyLinkedListNode<>(null);

        // empty DLL
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    public void removeNode(DoublyLinkedListNode<E> node) {
        if (node == null) return;
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public boolean isEmpty() {
        return dummyHead.next == dummyTail;
    }

    public DoublyLinkedListNode<E> getFirstNode() {
        if (isEmpty()) {
            return null;
        }
        return dummyHead.next;
    }

    public DoublyLinkedListNode<E> getLastNode() {
        if (isEmpty()) {
            return null;
        }
        return dummyTail.prev;
    }

    public void addNodeAtLast(DoublyLinkedListNode<E> node) {
        DoublyLinkedListNode<E> tail = dummyTail.prev;
        tail.next = node;
        node.prev = tail;

        node.next = dummyTail;
        dummyTail.prev = node;
    }

    public DoublyLinkedListNode<E> addElementAtLast(E element) {
        if (element == null) {
            throw new InvalidElementException();
        }

        DoublyLinkedListNode<E> node = new DoublyLinkedListNode<>(element);
        addNodeAtLast(node);
        return node;
    }
}
