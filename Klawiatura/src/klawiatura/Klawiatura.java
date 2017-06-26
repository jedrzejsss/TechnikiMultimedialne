/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klawiatura;

/**
 *
 * @author Jedrzej
 */
import javafx.animation.Interpolator;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

public class Klawiatura extends Application {

    private LettersPane lettersPane;

    private void init(Stage primaryStage) { //ustawienia rozmiaru okna
        Group root = new Group();
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 520, 520));
        lettersPane = new LettersPane();
        root.getChildren().add(lettersPane);
    }

    public void play() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lettersPane.requestFocus();
            }
        });
    }

    public static class LettersPane extends Region { //klasa odpowiedzialna za dodanie elementów do sceny oraz ustawienie ich elementów 

        private static final Font FONT_DEFAULT = new Font(Font.getDefault().getFamily(), 200);
        private static final Interpolator INTERPOLATOR = Interpolator.SPLINE(0.295, 0.800, 0.305, 1.000);
        private Text polecenieText;
        private Text textDoPrzepisania;
        private VBox vbox;
        TextArea textArea;
        VBox vbox2;
        private PrzechwytywanieKlawiszy przechwytywanieKlawiszy;

        public LettersPane() { //konstruktor klasy letter pane, ustwanienie jej parametrów
            setId("LettersPane");
            setPrefSize(480, 480);
            setFocusTraversable(true);
            setOnMousePressed(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent me) {
                    requestFocus();
                    me.consume();
                }
            });

            polecenieText = new Text("Przepisz poniższy tekst:");
            polecenieText.setTextOrigin(VPos.TOP);
            polecenieText.setFont(new Font(Font.getDefault().getFamily(), 32));
            polecenieText.setFill(Color.rgb(80, 80, 80));

            textDoPrzepisania = new Text("Ala ma kota 123, a kot ma kaca.\n Piwo lej barmanie piwo lej\n albowiem ja pragnienie mam");
            textDoPrzepisania.setTextOrigin(VPos.TOP);
            textDoPrzepisania.setFont(new Font(Font.getDefault().getFamily(), 20));
            textDoPrzepisania.setLayoutY(100);
            textDoPrzepisania.setFill(Color.rgb(80, 80, 80));

            przechwytywanieKlawiszy = new PrzechwytywanieKlawiszy();

            textArea = new TextArea();
            textArea.minHeight(20);
            textArea.minWidth(3000);
            textArea.setOnKeyPressed((KeyEvent ke) -> {
                przechwytywanieKlawiszy.zapiszPressed(ke.getText());
                ke.consume();
            });
            
            textArea.setOnKeyReleased((KeyEvent ke) -> {
                przechwytywanieKlawiszy.zapiszRelased(ke.getText());
                ke.consume();
            });

            vbox2 = new VBox(textArea);
            vbox2.setMinHeight(100);
            vbox2.setMinWidth(300);

            Button przycisk = new Button("wypisz");
            przycisk.setMinWidth(100);

            przycisk.setOnAction(value -> {
                przechwytywanieKlawiszy.sortuj();
            });

            vbox = new VBox();
            vbox.getChildren().addAll(polecenieText, textDoPrzepisania, vbox2, przycisk);
            vbox.setSpacing(20);
            getChildren().add(vbox);
        }

        @Override
        protected void layoutChildren() {
            vbox.setLayoutX((getWidth() - textDoPrzepisania.getLayoutBounds().getWidth()) / 2);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
        play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
