package sorts;

public class HeapSort {
    public static int[] sort(int[] arr) {
        for (int i = arr.length/2 -1; i >= 0 ; i--) {
            adjustHeap(arr, i, arr.length);
        }

        for (int i = arr.length - 1; i > 0 ; i--) {
            swap(arr, i, 0);
            adjustHeap(arr, 0, i);
        }

        return arr;
    }

    private static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    private static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int j =  2*i+1; j < length ; j = 2 * j+1) {
            if(j +1 < length && arr[j+1] > arr[j]){
                j++;
            }
            if(temp< arr[j]){
                arr[i] = arr[j];
                i = j;
            }else {
                break;
            }
        }
        arr[i] = temp;
    }
}
