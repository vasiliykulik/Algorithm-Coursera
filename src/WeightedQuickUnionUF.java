/**
 * Created by Vasiliy Kylik on 09.10.2017.
 */
// Social Network Connectivity
public class WeightedQuickUnionUF {
    private int id[]; // parents of node
    private int[] sz; //size of node
    private int count;

    // create array and set each object to itself
    public WeightedQuickUnionUF(int N) {

        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }// that there were no compilation errors
    }
    // chasing pointers to find root of element
    private int root(int i) {
        while (i != id[i]) i = id[i];
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;

        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }
}
