import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Ball {
    public static final int BALL_DIAMETER = 40;
    public static final double GRAVITY = 0.2; // bigger the quicker is falls (default value: 0.2)
    public static final double BOUNCINESS = 0.9; // 1 or more infinite bouncing ---- 0 no bouncing (default value: 0.9)
    public static final double FRICTION = 0.996;// 0 full friction stops instantly ---- 1 infinite movement(default
                                                // value: 0.996)

    // Ball properties
    private double x;
    private double y;
    private double velocityX;
    private double velocityY;
    private Ellipse ballVisual;
    private Score score;

    public Ball(Score score) {
        x = Game.CANVAS_WIDTH / 2 + 20;
        y = Game.CANVAS_HEIGHT / 2;
        ballVisual = new Ellipse(x, y, BALL_DIAMETER, BALL_DIAMETER);
        this.score = score;
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

        checkCollisionsWithWalls(p1, p2);

        show();

        getKickCollisionsForLeftPlayer(p1.getPlayerImage());
        getKickCollisionsForRightPlayer(p2.getPlayerImage());
    }

    private void movement() {
        velocityY += GRAVITY;

        x += velocityX;
        y += velocityY;

        velocityX *= FRICTION;
    }

    private void checkCollisionsWithWalls(Player p1, Player p2) {
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

            if (checkGoal(p1, p2, -5)) {
                if (score.player2Scored()) {
                    setVelocity(0, 0);
                }
            }
        }

        // Check for collision with the right wall
        if (x + BALL_DIAMETER >= Game.CANVAS_WIDTH + 70) {
            x = (Game.CANVAS_WIDTH + 70) - BALL_DIAMETER;
            velocityX = -velocityX * BOUNCINESS;

            if (checkGoal(p1, p2, 5)) {
                if (score.player1Scored()) {
                    setVelocity(0, 0);
                }
            }
        }
    }

    private boolean checkGoal(Player p1, Player p2, int xPos) {
        if (y >= Game.CANVAS_HEIGHT / 2 + 100) {
            x = Game.CANVAS_WIDTH / 2;
            y = BALL_DIAMETER;
            setVelocity(xPos, 0);
            p1.resetPlayer();
            p2.resetPlayer();
            return true;
        }
        return false;
    }

    private double[] ballCenter() {
        double ballCenterX = x + BALL_DIAMETER / 2;
        double ballCenterY = y + BALL_DIAMETER / 2;

        return new double[] { ballCenterX, ballCenterY };
    }

    private double[] nearestPoint(Picture player, double[] ballCenter) {
        // Find the nearest point of the ball limits
        double nearestX = Math.max(player.getX(), Math.min(ballCenter[0], player.getX() + player.getWidth()));
        double nearestY = Math.max(player.getY(), Math.min(ballCenter[1], player.getY() + player.getHeight()));

        return new double[] { nearestX, nearestY };
    }

    private double[] calculateDistance(double[] ballCenter, double[] nearestPoint) {
        double distanceX = ballCenter[0] - nearestPoint[0];
        double distanceY = ballCenter[1] - nearestPoint[1];

        return new double[] { distanceX, distanceY };
    }

    private boolean isTouching(Picture player) {

        double[] ballCenter = ballCenter();
        double[] nearestPoint = nearestPoint(player, ballCenter);
        double[] distance = calculateDistance(ballCenter, nearestPoint);
        double distanceSquared = distance[0] * distance[0] + distance[1] * distance[1];

        // If the distance is less than or equal to the ball's radius, they are touching
        return distanceSquared <= (BALL_DIAMETER / 2) * (BALL_DIAMETER / 2);
    }

    private int getCollisionsForKick(Picture player) {
        // If they are not touching, return "No collision"
        if (!isTouching(player)) {
            return 0;
        }

        double[] ballCenter = ballCenter();
        double[] nearestPoint = nearestPoint(player, ballCenter);
        double[] distance = calculateDistance(ballCenter, nearestPoint);

        int[] up = { 0, -1 }; // Normal for the top side
        int[] down = { 0, 1 }; // Normal for the bottom side
        int[] left = { -1, 0 }; // Normal for the left side
        int[] right = { 1, 0 }; // Normal for the right side

        // Calculate dot products with each side's normal vector
        double dotUp = distance[0] * up[0] + distance[1] * up[1];
        double dotDown = distance[0] * down[0] + distance[1] * down[1];
        double dotLeft = distance[0] * left[0] + distance[1] * left[1];
        double dotRight = distance[0] * right[0] + distance[1] * right[1];

        // Determine which side is closest by comparing dot products
        double maxDot = Math.max(Math.max(dotUp, dotDown), Math.max(dotLeft, dotRight));

        score.resetAnnouncer();

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

    private void getKickCollisionsForLeftPlayer(Picture leftPlayer) {
        switch (getCollisionsForKick(leftPlayer)) {
            case 1:// top
                setVelocity(15, -20);
                break;
            case 2:// down
                setVelocity(10, -10);
                break;
            case 3:// left
                setVelocity(25, -5);
                break;
            case 4:// right
                setVelocity(25, -5);
                break;
        }
    }

    private void getKickCollisionsForRightPlayer(Picture RightPlayer) {
        switch (getCollisionsForKick(RightPlayer)) {
            case 1:// top
                setVelocity(-15, -20);
                break;
            case 2:// down
                setVelocity(-10, -10);
                break;
            case 3:// left
                setVelocity(-25, -5);
                break;
            case 4:// right
                setVelocity(-25, -5);
                break;
        }
    }

    private void show() {
        ballVisual.delete();
        ballVisual = new Ellipse(x, y, BALL_DIAMETER, BALL_DIAMETER);
        ballVisual.fill();
    }

}
