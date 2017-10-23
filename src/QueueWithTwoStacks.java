import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Created by Vasiliy Kylik on 14.10.2017.
 */
public class QueueWithTwoStacks<Item> {
    private Stack<Item> stack1 = new Stack<Item>();
    private Stack<Item> stack2 = new Stack<Item>();

    public QueueWithTwoStacks() {

    }

    public int size() {
        return stack1.size() + stack2.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        stack1.push(item);
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();

        }
        if (stack2.isEmpty())
        {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        QueueWithTwoStacks<Integer> integerQueueWithTwoStacks = new QueueWithTwoStacks<>();
        System.out.println("Size: " + integerQueueWithTwoStacks.size());
        for (int i = 1; i <= 100; i++) {
            if (i % 7 == 0) {
                integerQueueWithTwoStacks.enqueue(i);
            }
        }
        System.out.println("Size: " + integerQueueWithTwoStacks.size());
        while (!integerQueueWithTwoStacks.isEmpty()) {
            System.out.println("Dequeue" + integerQueueWithTwoStacks.dequeue());
        }
        System.out.println("Size: " + integerQueueWithTwoStacks.size());
    }
}
