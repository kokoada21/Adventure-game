package cz.vse.java.jesa02.adventuraSem.logika;
 

import cz.vse.java.jesa02.adventuraSem.main.Observer;
import cz.vse.java.jesa02.adventuraSem.main.Subject;

import java.util.*;

public class Batoh implements Subject
/**
     *  Tato třída definuje objekt batoh, jehož instance provází hráče po celou hru.
     *  Do batohu je možné vkládat a odebírat z něj věci. Zároveň funguje jako jakýsi inventář, vyvolávaný příkazem "PrikazInventar".
     *  Implementuje rozhrani Subject pro gui.
     *@author     Adam Ješina
     *@version    LS 2020
 **/
{
    private Set<Vec> seznamVeci;
    private Set<Vec> obleceno;
    public static final int velikostBatohu = 4;
    private Set<Observer> seznamPozorovatelu;
     /**
     *  Konstruktor třídy Batoh, vytváří dvě kolekce, a to množinu věcí v batohu a  právě oblečených věcí.
     */
    public Batoh()
    {
       seznamVeci = new HashSet<Vec>();
       obleceno = new HashSet<Vec>();
       seznamPozorovatelu = new HashSet<Observer>();
    }
    
    /**
     *  Přídává novou věc do kolekce obleceno.
     */
    public void oblect(Vec obleceni){
           notifyObservers();
           obleceno.add(obleceni);
                  
    }
    
     /**
     *  Odebírá věc z kolekce obleceno.
     */
    public void svlect(Vec obleceni){
        obleceno.remove(obleceni);
                  
    }
    
     /**
     *  Vrátí boolean funkci ověřující, zda je daná věc v kolekci obleceno.
     */
    public boolean isObleceno(String nazev){
        for (Vec obleceni :obleceno){
          if(obleceni.getNazev().equals(nazev)){
            return true;
            }
           
        }
        return false;
    }
    
     /**
     *  Vrátí boolean hodnotu zda je v kolekci "obleceno" dostatečný počet objektů. 
     *  Metoda používá například příkaz "PriikazOslov", kterýv jistém případě může i hru ukončit.
     */
    public boolean getJsiOblecen(){
        int obleceni = 0;
        for(Vec vec :obleceno){
                   obleceni += 1;
               }
        if (obleceni < 2){
         return false;
        }
        return true; 
    }
    
     /**
     *  Metoda mažea zároveň vrací z kolekce "obleceno" objekt na základě zadané hodnoty String 
     */
    public Vec sundejObleceni(String nazev){
       Vec sundanaVec = null;
       for (Vec neco :obleceno){
           if(neco.getNazev().equals(nazev)){
            sundanaVec = neco;
            }
        }
       if(sundanaVec != null) {
        obleceno.remove(sundanaVec);
        }
       notifyObservers();
       return sundanaVec; 
    }
    
     /**
     *  Ověřuje, zda počet objektů nepřesáhl static final proměnnou "velikostBatohu"
     */
    public boolean neniPlny()
    { 
      if(seznamVeci.size() < velikostBatohu){
      return true; 
      }
      return false; 
    }
    
     /**
     *  Vkládá věc do batohu
     */
    public boolean vlozBatoh(Vec vec){
      if(neniPlny()){
          seznamVeci.add(vec);
          notifyObservers();
          return true;

        }
      return false;
    }
    
     /**
     *  Maže věc z batohu a zároveň ji vrací. Metoda je použitá například v příkazu "PrikazZahod", který zahazovaný objekt umisťuje do aktulního prostoru
     */
    public Vec zahodVec(String nazev){
       Vec zahozenaVec = null;
       for (Vec neco :seznamVeci){
           if(neco.getNazev().equals(nazev)){
            zahozenaVec = neco;
            }
        }
       if(zahozenaVec != null) {
        seznamVeci.remove(zahozenaVec);

        }
        notifyObservers();
       return zahozenaVec; 
    }
    
    /**
     *  Metoda vrací věc z batohu zadanou hodnotou String.
     */
    public Vec vratVec(String nazev){
       Vec vracenaVec = null;
       for (Vec neco :seznamVeci){
           if(neco.getNazev().equals(nazev)){
            vracenaVec = neco;
            }
        }
       return vracenaVec; 
    }
   
    
    /**
     *  Vrátí boolean funkci zda je věc v batohu.
     */
    public boolean obsahujeVec(String nazev){
        for (Vec vec :seznamVeci){
          if(vec.getNazev().equals(nazev)){
            return true;
            }
           
        }
        return false;
    }
    
     /**
     *  Vrací kompletní výpis předmětů, které má hráč u sebe a na sobě. Používá příkaz PrikazInventar
     */
    public String getSeznamVeci(){
        String obsahBatohu;
        String vsechnoObleceni = null;
        if(seznamVeci.isEmpty()){
         obsahBatohu = "Nic v batohu nemas";         
        }
        
        else{
         obsahBatohu = "Obsah batohu: ";
           for(Vec vec :seznamVeci){
               obsahBatohu += vec.getNazev() + " ";
               }
            }
        
        if(obleceno.isEmpty()) {
            vsechnoObleceni = "Obleceni: Nic na sobě nemáš";
                }
        else{
         vsechnoObleceni = "Obleceno: ";
            for(Vec obleceni :obleceno){
                vsechnoObleceni += obleceni.getNazev() + " ";
                }
             }
        return obsahBatohu + " " + vsechnoObleceni;

    }   /**
        *Vraci kolekci veci v batohu.
        */
        public Collection<Vec> getVeciBatoh(){
         return seznamVeci;
        }

        /**
        *Vraci kolekci oblecenych veci.
        */
        public Collection<Vec> getobleceniBatoh(){
        return obleceno;
    }
    
     /**
     *  Metoda pro ověření obsahu batohu o vítězné předměty. Používá příkazem "PrikazSeber", která po sebrání posledního hledaného předmětu ukončuje hru.
     */
    public boolean getWinVeci(){
        int viteznePredmety = 0;
        for (Vec neco :seznamVeci){
           if(neco.getNazev().equals("mobil") || neco.getNazev().equals("klice") || neco.getNazev().equals("penezenka")){
            viteznePredmety += 1;
            }
        }
        if(viteznePredmety == 3){
        return true;
        }
        return false; 
    }

    /**
     * reistrace pozorovatele, metoda rozhrani Subject
     * @param observer
     */
    @Override
    public void register(Observer observer) {
        seznamPozorovatelu.add(observer);
    }

    /**
     * odebrani pozorovatele, metoda rozhrani Subject
     * @param observer
     */
    @Override
    public void unregister(Observer observer) {
        seznamPozorovatelu.remove(observer);
    }

    /**
     * Metoda, která upozornuje observers na zmenu sv subjectu.
     */
    @Override
    public void notifyObservers() {
      for (Observer observer: seznamPozorovatelu) {
          observer.updateBatoh();
      }
    }

}
