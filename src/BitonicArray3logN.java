/**
 * Created by Vasiliy Kylik on 17.10.2017.
 */
/*An efficient solution is based on Binary Search. The idea is to find the bitonic point k which is the index of the maximum element of given sequence. If the element to be searched is greater than maximum element return -1, else search the element in both halves.

        Find the bitonic point in the given array, i.e the maximum element in the given bitonic array. This can be done in log(n) time by modifying the binary search algorithm.
        If the element to be searched is equal to the element at bitonic point then print the index of bitonic point.
        If the element to be searched is greater than element at bitonic point then element does not exist in the array.
        If the element to be searched is less than element at bitonic point then search for element in both half of the array using binary search.*/

public class BitonicArray3logN {
    public int searchInBitonicArray(int[] array, int key) {
        int max = findMax(array, 0, array.length);
        int k = binarySearch(array, 0, max, key);
        if (k != -1) return k;
        else return binarySearch(array, max, array.length - 1, key);
    }
    private int binarySearch(int[] array, int low, int high, int key) {
        while (low <= high) {
            int mid = (low + high) / 2;
            if (array[mid] < key) low = mid + 1;
            else if (array[mid] > key) high = mid - 1;
            else return mid;
        }
        return -1;
    }
    private int findMax(int[] array, int low, int high) {
        if (array.length <= 2)
            return -1;
        int middle = (low + high) / 2;
        if (low == middle || middle + 1 == high)
            return -1;
      /* If middle element is the largest element in the array */
        if (array[middle] > array[middle - 1] && array[middle] > array[middle + 1])
            return middle;
        int n1 = findMax(array, low, middle);
        int n2 = findMax(array, middle, high);
      /* If element is found in the increasing part of the array */
        if (n1 != -1)
            return n1;
      /* If element is found in the decreasing part of the array */
        if (n2 != -1)
            return n2;
        return -1;
    }
}
