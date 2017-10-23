/**
 * Created by Vasiliy Kylik on 09.10.2017.
 */
public class WeightedQuickUnionPathCompression {
    private int id[];
    int[] sz;

    // create array and set each object to itself
    public WeightedQuickUnionPathCompression(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) id[i] = i;
        sz = new int[N];// that there were no compilation errors
    }

    // chasing pointers to find root of element
    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];// only one extra line of code
            i = id[i];
        }
        return i;
    }

    public boolean connected (int p, int q){
        return root(p)==root(q);
    }
    public void union(int p, int q){
        int i = root(p);
        int j = root(q);
        if (i==j) return;

        if (sz[i]<sz[j]){id[i]=j;sz[j]+=sz[i];}
        else {id[j]=i;sz[i]+=sz[j];}
    }
}
