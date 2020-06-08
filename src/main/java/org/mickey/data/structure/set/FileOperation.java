package org.mickey.data.structure.set;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author mickey
 * @date 2020/6/8 15:16
 */
public class FileOperation {

    private FileOperation(){

    }

    public static List<String> readFromFile(String filename) {
        List<String> words = new ArrayList<>();
        if (filename == null) {
            return words;
        }

        try (Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(new File(filename))), "UTF-8")) {
            scanner.useLocale(Locale.ENGLISH);

            if (scanner.hasNextLine()) {
                String content = scanner.useDelimiter("\\A").next();
                int start = firstCharacterIndex(content, 0);
                for (int i = start + 1; i <= content.length();) {
                    if (i==content.length() || !Character.isLetter(content.charAt(i))) {
                        String word = content.substring(start, i).toLowerCase();
                        words.add(word);
                        start = firstCharacterIndex(content, i);
                        i = start + 1;
                    } else
                        i++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return words;
    }

    // 寻找字符串s中，从start的位置开始的第一个字母字符的位置
    private static int firstCharacterIndex(String s, int start){

        for( int i = start ; i < s.length() ; i ++ )
            if( Character.isLetter(s.charAt(i)) )
                return i;
        return s.length();
    }
}
