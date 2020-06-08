package org.mickey.data.structure;

import org.junit.Test;
import org.mickey.data.structure.set.BSTSet;
import org.mickey.data.structure.set.FileOperation;
import org.mickey.data.structure.set.LinkedListSet;
import org.mickey.data.structure.set.Set;

import java.util.List;
import java.util.Random;

/**
 * @author mickey
 * @date 2020/6/8 15:15
 */
public class SetTest {


    @Test
    public void testSetTime() {
        String filename = "pride-and-prejudice.txt";
        Set<String> bstSet = new BSTSet<>();
        System.out.println("bst time:" + time(bstSet, filename));

        Set<String> linkedListSet = new LinkedListSet<>();
        System.out.println("linkedListSet time:" + time(linkedListSet, filename));
    }

    @Test
    public void testSetTime2(){
        long opCount = 100000;
        Set<Integer> bstSet = new BSTSet<>();
        System.out.println("bst set:"+time2(bstSet, opCount));
        Set<Integer> linkedListSet = new LinkedListSet<>();
        System.out.println("linked list set:" + time2(linkedListSet, opCount));
    }

    private static double time2(Set<Integer> set, long opCount) {
        long start = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i++)
            set.add(random.nextInt(Integer.MAX_VALUE));

        long end= System.nanoTime();
        return (end - start) / 1000000000.0;
    }


    private static double time(Set<String> set, String filename) {
        long start = System.nanoTime();
        final String userDir = System.getProperty("user.dir");
        List<String> words1 = FileOperation.readFromFile(userDir + "/" + filename);
        for (String word : words1)
            set.add(word);

        System.out.println("Total different words:" + set.getSize());

        long end = System.nanoTime();
        return (end - start) / 1000000000.0;
    }
}
