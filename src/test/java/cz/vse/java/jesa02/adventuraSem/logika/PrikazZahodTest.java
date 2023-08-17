package cz.vse.java.jesa02.adventuraSem.logika;



import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**
 * Testovací třída PrikazZahodTest.
 *
 * @author  Adam Ješina
 * @version LS 2020
 */
public class PrikazZahodTest
{
    private HerniPlan plan;
    private Prostor prostor; 
    private Batoh mujBatoh;
    private Vec vec;
    public PrikazZahodTest()
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
        prostor = new Prostor("pokoj","tvuj pokoj");
        plan.setAktualniProstor(prostor);
        mujBatoh = plan.getBatoh();
        vec = new Vec("triko", true, true);
        mujBatoh.vlozBatoh(vec);
    }

    @AfterEach
    public void tearDown()
    {
    }

    /**
     * Metoda testuje zda po zahození věci věc zmizí z batohu a vloží se do aktuální místnosti
     * testuje také neúspěšné zahození v případě, že se věc nenachází v hráčově batohu
    */
    @Test
    public void zahodTest()
    {
        PrikazZahod prikazZa1 = new PrikazZahod(plan);
        assertEquals("triko mizi z batohu a zustava v pokoj", prikazZa1.provedPrikaz("triko"));
        assertEquals(false, mujBatoh.obsahujeVec("triko"));
        assertEquals(true, prostor.obsahujeVec("triko"));
        
        assertEquals("Tuto vec v batohu nemas", prikazZa1.provedPrikaz("triko")); 
    }
}

