package sorts;

public class MergeSort {
    public static int[] sort(int[] arr) {
        mergeSortRecursive(arr, 0, arr.length - 1);
        return arr;
    }

    private static void mergeSortRecursive(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (end + start) / 2;
        mergeSortRecursive(arr, start, mid);
        mergeSortRecursive(arr, mid +1, end);
        merge(arr, start, mid, end);
    }

    private static void merge(int[] arr, int start, int mid, int end) {
        int[]temp = new int[end - start + 1];
        int i = start;
        int j = mid +1;
        int k = 0;
        while (i <= mid && j <= end) {
            temp[k++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid ){
            temp[k ++] = arr[i++];
        }

        while (j <= end ){
            temp[k++] = arr[j++];
        }

        for (i = start,k = 0; i <= end ; i++, k++) {
            arr[i] = temp[k];
        }
    }

}
