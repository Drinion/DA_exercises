import java.awt.*;
import java.awt.geom.*;
import java.util.*;

/**
 * Implements a bouncing ball simulation.
 */
public class BouncingBallsSimulationAlternative extends Component implements Runnable {

    ArrayList<ArrayList<LinkedList<Ball>>> hash_table;
    LinkedList<Ball> bucket;    // bucket for balls / one per cell in hash table
    ArrayList<Ball> balls;    // List of balls.
    Image img;                // Image to display balls.
    int w, h;                // Width an height of image.
    Graphics2D gi;            // Graphics object to draw balls.
    float r;                // Radius of balls.
    int n;                    // Number of balls.
    Thread thread;            // Thread that runs simulation loop.
    int m = 15;                 //Size Hashtable

    public int hashX(Ball ball){return (int)Math.floor(ball.x*(m-1)/w);}
    public int hashY(Ball ball){return (int)Math.floor(ball.y*(m-1)/h);}

    /**
     * Initializes the simulation.
     *
     * @param w width of simulation window.
     * @param h height of simulation window.
     * @param n number of balls.
     * @param r radius of balls.
     * @param v initial velocity of balls.
     */
    public BouncingBallsSimulationAlternative(int w, int h, int n, float r, float v)
    {
        this.r = r;
        this.n = n;
        this.w = w;
        this.h = h;

        // Initialize hash table
        hash_table = new ArrayList<ArrayList<LinkedList<Ball>>>(m);    //columns, x-achsis, 1D
        for (int i=0; i < m; i++){
            ArrayList<LinkedList<Ball>> cells = new ArrayList<LinkedList<Ball>>(m);
            hash_table.add(cells);  //rows, y-achsis, 2D
            for (int j=0; j < m; j++){
                bucket = new LinkedList<>();
                cells.add(bucket); //buckets per cell
            }
        }

        // Initialize balls by distributing them randomly.
        balls = new ArrayList<Ball>();
        for(int i=0; i<n; i++)
        {
            float vx = 2*(float)Math.random()-1;
            float vy = 2*(float)Math.random()-1;
            float tmp = (float)Math.sqrt((double)vx*vx+vy*vy);
            vx = vx/tmp*v;
            vy = vy/tmp*v;
            int color = (i < n / 100 ) ? 1 : 0;
            Ball ball = new Ball(r+(float)Math.random()*(w-2*r), r+(float)Math.random()*(h-2*r), vx, vy, r, color);
            balls.add(ball);

            getBucket(ball).add(ball); //add balls to cells/buckets in hash table

        }

    }


    public LinkedList<Ball> getBucket(Ball ball){
        return hash_table.get(hashX(ball)).get(hashY(ball));
    }


    public Dimension getPreferredSize() {
        return new Dimension(w, h);
    }

    /**
     * Paint the window that displays the simulation. This method is called
     * automatically by the Java window system as a response to the call to
     * repaint() in the run() method below.
     */
    public void paint(Graphics g)
    {
        gi.clearRect(0, 0, w, h);

        Iterator<Ball> it = balls.iterator();
        while(it.hasNext())
        {
            Ball ball = it.next();
            gi.setColor(ball.color == 1 ? Color.red : Color.black);
            gi.fill(new Ellipse2D.Float(ball.x-r, ball.y-r, 2*r, 2*r));
        }

        g.drawImage(img, 0, 0, null);
    }

    /**
     * Starts the simulation loop.
     */
    public void start()
    {
        img = createImage(w, h);
        gi = (Graphics2D)img.getGraphics();
        gi.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        thread = new Thread(this);
        thread.run();
    }

    /**
     * The simulation loop.
     */
    public void run()
    {
        // Set up timer.
        int c = 0;
        Timer timer = new Timer();
        timer.reset();

        // Loop forever (or until the user closes the main window).
        while(true)
        {
            // Run one simulation step.

            // Iterate over all balls.
            for (int i=0; i < m; i++) {
                ArrayList<LinkedList<Ball>> cells = hash_table.get(i);
                for (int j = 0; j < m; j++) {
                    LinkedList<Ball> bucket = cells.get(j);
                    ListIterator<Ball> it = bucket.listIterator();

                    while (it.hasNext()) {
                        Ball ball = it.next();

                        // Move the ball.
                        ball.move();

                        // Handle collisions with boundaries.
                        if (ball.doesCollide((float) w, 0.f, -1.f, 0.f))
                            ball.resolveCollision((float) w, 0.f, -1.f, 0.f);
                        if (ball.doesCollide(0.f, 0.f, 1.f, 0.f))
                            ball.resolveCollision(0.f, 0.f, 1.f, 0.f);
                        if (ball.doesCollide(0.f, (float) h, 0.f, -1.f))
                            ball.resolveCollision(0.f, (float) h, 0.f, -1.f);
                        if (ball.doesCollide(0.f, 0.f, 0.f, 1.f))
                            ball.resolveCollision(0.f, 0.f, 0.f, 1.f);



                        // Handle collisions with other balls.

                        ListIterator<Ball> it2 = bucket.listIterator();
                        Ball ball2 = it2.next();

                        if((hashX(ball2)+1 >= hashX(ball) && hashX(ball) <= hashX(ball2)+1) &&
                                (hashY(ball2)+1 >= hashY(ball) && hashY(ball) <= hashY(ball2)+1)) {

                            while (ball2 != ball) {

                                if (ball.doesCollide(ball2))
                                    ball.resolveCollision(ball2);
                                ball2 = it2.next();
                            }
                        }
                    }

                }
            }

            //Remove balls from current bucket and add to new
            for (int i=0; i < m; i++) {
                ArrayList<LinkedList<Ball>> cells = hash_table.get(i);
                for (int j = 0; j < m; j++) {
                    LinkedList<Ball> bucket = cells.get(j);
                    ListIterator<Ball> it = bucket.listIterator();
                    while (it.hasNext()) {
                        Ball ball = it.next();
                        if(hashX(ball) != i || hashY(ball) != j){
                            it.remove();
                            getBucket(ball).add(ball); //add balls to new bucket in hash table
                        }
                    }
                }
            }

            // Trigger update of display.
            repaint();

            // Print time per simulation step.
            c++;
            if(c==10)
            {
                System.out.printf("Timer per simulation step: %fms\n", (float)timer.timeElapsed()/(float)c);
                timer.reset();
                c = 0;
            }
        }
    }
}
