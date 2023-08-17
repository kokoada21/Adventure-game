package cz.vse.java.jesa02.adventuraSem.logika;


/**
 * Třída příkazu vyměň.
 * Tato třída je součástí textové hry.
 * @Adam Ješina
 * LS 2020
 */
public class PrikazVymen implements IPrikaz
{
private static final String NAZEV = "vymen";
    private HerniPlan plan;
    private Hra hra;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře směňovat.
    *  @param hra odkaz na hru, která může být za jistých podmínek ukončena.
    */   
    public PrikazVymen(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.hra = hra;
    }

    /**
    * Provádí příkaz "vymen". Testuje zda hráč zadal všechna tři slova příkazu
    * testuje zda je vec v inventari, zda je osoba v místnosti a je ochotna něco směnit.
    * po splnění podmínek maže věc, kterou hráč dává, z batohu a vkládá požadovanou věc
    * úspěšná směna je možná provést pouze jednou, dále už postava směnit nechce
    * ukončuje hru v případě, že není splněna podmínka oblečení
    *
    *@param parametry - vec, kterou si hrac přeje dát, osoba s kterou směňuje
    *@return zpráva, kterou vypíše hra hráči v závislosti na vloženém parametru
    */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mam dat?";
        }
        if (parametry.length == 1) {
            return "S kym mam vymenit?";
        }
        String predavanaVec = parametry[0];
        String komuDavam = parametry[1];
        Prostor aktualniProstor = plan.getAktualniProstor();        
        
        if(aktualniProstor.obsahujePostavu(komuDavam)){
            Postava clovek = aktualniProstor.vratPostavu(komuDavam);
            Batoh mujBatoh = plan.getBatoh();
            if (mujBatoh.getJsiOblecen()){
                clovek.setHodna();
                if(clovek.chceMenit()){
                    Vec coChce = clovek.getCoChce();
                    Vec coMa = clovek.getCoMa();
                    if(predavanaVec.equals(coChce.getNazev())){
                        if(mujBatoh.obsahujeVec(predavanaVec)){                           
                        mujBatoh.zahodVec(predavanaVec); 
                        mujBatoh.vlozBatoh(coMa);
                        clovek.setUzVymenil();
                        return "Vymenil si " + predavanaVec + " za " + coMa.getNazev();
                        }
                        else{return predavanaVec + " neni ve tvem batohu";
                        }
                    }
                    else{
                        return "Tuto vec " + clovek.getJmenoPost() + " nechce";
                    }
                }
                else{
                    return clovek.getJmenoPost() + " s tebou nechce nic smenit.";            
                }
                
            }
            else{
               clovek.setZla();
               hra.setKonecHry(true);
               return clovek.getJmenoPost() + " říká: " + clovek.getProslov() + "\n" + "Priste se oblekni, byl jsi odhalen!"; 
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
