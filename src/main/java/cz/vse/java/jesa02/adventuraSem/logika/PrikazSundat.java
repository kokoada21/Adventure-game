package cz.vse.java.jesa02.adventuraSem.logika;


/**
 * Třída příkazu sundej.
 * Tato třída je součástí textové hry.
 * @Adam Ješina
 * LS 2020
 */
public class PrikazSundat implements IPrikaz
{
private static final String NAZEV = "sundej";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "sundávat" 
    */    
    public PrikazSundat(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "sundej". Testuje zda hráč zadal obě části příkazu
     *  kontroluje zda je věc oblečena
     *  odebírá předmět s kolekce oblečení a vkládá je do inventáře
     *
     *@param parametry - jako  parametr obsahuje jméno oblečení, které si hráč přeje sundat
     *@return zpráva, kterou vypíše hra hráči v závislosti na vstupech
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám sundat? Chybí název věci";
        }
        String nazevSundavaneVeci = parametry[0];
        Batoh mujBatoh = plan.getBatoh();
        
        if(mujBatoh.isObleceno(nazevSundavaneVeci)){
            if(mujBatoh.neniPlny()){
            Vec vecDoBatohu = mujBatoh.sundejObleceni(nazevSundavaneVeci);
            mujBatoh.vlozBatoh(vecDoBatohu);
            return "Sundal sis " + nazevSundavaneVeci + " vec vsak zustava v batohu.";
            }
            return "Batoh je plny";
            }
        return "Tuto vec nemas na sobe.";                    
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
