package LRUCache.cache.policies;

import LRUCache.algorithms.DoublyLinkedList;
import LRUCache.algorithms.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<Key> implements EvictionPolicy<Key>{
    private DoublyLinkedList<Key> dll;
    private Map<Key, DoublyLinkedListNode<Key>> mapper;

    // DLL -> LRU - node1 - node2 - MRU

    public LRUEvictionPolicy() {
        dll = new DoublyLinkedList<>();
        mapper = new HashMap<>();
    }

    @Override
    public void keyAccessed(Key key) {
        if (mapper.containsKey(key)) {
            dll.removeNode(mapper.get(key));
            dll.addNodeAtLast(mapper.get(key));
        } else {
            DoublyLinkedListNode<Key> newNode = dll.addElementAtLast(key);
            mapper.put(key, newNode);
        }
    }

    @Override
    public Key evictKey() {
        DoublyLinkedListNode<Key> firstNode = dll.getFirstNode();
        if (firstNode == null) {
            return null;
        }

        dll.removeNode(firstNode);
        return firstNode.getData();
    }
}
