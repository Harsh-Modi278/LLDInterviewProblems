public class Entry<K,V> {
    K key;
    V value;
    Entry next;

    Entry(K key,V value){
        this.key = key;
        this.value = value;
    }

    public void setKey(K key){
        this.key = key;
    }

    public K getKey(){
        return key;
    }

    public void setValue(V value){
        this.value = value;
    }

    public V getValue(){
        return value;
    }
}
