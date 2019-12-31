package sample;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
    private AnimationTimer timer;
    private List<Node> cars = new ArrayList<>();
    private List<RoadSeparator> separators = new ArrayList<>();
    private Pane root = new Pane();




    private Sprite player = new Sprite(300, 700, 38, 60, Color.TRANSPARENT);
    Image image = new Image("/resources/car4.png");
    ImagePattern car = new ImagePattern(image);

    Image image1 = new Image("/resources/car3.png");
    ImagePattern enemycar = new ImagePattern(image1);


    private Parent createContent() {
        player.setFill(car);
        root.setPrefSize(600, 800);
        root.setStyle("-fx-background-color: black;");

                generateSeparators();


        root.getChildren().addAll(player);

       timer = new AnimationTimer() {
           @Override
            public void handle(long now) {

                   update();


            }
        };
        timer.start();

       return root;
   }

    private void generateSeparators() {
        for(int i = 0; i<10000; i++){
            RoadSeparator s = new RoadSeparator(300,  800 +(-i*100), 10, 40, Color.WHITE);
            separators.add(s);
           root.getChildren().add(s);
        }
    }


    private void update(){


            for(RoadSeparator separator : separators){

                separator.setTranslateY(separator.getTranslateY() + 1); //speed of the separators


            }


            for(Node car : cars){
                car.setTranslateY(car.getTranslateY()+2);//speed of the enemy
            }

            if(Math.random()<0.01){
                cars.add(spawnCar());
            }

                checkState();

        }

        private void checkState(){

            for(Node car: cars){
                if(car.getBoundsInParent().intersects(player.getBoundsInParent())){
                    timer.stop();
                    player.dead = true;
                    String lose = "Game Over";
                    HBox hBox = new HBox();
                    hBox.setTranslateX(175);
                    hBox.setTranslateY(350);
                    root.getChildren().add(hBox);
                    for(int i = 0;  i <lose.toCharArray().length; i++){
                        char letter = lose.charAt(i);
                        Text text = new Text(String.valueOf(letter));
                        text.setFont(Font.font(50));
                        text.setFill(Color.WHITE);
                        text.setOpacity(0);
                        hBox.getChildren().add(text);
                        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.66), text);
                        fadeTransition.setToValue(1);
                        fadeTransition.setDelay(Duration.seconds(i * 0.15));
                        fadeTransition.play();
                    }
                }
            }
        }



    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createContent());

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case A:
                    player.moveLeft();
                    break;
                case D:
                    player.moveRight();
                    break;

            }
        });
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }




    private Node spawnCar(){
        Rectangle rect = new Rectangle(38, 60, enemycar);
        rect.setTranslateX((int)(Math.random()*14)*40);
        root.getChildren().add(rect);

        return rect;
    }

    public static void main(String[] args) {
        launch(args);
    }
}