package cz.vse.java.jesa02.adventuraSem.logika;



import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovací třída PrikazOslovTest.
 *
 * @author  Adam Ješina
 * @version LS 2020
 */
public class PrikazOslovTest
{
    private HerniPlan plan;
    private Hra hra;
    private Vec triko;
    private Vec kalhoty;
    private Batoh mujBatoh;
    

    public PrikazOslovTest()
    {
        hra = new Hra();
        plan = hra.getHerniPlan();
        mujBatoh = plan.getBatoh();
        Prostor prostor = new Prostor("pokoj","Tvuj pokoj");
        plan.setAktualniProstor(prostor);
        Postava postava = new Postava("otec", false, "ahoj", "zle ahoj");
        triko = new Vec("triko", true, true);
        kalhoty = new Vec("kalhoty", true, true);
        mujBatoh.vlozBatoh(kalhoty);
        mujBatoh.vlozBatoh(triko);
        prostor.vlozPostavu(postava);
    }


    /**
    * Metoda testuje dvě možnosti výsledku příkazu oslov:
    * úspěšně oslovení s kladnou odpovědí - hra pokračuje
    * oslovení nepřítomné postavy s chybovou hláškou
    */
    @Test
    public void oslovTest()
    {        
        mujBatoh.oblect(triko);
        mujBatoh.oblect(kalhoty);
        
        PrikazOslov prikazOs1 = new PrikazOslov(plan, hra);
        assertEquals("otec říká: ahoj", prikazOs1.provedPrikaz("otec")); 
        assertEquals(false, hra.konecHry());
        
        PrikazOslov prikazOs2 = new PrikazOslov(plan, hra);
        assertEquals("Tato osoba tu neni", prikazOs2.provedPrikaz("matka"));
        assertEquals(false, hra.konecHry());
    }
    
    /**
    * Metoda testuje podmínku oblečení:
    * v případě neoblečeného hráče vrací špatnou odpověď a ukončuje hru
    */
    @Test
    public void oslovTest2()
    {               
        PrikazOslov prikazOs1 = new PrikazOslov(plan, hra);
        assertEquals("otec říká: zle ahoj" + "\n" + "Priste se oblekni!", prikazOs1.provedPrikaz("otec"));
        assertEquals(true, hra.konecHry());
        
    }

}


