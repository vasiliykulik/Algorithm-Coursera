/**
 * Created by Vasiliy Kylik on 14.10.2017.
 */
public class ResizingArrayStackOfStrings {
    private String s[]; //stub
    private int N; // stub

    public ResizingArrayStackOfStrings() {
        s = new String[1];
    }

    public void push(String item) {
        if (N == s.length) resize(2 * s.length);
        s[N++] = item;
    }

    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < N; i++)
            copy[i] = s[i];
        s = copy;
    }

    public String pop() {
        String item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length / 4) resize(s.length / 2);
        return item;
    }
}
