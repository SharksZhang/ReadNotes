package sorts;

public class BubbleSort {
    public static int[] sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean swapFlag = false;
            for (int j = arr.length - 1; j > i ; j--) {
                if(arr[j] < arr[j - 1]){
                    swap(arr, j, j - 1);
                    swapFlag = true;
                }
            }
            if (!swapFlag){
                return arr;
            }
        }
        return arr;
    }

    private static void swap(int arr[], int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}
