package newgame01;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;

public class Newgame01 extends Application {

    private boolean isLeft, isRight, isUp, isDown;
    private ImageView player = new ImageView(new Image(Newgame01.class.getResourceAsStream("player.png")));
    private ImageView backimage = new ImageView(new Image(Newgame01.class.getResourceAsStream("newmap01.png")));
    private Camera camera = new PerspectiveCamera();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("newgame01");
        primaryStage.setScene(scene);
        primaryStage.show();

//        File player_path = new File("file:./newgame01/player.png");
//        if(!player_path.exists()){
//            Platform.exit();
//        }
//        ImageView player = new ImageView(player_path.toURI().toString());
//        Image image = new Image(Newgame01.class.getResourceAsStream("player.png"));
//        ImageView player = new ImageView(new Image(Newgame01.class.getResourceAsStream("player.png")));
//        player.setImage(image);

        root.getChildren().add(backimage);
        player.setX(300);
        player.setY(200);
        root.getChildren().add(player);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()){
                case LEFT:
                    isLeft = true;
                    break;
                case RIGHT:
                    isRight = true;
                    break;
                case UP:
                    isUp = true;
                    break;
                case DOWN:
                    isDown = true;
                    break;
                default:
                    break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()){
                case LEFT:
                    isLeft = false;
                    break;
                case RIGHT:
                    isRight = false;
                    break;
                case UP:
                    isUp = false;
                    break;
                case DOWN:
                    isDown = false;
                    break;
                default:
                    break;
            }
        });

        // start game loop
        new AnimationTimer(){
            @Override
            public void handle(long now) {
                gameLoop();
            }
        }.start();
    }

    private void gameLoop() {
        int move_unit = 6;
        if(isLeft)  { player.setX(player.getX()- move_unit);}
        if(isRight) { player.setX(player.getX()+ move_unit);}
        if(isUp)    { player.setY(player.getY()- move_unit);}
        if(isDown)  { player.setY(player.getY()+ move_unit);}
        
        camera_move();
    }

    private void camera_move() {
        if(isLeft){ camera.setTranslateX(camera.getTranslateX()-12);}
        if(isRight){ camera.setTranslateX(camera.getTranslateX()+12);}
        if(isUp){ camera.setTranslateY(camera.getTranslateY()-12);}
        if(isDown){ camera.setTranslateY(camera.getTranslateY()+12);}
    }


}
