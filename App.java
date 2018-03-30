import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;


public class App extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{

        //ウィンドウにタイトルをつける
        stage.setTitle("Slot Machine");

        //MyLabelのArrayListを作成
        ArrayList<MyLabel> label_array = new ArrayList<>();

        //3つMyLabelを用意し、最初はすべて１を出力するようにする
        for(int i = 0; i < 3; i++){
            label_array.add(new MyLabel("1"));
        }

        //上記で用意した3つのMyLabelをまとめるHBoxを作成
        HBox numberBox = new HBox(20d);
        numberBox.setPadding(new Insets(10,10,10,10));
        numberBox.setAlignment(Pos.CENTER);

        numberBox.getChildren().addAll(label_array);


        //Start・Stopボタンを作成する
        Button startButton = new Button("Start");
        Button stopButton = new Button("Stop");


        //Startボタンをおした時、スロットがスタートする
        startButton.setOnAction(event -> {
            for(int i = 0; i < label_array.size(); i++){
                label_array.get(i).setSlotStarted();
            }
        });

        //Stopボタンをおした時、[1]スロットがストップする [2]配列の中身が一緒ならば新しいウインドウを生成
        stopButton.setOnAction(event -> {

            //[1]スロットをストップ
            for(int i = 0; i < label_array.size(); i++){
                label_array.get(i).stopSlot();
            }

            String result1 = label_array.get(0).stopSlot();
            String result2 = label_array.get(1).stopSlot();
            String result3 = label_array.get(2).stopSlot();

            //[2]配列の中身が一緒ならば新しいウインドウを生成
            if(result1.equals(result2) && result2.equals(result3)){
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
            }

        });



        //start・stopボタンをまとめるHBoxを作成
        HBox setBox = new HBox(20d);
            setBox.setPadding(new Insets(10,10,10,10));
            setBox.setAlignment(Pos.CENTER);
            setBox.getChildren().add(startButton);
            setBox.getChildren().add(stopButton);


        //BorderPaneを作成
        BorderPane borderPane = new BorderPane();
            borderPane.setCenter(numberBox);
            borderPane.setBottom(setBox);

        Scene scene = new Scene(borderPane, 350, 150);
            stage.setScene(scene);
            stage.show();

    }
}


