/**
 * Created by Vasiliy Kylik on 17.10.2017.
 */
public class BitonicArray2logN {
    public void descending_binary_search(int[] array, int left, int right, int value)
    {
        // cout << "descending_binary_search: " << left << " " << right << endl;
        // empty interval
        if (left == right) {
            return;
        }
        // look at the middle of the interval
        int mid = (right+left)/2;
        if (array[mid] == value) {
            System.out.println("value found:"+mid);
            return;
        }
        // interval is not splittable
        if (left+1 == right) {
            return;
        }
        if (value < array[mid]) {
            descending_binary_search(array, mid+1, right, value);
        }
        else {
            descending_binary_search(array, left, mid, value);
        }
    }
    public void ascending_binary_search(int[] array, int left, int right, int value)
    {
        // cout << "ascending_binary_search: " << left << " " << right << endl;
        // empty interval
        if (left == right) {
            return;
        }
        // look at the middle of the interval
        int mid = (right+left)/2;
        if (array[mid] == value) {
            System.out.println("value found"+mid);
            return;
        }
        // interval is not splittable
        if (left+1 == right) {
            return;
        }
        if (value > array[mid]) {
            ascending_binary_search(array, mid+1, right, value);
        }
        else {
            ascending_binary_search(array, left, mid, value);
        }
    }
    public void bitonic_search(int[] array, int left, int right, int value)
    {
        // empty interval
        if (left == right) {
            return;
        }
        int mid = (right+left)/2;
        if (array[mid] == value) {
            System.out.println("value found"+mid);
            return;
        }
        // not splittable interval
        if (left+1 == right) {
            return;
        }
        if(array[mid] > array[mid-1]) {
            if (value > array[mid]) {
                bitonic_search(array, mid+1, right, value);
            }
            else {
                ascending_binary_search(array, left, mid, value);
                descending_binary_search(array, mid+1, right, value);
            }
        }
        else {
            if (value > array[mid]) {
                bitonic_search(array, left, mid, value);
            }
            else {
                ascending_binary_search(array, left, mid, value);
                descending_binary_search(array, mid+1, right, value);
            }
        }
    }
}
