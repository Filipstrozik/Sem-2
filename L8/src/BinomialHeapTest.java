import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BinomialHeapTest {

    BinomialHeap<Integer> bh;

    @BeforeEach
    void setUp() {
        bh = new BinomialHeap<>();
    }

    @Test
    void sizeWhenEmptyTest() {
        assertEquals(0, bh.size());
    }

    @Test
    void sizeWhenContainsOnlyOneTest() {
        bh.insert(10);
        assertEquals(1, bh.size());
    }

    @Test
    void insertTwoElementsTest() {
        bh.insert(10);
        bh.insert(5);
        assertEquals(2, bh.size());
    }

    @Test
    void insertAndDeleteTest() {
        bh.insert(10);
        bh.insert(5);
        assertEquals(2, bh.size());
        bh.delete(5);
        assertEquals(1, bh.size());
        bh.delete(10);
        assertEquals(0, bh.size());
    }

    @Test
    void deleteTest() {
        bh.insert(10);
        bh.showHeap();
        boolean flag = false;
        try {
            bh.delete(5);
        } catch (NoSuchElementException e){
            flag = true;
        }
        assertTrue(flag);

        bh.showHeap();

        bh.delete(10);

        assertEquals(0, bh.size());
        bh.showHeap();
    }

    @Test
    void minimumTestAllPosibilities(){
        bh.insert(10);
        assertEquals(10, bh.minimumKey());
        bh.insert(5);
        assertEquals(5, bh.minimumKey());
        bh.insert(2);
        assertEquals(2, bh.minimumKey());
        bh.insert(3);
        assertEquals(2, bh.minimumKey());
        bh.delete(3);
        assertEquals(2, bh.minimumKey());
        bh.delete(2);
        bh.delete(5);
        bh.delete(10);
        assertNull(bh.minimumKey());
    }


    @Test
    void extractMinTest() {
        bh.insert(10);
        assertEquals(1, bh.size());
        assertEquals(10, bh.extractMin());
        assertEquals(0, bh.size());
        bh.insert(5);
        assertEquals(1, bh.size());
        assertEquals(5, bh.extractMin());
        assertEquals(0, bh.size());
        bh.insert(2);
        assertEquals(1, bh.size());
        assertEquals(2, bh.extractMin());
        assertEquals(0, bh.size());
        bh.insert(3);
        assertEquals(1, bh.size());
        assertEquals(3, bh.extractMin());
        assertEquals(0, bh.size());
        bh.insert(3);
        bh.insert(2);
        assertEquals(2, bh.extractMin());
    }

    @Test
    void unionTest(){
        BinomialHeap<Integer> bh2 = new BinomialHeap<>();
        bh.union(bh2);
        assertEquals(0, bh.size());

        bh2.insert(1);
        assertEquals(1, bh2.size());
        assertEquals(0, bh.size());


        bh.insert(20);
        bh.insert(40);
        bh.insert(50);

        assertEquals(3, bh.size());

        bh2.insert(15);
        bh2.insert(16);
        bh2.insert(13);
        assertEquals(4, bh2.size());


        bh.showHeap();


        bh2.showHeap();

        bh.union(bh2);
        assertEquals(7, bh.size());
        assertEquals(4, bh2.size());

        bh.showHeap();


        bh2.showHeap();

    }


    @Test
    void decreaseKeyTest(){
        assertEquals(0, bh.size());
        bh.insert(20);
        bh.insert(10);
        assertEquals(10,bh.minimumKey());
        bh.showHeap();
        bh.decreaseKey(20,5);
        assertEquals(5,bh.minimumKey());
        boolean flag = false;
        try {
            bh.decreaseKey(5,8);
        }
        catch (IllegalStateException e){
            flag = true;
        }
        bh.showHeap();
        assertTrue(flag);
    }
}