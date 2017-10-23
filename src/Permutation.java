import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Vasiliy Kylik on 15.10.2017.
 */
public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            randomizedQueue.enqueue(StdIn.readString());
        }
        int num = Integer.parseInt(args[0]);
        while (num > 0) {
            StdOut.println(randomizedQueue);
            num--;
        }
    }
}
