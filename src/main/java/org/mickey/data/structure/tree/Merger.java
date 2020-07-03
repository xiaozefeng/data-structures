package org.mickey.data.structure.tree;

/**
 * @author mickey
 * @date 2020/7/2 19:44
 */
public interface Merger<E> {
    E merge(E a, E b);
}
