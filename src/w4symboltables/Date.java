package w4symboltables;

/**
 * Created by Vasiliy Kylik on 15.11.2017.
 */
/*
typically unsafe to use equals() with inheritance
        (would violate symmetry)*//*
public final class Date implements Comparable<Date>
{
    private final int month;
    private final int day;
    private final int year;

    must be Object.
        Why? Experts still debate.
    public boolean equals(Object y)
    {
        optimize for true object equality
        if (y == this) return true;
        check for null
        if (y == null) return false;
        objects must be in the same class
        (religion: getClass() vs. instanceof)
        if (y.getClass() != this.getClass())
            return false;
        cast is guaranteed to succeed
        Date that = (Date) y;
        check that all significant
        fields are the same
        if (this.day != that.day ) return false;
        if (this.month != that.month) return false;
        if (this.year != that.year ) return false;
        return true;
    }
}*/
