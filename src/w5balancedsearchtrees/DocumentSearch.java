package w5balancedsearchtrees;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;

/**
 * Created by Vasiliy Kylik on 18.11.2017.
 */
public class DocumentSearch {
    public static void main(String[] args) {
        In in = new In("");
        String[] words = in.readAllStrings();

        ST<String, Queue<Integer>> windices = new ST<String, Queue<Integer>>();

        for (int i = 0; i < words.length; i++) {
            if (!windices.contains(words[i])) {
                Queue<Integer> tmp = new Queue<Integer>();
                tmp.enqueue(i);
                windices.put(words[i], tmp);
            }
            else {
                Queue<Integer> tmp = windices.get(words[i]);
                tmp.enqueue(i);
                windices.put(words[i], tmp);
            }
        }

        int bestlo = -1;
        int besthi = words.length;
        String[] query = StdIn.readAllStrings();
        Queue<Integer>[] queues = (Queue<Integer>[]) new Queue[query.length];

        for (int i = 0; i < query.length; i++) {
            queues[i] = windices.get(query[i]);
        }

        Queue<Integer> starts = windices.get(query[0]);

        for (Integer start: starts) {
            boolean end = true;
            int lo = start;
            int hi = lo;

            for (int i = 1; i < queues.length; i++) {
                while (!queues[i].isEmpty() && queues[i].peek() <= hi) queues[i].dequeue();
                if (queues[i].isEmpty()) {
                    end = false;
                    break;
                }
                else {
                    hi = queues[i].peek();
                }
            }
            if (end && hi - lo < besthi - bestlo) {
                besthi = hi;
                bestlo = lo;
            }
        }

        if (bestlo >= 0) {
            int interval = besthi - bestlo;
            System.out.println("Shortest interval found: " + interval);
        }
        else {
            System.out.println("Not found");
        }
    }
}
