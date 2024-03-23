package deque;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

public class MaxArrayDequeTest {

    private MaxArrayDeque<Integer> deque;

    @Before
    public void setUp() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        deque = new MaxArrayDeque<>(comparator);
    }

    @Test
    public void testMaxWithEmptyDeque() {
        assertNull(deque.max());
    }

    @Test
    public void testMaxWithIntegerComparator() {
        deque.addLast(5);
        deque.addLast(8);
        deque.addLast(3);
        deque.addLast(10);
        assertEquals(Integer.valueOf(10), deque.max());
    }

    @Test
    public void testMaxWithCustomComparator() {
        Comparator<Integer> reverseComparator = Comparator.reverseOrder();
        MaxArrayDeque<Integer> customDeque = new MaxArrayDeque<>(reverseComparator);
        customDeque.addLast(5);
        customDeque.addLast(8);
        customDeque.addLast(3);
        customDeque.addLast(10);
        assertEquals(Integer.valueOf(3), customDeque.max());
    }
}
