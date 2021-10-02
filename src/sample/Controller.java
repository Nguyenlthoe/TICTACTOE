package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.Random;

public class Controller {

    @FXML
        private GridPane grid = new GridPane();
    @FXML
        private Label notification;
    @FXML
        private Label selectmode;
    @FXML
        private Button changemode;

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
        changemode.setDisable(true);
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
            if(firsttic == false){
                Random ran = new Random();
                int random = ran.nextInt(9) + 1;
                button[random].setDisable(true);
                imageViews[random].setImage(image_X);
                turn = false;
                tic[random] = 1;
                stackPanes[random].getChildren().get(1).toBack();

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
            grid.add(stackPanes[i], a, b );
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
                            changemode.setDisable(false);
                        } else {
                            for(int k = 1; k < 10; k++){
                                if(tic[k] == 0){
                                    break;
                                }
                                if(k == 9){
                                    notification.setText("Hòa");
                                    start = false;
                                    changemode.setDisable(false);
                                }
                            }
                        }
                        if(start == true){
                            int cpt = computercheck();
                            button[cpt].setDisable(true);
                            if(turn == true){
                                imageViews[cpt].setImage(image_X);
                                tic[cpt] = 1;
                                turn = false;
                            } else {
                                imageViews[cpt].setImage(image_O);
                                tic[cpt] = 2;
                                turn = true;
                            }
                            stackPanes[cpt].getChildren().get(1).toBack();
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
                                changemode.setDisable(false);
                            } else {
                                for(int k = 1; k < 10; k++){
                                    if(tic[k] == 0){
                                        break;
                                    }
                                    if(k == 9){
                                        notification.setText("Hòa");
                                        start = false;
                                        changemode.setDisable(false);
                                    }
                                }
                            }
                        }
                    }

                }
            });

        }
        if(firsttic == false){
            Random ran = new Random();
            int random = ran.nextInt(9) + 1;
            button[random].setDisable(true);
            imageViews[random].setImage(image_X);
            turn = false;
            tic[random] = 1;
            stackPanes[random].getChildren().get(1).toBack();

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
    public int computercheck(){
        int cpt,hm;
        if(firsttic == true){
            cpt = 2;
            hm = 1;
        } else {
            cpt = 1;
            hm = 2;
        }
        if(tic[5] == hm){
            for(int i = 1; i < 10; i++){
                if(i != 5 && tic[i] != 0){
                    break;
                }
                if(i == 9){
                    return 1;
                }
            }
        }
        int first = computercheck2(cpt);
        int second = computercheck2(hm);
        if(first != 0){
            return first;
        }
        if(second != 0){
            return second;
        }
        if(tic[5] == 0){
            return 5;
        }
        for(int i = 2; i < 9; i+=2){
            if(tic[i] == 0){
                return i;
            }
        }
        for(int i = 1; i < 10; i += 2){
            if(tic[i] == 0){
                return i;
            }
        }
        return 0;
    }
    public int computercheck2(int target){
        if(tic[1] == target){
            if(tic[2] == target && tic[3] == 0){
                return 3;
            } else if(tic[2] == 0 && tic[3] == target){
                return 2;
            } else if(tic[4] == target && tic[7] == 0){
                return 7;
            } else if(tic[4] == 0 && tic[7] == target){
                return 4;
            } else if(tic[5] == target && tic[9] == 0){
                return 9;
            } else if(tic[9] == target && tic[5] == 0){
                return 5;
            }
        }
        if(tic[2] == target){
            if(tic[5] == target && tic[8] == 0){
                return 8;
            } else if(tic[8] == target && tic[5] == 0){
                return 5;
            } else if(tic[3] == target && tic[1] == 0){
                return 1;
            }
        }
        if(tic[3] == target){
            if(tic[5] == target && tic[7] == 0){
                return 7;
            } else if(tic[7] == target && tic[5] == 0){
                return 5;
            } else if(tic[6] == target && tic[9] == 0){
                return 9;
            } else if(tic[9] == target && tic[6] == 0){
                return 6;
            }
        }
        if(tic[4] == target){
            if(tic[5] == target && tic[6] == 0){
                return 6;
            } else if(tic[6] == target && tic[5] == 0){
                return 5;
            } else if(tic[7] == target && tic[1] == 0){
                return 1;
            }
        }
        if(tic[5] == target){
            if(tic[6] == target && tic[4] == 0){
                return 4;
            } else if(tic[7] == target && tic[3] == 0){
                return 3;
            } else if(tic[8] == target && tic[2] == 0){
                return 2;
            } else if(tic[9] == target && tic[1] == 0){
                return 1;
            }
        }
        if(tic[6] == target){
            if(tic[9] == target && tic[3] == 0){
                return 3;
            }
        }
        if(tic[7] == target){
            if(tic[8] == target && tic[9] == 0){
                return 9;
            } else if(tic[9] == target && tic[8] == 0){
                return 8;
            }
        }
        if(tic[8] == target){
            if(tic[9] == target && tic[7] == 0){
                return 7;
            }
        }
        return 0;
    }
    public void change(){
        if(firsttic == true){
            firsttic = false;
            selectmode.setText("Bạn chơi sau");
        } else {
            firsttic =true;
            selectmode.setText("Bạn chơi trước");
        }
    }
}
