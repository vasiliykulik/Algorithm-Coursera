import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * Created by Vasiliy Kylik on 27.10.2017.
 */
public class FastCollinearPoints {
  private final LineSegment[] segments;

  public FastCollinearPoints(Point[] points) {
    List<LineSegment> lines = new ArrayList<>();
    Arrays.sort(points);
    for (int i = 0; i < points.length - 1; i++) {
      if (points[i].compareTo(points[i + 1]) == 0) {
        throw new IllegalArgumentException();
      }
    }
    for (int i = 0; i < points.length; i++) {
      Point p = points[i];
      Point[] others = new Point[points.length - 1];
      System.arraycopy(points, 0, others, 0, i);
      System.arraycopy(points, i + 1, others, i, others.length - i);
      Arrays.sort(others, p.slopeOrder());
      int start = 0, end = 1;
      while (end < others.length) {
        boolean equalSlope = p.slopeTo(others[end]) == p.slopeTo(others[end - 1]);
        if (equalSlope) {
          end++;
        }
        if (!equalSlope || end == others.length) {
          if (end - start >= 3) {
            Point[] segment = new Point[end - start + 1];
            segment[segment.length - 1] = p;
            for (int k = 0; k < segment.length - 1; k++) {
              segment[k] = others[start + k];
            }
            Arrays.sort(segment);
            LineSegment line = new LineSegment(segment[0], segment[segment.length - 1]);
            boolean added = false;
            for (LineSegment lineSegment : lines) {
              if (lineSegment.toString().equals(line.toString())) {
                added = true;
                break;
              }
            }
            if (!added) {
              lines.add(line);
            }
          }
          start = end;
          end = start + 1;
        }
      }
    }
    segments = lines.toArray(new LineSegment[lines.size()]);
  }

  public int numberOfSegments() {
    return segments.length;
  }

  public LineSegment[] segments() {
    return segments.clone();
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
