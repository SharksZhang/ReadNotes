package sorts;

public class SelectSort {
    public static int[] sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i; j < arr.length ; j++) {
                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            if (i != min){
                int temp = arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
            }
        }
        return arr;
    }
}
