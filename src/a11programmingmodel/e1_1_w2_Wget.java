package a11programmingmodel;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
/*
argument https://introcs.cs.princeton.edu/java/data/codes.csv
 */

public class e1_1_w2_Wget {
    public static void main(String[] args) {
        String url = args[0];
        In in = new In (url);
        String data = in.readAll();

        String filename = url.substring(url.lastIndexOf('/')+1);
        Out out = new Out(filename);
        out.println(data);
        out.close();
    }
}
