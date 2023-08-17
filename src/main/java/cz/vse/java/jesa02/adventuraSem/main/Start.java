/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package cz.vse.java.jesa02.adventuraSem.main;




import cz.vse.java.jesa02.adventuraSem.logika.Hra;
import cz.vse.java.jesa02.adventuraSem.logika.IHra;
import cz.vse.java.jesa02.adventuraSem.uiText.TextoveRozhrani;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;

/*******************************************************************************
 * Třída  Start je hlavní třídou projektu,
 * který představuje jednoduchou textovou adventuru určenou k dalším úpravám a rozšiřování
 *
 * @author    Jarmila Pavlíčková
 * @version   ZS 2016/2017
 */

public class  Start extends Application {
        /***************************************************************************
         * Metoda, prostřednictvím níž se spouští celá aplikace.
         *
         * @param args Parametry příkazového řádku
         */

        public static void main(String[] args) {
            launch();
        /*
        IHra hra = new Hra();
        TextoveRozhrani ui = new TextoveRozhrani(hra);
        ui.hraj();
        */
        }

        @Override
        public void start (Stage stage) throws Exception {
            GridPane menu = FXMLLoader.load(getClass().getResource("/mainMenu.fxml"));
            stage.setScene(new Scene(menu));
            stage.show();
        }


    


}
