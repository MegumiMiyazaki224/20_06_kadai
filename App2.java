import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;


public class App2 extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{

        //ウィンドウにタイトルをつける
        stage.setTitle("Slot Machine");

        //MyButtonのArrayListを作成
        ArrayList<MyButton> button_array = new ArrayList<>();

        //5つMyButtonを用意し、最初はすべて１を出力するようにする
        for(int i = 0; i < 5; i++){
            button_array.add(new MyButton("1"));
        }

        //上記で用意した3つのMyButtonをまとめるHBoxを作成
        HBox numberBox = new HBox(20d);
        numberBox.setPadding(new Insets(10,10,10,10));
        numberBox.setAlignment(Pos.CENTER);

        numberBox.getChildren().addAll(button_array);


        //Startボタンを作成する
        Button startButton = new Button("Start");
//        Button stopButton = new Button("Stop");


        //Startボタンをおした時、スロットがスタートする
        startButton.setOnAction(event -> {
            for(int i = 0; i < button_array.size(); i++){
                button_array.get(i).setSlotStarted();
            }
        });


        //各々ボタン押したらスロット止まるようにする
        button_array.get(0).setOnAction(event -> {
            button_array.get(0).stopSlot();
//            boolean roll1 = button_array.get(0).getIsSlotStarted();
//            System.out.println(roll1);
        });
        button_array.get(1).setOnAction(event -> {
            button_array.get(1).stopSlot();
//            boolean roll2 = button_array.get(1).getIsSlotStarted();
//            System.out.println(roll2);
        });
        button_array.get(2).setOnAction(event -> {
            button_array.get(2).stopSlot();
//            boolean roll3 = button_array.get(2).getIsSlotStarted();
//            System.out.println(roll3);

        });
        button_array.get(3).setOnAction(event -> {
            button_array.get(3).stopSlot();
//            boolean roll4 = button_array.get(3).getIsSlotStarted();
//            System.out.println(roll4);

        });
        button_array.get(4).setOnAction(event -> {
            button_array.get(4).stopSlot();
//            boolean roll5 = button_array.get(4).getIsSlotStarted();
//            System.out.println(roll5);
        });

        //ボタンを押して止めたときの数字を取得して、resultに入れるよ(数字のみを比較するため)
        String result1 = button_array.get(0).stopSlot();
        String result2 = button_array.get(1).stopSlot();
        String result3 = button_array.get(2).stopSlot();
        String result4 = button_array.get(3).stopSlot();
        String result5 = button_array.get(4).stopSlot();

        //各々の配列（ボタン）はスロットスタート前か後かを取得
        boolean roll1 = button_array.get(0).getIsSlotStarted();
        boolean roll2 = button_array.get(1).getIsSlotStarted();
        boolean roll3 = button_array.get(2).getIsSlotStarted();
        boolean roll4 = button_array.get(3).getIsSlotStarted();
        boolean roll5 = button_array.get(4).getIsSlotStarted();

//        System.out.println(roll1);
//        System.out.println(roll2);
//        System.out.println(roll3);
//        System.out.println(roll4);
//        System.out.println(roll5);


        //rollの中身がすべてtrueだった際に以下の判定が行われる
        if(roll1 == true && roll2 == true && roll3 == true && roll4 == true && roll5 == true){

            //resultの中身が一緒ならば新しいウインドウを生成
            if (result1.equals(result2) && result1.equals(result3) && result1.equals(result4) && result1.equals(result5)) {
                //新しいウインドウを生成
                Stage newStage = new Stage();

                //モーダルウインドウを設定
                newStage.initModality(Modality.APPLICATION_MODAL);
                //オーナーを設定
                newStage.initOwner(stage);

                //新しいウインドウ内に配置するコンテンツを生成
                HBox hBox = new HBox();
                Label label = new Label("おめでとう！");
                hBox.getChildren().add(label);

                newStage.setScene(new Scene(hBox));
                newStage.show();

                //音を鳴らす
                Media sound = new Media(getClass().getResource("fanfare.mp3").toExternalForm());
                MediaPlayer mp = new MediaPlayer(sound);
                mp.play();
            }

        }







        //start・stopボタンをまとめるHBoxを作成
        HBox setBox = new HBox(20d);
        setBox.setPadding(new Insets(10,10,10,10));
        setBox.setAlignment(Pos.CENTER);
        setBox.getChildren().add(startButton);
//        setBox.getChildren().add(stopButton);



        //BorderPaneを作成
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(numberBox);
        borderPane.setBottom(setBox);

        Scene scene = new Scene(borderPane, 500, 150);
        stage.setScene(scene);
        stage.show();

    }
}


