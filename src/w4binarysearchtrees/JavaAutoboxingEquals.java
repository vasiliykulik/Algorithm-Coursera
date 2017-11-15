package w4binarysearchtrees;

/**
 * Created by Vasiliy Kylik on 15.11.2017.
 */
public class JavaAutoboxingEquals {
    public static void main(String[] args) {

        double a=0.0, b=-0.0;
        System.out.println(a==b);
        Double x = a;
        Double y = b;
        System.out.println(x.equals(y));

        System.out.println("---------------------");
        a=Double.NaN;
        b=Double.NaN;
        System.out.println(a==b);
        x = a;
        y = b;
        System.out.println(x.equals(y));
    }
}
