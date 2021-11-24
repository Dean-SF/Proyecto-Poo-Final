package datos;

import java.io.Serializable;

/**
 *
 * @author Esteban
 * @param <K>
 * @param <V>
 */
public class KVPair<K,V> implements Serializable{
    private K key;
    private V value;
    
    public KVPair(){};
    public KVPair(K key) {
        this.key = key;
    }
    public KVPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
    public boolean igual(KVPair<K,V> par){
        return key.equals(par.getKey());
    }

    @Override
    public String toString() {
        return "KVPair{" + "key=" + key + ", value=" + value + '}';
    } 
    
}
