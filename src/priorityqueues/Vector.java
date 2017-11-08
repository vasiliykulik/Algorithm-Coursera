package priorityqueues;

/**
 * Created by Vasiliy Kylik on 07.11.2017.
 */
/*                Immutability: implementing in Java
                        Data type. Set of values and operations on those values.
                        Immutable data type. Can't change the data type value once created.*/
public final class Vector {
    private final int N; // can't override instance methods
    private final double[] data; // all instance variables private and final
    public Vector(double[] data) {
        this.N = data.length;
        this.data = new double[N];
        for (int i = 0; i < N; i++) // defensive copy of mutableinstance variables
            this.data[i] = data[i];
    }
// ... instance methods don't change instance variables
}
