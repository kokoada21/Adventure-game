package cz.vse.java.jesa02.adventuraSem.logika;



import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testovací třída BatohTest.
 *
 * @author  Adam Ješina
 * @version LS 2020
 */
public class BatohTest
{   private Vec vec1;
    private Vec vec2;
    private Vec vec3;
    private Vec vec4;
    private Vec vec5;
    private Batoh batoh1;
    /**
     *Kontruktor testovací třídy batoh.
    */
    public BatohTest()
    {
        batoh1 = new Batoh();
        vec1 = new Vec("tuzka", true, false);
        vec2 = new Vec("hodinky", true, true);
        vec3 = new Vec("telefon", true, false);
        vec4 = new Vec("sluchatka", true, true);
        vec5 = new Vec("mys", true, false);
    }

    /**
     * Metoda testuje omezenou velikost batohu. 
     * Když se velikost obsahu rovná maximální velikosti batohu, vrací metoda neniPLny() false.
     * Další vkládání do batohu není možné.
     */
    @Test
    public void testVelikostiBatohu()
    {        
        assertEquals(true, batoh1.vlozBatoh(vec1));
        assertEquals(true, batoh1.vlozBatoh(vec2));
        assertEquals(true, batoh1.vlozBatoh(vec3));
        assertEquals(true, batoh1.neniPlny());       
        assertEquals(true, batoh1.vlozBatoh(vec4));
        assertEquals(false, batoh1.neniPlny());
        assertEquals(false, batoh1.vlozBatoh(vec5));
    }
}

