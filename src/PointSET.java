import edu.princeton.cs.algs4.*;

/**
 * Created by Vasiliy Kylik on 19.11.2017.
 */
/*Brute-force implementation. Write a mutable data type PointSET.java that represents a set of points in the unit square.
 Implement the following API by using a red–black BST:

public class PointSET {
    public         PointSET()                               // construct an empty set of points
    public           boolean isEmpty()                      // is the set empty?
    public               int size()                         // number of points in the set
    public              void insert(Point2D p)              // add the point to the set (if it is not already in the set)
    public           boolean contains(Point2D p)            // does the set contain point p?
    public              void draw()                         // draw all points to standard draw
    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle (or on the boundary)
    public           Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args)                  // unit testing of the methods (optional)
}*/
public class PointSET {
    private final SET<Point2D> set = new SET<Point2D>();

    /*
     * construct an empty set of points
     */
    public PointSET() {
    }

    /*
     * is the set empty?
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /*
     * number of points in the set
     */
    public int size() {
        return set.size();
    }

    /*
     * add the point p to the set (if it is not already in the set)
     */
    public void insert(Point2D p) {
        if (set.contains(p)) {
            return;
        }
        set.add(p);
    }

    /*
     * does the set contain the point p?
     */
    public boolean contains(Point2D p) {
        return set.contains(p);
    }

    /*
     * draw all of the points to standard draw
     */
    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        for (Point2D p : set) {
            p.draw();
        }
    }

    /*
     * all points in the set that are inside the rectangle
     */
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> queue = new Queue<Point2D>();
        for (Point2D p : set) {
            if (rect.contains(p)) {
                queue.enqueue(p);
            }
        }
        return queue;
    }

    /*
     * a nearest neighbor in the set to p; null if set is empty
     */
    public Point2D nearest(Point2D p) {
        double distance = Double.MAX_VALUE;
        Point2D nearest = null;
        for (Point2D other : set) {
            if (p.distanceTo(other) < distance) {
                distance = p.distanceTo(other);
                nearest = other;
            }

        }
        return nearest;
    }
}
