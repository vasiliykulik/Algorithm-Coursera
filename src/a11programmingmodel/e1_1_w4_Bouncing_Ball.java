package a11programmingmodel;

import edu.princeton.cs.algs4.StdDraw;

public class e1_1_w4_Bouncing_Ball {
    public static void main(String[] args) {
        StdDraw.setXscale(-1.0,1.0);
        StdDraw.setYscale(-1.0,1.0);
        StdDraw.enableDoubleBuffering();

        double rx = 0.480, ry = 0.860;  // position
        double vx = 0.015, vy = 0.023;  // velocity
        double radius = 0.05;

        // bounce off wall according to law of elastic collision
        while(true){
            if(Math.abs(rx+vx)>1.0-radius) vx = -vx;
            if(Math.abs(ry+vy)>1.0-radius) vy = -vy;

            //update position
            rx = rx + vx;
            ry = ry + vy;

            // clear the background
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledSquare(0,0,1.0);

            // draw ball on the screen
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledCircle(rx,ry,radius);

            //
            StdDraw.show();
            StdDraw.pause(20);
        }
    }
}
