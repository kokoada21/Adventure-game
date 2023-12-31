package cz.vse.java.jesa02.adventuraSem.logika;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Adam Ješina
 *@version    LS 2020
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazInventar(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazZahod(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazOblect(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazOslov(herniPlan, this));
        platnePrikazy.vlozPrikaz(new PrikazSundat(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazVymen(herniPlan, this));
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "Vítej!\n" +
               "Je ráno a probouzíš se ve svém pokoji. Tvůj pohled je rozostřený a ve \n" +
                "spáncích ti tepe tupá bolest. \n" +
               "Vše je najednou jasné. Jsi po party a máš pořádnou kocovinu. \n" +
               "Rozhlížíš se, tvůj instinkt hledá tři věci: mobil, klíče a peněženku.\n" +
               "Nejsou tu!  \n" +
               "Napište 'nápověda', pokud si nevíte rady, jak hrát dál.\n" +
               "\n" +
               herniPlan.getAktualniProstor().dlouhyPopis();
    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Díky, že jste si zahráli.";
    }
    


    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */

    public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
            parametry[i]= slova[i+1];   
         } 
        String textKVypsani=" .... ";
         if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
        
            
          if(isVyhra()){
            textKVypsani="Nasel jsi vsechny ztracene veci, vyhrál jsi!";
            konecHry = true;
        
          }
        }
        else {
            textKVypsani="Nevím co tím myslíš? Tento příkaz neznám. ";
          }
          
        return textKVypsani;
    }
    
    /** 
     * Metoda, která ověřuje, zda nastala vítězná podmínka obsahu batohu
     */
    private boolean isVyhra(){
         Batoh mujBatoh = herniPlan.getBatoh();
         if (mujBatoh.getWinVeci()){
            return true;
            
         }
         return false; 
        
     }
            
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec, PrikazOslov,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    public void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /** 
     * Vrací true, pokud hra skončila.
     */
    public boolean konecHry() {
        return konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }
    
}

