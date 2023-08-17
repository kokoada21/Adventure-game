package cz.vse.java.jesa02.adventuraSem.logika;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková, Adam Ješina
 * @version  LS 2020
 */
public class HraTest {
    private Hra hra1;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @BeforeEach
    public void setUp() {
        hra1 = new Hra();
    }


    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, konkrétně případ prohry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Po oslovení otce bez oblečení hra končí neúspěchem.
     * 
     */
    @Test
    public void testProhry() {
        assertEquals("pokoj", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi chodba");
        assertEquals(false, hra1.konecHry());
        assertEquals("chodba", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi hala");
        assertEquals(false, hra1.konecHry());
        assertEquals("hala", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi kuchyn");
        assertEquals(false, hra1.konecHry());
        assertEquals("kuchyn", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("oslov otec");
        assertEquals(true, hra1.konecHry());
    }
    
    /**
     * Testuje vítězný průběh hry.
     * 
     */
    @Test
    public void testVyhry(){
        assertEquals("pokoj", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("seber kalhoty");
        hra1.zpracujPrikaz("seber triko");
        hra1.zpracujPrikaz("oblekni triko");
        hra1.zpracujPrikaz("oblekni kalhoty");
        hra1.zpracujPrikaz("jdi chodba");
        hra1.zpracujPrikaz("jdi hala");
        hra1.zpracujPrikaz("jdi kuchyn");
        hra1.zpracujPrikaz("jdi kuchyn");
        hra1.zpracujPrikaz("seber klice");
        hra1.zpracujPrikaz("jdi hala");
        hra1.zpracujPrikaz("seber boty");
        hra1.zpracujPrikaz("oblekni boty");
        hra1.zpracujPrikaz("jdi krizovatka");
        hra1.zpracujPrikaz("jdi obchod");
        hra1.zpracujPrikaz("seber chleba");
        hra1.zpracujPrikaz("jdi krizovatka");
        hra1.zpracujPrikaz("jdi park");
        hra1.zpracujPrikaz("vymen chleba tulak");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi patrikuv_dum");
        hra1.zpracujPrikaz("jdi klub");
        hra1.zpracujPrikaz("seber mobil");
        assertEquals(true, hra1.konecHry());
    }
}
