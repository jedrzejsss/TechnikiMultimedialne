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

/** główna klasa odpowiedzialna za okienko */
public class Klawiatura extends Application {

    private LettersPane lettersPane;

    /** ustawienia rozmiaru okna */
    private void init(Stage primaryStage) { 
        Group root = new Group();
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 750, 750));
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

    /** klasa odpowiedzialna za dodanie elementów do sceny oraz ustawienie ich elementów */
    public static class LettersPane extends Region { 

        private static final Font FONT_DEFAULT = new Font(Font.getDefault().getFamily(), 200);
        private static final Interpolator INTERPOLATOR = Interpolator.SPLINE(0.295, 0.800, 0.305, 1.000);
        private Text polecenieText;
        private Text textDoPrzepisania;
        private VBox vbox;
        private TextArea textArea;
        private VBox vbox2;
        private PrzechwytywanieKlawiszy przechwytywanieKlawiszy;
        private Grupowanie grupowanie;

        /** konstruktor klasy letter pane, ustwanienie jej parametrów */
        public LettersPane() { 
            setId("LettersPane");
            setPrefSize(700, 700);
            setFocusTraversable(true);
            setOnMousePressed(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent me) {
                    requestFocus();
                    me.consume();
                }
            });

            polecenieText = new Text("Przepisz poniższy tekst lub wpisz cokolwiek:");
            polecenieText.setTextOrigin(VPos.TOP);
            polecenieText.setFont(new Font(Font.getDefault().getFamily(), 32));
            polecenieText.setFill(Color.rgb(80, 80, 80));

            textDoPrzepisania = new Text("Java to obiektowy język programowania \n stworzony przez grupę roboczą pod kierunkiem\nJamesa Goslinga z firmy Sun Microsystems. Java jest językiem\n tworzenia programów źródłowych kompilowanych do kodu bajtowego,\nczyli postaci wykonywanej przez maszynę wirtualną. Język\ncechuje się silnym typowaniem. Jego podstawowe\nkoncepcje zostały przejęte z języka Smalltalk\noraz z języka C++");
            textDoPrzepisania.setTextOrigin(VPos.TOP);
            textDoPrzepisania.setFont(new Font(Font.getDefault().getFamily(), 20));
            textDoPrzepisania.setLayoutY(100);
            textDoPrzepisania.setFill(Color.rgb(80, 80, 80));

            przechwytywanieKlawiszy = new PrzechwytywanieKlawiszy();
            grupowanie = new Grupowanie();

            textArea = new TextArea();
            textArea.minHeight(200);
            textArea.minWidth(30000);
            textArea.setOnKeyPressed((KeyEvent ke) -> {
                przechwytywanieKlawiszy.zapiszPressed(ke.getText());
                ke.consume();
            });
            
            textArea.setOnKeyReleased((KeyEvent ke) -> {
                przechwytywanieKlawiszy.zapiszRelased(ke.getText());
                ke.consume();
            });

            vbox2 = new VBox(textArea);
            vbox2.setMinHeight(300);
            vbox2.setMinWidth(700);

            Button przycisk = new Button("wypisz");
            przycisk.setMinWidth(100);

            przycisk.setOnAction(value -> {
                przechwytywanieKlawiszy.segreguj();
                grupowanie.grupuj(przechwytywanieKlawiszy.dajPrzejscia());
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
