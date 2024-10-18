import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.pictures.Picture;

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

    public void update(Player p1, Player p2) {

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

    private boolean isTouching(Picture player) {

        double ballCenterX = x + BALL_DIAMETER / 2;
        double ballCenterY = y + BALL_DIAMETER / 2;

        // Find the nearest point on the rectangle to the ball
        double nearestX = Math.max(player.getX(), Math.min(ballCenterX, player.getX() + player.getWidth()));
        double nearestY = Math.max(player.getY(), Math.min(ballCenterY, player.getY() + player.getHeight()));

        double distanceX = ballCenterX - nearestX;
        double distanceY = ballCenterY - nearestY;
        double distanceSquared = distanceX * distanceX + distanceY * distanceY;

        // If the distance is less than or equal to the ball's radius, they are touching
        return distanceSquared <= (BALL_DIAMETER / 2) * (BALL_DIAMETER / 2);
    }

    private int getCollisionsForKick(Picture player) {
        // If they are not touching, return "No collision"
        if (!isTouching(player)) {
            return 0;
        }

        double ballCenterX = x + BALL_DIAMETER / 2;
        double ballCenterY = y + BALL_DIAMETER / 2;

        // Find the nearest point on the rectangle to the ball
        double nearestX = Math.max(player.getX(), Math.min(ballCenterX, player.getX() + player.getWidth()));
        double nearestY = Math.max(player.getY(), Math.min(ballCenterY, player.getY() + player.getHeight()));

        double vectorX = nearestX - ballCenterX;
        double vectorY = nearestY - ballCenterY;

        int[] up = { 0, 1 }; // Normal for the top side
        int[] down = { 0, -1 }; // Normal for the bottom side
        int[] left = { -1, 0 }; // Normal for the left side
        int[] right = { 1, 0 }; // Normal for the right side

        // Calculate dot products with each side's normal vector
        double dotUp = vectorX * up[0] + vectorY * up[1];
        double dotDown = vectorX * down[0] + vectorY * down[1];
        double dotLeft = vectorX * left[0] + vectorY * left[1];
        double dotRight = vectorX * right[0] + vectorY * right[1];

        // Determine which side is closest by comparing dot products
        double maxDot = Math.max(Math.max(dotUp, dotDown), Math.max(dotLeft, dotRight));

        // Return which side of the rectangle is being touched
        if (maxDot == dotUp) {
            return 1; // top
        } else if (maxDot == dotDown) {
            return 2; // down
        } else if (maxDot == dotLeft) {
            return 3;// left
        } else {
            return 4;// right
        }
    }

    public void getKickCollisionsForLeftPlayer(Picture leftPlayer) {
        switch (getCollisionsForKick(leftPlayer)) {
            case 1:// top
                setVelocity(10, -20);
                break;
            case 2:// down
                setVelocity(10, 10);
                break;
            case 3:// left
                setVelocity(-20, -5);
                break;
            case 4:// right
                setVelocity(20, -5);
                break;
        }
    }

    public void getKickCollisionsForRightPlayer(Picture rightPlayer) {
        switch (getCollisionsForKick(rightPlayer)) {
            case 1:// top
                setVelocity(-10, -20);
                break;
            case 2:// down
                setVelocity(-10, -10);
                break;
            case 3:// left
                setVelocity(-20, -5);
                break;
            case 4:// right
                setVelocity(-20, -5);
                break;
        }
    }


    private void show() {
        ballVisual.delete();
        ballVisual = new Ellipse(x, y, BALL_DIAMETER, BALL_DIAMETER);
        ballVisual.fill();
    }

}
