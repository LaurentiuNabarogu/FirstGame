package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RoadSeparator extends Rectangle {


    public RoadSeparator(int x, int y, int w, int h, Color color){
        super(w,h,color);
        setTranslateX(x);
        setTranslateY(y);


    }


}
