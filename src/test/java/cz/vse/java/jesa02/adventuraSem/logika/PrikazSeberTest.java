package cz.vse.java.jesa02.adventuraSem.logika;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovací třída PrikazVymenTest.
 *
 * @author  Adam Ješina
 * @version LS 2020
 */
public class PrikazSeberTest
{
    private HerniPlan plan;
    private Prostor prostor;
    private Vec klice;
    private Vec penezenka;
    private Vec mobil;
    private Vec stul;
    private Batoh mujBatoh;
    

    public PrikazSeberTest()
    {
    }

    /**
     * Metoda se provede před spuštěním testovacích metod
     * vytváří objekty pro testování
     */
    @BeforeEach
    public void setUp()
    { 
        Hra hra = new Hra();
        plan = hra.getHerniPlan();
        mujBatoh = plan.getBatoh();
        prostor = new Prostor("pokoj","Tvuj pokoj");
        plan.setAktualniProstor(prostor);       
        mobil = new Vec("mobil", true, false);
        penezenka = new Vec("penezenka", true, false);
        klice = new Vec("klice", true, false);
        stul = new Vec("stul", false, false);

    }

    @AfterEach
    public void tearDown()
    {
    }

    
    /**
     * Metoda testuje prenositelne a neprenositelne veci. Přenositelné věci mizí z místnosti a přesouvají se do batohu.
     */
    
    @Test
    public void prenositelnostTest()
    {        
        prostor.vlozVec(stul);
        prostor.vlozVec(mobil);
        PrikazSeber prikazSe1 = new PrikazSeber(plan);
        assertEquals("Vec nejde sebrat", prikazSe1.provedPrikaz("stul")); 
        assertEquals(true, prostor.obsahujeVec("stul"));
        assertEquals("mobil je v batohu", prikazSe1.provedPrikaz("mobil")); 
        assertEquals(false, prostor.obsahujeVec("mobil"));
        assertEquals(true, mujBatoh.obsahujeVec("mobil"));  
    }
    
    /**
     * Metoda testuje výherní předměty. Samotné ukončení se děje ve tříde Hra. 
     */
    @Test
    public void vyherniVeciTest()
    {        
        prostor.vlozVec(penezenka);
        prostor.vlozVec(mobil);
        prostor.vlozVec(klice);
        
        PrikazSeber prikazSe1 = new PrikazSeber(plan);
        assertEquals("penezenka je v batohu", prikazSe1.provedPrikaz("penezenka")); 
        assertEquals(false, mujBatoh.getWinVeci());
        
        assertEquals("klice je v batohu", prikazSe1.provedPrikaz("klice")); 
        assertEquals(false, mujBatoh.getWinVeci());
        
        assertEquals("mobil je v batohu", prikazSe1.provedPrikaz("mobil")); 
        assertEquals(true, mujBatoh.getWinVeci());

    }

}


