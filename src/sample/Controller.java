package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class Controller {

    @FXML
        private GridPane grid = new GridPane();

    private StackPane[] stackPanes = new StackPane[10];
    private Button[] button = new Button[10];
    private ImageView[] imageViews = new ImageView[10];
    private int[] tic = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private boolean turn = true;
    private int i = 0;
    Image image_X = new Image("/image/x_image.png", 242, 178, false, true);
    Image image_O = new Image("/image/o_image.png", 242, 178, false, true);


    public void newgame(){
        grid.setAlignment(Pos.CENTER);
        grid.setPrefSize(640, 490);
        grid.setHgap(20);
        grid.setVgap(20);
        for(i = 1; i < 10; i++){
            stackPanes[i] = new StackPane();
            int a = (i % 3 == 0) ? 2 : (i % 3 - 1);
            int b = (i % 3 == 0) ? i/3 - 1 : i / 3;
            grid.add(stackPanes[i], b, a );
            imageViews[i] = new ImageView(image_O);
            button[i] = new Button();
            button[i].setDisable(false);
            button[i].setPrefSize(242, 178);
            stackPanes[i].getChildren().add(imageViews[i]);
            stackPanes[i].getChildren().add(button[i]);
            int index = i;
            button[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    button[index].setDisable(true);
                    System.out.println(index);
                    if(turn == true){
                        imageViews[index].setImage(image_X);
                        turn = false;
                    } else {
                        imageViews[index].setImage(image_O);
                        turn = true;
                    }
                    stackPanes[index].getChildren().get(1).toBack();
                }
            });
        }
    }

    public void checkwin(){

    }
}
