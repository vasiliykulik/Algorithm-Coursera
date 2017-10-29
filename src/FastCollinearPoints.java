import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;



/**
 * Created by Vasiliy Kylik on 27.10.2017.
 */
public class FastCollinearPoints {
  private ArrayList<LineSegment> segments = new ArrayList<>();

  public FastCollinearPoints(Point[] points) {
    // check corner cases
    if (points == null)
      throw new NullPointerException();

    Point[] copy = points.clone();
    Arrays.sort(copy);

    if (hasDuplicate(copy)) {
      throw new IllegalArgumentException("You have duplicate points");
    }
    // and now show must go on )))

    for (int i = 0; i < copy.length - 3; i++) {
      Arrays.sort(copy);

      // Sort the points according to the slopes they makes with p.
      // Check if any 3 (or more) adjacent points in the sorted order
      // have equal slopes with respect to p. If so, these points,
      // together with p, are collinear.

      Arrays.sort(copy, copy[i].slopeOrder());

      for (int p = 0, first = 1, last = 2; last < copy.length; last++) {
        // find last collinear to p point
        while (last < copy.length
                && Double.compare(copy[p].slopeTo(copy[first]), copy[p].slopeTo(copy[last])) == 0) {
          last++;
        }
        // if found at least 3 elements, make segment if it's unique
        if (last - first >= 3 && copy[p].compareTo(copy[first]) < 0) {
          segments.add(new LineSegment(copy[p], copy[last - 1]));
        }
        // Try to find next
        first = last;
      }
    }
    // finds all line segments containing 4 or more points
  }

  // the number of line segments
  public int numberOfSegments() {
    return segments.size();
  }

  // the line segments
  public LineSegment[] segments() {
    return segments.toArray(new LineSegment[segments.size()]);
  }

  // test the whole array fo duplicate points
  private boolean hasDuplicate(Point[] points) {
    for (int i = 0; i < points.length - 1; i++) {
      if (points[i].compareTo(points[i + 1]) == 0) {
        return true;
      }
    }
    return false;
  }
  public static void main(String[] args) {

    // read the n points from a file
    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
    }

    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
      p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }
}
