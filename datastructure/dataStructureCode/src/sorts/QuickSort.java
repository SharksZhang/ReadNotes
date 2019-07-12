package sorts;

public class QuickSort {
    public static int[] sort(int[] arr) {
        quickSort(arr, 0, arr.length-1);
        return  arr;
    }

    public static void quickSort(int [] arr, int low, int high) {
        if (low >= high){
            return;
        }
        int m = partition(arr, low, high);
        quickSort(arr, low, m-1);
        quickSort(arr, m+1, high);
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= pivot){
                high--;
            }
            swap(arr, low, high);

            while (low < high && arr[low] <= pivot) {
                low ++;
            }
            swap(arr, low, high);
        }
        return low;
    }

    private static void swap(int[] arr, int low, int high) {
        int temp = arr[low];
        arr[low] = arr[high];
        arr[high] = temp;
    }
}
