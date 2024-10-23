//package io.codeforall.fanstatics;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player implements KeyboardHandler {
    public static final Integer PLAYER_WIDTH = 70;
    public static final Integer PLAYER_HEIGHT = 100;
    private static final double GRAVITY = 1;
    private static final double FRICTION = 0.9;

    private double initialX;
    private double initialY;
    private double x;
    private double y;
    private double velocityX;
    private double velocityY;
    private String imgPath;

    private final Keyboard keyboard;
    private Picture playerImage;
    private final ControlScheme controlScheme;

    public Player(ControlScheme controlScheme, Double x, Double y, String imgPath, int offsetX, int offsetY) {
        this.controlScheme = controlScheme;
        this.x = x;
        this.y = y;
        this.imgPath = imgPath;
        this.initialX = x;
        this.initialY = y;
        playerImage = new Picture(PLAYER_WIDTH, PLAYER_HEIGHT, this.imgPath);
        playerImage.grow(offsetX, offsetY);
        playerImage.draw();
        this.keyboard = new Keyboard(this);

        initKeyboard();
    }

    public Picture getPlayerImage() {
        return playerImage;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void resetPlayer() {
        x = initialX;
        y = initialY;
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
        if (controlScheme == ControlScheme.WAD) {
            if (x <= 0) {
                x = 0;
            }
        } else {
            if (x <= 650) {
                x = 650;
            }
        }

        // Check for collision with the right wall
        if (controlScheme == ControlScheme.ARROWS) {
            if (x + PLAYER_WIDTH >= Game.CANVAS_WIDTH + 25) {
                x = (Game.CANVAS_WIDTH + 25) - PLAYER_WIDTH;
            }
        } else {
            if (x + PLAYER_WIDTH >= 600) {
                x = 600 - PLAYER_WIDTH;
            }
        }

    }

    private void show() {
        playerImage.delete();
        updateImage();
        playerImage.draw();
    }

    private void updateImage() {
        double xDifference = (x - playerImage.getX());
        double yDifference = y - playerImage.getY();
        if (Math.abs(xDifference) > 1 || Math.abs(yDifference) > 1) {
            playerImage.translate(xDifference, yDifference);
        }
    }

    private void initKeyboard() {
        if (this.controlScheme == ControlScheme.ARROWS) {
            setUpKey(KeyboardEvent.KEY_RIGHT, KeyboardEventType.KEY_PRESSED);
        } else if (this.controlScheme == ControlScheme.WAD) {
            setUpKey(KeyboardEvent.KEY_D, KeyboardEventType.KEY_PRESSED);
        }

        if (this.controlScheme == ControlScheme.ARROWS) {
            setUpKey(KeyboardEvent.KEY_LEFT, KeyboardEventType.KEY_PRESSED);
        } else if (this.controlScheme == ControlScheme.WAD) {
            setUpKey(KeyboardEvent.KEY_A, KeyboardEventType.KEY_PRESSED);
        }

        if (this.controlScheme == ControlScheme.ARROWS) {
            setUpKey(KeyboardEvent.KEY_UP, KeyboardEventType.KEY_PRESSED);
        } else if (this.controlScheme == ControlScheme.WAD) {
            setUpKey(KeyboardEvent.KEY_W, KeyboardEventType.KEY_PRESSED);
        }

        setUpKey(KeyboardEvent.KEY_H, KeyboardEventType.KEY_PRESSED);
    }

    private void setUpKey(int keyEvent, KeyboardEventType keyType) {

        KeyboardEvent key = new KeyboardEvent();
        key.setKey(keyEvent);
        key.setKeyboardEventType(keyType);
        keyboard.addEventListener(key);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
            case KeyboardEvent.KEY_D:
                velocityX = 15.0;
                break;
            case KeyboardEvent.KEY_LEFT:
            case KeyboardEvent.KEY_A:
                velocityX = -15.0;
                break;
            case KeyboardEvent.KEY_UP:
            case KeyboardEvent.KEY_W:
                jump();
                break;
            case KeyboardEvent.KEY_H:
                System.exit(0);// (Only works in .jar) Exit application beacuse alt+f4 doesn't really close the
                               // application.
                break;
        }
    }

    private void jump() {
        if (y == (Game.CANVAS_HEIGHT - PLAYER_HEIGHT)) {
            this.velocityY = -25.0;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
