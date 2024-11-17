package cs143;

import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class EndlessListTest {

    EndlessList<Integer> list;

    @Before
    public void setUp() {
        list = new EndlessList<>();
    }

    /**
     * Test of addPrev method, of class EndlessList.
     */
    @Test
    public void testAddPrev() {
        for (int i = 0; i < 10; i++) {
            list.addPrev(i * 10);
        }
        assertTrue(list.getValue() == 90);
        assertTrue(list.getNext() == 80);
        assertTrue(list.getPrev() == 90);
        assertTrue(list.getPrev() == 0);
        assertTrue(list.getPrev() == 10);
        assertTrue(list.getPrev() == 20);
    }

    /**
     * Test of addNext method, of class EndlessList.
     */
    @Test
    public void testAddNext() {
        for (int i = 0; i < 10; i++) {
            list.addNext(i * 10);
        }
        assertTrue(list.getValue() == 90);
        assertTrue(list.getPrev() == 80);
        assertTrue(list.getNext() == 90);
        assertTrue(list.getNext() == 0);
        assertTrue(list.getNext() == 10);
        assertTrue(list.getNext() == 20);
    }

    /**
     * Test of remove method, of class EndlessList.
     */
    @Test
    public void testRemove() {
        list.addNext(10);
        assertTrue(list.remove() == 10);
        for (int i = 0; i < 10; i++) {
            list.addNext(i * 10);
        }
        list.remove();
        assertTrue(list.getValue() == 0);
        assertTrue(list.getPrev() == 80);
        for (int i = 0; i < 7; i++) {
            list.remove();
        }
        assertSame(list.getValue(), 60);
        list.remove();
        assertSame(list.getValue(), 70);
        list.getPrev();
        assertSame(list.getValue(), 70);
        list.remove();
    }

    /**
     * Test of getValue method, of class EndlessList.
     */
    @Test
    public void testGetValue() {
        list.addNext(50);
        assertTrue(list.getValue() == 50);
        list.addPrev(30);
        assertTrue(list.getValue() == 30);
        list.addNext(60);
        assertTrue(list.getValue() == 60);
        list.getNext();
        assertTrue(list.getValue() == 50);
        list.getNext();
        assertTrue(list.getValue() == 30);
    }

    /**
     * Test of setValue method, of class EndlessList.
     */
    @Test
    public void testSetValue() {
        assertFalse(list.setValue(5));
        for (int i = 0; i < 10; i++) {
            list.addNext(i * 10);
        }
        list.setValue(99);
        assertTrue(list.getValue() == 99);
        list.getNext();
        assertTrue(list.getValue() == 0);
        list.getNext();
        assertTrue(list.getValue() == 10);
        list.setValue(11);
        assertTrue(list.getValue() == 11);
        list.getPrev();
        assertTrue(list.getValue() == 0);
        list.getPrev();
        assertTrue(list.getValue() == 99);
    }

    /**
     * Test of getPrev method, of class EndlessList.
     */
    @Test
    public void testGetPrev() {
        assertEquals(list.getPrev(), null);
        for (int i = 0; i < 10; i++) {
            list.addNext(i * 10);
        }
        for (int i = 8; i >= 0; i--) {
            list.getPrev();
            assertTrue(list.getValue() == (i * 10));
        }
    }

    /**
     * Test of getNext method, of class EndlessList.
     */
    @Test
    public void testGetNext() {
        assertEquals(list.getNext(), null);
        for (int i = 0; i < 10; i++) {
            list.addNext(i * 10);
        }
        for (int i = 0; i < 10; i++) {
            list.getNext();
            assertTrue(list.getValue() == (i * 10));
        }
    }

    /**
     * Test of moveToNext method, of class EndlessList.
     */
    @Test
    public void testMoveToNext() {
        assertFalse(list.moveToNext(10));
        for (int i = 0; i < 10; i++) {
            list.addNext(i * 10);
        }
        list.addPrev(10);
        assertTrue(list.moveToNext(10) == true);
        assertTrue(list.moveToNext(11) == false);
        assertTrue(list.getValue() == 10);
        list.getNext();
        assertTrue(list.getValue() == 20);
        list.moveToNext(10);
        assertTrue(list.getValue() == 10);
        list.getNext();
        assertTrue(list.getValue() == 90);
    }

    /**
     * Test of moveToPrev method, of class EndlessList.
     */
    @Test
    public void testMoveToPrev() {
        assertFalse(list.moveToPrev(10));
        for (int i = 0; i < 10; i++) {
            list.addNext(i * 10);
        }
        list.addPrev(10);
        assertTrue(list.moveToPrev(10) == true);
        assertTrue(list.moveToPrev(11) == false);
        assertTrue(list.getValue() == 10);
        list.getNext();
        assertTrue(list.getValue() == 20);
        list.moveToPrev(10);
        list.moveToPrev(10);
        assertTrue(list.getValue() == 10);
        list.getNext();
        assertTrue(list.getValue() == 90);
    }

    /**
     * Test of iterator method, of class EndlessList.
     */
    @Test
    public void testIterator() {
        Iterator<Integer> it = list.iterator();
        assertFalse(it.hasNext());
        for (int i = 0; i < 10; i++) {
            list.addNext(i * 10);
        }
        EndlessList<Integer> tempList = new EndlessList<>();
        for (int i = 0; i < 10; i++) {
            tempList.addNext(i * 10);
        }
        list.getNext();
        it = list.iterator();
        while (it.hasNext()) {
            tempList.getNext();
            assertTrue(tempList.getValue().equals(it.next()));
        }
        for (Integer e : list) {
            tempList.getNext();
            assertTrue(e.equals(tempList.getValue()));
        }
        it = list.iterator();
        while (it.hasNext()) {
           it.remove();
        }
    }

}
