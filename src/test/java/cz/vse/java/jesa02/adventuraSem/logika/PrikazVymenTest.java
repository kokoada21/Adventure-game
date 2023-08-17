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
public class PrikazVymenTest
{
    private HerniPlan plan;
    private Postava tulak;
    private Postava otec;
    private Prostor prostor;
    private Hra hra;
    private Vec triko;
    private Vec kalhoty;
    private Vec tuzka;
    private Batoh mujBatoh;
    private Vec chleba;
    private Vec penezenka;
    

    public PrikazVymenTest()
    {
    }

    /**
     * Metoda se provede před spuštěním testovacích metod
     * vytváří objekty pro testování
     */
    @BeforeEach
    public void setUp()
    { 
        hra = new Hra();
        plan = hra.getHerniPlan();
        mujBatoh = plan.getBatoh();
        prostor = new Prostor("pokoj","Tvuj pokoj");
        plan.setAktualniProstor(prostor);
        tulak = new Postava("tulak", false, "ahoj", "zle ahoj");
        otec = new Postava("otec", false, "ahoj", "ahoj");
        
        chleba = new Vec("chleba", true, false);
        penezenka = new Vec("penezenka", true, false);
        tulak.setVymena(penezenka, chleba);
        triko = new Vec("triko", true, true);
        tuzka = new Vec("tuzka", true, false);
        kalhoty = new Vec("kalhoty", true, true);
        prostor.vlozPostavu(tulak);
        prostor.vlozPostavu(otec);

    }

    @AfterEach
    public void tearDown()
    {
    }

    
    /**
     * Metoda testuje úspěšné předání věci.
     * Metoda pěstuje některé případy neúspěšného předání věci: 
     * osoba není v místnosti, osoba měnit nechce, hráč nabízí nesprávnou věc
     */
    
    @Test
    public void vymenTest()
    {        
        mujBatoh.vlozBatoh(kalhoty);
        mujBatoh.vlozBatoh(triko);
        mujBatoh.vlozBatoh(chleba);
        mujBatoh.vlozBatoh(tuzka);
        mujBatoh.oblect(triko);
        mujBatoh.oblect(kalhoty);
        
        PrikazVymen prikazVy1 = new PrikazVymen(plan, hra);
        assertEquals("Vymenil si chleba za penezenka", prikazVy1.provedPrikaz("chleba","tulak")); 
        assertEquals(false, hra.konecHry());
        
        PrikazVymen prikazVy2 = new PrikazVymen(plan, hra);
        assertEquals("Tato osoba tu neni", prikazVy2.provedPrikaz("rizek", "matka"));
        assertEquals(false, hra.konecHry());
        
        PrikazVymen prikazVy3 = new PrikazVymen(plan, hra);
        assertEquals("otec s tebou nechce nic smenit.", prikazVy3.provedPrikaz("chleba", "otec"));
        assertEquals(false, hra.konecHry());
        
        tulak.setVymena(penezenka, chleba);
        PrikazVymen prikazVy4 = new PrikazVymen(plan, hra);
        assertEquals("Tuto vec tulak nechce", prikazVy1.provedPrikaz("tuzka","tulak")); 
        assertEquals(false, hra.konecHry());
    }
    
    /**
     * Metoda testuje neúspěšné předání, podmínka oblečení.
     * V případě, že hráč není oblečený ukončuje hru.
     */    
    @Test
    public void vymenTest2()
    {               
        PrikazVymen prikazVy1 = new PrikazVymen(plan, hra);
        assertEquals("tulak říká: zle ahoj" + "\n" + "Priste se oblekni, byl jsi odhalen!", prikazVy1.provedPrikaz("chleba", "tulak")); 
        assertEquals(true, hra.konecHry());
    }
    
     /**
     * Metoda testuje neúspěšné předání, podmínka: věc musí být v batohu hráče, aby ji mohl směnit.
     */ 
    @Test
    public void vymenTest3()
    {        
        mujBatoh.vlozBatoh(kalhoty);
        mujBatoh.vlozBatoh(triko);
        mujBatoh.oblect(triko);
        mujBatoh.oblect(kalhoty);
        
        PrikazVymen prikazVy1 = new PrikazVymen(plan, hra);
        assertEquals("chleba neni ve tvem batohu", prikazVy1.provedPrikaz("chleba","tulak"));
        assertEquals(false, hra.konecHry());
    }

}


