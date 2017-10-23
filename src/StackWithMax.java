import java.util.Stack;

/**
 * Created by Vasiliy Kylik on 15.10.2017.
 */
public class StackWithMax<T extends Comparable> {
    private Stack<T> data = new Stack<T>();

    public StackWithMax() {
    }
    private Stack<T> max = new Stack<T>();

    @SuppressWarnings("unchecked")
    public void push(T value) {
        data.push(value);
        if (max.isEmpty()) {
            max.push(value);
        } else {
            if (value.compareTo(max.peek()) > 0) {
                max.push(value);
            } else {
                max.push(max.peek());
            }
        }
    }

    public T pop() {
        max.pop();
        return data.pop();
    }

    public T max() {
        System.out.println("Max : " + max.peek());
        return max.peek();
    }
}
