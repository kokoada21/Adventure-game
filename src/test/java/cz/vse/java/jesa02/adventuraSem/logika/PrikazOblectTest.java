package cz.vse.java.jesa02.adventuraSem.logika;



import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Testovací třída PrikazOslovTest.
 *
 * @author  Adam Ješina
 * @version LS 2020
 */
public class PrikazOblectTest
{
    private HerniPlan plan;
    private Batoh mujBatoh;
    /**
     * Konstruuktor testovací třídy
     */
    public PrikazOblectTest()
    {
        plan = new HerniPlan();
        mujBatoh = plan.getBatoh();
        Vec vec = new Vec("triko", true, true);
        mujBatoh.vlozBatoh(vec);
        mujBatoh.oblect(vec);
    }

    
    /**
     * Metoda testuje navratové hodnoty příkazu oblect a zkouší zda je oblečená věc přítomna v kolekci oblečeno.
    */
    @Test
    public void oblectTest()
    {
        PrikazOblect prikazOb1 = new PrikazOblect(plan);
        assertEquals("Oblekl sis triko", prikazOb1.provedPrikaz("triko"));
        assertEquals(true, mujBatoh.isObleceno("triko"));
        
        PrikazOblect prikazOb2 = new PrikazOblect(plan);
        assertEquals("Vec neni v batohu", prikazOb2.provedPrikaz("kalhoty"));
        assertEquals(false, mujBatoh.isObleceno("kalhoty"));
    }
}

