import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


class KMPTest {

    @Test
    void TestShortPatternInOnePageText() throws FileNotFoundException {
        System.out.println("TestShortPatternInOnePageText");
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File("D:/MAIN/CODING/AISD/L11/src/Swietoszek.txt");
        Scanner in = new Scanner(file);
        while (in.hasNextLine()){
            stringBuilder.append(" ").append(in.nextLine());
        }
        in.close();
        String s = stringBuilder.toString();
        long start = System.nanoTime();
        ArrayList<Integer> answer = KMP.compute("mi",s);
        long finish = System.nanoTime();
        ArrayList<Integer> excpected = new ArrayList<>(Arrays.asList(45, 71, 141, 148, 844, 1289, 1558, 1833, 2118, 2371));
        Assert.assertEquals(excpected,answer);
        System.out.println(answer);

        System.out.println((finish - start)/1000000.0);
    }

    @Test
    void TestLongPatternInOnePageText() throws FileNotFoundException {
        System.out.println("TestLongPatternInOnePageText");
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File("D:/MAIN/CODING/AISD/L11/src/Swietoszek.txt");
        Scanner in = new Scanner(file);
        while (in.hasNextLine()){
            stringBuilder.append(" ").append(in.nextLine());
        }
        in.close();
        String s = stringBuilder.toString();
        long start = System.nanoTime();

        ArrayList<Integer> answer = KMP.compute("impertynentka",s);
        long finish = System.nanoTime();
        ArrayList<Integer> excpected = new ArrayList<>(Collections.singletonList(816));
        Assert.assertEquals(excpected,answer);
        System.out.println(answer);


        System.out.println((finish - start)/1000000.0);
    }

    @Test
    void TestLongPatternInABook() throws FileNotFoundException {
        System.out.println("TestLongPatternInABook");
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File("D:/MAIN/CODING/AISD/L11/src/Zemsta.txt");
        Scanner in = new Scanner(file);
        while (in.hasNextLine()){
            stringBuilder.append(" ").append(in.nextLine());
        }
        in.close();
        String s = stringBuilder.toString();
        long start = System.nanoTime();

        ArrayList<Integer> answer = KMP.compute("Niech się dzieje wola nieba",s);
        long finish = System.nanoTime();

        ArrayList<Integer> excpected = new ArrayList<>(Arrays.asList(51042, 52038, 53133, 55565, 64570, 86714));
        Assert.assertEquals(excpected,answer);
        System.out.println(answer);
        System.out.println((finish - start)/1000000.0);
    }

    @Test
    void TestShortPatternInABook() throws FileNotFoundException {
        System.out.println("TestShortPatternInABook");
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File("D:/MAIN/CODING/AISD/L11/src/Zemsta.txt");
        Scanner in = new Scanner(file);
        while (in.hasNextLine()){
            stringBuilder.append(" ").append(in.nextLine());
        }
        in.close();
        String s = stringBuilder.toString();
        long start = System.nanoTime();

        ArrayList<Integer> answer = KMP.compute("domu",s);
        long finish = System.nanoTime();

        ArrayList<Integer> excpected = new ArrayList<>(Arrays.asList(2086, 18754, 19090, 28025, 30652, 34788, 36588, 53391, 59602, 62718, 64658, 70463, 76987, 79890, 83765));
        Assert.assertEquals(excpected,answer);
        System.out.println(answer);
        System.out.println((finish - start)/1000000.0);
    }
    @Test
    void TestVeryPatternLikeDNA(){
        System.out.println("TestVeryPatternLikeDNA");
        String text = "ABABCDABABCDBBABABCD";
        String pattern = "ABABCD";
        long start = System.nanoTime();
        ArrayList<Integer> answer = KMP.compute(pattern,text);
        long finish = System.nanoTime();
        ArrayList<Integer> excpected = new ArrayList<>(Arrays.asList(0,6,14));
        Assert.assertEquals(excpected,answer);

        for (int i = 0; i < text.length(); i++) {
            System.out.printf("%4c",text.charAt(i));
        }
        System.out.println();
        for (int i = 0; i < text.length(); i++) {
            System.out.printf("%4d",i);
        }
        System.out.println();
        System.out.println(KMP.compute(pattern,text));
        System.out.println((finish - start)/1000000.0);

    }

}