package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Sprite extends Rectangle {
    boolean dead = false;



    Sprite(int x, int y, int w, int h,  Color color) {
        super(w, h, color);

        setTranslateX(x);
        setTranslateY(y);
    }

    void moveLeft() {
        setTranslateX(getTranslateX() - 5);
    }

    void moveRight() {
        setTranslateX(getTranslateX() + 5);
    }



}