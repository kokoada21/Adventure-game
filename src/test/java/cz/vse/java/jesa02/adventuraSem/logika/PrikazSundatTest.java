package cz.vse.java.jesa02.adventuraSem.logika;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovací třída PrikazSundatTest.
 *
 * @author  Adam Ješina
 * @version LS 2020
 */
public class PrikazSundatTest
{
    private HerniPlan plan;
    private Batoh mujBatoh;
    private Vec vec;

    public PrikazSundatTest()
    {
    }

    /**
     * Metoda se provede před spuštěním testovacích metod
     * vytváří objekty pro testování
     */
    @BeforeEach
    public void setUp()
    { 
        plan = new HerniPlan();
        mujBatoh = plan.getBatoh();
        vec = new Vec("triko", true, true);
        mujBatoh.vlozBatoh(vec);
        assertEquals(true, mujBatoh.obsahujeVec("triko"));
        
        PrikazOblect prikazOb1 = new PrikazOblect(plan);
        assertEquals("Oblekl sis triko", prikazOb1.provedPrikaz("triko"));
        assertEquals(false, mujBatoh.obsahujeVec("triko"));
    }

    @AfterEach
    public void tearDown()
    {
    }

    /**
    *Metoda testuje dva případy: 
    *věc se úspěšně sundá a vloží do batohu
    *požadovaná věc v batohu není    
    */
    @Test
    public void SundejTest()
    {        
        PrikazSundat prikazSu1 = new PrikazSundat(plan);
        assertEquals("Sundal sis triko vec vsak zustava v batohu.", prikazSu1.provedPrikaz("triko")); 
        assertEquals(true, mujBatoh.obsahujeVec("triko"));
        PrikazSundat prikazSu2 = new PrikazSundat(plan);
        assertEquals("Tuto vec nemas na sobe.", prikazSu1.provedPrikaz("kalhoty")); 
    }
}

