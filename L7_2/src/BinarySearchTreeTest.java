import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    BinarySearchTree<Integer> bst;
    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
    }

    @Test
    void getRootTest() {
        assertNull(bst.getRoot());
    }

    @Test
    void insertTest() {
        bst.insert(10);
        assertEquals(10, bst.getRootValue());
    }

    @Test
    void insertTestWithDelete() {
        bst.insert(10);
        bst.delete(10);
        bst.insert(5);
        assertEquals(5, bst.getRootValue());
    }

    @Test
    void deleteTestSmallest() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(1);
        bst.insert(7);
        bst.insert(40);
        bst.insert(50);
        bst.delete(1);
        assertNull(bst.find(1));
    }

    @Test
    void deleteTestLargest() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(1);
        bst.insert(7);
        bst.insert(40);
        bst.insert(50);
        bst.delete(50);
        assertNull(bst.find(50));
    }

    @Test
    void deleteTestRoot() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(1);
        bst.insert(7);
        bst.insert(40);
        bst.insert(50);
        bst.delete(10);
        assertNull(bst.find(10));
    }

    @Test
    void deleteTestRootWithOnlyRoot() {
        bst.insert(10);
        bst.delete(10);
        assertNull(bst.find(10));
    }

    @Test
    void lowerTest() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(1);
        bst.insert(7);
        bst.insert(40);
        bst.insert(50);
        //wszystkie mozliwe przypadki w jednym

        assertNull(bst.lower(0));
        assertEquals(1,bst.lower(1));
        assertEquals(1,bst.lower(4));
        assertEquals(5,bst.lower(5));
        assertEquals(5,bst.lower(6));
        assertEquals(7,bst.lower(7));
        assertEquals(7,bst.lower(9));
        assertEquals(10,bst.lower(10));
        assertEquals(10,bst.lower(11));
        assertEquals(10,bst.lower(39));
        assertEquals(40,bst.lower(40));
        assertEquals(40,bst.lower(49));
        assertEquals(50,bst.lower(50));
        assertEquals(50,bst.lower(60));
    }

    @Test
    void upperTest() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(1);
        bst.insert(7);
        bst.insert(40);
        bst.insert(50);

        //wszystkie mozliwe przypadki w jednym
        assertEquals(1,bst.upper(0));
        assertEquals(1,bst.upper(1));
        assertEquals(5,bst.upper(2));
        assertEquals(5,bst.upper(5));
        assertEquals(7,bst.upper(6));
        assertEquals(7,bst.upper(7));
        assertEquals(10,bst.upper(8));
        assertEquals(10,bst.upper(10));
        assertEquals(40,bst.upper(11));
        assertEquals(40,bst.upper(40));
        assertEquals(50,bst.upper(41));
        assertEquals(50,bst.upper(50));
        assertNull(bst.upper(60));
    }

    @Test
    void inOrderTest(){
        /*   45
           /     \
          10      90
         /  \    /
        7   12  50  */
        bst.insert(45);
        bst.insert(10);
        bst.insert(7);
        bst.insert(12);
        bst.insert(90);
        bst.insert(50);
        System.out.println("7 10 12 45 50 90");
        bst.inOrder();
    }

    @Test
    void preOrderTest(){
        /*   45
           /     \
          10      90
         /  \    /
        7   12  50  */
        bst.insert(45);
        bst.insert(10);
        bst.insert(7);
        bst.insert(12);
        bst.insert(90);
        bst.insert(50);
        System.out.println("45 10 7 12 90 50");
        bst.preOrder();

    }

    @Test
    void postOrderTest(){
        bst.insert(45);
        bst.insert(10);
        bst.insert(7);
        bst.insert(12);
        bst.insert(90);
        bst.insert(50);
        System.out.println("7 12 10 50 90 45");
        bst.postOrder();
    }

    @Test
    void getMaxWhenNullAsRoot(){
        boolean thrown=false;
        try{
            bst.getMax();
        }catch (NoSuchElementException e){
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void getMinWhenNullAsRoot(){
        boolean thrown=false;
        try{
            bst.getMin();
        }catch (NoSuchElementException e){
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void getMax(){
        bst.insert(1);
        assertEquals(1,bst.getMax());
        bst.insert(3);
        assertEquals(3,bst.getMax());
        bst.insert(2);
        assertEquals(3,bst.getMax());
    }

    @Test
    void getMin(){
        bst.insert(2);
        assertEquals(2,bst.getMin());
        bst.insert(3);
        assertEquals(2,bst.getMin());
        bst.insert(1);
        assertEquals(1,bst.getMin());
    }
}
