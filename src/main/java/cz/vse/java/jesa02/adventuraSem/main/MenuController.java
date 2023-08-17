package cz.vse.java.jesa02.adventuraSem.main;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class  MenuController extends GridPane{
    @FXML Button napovedaButton;
    @FXML private Button startButton;

    /**
     * Metoda spousti hru ve stejnem okne.
     * @param actionEvent
     * @throws Exception
     */
    public void buttonPress(javafx.event.ActionEvent actionEvent) throws Exception {
        Stage menu = (Stage) startButton.getScene().getWindow();

        GridPane home = FXMLLoader.load(getClass().getResource("/home.fxml"));
        menu.setScene(new Scene(home));
        menu.show();

    }

    /**
     * Metoda spousti napovedu v novem okne.
     * @param actionEvent
     * @throws Exception
     */
    public void buttonPressMenu(javafx.event.ActionEvent actionEvent) throws Exception {
        Pane napoveda = FXMLLoader.load(getClass().getResource("/napoveda.fxml"));

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load(getClass().getResource("/napoveda.html").toString());
        napoveda.getChildren().addAll(webView);

        Stage stage = new Stage();
        stage.setScene(new Scene(napoveda));
        stage.show();

    }
}
