import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {

    private MyStack<Integer> mystack;

    @BeforeEach
    void setUp() throws Exception{
            mystack = new MyStack<Integer>();
    }

    @Test
    void isEmpty1() {
        assertTrue(mystack.isEmpty());
    }

    @Test
    void isEmpty2() throws FullStackException {
        mystack.push(1);
        assertFalse(mystack.isEmpty());
    }

    @Test
    void isEmpty3() {
        assertTrue(mystack.isEmpty());
    }

    @Test
    void isFull() throws FullStackException {
        mystack.push(2);
        mystack.push(5);
        mystack.push(3);
        assertTrue(mystack.isFull());
    }

    @Test
    void pop() throws FullStackException, EmptyStackException {
        Integer element = 1888;
        mystack.push(element);
        assertEquals(element,mystack.pop());
        assertEquals(0, mystack.size());
    }

    @Test
    void push() throws FullStackException {
        assertEquals(0, mystack.size());
        Integer element = 1888;
        mystack.push(element);
        assertEquals(1, mystack.size());
    }

    @Test
    void size() {
        assertEquals(0,mystack.size());
    }

    @Test
    void top() throws EmptyStackException, FullStackException {
        Integer element = 1888;
        mystack.push(element);
        assertEquals(element,mystack.top());
        assertEquals(1, mystack.size());
    }

}