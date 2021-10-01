package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class Controller {

    @FXML
        private GridPane grid = new GridPane();
    @FXML
        private Label notification;
    private StackPane[] stackPanes = new StackPane[10];
    private Button[] button = new Button[10];
    private ImageView[] imageViews = new ImageView[10];
    private int[] tic = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private boolean turn = true;
    private boolean firsttic = true;
    Image image_X = new Image("/image/x_image.png", 242, 178, false, true);
    Image image_O = new Image("/image/o_image.png", 242, 178, false, true);

    private boolean start = false;
    int checkfirst = 0;
    public void newgame(){
        start = true;
        notification.setText("");
        turn = true;
        if(checkfirst == 1){
            for(int i = 1; i < 10; i++){
                button[i].setDisable(false);
                if(tic[i] != 0){
                    stackPanes[i].getChildren().get(1).toBack();
                    tic[i] = 0;
                }
            }
            return ;
        }

        grid.setAlignment(Pos.CENTER);
        grid.setPrefSize(640, 490);
        grid.setHgap(20);
        grid.setVgap(20);
        for(int i = 1; i < 10; i++){
            stackPanes[i] = new StackPane();
            int a = (i % 3 == 0) ? 2 : (i % 3 - 1);
            int b = (i % 3 == 0) ? i/3 - 1 : i / 3;
            grid.add(stackPanes[i], b, a );
            imageViews[i] = new ImageView(image_O);
            button[i] = new Button();
            button[i].setDisable(false);
            imageViews[i].setVisible(true);
            button[i].setPrefSize(242, 178);
            stackPanes[i].getChildren().add(imageViews[i]);
            stackPanes[i].getChildren().add(button[i]);
            int index = i;
            button[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(start == true){
                        button[index].setDisable(true);
                        System.out.println(index);
                        if(turn == true){
                            imageViews[index].setImage(image_X);
                            tic[index] = 1;
                            turn = false;
                        } else {
                            imageViews[index].setImage(image_O);
                            tic[index] = 2;
                            turn = true;
                        }
                        stackPanes[index].getChildren().get(1).toBack();
                        if(checkwin() == true){
                            if(turn == false){
                                if(firsttic == true){
                                    notification.setText("Bạn đã thắng!");
                                } else notification.setText("Bạn đã thua!");
                            } else {
                                if(firsttic == true){
                                    notification.setText("Bạn đã thua!");
                                } else notification.setText("Bạn đã thắng!");
                            }
                            start = false;
                        } else {
                            for(int k = 1; k < 10; k++){
                                if(tic[k] == 0){
                                    break;
                                }
                                if(k == 9){
                                    notification.setText("Hòa");
                                }
                            }
                        }
                    }

                }
            });
        }
        checkfirst = 1;
    }

    public boolean checkwin(){
        if(checkcolumn() == true){
            return true;
        } else if(checkcross() == true){
            return true;
        } else if(checkrow() == true){
            return true;
        } else return false;
    }
    public boolean checkrow(){
        for(int i = 1; i < 4; i++){
            if(tic[i] != 0 && tic[i] == tic[i + 3] && tic[i + 6] == tic[i]){
                return true;
            }
        }
        return false;
    }
    public boolean checkcolumn(){
        for(int i = 1; i < 8; i += 3){
            if(tic[i] != 0 && tic[i] == tic[i + 1] && tic[i] == tic[i+2]){
                return true;
            }
        }
        return false;
    }
    public boolean checkcross(){
        if(tic[5] != 0){
            if(tic[5] == tic[1] && tic[5] == tic[9] || tic[5] == tic[3] && tic[5] == tic[7]){
                return true;
            }
        }
        return false;
    }
}
