package sort;

/**
 * Created by Vasiliy Kylik on 24.10.2017.
 * Dutch national flag. Given an array of n buckets, each containing a red, white, or blue pebble, sort them by color. The allowed operations are:
 * <p>
 * swap(i,j): swap the pebble in bucket i with the pebble in bucket j.
 * color(i): determine the color of the pebble in bucket i.
 * The performance requirements are as follows:
 * <p>
 * At most n calls to color().
 * At most n calls to swap().
 * Constant extra space.
 */
enum Pebble {
  Red, White, Blue
}

public class DutchNationalFlag {
  private Pebble[] pebbles;

  private Pebble color(int i) {
    return pebbles[i];
  }

  private int compare(Pebble p) {
    Pebble white = Pebble.White;
    return p.ordinal() - white.ordinal();
  }

  private void swap(int i, int j) {
    Pebble tmp = pebbles[i];
    pebbles[i] = pebbles[j];
    pebbles[j] = tmp;
  }

  public void sort() {
    assert pebbles.length > 0;
    int i = 0;
    int j = 0;
    int n = pebbles.length - 1;

    while (j <= n) {
      Pebble color = color(j);
      int cmp = compare(color);
      if (cmp < 0) {
        swap(j++, i++);
      } else if (cmp > 0) {
        swap(j, n--);
      } else {
        j++;
      }

    }
  }
}