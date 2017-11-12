package priorityqueues.questions;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

/**
 * Created by Vasiliy Kylik on 09.11.2017.
 */
public class TaxicabNumber {
    class Taxicab implements Comparable<Taxicab>{
        int n1;
        int n2;
        int cube;

        Taxicab(int n1, int n2) {
            this.n1 = n1;
            this.n2 = n2;
            this.cube = n1 * n1 * n1 + n2 * n2 * n2;
        }

        @Override
        public int compareTo(Taxicab that) {
            if (that.cube > this.cube) return -1;
            if (that.cube < this.cube) return 1;
            return 0;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Taxicab) {
                if (((Taxicab)o).compareTo(this) == 0)
                    return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "number: " + cube + " (" + n1 + ", " + n2 + ")";
        }
    }
    public void findTaxinumber(int N) {
        MinPQ<Taxicab> candidates = new MinPQ<Taxicab>();

        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                Taxicab t = new Taxicab(i, j);
                if (candidates.size() < N) {
                    candidates.insert(t);
                }
                else {
                    Queue<Taxicab> temp = new Queue<Taxicab>();
                    Taxicab min = candidates.delMin();
                    while (candidates.min().equals(min)) {
                        temp.enqueue(candidates.delMin());
                    }
                    if (!t.equals(min)) {
                        candidates.insert(t);
                    }
                    else {
                        temp.enqueue(t);
                    }
                    if (!temp.isEmpty()) {
                        for (Taxicab taxi: temp) {
                            System.out.println(taxi);
                        }
                        System.out.println(min);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TaxicabNumber p = new TaxicabNumber();
        p.findTaxinumber(12);
    }
}
