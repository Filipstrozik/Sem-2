import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VTStackTest {

    private VTStack<Integer> vtstack;

    @BeforeEach
    void setUp() throws Exception{
        vtstack = new VTStack<Integer>();
    }

    @Test
    void isEmpty1() {
        assertTrue(vtstack.isEmpty());
    }

    @Test
    void isEmpty2() throws FullStackException {
        vtstack.push(1);
        assertFalse(vtstack.isEmpty());
    }

    @Test
    void isEmpty3() {
        assertTrue(vtstack.isEmpty());
    }

    @Test
    void isFull() throws FullStackException {
        vtstack.push(2);
        vtstack.push(5);
        vtstack.push(3);
        assertTrue(vtstack.isFull());
    }

    @Test
    void pop() throws FullStackException, EmptyStackException {
        Integer element = 1888;
        vtstack.push(element);
        assertEquals(element,vtstack.pop());
        assertEquals(0, vtstack.size());
    }

    @Test
    void push() throws FullStackException {
        assertEquals(0, vtstack.size());
        Integer element = 1888;
        vtstack.push(element);
        assertEquals(1, vtstack.size());
    }

    @Test
    void size() {
        assertEquals(0,vtstack.size());
    }

    @Test
    void top() throws EmptyStackException, FullStackException {
        Integer element = 1888;
        vtstack.push(element);
        assertEquals(element,vtstack.top());
        assertEquals(1, vtstack.size());
    }



    @Test
    void peek() throws FullStackException, EmptyStackException {
        vtstack.push(5);
        assertEquals(5,vtstack.peek());
    }

    @Test
    void toTop() throws FullStackException, EmptyStackException {
        vtstack.push(5);
        vtstack.push(7);
        vtstack.push(6);
        vtstack.toTop();
        assertEquals(2,vtstack._cursor);
    }

    @Test
    void down() throws FullStackException, BottomOfStackException {
        vtstack.push(5);
        vtstack.push(7);
        vtstack.push(6);
        vtstack.toTop();
        vtstack.down();
        vtstack.down();
        assertEquals(0,vtstack._cursor);
    }

    @Test
    void down2() throws FullStackException, BottomOfStackException {
        vtstack.push(5);
        vtstack.push(7);
        vtstack.push(6);
        vtstack.toTop();
        vtstack.down();
        assertEquals(1,vtstack._cursor);
    }
}