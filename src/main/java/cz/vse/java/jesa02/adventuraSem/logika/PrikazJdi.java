package cz.vse.java.jesa02.adventuraSem.logika;

/**
 * Třída příkazu zahoď.
 * Tato třída je součástí textové hry.
 * @Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Adam Ješina
 * @1.0
 */
class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazJdi(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *  Přidává omezení, východu ven v případě, že si hráč neobuje boty.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        String smer = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);
        Prostor vychozProstor = plan.getAktualniProstor();
        if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!";
        }
        if(vychozProstor.getNazev().equals("hala") && sousedniProstor.getNazev().equals("krizovatka")){          
          Batoh mujBatoh = plan.getBatoh();
          if(mujBatoh.isObleceno("boty")){
            plan.setAktualniProstor(sousedniProstor);
            return sousedniProstor.dlouhyPopis();
            }
          else{return "Mel by sis oblect boty nez pujdes ven.";}
        }
        else {
            plan.setAktualniProstor(sousedniProstor);
            return sousedniProstor.dlouhyPopis();
        }
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
