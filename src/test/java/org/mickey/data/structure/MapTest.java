package org.mickey.data.structure;

import org.junit.Test;
import org.mickey.data.structure.map.BSTMap;
import org.mickey.data.structure.map.LinkedListMap;
import org.mickey.data.structure.map.Map;
import org.mickey.data.structure.set.FileOperation;

import java.util.List;

/**
 * @author mickey
 * @date 2020/6/9 11:52
 */
public class MapTest {

    @Test
    public void testMap() {

        Map<String, Integer> linkedListMap = new LinkedListMap<>();
        System.out.println("linked list map time:" + time(linkedListMap, "pride-and-prejudice.txt"));

        Map<String, Integer> bstMap = new BSTMap<>();
        System.out.println("bst map time:" + time(bstMap, "pride-and-prejudice.txt"));

    }

    private static double time(Map<String, Integer> map, String filename) {
        List<String> words = FileOperation.readFromFile(System.getProperty("user.dir") + "/" + filename);
        long start = System.nanoTime();
        for (String word : words) {
            if (map.contains(word))
                map.set(word, map.get(word) + 1);
            else
                map.add(word, 1);
        }
        System.out.println("Total different words: " + map.getSize());
        System.out.println("Frequency of pride: " + map.get("pride"));
        System.out.println("Frequency of prejudice: " + map.get("prejudice"));
        long end = System.nanoTime();
        return (end - start) / 1000000000.0;
    }
}
