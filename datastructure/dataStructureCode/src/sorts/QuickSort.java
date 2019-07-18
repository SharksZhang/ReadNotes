package sorts;

public class QuickSort {
    public static int[] sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
        return  arr;
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low >= high){
            return;
        }
        int pivot = partition(arr, low , high);
        quickSort(arr, low, pivot -1);
        quickSort(arr, pivot +1, high);

    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        while (low < high){
            while (low < high && arr[high] >= pivot){
                high--;
            }
            swap(arr, low, high);
            while (low < high && arr[low] <= pivot){
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
