public class MyHashMap<K,V> {
    private static final int DEFAULT_CAPACITY = 1 << 4;
    private static final float MAX_CAPACITY = 1 << 30;

    private Entry[] hashTable;

    MyHashMap(int capacity) {
        int sz = 1;
        while (sz < capacity) {
            sz <<= 1;
        }

        hashTable = new Entry[sz];
    }

    public V get(K key) {
        int hashCode = key.hashCode()%hashTable.length;
        Entry node = hashTable[hashCode];

        while (node != null) {
            if (node.key.equals(key)) {
                return (V) node.value;
            }
            node = node.next;
        }

        return null;
    }

    public void put(K key, V value) {
        int hashCode = key.hashCode()%hashTable.length;
        Entry node = hashTable[hashCode];

        if (node == null) {
            Entry newNode = new Entry(key, value);
            hashTable[hashCode] = newNode;
        } else {
            Entry prevNode = node;
            while (node != null) {
                if (node.key.equals(key)) {
                    node.value = value;
                    return;
                }
                prevNode = node;
                node = node.next;
            }
            Entry newNode = new Entry(key, value);
            prevNode.next = newNode;
        }
        return;
    }
}
