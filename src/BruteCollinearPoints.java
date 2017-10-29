import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Vasiliy Kylik on 27.10.2017.
 */
/*
 * Write a program BruteCollinearPoints.java
 * that examines 4 points at a time and checks whether they all
 * lie on the same line segment, returning all such line segments.
 *
 * To check whether the 4 points p, q, r, and s are collinear,
 * check whether the three slopes between p and q, between p and r,
 * and between p and s are all equal.
 */

public class BruteCollinearPoints {
  private ArrayList<LineSegment> segments = new ArrayList<>();

  // finds all line segments containing 4 points
  public BruteCollinearPoints(Point[] points) {

    if (points == null)
      throw new NullPointerException();

    Point[] copy = points.clone();
    Arrays.sort(copy);

    if (hasDuplicate(copy)) {
      throw new IllegalArgumentException("You have duplicate points");
    }

    for (int first = 0; first < copy.length - 3; first++) {
      for (int second = first + 1; second < copy.length - 2; second++) {
        double slopeFS = copy[first].slopeTo(copy[second]);
        for (int third = second + 1; third < copy.length - 1; third++) {
          double slopeFT = copy[first].slopeTo(copy[third]);
          if (slopeFS == slopeFT) {
            for (int forth = third + 1; forth < copy.length; forth++) {
              double slopeFF = copy[first].slopeTo(copy[forth]);
              if (slopeFS == slopeFF) {
                segments.add(new LineSegment(copy[first], copy[forth]));
              }
            }
          }
        }

      }
    }
  }

  private boolean hasDuplicate(Point[] points) {
    for (int i = 0; i < points.length - 1; i++) {
      if (points[i].compareTo(points[i + 1]) == 0) {
        return true;
      }
    }
    return false;

  }

  // the number of line segments
  public int numberOfSegments() {
    return segments.size();
  }

  // the line segments
  public LineSegment[] segments() {
    return segments.toArray(new LineSegment[segments.size()]);
  }
}