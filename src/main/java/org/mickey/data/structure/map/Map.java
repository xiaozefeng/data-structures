package org.mickey.data.structure.map;

/**
 * @author mickey
 * @date 6/8/20 22:43
 */
public interface Map<K, V> {

    void set(K k, V v);

    void add(K k, V v);

    V get(K k);

    int getSize();

    boolean isEmpty();

    void remove(K k);

    boolean contains(K k);

}
