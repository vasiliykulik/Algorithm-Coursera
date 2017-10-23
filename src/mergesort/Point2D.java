package mergesort;

/**
 * Created by Vasiliy Kylik on 19.10.2017.
 */
// key words sort, mergesort
// Convex Hull, namely Graham Scan approach, thru matrix
public class Point2D {

    // new Comparator for each point (not static)
    private final Comparator<Point2D> POLAR_ORDER = new PolarOrder();
    private final double x;
    private final double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static int ccw(Point2D a, Point2D b, Point2D c) {
        double area2 = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x); // a.y danger of floating point roundoff error
        if (area2 < 0) return -1;       //clockwise
        else if (area2 > 0) return +1;  // counter-clockwise
        else return 0;                  // collinear
    }

    private class PolarOrder implements Comparator<Point2D> {
        public int compare(Point2D q1, Point2D q2) {
            double dy1 = q1.y - y;
            double dy2 = q2.y - y;

            if (dy1 == 0 && dy2 == 0) {}//p,q1,q2 horizontal
            else if(dy1>=0&&dy2<0) return -1;// q1 above p,q2 below p
            else if (dy2>=0&&dy1<0) return +1;//q1 below p, q2 above p
            else return -ccw(Point2D.this,q1,q2);
        }
    }

/*    public static void main(String[] args) {
        Stack<Point2D> hull = new Stack<>();

        Arrays.sort(p, Point2D.Y_ORDER); // p[0] is now point with lowest y-coordinate
        Arrays.sort(p, p[0].BY_POLAR_ORDER); // sort by poolar angle with respect to p[0]
        hull.push(p[0]);
        hull.push(p[1]); // definitely on hull

        for(int i =2; i<N; i++){
            Point2D top = hull.pop();
            while(Point2D.ccw(hull.peek(),top, p[i]<=0)) // <= discard points that would create clockwise turn
                top = hull.pop();
            hull.push(top);
            hull.push(p[i]); // aa p[i] to putative hull
        }
    }*/
}
