package sorts;

public class MergeSort {
    public static int[] sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
        return arr;
    }

    public static void mergeSort(int[] arr, int low , int high) {
        if(low >= high){
            return;
        }

        int mid = (low + high)/2;

        mergeSort(arr, low, mid);
        mergeSort(arr, mid +1, high);

        merge(arr, low, mid, high);
    }

    private static void merge(int[] arr, int low, int mid, int high) {
        int[] tmpArr = new int[high-low+1];
        int i = low;
        int j = mid +1;
        int k =0;
        while (i<= mid && j <= high){
            tmpArr[k++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
        }

        while (i<= mid){
            tmpArr[k++] = arr[i++];
        }
        while (j <= high){
            tmpArr[k++] = arr[j++];
        }

        for (int i1 : tmpArr) {
            arr[low++] = i1;
        }
    }


}
