package cz.vse.java.jesa02.adventuraSem.logika;


/**
 * Třída příkazu zahoď.
 * Tato třída je součástí textové hry.
 * @Adam Ješina
 * LS 2020
 */
public class PrikazZahod implements IPrikaz
{
private static final String NAZEV = "zahod";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře zahazovat.
    */    
    public PrikazZahod(HerniPlan plan) {
        this.plan = plan;
    }

    /**
    * Provádí příkaz "zahod". Testuje zda hráč zadal obě slova příkaz
    * testuje zda je vec v inventari
    * vkládá zahazovanou věc do aktuální místnosti
    *
    *@param parametry - vec, kterou si hrac preje zahodit
    *@return zpráva, kterou vypíše hra hráči v závislosti na vloženém parametru
    */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám zahodit? Chybí název věci";
        }
        String nazevZahazovaneVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Batoh mujBatoh = plan.getBatoh();
        
        if(mujBatoh.obsahujeVec(nazevZahazovaneVeci)){
            Vec zahazovanaVec = mujBatoh.zahodVec(nazevZahazovaneVeci);
            aktualniProstor.vlozVec(zahazovanaVec);
            return nazevZahazovaneVeci + " mizi z batohu a zustava v " + aktualniProstor.getNazev();
            }
        return "Tuto vec v batohu nemas";                    
        }
     
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
