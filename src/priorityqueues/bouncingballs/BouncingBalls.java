package priorityqueues.bouncingballs;

import edu.princeton.cs.algs4.StdDraw;

/**
 * Created by Vasiliy Kylik on 07.11.2017.
 */
public class BouncingBalls
{
    public static void main(String[] args)
    {
        int N = Integer.parseInt(args[0]);
        Ball[] balls = new Ball[N];
        for (int i = 0; i < N; i++)
            balls[i] = new Ball();
        while(true)
        {
            StdDraw.clear();
            for (int i = 0; i < N; i++) // main simulation loop
            {
                balls[i].move(0.5);
                balls[i].draw();
            }
            StdDraw.show(50);
        }
    }
}
