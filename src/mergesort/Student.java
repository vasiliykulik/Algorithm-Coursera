package mergesort;

/**
 * Created by Vasiliy Kylik on 23.10.2017.
 */
public class Student {
    public static final Comparator<Student> BY_NAME = new ByName();
    public static final Comparator<Student> BY_Section = new BySection();
    private final String name;
    private final int section;

    // one Comparator for the class
    private static class ByName implements Comparator<Student> {
        public int compare(Student v, Student w) {
            return v.name.compareTo(w.name);
        }
    }

    private static class BySection implements Comparator<Student> {
        public int compare(Student v, Student w) {
            //this techique works here since no danger of oveerflow
            return v.section - w.section;
        }
    }
}
