import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Random;

public class MyButton extends Button {

    private boolean isSlotStarted;

    MyButton(String text){
        super(text);
        isSlotStarted = false;
        this.setPrefSize(80,80);
        this.setAlignment(Pos.CENTER);
        this.setBorder(border);

    }

    int randomNum;
    Timeline timer = new Timeline(new KeyFrame(Duration.millis(100), e ->{
        Random rand = new Random();
        randomNum = rand.nextInt(2) + 1;
        this.setText(Integer.toString(randomNum));
    }));

    public void setSlotStarted(){
        // この中に各数字のスロットが回り始めた時の処理を記載しましょう
//        isSlotStarted = false;
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    public String stopSlot() {
        // この中に各数字のスロットが止まった時の処理を記載しましょう
        isSlotStarted = true;
        timer.stop();
        return Integer.toString(randomNum);
    }

    public boolean getIsSlotStarted(){
        return isSlotStarted;

    }

    //ボーダーを設定
    Border border = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT));




}
