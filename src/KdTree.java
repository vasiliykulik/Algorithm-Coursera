import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

/**
 * Created by Vasiliy Kylik on 19.11.2017.
 */
/*
2d-tree implementation. Write a mutable data type KdTree.java that uses a 2d-tree to implement the same API (but replace
PointSET with KdTree). A 2d-tree is a generalization of a BST to two-dimensional keys. The idea is to build a BST with
points in the nodes, using the x- and y-coordinates of the points as keys in strictly alternating sequence.

        Search and insert. The algorithms for search and insert are similar to those for BSTs, but at the root we use
        the x-coordinate (if the point to be inserted has a smaller x-coordinate than the point at the root, go left;
        otherwise go right); then at the next level, we use the y-coordinate (if the point to be inserted has a smaller
        y-coordinate than the point in the node, go left; otherwise go right); then at the next level the x-coordinate,
        and so forth.
        Insert (0.7, 0.2)

        insert (0.7, 0.2)
        Insert (0.5, 0.4)

        insert (0.5, 0.4)
        Insert (0.2, 0.3)

        insert (0.2, 0.3)
        Insert (0.4, 0.7)

        insert (0.4, 0.7)
        Insert (0.9, 0.6)

        insert (0.9, 0.6)
        Insert (0.7, 0.2)
        Insert (0.5, 0.4)
        Insert (0.2, 0.3)
        Insert (0.4, 0.7)
        Insert (0.9, 0.6)
        Draw. A 2d-tree divides the unit square in a simple way: all the points to the left of the root go in the left subtree;
         all those to the right go in the right subtree; and so forth, recursively. Your draw() method should draw all of
          the points to standard draw in black and the subdivisions in red (for vertical splits) and blue (for horizontal
           splits). This method need not be efficient—it is primarily for debugging.
        The prime advantage of a 2d-tree over a BST is that it supports efficient implementation of range search and
        nearest-neighbor search. Each node corresponds to an axis-aligned rectangle in the unit square, which encloses
        all of the points in its subtree. The root corresponds to the unit square; the left and right children of the
        root corresponds to the two rectangles split by the x-coordinate of the point at the root; and so forth.

        Range search. To find all points contained in a given query rectangle, start at the root and recursively
        search for points in both subtrees using the following pruning rule: if the query rectangle does not intersect
        the rectangle corresponding to a node, there is no need to explore that node (or its subtrees). A subtree is
        searched only if it might contain a point contained in the query rectangle.
        Nearest-neighbor search. To find a closest point to a given query point, start at the root and recursively
        search in both subtrees using the following pruning rule: if the closest point discovered so far is closer
        than the distance between the query point and the rectangle corresponding to a node, there is no need to
        explore that node (or its subtrees). That is, search a node only only if it might contain a point that is
        closer than the best one found so far. The effectiveness of the pruning rule depends on quickly finding a
        nearby point. To do this, organize the recursive method so that when there are two possible subtrees to go
        down, you always choose the subtree that is on the same side of the splitting line as the query point as the
        first subtree to explore—the closest point found while exploring the first subtree may enable pruning of the
        second subtree.
*/
public class KdTree {
    private int size = 0;

    private enum Orientation {
        LeftRight, AboveBelow;

        public Orientation next() {
            if (this.equals(Orientation.AboveBelow))
                return Orientation.LeftRight;

            return Orientation.AboveBelow;
        }
    }

    private static class Node {
        /*
         * the point
         */
        private Point2D p;
        /*
         * the axis-aligned rectangle corresponding to this/ node
         */
        private RectHV rect;
        /*
         * the left/bottom subtree
         */
        private Node lb;
        /*
         * the right/top subtree
         */
        private Node rt;

        public Node(Point2D p) {
            this.p = p;
        }
    }

    private Node root;

    /*
     * construct an empty set of points
     */
    public KdTree() {
    }

    /*
     * is the set empty?
     */
    public boolean isEmpty() {
        return root == null;
    }

    /*
     * number of points in the set
     */
    public int size() {
        return size;
    }

    /*
     * add the point p to the set (if it is not already in the set)
     */
    public void insert(Point2D p) {
        if (isEmpty()) {
            root = new Node(p);
            root.rect = new RectHV(0, 0, 1, 1);
            size++;
            return;
        }
        root = put(root, p, Orientation.LeftRight);
    }

