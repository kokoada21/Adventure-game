package cz.vse.java.jesa02.adventuraSem.logika;


/**
 * Třída příkazu oblekni.
 * Tato třída je součástí textové hry.
 * @Adam Ješina
 * LS 2020
 */
public class PrikazOblect implements IPrikaz
{
private static final String NAZEV = "oblekni";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře oblékat.
    */    
    public PrikazOblect(HerniPlan plan) {
        this.plan = plan;
    }

    /**
    * Provádí příkaz "oblekni". Testuje zda hráč zadal obě slova příkaz
    * testuje zda je vec v inventari
    * testuje zda je věc možné obléknout
    * vkládá zahazovanou věc do kolekce obleceni a maže ji z věcí v batohu.
    *
    *@param parametry - vec, kterou si hrac preje oblect
    *@return zpráva, kterou vypíše hra hráči v závislosti na vloženém parametru
    */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám oblect? Chybí název obleceni";
        }
        String nazevObleceni = parametry[0];
        Batoh mujBatoh = plan.getBatoh();        
        if(mujBatoh.obsahujeVec(nazevObleceni)){
            Vec oblekanaVec = mujBatoh.vratVec(nazevObleceni);
            if(oblekanaVec.isObleceni()){
            
            mujBatoh.oblect(oblekanaVec);
            mujBatoh.zahodVec(nazevObleceni);
            return "Oblekl sis " + nazevObleceni;
            }
            return "Tato vec se neda oblect!";
        }
        return "Vec neni v batohu";
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
