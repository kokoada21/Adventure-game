package cz.vse.java.jesa02.adventuraSem.logika;



import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovací třída PrikazOslovTest.
 *
 * @author  Adam Ješina
 * @version LS 2020
 */
public class PrikazInventarTest
{
    private HerniPlan plan;
    private Batoh mujBatoh;
    private Vec vec;
    /**
     * Konstruktor testovací třídy PrikazOslov
     */
    public PrikazInventarTest()
    {   plan = new HerniPlan();
        mujBatoh = plan.getBatoh();
        vec = new Vec("triko", true, true);
    }



    /**
    * Metoda testuje správnou návratovou hodnotu pro příkaz inventar v zásvislosti na obsahu batohu a oblecených předmětů
    */
    @Test
    public void inventarTest()
    {
        PrikazInventar prikazIn1 = new PrikazInventar(plan);
        assertEquals("Nic v batohu nemas Obleceni: Nic na sobě nemáš", prikazIn1.provedPrikaz());
        
        mujBatoh.vlozBatoh(vec);
        assertEquals("Obsah batohu: triko  Obleceni: Nic na sobě nemáš", prikazIn1.provedPrikaz());
        
        PrikazOblect prikazOb1 = new PrikazOblect(plan);
        assertEquals("Oblekl sis triko", prikazOb1.provedPrikaz("triko"));
        
        assertEquals("Nic v batohu nemas Obleceno: triko ", prikazIn1.provedPrikaz());
    }
}