    private Node put(Node x, Point2D p, Orientation orientation) {
        if (x == null) {
            size++;
            return new Node(p);
        }
        if (x.p.equals(p)) {
            return x;
        }
        int cmp = compare(p, x.p, orientation);
        Orientation nextOrientation = orientation.next();
        if (cmp < 0) {
            x.lb = put(x.lb, p, nextOrientation);
            if (x.lb.rect == null) {
                if (orientation == Orientation.LeftRight) {
                    x.lb.rect = new RectHV(x.rect.xmin(), x.rect.ymin(),
                            x.p.x(), x.rect.ymax());
                } else {
                    x.lb.rect = new RectHV(x.rect.xmin(), x.rect.ymin(),
                            x.rect.xmax(), x.p.y());
                }
            }
        } else {
            x.rt = put(x.rt, p, nextOrientation);
            if (x.rt.rect == null) {
                if (orientation == Orientation.LeftRight) {
                    x.rt.rect = new RectHV(x.p.x(), x.rect.ymin(),
                            x.rect.xmax(), x.rect.ymax());
                } else {
                    x.rt.rect = new RectHV(x.rect.xmin(), x.p.y(),
                            x.rect.xmax(), x.rect.ymax());
                }
            }
        }
        return x;
    }

    private int compare(Point2D p, Point2D q, Orientation orientation) {
        if (orientation == Orientation.LeftRight) {
            return Double.compare(p.x(), q.x());
        } else {
            return Double.compare(p.y(), q.y());
        }
    }

    /*
     * does the set contain the point p?
     */
    public boolean contains(Point2D p) {
        return contains(root, p, Orientation.LeftRight);
    }

    private boolean contains(Node x, Point2D p, Orientation orientation) {
        if (x == null) {
            return false;
        }
        if (x.p.equals(p)) {
            return true;
        }
        int cmp = compare(p, x.p, orientation);
        Orientation nextOrientation = orientation.next();
        if (cmp < 0) {
            return contains(x.lb, p, nextOrientation);
        } else {
            return contains(x.rt, p, nextOrientation);
        }
    }

    /*
     * draw all of the points to standard draw
     */
    public void draw() {
        draw(root, Orientation.LeftRight);
    }

    private void draw(Node x, Orientation orientation) {
        if (x == null) {
            return;
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        x.p.draw();
        if (orientation == Orientation.LeftRight) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(0.001);
            StdDraw.line(x.p.x(), x.rect.ymin(), x.p.x(), x.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius(0.001);
            StdDraw.line(x.rect.xmin(), x.p.y(), x.rect.xmax(), x.p.y());
        }
        Orientation next = orientation.next();
        draw(x.lb, next);
        draw(x.rt, next);
    }

    /*
     * all points in the set that are inside the rectangle
     */
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> queue = new Queue<Point2D>();

        if (!isEmpty()) {
            findPoints(queue, rect, root);
        }
        return queue;
    }

    private void findPoints(Queue<Point2D> queue, RectHV rect, Node x) {
        if (!rect.intersects(x.rect)) {
            return;
        }
        if (rect.contains(x.p)) {
            queue.enqueue(x.p);
        }
        if (x.lb != null) {
            findPoints(queue, rect, x.lb);
        }
        if (x.rt != null) {
            findPoints(queue, rect, x.rt);
        }
    }

    /*
     * a nearest neighbor in the set to p; null if set is empty
     */
    public Point2D nearest(Point2D p) {
        if (isEmpty()) {
            return null;
        }
        return findNearest(root, p, root.p, Double.MAX_VALUE,
                Orientation.LeftRight);
    }

    private Point2D findNearest(Node x, Point2D p, Point2D nearest,
                                double nearestDistance, Orientation orientation) {
        if (x == null) {
            return nearest;
        }
        Point2D closest = nearest;
        double closestDistance = nearestDistance;
        double distance = x.p.distanceSquaredTo(p);
        if (distance < nearestDistance) {
            closest = x.p;
            closestDistance = distance;
        }
        Node first, second;
        if (orientation == Orientation.LeftRight) {
            if (p.x() < x.p.x()) {
                first = x.lb;
                second = x.rt;
            } else {
                first = x.rt;
                second = x.lb;
            }
        } else {
            if (p.y() < x.p.y()) {
                first = x.lb;
                second = x.rt;
            } else {
                first = x.rt;
                second = x.lb;
            }
        }
        Orientation nextOrientation = orientation.next();
        if (first != null && first.rect.distanceSquaredTo(p) < closestDistance) {
            closest = findNearest(first, p, closest, closestDistance,
                    nextOrientation);
            closestDistance = closest.distanceSquaredTo(p);
        }
        if (second != null
                && second.rect.distanceSquaredTo(p) < closestDistance) {
            closest = findNearest(second, p, closest, closestDistance,
                    nextOrientation);
        }

        return closest;
    }
}
