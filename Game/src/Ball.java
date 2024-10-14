import org.academiadecodigo.simplegraphics.graphics.Ellipse;

public class Ball {
    private static final int BALL_DIAMETER = 30;
    private static final double GRAVITY = 0.2; // bigger the quicker is falls (default value: 0.2)
    private static final double BOUNCINESS = 0.9; // 1 or more infinite bouncing ---- 0 no bouncing (default value: 0.9)
    private static final double FRICTION = 0.996;// 1 full friction stops instantly ---- 0 infinite movement(default
                                                 // value: 0.996)

    // Ball properties
    private double x;
    private double y;
    private double velocityX;
    private double velocityY;
    private Ellipse ballVisual;

    public Ball() {
        x = Game.CANVAS_WIDTH / 2;
        y = Game.CANVAS_HEIGHT / 2;
        ballVisual = new Ellipse(x, y, BALL_DIAMETER, BALL_DIAMETER);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getBallDiameter() {
        return BALL_DIAMETER;
    }

    // Optional: Set ball velocity if needed
    public void setVelocity(double vx, double vy) {
        this.velocityX = vx;
        this.velocityY = vy;
    }

    public void update() {

        movement();

        checkCollisionsWithWalls();

        show();
    }

    private void movement() {
        velocityY += GRAVITY;

        x += velocityX;
        y += velocityY;

        velocityX *= FRICTION;
    }

    private void checkCollisionsWithWalls() {
        // Check for collision with the floor (bottom)
        if (y + BALL_DIAMETER >= Game.CANVAS_HEIGHT) {
            y = Game.CANVAS_HEIGHT - BALL_DIAMETER;
            velocityY = -velocityY * BOUNCINESS;
        }

        // Check for collision with the ceiling (top)
        if (y <= 0) {
            y = 0;
            velocityY = -velocityY * BOUNCINESS;
        }

        // Check for collision with the left wall
        if (x <= 0) {
            x = 0;
            velocityX = -velocityX * BOUNCINESS;
        }

        // Check for collision with the right wall
        if (x + BALL_DIAMETER >= Game.CANVAS_WIDTH) {
            x = Game.CANVAS_WIDTH - BALL_DIAMETER;
            velocityX = -velocityX * BOUNCINESS;
        }
    }

    private void show() {
        ballVisual.delete();
        ballVisual = new Ellipse(x, y, BALL_DIAMETER, BALL_DIAMETER);
        ballVisual.fill();
    }

}
