package priorityqueues.questions;


import edu.princeton.cs.algs4.MaxPQ;
import priorityqueues.MinPQ;

/**
 * Created by Vasiliy Kylik on 09.11.2017.
 */
/*Dynamic median.
 Design a data type that supports insert in logarithmic time,
 find-the-median in constant time,
  and remove-the-median in logarithmic time.*/
public class MediaHeap {
        private MaxPQ<Integer> left; //implementation from algs4
        private MinPQ<Integer> right;
        private int L;
        private int R;

        MediaHeap() {
            left = new MaxPQ<Integer>();
            right = new MinPQ<Integer>();
        }

        public double findMedian() {
            int L = left.size();
            int R = right.size();
            if (L == R)
                return ((double)left.max() + (double)right.min()) / 2;
            else if (L > R)
                return left.max();
            else
                return right.min();
        }

        public void insert(int key) {
            double median = findMedian();
            int L = left.size();
            int R = right.size();
            if (key <= median) {
                left.insert(key);
                if (L - R > 1)
                    right.insert(left.delMax());
            }
            else {
                right.insert(key);
                if (R - L > 1)
                    left.insert(right.delMin());
            }
        }

        public void removeMedian() {
            int L = left.size();
            int R = right.size();
            if (L > R) {
                left.delMax();
            }
            else {
                right.delMin();
            }
        }

    }
