package cz.vse.java.jesa02.adventuraSem.logika;


/**
 * Třída příkazu seber.
 * Tato třída je součástí textové hry.
 * @Adam Ješina
 * @1.0
 */
public class PrikazSeber implements IPrikaz
{
private static final String NAZEV = "seber";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "sbírat" 
    */    
    public PrikazSeber(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Provádí příkaz "seber". Testuje zda hráč zadal obě slova příkaz
     * testuje zda je vec v místnosti a je prenositelna
     * ověřuje místo v batohu
     *
     *@param parametry - vec, kterou si hrac preje sebrat
     *@return zpráva, kterou vypíše hra hráči v závislosti na vloženém parametru
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám sebrat? Chybí název věci";
        }
        String nazevSbiraneVeci = parametry[0];       
        Prostor aktualniProstor = plan.getAktualniProstor();
        Batoh mujBatoh = plan.getBatoh();  
        if(aktualniProstor.obsahujeVec(nazevSbiraneVeci)){            
            Vec sbiranaVec = aktualniProstor.vzitVec(nazevSbiraneVeci);
            if (sbiranaVec.isPrenositelna()){
                
                    if(mujBatoh.neniPlny()){
                    mujBatoh.vlozBatoh(sbiranaVec);
                    mujBatoh.getWinVeci();
                    return sbiranaVec.getNazev() + " je v batohu";
                    }                    
                    else{
                     aktualniProstor.vlozVec(sbiranaVec);
                     return "Batoh je plny";  
                    }
              }
              return "Vec nejde sebrat";
            }
         return "Vec tady neni";
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
