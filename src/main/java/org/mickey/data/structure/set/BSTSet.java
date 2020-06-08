package org.mickey.data.structure.set;

import org.mickey.data.structure.tree.BST;

import java.util.List;

/**
 * @author mickey
 * @date 2020/6/8 15:08
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private final BST<E> data;

    public BSTSet() {
        data = new BST<>();
    }

    @Override
    public void add(E e) {
        data.add(e);
    }

    @Override
    public void remove(E e) {
        data.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return data.contains(e);
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");
        System.out.println("Pride and Prejudice");
        List<String> words1 = FileOperation.readFromFile(userDir + "/pride-and-prejudice.txt");
        Set<String> set1 = new BSTSet<>();
        for (String word : words1)
            set1.add(word);

        System.out.println("Total different words:" + set1.getSize());

        System.out.println("A Tale of Tow Cities");
        List<String> words2 = FileOperation.readFromFile(userDir + "/a-tale-of-two-cities.txt");
        Set<String> set2 = new BSTSet<>();
        for (String word : words2)
            set2.add(word);

        System.out.println("Total different words:" + set2.getSize());
    }
}
