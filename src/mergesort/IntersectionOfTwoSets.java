package mergesort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import edu.princeton.cs.algs4.Stopwatch;


/**
 * Created by Vasiliy Kylik on 23.10.2017.
 */
/*Intersection of two sets. Given two arrays a[] and b[], each containing n distinct 2D points in the plane, design a subquadratic algorithm to count the number of points that are contained both in array a[] and array b[].

Note: these interview questions are ungraded and purely for your own enrichment. To get a hint, submit a solution.*/
public class IntersectionOfTwoSets {

  public class Point implements Comparable{
    int x;
    int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int compareTo(Object o) {
      if(this.x > ((Point)o).x) return 1;
      if(this.x < ((Point)o).x) return -1;
      if(this.y > ((Point)o).y) return 1;
      if(this.y < ((Point)o).y) return -1;
      return 0;
    }
  }

  public Point[] intersectionOf(Point[] a, Point[] b) {
    List<Point> result = new ArrayList<>();
    Arrays.sort(a);
    Arrays.sort(b);

    for(int i = 0, j = 0; i < a.length && j < b.length; ) {
      if(a[i].compareTo(b[j]) == 0) {
        result.add(a[i]);
        i++;
        j++;
      } else if (a[i].compareTo(b[j]) < 0) {
        i ++;
      } else {
        j ++;
      }
    }
    return (Point[])result.toArray();
  }
}