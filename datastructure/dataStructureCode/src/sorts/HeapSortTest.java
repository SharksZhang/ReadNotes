package sorts;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

class HeapSortTest {
    @Test
    void should_sorted_after_sort() {
        assertArrayEquals(new int[]{5, 7, 8, 8, 8, 10, 19, 22}, HeapSort.sort(new int[]{ 8, 10, 5, 7, 19 ,8, 22, 8}));
    }

    @Test
    void test_priorityQueue() {

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(5);
        queue.add(2);
        queue.add(8);
        System.out.println(queue.poll());
    }

}