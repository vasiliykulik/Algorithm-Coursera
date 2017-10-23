/**
 * Created by Vasiliy Kylik on 12.10.2017.
 */
public class LinkedStackOfStrings {
    private Node first = null;

    private class Node {
        String item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(String item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    public String first() {
        String item = first.item;
        first = first.next;
        return item;
    }
}
