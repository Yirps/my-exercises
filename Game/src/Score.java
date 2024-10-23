import org.academiadecodigo.simplegraphics.graphics.Text;

public class Score {
    private int p1Score;
    private int p2Score;
    private final int MAX_SCORE = 5;
    private Text announcerText;
    private Text scoreBoard;

    public Score() {
        p1Score = 0;
        p2Score = 0;
        scoreBoard = new Text(660, 245, p1Score + " - " + p2Score);// redundency to make update score more abstract
        updateScore();
    }

    public void updateScore() {
        scoreBoard.delete();
        scoreBoard = new Text(660, 238, p1Score + " - " + p2Score);
        scoreBoard.grow(100, 50);
        scoreBoard.draw();
    }

    public boolean player1Scored() {
        p1Score++;
        updateScore();
        scoreAnnouncer("Burger Scores !");
        return checkForWinner();
    }

    public boolean player2Scored() {
        p2Score++;
        updateScore();
        scoreAnnouncer("Pizza Scores !");
        return checkForWinner();
    }

    private boolean checkForWinner() {
        if (p1Score >= MAX_SCORE) {
            resetAnnouncer();
            resetScores();
            updateScore();
            scoreAnnouncer("Burger Wins!");
            return true;
        } else if (p2Score >= MAX_SCORE) {
            resetAnnouncer();
            resetScores();
            updateScore();
            scoreAnnouncer("Pizza Wins!");
            return true;
        }
        return false;
    }

    private void resetScores() {
        p1Score = 0;
        p2Score = 0;
    }

    private void scoreAnnouncer(String message) {
        resetAnnouncer();
        announcerText = new Text(660, 350, message);
        announcerText.grow(200, 50);
        announcerText.draw();
    }

    public void resetAnnouncer() {
        if (announcerText != null) {
            announcerText.delete();
        }
    }

}