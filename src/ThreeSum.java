import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Vasiliy Kylik on 17.10.2017.
 */
public class ThreeSum {
    public static void main(String[] args) {

        // initialize array
        ArrayList<Integer> data = new ArrayList<Integer>();
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextInt()) {
            data.add(scan.nextInt());
        }

        // sort array
        Collections.sort(data);

        // compute all 3-sum combinations
        for (int i = 0; i < data.size() - 2; ++i) {
            int start = i + 1;
            int end = data.size() - 1;

            while (start < end) {
                int sum = data.get(i) + data.get(start) + data.get(end);
                if (sum == 0) {
                    System.out.println(i + ":" + data.get(i) + ", " + start + ":" + data.get(start) + ", " + end + ":" + data.get(end));
                }
                if (sum >= 0) {
                    --end;
                } else {
                    ++start;
                }
            }
        }
    }
}
