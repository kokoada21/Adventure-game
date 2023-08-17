package cz.vse.java.jesa02.adventuraSem.logika;


/**
 * Třída příkazu oslov.
 * Tato třída je součástí textové hry.
 * @Adam Ješina
 * LS 2020
 */
public class PrikazOslov implements IPrikaz
{
private static final String NAZEV = "oslov";
    private HerniPlan plan;
    private Hra hra;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "sbírat"
    *  @param hra odkaz na hru, která může být za jistých podmínek ukončena
    */    
    public PrikazOslov(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.hra = hra;
    }

    /**
    * Provádí příkaz "oslov". Testuje zda hráč zadal obě slova příkazu.
    * Testuje zda je osoba v místnosti a mění verzi odpovědi na základě podmíky oblečení.
    * U postav, které chtějí vyměňovat vrací předměty k výměně.
    * Ukončuje hru v případě nesplnění podmínky oblečení.
    *
    *@param parametry - osoba, kterou si hráč přeje oslovit
    *@return zpráva, kterou vypíše hra hráči v závislosti na vloženém parametru
    */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Koho mam oslovit? Chybi nazev postavy.";
        }
        String jmenoOsoby = parametry[0];       
        Prostor aktualniProstor = plan.getAktualniProstor();        
        
        if(aktualniProstor.obsahujePostavu(jmenoOsoby)){
            Postava clovek = aktualniProstor.vratPostavu(jmenoOsoby);
            Batoh mujBatoh = plan.getBatoh();
            if(mujBatoh.getJsiOblecen()){
            clovek.setHodna();
              if(clovek.chceMenit()){
                        return clovek.getJmenoPost() + " říká: " + clovek.getProslov() + clovek.getPredmetyKVymene();
                    }    
            return clovek.getJmenoPost() + " říká: " + clovek.getProslov();
                
            }    
            else{
            clovek.setZla();
            hra.setKonecHry(true);
            return clovek.getJmenoPost() + " říká: " + clovek.getProslov() + "\n" + "Priste se oblekni!";            
            }
        }
        return "Tato osoba tu neni";    
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
