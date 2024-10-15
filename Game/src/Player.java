//package io.codeforall.fanstatics;

import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player implements KeyboardHandler {
    public static final Integer PLAYER_WIDTH = 70;
    public static final Integer PLAYER_HEIGHT = 30;
    private double x;
    private double y;
    private double velocityX;
    private double velocityY;
    private static final double GRAVITY = 0.2;
    private static final double FRICTION = 0.9;


    private final Keyboard keyboard;
    private Ellipse playerImage;
    private final ControlScheme controlScheme;
    private Player input;
    private int ellipseSize = 70;

    public Player(ControlScheme controlScheme, Double x, Double y) {
        this.controlScheme = controlScheme;
        this.x = x;
        this.y = y;
        this.playerImage = new Ellipse(x, y, Player.PLAYER_WIDTH, Player.PLAYER_HEIGHT);
        this.playerImage.fill();
        this.keyboard = new Keyboard(this);

        this.initKeyboard();
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    private void initKeyboard() {
        KeyboardEvent moveRight = new KeyboardEvent();
        if (this.controlScheme == ControlScheme.ARROWS) {
            moveRight.setKey(KeyboardEvent.KEY_RIGHT);
        } else if (this.controlScheme == ControlScheme.WAD) {
            moveRight.setKey(KeyboardEvent.KEY_D);
        }
        moveRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        this.keyboard.addEventListener(moveRight);

        KeyboardEvent moveLeft = new KeyboardEvent();
        if (this.controlScheme == ControlScheme.ARROWS) {
            moveLeft.setKey(KeyboardEvent.KEY_LEFT);
        } else if (this.controlScheme == ControlScheme.WAD) {
            moveLeft.setKey(KeyboardEvent.KEY_A);
        }
        moveLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        this.keyboard.addEventListener(moveLeft);

        KeyboardEvent jump = new KeyboardEvent();
        if (this.controlScheme == ControlScheme.ARROWS) {
            jump.setKey(KeyboardEvent.KEY_UP);
        } else if (this.controlScheme == ControlScheme.WAD) {
            jump.setKey(KeyboardEvent.KEY_W);
        }
        jump.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        this.keyboard.addEventListener(jump);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
            case KeyboardEvent.KEY_D:
                //playerImage.translate(50, 0);
                this.velocityX = 5.0;
                break;
            case KeyboardEvent.KEY_LEFT:
            case KeyboardEvent.KEY_A:
                //playerImage.translate(-50, 0);
                this.velocityX = -5.0;
                break;
            case KeyboardEvent.KEY_UP:
            case KeyboardEvent.KEY_W:
                //playerImage.translate(0, -100);
                if (y == (Game.CANVAS_HEIGHT - PLAYER_HEIGHT)) {
                    this.velocityY = -10.0;
                }
                break;
        }
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
        if (y + PLAYER_HEIGHT >= Game.CANVAS_HEIGHT) {
            y = Game.CANVAS_HEIGHT - PLAYER_HEIGHT;
        }

        // Check for collision with the left wall
        if (x <= 0) {
            x = 0;
        }

        // Check for collision with the right wall
        if (x + PLAYER_WIDTH >= Game.CANVAS_WIDTH) {
            x = Game.CANVAS_WIDTH - PLAYER_WIDTH;
        }
    }

    private void show() {
        playerImage.delete();
        playerImage = new Ellipse(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
        playerImage.fill();
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent){

    }
}

