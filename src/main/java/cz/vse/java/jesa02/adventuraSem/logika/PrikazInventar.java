package cz.vse.java.jesa02.adventuraSem.logika;

/**
 * Třída příkazu inventář.
 * Tato třída je součástí textové hry.
 * @Adam Ješina
 * LS 2020
 */
public class PrikazInventar implements IPrikaz
{
private static final String NAZEV = "inventar";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře zobrazovat inventář.
    */  
    public PrikazInventar(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * V případě, že příkaz má jen jedno slovo "inventar"
     * jinak pokračuje např. při zadání "inventar a".
     * 
     * @return obsah batohu a oblečení, které má hráč na sobě
     */
    @Override
    public String provedPrikaz(String... parametry) {
      Batoh mujBatoh = plan.getBatoh();
      return mujBatoh.getSeznamVeci();
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */    
    public String getNazev(){       
       return NAZEV; 
    }
}
