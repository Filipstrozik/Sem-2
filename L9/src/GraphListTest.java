import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphListTest {


    GraphList gl;

    @BeforeEach
    void setUp() {
        gl = new GraphList(3);

    }

    @Test
    void addEdgeTest() {
        gl.addEdge(0,1,15);
        assertTrue(gl.hasEdge(0,1));
        assertTrue(gl.hasEdge(1,0));
        assertFalse(gl.hasEdge(0,0));

        assertFalse(gl.hasEdge(0,2));
        gl.addEdge(0,2,15);
        assertTrue(gl.hasEdge(2,0));
        assertTrue(gl.hasEdge(0,2));
    }

    @Test
    void hasEdgeThatDoesNotExist() {
        boolean trown = false;
        try {
            gl.hasEdge(4,0);
        } catch (ArrayIndexOutOfBoundsException e){
            trown = true;
        }
        assertTrue(trown);
        assertFalse(gl.hasEdge(0,4));

    }

    @Test
    void getEdgeValue() {
        gl.addEdge(0,1,10);
        assertEquals(10, gl.getEdgeValue(0,1));
        assertEquals(10, gl.getEdgeValue(1,0));
    }

    @Test
    void getEdgeValueWhenChanged() {
        gl.addEdge(0,1,10);
        assertEquals(10, gl.getEdgeValue(0,1));
        assertEquals(10, gl.getEdgeValue(1,0));
        gl.addEdge(0,1,16);
        assertEquals(16, gl.getEdgeValue(0,1));
        assertEquals(16, gl.getEdgeValue(1,0));
    }
}