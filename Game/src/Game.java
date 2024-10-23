import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game {
    public static final int CANVAS_WIDTH = 1250;
    public static final int CANVAS_HEIGHT = 720;

    Picture canvas;
    Score score;
    Ball ball;
    Player p1;
    Player p2;

    public Game() {
        setupCanvas();

        setupGoals();

        score = new Score();

        ball = new Ball(score);// setup ball

        // setup players
        p1 = new Player(ControlScheme.WAD, 50.0, 500.0, "rsc/burger.png", -75, -75);
        p2 = new Player(ControlScheme.ARROWS, 1200.0, 500.0, "rsc/pizza1.png", -35, -75);
    }

    private void setupCanvas() {
        canvas = new Picture(CANVAS_WIDTH, CANVAS_HEIGHT, "rsc/stadium.png");
        canvas.translate(-1550, -910);
        canvas.grow(-300, 0);
        canvas.draw();
    }

    private void setupGoals() {
        Picture goal;
        goal = new Picture(0, 0, "rsc/baliza.png");
        goal.grow(-50, -200);
        goal.translate(-55, 250);
        goal.draw();

        goal = new Picture(0, 0, "rsc/baliza.png");
        goal.grow(-135, -200);
        goal.translate(1190, 250);
        goal.draw();

    }

    public void update(long millis) throws InterruptedException {
        while (true) {
            Thread.sleep(millis);

            p1.update();

            p2.update();

            ball.update(p1, p2);
        }

    }

}
