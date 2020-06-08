package org.mickey.data.structure.set;

import org.mickey.data.structure.linkedlist.LinkedList;

import java.util.List;

/**
 * @author mickey
 * @date 2020/6/8 14:50
 */
public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> data;

    public LinkedListSet() {
        data = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        if (!data.contains(e))
            data.addFirst(e);

    }

    @Override
    public void remove(E e) {
        data.removeElement(e);
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
        Set<String> set1 = new LinkedListSet<>();
        for (String word : words1)
            set1.add(word);

        System.out.println("Total different words:" + set1.getSize());

        System.out.println("A Tale of Tow Cities");
        List<String> words2 = FileOperation.readFromFile(userDir + "/a-tale-of-two-cities.txt");
        Set<String> set2 = new LinkedListSet<>();
        for (String word : words2)
            set2.add(word);

        System.out.println("Total different words:" + set2.getSize());
    }
}
