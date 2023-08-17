package cz.vse.java.jesa02.adventuraSem.main;


import cz.vse.java.jesa02.adventuraSem.logika.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller pro hlavní rozhraní aplikace
 * Implementuje rozhrani Observer a dedi tridu Gridpane.
 */
public class HomeController extends GridPane implements Observer {


    @FXML private ImageView botyOn, kalhotyOn, trikoOn, trikoFanousek;
    @FXML private ImageView hrac;
    @FXML private ListView panelVychodu;
    @FXML private TextArea vystup;
    @FXML private TextField vstup;
    @FXML private Button odesli;
    @FXML private VBox vypisBatoh;

    private IHra hra;
    Map<String, Point2D> souradnice = new HashMap<>();

    /**
     * inicializani metoda, spousti hru a regisruje sebe jako pozorovatele
     * Zaklada souradnice pro pohyb na mape, nastavuje vychozi polohu na mape, zneviditelni ekter objekty imageview
     */
    @FXML
    private void initialize(){
        hra = new Hra();
        hra.getHerniPlan().register(this);
        hra.getHerniPlan().getBatoh().register(this);
        vystup.appendText(hra.vratUvitani()+"\n\n");
        panelVychodu.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
        setInvisible();
        souradnice = createSouradnice();
        hrac.setLayoutX(souradnice.get(hra.getHerniPlan().getAktualniProstor().getNazev()).getX());
        hrac.setLayoutY(souradnice.get(hra.getHerniPlan().getAktualniProstor().getNazev()).getY());


        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vstup.requestFocus();
            }
        });
    }

    /**
     * Metoda pro aktualizaci grafické podoby batohu
     */
    public void updateBatoh(){
        Collection<Vec> mujBatoh = hra.getHerniPlan().getBatoh().getVeciBatoh();
        vypisBatoh.getChildren().clear();

        for (Vec vec : mujBatoh) {
            String vecNazev = vec.getNazev();
            Label itemLabel = new Label(vecNazev);
            InputStream stream = getClass().getClassLoader().getResourceAsStream(vecNazev + ".png");
            Image img = new Image(stream);
            ImageView imageView = new ImageView(img);
            imageView.setFitWidth(60);
            imageView.setFitHeight(60);
            itemLabel.setGraphic(imageView);

            vypisBatoh.getChildren().add(itemLabel);
        }
        panacekUpdate();
    }

    /**
     * Metoda pro aktualizaci stavu obleceni postavy, volana v metoda updateBatoh()
     */
    public void panacekUpdate(){
        Batoh batuzek = hra.getHerniPlan().getBatoh();
        if(batuzek.isObleceno("triko")){
            trikoOn.setVisible(true);}
            else {trikoOn.setVisible(false);}


        if(batuzek.isObleceno("kalhoty")){
            kalhotyOn.setVisible(true);}
        else {kalhotyOn.setVisible(false);}

        if(batuzek.isObleceno("boty")){
            botyOn.setVisible(true);}
        else {botyOn.setVisible(false);}

        if(batuzek.isObleceno("triko_fanouska")){
            trikoFanousek.setVisible(true);}
        else {trikoFanousek.setVisible(false);}

    }

    /**
     * Metoda pro zpracovani textoveho vstupu hrace
     * @param actionEvent
     */
    @FXML
    private void zpracujVstup(ActionEvent actionEvent) {
        String prikaz = vstup.getText();
        zpracujPrikaz(prikaz);
        vstup.clear();

    }

    /**
     * metoda aktualizuje mistnosti zobrazovano v panelu vychozich mistnosti
     * aktualizuje polohu hrace na mape
     */
    public void update() {
        Prostor aktualniProstor = hra.getHerniPlan().getAktualniProstor();
        panelVychodu.getItems().clear();
        panelVychodu.getItems().addAll(aktualniProstor.getVychody());

        hrac.setLayoutX(souradnice.get(aktualniProstor.getNazev()).getX());
        hrac.setLayoutY(souradnice.get(aktualniProstor.getNazev()).getY());

    }

    /**
     * Metoda spojuje stringove nazvy mistnosti s pozici na mape.
     * @return Hashmapa nazvu mistnosti a pozice na mape.
     */
    private Map<String, Point2D> createSouradnice(){
        Map<String, Point2D> souradnice = new HashMap<>();
        souradnice.put("pokoj", new Point2D(188, 268));
        souradnice.put("chodba", new Point2D(188, 211));
        souradnice.put("koupelna", new Point2D(266,211));
        souradnice.put("hala", new Point2D(188,157));
        souradnice.put("kuchyn", new Point2D(121,157));
        souradnice.put("obyvaci_pokoj", new Point2D(265, 158));
        souradnice.put("krizovatka", new Point2D(188,95));
        souradnice.put("patrikuv_dum", new Point2D(43,95));
        souradnice.put("obchod", new Point2D(261,94));
        souradnice.put("ericin_dum", new Point2D(348,93));
        souradnice.put("klub", new Point2D(188,30));
        souradnice.put("park", new Point2D(129,96));

        return souradnice;
    }

    /**
     * Metoda vypise vysledek prikazu do pole vystupu.
     * @param prikaz
     */

    public void zpracujPrikaz(String prikaz){
        vystup.appendText("Příkaz: "+prikaz+"\n");
        String vysledek = hra.zpracujPrikaz(prikaz);
        vystup.appendText(vysledek+"\n\n");

        if(hra.konecHry()){
            vypnoutGui();
        }
    }

    /**
     * Metoda pro prechod mezi mistnosti pomoci myši.
     * @param mouseEvent
     */
    public void vybranVychod(MouseEvent mouseEvent) {
        Prostor vybranyProstor = (Prostor) panelVychodu.getSelectionModel().getSelectedItem();
        if(vybranyProstor != null) {
            String prikaz = "jdi " + vybranyProstor.getNazev();
            zpracujPrikaz(prikaz);
        }
    }

    /**
     * Metoda spousti novou hru pomoci metody initialize, zaroven resetuje vsechna vstupni a vystupni pole.
     * @param mouseEvent
     */
    public void novaHra(ActionEvent mouseEvent){
        vystup.clear();
        vstup.clear();
        vypisBatoh.getChildren().clear();
        panelVychodu.getItems().clear();
        vstup.setDisable(false);
        vypisBatoh.setDisable(false);
        panelVychodu.setDisable(false);
        initialize();
    }

    /**
     * Metoda otevira nove okno a zobrazuje napovedu
     * @param mouseEvent
     * @throws Exception
     */
    public void napoveda(ActionEvent mouseEvent) throws Exception{
            Pane napoveda = FXMLLoader.load(getClass().getResource("/napoveda.fxml"));

            WebView webView = new WebView();
            WebEngine webEngine = webView.getEngine();
            webEngine.load(getClass().getResource("/napoveda.html").toString());
            napoveda.getChildren().addAll(webView);

            Stage stage = new Stage();
            stage.setScene(new Scene(napoveda));
            stage.show();

    }

    /**
     * Metoda vypina prvky gui po skonceni hry.
     */
    public void vypnoutGui(){
        vystup.appendText(hra.vratEpilog());
        vstup.setDisable(true);
        odesli.setDisable(true);
        panelVychodu.setDisable(true);
        vypisBatoh.setDisable(true);
    }

    /**
     * Metoda zneviditelni vsechny Imageview ktere se tykaji obleceni postavy.
     */
    public void setInvisible() {
        trikoOn.setVisible(false);
        kalhotyOn.setVisible(false);
        botyOn.setVisible(false);
        trikoFanousek.setVisible(false);
    }

}
