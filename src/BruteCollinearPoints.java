import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Vasiliy Kylik on 27.10.2017.
 */
public class BruteCollinearPoints {
  private LineSegment[] segments;

  public BruteCollinearPoints(Point[] points) {
    checkDuplicateEntries(points);
    ArrayList<LineSegment> foundSegments = new ArrayList<>();
    Point[] pointsCopy = Arrays.copyOf(points, points.length);
    Arrays.sort(pointsCopy);

    for (int p = 0; p < pointsCopy.length - 3; p++) {
      for (int q = p + 1; q < pointsCopy.length - 2; q++) {
        for (int r = q + 1; r < pointsCopy.length - 1; r++) {
          for (int s = r + 1; s < pointsCopy.length; s++) {
            if (pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[r]) &&
                    pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[s])) {
              foundSegments.add(new LineSegment(pointsCopy[p], pointsCopy[s]));
            }
          }
        }
      }
    }
  }   // finds all line segments containing 4 points

  private void checkDuplicateEntries(Point[] points) {
    for (int i = 0; i < points.length; i++) {
      for (int j = i + 1; j < points.length; j++) {
        if (points[i].compareTo(points[j]) == 0) {
          throw new IllegalArgumentException("Duplicate entries in given points.");
        }
      }
    }
  }

  public int numberOfSegments() {
    return segments.length;

  }// the number of line segments

  public LineSegment[] segments() {
    return Arrays.copyOf(segments, numberOfSegments());
  }        // the line segments


}
