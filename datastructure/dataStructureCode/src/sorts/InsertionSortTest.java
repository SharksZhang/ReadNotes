package sorts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsertionSortTest {

    @Test
    void should_sorted_after_sort() {
        assertArrayEquals(new int[]{5, 7, 8, 10, 19, 22}, InsertionSort.sort(new int[]{ 10, 5, 7, 19 ,8, 22}));
    }

}