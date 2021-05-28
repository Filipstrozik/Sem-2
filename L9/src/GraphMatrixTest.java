import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class GraphMatrixTest {

    GraphMatrix gm;

    @BeforeEach
    void setUp() {
        gm = new GraphMatrix(3,false,true);
    }

    @Test
    void verticiesTest(){
        assertEquals(3,gm.verticies());
    }


    @Test
    void addEdgeTest() {
        gm.addEdge(0,1,10);
        assertTrue(gm.hasEdge(0,1));
        assertTrue(gm.hasEdge(1,0));
        assertFalse(gm.hasEdge(0,0));

        assertFalse(gm.hasEdge(0,2));
        gm.addEdge(0,2,15);
        assertTrue(gm.hasEdge(2,0));
        assertTrue(gm.hasEdge(0,2));
    }

    @Test
    void hasEdgeThatDoesNotExist() {
        boolean trown = false;
        try {
            gm.hasEdge(4,0);
        } catch (ArrayIndexOutOfBoundsException e){
            trown = true;
        }
        assertTrue(trown);
    }

    @Test
    void getEdgeValue() {
        gm.addEdge(0,1,10);
        assertEquals(10,gm.getEdgeValue(0,1));
        assertEquals(10,gm.getEdgeValue(1,0));
    }

    @Test
    void getEdgeValueWhenChanged() {
        gm.addEdge(0,1,10);
        assertEquals(10,gm.getEdgeValue(0,1));
        assertEquals(10,gm.getEdgeValue(1,0));
        gm.addEdge(0,1,16);
        assertEquals(16,gm.getEdgeValue(0,1));
        assertEquals(16,gm.getEdgeValue(1,0));
    }
}